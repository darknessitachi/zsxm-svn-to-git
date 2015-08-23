package com.group.zsxm.service;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.stereotype.Service;

import com.group.core.common.TreeBean;
import com.group.zsxm.entity.Rclb;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.XtDlb;
import com.group.zsxm.exception.BusException;
import com.group.zsxm.service.common.BaseService;

@Service
public class RcdaService extends BaseService{
	
	//private static final String bgbs = "000000000000";
	/**
	 * 获取当前人才 是否修改过数据 （主要通过备份中查询 status=5）
	 * 用于人才登入时判别
	 * @param tablename
	 * @param rcid
	 * @param status
	 * @return
	 */
	public Integer getMaxCount(String tablename,String rcid,String bz){
		String sql_q = "";
		int c = 0;
		if(rcid != null && !rcid.equals("")){
			sql_q = " select count(*) from "+tablename+" where rcid="+rcid+" and status=5 " ;
			c = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		return c;
	}
	
	/**
	 * 获取 变更标识
	 * @param tablename
	 * @param rcid
	 * @param bz
	 * @return
	 */
	public String getBgbs( String rcid ){
		String sql_q = "";
		String bgbs = "";
		if(rcid != null && !rcid.equals("")){
			sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1 " ;
			bgbs = String.valueOf(this.getJdbcTemplate().queryForObject(sql_q, String.class));
		}
		return bgbs;
	}
	
	/**
	 * 获取人才信息
	 * @param rcid
	 * @return
	 */
	public Map preRcxx(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.rcname,a.oldname,a.sex,a.nation,a.jg,CONVERT(varchar(100), a.birthday, 23) birthday,a.zjlb,a.zjno," +
					"a.rclb,a.xl,a.zc,a.zw,a.xw,a.email,a.ptel,a.xxzy,a.sxzy1,a.sxzy2,a.sxzy3,a.cszy,a.byxx,CONVERT(varchar(100), a.byrq, 23) byrq," +
					"a.workunit,a.zgbm,a.dwdq,a.dwxz,a.dwaddr,a.officetel,a.dwcode,a.fax,a.jtaddr,a.jtcode,a.jttel,a.zgbs,a.xsjt,a.hdzz,bgbs,isnull(btgyy,'') btgyy,"+
					"(select dictname from xt_dict b where lbid=20 and a.nation=b.dictbh ) nationmc,"+
					"(select lbmc from rc_rclb b where a.rclb=b.lbdm ) rclbmc, "+
					"(select dictname from xt_dict b where lbid=13 and a.xxzy=b.dictbh ) xxzymc ,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy1=b.dictbh ) sxzy1mc,"+   
					"(select dictname from xt_dict b where lbid=13 and a.sxzy2=b.dictbh ) sxzy2mc,"+
					"(select dictname from xt_dict b where lbid=13 and a.sxzy3=b.dictbh ) sxzy3mc,"+  
					"(select dictname from xt_dict b where lbid=13 and a.cszy=b.dictbh ) cszymc," +
					" (SELECT DICTNAME  FROM XT_DICT AS b WHERE (LBID = 10) AND (a.JG = DICTBH)) AS jgmc " +
					"  from rc_pinfo a where a.rcid="+rcid+" and a.status="+status+" and isNull(a.rcbs,0)=0";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	
	/**
	 * 获取留学国家数据
	 */
	public List<Map<String, String>> getLxgjList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select * from rc_lxgk where rcid="+rcid+" and status=1  order by xh";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	/**
	 * 
	 */
	/**
	 * 人才信息保存
	 * @param rcdarcxx
	 * @param rcdalygj
	 * @param status 1: 工作人员， 2：人才登入
	 * @return
	 */
	public int doSaveRcxx(String rcid ,Integer loginbz, Map<String,String> rcdarcxx,String[] lxgjgjmc,Integer zcbz){
		String sql_q = "",sql_o ="";
		String maxid = rcid;
		
		if(rcid !=null && !rcid.equals("")){
			sql_q = " select count(*) from rc_pinfo where zjno='"+rcdarcxx.get("zjno")+"' and rcid!="+rcid+" and isnull(rcbs,0)=0 and status!=9 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			if(loginbz == 2 && zcbz !=2){//人才登入，将备份(除去人才新注册的信息)
				sql_q = "select count(*) from rc_pinfo where rcid="+rcid +" and status=5 ";
				Integer c = this.getSimpleJdbcTemplate().queryForInt(sql_q);
				if(c == 0){
					sql_o = " insert into rc_pinfo(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel,xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,zgbm,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode,jttel,zgbs,xsjt,hdzz,bgbs,rcbs,info,bz,RCBZ,XZBZ) " +
							" select rcid,5,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel,xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,zgbm,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode,jttel,zgbs,xsjt,hdzz,bgbs,rcbs,info,bz,RCBZ,XZBZ " +
							" from rc_pinfo where rcid="+rcid+" and status=1 and isnull(xzbz,1)=1";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
				
				sql_q = "select count(*) from rc_lxgk where rcid="+rcid +" and status=5 ";
				c = this.getSimpleJdbcTemplate().queryForInt(sql_q);
				if(c == 0){
					sql_o = " insert into rc_lxgk(rcid,xh,status,gjdm,gjmc,xzbz) " +
							" select rcid,xh,5,gjdm,gjmc,xzbz from rc_lxgk where rcid="+rcid+" and status=1 and isnull(xzbz,1)=1 ";
					this.getSimpleJdbcTemplate().update(sql_o);
				}
			}
			
			sql_q = " select isnull(bgbs,'000000000000') BGBS,isnull(INFO,'') INFO,isnull(BZ,'') BZ from rc_pinfo where rcid="+rcid+" and status=1";
			Map m = this.getSimpleJdbcTemplate().queryForMap(sql_q);
			//已经存在进行修改操作
			sql_o = " delete from rc_pinfo where rcid="+rcid+" and status=1";
			this.getSimpleJdbcTemplate().update(sql_o);
			sql_o = " delete from rc_lxgk where rcid="+rcid+" and status=1";
			this.getSimpleJdbcTemplate().update(sql_o);
			//插入人才信息
			sql_o = " insert into rc_pinfo(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
					" xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,zgbm,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
					"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,info,bz,rcbz,xzbz) " +
					"values('"+rcid+"',1,'"+rcdarcxx.get("rcname")+"','"+rcdarcxx.get("oldname")+"','"+rcdarcxx.get("sex")+"','"+rcdarcxx.get("nation")+"'," +
					"'"+rcdarcxx.get("jg")+"',"+(rcdarcxx.get("birthday").equals("")?"null":("'"+rcdarcxx.get("birthday")+"'"))+",'"+rcdarcxx.get("zjlb")+"','"+rcdarcxx.get("zjno")+"'," +
					"'"+rcdarcxx.get("rclb")+"','"+rcdarcxx.get("xl")+"','"+rcdarcxx.get("zc")+"','"+rcdarcxx.get("zw")+"','"+rcdarcxx.get("xw")+"'," +
					"'"+rcdarcxx.get("email")+"','"+rcdarcxx.get("ptel")+"','"+rcdarcxx.get("xxzy")+"','"+rcdarcxx.get("sxzy1")+"','"+rcdarcxx.get("sxzy2")+"'," +
					"'"+rcdarcxx.get("sxzy3")+"','"+rcdarcxx.get("cszy")+"','"+rcdarcxx.get("byxx")+"',"+(rcdarcxx.get("byrq").equals("")?"null":("'"+rcdarcxx.get("byrq")+"'"))+",'"+rcdarcxx.get("workunit")+"'," +
					"'"+rcdarcxx.get("zgbm")+"','"+rcdarcxx.get("dwdq")+"','"+rcdarcxx.get("dwxz")+"','"+rcdarcxx.get("dwaddr")+"','"+rcdarcxx.get("officetel")+"'," +
					"'"+rcdarcxx.get("dwcode")+"','"+rcdarcxx.get("fax")+"','"+rcdarcxx.get("jtaddr")+"','"+rcdarcxx.get("jtcode")+"','"+rcdarcxx.get("jttel")+"'," +
					"'"+rcdarcxx.get("zgbs")+"','"+rcdarcxx.get("xsjt")+"','"+rcdarcxx.get("hdzz")+"','"+String.valueOf(m.get("BGBS"))+"',0,'"+String.valueOf(m.get("INFO"))+"','"+String.valueOf(m.get("BZ"))+"',"+zcbz+","+loginbz+")";
			int xx = this.getSimpleJdbcTemplate().update(sql_o);
			//插入 人才留国信息
			int xh =0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into rc_lxgk(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+rcid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+loginbz+")";
						this.getSimpleJdbcTemplate().update(sql_o);
					}
				}
			}
			
			if(loginbz == 2 && zcbz !=2){
				Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_PINFO'");
				sql_o = " update rc_pinfo set bgbs='"+(String.valueOf(m.get("BGBS")).substring(0,cxh-1)+"1"+String.valueOf(m.get("BGBS")).substring(cxh,String.valueOf(m.get("BGBS")).length()))+"' where rcid='"+rcid+"' and status=1";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
			
			if(loginbz == 1){
				
				//更新人才登入用户
				String rcpass = "";
				if(rcdarcxx.get("zjno").trim().length() <= 6){
					rcpass = rcdarcxx.get("zjno").trim();
				}else{
					rcpass = rcdarcxx.get("zjno").trim().substring(rcdarcxx.get("zjno").trim().length()-6,rcdarcxx.get("zjno").trim().length());
				}
				sql_o = " update RC_user set rcname='"+rcdarcxx.get("rcname")+"',loginname='"+rcdarcxx.get("zjno").trim()+"',password='"+rcpass+"' where xtrcid="+rcid +" and loginname!='"+rcdarcxx.get("zjno").trim()+"'";
				this.getSimpleJdbcTemplate().update(sql_o);
			}

		}else{//新增操作
			sql_q = " select count(*) from rc_pinfo where zjno='"+rcdarcxx.get("zjno")+"' and isnull(rcbs,0)=0 ";
			if(this.getSimpleJdbcTemplate().queryForInt(sql_q)>0){
				throw new BusException("当前的证件号码已存在，请重输！");
			}
			
			maxid = String.valueOf(this.getMaxKey("RC_PINFO"));
			//插入人才信息
			sql_o = " insert into rc_pinfo(rcid,status,rcname,oldname,sex,nation,jg,birthday,zjlb,zjno,rclb,xl,zc,zw,xw,email,ptel," +
			" xxzy,sxzy1,sxzy2,sxzy3,cszy,byxx,byrq,workunit,zgbm,dwdq,dwxz,dwaddr,officetel,dwcode,fax,jtaddr,jtcode," +
			"jttel,zgbs,xsjt,hdzz,bgbs,rcbs,rcbz,xzbz) " +
			"values('"+maxid+"',1,'"+rcdarcxx.get("rcname")+"','"+rcdarcxx.get("oldname")+"','"+rcdarcxx.get("sex")+"','"+rcdarcxx.get("nation")+"'," +
			"'"+rcdarcxx.get("jg")+"',"+(rcdarcxx.get("birthday").equals("")?"null":("'"+rcdarcxx.get("birthday")+"'"))+",'"+rcdarcxx.get("zjlb")+"','"+rcdarcxx.get("zjno")+"'," +
			"'"+rcdarcxx.get("rclb")+"','"+rcdarcxx.get("xl")+"','"+rcdarcxx.get("zc")+"','"+rcdarcxx.get("zw")+"','"+rcdarcxx.get("xw")+"'," +
			"'"+rcdarcxx.get("email")+"','"+rcdarcxx.get("ptel")+"','"+rcdarcxx.get("xxzy")+"','"+rcdarcxx.get("sxzy1")+"','"+rcdarcxx.get("sxzy2")+"'," +
			"'"+rcdarcxx.get("sxzy3")+"','"+rcdarcxx.get("cszy")+"','"+rcdarcxx.get("byxx")+"',"+(rcdarcxx.get("byrq").equals("")?"null":("'"+rcdarcxx.get("byrq")+"'"))+",'"+rcdarcxx.get("workunit")+"'," +
			"'"+rcdarcxx.get("zgbm")+"','"+rcdarcxx.get("dwdq")+"','"+rcdarcxx.get("dwxz")+"','"+rcdarcxx.get("dwaddr")+"','"+rcdarcxx.get("officetel")+"'," +
			"'"+rcdarcxx.get("dwcode")+"','"+rcdarcxx.get("fax")+"','"+rcdarcxx.get("jtaddr")+"','"+rcdarcxx.get("jtcode")+"','"+rcdarcxx.get("jttel")+"'," +
			"'"+rcdarcxx.get("zgbs")+"','"+rcdarcxx.get("xsjt")+"','"+rcdarcxx.get("hdzz")+"','"+(loginbz==1?"000000000000":"111111111111")+"',0,"+zcbz+","+loginbz+")";
			this.getSimpleJdbcTemplate().update(sql_o);
			//插入 人才留国信息
			int xh = 0;
			if(lxgjgjmc != null && lxgjgjmc.length > 0){
				for(int i=0 ;i<lxgjgjmc.length;i++){
					if(!lxgjgjmc[i].trim().equals("")){
						sql_o = " insert into rc_lxgk(rcid,xh,status,gjdm,gjmc,xzbz) " +
								" values('"+maxid+"','"+(++xh)+"',1,'','"+lxgjgjmc[i]+"',"+loginbz+")";
						this.getSimpleJdbcTemplate().update(sql_o);
					}
				}
			}
			
			//插入人才登入用户
			String rcmaxid = String.valueOf(this.getMaxKey("RC_USER"));
			String rcpass = "";
			if(rcdarcxx.get("zjno").trim().length() <= 6){
				rcpass = rcdarcxx.get("zjno").trim();
			}else{
				rcpass = rcdarcxx.get("zjno").trim().substring(rcdarcxx.get("zjno").trim().length()-6,rcdarcxx.get("zjno").trim().length());
			}
			sql_o = " insert into RC_user(rcid,rcname,loginname,password,xtrcid,bz)" +
					" values('"+rcmaxid+"','"+rcdarcxx.get("rcname")+"','"+rcdarcxx.get("zjno").trim()+"','"+rcpass+"','"+maxid+"','"+loginbz+"')";
			this.getSimpleJdbcTemplate().update(sql_o);	

		}
		return Integer.parseInt(maxid);
	}
	
	
	/**
	 * 获取技术专长数据
	 */
	public List<Map<String, String>> getJszcList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
					"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
					"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
					"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
					" from rc_jszc a where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 技术专长插入操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doJszcI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2  && zcbz !=2){//人才登入，将备份(除去人才新注册的信息)
			sql = "select count(*) from rc_jszc where rcid="+rcid +" and status=5  ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_jszc(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
						" select rcid,xh,5,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz from rc_jszc where rcid="+rcid+" and status=1 " +
								" and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		//获取 XH 最大值
		Integer maxxh = 1;
		sql = "select count(*) from rc_jszc where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from rc_jszc where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into rc_jszc(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("sxcd")+"','"+m.get("fx")+"'," +
			"'"+m.get("fsmc")+"','"+m.get("ly")+"','"+m.get("zly")+"','"+m.get("zlymc")+"','"+m.get("lymc")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
		//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_JSZC'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		return 1;
	}
	/**
	 * 修改技术专长取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getJszcU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select a.*,(select dictname from xt_dict b where lbid=16 and a.ly=b.dictbh ) ly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.zly=b.dictbh ) zly_mc," +
			"(select dictname from xt_dict b where lbid=16 and a.fx=b.dictbh ) fx_mc," +
			"(select dictname from xt_dict b where lbid=18 and a.sxcd=b.dictbh ) sxcd_mc," +
			"substring(ly,len(ly)-2,len(ly)) lyother,substring(zly,len(zly)-2,len(zly)) zlyother,substring(fx,len(fx)-2,len(fx)) fxother" +
			" from rc_jszc a where a.rcid="+rcid+" and a.status=1  and a.xh="+xh ;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 技术专长修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doJszcU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from rc_jszc where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_jszc(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
						" select rcid,xh,5,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz from rc_jszc where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		sql = " update rc_jszc set sxcd='"+m.get("sxcd")+"'," +
				" fx='"+m.get("fx")+"',fsmc='"+m.get("fsmc")+"',ly='"+m.get("ly")+"'," +
				"zly='"+m.get("zly")+"',zlymc='"+m.get("zlymc")+"',lymc='"+m.get("lymc")+"' where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_JSZC'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除技术专长操作
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doJszcD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from rc_jszc where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_jszc(rcid,xh,status,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz) " +
						" select rcid,xh,5,sxcd,fx,fsmc,ly,zly,zlymc,lymc,xzbz from rc_jszc where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		if(xh != null && xh.length > 0){
			sql = " delete from rc_jszc where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_JSZC'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 保存人才信息--技术专长
	 * @param rcxx1 熟悉程度
	 * @param rcxx2 领域
	 * @param rcxx3 子领域
	 * @param rcxx4 方向
	 * @param rcxx5 领域名称（领域选择其它时填写）
	 * @param rcxx6 子领域名称（子领域选择其它时填写）
	 * @param rcxx7 方向名称（方向选择其它时填写）
	 * @return
	 */
	public int doSaveJszc(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4,String[] rcxx5,String[] rcxx6,String[] rcxx7){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from rc_jszc where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from rc_jszc where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from rc_jszc where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx2[i].equals("")){//选择领域后才可保存，否则默认剔除
				++xh;
				sql_o = " insert into rc_jszc(rcid,xh,status,sxcd,ly,lymc,zly,zlymc,fx,fsmc) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"','"+rcxx5[i]+"','"+rcxx3[i]+"','"+rcxx6[i]+"','"+rcxx4[i]+"','"+rcxx7[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_JSZC'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取学习简历数据
	 */
	public List<Map<String, String>> getXxjlList(String rcid,Integer status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq," +
					"a.yx,a.sxzy,a.xl,a.xw,a.byjy,a.xh," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 2) AND (a.XL = DICTBH)) AS xl_mc," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 3) AND (a.XW = DICTBH)) AS xw_mc," +
					" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from rc_xxjl a " +
					" where a.rcid="+rcid+" and a.status=1  order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	
	/**
	 * 学习简历新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doXxjlI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2  && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from rc_xxjl where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
						" select rcid,xh,5,brq,erq,yx,sxzy,xl,xw,byjy,xzbz from rc_xxjl where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from rc_xxjl where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from rc_xxjl where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into rc_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+","+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+"," +
			"'"+m.get("yx")+"','"+m.get("sxzy")+"','"+m.get("xl")+"','"+m.get("xw")+"','"+m.get("byjy")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2  && zcbz !=2 ){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XXJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getXxjlU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.yx,a.sxzy,a.xl,a.xw,a.byjy," +
			" (select dictname from xt_dict b where lbid=13 and a.sxzy=b.dictbh ) sxzy_mc from rc_xxjl a " +
			" where a.rcid="+rcid+" and a.status=1 and a.xh='"+xh+"' ";
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 学习简历修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doXxjlU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from rc_xxjl where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
						" select rcid,xh,5,brq,erq,yx,sxzy,xl,xw,byjy,xzbz from rc_xxjl where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update rc_xxjl set brq="+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
				" erq="+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+",yx='"+m.get("yx")+"',sxzy='"+m.get("sxzy")+"'," +
				"xl='"+m.get("xl")+"',xw='"+m.get("xw")+"',byjy='"+m.get("byjy")+"' where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XXJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除学习简历
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doXxjlD(String rcid,String[] xh,Integer loginbz ,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from rc_xxjl where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into rc_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy,xzbz) " +
						" select rcid,xh,5,brq,erq,yx,sxzy,xl,xw,byjy,xzbz from rc_xxjl where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from rc_xxjl where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XXJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 学习简历保存
	 * @param rcid
	 * @param rcxx1 开始日期
	 * @param rcxx2 结束日期
	 * @param rcxx3 院校
	 * @param rcxx4 所学专业
	 * @param rcxx5 学历
	 * @param rcxx6 学位
	 * @param rcxx7 毕业
	 * @return
	 */
	public int doSaveXxjl(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4,String[] rcxx5,String[] rcxx6,String[] rcxx7){
		String sql_q = "",sql_o = "";
		int xh = 0;
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from rc_xxjl where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/*
		sql_q = " select count(*) from rc_xxjl where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from rc_xxjl where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx3.length;i++){
			if(!rcxx3[i].trim().equals("")){//院校非空才保存
				++xh;
				sql_o = " insert into rc_xxjl(rcid,xh,status,brq,erq,yx,sxzy,xl,xw,byjy) " +
						" values('"+rcid+"','"+xh+"',"+status+","+(rcxx1[i].equals("")?"null":("'"+rcxx1[i]+"'"))+","+(rcxx2[i].equals("")?"null":("'"+rcxx2[i]+"'"))+"," +
						"'"+rcxx3[i]+"','"+rcxx4[i]+"','"+rcxx5[i]+"','"+rcxx6[i]+"','"+rcxx7[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XXJL'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取工作简历数据
	 */
	public List<Map<String, String>> getGzjlList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw,a.xh " +
					" from RC_WORDJL a " +
					" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 工作简历新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doGzjlI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_WORDJL where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_WORDJL(rcid,xh,status,brq,erq,dwbm,zw,xzbz) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_WORDJL where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_WORDJL where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_WORDJL where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_WORDJL(rcid,xh,status,brq,erq,dwbm,zw,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+","+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+"," +
			"'"+m.get("dwbm")+"','"+m.get("zw")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_WORDJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getGzjlU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.dwbm,a.zw " +
			" from RC_WORDJL a " +
			" where a.rcid="+rcid+" and a.status=1 and a.xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 工作简历修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doGzjlU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_WORDJL where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_WORDJL(rcid,xh,status,brq,erq,dwbm,zw,xzbz) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_WORDJL where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_WORDJL set brq="+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
				" erq="+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+",dwbm='"+m.get("dwbm")+"',zw='"+m.get("zw")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_WORDJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除工作简历
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doGzjlD(String rcid,String[] xh,Integer loginbz ,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_WORDJL where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_WORDJL(rcid,xh,status,brq,erq,dwbm,zw,xzbz) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_WORDJL where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_WORDJL where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_WORDJL'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}
			

		
		return 1;
	}	
	/**
	 * 保存工作简历 
	 * @param rcid
	 * @param rcxx1 开始日期
	 * @param rcxx2 结束日期
	 * @param rcxx3 所在单位及部门
	 * @param rcxx4 职务
	 * @return
	 */
	public int doSaveGzjl(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_WORDJL where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_WORDJL where rcid="+rcid+" and status="+status;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_WORDJL where rcid="+rcid+" and status="+status;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx3.length;i++){
			if(!rcxx3[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_WORDJL(rcid,xh,status,brq,erq,dwbm,zw) " +
						" values('"+rcid+"','"+xh+"',"+status+","+(rcxx1[i].equals("")?"null":("'"+rcxx1[i]+"'"))+","+(rcxx2[i].equals("")?"null":("'"+rcxx2[i]+"'"))+"," +
						"'"+rcxx3[i]+"','"+rcxx4[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_WORDJL'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取课题项目数据
	 */
	public List<Map<String, String>> getKtxmList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xmmc,a.xmly,a.wcqk, CONVERT(varchar(100), a.wcrq, 23) wcrq,xh," +
					" (SELECT DICTNAME FROM dbo.XT_DICT AS b WHERE  (LBID = 19) AND (a.wcqk = DICTBH)) AS wcqk_mc " +
					" from RC_ZCDDYJ a " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 课题项目新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doKtxmI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZCDDYJ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZCDDYJ(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz) " +
						" select rcid,xh,5,xmmc,xmly,wcqk,wcrq,lx,xzbz from RC_ZCDDYJ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_ZCDDYJ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_ZCDDYJ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_ZCDDYJ(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("xmmc")+"','"+m.get("xmly")+"'," +
			"'"+m.get("wcqk")+"',"+(m.get("wcrq").equals("")?"null":"'"+m.get("wcrq")+"'")+",null,"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZCDDYJ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getKtxmU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xmmc,xmly,wcqk, CONVERT(varchar(100), wcrq, 23) wcrq " +
			" from RC_ZCDDYJ " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 课题项目修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doKtxmU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZCDDYJ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZCDDYJ(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz) " +
						" select rcid,xh,5,xmmc,xmly,wcqk,wcrq,lx,xzbz from RC_ZCDDYJ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_ZCDDYJ set wcrq="+(m.get("wcrq").equals("")?"null":"'"+m.get("wcrq")+"'")+"," +
				" xmmc='"+m.get("xmmc")+"',xmly='"+m.get("xmly")+"',wcqk='"+m.get("wcqk")+"'  " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZCDDYJ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除课题项目
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doKtxmD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZCDDYJ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZCDDYJ(rcid,xh,status,xmmc,xmly,wcqk,wcrq,lx,xzbz) " +
						" select rcid,xh,5,xmmc,xmly,wcqk,wcrq,lx,xzbz from RC_ZCDDYJ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_ZCDDYJ where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZCDDYJ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}		
	/**
	 * 保存课题项目
	 * @param rcid
	 * @param rcxx1 项目或课题名称
	 * @param rcxx2 项目或课题来源
	 * @param rcxx3 完成情况
	 * @param rcxx4 完成时间
	 * @return 
	 */
	public int doSaveKtxm(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_ZCDDYJ where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_ZCDDYJ where rcid="+rcid+" and status="+status;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_ZCDDYJ where rcid="+rcid+" and status="+status;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_ZCDDYJ(rcid,xh,status,xmmc,xmly,wcqk,wcrq) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						"'"+rcxx3[i]+"',"+(rcxx4[i].equals("")?"null":("'"+rcxx4[i]+"'"))+")";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZCDDYJ'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	
	/**
	 * 获取荣誉称号及获奖情况数据
	 */
	public List<Map<String, String>> getRyhjList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select XH,JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ " +
					" from RC_HJQK " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}


	/**
	 * 获取荣誉称号及获奖情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doRyhjI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_HJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_HJQK(rcid,xh,status,jxmc,hjdj,hjrq,xzbz) " +
						" select rcid,xh,5,jxmc,hjdj,hjrq,xzbz  from RC_HJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_HJQK where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_HJQK where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_HJQK(rcid,xh,status,jxmc,hjdj,hjrq) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("jxmc")+"','"+m.get("hjdj")+"'," +
			""+(m.get("hjrq").equals("")?"null":"'"+m.get("hjrq")+"'")+" )";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_HJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);
						
		}

		return 1;
	}
	/**
	 * 修改取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getRyhjU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select JXMC,HJDJ, CONVERT(varchar(100), HJRQ, 23) HJRQ " +
			" from RC_HJQK " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 获取荣誉称号及获奖情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doRyhjU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_HJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_HJQK(rcid,xh,status,jxmc,hjdj,hjrq,xzbz) " +
						" select rcid,xh,5,jxmc,hjdj,hjrq,xzbz  from RC_HJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_HJQK set hjrq="+(m.get("hjrq").equals("")?"null":"'"+m.get("hjrq")+"'")+"," +
				" jxmc='"+m.get("jxmc")+"',hjdj='"+m.get("hjdj")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_HJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除获取荣誉称号及获奖情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doRyhjD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_HJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_HJQK(rcid,xh,status,jxmc,hjdj,hjrq,xzbz) " +
						" select rcid,xh,5,jxmc,hjdj,hjrq,xzbz  from RC_HJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_HJQK where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_HJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}		
	
	/**
	 * 保存荣誉称号及获奖情况
	 * @param rcid
	 * @param rcxx1 奖项名称
	 * @param rcxx2 获奖等级
	 * @param rcxx3 获奖时间
	 * @return
	 */
	public int doSaveRyhj(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_HJQK where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_HJQK where rcid="+rcid+" and status="+status;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_HJQK where rcid="+rcid+" and status="+status;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_HJQK(rcid,xh,status,JXMC,HJDJ,HJRQ) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						""+(rcxx3[i].equals("")?"null":("'"+rcxx3[i]+"'"))+")";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_HJQK'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取产品或技术产业化情况数据
	 */
	public List<Map<String, String>> getCpjsList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select cpmc,scjd,scxsdw,xh " +
					" from RC_CPQSCYH " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 产品或技术产业化情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doCpjsI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_CPQSCYH where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_CPQSCYH(rcid,xh,status,cpmc,scjd,scxsdw,xzbz) " +
						" select rcid,xh,5,cpmc,scjd,scxsdw,xzbz  from RC_CPQSCYH where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_CPQSCYH where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_CPQSCYH where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_CPQSCYH(rcid,xh,status,cpmc,scjd,scxsdw,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("cpmc")+"','"+m.get("scjd")+"', '"+m.get("scxsdw")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_CPQSCYH'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 修改产品或技术产业化情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getCpjsU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select cpmc,scjd,scxsdw,xh " +
			" from RC_CPQSCYH " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 产品或技术产业化情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doCpjsU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_CPQSCYH where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_CPQSCYH(rcid,xh,status,cpmc,scjd,scxsdw,xzbz) " +
						" select rcid,xh,5,cpmc,scjd,scxsdw,xzbz  from RC_CPQSCYH where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_CPQSCYH set cpmc='"+m.get("cpmc")+"'," +
				" scjd='"+m.get("scjd")+"',scxsdw='"+m.get("scxsdw")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_CPQSCYH'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除产品或技术产业化情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doCpjsD(String rcid,String[] xh,Integer loginbz ,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_CPQSCYH where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_CPQSCYH(rcid,xh,status,cpmc,scjd,scxsdw,xzbz) " +
						" select rcid,xh,5,cpmc,scjd,scxsdw,xzbz  from RC_CPQSCYH where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_CPQSCYH where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_CPQSCYH'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}		
	
	/**
	 * 保存产品或技术产业化情况
	 * @param rcid
	 * @param rcxx1 产品或技术名称
	 * @param rcxx2 所处阶段
	 * @param rcxx3 生产销售企业/单位
	 * @return
	 */
	public int doSaveCpjs(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_CPQSCYH where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_CPQSCYH where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_CPQSCYH where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_CPQSCYH(rcid,xh,status,cpmc,scjd,scxsdw) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						"'"+rcxx3[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_CPQSCYH'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取社会兼、聘职情况数据
	 */
	public List<Map<String, String>> getShjzList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm,a.xh " +
					" from RC_SHJZQK a " +
					" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 社会兼、聘职情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doShjzI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_SHJZQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_SHJZQK(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
						" select rcid,xh,5,brq,erq,jsdw,jssf,sm,xzbz  from RC_SHJZQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_SHJZQK where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_SHJZQK where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_SHJZQK(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,"+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+","+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+"," +
					"'"+m.get("jsdw")+"','"+m.get("jssf")+"','"+m.get("sm")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_SHJZQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 *社会兼、聘职情况情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getShjzU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select CONVERT(varchar(100), a.brq, 23) brq,CONVERT(varchar(100), a.erq, 23) erq,a.jsdw,a.jssf,a.sm " +
			" from RC_SHJZQK a " +
			" where a.rcid="+rcid+" and a.status="+status+" and a.xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 社会兼、聘职情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doShjzU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_SHJZQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_SHJZQK(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
						" select rcid,xh,5,brq,erq,jsdw,jssf,sm,xzbz  from RC_SHJZQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_SHJZQK set brq="+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
				" erq="+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+",jsdw='"+m.get("jsdw")+"',jssf='"+m.get("jssf")+"'" +
						",sm='"+m.get("sm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_SHJZQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除社会兼、聘职情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doShjzD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_SHJZQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_SHJZQK(rcid,xh,status,brq,erq,jsdw,jssf,sm,xzbz) " +
						" select rcid,xh,5,brq,erq,jsdw,jssf,sm,xzbz  from RC_SHJZQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_SHJZQK where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_SHJZQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}		
	
	/**
	 * 保存社会兼、聘职情况
	 * @param rcid
	 * @param rcxx1 开始日期
	 * @param rcxx2 结束日期
	 * @param rcxx3 兼职（聘用）单位
	 * @param rcxx4 兼（聘）职身份
	 * @param rcxx5 备注
	 * @return
	 */
	public int doSaveShjz(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4,String[] rcxx5){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_SHJZQK where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_SHJZQK where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_SHJZQK where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx3.length;i++){
			if(!rcxx3[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_SHJZQK(rcid,xh,status,brq,erq,jsdw,jssf,sm) " +
						" values('"+rcid+"','"+xh+"',"+status+","+(rcxx1[i].equals("")?"null":("'"+rcxx1[i]+"'"))+","+(rcxx2[i].equals("")?"null":("'"+rcxx2[i]+"'"))+"," +
						"'"+rcxx3[i]+"','"+rcxx4[i]+"','"+rcxx5[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_SHJZQK'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取被聘专家情况数据
	 */
	public List<Map<String, String>> getBpzjList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.rxnf,a.zjlb,a.sm,a.xh " +
					" from RC_BPZJQK a " +
					" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 被聘专家情况数据新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doBpzjI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_BPZJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_BPZJQK(rcid,xh,status,rxnf,zjlb,sm,xzbz) " +
						" select rcid,xh,5,rxnf,zjlb,sm  from,xzbz RC_BPZJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_BPZJQK where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_BPZJQK where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_BPZJQK(rcid,xh,status,rxnf,zjlb,sm,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("rxnf")+"','"+m.get("zjlb")+"'," +
					"'"+m.get("sm")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_BPZJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
			
		}

		
		return 1;
	}
	/**
	 *被聘专家情况数据取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getBpzjU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select a.rxnf,a.zjlb,a.sm " +
			" from RC_BPZJQK a " +
			" where a.rcid="+rcid+" and a.status="+status+" and a.xh ="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 被聘专家情况数据修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doBpzjU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_BPZJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_BPZJQK(rcid,xh,status,rxnf,zjlb,sm,xzbz) " +
						" select rcid,xh,5,rxnf,zjlb,sm,xzbz  from RC_BPZJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_BPZJQK set rxnf='"+m.get("rxnf")+"',zjlb='"+m.get("zjlb")+"'" +
						",sm='"+m.get("sm")+"' " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_BPZJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除被聘专家情况数据
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doBpzjD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_BPZJQK where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_BPZJQK(rcid,xh,status,rxnf,zjlb,sm,xzbz) " +
						" select rcid,xh,5,rxnf,zjlb,sm,xzbz  from RC_BPZJQK where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_BPZJQK where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_BPZJQK'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}	
	
	/**
	 * 保存被聘专家情况
	 * @param rcid
	 * @param rcxx1 入选年份
	 * @param rcxx2 专家类别
	 * @param rcxx3 备注
	 * @return
	 */
	public int doSaveBpzj(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_BPZJQK where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_BPZJQK where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_BPZJQK where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("") && !rcxx2[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_BPZJQK(rcid,xh,status,rxnf,zjlb,sm) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						"'"+rcxx3[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_BPZJQK'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取主要论著论文数据
	 */
	public List<Map<String, String>> getZylzList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
					" from RC_ZYLZLW  " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}


	/**
	 * 主要论著论文新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doZylzI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZYLZLW where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZYLZLW(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
						" select rcid,xh,5,zzmc,smno,cbmc,cbrq,xzbz  from RC_ZYLZLW where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_ZYLZLW where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_ZYLZLW where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_ZYLZLW(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("zzmc")+"','"+m.get("smno")+"'," +
					"'"+m.get("cbmc")+"',"+(m.get("cbrq").equals("")?"null":"'"+m.get("cbrq")+"'")+","+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZYLZLW'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 主要论著论文取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getZylzU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select zzmc,smno,cbmc,CONVERT(varchar(100), cbrq, 23) cbrq " +
			" from RC_ZYLZLW  " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 主要论著论文修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doZylzU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZYLZLW where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZYLZLW(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
						" select rcid,xh,5,zzmc,smno,cbmc,cbrq,xzbz  from RC_ZYLZLW where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_ZYLZLW set zzmc='"+m.get("zzmc")+"',smno='"+m.get("smno")+"'" +
						",cbmc='"+m.get("cbmc")+"',cbrq="+(m.get("cbrq").equals("")?"null":"'"+m.get("cbrq")+"'")+" " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZYLZLW'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除主要论著论文
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doZylzD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZYLZLW where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZYLZLW(rcid,xh,status,zzmc,smno,cbmc,cbrq,xzbz) " +
						" select rcid,xh,5,zzmc,smno,cbmc,cbrq,xzbz  from RC_ZYLZLW where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_ZYLZLW where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZYLZLW'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
			
		}

		
		return 1;
	}	
	
	/**
	 * 保存主要论著论文
	 * @param rcid
	 * @param rcxx1 论著（论文）名称
	 * @param rcxx2 出版社或刊物名称
	 * @param rcxx3 署名顺序
	 * @param rcxx4 出版（发表）时间
	 * @return 
	 */
	public int doSaveZylz(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_ZYLZLW where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_ZYLZLW where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_ZYLZLW where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_ZYLZLW(rcid,xh,status,zzmc,smno,cbmc,cbrq) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						"'"+rcxx3[i]+"',"+(rcxx4[i].equals("")?"null":("'"+rcxx4[i]+"'"))+")";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZYLZLW'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取知识产权情况数据
	 */
	public List<Map<String, String>> getZscqList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select a.xh,a.zsmc,a.zsno,a.sm ,CONVERT(varchar(100), a.hdrq, 23) hdrq," +
					" (SELECT  DICTNAME  FROM  XT_DICT AS b  WHERE   (LBID = 17) AND (a.zsno = DICTBH)) AS zsno_mc " +
					" from RC_ZSCQ  a " +
					" where a.rcid="+rcid+" and a.status=1 order by a.xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 存知识产权情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doZscqI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZSCQ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZSCQ(rcid,xh,status,zsmc,zsno,hdrq,sm,xzbz) " +
						" select rcid,xh,5,zsmc,zsno,hdrq,sm,xzbz from RC_ZSCQ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_ZSCQ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_ZSCQ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_ZSCQ(rcid,xh,status,zsmc,zsno,hdrq,sm,xzbz) " +
			" values('"+rcid+"','"+maxxh+"',1,'"+m.get("zsmc")+"','"+m.get("zsno")+"'," +
					" "+(m.get("hdrq").equals("")?"null":"'"+m.get("hdrq")+"'")+",'"+m.get("sm")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZSCQ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 *存知识产权情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getZscqU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select xh,zsmc,zsno,sm ,CONVERT(varchar(100), hdrq, 23) hdrq " +
			" from RC_ZSCQ  " +
			" where rcid="+rcid+" and status="+status+" and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 存知识产权情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doZscqU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZSCQ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZSCQ(rcid,xh,status,zsmc,zsno,hdrq,sm,xzbz) " +
						" select rcid,xh,5,zsmc,zsno,hdrq,sm,xzbz from RC_ZSCQ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_ZSCQ set zsmc='"+m.get("zsmc")+"',zsno='"+m.get("zsno")+"'" +
						",sm='"+m.get("sm")+"',hdrq="+(m.get("hdrq").equals("")?"null":"'"+m.get("hdrq")+"'")+" " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZSCQ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除存知识产权情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doZscqD(String rcid,String[] xh,Integer loginbz ,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_ZSCQ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_ZSCQ(rcid,xh,status,zsmc,zsno,hdrq,sm,xzbz) " +
						" select rcid,xh,5,zsmc,zsno,hdrq,sm,xzbz from RC_ZSCQ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_ZSCQ where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZSCQ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}	
	
	/**
	 * 保存知识产权情况
	 * @param rcid
	 * @param rcxx1 知识产权名称
	 * @param rcxx2 知识产权类型
	 * @param rcxx3 获得时间
	 * @param rcxx4 备注
	 * @return 
	 */
	public int doSaveZscq(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_ZSCQ where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_ZSCQ where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_ZSCQ where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx1[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_ZSCQ(rcid,xh,status,zsmc,zsno,hdrq,sm) " +
						" values('"+rcid+"','"+xh+"',"+status+",'"+rcxx1[i]+"','"+rcxx2[i]+"'," +
						""+(rcxx3[i].equals("")?"null":("'"+rcxx3[i]+"'"))+",'"+rcxx4[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_ZSCQ'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	
	/**
	 * 获取参加学术团及任职情况数据
	 */
	public List<Map<String, String>> getXsrzList(String rcid,String status){
		String sql_q = " ";
		List<Map<String, String>> list = new ArrayList();
		if(rcid!= null && !rcid.equals("")){
			sql_q = " select xh,dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
					" from RC_XSRZ  " +
					" where rcid="+rcid+" and status=1 order by xh ";
			list = this.getJdbcTemplate().queryForList(sql_q, new Object[]{});
		}
		return list;
	}
	

	/**
	 * 参加学术团及任职情况新增操作
	 * @param rcid
	 * @param m
	 * @param loginbz
	 * @return
	 */
	public int doXsrzI(String rcid,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1 and isnull(xzbz,1)=1 ";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		Integer maxxh = 1;
		sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=1 ";
		if( this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = " select max(xh) from RC_XSRZ where rcid="+rcid +" and status=1 ";
			maxxh = this.getSimpleJdbcTemplate().queryForInt(sql)+1;
		}
		sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
			" values('"+rcid+"','"+maxxh+"',1,"+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
					""+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+",'"+m.get("dwbm")+"'," +
					" '"+m.get("zw")+"',"+loginbz+")";
		this.getSimpleJdbcTemplate().update(sql);
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 *参加学术团及任职情况取数
	 * @param rcid
	 * @param status
	 * @param xh
	 * @return
	 */
	public Map getXsrzU(String rcid,Integer status,String xh){
		String sql_q = " ";
		Map map = new HashMap();
		sql_q = " select dwbm,zw,CONVERT(varchar(100), brq, 23) brq,CONVERT(varchar(100), erq, 23) erq " +
			" from RC_XSRZ  " +
			" where rcid="+rcid+" and status=1 and xh="+xh;
		map = this.getSimpleJdbcTemplate().queryForMap(sql_q);
		return map;
	}
	
	/**
	 * 参加学术团及任职情况修改操作
	 * @param rcid
	 * @param xh
	 * @param loginbz
	 * @return
	 */
	public int doXsrzU(String rcid,String xh,Map<String, String > m , Integer loginbz,Integer zcbz){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		sql = " update RC_XSRZ set dwbm='"+m.get("dwbm")+"',zw='"+m.get("zw")+"'," +
						" brq="+(m.get("brq").equals("")?"null":"'"+m.get("brq")+"'")+"," +
						" erq="+(m.get("erq").equals("")?"null":"'"+m.get("erq")+"'")+" " +
				"  where rcid="+rcid+" and xh="+xh+" and status=1";
		this.getSimpleJdbcTemplate().update(sql);
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	/**
	 * 删除参加学术团及任职情况
	 * @param rcid
	 * @param xh
	 * @param status
	 * @param loginbz
	 * @return
	 */
	public int doXsrzD(String rcid,String[] xh,Integer loginbz,Integer zcbz ){
		String sql = "";
		if(loginbz == 2 && zcbz !=2){//人才登入，将备份
			sql = "select count(*) from RC_XSRZ where rcid="+rcid +" and status=5 ";
			Integer c = this.getSimpleJdbcTemplate().queryForInt(sql);
			if(c == 0){
				sql = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw,xzbz ) " +
						" select rcid,xh,5,brq,erq,dwbm,zw,xzbz from RC_XSRZ where rcid="+rcid+" and status=1  and isnull(xzbz,1)=1";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		if(xh != null && xh.length > 0){
			sql = " delete from RC_XSRZ where rcid="+rcid+" and status=1 and xh in ("+ArrayToString(xh)+")";
			this.getSimpleJdbcTemplate().update(sql);
		}
		
		if(loginbz == 2 && zcbz !=2){
			//获取变更标识
			sql = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
			String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,Object.class));
			
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			sql = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			this.getSimpleJdbcTemplate().update(sql);			
		}

		
		return 1;
	}
	
	/**
	 * 保存参加学术团及任职情况
	 * @param rcid
	 * @param rcxx1 开始日期
	 * @param rcxx2 结束日期
	 * @param rcxx3 所在单位及部门
	 * @param rcxx4 职 务
	 * @return 
	 */
	public int doSaveXsrz(String rcid,Integer status,String[] rcxx1,String[] rcxx2,String[] rcxx3,String[] rcxx4){
		String sql_q = "",sql_o = "";
		int xh = 0;
		
		sql_q = " select isnull(bgbs,'000000000000') from rc_pinfo where rcid="+rcid+" and status=1";
		String bgbs = String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql_q,Object.class));
		
		sql_o = " delete from RC_XSRZ where rcid="+rcid+" and status="+status;
		this.getSimpleJdbcTemplate().update(sql_o);
		/**
		sql_q = " select count(*) from RC_XSRZ where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) >0){
			sql_q = " select max(xh) from RC_XSRZ where rcid="+rcid;
			xh = this.getSimpleJdbcTemplate().queryForInt(sql_q);
		}
		*/
		for(int i=0;i<rcxx1.length;i++){
			if(!rcxx3[i].trim().equals("")){//所在单位及部门非空才保存
				++xh;
				sql_o = " insert into RC_XSRZ(rcid,xh,status,brq,erq,dwbm,zw) " +
						" values('"+rcid+"','"+xh+"',"+status+","+(rcxx1[i].equals("")?"null":("'"+rcxx1[i]+"'"))+"," +
								""+(rcxx2[i].equals("")?"null":("'"+rcxx2[i]+"'"))+"," +
						"'"+rcxx3[i]+"','"+rcxx4[i]+"')";
				this.getSimpleJdbcTemplate().update(sql_o);
			}
		}
		if(status == 2){
			Map<String, Object> m = new HashMap();
			Integer cxh = this.getSimpleJdbcTemplate().queryForInt("select cxh from xt_const where value='RC_XSRZ'");
			m.put("RCID", rcid);
			m.put("TABLEXH", cxh);
			Object cc = this.callProc("{call SET_TABLEBYRC(?,?,?)}", m);
			if( Integer.parseInt(cc.toString())==0){
				//没有变动
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"0"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}else{
				sql_o = " update rc_pinfo set bgbs='"+(bgbs.substring(0,cxh-1)+"1"+bgbs.substring(cxh,bgbs.length()))+"' where rcid='"+rcid+"' and status=1";
			}
			this.getSimpleJdbcTemplate().update(sql_o);
			//this.callProcedure("{call SET_TABLEBYRC(?,?)}", m);
		}
		return 1;
	}
	

	/**
	 * 获取人才信息简介、备注
	 * @param rcid
	 * @return
	 */
	public Map getJjbzInfo(String rcid,String status){
		Map m = new HashMap();
		if(rcid != null && !rcid.equals("")){
			String sql_q = "select a.info,a.bz from rc_pinfo a where a.rcid="+rcid+" and status=1";
			m = this.getSimpleJdbcTemplate().queryForMap(sql_q);			
		}
		return m;
	}
	
	/**
	 * 保存简介、备注
	 * @param rcid
	 * @param rcdarcxx
	 * @return
	 */
	public int doSaveJjbz(String rcid,Integer status,Map<String, String> rcdarcxx){
		String sql_o  = "";
		sql_o = "update RC_PINFO set info='"+rcdarcxx.get("info")+"',bz='"+rcdarcxx.get("bz")+"' where rcid="+rcid+" and status=1";
		this.getSimpleJdbcTemplate().update(sql_o);
		return 1;
	}
	/**
	 * 获取所有的基础数据
	 * @return
	 */
	public Map<Integer, List<XtDict>> getDictListWithSelectAll(){
		Map<Integer, List<XtDict>> ms = new HashMap();
		List<XtDict> xtdicts = new ArrayList();
		int lbid = 0;
		String sql_q = " select * from xt_dlb where lbframe!=1 ";
		List<XtDlb> xtdlbs = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtDlb.class));
		for(int i=0;i<xtdlbs.size();i++){
			lbid = xtdlbs.get(i).getLbid();
			ms.put(lbid, this.getSimpleJdbcTemplate().query(" select dictbh,dictname from xt_dict where lbid="+lbid+" and enable=1 order by dictbh", resultBeanMapper(XtDict.class)));
			
		}
		return ms;
	}

	/**
	 * 获取人才的基础数据
	 * 主要用下拉框
	 * @param lbid
	 */
	public List<XtDict> getDictListWithSelect(Integer lbid){
		List<XtDict> ls = new ArrayList();
		String sql_q = "select dictbh,dictname from xt_dict where lbid="+lbid+"  order by dictbh ";
		ls = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return ls;
	}
	
	/**
	 * 获取人才的基础数据
	 * 主要用于树形
	 * @param lbid
	 * @return
	 */
	public List<TreeBean> getDictListWithTree(Integer lbid){
		List<TreeBean> ls = new ArrayList();
		ls = this.getSimpleJdbcTemplate().query(" select a.dictbh as dm ,a.dictname as mc ," +
				" (select count(*) from xt_dict b where b.dictbh like a.dictbh+'%' and a.dictbh!=b.dictbh) as isc," +
				" case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm " +
				" from xt_dict a where a.lbid="+lbid+" and a.enable=1 order by a.dictbh", resultBeanMapper(TreeBean.class));
		return ls;
	}
	
	/**
	 * 逐级获取树形
	 */
	public List<XtDict> getDictTreeWithStep(Integer i,String dm){
		List<XtDict> maps = new ArrayList();
		String sql_q = "";
		if(dm != null && !dm.equals("")){
			sql_q = " select * from xt_dict where lbid="+i+" " +
					" and len(dictbh)="+(dm.length()+3)+" and dictbh like '"+dm+"%'";
		}else{
			sql_q = " select * from xt_dict where lbid="+i+" and len(dictbh)=3 ";
		}
		maps = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(XtDict.class));
		return maps;
	}
	
	/**
	 * 用于分步获取人才信息的树形
	 * @param i
	 * @param dm
	 * @return
	 */
	public List<TreeBean> getRcxxTreeWithStep(Integer i,String dm){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		if(dm != null && !dm.equals("")){
			sql_q = " select a.dictbh as dm ,a.dictname as mc ," +
				" '0' as isc," +
				" '0' as pdm " +
				" from xt_dict a where lbid="+i+" and len(a.dictbh)='"+(dm.length()+3)+"' and a.dictbh like '"+dm+"%' order by a.dictbh";
		}else{
			sql_q = " select a.dictbh as dm ,a.dictname as mc ," +
				" '0' as isc," +
				" '0' as pdm " +
				" from xt_dict a where a.lbid="+i+" and len(a.dictbh)=3 order by a.dictbh";
		}
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/**
	 * 获取人才类别 树形
	 * @return
	 */
	public List<TreeBean> getRclbTree(){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.lbdm as dm ,a.lbmc as mc ," +
			" (select count(*) from rc_rclb b where b.lbdm like a.lbdm+'%' and a.lbdm!=b.lbdm) as isc," +
			" case when len(lbdm)=3 then '0' else substring(lbdm,1,len(lbdm)-3) end as pdm " +
			" from rc_rclb a  order by a.lbdm";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	}
	
	/**
	 * 对存储过程调用进行了简单封装  带返回值
	 */
	public Object callProc(String procn,final Map<String, Object> par){
		return getJdbcTemplate().execute("{call SET_TABLEBYRC(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setInt(1, Integer.parseInt(par.get("RCID").toString()));
				cs.setInt(2, Integer.parseInt(par.get("TABLEXH").toString()));
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				return cs.getInt(3);
			}
		});
	}
}

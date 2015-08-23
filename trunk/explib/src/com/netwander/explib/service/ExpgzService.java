package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.netwander.core.common.TreeBean;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;

@Service
public class ExpgzService extends BaseService{

	@SuppressWarnings("unchecked")
	public String saveKc(Map parMap) {
		String sql = "";
		String maxid = "";
		if(parMap.get("kcid")==null || parMap.get("kcid").toString().equals("")){
			maxid = getMaxKey("GZ_KC").toString();
			sql = "insert into GZ_KC(rcid,kcid,kcmc,kcsj,kcdd) values("+parMap.get("rcid").toString()+","+maxid+"," +
					"	'"+parMap.get("kcmc").toString()+"','"+parMap.get("kcsj").toString()+"','"+parMap.get("kcdd").toString()+"')";
		}else{
			maxid = parMap.get("kcid").toString();
			sql = "update GZ_KC set kcmc='"+parMap.get("kcmc").toString()+"',kcsj='"+parMap.get("kcsj").toString()+"',kcdd='"+parMap.get("kcdd").toString()+"' where" +
					" rcid="+parMap.get("rcid").toString()+" and kcid="+maxid+"";
		}
		this.getJdbcTemplate().update(sql);
		return maxid;
	}
	
	/**
	 * 批量保存
	 * @param rcid
	 * @param parMap
	 * @return
	 */
	public Integer doKcI(String rcid,Map parMap){
		String sql = "";
		if(rcid != null && !rcid.equals("")){
			String[] r = rcid.split(",");
			Integer maxid = Integer.parseInt(getMaxKey("GZ_KC", r.length).toString());
			for(int i=0;i<r.length;i++){
				sql = "insert into GZ_KC(rcid,kcid,kcmc,kcsj,kcdd) values("+r[i]+","+maxid+"," +
					"'"+parMap.get("kcmc").toString()+"','"+parMap.get("kcsj").toString()+"','"+parMap.get("kcdd").toString()+"')";
				this.getJdbcTemplate().update(sql);
				++maxid;
			}
		}
		return 1;
	}
	
	public Integer doGzI(String rcid,Map parMap,Map wtMap){
		String sql = "";
		if(rcid != null && !rcid.equals("")){
			String[] r = rcid.split(",");
			Integer maxid = Integer.parseInt(getMaxKey("exp_gz", r.length).toString());
			for(int i=0;i<r.length;i++){
				if(parMap!= null && parMap.get("gzmc") != null  && !String.valueOf(parMap.get("gzmc")).equals("") ){
					
					sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+parMap.get("gzmc")+"'";
					if(this.getSimpleJdbcTemplate().queryForInt(sql) != 1){
						throw new BusException("当前选择跟踪来自外部，不能新建跟踪！");
					}
					sql = "insert into exp_gz(rcid,gzid,gzmc,gzstrdate,gzenddate,hddd,hdzbdw,sm,jspj,hbpj,psdf," +
							"wtpjdj,wtpjyj,wtsm,GZTYPE) values("+r[i]+","+maxid+"," +
						"'"+parMap.get("gzmc").toString()+"',"+transToD(parMap.get("gzstrdate").toString())+","+transToD(parMap.get("gzenddate").toString())+"," +
						"'"+parMap.get("hddd").toString()+"','"+parMap.get("hdzbdw").toString()+"','"+parMap.get("sm").toString()+"'" +
						",'"+transToN(parMap.get("jspj").toString())+"','"+transToN(parMap.get("hbpj").toString())+"'," +
								"'"+transToN(parMap.get("psdf").toString())+"'," +
								"'"+wtMap.get("wtpjdj").toString()+"','"+wtMap.get("wtpjyj").toString()+"'," +
								"'"+wtMap.get("wtsm").toString()+"',1)";
					this.getJdbcTemplate().update(sql);
					++maxid;
				}
				/**
				++maxid;
				if(wtMap!= null && wtMap.get("gzmc") != null && !String.valueOf(wtMap.get("gzmc")).equals("") ){
					sql = "insert into exp_gz(rcid,gzid,gzmc,wtpjdj,wtpjyj,sm,GZTYPE) values("+r[i]+","+maxid+"," +
						"'"+wtMap.get("gzmc").toString()+"','"+wtMap.get("wtpjdj").toString()+"','"+wtMap.get("wtpjyj").toString()+"'" +
								",'"+wtMap.get("sm").toString()+"',2)";
					this.getJdbcTemplate().update(sql); 
				}
				++maxid;**/
			}
		}
		return 1;
	}
	
	
	/**
	 * 获取人才考察树
	 * @return
	 */
	public List<TreeBean> getGzTree(String rcid,String bmdm,Integer gztype){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.gzid as dm,( " +
				" select case when isnull(bz,1) =1 then dictname else dictname+'(外部)' end from exp_gzgl_bm  where dictbh=a.gzmc) as mc , " +
				"'0' as isc,'0' as pdm from exp_gz a where isnull(gztype,1)="+gztype+" and a.rcid="+rcid+" order by a.gzid";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	} 
	
	public List<TreeBean> getGzTreeZj(String rcid,String bmdm){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.gzid as dm,(" +
				"select case when isnull(bz,1) =1 then dictname else dictname+'(外部)' end" +
				" from exp_gzgl_bm  where dictbh=a.gzmc) as mc , " +
				"'0' as isc,'0' as pdm from exp_gz a where isnull(gztype,1)=2 and a.rcid="+rcid+" order by a.gzid";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	} 
	
	public Map getGzkcByKcid(String kcid){
		Map m = new HashMap();
		String sql = " select KCMC,CONVERT(varchar(100), KCSJ, 23) KCSJ,KCDD from gz_kc where kcid="+kcid;
		m = this.getSimpleJdbcTemplate().queryForMap(sql);
		return m;
	}
	
	public Map getGzByGzid(String gzid){
		Map m = new HashMap();
		String sql = " select GZMC,isnull(CONVERT(varchar(100), GZSTRDATE, 23),'') GZSTRDATE," +
				"isnull(CONVERT(varchar(100), GZENDDATE, 23),'') GZENDDATE,HDDD,HDZBDW," +
				" isnull(SM,'') SM,isnull(JSPJ,'') JSPJ, isnull(HBPJ,'') HBPJ,isnull(PSDF,'') PSDF," +
				"isnull(WTPJDJ,'') WTPJDJ,isnull(WTPJYJ,'') WTPJYJ ,isnull(GZQK,'') GZQK,isnull(WTSM,'') WTSM" +
				"  from exp_gz where gzid="+gzid;
		m = this.getSimpleJdbcTemplate().queryForMap(sql);
		return m;
	}
	
	
	public Integer doKcU(String rcid,String kcid,Map parMap){
		String sql = "";
		if(kcid != null && !kcid.equals("")){
			sql = " update gz_kc set kcmc='"+parMap.get("kcmc").toString()+"',kcsj='"+parMap.get("kcsj").toString()+"'," +
					"kcdd='"+parMap.get("kcdd").toString()+"',JSPJ="+transToN(parMap.get("jspj").toString())+"" +
							",HBPJ="+transToN(parMap.get("hbpj").toString())+",PSDF="+transToN(parMap.get("psdf").toString())+" where kcid="+kcid+" and rcid="+rcid;
			this.getJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	public Integer doGzU(String rcid,String gzid,Map parMap,String wtkcid,Map wtMap){
		String sql = "";
		if(gzid != null && !gzid.equals("")){
			sql = "select gzmc from exp_gz where gzid="+gzid+" and rcid="+rcid;
			String gzmc = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
			sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+gzmc+"'";
			
			if(this.getSimpleJdbcTemplate().queryForInt(sql) == 1){
				if(parMap.get("gzstrdate") == null || String.valueOf(parMap.get("gzstrdate")).equals("")){
					throw new BusException("请输入跟踪起始时间");
				}
				if(parMap.get("gzenddate") == null || String.valueOf(parMap.get("gzenddate")).equals("")){
					throw new BusException("请输入跟踪结束时间");
				}
				if(parMap.get("hddd") == null || String.valueOf(parMap.get("hddd")).equals("")){
					throw new BusException("请输入评审地点");
				}
				
				sql = " update exp_gz set gzstrdate='"+parMap.get("gzstrdate").toString()+"'," +
					"gzenddate='"+parMap.get("gzenddate").toString()+"',hddd='"+parMap.get("hddd").toString()+"'," +
					"hdzbdw='"+parMap.get("hdzbdw").toString()+"',gzqk='"+parMap.get("gzqk").toString()+"',sm='"+parMap.get("sm").toString()+"'," +
					"JSPJ="+transToN(parMap.get("jspj").toString())+"" +
					",HBPJ="+transToN(parMap.get("hbpj").toString())+",PSDF="+transToN(parMap.get("psdf").toString())+" where gzid="+gzid+" and rcid="+rcid;
				this.getJdbcTemplate().update(sql);
				
				sql = " update exp_gz set " +
						"wtsm='"+wtMap.get("wtsm").toString()+"',wtpjdj='"+wtMap.get("wtpjdj").toString()+"'" +
					",wtpjyj='"+wtMap.get("wtpjyj").toString()+"' " +
					" where gzid="+gzid+" and rcid="+rcid;
				this.getJdbcTemplate().update(sql);
			}else{
				sql = " update exp_gz set " +
						"wtsm='"+wtMap.get("wtsm").toString()+"',wtpjdj='"+wtMap.get("wtpjdj").toString()+"'" +
					",wtpjyj='"+wtMap.get("wtpjyj").toString()+"'" +
					",HBPJ="+transToN(parMap.get("hbpj").toString())+",PSDF="+transToN(parMap.get("psdf").toString())+"" +
					" " +
					" where gzid="+gzid+" and rcid="+rcid;
				this.getJdbcTemplate().update(sql);
				//throw new BusException("当前跟踪来自外部！不能修改");
			}
		}
		
		/**
		if(wtkcid!= null && !wtkcid.equals("")){
			sql = "select gzmc from exp_gz where gzid="+wtkcid+" and rcid="+rcid;
			String gzmc = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
			sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+gzmc+"'";
			
			if(this.getSimpleJdbcTemplate().queryForInt(sql) == 1){
				sql = " update exp_gz set gzmc='"+wtMap.get("gzmc").toString()+"'," +
						"sm='"+wtMap.get("sm").toString()+"',wtpjdj='"+wtMap.get("wtpjdj").toString()+"'" +
					",wtpjyj='"+wtMap.get("wtpjyj").toString()+"' " +
					" where gzid="+wtkcid+" and rcid="+rcid;
				this.getJdbcTemplate().update(sql);
			}else{
				throw new BusException("当前跟踪来自外部！不能修改");
			}
		}
		**/
		return 1;
	}
	
	
	
	public Integer doKcD(String kcid){
		String sql = "";
		if(kcid != null && !kcid.equals("")){
			sql = " delete from gz_kc where kcid="+kcid;
			this.getJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	public Integer doGzD(String gzid){
		String sql = "";
		if(gzid != null && !gzid.equals("")){
			sql = "select gzmc from exp_gz where gzid="+gzid;
			String gzmc = this.getSimpleJdbcTemplate().queryForObject(sql, String.class);
			sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+gzmc+"'";
			
			if(this.getSimpleJdbcTemplate().queryForInt(sql) == 1){
				sql = " delete from exp_gz where gzid="+gzid;
				this.getJdbcTemplate().update(sql);
			}else{
				throw new BusException("当前跟踪来自外部！不能删除");
			}
			
		}
		return 1;
	}
	
	/**
	 * 人才培训批量
	 * @param rcid
	 * @param parMap
	 * @return
	 */
	public Integer doPxI(String rcid,Map parMap){
		String sql = "";
		if(rcid != null && !rcid.equals("")){
			String[] r = rcid.split(",");
			Integer maxid = Integer.parseInt(getMaxKey("GZ_PX", r.length).toString());
			for(int i=0;i<r.length;i++){
				sql = "insert into GZ_PX(rcid,pxid,pxmc,pxsj,pxdd,pxdw,pxendsj) values("+r[i]+","+maxid+"," +
					"'"+parMap.get("pxmc").toString()+"','"+parMap.get("pxsj").toString()+"','"+parMap.get("pxdd").toString()+"','"+parMap.get("pxdw").toString()+"'," +
							""+(parMap.get("pxendsj").equals("")?"null":"'"+parMap.get("pxendsj")+"'")+")";
				this.getJdbcTemplate().update(sql);
				++maxid;
			}
		}
		return 1;
	}
	
	/**
	 * 获取人才考察树
	 * @return
	 */
	public List<TreeBean> getGzpxTree(String rcid){
		List<TreeBean> l = new ArrayList();
		String sql_q = "";
		sql_q = " select a.pxid as dm,a.pxmc as mc ,'0' as isc,'0' as pdm from gz_px a where a.rcid="+rcid+" order by a.pxid";
		l = this.getSimpleJdbcTemplate().query(sql_q,resultBeanMapper(TreeBean.class));
		return l;
	} 
	
	public Map getGzpxByPxid(String pxid){
		String sql = " select PXMC,PXDW,PXDD,CONVERT(varchar(100), PXSJ, 23) PXSJ,CONVERT(varchar(100), PXENDSJ, 23) PXENDSJ from gz_px where pxid="+pxid;
		return this.getSimpleJdbcTemplate().queryForMap(sql);
	}
	
	public Integer doPxU(String rcid,String pxid,Map parMap){
		String sql = "";
		if(pxid != null && !pxid.equals("")){
			sql = " update gz_px set pxmc='"+parMap.get("pxmc").toString()+"',pxsj='"+parMap.get("pxsj").toString()+"'," +
					"pxdd='"+parMap.get("pxdd").toString()+"',pxdw='"+parMap.get("pxdw").toString()+"'," +
					"pxendsj="+(parMap.get("pxendsj").equals("")?"null":"'"+parMap.get("pxendsj")+"'")+" where pxid="+pxid+" and rcid="+rcid;
			this.getJdbcTemplate().update(sql);
		}
		return 1;
	}
	
	
	public Integer doPxD(String pxid){
		String sql = "";
		if(pxid != null && !pxid.equals("")){
			sql = " delete from gz_px where pxid="+pxid;
			this.getJdbcTemplate().update(sql);
		}
		return 1;
	}
	@SuppressWarnings("unchecked")
	public String savePx(Map parMap) {
		String sql = "";
		String maxid = "";
		if(parMap.get("pxid")==null || parMap.get("pxid").toString().equals("")){
			maxid = getMaxKey("GZ_PX").toString();
			sql = "insert into GZ_PX(rcid,pxid,pxmc,pxsj,pxdd,pxdw,pxendsj) values("+parMap.get("rcid").toString()+","+maxid+"," +
					"	'"+parMap.get("pxmc").toString()+"','"+parMap.get("pxsj").toString()+"','"+parMap.get("pxdd").toString()+"','"+parMap.get("pxdw").toString()+"'," +
							""+(parMap.get("pxendsj").equals("")?"null":"'"+parMap.get("pxendsj")+"'")+")";
		}else{
			maxid = parMap.get("pxid").toString();
			sql = "update GZ_PX set pxmc='"+parMap.get("pxmc").toString()+"',pxdw='"+parMap.get("pxdw").toString()+"',pxsj='"+parMap.get("pxsj").toString()+"',pxendsj="+(parMap.get("pxendsj").equals("")?"null":"'"+parMap.get("pxendsj")+"'")+"" +
					",pxdd='"+parMap.get("pxdd").toString()+"' where" +
					" rcid="+parMap.get("rcid").toString()+" and pxid="+maxid+"";
		}
		this.getJdbcTemplate().update(sql);
		return maxid;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> loadPx(String rcid) {
		String sql = "select pxid,pxmc,convert(char(10),pxsj,20) as pxsj,pxdd,pxdw,rcid from gz_px where rcid="+rcid+"";
		return this.getJdbcTemplate().queryForList(sql);
	}

	@SuppressWarnings("unchecked")
	public  List<Map<String, Object>> loadKc(String rcid) {
		String sql = "select kcid,kcmc,convert(char(10),kcsj,20) as kcsj,kcdd,rcid from gz_kc where rcid="+rcid+"";
		return this.getJdbcTemplate().queryForList(sql);
	}
	
    public List<TreeBean> getDicts(){
    	List<TreeBean> l = new ArrayList();
    	String sql = "select case when isnull(bz,1) =1 then dictname else dictname+'(外部)' end mc ,dictbh dm,'0' isc,case when len(dictbh)=3 " +
    			" then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm from exp_gzgl_bm  order by dictbh";
    	l =  this.getJdbcTemplate().query(sql,resultBeanMapper(TreeBean.class));
    	return l ;
    }
    
    public Map<String,String> addDict(Map<String,String> query){
    	
    	String type = query.get("type");
    	String bh   = query.get("dictbh")==null?"":query.get("dictbh");
    	String perbh = "0"; 
    	int bhlen = 3;
    	if(type.equals("1")){//新增本级
    	    if(bh.length() > 3){
	    		bhlen = bh.length();
	    		perbh = bh.substring(0,bh.length()  - 3);
    	    }
    	}else{//新增下级
    	    bhlen = bh.length() + 3;
    	    perbh = bh;
    	}
    	
    	
    	String sql ="select isnull(max(dictbh),0) + 1 from exp_gzgl_bm where len(dictbh) = ? and dictbh like ?";
    	String dictbh  = String.valueOf(this.getJdbcTemplate().queryForLong(sql, new Object[]{bhlen,perbh+"%" }));
    	String zero = "";
    	int value = dictbh.length() % 3;
    	if(value>0){
    		for(int i=0;i<3-value;i++){
    			zero += "0";
    		}
    	}
        dictbh = zero + dictbh;
        
        if(bhlen != dictbh.length())
            dictbh = bh + dictbh;
        
        if(dictbh.length() > 3){
        	sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+dictbh.substring(0,3)+"'";
        	if(this.getSimpleJdbcTemplate().queryForInt(sql)!= 1){
        		throw new BusException("当前上级来自外部系统，不能在其下新建！");
        	}
        }
       // int maxID = (Integer) getMaxKey("XT_DICT", 1);
        String isql ="insert into exp_gzgl_bm(dictbh,dictname,bmdm,bz) values('"+dictbh+"','"+query.get("dictname")+"',null,null)";
        this.jdbcTemplate.update(isql);
        query.put("dictbh", dictbh);
    	return query;
    }
    
    @SuppressWarnings("unchecked")
    public Map<String,Object> getDictMap(Map<String,String> query){

    	return this.getJdbcTemplate().queryForMap("select * from exp_gzgl_bm where dictbh=?",new Object[]{query.get("dictbh")});
    }
    
    public void updateDict(Map<String,String> map){
    	String sql = "";
    	sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+map.get("dictbh")+"'";
    	if(this.getSimpleJdbcTemplate().queryForInt(sql) != 1){
    		throw new BusException("当前跟踪来自外部系统，不能修改！");
    	}
    	sql = "update exp_gzgl_bm set dictname='"+map.get("dictname")+"' where  dictbh='"+map.get("dictbh")+"'";
  	  this.getJdbcTemplate().update(sql);
    }
    
    public void deletedict(Map<String,String> query){
    	String bhs = query.get("dictbh");
    	String sql = " select count(*) from EXP_GZ where gzmc like '"+bhs+"%'";
    	if(this.getSimpleJdbcTemplate().queryForInt(sql) == 0){
    		sql = " select isnull(bz,1) from exp_gzgl_bm where dictbh='"+bhs+"'";
    		if(this.getSimpleJdbcTemplate().queryForInt(sql) == 1){
    			this.getSimpleJdbcTemplate().update("delete exp_gzgl_bm where  dictbh like '"+bhs+"%'");
    		}else{
    			throw new BusException("当前跟踪来自外部系统，不能删除！");
    		}
    	}else{
    		throw new BusException("当前中已有跟踪维护不能删除！");
    	}
    }
}

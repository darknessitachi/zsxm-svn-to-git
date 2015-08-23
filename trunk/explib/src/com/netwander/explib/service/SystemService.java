package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.netwander.core.common.TreeBean;
import com.netwander.explib.entity.XtMenu;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.common.BaseService;
import com.sun.java_cup.internal.internal_error;

@Service
public class SystemService extends BaseService{
	
	
	public Xtuser checkLogon(String logintype,String loginname,String password){
		Xtuser xtuser = new Xtuser();
		String sql = " select count(*) from XT_USER where lower(loginname)=? and lower(password)=? ";
		if(queryForInt(sql, new Object[]{loginname.trim().toLowerCase(),password.trim().toLowerCase()}) > 0){
			sql = " select USERID, LOGINNAME, CNNAME, BMMC, ZW, DQ, ROLEDM, PASSWORD, PX, BMDM,convert(varchar(20),getdate(),23) logindate" +
				" from XT_USER where lower(loginname)=? and lower(password)=? ";
			xtuser = queryForObject(sql,  resultBeanMapper(Xtuser.class),new Object[]{loginname.trim().toLowerCase(),password.trim().toLowerCase()});
			xtuser.setLoginbz(Integer.parseInt(logintype));
		}else{
			throw new BusException("用户或密码错误!");
		}
		return xtuser;
	}
	
	public Xtuser checkLogonWithZj(String logintype,String loginname,String password){
		Xtuser xtuser = new Xtuser();
		String sql = " select count(*) from XT_USER_EXP where lower(loginname)=? and lower(password)=? ";
		if(queryForInt(sql, new Object[]{loginname.trim().toLowerCase(),password.trim().toLowerCase()}) > 0){
			sql = " select DBID USERID, LOGINNAME, RCNAME CNNAME,  PASSWORD,convert(varchar(20),getdate(),23) logindate, bz expbz,rcid exprcid " +
				" from XT_USER_EXP where lower(loginname)=? and lower(password)=? ";
			xtuser = queryForObject(sql,  resultBeanMapper(Xtuser.class),new Object[]{loginname.trim().toLowerCase(),password.trim().toLowerCase()});
			xtuser.setLoginbz(Integer.parseInt(logintype));
			
			sql = " select jhbz from XT_USER_EXP where dbid="+xtuser.getUserid();
			if(this.getSimpleJdbcTemplate().queryForInt(sql) == 1){ 
				//如果专家未激活过，且注册，或正式库已存在，则第一次登陆初始化数据,每个专家只初始化一次，自己注册的专家不初始化
				ExptbInfo(xtuser.getExprcid());
				
				sql = " update XT_USER_EXP set jhbz=2 where dbid="+xtuser.getUserid();
				update(false, sql);
			}
		}else{
			throw new BusException("用户或密码错误!");
		}
		return xtuser;
	}
	
	public Integer getExpTbbz(String rcid){
		String sql = "select count(*) from exp_main_zj where rcid="+rcid;
		if(this.getSimpleJdbcTemplate().queryForInt(sql) > 0){
			sql = "select isnull(tbbz,0) from exp_main_zj where rcid="+rcid;
			return this.getSimpleJdbcTemplate().queryForInt(sql);
		}else{
			return 0;
		}
	}
	/**
	 * 如果专家未激活过 JHBZ =1 
	 * 将备同步数据
	 * @param rcid
	 */
	public void ExptbInfo(Integer rcid){
		String sql = " select value from xt_const where cdm='001' order by cxh";
		List<Map<String, Object>> list = this.getSimpleJdbcTemplate().queryForList(sql);
		for(int i=0;i<list.size();i++){
			sql = "insert into "+String.valueOf(list.get(i).get("value"))+"_ZJ select * from "+String.valueOf(list.get(i).get("value"))+" where rcid="+rcid;
			this.getSimpleJdbcTemplate().update(sql);
		}
	}
	
	/**
	 * 获取工作员用户可操作菜单
	 * @param userid
	 * @return
	 */
	public List<XtMenu> getMenuList(String userid){
		List<XtMenu> menus = new ArrayList();
		String sql_q = "select count(*) from xt_menu a,xt_user_menu b " +
				" where a.menuid=b.menuid and b.userid='"+userid+"'";
		if( this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
			sql_q = "select a.* from xt_menu a,xt_user_menu b " +
			" where a.menuid=b.menuid and b.userid='"+userid+"' order by a.sort,a.menuid";
			menus = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtMenu.class));
		}
		return menus;
	}
	
	/**
	 * 获取栏目
	 * @return
	 */
	public List getInfoLmList(){
		List list = new ArrayList();
		list = this.getSimpleJdbcTemplate().queryForList("select top 4 lmid,lmmc from info_lm order by lmid ");
		return list;
	}
	
	
	public List getSearchFieldList(){
		List list = new ArrayList();
		String sql = " select smc,swhere from XT_SEARCH order by sid ";
		list = this.getSimpleJdbcTemplate().queryForList(sql);
		return list;
	}
	
	
	public List getChangeSel(String userid,String fldm,String lbid){
		List list = new ArrayList();
		String sql = "";
		if(lbid.equals("-99")){
			sql = "select case when isnull(bz,1)=1 then dictname else dictname+'(外部)' end mc ," +
					" dictbh dm,'0' isc,case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end" +
					" as pdm from exp_gzgl_bm  order by dictbh";
			list = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(TreeBean.class));
		}else{
			sql = " select dictbh dm,dictname mc,0 isc," +
				"case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm from xt_dict where lbid="+lbid+" order by dictbh ";
			list = this.getSimpleJdbcTemplate().queryForList(sql);
		}
		return list;
	}
	
	public List<TreeBean> getChangeTree(String userid,String fldm,String lbid){
		List<TreeBean> list = new ArrayList();
		String sql = " select dictbh dm,dictname mc,0 isc," +
				"case when len(dictbh)=3 then '0' else substring(dictbh,1,len(dictbh)-3) end as pdm from xt_dict where lbid="+lbid+" order by dictbh ";
		list = this.getSimpleJdbcTemplate().query(sql, resultBeanMapper(TreeBean.class));
		return list;
	}
}

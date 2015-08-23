package com.group.zsxm.service.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.group.zsxm.entity.XtMenu;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.exception.BusException;
import com.sun.org.apache.bcel.internal.generic.Select;

@Service
public class SystemService extends BaseService{
	
	
	/**
	 * 获取所有用户
	 */
	public List getXtuserList(){
		List l = new ArrayList();
		String sql = " select * from xt_user where loginname!='admin'";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	/**
	 * 获取登入日期
	 * @return
	 */
	public String getLogindate(){
		String sql = " select CONVERT(varchar(100), GETDATE(), 23) as logindate ";
		return String.valueOf(this.getSimpleJdbcTemplate().queryForObject(sql,String.class));
	}
	/**
	 * 工作人员用户登录检查
	 * @param u
	 * @return
	 */
	public Xtuser doCheckLogin(Xtuser u){
		String sql_q = "";
		Xtuser xtuser = new Xtuser();
		sql_q = "select count(*) from xt_user where loginname='"+u.getLoginname()+"' and password='"+u.getPassword()+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
			xtuser = this.getSimpleJdbcTemplate().queryForObject("select *,CONVERT(varchar(100), GETDATE(), 23) as logindate from xt_user where loginname='"+u.getLoginname()+"' and password='"+u.getPassword()+"' ",resultBeanMapper(Xtuser.class));
		}else{
			throw new BusException("您输入的用户名或密码错误！");
		}
		return xtuser;
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
			" where a.menuid=b.menuid and b.userid='"+userid+"' order by a.sortid,a.menuid ";
			menus = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtMenu.class));
		}
		return menus;
	}
	
	
	/**
	 * 人才用户登入
	 * @param u
	 * @return
	 */
	public Xtuser doCheckLoginWithRc(Xtuser u){
		String sql_q = "";
		Xtuser xtuser = new Xtuser();
		sql_q = "select count(*) from rc_user where loginname='"+u.getLoginname()+"' and password='"+u.getPassword()+"'";
		if(this.getSimpleJdbcTemplate().queryForInt(sql_q) > 0){
			xtuser = this.getSimpleJdbcTemplate().queryForObject("select rcid as userid,rcname as cnname,loginname,password,xtrcid,isnull(bz,'2') bz,CONVERT(varchar(100), GETDATE(), 23) as logindate from rc_user where loginname='"+u.getLoginname()+"' and password='"+u.getPassword()+"' ",resultBeanMapper(Xtuser.class));
			if(xtuser.getBz() !=1 ){
				throw new BusException("您的信息还在审批中! 不能登入系统...！");
			}
		}else{
			throw new BusException("您输入的用户名或密码错误！");
		}
		return xtuser;
	}
	
	/**
	 * 获取人才用户可操作菜单
	 * @param userid
	 * @return
	 */
	public List<XtMenu> getMenuListWithRc(String userid){
		List<XtMenu> menus = new ArrayList();
		String sql_q = " select a.* from rc_menu a  order by a.menuid ";
		menus = this.getSimpleJdbcTemplate().query(sql_q, resultBeanMapper(XtMenu.class));
		return menus;
	}

}

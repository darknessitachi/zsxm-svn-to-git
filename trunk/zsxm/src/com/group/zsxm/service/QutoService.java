package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.group.core.common.TreeBean;
import com.group.zsxm.service.common.BaseService;

@Service
public class QutoService extends BaseService {

	@SuppressWarnings("unchecked")
	public List getMenuByUserId(String userid) {
		return getSimpleJdbcTemplate()
				.query(
						"select m.menuid as dm,m.pmenuid as pdm,m.title as mc,(select count(u.userid) from xt_user u,xt_user_menu um where u.userid = um.userid and um.menuid = m.menuid and u.userid =?) as isc from xt_menu  m order by m.menuid ",
						resultBeanMapper(TreeBean.class), userid);
	}
	
	public String getUserNameById(String userid){
		return getSimpleJdbcTemplate().queryForObject("select cnname from xt_user where userid=?",String.class, userid);
	}

	public void saveMenuByUserId(String userId, String menuIds) {
		getSimpleJdbcTemplate().update("delete from xt_user_menu where userid=?", userId);
		if (StringUtils.isNotBlank(menuIds)) {
			for (int i = 0; i < menuIds.split(",").length; i++) {
                String menuid =  menuIds.split(",")[i];
                getSimpleJdbcTemplate().update("insert into xt_user_menu (userid,menuid) values (?,?)",userId,menuid);
			}
		}
	}
	
	/**
	 * 获取所有用户
	 * @return
	 */
	public List<Map<String,String>> getAllUser(){
		List<Map<String,String>> l = new ArrayList();
		String sql = " select * from xt_user ";
		l = this.getJdbcTemplate().queryForList(sql);
		return l;
	}
}

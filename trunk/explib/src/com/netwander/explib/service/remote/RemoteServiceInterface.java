package com.netwander.explib.service.remote;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;


public interface RemoteServiceInterface  extends Serializable{
	
	/***
	 * 用于判断是否连通
	 * @return
	 */
	public int isLink();
	
	/**
	 * 同步专家信息
	 * @param user 用户
	 * @param pass 密码
	 * @param type 系统类型
	 * @param einfo 专家信息
	 * @return 
	 */
	public Map doSEI(String user,String pass,String type,String wbfl,Map einfo);
	
	/**
	 * 批量
	 * @param user
	 * @param pass
	 * @param type
	 * @param einfo
	 * @return
	 */
	public Map doSEIList(String user,String pass,String type,List<Map> les);
	
	/**
	 * 获取专家信息
	 * @param user
	 * @param pass
	 * @param type
	 * @param id
	 * @return
	 */
	public Map getEI(String user,String pass,String type,String id);
	
	
	/**
	 * 判别基础信息是否有变动
	 * @param user
	 * @param pass
	 * @param type
	 * @return
	 */
	public int isBaseChange(String user,String pass,String type);
	
	
	/**
	 * 获取基础信息
	 * @param user
	 * @param pass
	 * @param type
	 * @return
	 */
	public Map getBaseInfo(String user,String pass,String type,String lbid);
	
	
	/***
	 * 获取分页专家信息
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @param xtuser
	 * @return
	 */
	public Object getListForRcdaByName(Map  limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,List list) ;
	
	
	/***
	 * 如果 sbz=111 ，则直接传 RCID，批量获取专家
	 */
	public Map getEIList(String user, String pass, String type, String fldm,Integer sbz, List list);
	
	
	/***
	 * 用于维护更踪
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @param list
	 * @return
	 */
	public Object getGzList(String user, String pass, String type,Map  limit,
			Map<String, String> parMap, SortInfo sortInfo,
			List<FilterInfo> filterInfos,List list);
	
	
	/***
	 * 保存跟踪
	 */
	public Map doSaveGz(String user, String pass, String type,Map parMap);
	
	
	
	/***
	 * 保存分类
	 */
	public Map doSaveGzFl(String user, String pass, String type,Map parMap);
	
	
	/***
	 * 校验用户是否正确
	 */
	public boolean checkLogin(String user,String password);
	
	/***
	 * 同步评价信息
	 * wbfl : 外部定义的 评审分类
	 * wbflmc: 名称
	 * gzlx:  指定在本系统中的 属于某类下的跟踪类型
	 * 
	 */
	public boolean doTbGz(String wbfl,String wbflmc,String gzlx,String type,List<Map<String, Object>> list );
}

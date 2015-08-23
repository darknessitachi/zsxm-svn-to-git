package com.netwander.explib.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.common.LimitPage;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.common.BaseService;

@Service
public class TempService  extends BaseService{
	/**
	 * TEMPTYPE: 1:导入定义   2：导出定义
	 * @param limit
	 * @param parMap
	 * @param sortInfo
	 * @param filterInfos
	 * @param xtuser
	 * @return
	 */
	public Object getListForTempImp(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		
		String ls_sql = "select * from xt_temp where  temptype=1 " ;

		if(name != null && !name.equals("")){
			ls_sql += " and tempmc like '%" + name + "%'";
		}
		
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by tempid ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
	
	public Object getListForTempExp(LimitPage limit, Map<String, String> parMap, SortInfo sortInfo, List<FilterInfo> filterInfos,Xtuser xtuser) {
		String name = parMap.get("name") == null ? "" :  parMap.get("name");
		
		String ls_sql = "select * from xt_temp where  temptype=2 " ;

		if(name != null && !name.equals("")){
			ls_sql += " and tempmc like '%" + name + "%'";
		}
		
		
		String sortCond = getGridSortString(sortInfo);
		String filterCond = getGridFilterString(filterInfos);
		if (filterCond != null) {
			ls_sql += filterCond;
		}
		if (sortCond != null) {
			ls_sql += " order by " + sortCond.split("_")[0];
		} else {
			ls_sql += " order by tempid ";
		}
		if (limit != null) {
			return this.queryForListWithPage(ls_sql, limit);
		} else {
			return this.simpleJdbcTemplate.queryForList(ls_sql);
		}
	}
}

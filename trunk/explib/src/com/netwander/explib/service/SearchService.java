package com.netwander.explib.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netwander.core.common.TreeBean;
import com.netwander.explib.service.common.BaseService;

@Service
public class SearchService extends BaseService{
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
}

package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.group.core.common.LimitPage;
import com.group.core.common.Page;
import com.group.zsxm.service.common.BaseService;

@Service
public class GzService extends BaseService {

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

	@SuppressWarnings("unchecked")
	public String savePx(Map parMap) {
		String sql = "";
		String maxid = "";
		if(parMap.get("pxid")==null || parMap.get("pxid").toString().equals("")){
			maxid = getMaxKey("GZ_PX").toString();
			sql = "insert into GZ_PX(rcid,pxid,pxmc,pxsj,pxdd,pxdw) values("+parMap.get("rcid").toString()+","+maxid+"," +
					"	'"+parMap.get("pxmc").toString()+"','"+parMap.get("pxsj").toString()+"','"+parMap.get("pxdd").toString()+"','"+parMap.get("pxdw").toString()+"')";
		}else{
			maxid = parMap.get("pxid").toString();
			sql = "update GZ_PX set pxmc='"+parMap.get("pxmc").toString()+"',pxdw='"+parMap.get("pxdw").toString()+"',pxsj='"+parMap.get("pxsj").toString()+"',pxdd='"+parMap.get("pxdd").toString()+"' where" +
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
}

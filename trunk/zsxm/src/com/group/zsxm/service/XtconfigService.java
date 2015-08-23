package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.group.zsxm.service.common.BaseService;

@Service
public class XtconfigService extends BaseService{
	public List<Map<String, Object>> getSjqList(){
		List<Map<String, Object>> l = new ArrayList();
		String sql = " select * from xt_dict where lbid=31 ";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	public void doSetSjq(String dictbh){
		String sql = "";
		dictbh += " ";
		String[] dic = dictbh.split("#");
		
		sql = "update xt_dict set pch='' where lbid=31 ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "update xt_dict set pch=1 where lbid=31 and dictbh='"+dic[0].trim()+"' ";
		this.getSimpleJdbcTemplate().update(sql);
		
		sql = "update xt_dict set pch=2 where lbid=31 and dictbh='"+dic[1].trim()+"' ";
		this.getSimpleJdbcTemplate().update(sql);
	}
	
	public List<Map<String, Object>> getGridszList(String type){
		List<Map<String, Object>> l = new ArrayList();
		String sql = " select * from xt_grid where gtype="+type+" order by gpx ";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	public void doSaveGrid(String type,String gcheckcolumn,String[] key,String[] gid,String[] gheader,String[] gwidth,String[] galign, String[] gpx,String[] gdisplay,String[] gscript ){
		String sql = "";
		if(key != null && key.length > 0){
			for(int i=0 ;i<key.length;i++){
				sql = "update xt_grid set gid='"+gid[i]+"',gheader='"+gheader[i]+"'," +
						"gwidth='"+gwidth[i]+"',galign='"+galign[i]+"',gpx='"+gpx[i]+"'," +
						"gdisplay='"+gdisplay[i]+"' where gtype="+type+" and gid='"+key[i]+"'";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
	}
	
	public List<Map<String, Object>> getQjwhszList(String type){
		List<Map<String, Object>> l = new ArrayList();
		String sql = " select * from xt_qjfw where qtype="+type+" order by qxh ";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	/***
	 * type:
	 * 1:注册资本（开办经费）
	 * 2:销售收入
	 * 3:销售收入增长率
	 * 4:税收
	 * 5:税收增长率
	 * 6:员工
	 * 7:员工增长率
	 * @param type
	 * @param qstrv
	 * @param qendv
	 */
	public void doSaveQjwh(String type,String[] qstrv,String[] qendv){
		String sql = "";
		if(qstrv != null && qstrv.length > 0){
			sql = "delete from xt_qjfw where qtype="+type;
			this.getSimpleJdbcTemplate().update(sql);
			for(int i=0 ;i<qstrv.length;i++){
				sql = "insert into xt_qjfw(qtype,qxh,qstrv,qendv,sm) values("+type+",'"+i+"','"+qstrv[i]+"','"+qendv[i]+"','')";
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
	}
	
}

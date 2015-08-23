package com.group.zsxm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.group.zsxm.service.common.BaseService;

@Service
public class AnalysService extends BaseService {
	@SuppressWarnings("unchecked")
	public List getListForDw(Map<String, String> parmMap) {
		if(parmMap.get("type").indexOf("A")>=0){
			String QT = parmMap.get("type").substring(1,2);
			String sql = " select QSTRV+'-'+QendV DM, QSTRV NL1,QENDV NL2 from xt_qjfw where qtype="+QT+" order by qxh";
			List<Map<String, Object>> list = new ArrayList();
			list = this.getJdbcTemplate().queryForList(sql, new Object[]{});
			
			for(int i=0;i<list.size();i++){
				String strnl = String.valueOf(list.get(i).get("NL1"));
				String endnl = String.valueOf(list.get(i).get("NL2"));
				sql = " update dw_info_v set fw='"+String.valueOf(list.get(i).get("DM"))+"' where 1=1 ";
				
				if(QT.equals("1")){
					if(!strnl.trim().equals("") ){
						sql += " and isnull(convert(numeric(20,2),zczb),0)>='"+strnl+"'" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and isnull(convert(numeric(20,2),zczb),0)<='"+endnl+"'" ;
					}
				}else if(QT.equals("2")){
					if(!strnl.trim().equals("") ){
						sql += " and isnull(convert(numeric(20,2),SNXSSR),0)>='"+strnl+"'" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and isnull(convert(numeric(20,2),SNXSSR),0)<='"+endnl+"'" ;
					}
				}else if(QT.equals("3")){
					if(!strnl.trim().equals("") ){
						sql += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00>=convert(numeric(20,2),'"+strnl+"')" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and ((isnull(convert(numeric(20,2),SNXSSR),0)-isnull(SNXSSR_,0))/isnull(nullif(SNXSSR_,0),1))*100.00<=convert(numeric(20,2),'"+endnl+"')" ;
					}
				}else if(QT.equals("4")){
					if(!strnl.trim().equals("") ){
						sql += " and isnull(convert(numeric(20,2),snjnss),0)>='"+strnl+"'" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and isnull(convert(numeric(20,2),snjnss),0)<='"+endnl+"'" ;
					}
				}else if(QT.equals("5")){
					if(!strnl.trim().equals("") ){
						sql += " and ((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00>=convert(numeric(20,2),'"+strnl+"')" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and ((isnull(convert(numeric(20,2),snjnss),0)-isnull(snjnss_,0))/isnull(nullif(snjnss_,0),1))*100.00<=convert(numeric(20,2),'"+endnl+"')" ;
					}
				}else if(QT.equals("6")){
					if(!strnl.trim().equals("") ){
						sql += " and isnull(convert(numeric(20,2),sndygs),0)>='"+strnl+"'" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and isnull(convert(numeric(20,2),sndygs),0)<='"+endnl+"'" ;
					}
				}else if(QT.equals("7")){
					if(!strnl.trim().equals("") ){
						sql += " and ((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00>=convert(numeric(20,2),'"+strnl+"')" ;
					}
					if(!endnl.trim().equals("") && !endnl.trim().equals("以上")){
						sql += " and ((isnull(convert(numeric(20,2),sndygs),0)-isnull(sndygs_,0))/isnull(nullif(sndygs_,0),1))*100.00<=convert(numeric(20,2),'"+endnl+"')" ;
					}
				}
				
				this.getSimpleJdbcTemplate().update(sql);
			}
		}
		
		return this.getSimpleJdbcTemplate().queryForList("{call QRY_DWTJ(:type)}",parmMap);
	}
	
	public List getListForDwcdxm(Map<String, String> parmMap) {
		return this.getSimpleJdbcTemplate().queryForList("{call QRY_DWCDXMTJ(:type)}",parmMap);
	}
	
	@SuppressWarnings("unchecked")
	public List getListForXm(Map<String, String> parmMap) {
		return this.getSimpleJdbcTemplate().queryForList("{call QRY_XMTJ(:type)}",parmMap);
	}
}

package com.group.zsxm.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.XtconfigService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/sys/xtconfig.jsp")
			})
public class XtconfigAction  extends BaseAction{
	
	@Autowired
	@Qualifier("xtconfigService")
	private XtconfigService xtconfigService;
	
	private String gcheckcolumn;
	private String[] key;
	private String[] gid;
	private String[] gheader;
	private String[] gwidth;
	private String[] galign;
	private String[] gpx;
	private String[] gdisplay;
	private String[] gscript;
	
	private String[] qstrv;
	private String[] qendv;
	
	private String dictbh;
	private Xtuser xtuser;
	private String type ;
	
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}  
	public String execute(){
		return Action.INPUT;
	}
	
	public void getSjqList(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		List<Map<String, Object>> list = xtconfigService.getSjqList();
		mapOut.put("data", list);
		this.renderJson(mapOut);
	}
	
	public void doSjq(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xtconfigService.doSetSjq(dictbh);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void getGridsz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		List<Map<String, Object>> list = xtconfigService.getGridszList(type);
		mapOut.put("data", list);
		this.renderJson(mapOut);
	}
	
	public void doSaveGrid(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xtconfigService.doSaveGrid(type, gcheckcolumn, key, gid, gheader, gwidth, galign, gpx, gdisplay,gscript);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void getQjwhsz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		List<Map<String, Object>> list = xtconfigService.getQjwhszList(type);
		mapOut.put("data", list);
		this.renderJson(mapOut);
	}
	

	public void doSaveQjwh(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xtconfigService.doSaveQjwh(type, qstrv, qendv);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void getGridFields(){
		
	}
	
	public void getGridCols(){
		
	}
	
	/**
	 * Mapping
	 * @return
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDictbh() {
		return dictbh;
	}
	public void setDictbh(String dictbh) {
		this.dictbh = dictbh;
	}
	public XtconfigService getXtconfigService() {
		return xtconfigService;
	}
	public void setXtconfigService(XtconfigService xtconfigService) {
		this.xtconfigService = xtconfigService;
	}
	public String getGcheckcolumn() {
		return gcheckcolumn;
	}
	public void setGcheckcolumn(String gcheckcolumn) {
		this.gcheckcolumn = gcheckcolumn;
	}
	public String[] getKey() {
		return key;
	}
	public void setKey(String[] key) {
		this.key = key;
	}
	public String[] getGid() {
		return gid;
	}
	public void setGid(String[] gid) {
		this.gid = gid;
	}
	public String[] getGheader() {
		return gheader;
	}
	public void setGheader(String[] gheader) {
		this.gheader = gheader;
	}
	public String[] getGwidth() {
		return gwidth;
	}
	public void setGwidth(String[] gwidth) {
		this.gwidth = gwidth;
	}
	public String[] getGalign() {
		return galign;
	}
	public void setGalign(String[] galign) {
		this.galign = galign;
	}
	public String[] getGpx() {
		return gpx;
	}
	public void setGpx(String[] gpx) {
		this.gpx = gpx;
	}
	
	public String[] getGdisplay() {
		return gdisplay;
	}
	public void setGdisplay(String[] gdisplay) {
		this.gdisplay = gdisplay;
	}
	public Xtuser getXtuser() {
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	public String[] getGscript() {
		return gscript;
	}
	public void setGscript(String[] gscript) {
		this.gscript = gscript;
	}
	public String[] getQstrv() {
		return qstrv;
	}
	public void setQstrv(String[] qstrv) {
		this.qstrv = qstrv;
	}
	public String[] getQendv() {
		return qendv;
	}
	public void setQendv(String[] qendv) {
		this.qendv = qendv;
	}
}

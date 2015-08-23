package com.group.zsxm.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

import com.group.core.Constants;
import com.group.core.common.Message;
import com.group.core.common.TreeBean;
import com.group.core.common.TreeFactory;
import com.group.zsxm.entity.DmMc;
import com.group.zsxm.entity.XtDict;
import com.group.zsxm.entity.Xtuser;
import com.group.zsxm.service.RcdaService;
import com.group.zsxm.web.common.BaseAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.inject.util.Function;

@ParentPackage("appDefault")
@Scope("prototype")
@Results( { @Result(name = "input", value = "/WEB-INF/jsp/rcda/rcda.jsp"),
			@Result(name = "update", value = "/WEB-INF/jsp/rcda/rcda_rep.jsp"),
			@Result(name = "rcxx", value = "/WEB-INF/jsp/rcda/rcda_rcxx.jsp"),
			@Result(name = "jszc", value = "/WEB-INF/jsp/rcda/rcda_jszc.jsp"),
			@Result(name = "jszcd", value = "/WEB-INF/jsp/rcda/rcda_jszc_d.jsp"),
			@Result(name = "xxjl", value = "/WEB-INF/jsp/rcda/rcda_xxjl.jsp"),
			@Result(name = "xxjld", value = "/WEB-INF/jsp/rcda/rcda_xxjl_d.jsp"),
			@Result(name = "gzjl", value = "/WEB-INF/jsp/rcda/rcda_gzjl.jsp"),
			@Result(name = "gzjld", value = "/WEB-INF/jsp/rcda/rcda_gzjl_d.jsp"),
			@Result(name = "ktxm", value = "/WEB-INF/jsp/rcda/rcda_ktxm.jsp"),
			@Result(name = "ktxmd", value = "/WEB-INF/jsp/rcda/rcda_ktxm_d.jsp"),
			@Result(name = "ryhj", value = "/WEB-INF/jsp/rcda/rcda_ryhj.jsp"),
			@Result(name = "ryhjd", value = "/WEB-INF/jsp/rcda/rcda_ryhj_d.jsp"),
			@Result(name = "cpjs", value = "/WEB-INF/jsp/rcda/rcda_cpjs.jsp"),
			@Result(name = "cpjsd", value = "/WEB-INF/jsp/rcda/rcda_cpjs_d.jsp"),
			@Result(name = "shjz", value = "/WEB-INF/jsp/rcda/rcda_shjz.jsp"),
			@Result(name = "shjzd", value = "/WEB-INF/jsp/rcda/rcda_shjz_d.jsp"),
			@Result(name = "bpzj", value = "/WEB-INF/jsp/rcda/rcda_bpzj.jsp"),
			@Result(name = "bpzjd", value = "/WEB-INF/jsp/rcda/rcda_bpzj_d.jsp"),
			@Result(name = "zylz", value = "/WEB-INF/jsp/rcda/rcda_zylz.jsp"),
			@Result(name = "zylzd", value = "/WEB-INF/jsp/rcda/rcda_zylz_d.jsp"),
			@Result(name = "zscq", value = "/WEB-INF/jsp/rcda/rcda_zscq.jsp"),
			@Result(name = "zscqd", value = "/WEB-INF/jsp/rcda/rcda_zscq_d.jsp"),
			@Result(name = "xsrz", value = "/WEB-INF/jsp/rcda/rcda_xsrz.jsp"),
			@Result(name = "xsrzd", value = "/WEB-INF/jsp/rcda/rcda_xsrz_d.jsp"),
			@Result(name = "jjbz", value = "/WEB-INF/jsp/rcda/rcda_jjbz.jsp")
			
		})
public class RcdaAction extends BaseAction{
	@Autowired
	@Qualifier("rcdaService")
	private RcdaService rcdaService;
	private Xtuser xtuser;
	
	private Map<Integer, List<XtDict>> xtdlbs;//基础数据集合
	
	private List<XtDict> xtdict1;//证件类别
	private List<XtDict> xtdict2;//学历
	private List<XtDict> xtdict3;//学位
	private List<XtDict> xtdict4;//性别
	private List<XtDict> xtdict5;//职称
	private List<XtDict> xtdict6;//专家状态
	private List<XtDict> xtdict7;//专家性质
	private List<XtDict> xtdict8;//专家用途
	private List<XtDict> xtdict9;//太岗状态
	private List<XtDict> xtdict10;//所在地区
	private List<XtDict> xtdict11;//从事行业
	private List<XtDict> xtdict12;//单位性质
	private List<XtDict> xtdict13;//学科专业
	private List<XtDict> xtdict14;//技术领域
	private List<XtDict> xtdict15;//专业
	private List<XtDict> xtdict16;//技术专长
	private List<XtDict> xtdict17;//知识产权情况
	private List<XtDict> xtdict18;//熟悉程度
	private List<XtDict> xtdict19;//完成情况
	private List<XtDict> xtdict20;//国籍
	private List<XtDict> xtdict21;//国籍
	private List<XtDict> xtdict22;//国籍
	private List<XtDict> xtdict23;//国籍
	private List<DmMc> dmmcs;
	
	private Map<String, String> rcdarcxx;
	
	//留学国家
	private String[] lxgjgjmc;
	
	//人才信息查询结果集
	private List<Map<String, String>> qlist;
	
	
	//人才信息的辅助信息
	private String[] rcxx1;
	private String[] rcxx2;
	private String[] rcxx3;
	private String[] rcxx4;
	private String[] rcxx5;
	private String[] rcxx6;
	private String[] rcxx7;
	//人才信息的辅助信息  序号
	private Integer maxxh;
	
	//判别人才有没有修改过相应的
	private Integer isxg;
	private String pname;
	private String xh;
	private Integer lbid;
	private String rcid;
	private String dm;
	private String[] dmkey;
	//操作类型   3： 修改，否则新增
	private Integer opttype;
	public void prepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	
	public String execute(){
		if(xtuser.getLoginbz() == 2){
			//人才登入
			rcid = xtuser.getXtrcid();
		}
		return Action.INPUT;
	}
	
	/**
	 * 新增
	 * @return
	 */
	public String preInsert(){
		return Action.INPUT;
	}
	
	/**
	 * 修改
	 */
	public String preUpdate(){
		return "update";
	}
	/**
	 * 人才信息
	 * @return
	 */
	public String preRcxx(){
		
		xtdlbs = rcdaService.getDictListWithSelectAll();
		xtdict1 = xtdlbs.get(1);
		xtdict2 = xtdlbs.get(2);
		xtdict3 = xtdlbs.get(3);
		xtdict4 = xtdlbs.get(4);
		xtdict5 = xtdlbs.get(5);
		xtdict6 = xtdlbs.get(6);
		xtdict7 = xtdlbs.get(7);
		xtdict8 = xtdlbs.get(8);
		xtdict9 = xtdlbs.get(9);
		xtdict12 = xtdlbs.get(12);
		xtdict20 = xtdlbs.get(20);
		xtdict21 = xtdlbs.get(21);
		xtdict22 = xtdlbs.get(22);
		rcdarcxx = rcdaService.preRcxx(rcid,"1");
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_PINFO", rcid, "1");
		}
		qlist = rcdaService.getLxgjList(rcid, "1");
		return "rcxx";
	}
	
	/**
	 * 保存人才信息
	 */
	public void doSaveRcxx(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcid = String.valueOf(rcdaService.doSaveRcxx(rcid,xtuser.getLoginbz(), rcdarcxx, lxgjgjmc,xtuser.getZcbz()));
			message = new Message("1",rcid);
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	/**
	 * 技术专长
	 */
	public String preJszc(){
		//xtdict18 = rcdaService.getDictListWithSelect(18);
		qlist = rcdaService.getJszcList(rcid, "1");
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_JSZC", rcid, null);
		}
		return "jszc";
	}
	

	public String preJszcI(){
		xtdict18 = rcdaService.getDictListWithSelect(18);
		return "jszcd";
	}
	
	public void doJszcI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doJszcI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preJszcU(){
		xtdict18 = rcdaService.getDictListWithSelect(18);
		rcdarcxx = rcdaService.getJszcU(rcid, 1, xh);
		return "jszcd";
	}
	
	public void doJszcU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doJszcU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doJszcD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doJszcD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	public void doSaveJszc(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveJszc(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4, rcxx5, rcxx6,rcxx7);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	**/
	/**
	 * 学习简历
	 */

	public String preXxjl(){
		//xtdict2 = rcdaService.getDictListWithSelect(2);
		//xtdict3 = rcdaService.getDictListWithSelect(3);
		qlist = rcdaService.getXxjlList(rcid, 1);
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_XXJL", rcid, null);
		}
		return "xxjl";
	}
	
	
	public String preXxjlI(){
		xtdict2 = rcdaService.getDictListWithSelect(2);
		xtdict3 = rcdaService.getDictListWithSelect(3);
		return "xxjld";
	}
	
	public void doXxjlI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXxjlI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preXxjlU(){
		xtdict2 = rcdaService.getDictListWithSelect(2);
		xtdict3 = rcdaService.getDictListWithSelect(3);
		rcdarcxx = rcdaService.getXxjlU(rcid, 1, xh);
		
		return "xxjld";
	}
	
	public void doXxjlU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXxjlU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doXxjlD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXxjlD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	public void doSaveXxjl(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveXxjl(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4, rcxx5, rcxx6, rcxx7);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}*/
	/**
	 * 工作简历
	 */
	public String preGzjl(){
		
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_WORDJL", rcid, null);
		}
		qlist = rcdaService.getGzjlList(rcid, "1");
		return "gzjl";
	}
	

	public String preGzjlI(){
		return "gzjld";
	}
	
	public void doGzjlI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doGzjlI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preGzjlU(){
		rcdarcxx = rcdaService.getGzjlU(rcid, 1, xh);
		return "gzjld";
	}
	
	public void doGzjlU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doGzjlU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doGzjlD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doGzjlD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	public void doSaveGzjl(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveGzjl(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 课题、项目
	 */
	public String preKtxm(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_ZCDDYJ", rcid, "1");
		}
		qlist = rcdaService.getKtxmList(rcid, "1");
		return "ktxm";
	}
	public String preKtxmI(){
		xtdict19 = rcdaService.getDictListWithSelect(19);
		return "ktxmd";
	}
	
	public void doKtxmI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doKtxmI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preKtxmU(){
		xtdict19 = rcdaService.getDictListWithSelect(19);
		rcdarcxx = rcdaService.getKtxmU(rcid, 1, xh);
		
		return "ktxmd";
	}
	
	public void doKtxmU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doKtxmU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doKtxmD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doKtxmD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	public void doSaveKtxm(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveKtxm(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 荣誉、获奖
	 */
	public String preRyhj(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_HJQK", rcid, "1");
		}
		qlist = rcdaService.getRyhjList(rcid, "1");
		return "ryhj";
	}
	
	public String preRyhjI(){
		return "ryhjd";
	}
	
	public void doRyhjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doRyhjI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preRyhjU(){
		//
		rcdarcxx = rcdaService.getRyhjU(rcid, 1, xh);
		return "ryhjd";
	}
	
	public void doRyhjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doRyhjU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doRyhjD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doRyhjD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	/**
	public void doSaveRyhj(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveRyhj(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}*/
	/**
	 * 产品、技术产业
	 */
	public String preCpjs(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_CPQSCYH", rcid, "1");
		}
		qlist = rcdaService.getCpjsList(rcid, "1");
		return "cpjs";
	}
	
	public String preCpjsI(){
		return "cpjsd";
	}
	
	public void doCpjsI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doCpjsI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preCpjsU(){
		//
		rcdarcxx = rcdaService.getCpjsU(rcid, 1, xh);
		return "cpjsd";
	}
	
	public void doCpjsU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doCpjsU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doCpjsD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doCpjsD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	/**
	public void doSaveCpjs(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveCpjs(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 社会兼、聘职
	 */
	public String preShjz(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_SHJZQK", rcid, "1");
		}
		qlist = rcdaService.getShjzList(rcid, "1");
		return "shjz";
	}
	

	public String preShjzI(){
		return "shjzd";
	}
	
	public void doShjzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doShjzI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preShjzU(){
		//
		rcdarcxx = rcdaService.getShjzU(rcid, 1, xh);
		return "shjzd";
	}
	
	public void doShjzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doShjzU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doShjzD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doShjzD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	/**
	public void doSaveShjz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveShjz(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4, rcxx5);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 被聘专家情况
	 */
	public String preBpzj(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_BPZJQK", rcid, "1");
		}
		
		qlist = rcdaService.getBpzjList(rcid, "1");
		return "bpzj";
	}
	

	public String preBpzjI(){
		
		int x= Integer.parseInt(xtuser.getLogindate().substring(0,4))-1960;
		dmmcs = new ArrayList<DmMc>();
		for(int i=0;i<=x;i++){
			DmMc dm = new DmMc(String.valueOf(1960+i),String.valueOf(1960+i));
			dmmcs.add(dm);
		}
		return "bpzjd";
	}
	
	public void doBpzjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doBpzjI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preBpzjU(){
		int x= Integer.parseInt(xtuser.getLogindate().substring(0,4))-1960;
		dmmcs = new ArrayList<DmMc>();
		for(int i=0;i<=x;i++){
			DmMc dm = new DmMc(String.valueOf(1960+i),String.valueOf(1960+i));
			dmmcs.add(dm);
		}
		rcdarcxx = rcdaService.getBpzjU(rcid, 1, xh);
		return "bpzjd";
	}
	
	public void doBpzjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doBpzjU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doBpzjD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doBpzjD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	
	/**
	public void doSaveBpzj(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveBpzj(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}*/
	/**
	 * 主要论著文
	 */
	public String preZylz(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_ZYLZLW", rcid, "1");
		}
		qlist = rcdaService.getZylzList(rcid, "1");
		return "zylz";
	}
	

	public String preZylzI(){
		
		return "zylzd";
	}
	
	public void doZylzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZylzI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preZylzU(){
		//
		rcdarcxx = rcdaService.getZylzU(rcid, 1, xh);
		return "zylzd";
	}
	
	public void doZylzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZylzU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doZylzD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZylzD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	/**
	public void doSaveZylz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveZylz(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}*/
	/**
	 * 知识产权情况
	 */
	public String preZscq(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_ZSCQ", rcid, "1");
		}
		qlist = rcdaService.getZscqList(rcid, "1");
		return "zscq";
	}
	


	public String preZscqI(){
		xtdict17 = rcdaService.getDictListWithSelect(17);
		return "zscqd";
	}
	
	public void doZscqI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZscqI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preZscqU(){
		xtdict17 = rcdaService.getDictListWithSelect(17);
		rcdarcxx = rcdaService.getZscqU(rcid, 1, xh);
		return "zscqd";
	}
	
	public void doZscqU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZscqU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doZscqD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doZscqD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	/**
	public void doSaveZscq(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveZscq(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 参加学术团任职
	 */
	public String preXsrz(){
		if(xtuser.getLoginbz() == 2){
			maxxh = rcdaService.getMaxCount("RC_XSRZ", rcid, "1");
		}
		qlist = rcdaService.getXsrzList(rcid, "1");
		return "xsrz";
	}
	

	public String preXsrzI(){
		return "xsrzd";
	}
	
	public void doXsrzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXsrzI(rcid, rcdarcxx , xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preXsrzU(){
		rcdarcxx = rcdaService.getXsrzU(rcid, 1, xh);
		return "xsrzd";
	}
	
	public void doXsrzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXsrzU(rcid, xh, rcdarcxx, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public void doXsrzD(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doXsrzD(rcid, dmkey, xtuser.getLoginbz(),xtuser.getZcbz());
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	/**
	public void doSaveXsrz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveXsrz(rcid,xtuser.getLoginbz(), rcxx1, rcxx2, rcxx3, rcxx4);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	*/
	/**
	 * 简介、备注
	 */
	public String preJjbz(){
		rcdarcxx = rcdaService.getJjbzInfo(rcid, "1");
		return "jjbz";
	}
	
	public void doSaveJjbz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			rcdaService.doSaveJjbz(rcid,xtuser.getLoginbz(), rcdarcxx);
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void doLoadrcTree(){
		List<TreeBean> treeBeans = rcdaService.getDictListWithTree(lbid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	public void doLoadRclbTree(){
		List<TreeBean> treeBeans = rcdaService.getRclbTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	public void getDictTreeWithStep(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xtdict14 = rcdaService.getDictTreeWithStep(lbid, dm);
			mapOut.put("info", xtdict14);
		}catch(Exception e){
			mapOut.put("info", new Message("-1",e.getMessage()));
		}
		renderText(JSONObject.fromMap(mapOut).toString());
	}
	
	public void getRcxxTreeWithStep(){
		List<TreeBean> treeBeans = rcdaService.getRcxxTreeWithStep(lbid, dm);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	//public 
	/**
	 * Mapping
	 * @return
	 */
	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}

	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}

	public List<XtDict> getXtdict1() {
		return xtdict1;
	}

	public void setXtdict1(List<XtDict> xtdict1) {
		this.xtdict1 = xtdict1;
	}

	public List<XtDict> getXtdict2() {
		return xtdict2;
	}

	public void setXtdict2(List<XtDict> xtdict2) {
		this.xtdict2 = xtdict2;
	}

	public List<XtDict> getXtdict3() {
		return xtdict3;
	}

	public void setXtdict3(List<XtDict> xtdict3) {
		this.xtdict3 = xtdict3;
	}

	public List<XtDict> getXtdict4() {
		return xtdict4;
	}

	public void setXtdict4(List<XtDict> xtdict4) {
		this.xtdict4 = xtdict4;
	}

	public List<XtDict> getXtdict5() {
		return xtdict5;
	}

	public void setXtdict5(List<XtDict> xtdict5) {
		this.xtdict5 = xtdict5;
	}

	public List<XtDict> getXtdict6() {
		return xtdict6;
	}

	public void setXtdict6(List<XtDict> xtdict6) {
		this.xtdict6 = xtdict6;
	}

	public List<XtDict> getXtdict7() {
		return xtdict7;
	}

	public void setXtdict7(List<XtDict> xtdict7) {
		this.xtdict7 = xtdict7;
	}

	public List<XtDict> getXtdict8() {
		return xtdict8;
	}

	public void setXtdict8(List<XtDict> xtdict8) {
		this.xtdict8 = xtdict8;
	}

	public List<XtDict> getXtdict9() {
		return xtdict9;
	}

	public void setXtdict9(List<XtDict> xtdict9) {
		this.xtdict9 = xtdict9;
	}

	public List<XtDict> getXtdict10() {
		return xtdict10;
	}

	public void setXtdict10(List<XtDict> xtdict10) {
		this.xtdict10 = xtdict10;
	}

	public List<XtDict> getXtdict11() {
		return xtdict11;
	}

	public void setXtdict11(List<XtDict> xtdict11) {
		this.xtdict11 = xtdict11;
	}

	public List<XtDict> getXtdict12() {
		return xtdict12;
	}

	public void setXtdict12(List<XtDict> xtdict12) {
		this.xtdict12 = xtdict12;
	}

	public List<XtDict> getXtdict13() {
		return xtdict13;
	}

	public void setXtdict13(List<XtDict> xtdict13) {
		this.xtdict13 = xtdict13;
	}

	public List<XtDict> getXtdict14() {
		return xtdict14;
	}

	public void setXtdict14(List<XtDict> xtdict14) {
		this.xtdict14 = xtdict14;
	}

	public List<XtDict> getXtdict15() {
		return xtdict15;
	}

	public void setXtdict15(List<XtDict> xtdict15) {
		this.xtdict15 = xtdict15;
	}

	public List<XtDict> getXtdict16() {
		return xtdict16;
	}

	public void setXtdict16(List<XtDict> xtdict16) {
		this.xtdict16 = xtdict16;
	}

	public List<XtDict> getXtdict17() {
		return xtdict17;
	}

	public void setXtdict17(List<XtDict> xtdict17) {
		this.xtdict17 = xtdict17;
	}

	public List<XtDict> getXtdict18() {
		return xtdict18;
	}

	public void setXtdict18(List<XtDict> xtdict18) {
		this.xtdict18 = xtdict18;
	}

	public List<XtDict> getXtdict19() {
		return xtdict19;
	}

	public void setXtdict19(List<XtDict> xtdict19) {
		this.xtdict19 = xtdict19;
	}

	public Integer getLbid() {
		return lbid;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public List<DmMc> getDmmcs() {
		return dmmcs;
	}

	public void setDmmcs(List<DmMc> dmmcs) {
		this.dmmcs = dmmcs;
	}

	public Map<String, String> getRcdarcxx() {
		return rcdarcxx;
	}

	public void setRcdarcxx(Map<String, String> rcdarcxx) {
		this.rcdarcxx = rcdarcxx;
	}

	public List<XtDict> getXtdict20() {
		return xtdict20;
	}

	public void setXtdict20(List<XtDict> xtdict20) {
		this.xtdict20 = xtdict20;
	}

	public String[] getLxgjgjmc() {
		return lxgjgjmc;
	}

	public void setLxgjgjmc(String[] lxgjgjmc) {
		this.lxgjgjmc = lxgjgjmc;
	}

	public String getRcid() {
		return rcid;
	}

	public void setRcid(String rcid) {
		this.rcid = rcid;
	}

	public String[] getRcxx1() {
		return rcxx1;
	}

	public void setRcxx1(String[] rcxx1) {
		this.rcxx1 = rcxx1;
	}

	public String[] getRcxx2() {
		return rcxx2;
	}

	public void setRcxx2(String[] rcxx2) {
		this.rcxx2 = rcxx2;
	}

	public String[] getRcxx3() {
		return rcxx3;
	}

	public void setRcxx3(String[] rcxx3) {
		this.rcxx3 = rcxx3;
	}

	public String[] getRcxx4() {
		return rcxx4;
	}

	public void setRcxx4(String[] rcxx4) {
		this.rcxx4 = rcxx4;
	}

	public String[] getRcxx5() {
		return rcxx5;
	}

	public void setRcxx5(String[] rcxx5) {
		this.rcxx5 = rcxx5;
	}

	public String[] getRcxx6() {
		return rcxx6;
	}

	public void setRcxx6(String[] rcxx6) {
		this.rcxx6 = rcxx6;
	}

	public String[] getRcxx7() {
		return rcxx7;
	}

	public void setRcxx7(String[] rcxx7) {
		this.rcxx7 = rcxx7;
	}

	public List<Map<String, String>> getQlist() {
		return qlist;
	}

	public void setQlist(List<Map<String, String>> qlist) {
		this.qlist = qlist;
	}

	public Integer getMaxxh() {
		return maxxh;
	}

	public void setMaxxh(Integer maxxh) {
		this.maxxh = maxxh;
	}

	public Integer getOpttype() {
		return opttype;
	}

	public void setOpttype(Integer opttype) {
		this.opttype = opttype;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String[] getDmkey() {
		return dmkey;
	}

	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List<XtDict> getXtdict21() {
		return xtdict21;
	}

	public void setXtdict21(List<XtDict> xtdict21) {
		this.xtdict21 = xtdict21;
	}

	public List<XtDict> getXtdict22() {
		return xtdict22;
	}

	public void setXtdict22(List<XtDict> xtdict22) {
		this.xtdict22 = xtdict22;
	}

	public List<XtDict> getXtdict23() {
		return xtdict23;
	}

	public void setXtdict23(List<XtDict> xtdict23) {
		this.xtdict23 = xtdict23;
	}

}

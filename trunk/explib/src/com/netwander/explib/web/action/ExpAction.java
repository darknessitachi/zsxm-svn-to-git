package com.netwander.explib.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.common.Message;
import com.netwander.core.common.TreeBean;
import com.netwander.core.common.TreeFactory;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.entity.DmMc;
import com.netwander.explib.entity.XtDict;
import com.netwander.core.Constants;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.ExpService;
import com.netwander.explib.service.SystemService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "input", value = "/WEB-INF/pages/exp/exp.jsp"),
			@Result(name = "update", value = "/WEB-INF/pages/exp/exp_rep.jsp"),
			@Result(name = "wbupdate", value = "/WEB-INF/pages/exp/exp_rep_wb.jsp"),
			@Result(name = "expzj", value = "/WEB-INF/pages/exp/exp_rep_zj.jsp"),
			@Result(name = "expzjzc", value = "/WEB-INF/pages/frame/expzj/expzc.jsp"),
			@Result(name = "exp", value = "/WEB-INF/pages/exp/exp_main.jsp"),
			@Result(name = "jszc", value = "/WEB-INF/pages/exp/exp_jszc.jsp"),
			@Result(name = "jszcd", value = "/WEB-INF/pages/exp/exp_jszc_d.jsp"),
			@Result(name = "xxjl", value = "/WEB-INF/pages/exp/exp_xxjl.jsp"),
			@Result(name = "xxjld", value = "/WEB-INF/pages/exp/exp_xxjl_d.jsp"),
			@Result(name = "gzjl", value = "/WEB-INF/pages/exp/exp_gzjl.jsp"),
			@Result(name = "gzjld", value = "/WEB-INF/pages/exp/exp_gzjl_d.jsp"),
			@Result(name = "ktxm", value = "/WEB-INF/pages/exp/exp_ktxm.jsp"),
			@Result(name = "ktxmd", value = "/WEB-INF/pages/exp/exp_ktxm_d.jsp"),
			@Result(name = "ryhj", value = "/WEB-INF/pages/exp/exp_ryhj.jsp"),
			@Result(name = "ryhjd", value = "/WEB-INF/pages/exp/exp_ryhj_d.jsp"),
			@Result(name = "cpjs", value = "/WEB-INF/pages/exp/exp_cpjs.jsp"),
			@Result(name = "cpjsd", value = "/WEB-INF/pages/exp/exp_cpjs_d.jsp"),
			@Result(name = "shjz", value = "/WEB-INF/pages/exp/exp_shjz.jsp"),
			@Result(name = "shjzd", value = "/WEB-INF/pages/exp/exp_shjz_d.jsp"),
			@Result(name = "bpzj", value = "/WEB-INF/pages/exp/exp_bpzj.jsp"),
			@Result(name = "bpzjd", value = "/WEB-INF/pages/exp/exp_bpzj_d.jsp"),
			@Result(name = "zylz", value = "/WEB-INF/pages/exp/exp_zylz.jsp"),
			@Result(name = "zylzd", value = "/WEB-INF/pages/exp/exp_zylz_d.jsp"),
			@Result(name = "zscq", value = "/WEB-INF/pages/exp/exp_zscq.jsp"),
			@Result(name = "zscqd", value = "/WEB-INF/pages/exp/exp_zscq_d.jsp"),
			@Result(name = "xsrz", value = "/WEB-INF/pages/exp/exp_xsrz.jsp"),
			@Result(name = "xsrzd", value = "/WEB-INF/pages/exp/exp_xsrz_d.jsp"),
			@Result(name = "otherinfo", value = "/WEB-INF/pages/exp/exp_otherInfo.jsp"),
			@Result(name = "exphtyy", value = "/WEB-INF/pages/frame/expzj/exp_htyy.jsp")
			
		})
public class ExpAction extends BaseAction{
	@Autowired
	@Qualifier("expService")
	private ExpService expService;
	
	@Autowired
	@Qualifier("systemService")
	private SystemService systemService;
	
	
	private String wbuser;
	private String wbpassword;
	private String wburl;
	private String wbuserid;
	private String wbfldm;
	private String wbappname;

	private Map<Integer, List<XtDict>> xtdlbs;//基础数据集合
	private List<XtDict> xtdict;
	private List<XtDict> xtdictsel;
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
	private List<XtDict> xtdict24;//
	private List<XtDict> xtdict25;//
	private List<XtDict> xtdict26;//
	private List<XtDict> xtdict28;//
	private List<XtDict> xtdict31;
	private List<XtDict> xtdict36;
	private String roledm;
	private List<DmMc> dmmcs;
	
	private String[] fxgsmc;
	private String[] fxgstzje;
	private String[] fxgscgbl;
	private Map<String, String> exp;
	private String[] bpzjqk;
	//人才信息的辅助信息  序号
	private Integer maxxh;
	
	private String pname;
	private String xh;
	private Integer lbid;
	private String fldm;
	private String rcid;
	private String dm;
	private String[] dmkey;
	private String[] dmkey2;
	//操作类型   3： 修改，否则新增
	private Integer opttype;
	
	private Integer shbz;
	
	private String thyy;
	//留学国家
	private String[] lxgjgjmc;
	/////
	private String[] expfls;
	private String[] fldms;
	private String[] sxly;
	private String[] csly;
	private String[] sxly1;
	private String[] sxly2;
	private String[] sxly3;
	
	private String[] sxzy;
	private String[] cszy;
	private String[] sxzy1;
	private String[] sxzy2;
	private String[] sxzy3;
	
	//人才信息查询结果集
	private List<Map<String, Object>> qlist;
	
	private List<Map<String, Object>> expfllist;
	
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	public String execute(){
		return Action.INPUT;
	}
	
	///
	
	/****
	 * 专家信息修改
	 * @return
	 */
	public String expzj(){
		if(xtuser.getExprcid() == null || xtuser.getExprcid() == 0){
			rcid = "";
		}else{
			rcid = String.valueOf(xtuser.getExprcid());
			shbz = expService.getExpTbbz(rcid);
		}
		
		return "expzj";
	}
	
	
	public String expzjZc(){
		if(xtuser.getExprcid() == null || xtuser.getExprcid() == 0){
			rcid = "";
		}else{
			
			rcid = String.valueOf(xtuser.getExprcid());
			shbz = expService.getExpTbbz(rcid);
		}
		
		return "expzjzc";
	}
	
	public String wbLogin(){
		try{
			xtuser = systemService.checkLogon("1", wbuser, wbpassword);
			if(xtuser != null){
				getSession().setAttribute(Constants.USER_SESSION_KEY, xtuser);
			}else{
				return "error";
			}
		}catch(Exception e){
			return "error";
		}
		return "wbupdate";
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
	 * 专家信息
	 * @return
	 */
	public String preExp(){
		xtdlbs = expService.getDictListWithSelectAll();
		roledm = xtuser.getRoledm();
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
		xtdict25 = xtdlbs.get(25);
		xtdict22 = xtdlbs.get(22);
		xtdict23 = xtdlbs.get(23);
		xtdict36 = xtdlbs.get(36);
		xtdict = expService.getDictListWithSelectByLen(14, 3);
		expfllist = expService.getExpFlList2(xtuser.getRoledm(), xtuser.getUserid(), rcid);
		if(xtuser.getLoginbz()==1){
			exp = expService.preExpMain(rcid);
			qlist = expService.getLxgjList(rcid);
			if(exp.get("sctzhy") != null && !exp.get("sctzhy").equals("")){
				dmkey2 = exp.get("sctzhy").split(",");
			}
		}else{
			exp = expService.preExpMainZj(rcid);
			qlist = expService.getLxgjListZj(rcid);
			if(exp.get("sctzhy") != null && !exp.get("sctzhy").equals("")){
				dmkey2 = exp.get("sctzhy").split(",");
			}
		}
		xtdictsel = expService.getDictWithSel(rcid);
		if(xtdictsel != null && xtdictsel.size() > 0){
			dmkey = new String[xtdictsel.size()];
			for(int i=0;i<xtdictsel.size();i++){
				dmkey[i] = xtdictsel.get(i).getDictbh();
			}
		}
		
		if(rcid == null || rcid.equals("")){
			exp = new HashMap();
			
			exp.put("exptype", "");
			exp.put("dwdm", "");
			exp.put("rcname", "");
			exp.put("oldname", "");
			exp.put("sex", "");
			exp.put("nation", "");
			exp.put("jg", "");
			exp.put("jg_mc", "");
			exp.put("birthday", "");
			exp.put("zjlb", "");
			exp.put("zjno", "");
			exp.put("rclb", "");
			exp.put("xl", "");
			exp.put("zc", "");
			exp.put("zw", "");
			exp.put("xw", "");
			exp.put("email", "");
			exp.put("ptel", "");
			exp.put("xxzy", "");
			exp.put("xxzy_mc", "");
			exp.put("sxzy1", "");
			exp.put("sxzy2", "");
			exp.put("sxzy3", "");
			exp.put("cszy", "");
			exp.put("sxly", "");
			exp.put("csly", "");
			exp.put("sxly1", "");
			exp.put("sxly2", "");
			exp.put("sxly3", "");
			exp.put("sxlyo", "");
			exp.put("cslyo", "");
			exp.put("sxly1o", "");
			exp.put("sxly2o", "");
			exp.put("sxly3o", "");
			exp.put("byxx", "");
			exp.put("byrq", "");
			exp.put("workunit", "");
			exp.put("szdq", "");
			exp.put("szdq_mc", "");
			exp.put("dwdq", "");
			exp.put("dwxz", "");
			exp.put("dwaddr", "");
			exp.put("officetel", "");
			exp.put("dwcode", "");
			exp.put("fax", "");
			exp.put("jtaddr", "");
			exp.put("jtcode", "");
			exp.put("jttel", "");
			exp.put("zgbs", "");
			exp.put("xsjt", "");
			exp.put("hdzz", "");
			exp.put("bgbs", "");
			exp.put("fjpath", "");
			exp.put("fjmc", "");
			exp.put("info", "");
			exp.put("expgjbs", "");
			exp.put("fid", "");
			exp.put("expjsdf", "");
			exp.put("EXPHBDF", "");
			exp.put("sctzhy", "");
			exp.put("dwssxt", "");
			exp.put("dwssxt2", "");
			exp.put("dwssxt_mc", "");
			exp.put("dwsshy", "");
			
			exp.put("lxgj", "");
			exp.put("lxgjmc", "");
			exp.put("csly_1", "");
			exp.put("sxly1_1", "");
			exp.put("sxly2_1", "");
			exp.put("sxly3_1", "");
			
			exp.put("csly_", "");
			exp.put("sxly1_", "");
			exp.put("sxly2_", "");
			exp.put("sxly3_", "");
		}
		
		return "exp";
	}
	
	public void getExpflList(){
		String xml = "";
		Map<String, Object> mapOut = new HashMap<String, Object>();
		xml = expService.getExpFlList(xtuser.getUserid(), xtuser.getRoledm(),rcid);
		mapOut.put("expfl", xml);
		this.renderJson(mapOut);
	}
	

	public void getExpflListView(){
		String xml = "";
		Map<String, Object> mapOut = new HashMap<String, Object>();
		xml = expService.getExpFlListView(xtuser.getUserid(), xtuser.getRoledm(),rcid);
		mapOut.put("expfl", xml);
		this.renderJson(mapOut);
	}
	
	/**
	 * 保存专家信息
	 */
	public void doSaveExp(){
		
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				rcid = String.valueOf(expService.doSaveExpMain(rcid,xtuser.getRoledm(),xtuser.getUserid(),exp, lxgjgjmc,fldms,sxly,csly,sxly1,sxly2,sxly3
						,sxzy,cszy,sxzy1,sxzy2,sxzy3,expfls));
			}else{
				if(!rcid.equals("")){
					if(expService.getExpTbbz(rcid) == 3){
						throw new BusException("信息已上报，不能操作！");
					}
				}
				rcid = String.valueOf(expService.doSaveExpMainZj(rcid,exp, lxgjgjmc));
			}
			message = new Message("1",rcid);
			xtuser.setExprcid(Integer.parseInt(rcid));
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	/**
	 * 技术专长----专业领域
	 */
	public String preJszc(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getJszcList(rcid);
		}else{
			qlist = expService.getJszcListZj(rcid);
		}
		return "jszc";
	}
	

	public String preJszcI(){
		xtdict18 = expService.getDictListWithSelect(18);
		return "jszcd";
	}
	
	public void doJszcI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doJszcI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doJszcIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preJszcU(){
		
		xtdict18 = expService.getDictListWithSelect(18);
		if(xtuser.getLoginbz() == 1){
			exp = expService.getJszcU(rcid, xh);
		}else{
			exp = expService.getJszcUZj(rcid, xh);
		}
		return "jszcd";
	}
	
	public void doJszcU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doJszcU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doJszcUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doJszcD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doJszcDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}

	/**
	 * 学习简历
	 */

	public String preXxjl(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getXxjlList(rcid);
		}else{
			qlist = expService.getXxjlListZj(rcid);
		}
		return "xxjl";
	}
	
	
	public String preXxjlI(){
		xtdict2 = expService.getDictListWithSelect(2);
		xtdict3 = expService.getDictListWithSelect(3);
		return "xxjld";
	}
	
	public void doXxjlI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doXxjlI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doXxjlIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preXxjlU(){
		xtdict2 = expService.getDictListWithSelect(2);
		xtdict3 = expService.getDictListWithSelect(3);
		if(xtuser.getLoginbz() == 1){
			exp = expService.getXxjlU(rcid, xh);
		}else{
			exp = expService.getXxjlUZj(rcid, xh);
		}
		return "xxjld";
	}
	
	public void doXxjlU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doXxjlU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doXxjlUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doXxjlD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doXxjlDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}

	
	/**
	 * 工作简历
	 */
	public String preGzjl(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getGzjlList(rcid);
		}else{
			qlist = expService.getGzjlListZj(rcid);
		}
		return "gzjl";
	}
	

	public String preGzjlI(){
		return "gzjld";
	}
	
	public void doGzjlI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doGzjlI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doGzjlIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preGzjlU(){
		if(xtuser.getLoginbz() == 1){
			exp = expService.getGzjlU(rcid, xh);
		}else{
			exp = expService.getGzjlUZj(rcid, xh);
		}
		return "gzjld";
	}
	
	public void doGzjlU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doGzjlU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doGzjlUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doGzjlD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doGzjlDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}

	/**
	 * 科研项目
	 */
	public String preKtxm(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getKtxmList(rcid);
		}else{
			qlist = expService.getKtxmListZj(rcid);
		}
		return "ktxm";
	}
	public String preKtxmI(){
		xtdict19 = expService.getDictListWithSelect(19);
		xtdict24 = expService.getDictListWithSelect(24);
		xtdict26 = expService.getDictListWithSelect(26);
		xtdict28 = expService.getDictListWithSelect(28);
		exp = new HashMap();
		exp.put("zzje", "0.00");
		return "ktxmd";
	}
	
	public void doKtxmI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doKtxmI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doKtxmIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preKtxmU(){
		xtdict19 = expService.getDictListWithSelect(19);
		xtdict24 = expService.getDictListWithSelect(24);
		xtdict26 = expService.getDictListWithSelect(26);
		xtdict28 = expService.getDictListWithSelect(28);
		
		if(xtuser.getLoginbz() == 1){
			exp = expService.getKtxmU(rcid, xh);
		}else{
			exp = expService.getKtxmUZj(rcid, xh);
		}
		return "ktxmd";
	}
	
	public void doKtxmU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doKtxmU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doKtxmUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doKtxmD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doKtxmDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}

	
	/**
	 * 荣誉、获奖
	 */
	public String preRyhj(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getRyhjList(rcid);
		}else{
			qlist = expService.getRyhjListZj(rcid);
		}
		return "ryhj";
	}
	
	public String preRyhjI(){
		xtdict = expService.getDictListWithSelect(32);
		return "ryhjd";
	}
	
	public void doRyhjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doRyhjI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doRyhjIZj(rcid, exp );
			}
			
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preRyhjU(){
		xtdict = expService.getDictListWithSelect(32);
		if(xtuser.getLoginbz() == 1){
			exp = expService.getRyhjU(rcid, xh);
		}else{
			exp = expService.getRyhjUZj(rcid, xh);
		}
		return "ryhjd";
	}
	
	public void doRyhjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doRyhjU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doRyhjUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doRyhjD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doRyhjDZj(rcid, dmkey);
			}
			
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	/**
	 * 社会兼、聘职
	 */
	public String preShjz(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getShjzList(rcid);
		}else{
			qlist = expService.getShjzListZj(rcid);
		}
		return "shjz";
	}
	

	public String preShjzI(){
		return "shjzd";
	}
	
	public void doShjzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doShjzI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doShjzIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preShjzU(){
		//
		if(xtuser.getLoginbz() == 1){
			exp = expService.getShjzU(rcid, xh);
		}else{
			exp = expService.getShjzUZj(rcid, xh);
		}
		return "shjzd";
	}
	
	public void doShjzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doShjzU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doShjzUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doShjzD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doShjzDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	
	/**
	 * 被聘专家情况====》专家类别
	 * 工作人员，没有权限增加
	 */
	public String preBpzj(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getBpzjList(rcid);
		}else{
			qlist = expService.getBpzjListZj(rcid);
		}
		return "bpzj";
	}
	
	public String preBpzjI(){
		
		int x= Integer.parseInt(xtuser.getLogindate().substring(0,4))-1960;
		dmmcs = new ArrayList<DmMc>();
		for(int i=0;i<=x;i++){
			DmMc dm = new DmMc(String.valueOf(1960+i),String.valueOf(1960+i));
			dmmcs.add(dm);
		}
		xtdict25 = expService.getDictListWithBpzjSelect(25,rcid);
		return "bpzjd";
	}
	
	public void doBpzjI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doBpzjI(rcid, exp ,bpzjqk);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doBpzjIZj(rcid, exp ,bpzjqk);
			}
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
		xtdict25 = expService.getDictListWithBpzjSelect(25,rcid);
		if(xtuser.getLoginbz() == 1){
			exp = expService.getBpzjU(rcid, xh);
		}else{
			exp = expService.getBpzjUZj(rcid, xh);
		}
		return "bpzjd";
	}
	
	public void doBpzjU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doBpzjU(rcid, xh, exp, bpzjqk);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doBpzjUZj(rcid, xh, exp, bpzjqk);
			}
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
			if(xtuser.getLoginbz() == 1){
				expService.doBpzjD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doBpzjDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	

	/**
	 * 主要论著文
	 */
	public String preZylz(){
		if(xtuser.getLoginbz() == 1 ){
			qlist = expService.getZylzList(rcid);
		}else{
			qlist = expService.getZylzListZj(rcid);
		}
		return "zylz";
	}
	

	public String preZylzI(){
		xtdict31 = expService.getDictListWithSelect(31);
		return "zylzd";
	}
	
	public void doZylzI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1 ){
				expService.doZylzI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZylzIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preZylzU(){
		xtdict31 = expService.getDictListWithSelect(31);
		if(xtuser.getLoginbz() == 1){ 
			exp = expService.getZylzU(rcid, xh);
		}else{
			exp = expService.getZylzUZj(rcid, xh);
		}
		return "zylzd";
	}
	
	public void doZylzU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){ 
				expService.doZylzU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZylzUZj(rcid, xh, exp);
			}
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
			if(xtuser.getLoginbz() == 1){ 
				expService.doZylzD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZylzDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	

	/**
	 * 知识产权情况
	 */
	public String preZscq(){
		if(xtuser.getLoginbz() == 1){
			qlist = expService.getZscqList(rcid);
		}else{
			qlist = expService.getZscqListZj(rcid);
		}
		return "zscq";
	}
	


	public String preZscqI(){
		xtdict17 = expService.getDictListWithSelect(17);
		return "zscqd";
	}
	
	public void doZscqI(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doZscqI(rcid, exp );
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZscqIZj(rcid, exp );
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preZscqU(){
		xtdict17 = expService.getDictListWithSelect(17);
		if(xtuser.getLoginbz() == 1){
			exp = expService.getZscqU(rcid, xh);
		}else{
			exp = expService.getZscqUZj(rcid, xh);
		}
		return "zscqd";
	}
	
	public void doZscqU(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doZscqU(rcid, xh, exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZscqUZj(rcid, xh, exp);
			}
			
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
			if(xtuser.getLoginbz() == 1){
				expService.doZscqD(rcid, dmkey);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doZscqDZj(rcid, dmkey);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}	
	
	/**
	 * 简介、备注
	 */
	public String preJjbz(){
		if(xtuser.getLoginbz() == 1){
			exp = expService.getJjbzInfo(rcid, "1");
		}else{
			exp = expService.getJjbzInfoZj(rcid, "1");
		}
		return "jjbz";
	}
	public void doSaveJjbz(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doSaveJjbz(rcid,xtuser.getLoginbz(), exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doSaveJjbzZj(rcid,xtuser.getLoginbz(), exp);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String preOtherinfo(){
		if(xtuser.getLoginbz() == 1){
			exp = expService.getOtherInfo(rcid, "1");
		}else{
			exp = expService.getOtherInfoZj(rcid, "1");
		}
		return "otherinfo";
	}
	public void doSaveOtherinfo(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			if(xtuser.getLoginbz() == 1){
				expService.doSaveOtherInfo(rcid,xtuser.getLoginbz(), exp);
			}else{
				if(expService.getExpTbbz(rcid) == 3){
					throw new BusException("信息已上报，不能操作！");
				}
				expService.doSaveOtherInfoZj(rcid,xtuser.getLoginbz(), exp);
			}
			message = new Message("1","保存成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	
	public void doExpInfoSb(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		if(xtuser.getExprcid() == null || xtuser.getExprcid()==0 ){
			message = new Message("-1","请选填写基本信息！");
		}else{
			if(expService.getExpTbbz(xtuser.getExprcid().toString()) != 3){
				expService.doExpZjTj(xtuser.getExprcid().toString());
				message = new Message("1","保存成功");
			}else{
				message = new Message("-1","信息已经上报！");
			}
		}
		mapOut.put("message", message);
		this.renderJson(mapOut);
	}
	
	public String getExphtyy(){
		thyy = expService.getExpHtyy(xtuser.getExprcid().toString());
		return "exphtyy";
	}
	
	public void doLoadrcTree(){
		List<TreeBean> treeBeans = expService.getDictListWithTree(lbid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	/**
	public void doLoadZfjlTree(){
		List<TreeBean> treeBeans = expService.getExpFlListWithTree(xtuser.getRoledm(),xtuser.getUserid(),rcid);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(true);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	**/
	
	public void doLoadrcFlTree(){
		List<TreeBean> treeBeans = expService.getFlDictListWithTree(lbid,fldm);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	public void doLoadRclbTree(){
		List<TreeBean> treeBeans = expService.getRclbTree();
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	public void getDictTreeWithStep(){
		Map<String, Object> mapOut = new HashMap<String, Object>();
		try{
			xtdict14 = expService.getDictTreeWithStep(lbid, dm);
			mapOut.put("info", xtdict14);
		}catch(Exception e){
			mapOut.put("info", new Message("-1",e.getMessage()));
		}
		renderText(JSONObject.fromMap(mapOut).toString());
	}
	
	public void getRcxxTreeWithStep(){
		List<TreeBean> treeBeans = expService.getRcxxTreeWithStep(lbid, dm);
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	
	public void getQueryTree(){
		List<TreeBean> treeBeans = new ArrayList();
		treeBeans = expService.getDictListWithTree(Integer.parseInt(dm));
		TreeFactory tf = new TreeFactory();	
		tf.setPdm("0");
		tf.setIsDisabledCheck(false);
		tf.setRootText("选择数据");
		renderText(tf.create(treeBeans));
	}
	
	/***
	 * Mapping
	 * @return
	 */
	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}

	public ExpService getExpService() {
		return expService;
	}

	public void setExpService(ExpService expService) {
		this.expService = expService;
	}

	public Map<Integer, List<XtDict>> getXtdlbs() {
		return xtdlbs;
	}

	public void setXtdlbs(Map<Integer, List<XtDict>> xtdlbs) {
		this.xtdlbs = xtdlbs;
	}

	public List<XtDict> getXtdict() {
		return xtdict;
	}

	public void setXtdict(List<XtDict> xtdict) {
		this.xtdict = xtdict;
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

	public List<XtDict> getXtdict20() {
		return xtdict20;
	}

	public void setXtdict20(List<XtDict> xtdict20) {
		this.xtdict20 = xtdict20;
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

	public List<XtDict> getXtdict24() {
		return xtdict24;
	}

	public void setXtdict24(List<XtDict> xtdict24) {
		this.xtdict24 = xtdict24;
	}

	public List<XtDict> getXtdict25() {
		return xtdict25;
	}

	public void setXtdict25(List<XtDict> xtdict25) {
		this.xtdict25 = xtdict25;
	}

	public List<XtDict> getXtdict26() {
		return xtdict26;
	}

	public void setXtdict26(List<XtDict> xtdict26) {
		this.xtdict26 = xtdict26;
	}

	public List<XtDict> getXtdict28() {
		return xtdict28;
	}

	public void setXtdict28(List<XtDict> xtdict28) {
		this.xtdict28 = xtdict28;
	}

	public List<XtDict> getXtdict31() {
		return xtdict31;
	}

	public void setXtdict31(List<XtDict> xtdict31) {
		this.xtdict31 = xtdict31;
	}

	public List<DmMc> getDmmcs() {
		return dmmcs;
	}

	public void setDmmcs(List<DmMc> dmmcs) {
		this.dmmcs = dmmcs;
	}

	public String[] getFxgsmc() {
		return fxgsmc;
	}

	public void setFxgsmc(String[] fxgsmc) {
		this.fxgsmc = fxgsmc;
	}

	public String[] getFxgstzje() {
		return fxgstzje;
	}

	public void setFxgstzje(String[] fxgstzje) {
		this.fxgstzje = fxgstzje;
	}

	public String[] getFxgscgbl() {
		return fxgscgbl;
	}

	public void setFxgscgbl(String[] fxgscgbl) {
		this.fxgscgbl = fxgscgbl;
	}

	public Map<String, String> getExp() {
		return exp;
	}

	public void setExp(Map<String, String> exp) {
		this.exp = exp;
	}

	public String[] getBpzjqk() {
		return bpzjqk;
	}

	public void setBpzjqk(String[] bpzjqk) {
		this.bpzjqk = bpzjqk;
	}

	public Integer getMaxxh() {
		return maxxh;
	}

	public void setMaxxh(Integer maxxh) {
		this.maxxh = maxxh;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public Integer getLbid() {
		return lbid;
	}

	public void setLbid(Integer lbid) {
		this.lbid = lbid;
	}

	public String getRcid() {
		return rcid;
	}

	public void setRcid(String rcid) {
		this.rcid = rcid;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String[] getDmkey() {
		return dmkey;
	}

	public void setDmkey(String[] dmkey) {
		this.dmkey = dmkey;
	}

	public Integer getOpttype() {
		return opttype;
	}

	public void setOpttype(Integer opttype) {
		this.opttype = opttype;
	}

	public String[] getLxgjgjmc() {
		return lxgjgjmc;
	}

	public void setLxgjgjmc(String[] lxgjgjmc) {
		this.lxgjgjmc = lxgjgjmc;
	}

	public List<Map<String, Object>> getQlist() {
		return qlist;
	}

	public void setQlist(List<Map<String, Object>> qlist) {
		this.qlist = qlist;
	}

	public Integer getShbz() {
		return shbz;
	}

	public void setShbz(Integer shbz) {
		this.shbz = shbz;
	}

	public String getThyy() {
		return thyy;
	}

	public void setThyy(String thyy) {
		this.thyy = thyy;
	}

	public List<Map<String, Object>> getExpfllist() {
		return expfllist;
	}

	public void setExpfllist(List<Map<String, Object>> expfllist) {
		this.expfllist = expfllist;
	}

	public String getFldm() {
		return fldm;
	}

	public void setFldm(String fldm) {
		this.fldm = fldm;
	}

	public String[] getFldms() {
		return fldms;
	}

	public void setFldms(String[] fldms) {
		this.fldms = fldms;
	}

	public String[] getSxly() {
		return sxly;
	}

	public void setSxly(String[] sxly) {
		this.sxly = sxly;
	}

	public String[] getCsly() {
		return csly;
	}

	public void setCsly(String[] csly) {
		this.csly = csly;
	}

	public String[] getSxly1() {
		return sxly1;
	}

	public void setSxly1(String[] sxly1) {
		this.sxly1 = sxly1;
	}

	public String[] getSxly2() {
		return sxly2;
	}

	public void setSxly2(String[] sxly2) {
		this.sxly2 = sxly2;
	}

	public String[] getSxly3() {
		return sxly3;
	}

	public void setSxly3(String[] sxly3) {
		this.sxly3 = sxly3;
	}

	public String[] getExpfls() {
		return expfls;
	}

	public void setExpfls(String[] expfls) {
		this.expfls = expfls;
	}

	public String getRoledm() {
		return roledm;
	}

	public void setRoledm(String roledm) {
		this.roledm = roledm;
	}

	public String[] getSxzy() {
		return sxzy;
	}

	public void setSxzy(String[] sxzy) {
		this.sxzy = sxzy;
	}

	public String[] getCszy() {
		return cszy;
	}

	public void setCszy(String[] cszy) {
		this.cszy = cszy;
	}

	public String[] getSxzy1() {
		return sxzy1;
	}

	public void setSxzy1(String[] sxzy1) {
		this.sxzy1 = sxzy1;
	}

	public String[] getSxzy2() {
		return sxzy2;
	}

	public void setSxzy2(String[] sxzy2) {
		this.sxzy2 = sxzy2;
	}

	public String[] getSxzy3() {
		return sxzy3;
	}

	public void setSxzy3(String[] sxzy3) {
		this.sxzy3 = sxzy3;
	}

	public String getWbuser() {
		return wbuser;
	}

	public void setWbuser(String wbuser) {
		this.wbuser = wbuser;
	}

	public String getWbpassword() {
		return wbpassword;
	}

	public void setWbpassword(String wbpassword) {
		this.wbpassword = wbpassword;
	}

	public String getWburl() {
		return wburl;
	}

	public void setWburl(String wburl) {
		this.wburl = wburl;
	}

	public String getWbuserid() {
		return wbuserid;
	}

	public void setWbuserid(String wbuserid) {
		this.wbuserid = wbuserid;
	}

	public String getWbfldm() {
		return wbfldm;
	}

	public void setWbfldm(String wbfldm) {
		this.wbfldm = wbfldm;
	}

	public List<XtDict> getXtdict36() {
		return xtdict36;
	}

	public void setXtdict36(List<XtDict> xtdict36) {
		this.xtdict36 = xtdict36;
	}

	public List<XtDict> getXtdictsel() {
		return xtdictsel;
	}

	public void setXtdictsel(List<XtDict> xtdictsel) {
		this.xtdictsel = xtdictsel;
	}

	public String[] getDmkey2() {
		return dmkey2;
	}

	public void setDmkey2(String[] dmkey2) {
		this.dmkey2 = dmkey2;
	}

	public String getWbappname() {
		return wbappname==null?"zjps":wbappname;
	}

	public void setWbappname(String wbappname) {
		this.wbappname = wbappname;
	}

	
}

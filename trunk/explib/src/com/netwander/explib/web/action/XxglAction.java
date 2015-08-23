package com.netwander.explib.web.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.explib.entity.Info_zg;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.service.XxglService;
import com.netwander.explib.web.common.BaseAction;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { @Result(name = "xxzgpre", value = "/widgets/xxzgEdit.jsp"),
			@Result(name = "uploadok", value = "/WEB-INF/pages/xxgl/fileUploadDone.jsp"),
			@Result(name = "zgsp", value = "/WEB-INF/pages/xxgl/xxsp.jsp"),
			@Result(name = "showXxzg", value = "/WEB-INF/pages/xxgl/showXxzg.jsp"),
			@Result(name = "xxglpre", value = "/WEB-INF/pages/xxgl/xxgl.jsp"),
			@Result(name = "lmglpre", value = "/WEB-INF/pages/xxgl/lmglPre.jsp"),
			@Result(name = "lmgl_detail", value = "/WEB-INF/pages/xxgl/lmgl_detail.jsp"),
			@Result(name = "xxglzj", value = "/WEB-INF/pages/xxgl/xxglzj.jsp")
		})
public class XxglAction extends BaseAction{

	@Autowired
	@Qualifier("xxglService")
	private XxglService xxglService;
	
	/**信息撰稿
	 * xufeng
	 * 2009.09.26
	 */
	private static final long serialVersionUID = 7792435474087396405L;
	
	private Xtuser xtuser;
	
	private File[] uploads;

    private String[] uploadFileNames;

    private String[] uploadContentTypes;

    private String[] dir;

    private String[] targetFileName;
    
    private Map<String,String> parMap;
    
    private Map<String,String> query;
    
    private Map mapOut;
    
    private List<Map<String,String>> lmlxList;
    
    private Info_zg info_zg;
	
	private List<Map<String,String>> outList;
	
	public void onPrepare(){
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	} 
	

	public String execute() {
		
	    return "index";
	}
	
	/*信息撰稿编辑
	 * 
	 */
	public String doXxzg(){
		outList = xxglService.getLmList();
		return "xxzgpre";
	}
	
	
	/*信息撰稿编辑修改
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String doXxzgModify(){
		outList = xxglService.getLmList();
		mapOut = new HashMap();
		parMap = xxglService.queryXxzg(query.get("xxid").toString());
		if(!parMap.get("fj").equals("0")){
			
			xxglService.queryFj(query.get("xxid").toString());
			mapOut.put("fjList", xxglService.queryFj(query.get("xxid").toString()));
		}
		
			mapOut.put("lmList", xxglService.queryLmzg(query.get("xxid").toString()));
		
		return "xxzgpre";
	}
	
	
	/*信息栏目管理
	 * 
	 */
	public String doLmglPre(){
		
		return "lmglpre";
	}
	
	@SuppressWarnings("unchecked")
	public void doQueryLminfo(){
		Map m = new HashMap();
    	m.put("lmglList", xxglService.queryLmgl());
		renderText(JSONObject.fromMap(m).toString());
	}
	
	@SuppressWarnings("unchecked")
	public String doLmglDetail(){
		return "lmgl_detail";
	}
	
	@SuppressWarnings("unchecked")
	public void doLoadLmdetail(){
		Map m = new HashMap();
		String lmid = "";
		
    	try {
    		if(parMap.get("lmid")!=null && !parMap.get("lmid").equals("")){
    		    lmid = parMap.get("lmid").toString();
    			m.put("lmmc", xxglService.queryLmmc(lmid).get("lmmc").toString());
    		}
    		m.put("userList", xxglService.queryLmqx(lmid));
    		m.put("code", "1");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "查询失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
	}
	
	@SuppressWarnings("unchecked")
	public void doCheckLmmc(){
		Map m = new HashMap();	
    	try {		
    		m.put("lmmcCode", xxglService.checkLmmc(parMap.get("lmmc").toString()));
    		m.put("code", "1");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "查询失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
	}

	@SuppressWarnings("unchecked")
	public void doSaveLmgl(){
    	Map m = new HashMap();
    	String lmid = "";
    	try {
    		if(parMap.get("lmid")!=null && !parMap.get("lmid").equals("")){
    		    lmid = parMap.get("lmid").toString();
    		}
    		xxglService.saveLmgl(parMap.get("userList").toString(), lmid, parMap.get("lmmc").toString());
    		m.put("code", "1");
    		m.put("text", "栏目信息保存成功�?");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
	
	@SuppressWarnings("unchecked")
	public void doDelLmgl(){
    	Map m = new HashMap();
    	String lmid = "";
    	try {
    		if(parMap.get("lmid")!=null && !parMap.get("lmid").equals("")){
    		    lmid = parMap.get("lmid").toString();
    		}
    		xxglService.deleteLmgl( lmid);
    		m.put("code", "1");
    		m.put("text", "栏目信息删除成功�?");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "删除失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
	
	@SuppressWarnings("unchecked")
	public void doDelXxzg(){
    	Map m = new HashMap();
    	
    	try {
    		
    		xxglService.deleteXxzg(parMap.get("xxid").toString());
    		m.put("code", "1");
    		m.put("text", "撰稿信息删除成功�?");
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "删除失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
    //文件上传

    public String doUpload() throws Exception {
        // 获得upload路径的实际目�?
        String realPath = ServletActionContext.getRequest().getRealPath("/upload");
        //获得实际目录
        String targetDirectory = realPath;
        String[] mydir = new String[uploads.length];
        String[] tname = new String[uploads.length];
        for (int i = 0; i < uploads.length; i++) {
            // 生成保存文件的文件名�?
            tname[i] = generateFileName(uploadFileNames[i]);
            // 保存文件的路�?
            mydir[i] = targetDirectory + "\\" + tname[i];
            // 建立�?个目标文�?
            File target = new File(targetDirectory, tname[i]);
            // 将临时文件复制到目标文件
            FileUtils.copyFile(uploads[i], target);
        }
        setDir(mydir);
        setTargetFileName(tname);
        return "uploadok";

    }

    @SuppressWarnings("unchecked")
	public void doSaveXxzg(){
    	Map m = new HashMap();
    	String realPath = ServletActionContext.getRequest().getRealPath("/upload");
    	try {
    		xxglService.saveXxzg(parMap.get("lxList").toString(),parMap.get("fjList").toString() , info_zg,xtuser.getUserid(),realPath);
    		m.put("code", "1");
			
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }

	/*信息撰稿编辑
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String doZgSp(){
		//outList = xxglService.getLmList();
		lmlxList = new ArrayList();
		lmlxList = xxglService.queryAllLm();
		return "zgsp";
	}
	
	/*信息撰稿编辑
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String doXxgl(){
		//outList = xxglService.getLmList();
		lmlxList = new ArrayList();
		lmlxList = xxglService.queryAllLm();
		return "xxglpre";
	}
	
	@SuppressWarnings("unchecked")
	public void doShowXxglList(){
    	Map mapOut = new HashMap();
		mapOut.put("xxglList",xxglService.queryXxglList(getLimitPage(),parMap.get("sql").toString()));
		renderText(JSONObject.fromMap(mapOut).toString());
    
    }
	
    @SuppressWarnings("unchecked")
	public void doShowZgList(){
    	Map mapOut = new HashMap();
		mapOut.put("xxglList",xxglService.queryZgList(getLimitPage(),parMap.get("sql").toString()));
		renderText(JSONObject.fromMap(mapOut).toString());
    
    }
    
    @SuppressWarnings("unchecked")
	public void doSaveZgsp(){
    	Map m = new HashMap();
    	try {
    		xxglService.saveZgsp(parMap.get("spList").toString(),xtuser.getUserid());
    		m.put("code", "1");
			
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因�?"+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
    @SuppressWarnings("unchecked")
	public void doSaveZght(){
    	Map m = new HashMap();
    	try {
    		xxglService.saveZght(parMap.get("spList").toString(),xtuser.getUserid());
    		m.put("code", "1");
			
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", "保存失败，导致的原因："+e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
    @SuppressWarnings("unchecked")
	public String doShowXxzg(){
    	parMap = xxglService.queryXxzg(parMap.get("xxid").toString());
    	return "showXxzg";
    }
    
    @SuppressWarnings("unchecked")
	public void doShowFj(){
    	Map m = new HashMap();
    	m.put("fjList", xxglService.queryFj(parMap.get("xxid").toString()));
		renderText(JSONObject.fromMap(m).toString());
    }
    
    @SuppressWarnings("unchecked")
	public void doCheckXxglOpt(){
    	Map m = new HashMap();
    	try {
    		if(parMap.get("opt").toString().equals("modify")){
    			xxglService.checkXxglUpdateOpt(parMap.get("xxid").toString(), xtuser.getUserid(), "",parMap.get("lmid").toString());
    		}else{
    			xxglService.checkXxglDeleteOpt(parMap.get("xxid").toString(), xtuser.getUserid(), "",parMap.get("lmid").toString());
    		}
    		m.put("code", "1");
			
		} catch (Exception e) {
			m.put("code", "-1");
			m.put("text", e.getMessage());
		}
		renderText(JSONObject.fromMap(m).toString());
    }
    
    
    //首页内容
    @SuppressWarnings("unchecked")
	public void doQueryIndex(){
    	Map mapOut = new HashMap();
    	List<Map<String, String>> l = xxglService.getInfoLmList();
    	for(int i=0;i<l.size();i++){
        	mapOut.put("xx_"+(i+1),xxglService.getIndexListByLx(String.valueOf(l.get(i).get("lmid"))));
    	}
		renderText(JSONObject.fromMap(mapOut).toString());
    
    }
    //属�?�的getter和setter方法
    

	public String xxglzj(){
		return "xxglzj";
	}
    
    public String[] getDir() {
        return dir;
    }

    public void setDir(String[] dir) {
        this.dir = dir;
    }

    public String[] getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String[] targetFileName) {
        this.targetFileName = targetFileName;
}

  //属�?�的getter和setter方法

    public File[] getUpload() {
        return this.uploads;
    }

    public void setUpload(File[] upload) {
        this.uploads = upload;
    }

    public String[] getUploadFileName() {
        return this.uploadFileNames;
    }

    public void setUploadFileName(String[] uploadFileName) {
        this.uploadFileNames = uploadFileName;
    }

    public String[] getUploadContentType() {
        return this.uploadContentTypes;
    }

    public void setUploadContentType(String[] uploadContentType) {
        this.uploadContentTypes = uploadContentType;
    }

	public List<Map<String, String>> getOutList() {
		return outList;
	}

	public void setOutList(List<Map<String, String>> outList) {
		this.outList = outList;
	}

	public Map<String, String> getParMap() {
		return parMap;
	}

	public void setParMap(Map<String, String> parMap) {
		this.parMap = parMap;
	}

	public Info_zg getInfo_zg() {
		return info_zg;
	}

	public void setInfo_zg(Info_zg info_zg) {
		this.info_zg = info_zg;
	}

	public Xtuser getXtuser() {
		return xtuser;
	}

	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}


	public List<Map<String, String>> getLmlxList() {
		return lmlxList;
	}


	public void setLmlxList(List<Map<String, String>> lmlxList) {
		this.lmlxList = lmlxList;
	}


	public Map getMapOut() {
		return mapOut;
	}


	public void setMapOut(Map mapOut) {
		this.mapOut = mapOut;
	}


	public Map<String, String> getQuery() {
		return query;
	}


	public void setQuery(Map<String, String> query) {
		this.query = query;
	}
	
}

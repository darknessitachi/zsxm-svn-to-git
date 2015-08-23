package com.netwander.explib.web.action;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.config.ParentPackage;
import org.apache.struts2.config.Result;
import org.apache.struts2.config.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.netwander.core.Constants;
import com.netwander.core.common.Message;
import com.netwander.explib.entity.Xtuser;
import com.netwander.explib.exception.BusException;
import com.netwander.explib.service.FileService;
import com.netwander.explib.web.common.BaseAction;
import com.opensymphony.xwork2.Action;

@ParentPackage("appDefault")
@Scope("prototype")
@Controller
@Results( { 
	@Result(name = "input", value = "/WEB-INF/pages/com/fileUploadDone.jsp")
})
public class FileAction extends BaseAction {
	@Autowired
	@Qualifier("fileService")
	private FileService fileService;
	
	private File[] upload;
	private String[] uploadContentType; // 文件的内容类型
    private String[] uploadFileName; // 上传文件名
    private String fileCaption;// 上传文件时的备注
	


    
    private String maxSize; //上传大小限制
    private String allowTypes; //上传类型 
    private Integer fid;
    
    private Map<String,Object> reMap;
    
	private Xtuser xtuser;
	public void onPrepare() {
		xtuser = (Xtuser)getSession().getAttribute(Constants.USER_SESSION_KEY);
	}
	
	
	public String uploadfile(){
		try{
			reMap = new HashMap();
			if(upload != null){   
				if(upload.length > 0){
					
					if(allowTypes != null && !allowTypes.equals("")){
						for(int i=0;i<uploadContentType.length;i++){
							if( allowTypes.indexOf(uploadContentType[i]) < 0){
								reMap.put("code", "-1");
								reMap.put("fid","");
								reMap.put("text", uploadFileName[i]+"附件格式不允许上传");
								return "input";
							}
						}
					}
					
					if(maxSize != null && !maxSize.equals("")){
						for(int i=0;i< upload.length;i++){
							if(upload[i].length()<=0){
								throw new BusException("有相应附件为空不能上传！");
							}
							if(upload[i].length() > Long.parseLong(maxSize)){
								reMap.put("code", "-1");
								reMap.put("fid","");
								reMap.put("text", uploadFileName[i]+" 附件超过 "+(Long.parseLong(maxSize)/1024)+"K, 不允许上传");
								return "input";
							}
						}
					}
					
					fid = fileService.fileUpload(fid, upload, uploadFileName, uploadContentType);
					reMap.put("code", "1");
					reMap.put("fid",fid);
					reMap.put("text", "上传成功!");
				}else{
					reMap.put("code", "-1");
					reMap.put("fid","");
					reMap.put("text", "附件不能为空");
				}
			}else{
				reMap.put("code", "-1");
				reMap.put("fid","");
				reMap.put("text", "附件不能为空");
				return "input";
			}  
		}catch(Exception e){
			reMap.put("code", "-1");
			reMap.put("fid","");
			reMap.put("text", ""+e.getMessage());
			return "input";
		}
		return "input";
	}
	
	public void download(){
		OutputStream os = null;
		try {
			String fname = fileService.getFileName(fid);
			os = getResponse().getOutputStream();// 得到输出流
			getResponse().reset();
			getResponse().setContentType("application/octet-stream");
			getResponse().setHeader("Content-Disposition", "attachment; filename=" + new String(fname.getBytes("GBK"), "ISO8859-1"));
			fileService.downloadFile(fid, os);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (os != null) {
					os.flush();
					os.close();
				}
			} catch (Exception e) {
			}
		}
	}
	
	public void delete(){
		try{
			fileService.doDeleteFile(fid);
			message = new Message("1","删除成功");
		}catch(Exception e){
			message = new Message("-1",e.getMessage());
		}
		renderJson(message);
	}
	/***
	 * Mapping
	 * @return
	 */
	
	public Map<String, Object> getReMap() {
		return reMap;
	}
	public void setReMap(Map<String, Object> reMap) {
		this.reMap = reMap;
	}

	public Integer getFid() {
		return fid;
	}


	public void setFid(Integer fid) {
		this.fid = fid;
	}



	public String getMaxSize() {
		return maxSize;
	}


	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}


	public String getAllowTypes() {
		return allowTypes;
	}


	public void setAllowTypes(String allowTypes) {
		this.allowTypes = allowTypes;
	}


	public File[] getUpload() {
		return upload;
	}


	public void setUpload(File[] upload) {
		this.upload = upload;
	}


	public String[] getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String[] getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String getFileCaption() {
		return fileCaption;
	}


	public void setFileCaption(String fileCaption) {
		this.fileCaption = fileCaption;
	}
	
	
}

package com.group.zsxm.web.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.group.core.Constants;

import com.group.core.common.FileEntry;
import com.group.core.common.LimitPage;
import com.group.core.common.Message;
import com.group.zsxm.entity.DmMc;

import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


@SuppressWarnings( { "unchecked", "unused" })
public class BaseAction extends ActionSupport implements Preparable,
		SessionAware, ServletResponseAware, ServletRequestAware {
	private static final long serialVersionUID = 1L;

	//public static final String UPLOAD_PATH = Constants.UPLOAD_PATH;

	private static final String ENCODING_PREFIX = "encoding:";

	private static final String NOCACHE_PREFIX = "no-cache:";

	private static final String ENCODING_DEFAULT = "UTF-8";

	private static final boolean NOCACHE_DEFAULT = true;

	protected Log logger = LogFactory.getLog(getClass());

	private static final int BUFFER_SIZE = 16 * 1024;

	private File[] upload;

	private String[] uploadContentType;

	private String[] uploadFileName;

	private FileEntry[] fileEntrties;

	private LimitPage limitpage;
	
	protected Message message;
	
	protected List<Map<String, Object>> gheades;
	protected List<Map<String, Object>> gcols;
	
	protected void onPrepare() {
	}

	public void prepare() throws Exception {
		limitpage = new LimitPage(getRequest());
		onPrepare();
	}

	public static HttpSession getSession() {
		return ServletActionContext.getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}

	public LimitPage getLimitPage() {
		if(this.limitpage == null){
			this.limitpage = new LimitPage(getRequest());
		}
		return this.limitpage;
	}
/**
	protected void setUploadFile() {
		if (upload != null && upload.length > 0) {
			fileEntrties = new FileEntry[upload.length];

			for (int i = 0; i < upload.length; i++) {
				String filename = generateFileName(uploadFileName[i]);
				String filepath = ServletActionContext.getServletContext().getRealPath(UPLOAD_PATH) + File.separator + filename;
				File file = new File(filepath);
				copy(this.upload[i], file);

				fileEntrties[i] = new FileEntry(filename, filepath, this.uploadContentType[i], this.uploadFileName[i]);
			}
		}
	}
*/
	
	public Map getParameterSimpleMap() {
		Iterator kit= getRequest().getParameterMap().keySet().iterator();
		Map param=new HashMap();
		while(kit.hasNext()){
			String key=String.valueOf(kit.next());
			String[] v=null;
			try {
				v=(String[]) getRequest().getParameterMap().get(key);
			} catch (Exception e) {
				continue;
			}
			if (v!=null && v.length==1){
				param.put(key,v[0]);
			}else{
				param.put(key,v);
			}
		}
		return param;
	}
	
	public static List<DmMc> ArrayToList(String[] dm, String[] mc) {
		List<DmMc> l = new ArrayList();
		for (int i = 0; i < dm.length; i++) {
			DmMc d = new DmMc();
			d.setDm(dm[i]);
			d.setMc(mc[i]);
			l.add(d);
		}
		return l;
	}
	
	protected String generateFileName(String fileName) {
		DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		String formatDate = format.format(new Date());
		int random = new Random().nextInt(10000);
		int position = fileName.lastIndexOf(".");
		String extension = fileName.substring(position);
		return formatDate + random + extension;
	}

	protected static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void render(final String contentType, final String content, final String... headers) {
		try {
			// 分析headers参数
			String encoding = ENCODING_DEFAULT;
			boolean noCache = NOCACHE_DEFAULT;
			for (String header : headers) {
				String headerName = StringUtils.substringBefore(header, ":");
				String headerValue = StringUtils.substringAfter(header, ":");

				if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
					encoding = headerValue;
				} else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
					noCache = Boolean.parseBoolean(headerValue);
				} else
					throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
			}

			HttpServletResponse response = ServletActionContext.getResponse();

			String fullContentType = contentType + ";charset=" + encoding;
			response.setContentType(fullContentType);
			if (noCache) {
				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
			}

			response.getWriter().write(content);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void renderText(final String text, final String... headers) {
		render("text/plain", text, headers);
	}

	public static void renderHtml(final String html, final String... headers) {
		render("text/html", html, headers);
	}

	public static void renderXml(final String xml, final String... headers) {
		render("text/xml", xml, headers);
	}

	public static void renderJson(final String string, final String... headers) {
		render("application/json", string, headers);
	}

	public static void renderJson(final Object object, final String... headers) {
		try {
			renderJson(JSONUtil.serialize(object), headers);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	/*********************************************
	 *    数据\日期格式化的处理
	 ********************************************/
	///////////////////////////////////////
	//	如果传入1234   输出 1,234.00,  主要用于格式化显示数据或报表
	/////////////////////////////////////
	public static String FormatNumber(String value){
		if(value == null || value.equals("")){value = "0";}
		DecimalFormat df1 = new DecimalFormat("###,##0.00");
		return df1.format(Double.parseDouble(value));
	}
	///////////////////////////////////////
	//	如果传入1234   输出 1234.00,  主要用于格式化输入文本框
	/////////////////////////////////////
	public static String FormatNumber_Input(String value){
		if(value == null || value.equals("") ){value = "0";}
		DecimalFormat df1 = new DecimalFormat("#####0.00");
		return df1.format(Double.parseDouble(value));
	}
	///////////////////////////////////////
	//	如果传入1234.03   输出 1234, 1234.67  输出 1235  主要用于格式化输入文本框
	/////////////////////////////////////
	public static String FormatNumber_Int(String value){
		if(value == null || value.equals("") ){value = "0";}
		DecimalFormat df1 = new DecimalFormat("#####");
		return df1.format(Double.parseDouble(value));
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
/*
	public FileEntry[] getFileEntrties() {
		if (fileEntrties == null) {
			setUploadFile();
		}
		return fileEntrties;
	}
*/
	
	public void setSession(Map arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Map<String, Object>> getGheades() {
		return gheades;
	}

	public void setGheades(List<Map<String, Object>> gheades) {
		this.gheades = gheades;
	}

	public List<Map<String, Object>> getGcols() {
		return gcols;
	}

	public void setGcols(List<Map<String, Object>> gcols) {
		this.gcols = gcols;
	}}
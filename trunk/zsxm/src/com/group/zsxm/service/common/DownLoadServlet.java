package com.group.zsxm.service.common;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.group.zsxm.service.MessageService;
import com.group.zsxm.service.XxglService;

@SuppressWarnings("serial")
public class DownLoadServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "APPLICATION/OCTET-STREAM;charset=UTF-8";

	private static final String DOWNLOAD_FLODER = "upload/";


	public DownLoadServlet() {
		super();
	}

	public void destroy() {
		super.destroy();
	}
	
	public String getFileName(String xxid,String fjid,String type){
		if(type != null && type.equals("xx")){
			MessageService messageService = (MessageService) WebApplicationContextUtils.getWebApplicationContext((getServletContext())).getBean(
			"messageService");
			return messageService.getFjName(xxid, fjid);
		}else{
			XxglService xxglService = (XxglService) WebApplicationContextUtils.getWebApplicationContext((getServletContext())).getBean(
			"xxglService");
			return xxglService.getFjName(xxid, fjid);
		}
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fileName = request.getParameter("filename");
		String xxid = request.getParameter("xxid");
		String fjid = request.getParameter("fjid");
		String type = request.getParameter("type");
		fileName = java.net.URLDecoder.decode(fileName,"UTF-8");
		if (StringUtils.isBlank(fileName)) {
			throw new ServletException("请先指定需要下载的文件名");
		}
		String fullPath = "";
			fullPath = DOWNLOAD_FLODER + fileName;
		ServletOutputStream out = null;
		FileInputStream in = null;
		File file = null;
		try {
			response.reset();
			response.setCharacterEncoding("UTF-8");
			String contextPath = request.getSession().getServletContext()
					.getRealPath("/");
			fullPath = contextPath + fullPath;
			file = new File(fullPath);
			if (!file.exists()) {
				throw new ServletException("找不到指定下载的文件");
			}
			String okFileName = getFileName(xxid,fjid,type);
			in = new FileInputStream(file);
			out = response.getOutputStream();
			response.setContentType(CONTENT_TYPE);
			response.setHeader("Content-Disposition", "attachment; filename="
					+ java.net.URLEncoder.encode(okFileName, "UTF-8"));
			byte[] b = new byte[1024];
			int len;
			while ((len = in.read(b)) > 0) {
				out.write(b, 0, len);
			}

		} catch (ServletException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e1) {
			System.out.println("ERROR :" + e1.getMessage());
		} finally {
			try {
				if (out != null) {
					try {
						out.close();
						out = null;
					} catch (Exception e2) {
						out = null;
					}
				}
				if (in != null) {
					try {
						in.close();
						in = null;
					} catch (Exception e2) {
						in = null;
					}
				}
				if (file != null) {
					file = null;
				}
			} catch (Exception ex) {
				System.out.println("DownLoad File Error:" + ex);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	public void init() throws ServletException {
	}
}

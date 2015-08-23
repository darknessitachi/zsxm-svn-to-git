package com.netwander.core.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.netwander.core.Constants;

public class LimitPage  {

    protected int currentRowsDisplayed;

	protected int page;

	protected int totalRows;
	
	public LimitPage(){}
	public LimitPage(HttpServletRequest request) {
		String p_start = StringUtils.defaultIfEmpty(request.getParameter(Constants.PAGE_START), "1");
		String p_length = StringUtils.defaultIfEmpty(request.getParameter(Constants.PAGE_SIZE), String.valueOf(Constants.DEFAULT_PAGE_SIZE));
		String p_count = StringUtils.defaultIfEmpty(request.getParameter(Constants.PAGE_COUNT), "0");

		this.page = Integer.parseInt(p_start);
		this.currentRowsDisplayed = Integer.parseInt(p_length);
		this.totalRows = Integer.parseInt(p_count);
	}

	public int getCurrentRowsDisplayed() {
		return currentRowsDisplayed;
	}

	public int getPage() {
		return page;
	}
	

	public int getTotalRows() {
		return totalRows;
	}

	public void setRowAttributes(int rowcount, int pageno,int pagesize) {
		this.totalRows = rowcount;
		this.page = pageno;
		this.currentRowsDisplayed = pagesize;
	}
}

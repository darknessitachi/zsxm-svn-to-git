package com.netwander.core.common;

import java.util.ArrayList;

public class Page implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private static final int DEFAULT_PAGE_SIZE = 50;
	/**
	 * 当前页第一条数据的位置,从0开始
	 */
	private int start = 0;

	/**
	 * 每页的记录数
	 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/**
	 * 当前页码
	 */
	private int pageNo = 1;

	/**
	 * 当前页中存放的记录
	 */
	private Object data;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 构造方法，只构造空页
	 */

	
	public Page() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public Page(int start, int totalSize, int pageSize, Object data) {
		this.start = start;
		this.totalCount = totalSize;
		this.pageSize = pageSize;
		this.pageNo = getCurrentPageNo();
		this.data = data;
		this.validator();
		
	}

	/**
	 * 验效分页数据正确性
	 */
	private void validator() {
		if (this.start < 0) {
			this.start = 0;
		}
		if (this.totalCount < 0) {
			this.totalCount = 0;
		}
		if (this.pageSize <= 0) {
			this.pageSize = DEFAULT_PAGE_SIZE;
		}
		if (this.pageNo <= 0) {
			this.pageNo = 1;
		}
		this.pageNo = getCurrentPageNo();
	}

	/**
	 * 取数据库中包含的总记录数
	 */
	public int getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数
	 */
	public int getTotalPageCount() {
		if (totalCount % pageSize == 0)
			return totalCount / pageSize;
		else
			return totalCount / pageSize + 1;
	}

	/**
	 * 取每页数据容量
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 当前页中的记录
	 */
	public Object getResult() {
		return data;
	}

	public void setResult(Object data) {
		this.data = data;
	}

	/**
	 * 取当前页码,页码从1开始
	 */
	public int getCurrentPageNo() {
		if (pageSize == 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		return (start / pageSize) + 1;
	}

	/**
	 * 是否有下一页
	 */
	public boolean getHasNextPage() {
		return (this.getCurrentPageNo() < this.getTotalPageCount());
	}

	/**
	 * 是否有上一页
	 */
	public boolean getHasPreviousPage() {
		return (this.getCurrentPageNo() > 1);
	}

	/**
	 * 获取任一页第一条数据的位置，每页条数使用默认值
	 */
	public static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据的位置,startIndex从0开始
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		if (pageSize <= 0) {
			pageSize = DEFAULT_PAGE_SIZE;
		}
		if (pageNo <= 0) {
			pageNo = 1;
		}
		return (pageNo - 1) * pageSize;
	}

	public Object getData() {
		return data;
	}
}

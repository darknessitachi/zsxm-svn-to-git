package com.group.zsxm.service.common;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.support.nativejdbc.NativeJdbcExtractor;
import org.springframework.util.Assert;

import com.group.core.common.LimitPage;
import com.group.core.common.Page;

public abstract class SimpleJdbcSupport {

	private DataSource dataSource = null;

	protected final Log logger = LogFactory.getLog(getClass());

	protected SimpleJdbcTemplate simpleJdbcTemplate;

	protected JdbcTemplate jdbcTemplate;

	@Autowired
	public void init(DataSource dataSource) {
		simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		 jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.setNativeJdbcExtractor(nativeJdbcExtractor);
	}


	public SimpleJdbcTemplate getSimpleJdbcTemplate() {
		if (this.simpleJdbcTemplate == null) {
			this.simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
		}
		return this.simpleJdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		if (this.jdbcTemplate == null) {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

	/**
	 * 
	 */
	public BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}

	/**
	 * 
	 */
	public <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(
			Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}


	protected Page queryForListWithPage(String sql, int pageNo, int pageSize,
			Object... paramArgs) {
		Assert.hasText(sql);

		int totalCount = this.getJdbcTemplate().queryForInt(
				getTotalString(sql), paramArgs);

		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

	//	paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
	//			pageSize + startIndex, startIndex });

		List resultList = this.getJdbcTemplate().queryForList(
				this.getLimitString(sql,startIndex,pageSize), paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql, int pageNo, int pageSize,
			int totalCount, Object... paramArgs) {
		Assert.hasText(sql);
		if (totalCount <= 0) {
			totalCount = this.getJdbcTemplate().queryForInt(
					getTotalString(sql), paramArgs);
		}
		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

	//	paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
	//			pageSize + startIndex, startIndex });

		List resultList = this.getJdbcTemplate().queryForList(
				this.getLimitString(sql,startIndex,pageSize), paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql, LimitPage limit,
			Object... paramArgs) {
		return queryForListWithPage(sql, limit.getPage(), limit
				.getCurrentRowsDisplayed(), limit.getTotalRows(), paramArgs);
	}

	protected Page queryForListWithPage(String sql,
			ParameterizedBeanPropertyRowMapper beanPropertyRowMapper,
			int pageNo, int pageSize, int totalCount, Object... paramArgs) {
		Assert.hasText(sql);
		if (totalCount <= 0) {
			totalCount = this.getSimpleJdbcTemplate().queryForInt(
					getTotalString(sql), paramArgs);
		}
		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

	//	paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
	//			pageSize + startIndex, startIndex });

		List resultList = this.getSimpleJdbcTemplate().query(
				this.getLimitString(sql,startIndex,pageSize), beanPropertyRowMapper, paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql,
			ParameterizedBeanPropertyRowMapper beanPropertyRowMapper,
			LimitPage limit, Object... paramArgs) {
		return queryForListWithPage(sql, beanPropertyRowMapper,
				limit.getPage(), limit.getCurrentRowsDisplayed(), limit
						.getTotalRows(), paramArgs);
	}

	protected List queryForList(String sql, Object... args) {
		return this.getJdbcTemplate().queryForList(sql, args);
	}

	private String getTotalString(String sql) {
		StringBuffer pageCountSb = new StringBuffer(100);
		pageCountSb.append("select count(*) from (");
		pageCountSb.append(sql);
		pageCountSb.append(")");
		return pageCountSb.toString();
	}

	private static int getLastIndexOfOrderBy(String sql) {
		return sql.toLowerCase().lastIndexOf("order by ");
	}

	public String getLimitString(String querySelect, int offset, int limit) {
		int lastIndexOfOrderBy = getLastIndexOfOrderBy(querySelect);
		// 　没有 order by 或第一页的情况下
		if (lastIndexOfOrderBy < 0 || querySelect.endsWith(")") || offset == 0)
			return getLimitString(querySelect, 0, limit);
		else {
			// 取出 order by 语句
			String orderby = querySelect.substring(lastIndexOfOrderBy,
					querySelect.length());
			// 取出 from 前的内容
			int indexOfFrom = querySelect.toLowerCase().indexOf("from");
			String selectFld = querySelect.substring(0, indexOfFrom);
			// 取出 from 语句后的内容
			String selectFromTableAndWhere = querySelect.substring(indexOfFrom,
					lastIndexOfOrderBy);
			StringBuffer sql = new StringBuffer(querySelect.length() + 100);
			sql.append("select TOP "+limit+" from (").append(selectFld).append(
					",ROW_NUMBER() OVER(").append(orderby).append(
					") as _page_row_num_hb ").append(selectFromTableAndWhere)
					.append(" ) temp ").append(
							" where _page_row_num_hb > "+ limit*(offset -1));
			return sql.toString();
		}
	}
}

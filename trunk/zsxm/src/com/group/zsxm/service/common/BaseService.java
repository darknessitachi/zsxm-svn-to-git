package com.group.zsxm.service.common;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.util.Assert;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.group.core.common.LimitPage;
import com.group.core.common.Page;

public class BaseService extends SimpleJdbcSupport {

	public static Map<String, String> logicMap = new HashMap<String, String>();

	static {
		logicMap.put("equal", "=");
		logicMap.put("notEqual", "!=");
		logicMap.put("less", "<");
		logicMap.put("great", ">");
		logicMap.put("lessEqual", "<=");
		logicMap.put("greatEqual", ">=");
		logicMap.put("like", "like");
		logicMap.put("startWith", ">=");
		logicMap.put("endWith", "<=");
	}
	
	//返回一个日期字符串在星期中的顺序
	//0 代表星期天 1---6 代表 星期一 至 星期六
	public static int getDateInWeek(String strDate) {
	  DateFormat df = DateFormat.getDateInstance();
	  try {
	    df.parse(strDate);
	    java.util.Calendar c = df.getCalendar();
	    int day = c.get(c.DAY_OF_WEEK) - c.SUNDAY;
	    return day;
	  }
	  catch (ParseException e) {
	    return -1;
	  }
	}
	/**
	 * 获取一个月的总天数
	 * @param strDate
	 * @return
	 */
	public static int getActualMaximumByDay(String strDate){
		DateFormat df = DateFormat.getDateInstance();
		try{
			df.parse(strDate);
		    java.util.Calendar c = df.getCalendar();
		    return c.getActualMaximum(c.DAY_OF_MONTH);
		}catch (ParseException e) {
		    return -1;
		}
	}
	
	public String getGridSortString(SortInfo sortInfo) {
		String sortCond = null;
		if (sortInfo != null) {
			String fieldName = sortInfo.getFieldName();
			String sortOrder = sortInfo.getSortOrder();
			if (!sortOrder.equalsIgnoreCase("defaultsort")) {
				sortCond = " " + fieldName + " " + sortOrder + " ";
			}
		}
		return sortCond;
	}

	public String getGridFilterString(List<FilterInfo> filters) {
		String filterCond = null;
		if (filters != null) {
			for (FilterInfo filterInfo : filters) {
				String opttype = logicMap.get(filterInfo.getLogic());
				String value = filterInfo.getValue();
				if (opttype.equalsIgnoreCase("like")) {
					value = "%" + value + "%";
				}
				if (filterCond == null)
					filterCond = "";
				filterCond += " and " + filterInfo.getFieldName() + " " + opttype + " '" + value+"' ";
			}
		}
		return filterCond;
	}

	/**
	 * 对存储过程调用进行了简单封装
	 */
	protected Map<String, Object> callProcedure(String procName, Map<String, Object> params) {
		if (StringUtils.isNotBlank(procName)) {
			this.getSimpleJdbcTemplate().queryForObject(procName, Map.class, params);
		}
		return params;
	}
	

	/**
	 * 取最大值
	 */
	protected Object getMaxKey(final String tablename) {
		return getMaxKey(tablename, null);
	}

	protected Object getMaxKey(final String tablename, final Integer step) {
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("Table", tablename.toUpperCase());
		map.put("iTal", (step == null) ? new Integer(1) : step);

		return getMaxKey(map);
	}

	protected Object getMaxKey(final Map<String, Object> params) {
		return getJdbcTemplate().execute("{call get_maxid(?,?,?)}", new CallableStatementCallback() {
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setString(1, params.get("Table").toString());
				cs.setInt(2, Integer.parseInt(params.get("iTal").toString()));
				cs.registerOutParameter(3, Types.INTEGER);
				cs.execute();
				return cs.getInt(3);
			}
		});
	}

	public static String ArrayToString(String[] str) {
		String reS = "";
		if (str != null && str.length > 0) {
			for (int i = 0; i < str.length; i++) {
				if (i == 0) {
					reS = "'" + str[i] + "'";
				} else {
					reS += ",'" + str[i] + "'";
				}
			}
		}
		return reS;
	}

	protected Page queryForListWithPage(String sql, int pageNo, int pageSize, Object... paramArgs) {
		Assert.hasText(sql);

		int totalCount = this.getJdbcTemplate().queryForInt(getTotalString(sql), paramArgs);

		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		// paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
		// pageSize + startIndex, startIndex });

		List resultList = this.getJdbcTemplate().queryForList(this.getLimitString(sql, startIndex, pageSize), paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql, int pageNo, int pageSize, int totalCount, Object... paramArgs) {
		Assert.hasText(sql);
		if (totalCount <= 0) {
			totalCount = this.getJdbcTemplate().queryForInt(getTotalString(sql), paramArgs);
		}
		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		// paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
		// pageSize + startIndex, startIndex });


		List resultList = this.getJdbcTemplate().queryForList(this.getLimitString(sql, pageNo, pageSize), paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql, LimitPage limit, Object... paramArgs) {
		return queryForListWithPage(sql, limit.getPage(), limit.getCurrentRowsDisplayed(), limit.getTotalRows(), paramArgs);
	}

	protected Page queryForListWithPage(String sql, ParameterizedBeanPropertyRowMapper beanPropertyRowMapper, int pageNo, int pageSize, int totalCount, Object... paramArgs) {
		Assert.hasText(sql);
		if (totalCount <= 0) {
			totalCount = this.getSimpleJdbcTemplate().queryForInt(getTotalString(sql), paramArgs);
		}
		if (totalCount < 1) {
			return new Page();
		}

		int startIndex = Page.getStartOfPage(pageNo, pageSize);

		// paramArgs = ArrayUtils.addAll(paramArgs, new Object[] {
		// pageSize + startIndex, startIndex });

		List resultList = this.getSimpleJdbcTemplate().query(this.getLimitString(sql, pageNo, pageSize), beanPropertyRowMapper, paramArgs);

		return new Page(startIndex, totalCount, pageSize, resultList);
	}

	protected Page queryForListWithPage(String sql, ParameterizedBeanPropertyRowMapper beanPropertyRowMapper, LimitPage limit, Object... paramArgs) {
		return queryForListWithPage(sql, beanPropertyRowMapper, limit.getPage(), limit.getCurrentRowsDisplayed(), limit.getTotalRows(), paramArgs);
	}

	protected List queryForList(String sql, Object... args) {
		return this.getJdbcTemplate().queryForList(sql, args);
	}

	private String getTotalString(String sql) {
		int lastIndexOfOrderBy = getLastIndexOfOrderBy(sql);
		// 取出 order by 语句
		String orderby = sql.substring(lastIndexOfOrderBy, sql.length());

		String tep_sql = sql.substring(0, lastIndexOfOrderBy);
		int indexOfFrom = tep_sql.toLowerCase().indexOf(" from ");
		String selectFld = tep_sql.substring(0, indexOfFrom);

		String selectFromTableAndWhere = tep_sql.substring(indexOfFrom + 6, tep_sql.length());

		StringBuffer pageCountSb = new StringBuffer(100);
		pageCountSb.append("select count(*) from ");
		pageCountSb.append(selectFromTableAndWhere);
		pageCountSb.append("");
		return pageCountSb.toString();
	}

	private static int getLastIndexOfOrderBy(String sql) {
		return sql.toLowerCase().lastIndexOf(" order by ");
	}

	public String getLimitString(String querySelect, int offset, int limit) {
		int lastIndexOfOrderBy = getLastIndexOfOrderBy(querySelect);
		String orderby = querySelect.substring(lastIndexOfOrderBy, querySelect.length());
		// 取出 from 前的内容
		int indexOfFrom = querySelect.toLowerCase().indexOf(" from ");
		String selectFld = querySelect.substring(0, indexOfFrom);
		// 取出 from 语句后的内容
		String selectFromTableAndWhere = querySelect.substring(indexOfFrom, lastIndexOfOrderBy);
		StringBuffer sql = new StringBuffer(querySelect.length() + 100);
		sql.append("select TOP " + limit + " * from (").append(selectFld).append(",ROW_NUMBER() OVER(").append(orderby).append(") as _page_row_num_hb ").append(selectFromTableAndWhere).append(
				" ) temp ").append(" where _page_row_num_hb > " + limit * (offset - 1));
		return sql.toString();
	}

	/**
	 * 简化将Bean反射到SQL参数的定义.
	 */
	public BeanPropertySqlParameterSource paramBeanMapper(Object object) {
		return new BeanPropertySqlParameterSource(object);
	}

	/**
	 * 简化将ResultSet反射到Bean的定义.
	 */
	public <T> ParameterizedBeanPropertyRowMapper<T> resultBeanMapper(Class<T> clazz) {
		return ParameterizedBeanPropertyRowMapper.newInstance(clazz);
	}
	

	public List<Map<String, Object>> getGridHeaders(String type){
		List<Map<String, Object>> l = new ArrayList();
		String sql = " select * from xt_grid where gtype="+type+" order by gpx ";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	public List<Map<String, Object>> getGridCols(String type){
		List<Map<String, Object>> l = new ArrayList();
		String sql = " select * from xt_grid where gtype="+type+" and isnull(gdisplay,0)=1 order by gpx ";
		l = this.getSimpleJdbcTemplate().queryForList(sql);
		return l;
	}
	
	/**
	 * 保存日志
	 * @param m
	 * @return
	 */
	public int doSaveLog(Map<String, String> m){
		try{
			String maxid = String.valueOf(this.getMaxKey("XT_SYSLOG"));
			String sql = "insert into XT_SYSLOG(logid,usrid,czsm,rq,status,usrname,czinfo)" +
					" values("+maxid+",'"+m.get("usrid")+"','"+m.get("czsm")+"',GETDATE() , 1,'"+m.get("usrname")+"','"+m.get("czinfo")+"')";
			this.getSimpleJdbcTemplate().update(sql);
		}catch(Exception e ){
		}
		return 1;
	}
	/**
	 */
	public static String FormatString(String s, int l) {
		String res = "";
		if (s != null && !s.equals("")) {
			res = s;
			if (s.length() < l) {
				for (int i = s.length(); i < l; i++) {
					res = "0" + res;
				}
			} else {
				res = s.substring(s.length() - l, s.length());
			}
		}
		return res;
	}
	/**
	 * 将传入的 String 转换成 N 
	 * @param s
	 * @return
	 */
	public static String transToN(String s){
		if(s==null || s.trim().equals("")){
			return "0";
		}else{
			return s;
		}
	}
	
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String transToD(String s){
		if(s == null || s.trim().equals("")){
			return "NULL";
		}else{
			return "'"+s+"'";
		}
	}
	
	public static String transToS(String s){
		if(s == null || s.trim().equals("") || s.equals("null") || s.equals("NULL")){
			return "";
		}else{
			return ""+s+"";
		}
	}
}

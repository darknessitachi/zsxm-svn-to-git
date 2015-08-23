package com.netwander.explib.service.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.util.Assert;

import com.fins.gt.model.FilterInfo;
import com.fins.gt.model.SortInfo;
import com.netwander.core.common.LimitPage;
import com.netwander.core.common.Page;
import com.netwander.explib.entity.Xtuser;

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
	
	public Xtuser xtuser;
	public String menuid;
	public String optionid;//具体操作 L:登入 0：查询 1:新增  2：修改 3：删除  4：同步 
	public String czsm;

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
		String totalsql = getTotalString(sql);
		if (totalCount <= 0) {
			totalCount = this.getJdbcTemplate().queryForInt(totalsql, paramArgs);
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

	private String getTotalString(String sql) {
		int lastIndexOfOrderBy = getLastIndexOfOrderBy(sql);
		// 取出 order by 语句
		String orderby = sql.substring(lastIndexOfOrderBy, sql.length());
		/**
		String tep_sql = sql.substring(0, lastIndexOfOrderBy);
		int indexOfFrom = tep_sql.toLowerCase().indexOf(" from ");
		String selectFld = tep_sql.substring(0, indexOfFrom);

		String selectFromTableAndWhere = tep_sql.substring(indexOfFrom + 6, tep_sql.length());

		StringBuffer pageCountSb = new StringBuffer(100);
		pageCountSb.append("select count(*) from ");
		pageCountSb.append(selectFromTableAndWhere);
		pageCountSb.append("");
		**/
		String tep_sql = sql.substring(0, lastIndexOfOrderBy);
		
		StringBuffer pageCountSb = new StringBuffer(100);
		pageCountSb.append("select count(*) from ");
		pageCountSb.append("("+tep_sql+") cc ");
		pageCountSb.append("");
		return pageCountSb.toString();
	}

	private static int getLastIndexOfOrderBy(String sql) {
		return sql.toLowerCase().lastIndexOf(" order by ");
	}

	public String getLimitString(String querySelect, int offset, int limit) {
		int lastIndexOfOrderBy = getLastIndexOfOrderBy(querySelect);
		String orderby = querySelect.substring(lastIndexOfOrderBy, querySelect.length());
		String tep_sql = querySelect.substring(0, lastIndexOfOrderBy);
		/**
		// 取出 from 前的内容
		int indexOfFrom = querySelect.toLowerCase().indexOf(" from ");
		String selectFld = querySelect.substring(0, indexOfFrom);
		// 取出 from 语句后的内容
		String selectFromTableAndWhere = querySelect.substring(indexOfFrom, lastIndexOfOrderBy);
		StringBuffer sql = new StringBuffer(querySelect.length() + 100);
		sql.append("select TOP " + limit + " * from (").append(selectFld).append(",ROW_NUMBER() OVER(").append(orderby).append(") as _page_row_num_hb ").append(selectFromTableAndWhere).append(
				" ) temp ").append(" where _page_row_num_hb > " + limit * (offset - 1));
		**/		
		
		String orderby_ = orderby.replaceAll(" order by ", "");
		String[] osx = orderby_.split("\\.");
		String osx_ = "";
		if(osx.length <= 1){
			orderby = " order by t_."+orderby_;
			osx_ = "t_";
		}else{
			osx_ = osx[0];
		}
		
		StringBuffer sql = new StringBuffer(querySelect.length() + 100);
		sql.append("select TOP " + limit + " * from ( select * ,ROW_NUMBER() OVER( "+orderby+" ) as _page_row_num_hb from (").append(tep_sql).append(") "+osx_).append(
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
	

	@SuppressWarnings("unchecked")
	public int[] batchUpdate(boolean islog,String arg0, Map[] arg1) {
		if(islog){
			doSaveLog(arg0);
		}
		int[] out = this.getSimpleJdbcTemplate().batchUpdate(arg0, arg1);
		return out;
	}

	public int[] batchUpdate(boolean islog,String sql, SqlParameterSource[] batchArgs) {
		if(islog){
			doSaveLog(sql);
		}
		int[] out = this.getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
		return out;
	}

	public int[] batchUpdate(boolean islog,String sql, List<Object[]> batchArgs) {
		if(islog){
			doSaveLog(sql);
		}
		int[] out = this.getSimpleJdbcTemplate().batchUpdate(sql, batchArgs);
		return out;
	}

	public int[] batchUpdate(boolean islog,String arg0, List<Object[]> arg1, int[] arg2) {
		if(islog){
			doSaveLog(arg0);
		}
		int[] out = this.getSimpleJdbcTemplate().batchUpdate(arg0, arg1, arg2);
		return out;
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> query(String arg0, ParameterizedRowMapper<T> arg1,
			Map arg2) throws DataAccessException {
		List<T> out = this.getSimpleJdbcTemplate().query(arg0, arg1, arg2);
		return out;
	}

	public <T> List<T> query(String arg0, ParameterizedRowMapper<T> arg1,
			SqlParameterSource arg2) throws DataAccessException {
		List<T> out = this.getSimpleJdbcTemplate().query(arg0, arg1, arg2);
		return out;
	}

	public <T> List<T> query(String arg0, ParameterizedRowMapper<T> arg1,
			Object... arg2) throws DataAccessException {
		List<T> out = this.getSimpleJdbcTemplate().query(arg0, arg1, arg2);
		return out;
	}

	@SuppressWarnings("unchecked")
	public int queryForInt(String arg0, Map arg1) throws DataAccessException {
		int out = this.getSimpleJdbcTemplate().queryForInt(arg0, arg1);
		return out;
	}

	public int queryForInt(String arg0, SqlParameterSource arg1)
			throws DataAccessException {
		
		int out = this.getSimpleJdbcTemplate().queryForInt(arg0, arg1);
		
		return out;
	}

	public int queryForInt(String arg0, Object... arg1)
			throws DataAccessException {
		
		int out = this.getJdbcTemplate().queryForInt(arg0, arg1);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> queryForList(String arg0, Map arg1)
			throws DataAccessException {
		
		List<Map<String, Object>> out = this.getSimpleJdbcTemplate().queryForList(arg0, arg1);
		
		return out;
	}

	public List<Map<String, Object>> queryForList(String arg0,
			SqlParameterSource arg1) throws DataAccessException {
		
		List<Map<String, Object>> out = this.getSimpleJdbcTemplate().queryForList(arg0, arg1);
		
		return out;
	}
	
	public List queryForObjectList(String ls_sql,Object[] arg1){
		return this.getJdbcTemplate().query(ls_sql,arg1, new RowMapper(){
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				String [] a = new String[rs.getRow()];
				a[i] = rs.getString(1);
				return a;
			}});
	}
	
	public List<Map<String, Object>> queryForList(String arg0, Object... arg1)
			throws DataAccessException {
		
		List<Map<String, Object>> out = this.getSimpleJdbcTemplate().queryForList(arg0, arg1);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public long queryForLong(String arg0, Map arg1) throws DataAccessException {
		
		long out = this.getSimpleJdbcTemplate().queryForLong(arg0, arg1);
		
		return out;
	}

	public long queryForLong(String arg0, SqlParameterSource arg1)
			throws DataAccessException {
		
		long out = this.getSimpleJdbcTemplate().queryForLong(arg0, arg1);
		
		return out;
	}

	public long queryForLong(String arg0, Object... arg1)
			throws DataAccessException {
		
		long out = this.getSimpleJdbcTemplate().queryForLong(arg0, arg1);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> queryForMap(String arg0, Map arg1)
			throws DataAccessException {
		
		Map<String, Object> out = this.getSimpleJdbcTemplate().queryForMap(arg0, arg1);
		
		return out;
	}

	public Map<String, Object> queryForMap(String arg0, SqlParameterSource arg1)
			throws DataAccessException {
		
		Map<String, Object> out = this.getSimpleJdbcTemplate().queryForMap(arg0, arg1);
		
		return out;
	}

	public Map<String, Object> queryForMap(String arg0, Object... arg1)
			throws DataAccessException {
		
		Map<String, Object> out = this.getSimpleJdbcTemplate().queryForMap(arg0, arg1);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public <T> T queryForObject(String arg0, Class<T> arg1, Map arg2)
			throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	public <T> T queryForObject(String arg0, Class<T> arg1,
			SqlParameterSource arg2) throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	public <T> T queryForObject(String arg0, Class<T> arg1, Object... arg2)
			throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public <T> T queryForObject(String arg0, ParameterizedRowMapper<T> arg1,
			Map arg2) throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	public <T> T queryForObject(String arg0, ParameterizedRowMapper<T> arg1,
			SqlParameterSource arg2) throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	public <T> T queryForObject(String arg0, ParameterizedRowMapper<T> arg1,
			Object... arg2) throws DataAccessException {
		
		T out = this.getSimpleJdbcTemplate().queryForObject(arg0, arg1, arg2);
		
		return out;
	}

	@SuppressWarnings("unchecked")
	public int update(boolean islog,String arg0, Map arg1) throws DataAccessException {
		if(islog){
			doSaveLog(arg0);
		}
		int out = this.getSimpleJdbcTemplate().update(arg0, arg1);
		return out;
	}

	public int update(boolean islog,String arg0, SqlParameterSource arg1)
			throws DataAccessException {
		if(islog){
			doSaveLog(arg0);
		}
		int out = this.getSimpleJdbcTemplate().update(arg0, arg1);
		return out;
	}

	
	public int update(boolean islog,String arg0, Object... arg1) throws DataAccessException {
		if(islog){
			doSaveLog(arg0);
		}
		int out = this.getSimpleJdbcTemplate().update(arg0, arg1);
		return out;
	}

	
	
	/**
	 * 保存日志
	 * @param m
	 * @return
	 */
	public int doSaveLog(String czinfo){
		try{
			String sql = "insert into XT_SYSLOG( USRID, LOGINNAME, CNNAME, MENUID, OPTIONID, CZSM, INTIME, CZINFO)" +
					" values("+getXtuser().getUserid()+",'"+getXtuser().getLoginname()+"','"+getXtuser().getCnname()+"', '"+getMenuid()+"','"+getOptionid()+"','"+getCzsm()+"',GETDATE() ,?)";
			this.getSimpleJdbcTemplate().update(sql,new Object[]{czinfo});
		}catch(Exception e ){
			//e.printStackTrace();
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
	 * 转换成 日期
	 * @param s
	 * @return
	 */
	public static String transToD(String s){
		if(s == null || s.trim().equals("") || s.toLowerCase().equals("null")){
			return "NULL";
		}else{
			return "'"+s+"'";
		}
	}
	
	/**
	 * 转换成 字符
	 * @param s
	 * @return
	 */
	public static String transToS(Object ss){
		String s = String.valueOf(ss);
		if(s == null || s.trim().equals("") || s.toLowerCase().equals("null")){
			return "";
		}else{
			return s;
		}
	}
	/**
	 * 根据生日算年龄
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
	public static int getAge(String d) throws Exception {
		if(d != null && !d.equals("")){
			Date birthDay = null;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthDay = format.parse(d);  
	        Calendar cal = Calendar.getInstance();
	        
	        if (cal.before(birthDay)) {
	            throw new IllegalArgumentException(
	                "The birthDay is before Now.It's unbelievable!");
	        }
	        
	        int yearNow = cal.get(Calendar.YEAR);
	        int monthNow = cal.get(Calendar.MONTH);
	        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
	        cal.setTime(birthDay);

	        int yearBirth = cal.get(Calendar.YEAR);
	        int monthBirth = cal.get(Calendar.MONTH);
	        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
	        int age = yearNow - yearBirth;
	        if (monthNow <= monthBirth) {
	            if (monthNow == monthBirth) {
	                if (dayOfMonthNow < dayOfMonthBirth) {
	                    age--;
	                } else {
	                }
	            } else {
	                age--;
	            }
	        } else {
	        }
	        return age;
	    
		}
		return 0;
	}
	/**
	 * 根据年龄算生日 最大最小值
	 * @param age  年龄
	 * @param m    0: 算出当前岁数的最小年龄
	 * 			   1: 算出当前岁数的最大年龄
	 * @return
	 */
	public static String  getBirthday(int age,Integer m){
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        String yy = String.valueOf((yearNow - age - m))+ "-" + (String.valueOf(monthNow+1).length()<2?("0"+String.valueOf(monthNow+1)):String.valueOf(monthNow+1)) 
        +"-"+ (String.valueOf(dayOfMonthNow).length()<2?("0"+String.valueOf(dayOfMonthNow)):(String.valueOf(dayOfMonthNow)));
        System.out.println(yy);
		return yy;
	}
	public Xtuser getXtuser() {
		if(xtuser == null){
			return new Xtuser();
		}
		return xtuser;
	}
	public void setXtuser(Xtuser xtuser) {
		this.xtuser = xtuser;
	}
	public String getMenuid() {
		if(menuid == null){
			return "";
		}
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getOptionid() {
		if(optionid == null){
			return "";
		}
		return optionid;
	}
	public void setOptionid(String optionid) {
		this.optionid = optionid;
	}
	//0：查询 1:新增  2：修改 3：删除  4：同步 
	public String getCzsm() {
		if(czsm == null || czsm.equals("")){
			String os = getOptionid();
			if(os.equals("L")){
				return "登入系统";
			}else if(os.equals("0")){
				return "查询操作";
			}else if(os.equals("1")){
				return "新增操作";
			}else if(os.equals("2")){
				return "修改操作";
			}else if(os.equals("3")){
				return "删除操作";
			}else if(os.equals("4")){
				return "同步操作";
			}
		}
		return czsm;
	}
	public void setCzsm(String czsm) {
		this.czsm = czsm;
	}
	
	/**
	 * 通知各系统专家库发生变动
	 * @param type
	 * 1: 专家信息放生变动
	 * 2: 基础信息发生变动
	 */
	public void notifySys(int type){
	   try{
		   String result = "";
		   URL U = new URL("http://127.0.0.1:8080/zjps/dwrPush!ni.do");
		   URLConnection connection = U.openConnection();
		   connection.connect();
		   BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		   String line;
		   while ((line = in.readLine())!= null)
		   {
			   result += line;
		   }
		   //System.out.println(result);
		   in.close(); 
	   }catch(Exception e){
		   e.printStackTrace();
	   }
	}
	
	public void expTb(String wburl,String rcid,String wbuserid,String psfldm,String wbappname){
		   try{
			   String result = "";
			   URL U = new URL("http://"+wburl+"/"+wbappname+"/login!expTb.do?expertid="+rcid+"&userid="+wbuserid+"&psfldm="+psfldm);
			   URLConnection connection = U.openConnection();
			   connection.connect();
			   BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			   String line;
			   while ((line = in.readLine())!= null)
			   {
				   result += line;
			   }
			   //System.out.println(result);
			   in.close(); 
		   }catch(Exception e){
			   e.printStackTrace();
		   }
	}
	
}

package com.write.bmybatis.utils;

import java.util.ArrayList;
import java.util.List;


public class SQLUtils {
	/**
	 * 
	 * 获取Insert语句后面values 参数信息
	 * @param sql
	 * @return
	 */
	public static String[] sqlInsertParameter(String sql) {
		int startIndex = sql.indexOf("values");
		int endIndex = sql.length();
		String substring = sql.substring(startIndex + 6, endIndex).replace("(", "").replace(")", "").replace("#{", "")
				.replace("}", "");
		String[] split = substring.split(",");
		return split;
	}

	/**
	 * 
	 * 获取select 后面where语句
	 * @param sql
	 * @return
	 */
	public static List<String> sqlSelectParameter(String sql) {
		int startIndex = sql.indexOf("where");
		int endIndex = sql.length();
		String substring = sql.substring(startIndex + 5, endIndex);
		String[] split = substring.split("and");
		List<String> listArr = new ArrayList<>();
		for (String string : split) {
			String[] sp2 = string.split("=");
			listArr.add(sp2[0].trim());
		}
		return listArr;
	}

	/**
	 * 将SQL语句的参数替换变为?
	 * @param sql
	 * @param parameterName
	 * @return
	 */
	public static String parameQuestion(String sql, String[] parameterName) {
		for (int i = 0; i < parameterName.length; i++) {
			String string = parameterName[i];
			sql = sql.replace("#{" + string + "}", "?");
		}
		return sql;
	}

	public static String parameQuestion(String sql, List<String> parameterName) {
		for (int i = 0; i < parameterName.size(); i++) {
			String string = parameterName.get(i);
			sql = sql.replace("#{" + string + "}", "?");
		}
		return sql;
	}

	public static void main(String[] args) {

		List<String> sqlSelectParameter = SQLUtils
				.sqlSelectParameter("select * from User where userName=#{userName} and userAge=#{userAge} ");
		for (String string : sqlSelectParameter) {
			System.out.println(string);
		}
	}
}

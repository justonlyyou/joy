package com.kvc.joy.plugin.monitor.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

/**
 * 预编译sql语句转化器，实现将预编译的sql语句转化成完整的sql语句
 */
public class PreparedSqlConvertor implements IPreparedSqlConvertor {

	protected static final Log logger = LogFactory.getLog(PreparedSqlConvertor.class);
	/** 预编译sql语句 */
	private String sql;

	/** 预编译参数 */
	private List<String> params = new ArrayList<String>();

	public PreparedSqlConvertor(String sql) {
		this.sql = sql;

	}

	public void setParam(int i, Object param) {
		try {
			String strValue;

			if (param instanceof String || param instanceof Date) {
				strValue = "'" + param + "'";
			} else {
				if (param == null) {
					strValue = "null";
				} else {
					strValue = String.valueOf(param);
				}
			}
			int j = i;
			while (j >= params.size()) {
				params.add(null);
				j--;
			}

			params.set(i - 1, strValue);
		} catch (Exception e) {
			logger.error(e, "sql转换异常");
		}

	}

	public String convert() {
		int len = sql.length();
		StringBuffer t = new StringBuffer(len * 2);
		try {
			if (params != null) {
				int i = 1, limit = 0, base = 0;

				while ((limit = sql.indexOf('?', limit)) != -1) {
					t.append(sql.substring(base, limit));
					t.append((params.size() > i - 1) ? params.get(i - 1) : "");
					i++;
					limit++;
					base = limit;
				}
				if (base < len) {
					t.append(sql.substring(base));
				}
			}
		} catch (Exception e) {
			logger.error(e, "sql转换异常");
		}

		return t.toString();
	}

	public String getSql() {
		return sql;
	}

	public List<String> getParams() {
		return params;
	}

}

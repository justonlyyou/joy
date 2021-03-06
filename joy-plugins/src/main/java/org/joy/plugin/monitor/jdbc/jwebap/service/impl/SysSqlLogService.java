package org.joy.plugin.monitor.jdbc.jwebap.service.impl;

import org.joy.commons.exception.ExceptionTool;
import org.joy.commons.lang.DateTool;
import org.joy.commons.lang.string.StringTool;
import org.joy.commons.log.Log;
import org.joy.commons.log.LogFactory;
import org.joy.core.init.support.properties.AppProperties;
import org.joy.core.init.support.properties.JoyProperties;
import org.joy.core.persistence.jdbc.support.utils.JdbcTool;
import org.joy.core.persistence.orm.jpa.JpaTool;
import org.joy.plugin.monitor.jdbc.jwebap.model.po.TSysSqlLog;
import org.joy.plugin.monitor.jdbc.jwebap.model.vo.ParamMsg;
import org.joy.plugin.monitor.jdbc.jwebap.service.ISysSqlLogService;
import org.joy.plugin.persistence.hibernate.HibernateTool;
import org.joy.plugin.support.PluginBeanFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统sql执行性能日志服务
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年10月3日 下午10:22:18
 */
public class SysSqlLogService implements ISysSqlLogService {
	
	protected static final Log logger = LogFactory.getLog(SysSqlLogService.class);
	
	/**
	 * 获取本实例，为了在本类的非事务方法调用事务方法
	 * 
	 * @return 本实例
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月4日 下午3:48:44
	 */
	protected SysSqlLogService getSelf() {
		return (SysSqlLogService) PluginBeanFactory.getSysSqlLogService();
	}

	@Override
	public void saveLog(ParamMsg param) {
		long costTime = param.getCostTime();
		Integer filterTime = JoyProperties.PLUGIN_JWEBAP_JDBC_FILTERTIME;
		String sql = param.getSql().toLowerCase();
		if (costTime >= filterTime && (sql.startsWith("select") || sql.startsWith("update") 
				|| sql.startsWith("insert") || sql.startsWith("delete"))) {
			final TSysSqlLog sysSqlLog = createTSysSqlLog(param);
			if (sysSqlLog != null) {
				PluginBeanFactory.getSysSqlLogThreadPool().execute(new Runnable() {
					
					@Override
					public void run() {
						getSelf().persist(sysSqlLog);				
					}
					
				});
			}	
		}
	}
	
	/**
	 * 持久化日志(纯粹是为了开事务)
	 * 
	 * @param sqlLog
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月4日 下午3:30:49
	 */
	@Transactional
	public void persist(TSysSqlLog sqlLog) {
		JpaTool.persist(sqlLog);
	}
	
	protected TSysSqlLog createTSysSqlLog(ParamMsg param) {
		TSysSqlLog sqlLog = new TSysSqlLog();
		String sql = processSql(param.getSql());
		String fullSql = processSql(param.getFullSql());
		if (StringTool.isBlank(sql) && StringTool.isBlank(fullSql)) {
			logger.warn("sql语句为空，不记录日志！");
			return null;
		}

		sqlLog.setAppName(AppProperties.NAME_ABBR);
		sqlLog.setSqlText(sql);
		if (StringTool.isNotBlank(fullSql)) {
			sqlLog.setFullSql(fullSql);
		} else {
			sqlLog.setFullSql(sql);
		}
		sqlLog.setVariables(param.getVars());
		
		String curTime = DateTool.currentDate(DateTool.UNFMT_yyyyMMddHHmmssSSS);
		sqlLog.setLogTime(curTime);
		sqlLog.setCostTime(param.getCostTime());
		// sqlLog.setThreadName(event.getThreadName());

		// 位置信息
		setLogLocation(sqlLog);
		
		return sqlLog;
	}
	
	private String processSql(String sql) {
		if (sql != null) {
			sql = sql.replaceAll("'", "''").trim();
			// return StringUtils.divideAverage(sql, 4000);
			if (sql.length() > 4000) {
				sql = sql.substring(0, 4000);
			}
		}
		return sql;
	}
	
	private void setLogLocation(TSysSqlLog sqlLog) {
		if (JoyProperties.PLUGIN_JWEBAP_JDBC_LOGPOSITION) {
			String classPrefix = AppProperties.CLASS_PREFIX;
			Class<?>[] persistToolClasses = {JpaTool.class, HibernateTool.class, JdbcTool.class};
			StackTraceElement elem = ExceptionTool.findFirstStackTraceElem(classPrefix, logger.getClass(), persistToolClasses);
			if (elem != null) {
				sqlLog.setClassName(elem.getClassName());
				sqlLog.setMethodName(elem.getMethodName());
				sqlLog.setLineNumber(elem.getLineNumber());

				String simpleClassName = null;
				try {
					simpleClassName = Class.forName(elem.getClassName()).getSimpleName();
				} catch (ClassNotFoundException e) {
					logger.error(e, "找不到类：" + elem.getClassName());
				}
				if (simpleClassName != null) {
					String moduleName = StringTool.substringBetween(elem.getClassName(), classPrefix + ".", "." + simpleClassName);
					final int MODULE_NAME_LEN = 128;
					if (moduleName.length() > MODULE_NAME_LEN) {
						moduleName = moduleName.substring(0, MODULE_NAME_LEN);
					}
					sqlLog.setModuleName(moduleName);
				}
			}
		}
	}

}

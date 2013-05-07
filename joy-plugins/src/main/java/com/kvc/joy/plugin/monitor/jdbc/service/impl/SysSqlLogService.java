package com.kvc.joy.plugin.monitor.jdbc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.kvc.joy.commons.exception.ExceptionTool;
import com.kvc.joy.commons.lang.DateTool;
import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.init.support.AppPropeties;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.plugin.monitor.jdbc.model.po.TSysSqlLog;
import com.kvc.joy.plugin.monitor.jdbc.model.vo.ParamMsg;
import com.kvc.joy.plugin.monitor.jdbc.service.ISysSqlLogService;
import com.kvc.joy.plugin.support.PluginBeanFactory;

/**
 * 系统sql执行性能日志服务
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013年10月3日 下午10:22:18
 */
public class SysSqlLogService implements ISysSqlLogService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 获取本实例，为了在本类的非事务方法调用事务方法
	 * 
	 * @return
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月4日 下午3:48:44
	 */
	protected SysSqlLogService getSelf() {
		return (SysSqlLogService) PluginBeanFactory.getSysSqlLogService();
	}

	@Override
	public void saveLog(ParamMsg param) {
		long costTime = param.getCostTime();
		Integer filterTime = JoyPropeties.PLUGIN_JWEBAP_JDBC_FILTERTIME;
		if (costTime >= filterTime) {
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
	 * @author 唐玮琳
	 * @time 2013年10月4日 下午3:30:49
	 */
	@Transactional
	public void persist(TSysSqlLog sqlLog) {
		JpaUtils.persist(sqlLog);
	}
	
	protected TSysSqlLog createTSysSqlLog(ParamMsg param) {
		TSysSqlLog sqlLog = new TSysSqlLog();
		String sql = processSql(param.getSql());
		String fullSql = processSql(param.getFullSql());
		if (StringTool.isBlank(sql) && StringTool.isBlank(fullSql)) {
			logger.warn("sql语句为空，不记录日志！");
			return null;
		}

		sqlLog.setAppName(AppPropeties.NAME_ABBR);
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
		if (JoyPropeties.PLUGIN_JWEBAP_JDBC_LOGPOSITION) {
			String classPrefix = AppPropeties.CLASS_PREFIX;
			StackTraceElement elem = ExceptionTool.findFirstStackTraceElem(classPrefix, getClass(), JpaUtils.class);
			if (elem != null) {
				sqlLog.setClassName(elem.getClassName());
				sqlLog.setMethodName(elem.getMethodName());
				sqlLog.setLineNumber(elem.getLineNumber());

				String simpleClassName = null;
				try {
					simpleClassName = Class.forName(elem.getClassName()).getSimpleName();
				} catch (ClassNotFoundException e) {
					logger.error("找不到类：" + elem.getClassName(), e);
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

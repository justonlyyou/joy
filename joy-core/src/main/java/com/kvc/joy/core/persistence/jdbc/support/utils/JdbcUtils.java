package com.kvc.joy.core.persistence.jdbc.support.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.core.persistence.jdbc.dao.BaseJdbcDao;
import com.kvc.joy.core.persistence.jdbc.model.vo.IMdRdbConn;
import com.kvc.joy.core.persistence.orm.jpa.JpaUtils;
import com.kvc.joy.core.spring.utils.SpringBeanUtils;
import com.kvc.joy.core.sysres.datasrc.model.po.TSysDataSrc;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-28 下午9:55:56
 */
 @Service
public class JdbcUtils extends BaseJdbcDao {

	private static Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
	private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();

	private JdbcUtils() {
	}
	
	private static JdbcUtils getInstance() {
		return SpringBeanUtils.getBean(JdbcUtils.class);
	}

	public static Connection getConnectionDirect(String datasourceId) { //TODO
		IMdRdbConn mdRdbConn = JpaUtils.get(TSysDataSrc.class, datasourceId);
		return getConnectionDirect(mdRdbConn);
	}

	/**
	 * 根据数据源id获取数据库连接(没从连接池中获取)
	 * 
	 * @param mdRdbConn 数据源
	 * @return 数据库连接
	 * @author 唐玮琳
	 * @time 2012-11-2 下午2:29:43
	 */
	public static Connection getConnectionDirect(IMdRdbConn mdRdbConn) { // TODO
		if (mdRdbConn == null) {
			return null;
		}
		Connection conn = null;
		try {
			Properties info = new Properties();
			info.setProperty("remarksReporting", "true");
			info.setProperty("user", mdRdbConn.getUsername());
			info.setProperty("password", mdRdbConn.getPassword());
			conn = DriverManager.getConnection(mdRdbConn.getUrl(), info);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return conn;
	}
	
	/**
	 * 
	 * 
	 * @param datasourceId
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午3:14:11
	 */
	public static Connection getConnection(String datasourceId) { //TODO
		IMdRdbConn mdRdbConn = JpaUtils.get(TSysDataSrc.class, datasourceId);
		return getConnection(mdRdbConn);
	}
	
	/**
	 * 
	 * 
	 * @param mdRdbConn
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午3:14:07
	 */
	public static Connection getConnection(IMdRdbConn mdRdbConn) { //TODO
		try {
			return getDataSource(mdRdbConn).getConnection();
		} catch (SQLException e) {
			logger.error("获取数据库连接出错！", e);
		}
		return null;
	}

	/**
	 * 关闭数据连接
	 * 
	 * @param conn 数据连接
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 获取数据源
	 * 
	 * @param mdRdbConn
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午2:59:18
	 */
	public static DataSource getDataSource(IMdRdbConn mdRdbConn) {
		String jndiName = mdRdbConn.getJndiName();
		DataSource dataSource = dataSourceMap.get(jndiName);
		if(dataSource == null) {
			if (StringTool.isNotBlank(jndiName)) {
				InitialContext context = null;
				try {
					context = new InitialContext();
					dataSource = (DataSource) context.lookup(jndiName);
				} catch (NamingException e) {
					try {
						dataSource = (DataSource) context.lookup("java:comp/env/" + jndiName);
					} catch (NamingException ex) {
						logger.error("以JNDI: " + jndiName + "获取数据源失败！", ex);
					}
				}
			}
		}
		return dataSource;
	}
	
	public static final JdbcTemplate jdbcTemplate() {
		return getInstance().getJdbcTemplate();
	}

}

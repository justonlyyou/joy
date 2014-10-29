package org.joy.core.persistence.jdbc.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import javax.annotation.Resource;

/**
 * 基本的Jdbc数据访问对象
 * 
 * @author Kevice
 * @time 2012-12-28 下午9:56:23
 * @since 1.0.0
 */
public class BaseJdbcDao extends JdbcDaoSupport {

	@Resource
	public void setJDBCTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	
}

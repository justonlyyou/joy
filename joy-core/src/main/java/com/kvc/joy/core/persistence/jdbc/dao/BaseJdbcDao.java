package com.kvc.joy.core.persistence.jdbc.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 
 * @author 唐玮琳
 * @time 2012-12-28 下午9:56:23
 */
public class BaseJdbcDao extends JdbcDaoSupport {

	@Resource
	public void setJDBCTemplate(JdbcTemplate jdbcTemplate) {
		super.setJdbcTemplate(jdbcTemplate);
	}
	
}

package org.joy.core.persistence.jdbc.service.impl;

import org.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;
import org.joy.core.persistence.jdbc.service.IMdRdbTableService;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * 关系型数据库表元数据信息缓存服务
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-1-3 上午12:11:50
 */
public class MdRdbTableCacheService implements IMdRdbTableService {

	public static final String CACHE_NAME = "MD_RDB_TABLE";
	private IMdRdbTableService mdRdbTableService;

	public void setMdRdbTableService(IMdRdbTableService mdRdbTableService) {
		this.mdRdbTableService = mdRdbTableService;
	}

	@Cacheable(value=CACHE_NAME, key="#connection.dsId")
	@Override
	public Map<String, MdRdbTable> getTables(RdbConnection connection) {
		return mdRdbTableService.getTables(connection);
	}

}
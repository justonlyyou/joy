package org.joy.core.persistence.jdbc.service.impl;

import org.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import org.joy.core.persistence.jdbc.model.vo.RdbConnection;
import org.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * 关系型数据库列元数据信息缓存服务
 * 
 * @author Kevice
 * @time 2013-2-3 下午9:28:36
 * @since 1.0.0
 */
public class MdRdbColumnCacheService implements IMdRdbColumnService {
	
	public static final String CACHE_NAME = "MD_RDB_COLUMN";
	private IMdRdbColumnService mdRdbColumnService;

	@Cacheable(value=CACHE_NAME, key="#connection.dsId + '-' + #tableName")
	@Override
	public Map<String, MdRdbColumn> getColumns(RdbConnection connection, String tableName) {
		return mdRdbColumnService.getColumns(connection, tableName);
	}
	
	public void setMdRdbColumnService(IMdRdbColumnService mdRdbColumnService) {
		this.mdRdbColumnService = mdRdbColumnService;
	}

}

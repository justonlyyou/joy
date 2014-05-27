package com.kvc.joy.core.persistence.jdbc.service.impl;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbColumnService;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-2-3 下午9:28:36
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

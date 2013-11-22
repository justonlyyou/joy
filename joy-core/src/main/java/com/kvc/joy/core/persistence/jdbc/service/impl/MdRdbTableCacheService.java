package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
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
package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbColumn;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbColumnService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:28:36
 */
public class MdRdbColumnCacheService implements IMdRdbColumnService {
	
	public static final String CACHE_NAME = "MD_RDB_COLUMN";
	private IMdRdbColumnService mdRdbColumnService;

	@Cacheable(CACHE_NAME)
	public List<MdRdbColumn> getColumns(String datasourceId, String tableName) {
		return mdRdbColumnService.getColumns(datasourceId, tableName);
	}
	
	public void setMdRdbColumnService(IMdRdbColumnService mdRdbColumnService) {
		this.mdRdbColumnService = mdRdbColumnService;
	}

}

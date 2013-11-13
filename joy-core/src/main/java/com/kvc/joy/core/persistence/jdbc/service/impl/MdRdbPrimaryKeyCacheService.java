package com.kvc.joy.core.persistence.jdbc.service.impl;

import org.springframework.cache.annotation.Cacheable;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:30:40
 */
public class MdRdbPrimaryKeyCacheService implements IMdRdbPrimaryKeyService {
	
	public static final String CACHE_NAME = "MD_RDB_PRIMARY_KEY";
	private IMdRdbPrimaryKeyService mdRdbPrimaryKeyService;

	@Cacheable(CACHE_NAME)
	@Override
	public MdRdbPrimaryKey getPrimaryKeyByDatasourceId(String datasourceId, String tableName) {
		return mdRdbPrimaryKeyService.getPrimaryKeyByDatasourceId(datasourceId, tableName);
	}
	
	@Cacheable(CACHE_NAME)
	@Override
	public MdRdbPrimaryKey getPrimaryKeyByJndi(String jndi, String tableName) {
		return mdRdbPrimaryKeyService.getPrimaryKeyByJndi(jndi, tableName);
	}
	
	public void setMdRdbPrimaryKeyService(IMdRdbPrimaryKeyService mdRdbPrimaryKeyService) {
		this.mdRdbPrimaryKeyService = mdRdbPrimaryKeyService;
	}

	

}

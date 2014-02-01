package com.kvc.joy.core.persistence.jdbc.service.impl;

import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbPrimaryKey;
import com.kvc.joy.core.persistence.jdbc.model.vo.RdbConnection;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbPrimaryKeyService;
import org.springframework.cache.annotation.Cacheable;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-3 下午9:30:40
 */
public class MdRdbPrimaryKeyCacheService implements IMdRdbPrimaryKeyService {
	
	public static final String CACHE_NAME = "MD_RDB_PRIMARY_KEY";
	private IMdRdbPrimaryKeyService mdRdbPrimaryKeyService;

	@Cacheable(value=CACHE_NAME, key="#connection.dsId + '-' + #tableName")
	@Override
	public MdRdbPrimaryKey getPrimaryKey(RdbConnection connection, String tableName) {
		return mdRdbPrimaryKeyService.getPrimaryKey(connection, tableName);
	}
	
	public void setMdRdbPrimaryKeyService(IMdRdbPrimaryKeyService mdRdbPrimaryKeyService) {
		this.mdRdbPrimaryKeyService = mdRdbPrimaryKeyService;
	}

	

}

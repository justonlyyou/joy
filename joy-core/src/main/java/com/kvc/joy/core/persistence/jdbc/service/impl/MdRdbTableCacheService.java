package com.kvc.joy.core.persistence.jdbc.service.impl;

import java.util.Map;

import com.kvc.joy.core.ehcache.support.AbstractEhCacheHolder;
import com.kvc.joy.core.init.service.ISystemInitService;
import com.kvc.joy.core.persistence.jdbc.model.vo.MdRdbTable;
import com.kvc.joy.core.persistence.jdbc.service.IMdRdbTableService;
import com.kvc.joy.core.persistence.jdbc.support.MdRdbCommentGenerator;

/**
 * 
 * @author 唐玮琳
 * @time 2013-1-3 上午12:11:50
 */
public class MdRdbTableCacheService extends AbstractEhCacheHolder<String, MdRdbTable> implements ISystemInitService {

	public static final String CACHE_NAME = "MD_RDB_TABLE";
	private IMdRdbTableService mdRdbTableService;

	public String getCacheName() {
		return CACHE_NAME;
	}

	public void init() {
		cache(loadAll());
//		MdRdbCommentGenerator.generate(); //TODO
	}

	@Override
	protected MdRdbTable loadEntity(String key) {
		return mdRdbTableService.get(key);
	}

	@Override
	protected Map<String, MdRdbTable> loadAll() {
		return mdRdbTableService.get();
	}

	public String getName() {
		return "关系数据库表元数据缓存";
	}

	public void setMdRdbTableService(IMdRdbTableService mdRdbTableService) {
		this.mdRdbTableService = mdRdbTableService;
	}

}

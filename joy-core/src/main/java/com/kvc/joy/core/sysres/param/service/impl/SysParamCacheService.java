package com.kvc.joy.core.sysres.param.service.impl;

import java.util.Map;

import com.kvc.joy.core.ehcache.support.AbstractEhCacheHolder;
import com.kvc.joy.core.init.service.ISystemInitService;
import com.kvc.joy.core.sysres.param.po.TSysParam;
import com.kvc.joy.core.sysres.param.service.ISysParamService;

/**
 * 
 * @author 唐玮琳
 * @time 2013-2-8 上午10:58:22
 */
public class SysParamCacheService extends AbstractEhCacheHolder<String, TSysParam> implements ISystemInitService {

	public static final String SYS_PARAM_CACHE = "SYS_PARAM";
	private ISysParamService sysParamService;

	public String getCacheName() {
		return SYS_PARAM_CACHE;
	}

	public String getName() {
		return "系统参数缓存";
	}

	public void init() {
		cache(loadAll());
	}

	@Override
	protected TSysParam loadEntity(String key) {
		return sysParamService.get(key);
	}

	@Override
	protected Map<String, TSysParam> loadAll() {
		return sysParamService.get();
	}

	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}

}

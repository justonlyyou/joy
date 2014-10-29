package org.joy.core.sysres.param.service.impl;

import org.joy.core.ehcache.support.AbstractEhCacheHolder;
import org.joy.core.init.service.IInitService;
import org.joy.core.sysres.param.model.po.TSysParam;
import org.joy.core.sysres.param.service.ISysParamCacheService;
import org.joy.core.sysres.param.service.ISysParamService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 系统参数缓存服务
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-2-8 上午10:58:22
 */
public class SysParamCacheService extends AbstractEhCacheHolder<String, TSysParam> implements ISysParamCacheService, IInitService {

	public static final String SYS_PARAM_CACHE = "SYS_PARAM";
	private ISysParamService sysParamService;

    @Override
    public Map<String, TSysParam> get() {
        return getMap();
    }

    @Override
    public Map<String, TSysParam> getProperties() {
        Map<String, TSysParam> propertyMap = new HashMap<String, TSysParam>();
        Set<String> keys = getKeys();
        for(String key : keys) {
            TSysParam param = get(key);
            if ("1".equals(param.getEncrypt())) {
                propertyMap.put(key, param);
            }
        }
        return propertyMap;
    }

	public String getCacheName() {
		return SYS_PARAM_CACHE;
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

    @Override
    public String getName() {
        return "系统参数";
    }

    @Override
    public void init() {
        cache(loadAll());
    }

    @Override
    public int getInitPriority() {
        return 0;
    }
}

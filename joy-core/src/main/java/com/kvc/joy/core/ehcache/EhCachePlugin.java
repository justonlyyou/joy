package com.kvc.joy.core.ehcache;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.ehcache.model.po.TSysCacheCfg;
import com.kvc.joy.core.ehcache.model.po.TSysCacheCfg_;
import com.kvc.joy.core.init.service.IJoyPlugin;
import com.kvc.joy.core.persistence.orm.jpa.JpaTool;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import javax.persistence.Transient;
import java.util.List;

/**
 * 
 * @author Kevice
 * @time 2013-2-3 下午4:24:32
 */
@Component
public class EhCachePlugin implements IJoyPlugin {

	protected static final Log logger = LogFactory.getLog(EhCachePlugin.class);

	public String getName() {
		return "EhCache缓存";
	}

	public void startup() {
		joinDbEhCacheConf();
	}

	public void destroy() {

	}

	private void joinDbEhCacheConf() {
		EhCacheCacheManager ehCacheCacheManager = CoreBeanFactory.getEhCacheCacheManager();
		CacheManager cacheManager = ehCacheCacheManager.getCacheManager();
		List<TSysCacheCfg> cacheCfgList = JpaTool.search(TSysCacheCfg.class, TSysCacheCfg_.deleted, "0");
		for (TSysCacheCfg cacheCfg : cacheCfgList) {
			joinDbEhCacheConf(cacheManager, cacheCfg);
		}
	}

	private void joinDbEhCacheConf(CacheManager cacheManager, TSysCacheCfg cacheCfg) {
		if (cacheManager.cacheExists(cacheCfg.getCacheName()) == false) {
			CacheConfiguration configuration = createCacheConfiguration(cacheCfg);
			Cache cache = new Cache(configuration);
			cacheManager.addCache(cache);
		} else {
			logger.warn("缓存【" + cacheCfg.getCacheName() + "】已存在，不能重复添加！");
		}
	}

	private CacheConfiguration createCacheConfiguration(TSysCacheCfg cacheCfg) {
		CacheConfiguration configuration = new CacheConfiguration();
		configuration.setName(cacheCfg.getCacheName());
		configuration.setMaxElementsInMemory(cacheCfg.getMaxElementsInMemory());
		configuration.setMaxElementsOnDisk(cacheCfg.getMaxElementsOnDisk());
		configuration.setEternal(cacheCfg.eternal());
		configuration.setOverflowToDisk(cacheCfg.overflowToDisk());
		configuration.setTimeToIdleSeconds(cacheCfg.getTimeToIdleSeconds());
		configuration.setTimeToLiveSeconds(cacheCfg.getTimeToLiveSeconds());
		configuration.setMemoryStoreEvictionPolicy(cacheCfg.getMemoryStoreEvictionPolicy());
		configuration.setDiskPersistent(cacheCfg.diskPersistent());
		configuration.setDiskExpiryThreadIntervalSeconds(cacheCfg.getDiskExpiryThreadIntervalSeconds());
		configuration.setDiskSpoolBufferSizeMB(cacheCfg.getDiskSpoolBufferSizeMb());
		return configuration;
	}

	@Transient
	public boolean isEnabled() {
		return true;
	}

	public int getInitPriority() {
		return 0;
	}

	@Override
	public String getSqlMigrationPrefix() {
		return "EHCACHE";
	}

	@Override
	public String getPoPackage() {
		return TSysCacheCfg.class.getPackage().getName();
	}

	@Override
	public String getCtxConfLocation() {
		return "classpath*:/conf/comp-appCtx-ehcache.xml";
	}

}

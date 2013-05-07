package com.kvc.joy.core.ehcache.support;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.exception.ServiceException;
import com.kvc.joy.core.init.support.JoyPropeties;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;

/**
 * 抽象的EhCache缓存持有者
 * @author <b>唐玮琳</b>
 */
public abstract class AbstractEhCacheHolder<K, V> implements IEhCacheHolder<K, V> {

	private Cache cache;
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 根据key从缓存中取得实体
	 * 
	 * @param key
	 * @return
	 * @author 唐玮琳
	 * @date 2012-5-30 上午11:22:13
	 */
	@SuppressWarnings("unchecked")
	public V get(K key) {
		V entity = null;
		try {
			Element element = null;
			if (JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
				element = getCache().get(key);
			}
			if (element == null) {
				entity = loadEntity(key);
				if (JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
					element = new Element(key, entity);
					getCache().put(element);
				}
			} else {
				entity = (V) element.getObjectValue();
			}
			return entity;
		} catch (CacheException e) {
			throw new ServiceException("缓存读取发生异常!", e);
		}
	}

	@SuppressWarnings("unchecked")
	public Set<K> getKeys() {
		List<K> keys = getCache().getKeys();
		return new HashSet<K>(keys);
	}

	public Map<K, V> getMap() {
		if(JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
			Set<K> keys = getKeys();
			Map<K, V> map = new LinkedHashMap<K, V>();
			for (K key : keys) {
				map.put(key, get(key));
			}
			return map;
		} else {
			return  loadAll();
		}
	}

	/**
	 * 缓存指定对象
	 * 
	 * @param data
	 * @author 唐玮琳
	 * @time 2013-1-2 下午11:11:52
	 */
	protected void cache(Map<K, V> data) {
		if (JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
			if (data != null) {
				for (Entry<K, V> entry : data.entrySet()) {
					Element element = new Element(entry.getKey(), entry.getValue());
					getCache().put(element);
				}
			}
		} else {
			logger.warn("ehcache缓存组件未启用，不能缓存数据！");
		}
	}

	/**
	 * 根据关键字加载要缓存的实体
	 * 
	 * @param key 关键字(能够惟一标识一个实体)
	 * @return 缓存的实体
	 * @author 唐玮琳
	 * @date 2012-5-30 上午10:56:21
	 */
	protected abstract V loadEntity(K key);
	
	/**
	 * 加载所有数据
	 * 
	 * @return
	 * @author 唐玮琳
	 * @time 2013-2-8 下午12:17:52
	 */
	protected abstract Map<K, V> loadAll();

	/**
	 * 刷新缓存里的所有数据
	 * 
	 * @author 唐玮琳
	 * @date 2012-5-30 上午11:14:54
	 */
	public void refresh() {
		if (JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
			logger.info("刷新缓存：" + getCacheName() + "里的所有数据...");
			getCache().removeAll();
			
		} else {
			logger.warn("ehcache缓存组件未启用，不能刷新缓存！");
		}
	}

	/**
	 * 刷新缓存里指定key的数据
	 * 
	 * @param key
	 * @author 唐玮琳
	 * @date 2012-5-30 上午11:21:27
	 */
	public void refresh(K key) {
		if (JoyPropeties.PLUGIN_EHCACHE_ENABLED) {
			logger.info("刷新缓存：" + getCacheName() + "里key为" + key + "的数据...");
			getCache().remove(key);
			get(key);
		} else {
			logger.warn("ehcache缓存组件未启用，不能刷新缓存！");
		}
	}

	protected Cache getCache() {
		if (cache == null) {
			cache = (Cache) CoreBeanFactory.getEhCacheCacheManager().getCache(getCacheName()).getNativeCache();
		}
		return cache;
	}

}

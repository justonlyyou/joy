package com.kvc.joy.core.ehcache.support;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;
import com.kvc.joy.core.spring.utils.CoreBeanFactory;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;

import java.util.*;
import java.util.Map.Entry;

/**
 * 抽象的EhCache缓存持有者
 * 
 * @author <b>唐玮琳</b>
 */
public abstract class AbstractEhCacheHolder<K, V> implements IEhCacheHolder<K, V> {

	private Cache cache;
	protected static final Log logger = LogFactory.getLog(AbstractEhCacheHolder.class);

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
		V entity;
		try {
			Element element;
			element = getCache().get(key);
			if (element == null) {
				entity = loadEntity(key);
				element = new Element(key, entity);
				getCache().put(element);
			} else {
				entity = (V) element.getObjectValue();
			}
			return entity;
		} catch (CacheException e) {
			throw new SystemException(e, "缓存读取发生异常!");
		}
	}

	@SuppressWarnings("unchecked")
	public Set<K> getKeys() {
		List<K> keys = getCache().getKeys();
		return new HashSet<K>(keys);
	}

	public Map<K, V> getMap() {
		Set<K> keys = getKeys();
		Map<K, V> map = new LinkedHashMap<K, V>(keys.size());
		for (K key : keys) {
			map.put(key, get(key));
		}
		return map;
	}

	/**
	 * 缓存指定对象
	 * 
	 * @param data
	 * @author 唐玮琳
	 * @time 2013-1-2 下午11:11:52
	 */
	protected void cache(Map<K, V> data) {
		if (data != null) {
			for (Entry<K, V> entry : data.entrySet()) {
				Element element = new Element(entry.getKey(), entry.getValue());
				getCache().put(element);
			}
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
		logger.info("刷新缓存：" + getCacheName() + "里的所有数据...");
		getCache().removeAll();
	}

	/**
	 * 刷新缓存里指定key的数据
	 * 
	 * @param key
	 * @author 唐玮琳
	 * @date 2012-5-30 上午11:21:27
	 */
	public void refresh(K key) {
		logger.info("刷新缓存：" + getCacheName() + "里key为" + key + "的数据...");
		getCache().remove(key);
		get(key);
	}

	protected Cache getCache() {
		if (cache == null) {
			cache = (Cache) CoreBeanFactory.getEhCacheCacheManager().getCache(getCacheName()).getNativeCache();
		}
		return cache;
	}

}

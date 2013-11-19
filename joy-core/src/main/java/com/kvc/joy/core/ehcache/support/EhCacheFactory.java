package com.kvc.joy.core.ehcache.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.string.StringTool;

/**
 * EhCache缓存工厂
 * 
 * @author <b>唐玮琳</b>
 */
public class EhCacheFactory {

	private static Map<String, IEhCacheHolder> cacheHolderMap;
	private static Logger logger = LoggerFactory.getLogger(EhCacheFactory.class);

	static {
		initCacheMap();
	}

	private EhCacheFactory() {
	}

	@SuppressWarnings("unchecked")
	private static void initCacheMap() {
		cacheHolderMap = new HashMap<String, IEhCacheHolder>();
		// TODO
		// List<IEhCacheHolder> lookup =
		// Registry.lookup(Registry.EHCACHE_HOLDERS);
		// for (IEhCacheHolder cacheHolder : lookup) {
		// cacheHolderMap.put(cacheHolder.getCacheName(), cacheHolder);
		// }
	}

	/**
	 * 获取指定名称的缓存中指定key的数据
	 * 
	 * @param cacheName 缓存名称
	 * @param key 关键字
	 * @return 缓存数据对象
	 * @author 唐玮琳
	 * @date 2012-5-30 下午02:10:57
	 */
	public static Object getEntity(String cacheName, Object key) {
		logger.debug("cacheName: " + cacheName + ", key: " + key);
		if (StringTool.isBlank(cacheName) || key == null) {
			throw new IllegalArgumentException("参数不能为空！");
		}
		IEhCacheHolder cacheHolder = getCacheHolder(cacheName);
		return cacheHolder.get(key);
	}

	/**
	 * 获取指定名称的缓存中所有key的数据
	 * 
	 * @param cacheName 缓存名称
	 * @return List<缓存数据对象>
	 * @author 唐玮琳
	 * @date 2012-5-30 下午02:12:10
	 */
	@SuppressWarnings("unchecked")
	public static List<Object> getEntities(String cacheName) {
		logger.debug("cacheName: " + cacheName);
		if (StringTool.isBlank(cacheName)) {
			throw new IllegalArgumentException("参数不能为空！");
		}
		IEhCacheHolder cacheHolder = getCacheHolder(cacheName);
		Set<Object> keys = cacheHolder.getKeys();
		List<Object> objList = new ArrayList<Object>(keys.size());
		for (Object key : keys) {
			objList.add(cacheHolder.get(key));
		}
		return objList;
	}

	/**
	 * 刷新指定名称的缓存中指定key的数据
	 * 
	 * @param cacheName 缓存名称
	 * @param key 关键字
	 * @author 唐玮琳
	 * @date 2012-5-30 下午02:27:51
	 */
	public static void refresh(String cacheName, Object key) {
		logger.debug("刷新缓存，cacheName: " + cacheName + ", key: " + key);
		if (StringTool.isBlank(cacheName) || key == null) {
			throw new IllegalArgumentException("参数不能为空！");
		}
		getCacheHolder(cacheName).refresh(key);
	}

	/**
	 * 刷新指定名称的缓存中所有key的数据
	 * 
	 * @param cacheName 缓存名称
	 * @author 唐玮琳
	 * @date 2012-5-30 下午02:29:25
	 */
	public static void refresh(String cacheName) {
		logger.debug("刷新缓存，cacheName: " + cacheName);
		if (StringTool.isBlank(cacheName)) {
			throw new IllegalArgumentException("参数不能为空！");
		}
		getCacheHolder(cacheName).refresh();
	}

	/**
	 * 获取缓存持有者
	 * 
	 * @param cacheName 缓存名称
	 * @return
	 * @author 唐玮琳
	 * @date 2012-5-30 下午02:13:32
	 */
	private static IEhCacheHolder getCacheHolder(String cacheName) {
		IEhCacheHolder cacheHolder = cacheHolderMap.get(cacheName);
		if (cacheHolder == null) {
			throw new SystemException("不存在名称为" + cacheName + "的缓存，请确定是否已注册该缓存！");
		}
		return cacheHolder;
	}

}

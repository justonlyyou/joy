package org.joy.core.ehcache.support;

import java.util.Map;
import java.util.Set;

/**
 * EhCache缓存持有者接口
 * 
 * @author Kevice
 * @time 2012-5-30 上午10:32:09
 * @since 1.0.0
 */
public interface IEhCacheHolder<K, V> {

	/**
	 * 取得缓存的名称(配置在ehcache.xml中，全局惟一)
	 * 
	 * @return 缓存的名称
	 * @author Kevice
	 * @time 2012-5-30 上午10:32:09
	 */
	String getCacheName();

	/**
	 * 根据key从缓存中取得实体
	 * 
	 * @param key
	 * @return
	 * @author Kevice
	 * @time 2012-5-30 上午11:22:13
	 */
	V get(K key);

	/**
	 * 获取所有Key值
	 * 
	 * @return Key值集合
	 * @author Kevice
	 * @time 2013-1-3 上午11:21:28
	 */
	Set<K> getKeys();

	/**
	 * 
	 * 
	 * @return
	 * @author Kevice
	 * @time 2013-2-4 下午11:50:24
	 */
	Map<K, V> getMap();

	/**
	 * 刷新缓存里的所有数据
	 * 
	 * @author Kevice
	 * @time 2012-5-30 上午11:14:54
	 */
	void refresh();

	/**
	 * 刷新缓存里指定key的数据
	 * 
	 * @param key
	 * @author Kevice
	 * @time 2012-5-30 上午11:21:27
	 */
	void refresh(K key);

}

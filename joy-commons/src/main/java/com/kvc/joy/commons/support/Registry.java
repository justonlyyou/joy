package com.kvc.joy.commons.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册器
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class Registry {

	/**
	 * 所有注册的对象Map
	 */
	private static Map<String, List<Object>> map = new HashMap<String, List<Object>>();

	private Registry() {
	}

	/**
	 * 根据key查询所注册的对象
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List lookup(String key) {
		List<Object> resultList = map.get(key);
		return resultList == null ? new ArrayList<Object>(0) : resultList;
	}

	/**
	 * 注册
	 * 
	 * @param key
	 * @param obj
	 */
	@SuppressWarnings("unchecked")
	public static void registe(String key, Object obj) {
		List<Object> resultList = lookup(key);
		resultList.add(obj);
		map.put(key, resultList);
	}

	/**
	 * 批量注册
	 * 
	 * @param key
	 * @param objList
	 */
	@SuppressWarnings("unchecked")
	public static void registe(String key, List<Object> objList) {
		List<Object> resultList = lookup(key);
		resultList.addAll(objList);
		map.put(key, resultList);
	}

}

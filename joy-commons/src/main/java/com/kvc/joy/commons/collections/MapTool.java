package com.kvc.joy.commons.collections;

import org.apache.commons.collections.*;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.collections.map.TransformedMap;
import org.apache.commons.collections.map.TransformedSortedMap;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.*;

/**
 * Map工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-6 上午10:17:58
 */
public class MapTool {

	private MapTool() {
	}
	
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.collections.MapUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 从Map中取得指定key的值，空安全
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return Map中的值, 如果map参数为null将返回<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午9:16:00
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> V getObject(final Map<K, V> map, final K key) {
        return (V) MapUtils.getObject(map, key);
    }
	
	/**
	 * <p>
	 * 从Map中取得指定key的值的toString，空安全
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的toString, 如果map参数为null将返回<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午9:36:08
	 */
	public static <K> String getString(final Map<K, ?> map, final K key) {
    	return MapUtils.getString(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的布尔值，空安全
	 * </p>
	 * 
	 * <p>
	 * 如果值为布尔值，直接返回。如果为字符串且等于'true'(忽略大小写)返回true,
	 * 否则返回false。如果为整型值0返回false，非0返回true。
	 * 其它情况全部返回null。
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的布尔值, 如果map参数为null将返回<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午9:51:27
	 */
	public static <K> Boolean getBoolean(final Map<K, ?> map, final K key) {
    	return MapUtils.getBoolean(map, key);
    }
	
	/**
	 * <p>
	 * 从Map中取得指定key的值的数值结果，空安全
	 * </p>
	 * 
	 * <p>
	 * 如果值为数值型，它将直接返回。如果为字符串，它将返回由{@link NumberFormat#parse(String)}
	 * 按系统默认的格式转换后的值，当转换失败时返回null。
	 * 其它情况全部返回null。
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的数值结果, 如果map参数为null将返回<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午9:56:59
	 */
	public static <K> Number getNumber(final Map<K, ?> map, final K key) {
    	return MapUtils.getNumber(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的字节值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的字节值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的字节值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:21:00
	 */
	public static <K> Byte getByte(final Map<K, ?> map, final K key) {
    	return MapUtils.getByte(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的短整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的短整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的短整型值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:27:06
	 */
	public static <K> Short getShort(final Map<K, ?> map, final K key) {
    	return MapUtils.getShort(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的整型值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:30:14
	 */
	public static <K> Integer getInteger(final Map<K, ?> map, final K key) {
    	return MapUtils.getInteger(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的长整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的长整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的长整型值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:30:34
	 */
	public static <K> Long getLong(final Map<K, ?> map, final K key) {
    	return MapUtils.getLong(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的浮点值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的浮点值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的浮点值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:31:34
	 */
	public static <K> Float getFloat(final Map<K, ?> map, final K key) {
    	return MapUtils.getFloat(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的双精度浮点值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的双精度浮点值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的双精度浮点值, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:32:54
	 */
	public static <K> Double getDouble(final Map<K, ?> map, final K key) {
    	return MapUtils.getDouble(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的Map，空安全
	 * </p>
	 * 
	 * <p>
	 * 如果从map中取出的值不是map，返回null
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的Map, 如果map参数为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:35:25
	 */
	@SuppressWarnings("rawtypes")
	public static <K> Map getMap(final Map<K, ?> map, final K key) {
    	return MapUtils.getMap(map, key);
    }

	// Type safe getters with default values
	// -------------------------------------------------------------------------
	/**
	 * <p>
	 * 从Map中取得指定key的对象，如果为null返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值, 如果为null返回指定的默认值，map为null返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:41:18
	 */
	public static <K> Object getObject( Map<K, ?> map, K key, Object defaultValue ) {
    	return MapUtils.getObject(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的String值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的String值，如果为原值为null、map为null或转换失败返回指定的默认值 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:48:08
	 */
	public static <K> String getString( Map<K, ?> map, K key, String defaultValue ) {
    	return MapUtils.getString(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Boolean值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Boolean值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:56:07
	 */
	public static <K> Boolean getBoolean( Map<K, ?> map, K key, Boolean defaultValue ) {
    	return MapUtils.getBoolean(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Number值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Number值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:57:07
	 */
	public static <K> Number getNumber( Map<K, ?> map, K key, Number defaultValue ) {
    	return MapUtils.getNumber(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Byte值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Byte值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> Byte getByte( Map<K, ?> map, K key, Byte defaultValue ) {
    	return MapUtils.getByteValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Short值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Short值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:59:15
	 */
	public static <K> Short getShort( Map<K, ?> map, K key, Short defaultValue ) {
    	return MapUtils.getShortValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Integer值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Integer值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:59:59
	 */
	public static <K> Integer getInteger( Map<K, ?> map, K key, Integer defaultValue ) {
    	return MapUtils.getInteger(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Long值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Long值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:01:04
	 */
	public static <K> Long getLong( Map<K, ?> map, K key, Long defaultValue ) {
    	return MapUtils.getLong(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Float值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Float值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:02:21
	 */
	public static <K> Float getFloat( Map<K, ?> map, K key, Float defaultValue ) {
    	return MapUtils.getFloat(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Double值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Double值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:02:59
	 */
	public static <K> Double getDouble( Map<K, ?> map, K key, Double defaultValue ) {
    	return MapUtils.getDouble(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Map值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null时要返回的默认值
	 * @return map中key对应的值的Map值，如果为原值为null、map为null或转换失败返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:04:55
	 */
	@SuppressWarnings("rawtypes")
	public static <K> Map getMap( Map<K, ?> map, K key, Map defaultValue ) {
    	return MapUtils.getMap(map, key, defaultValue);
    }

	// Type safe primitive getters
	// -------------------------------------------------------------------------

	/**
	 * <p>
	 * 从Map中取得指定key的值的布尔值，空安全
	 * </p>
	 * 
	 * <p>
	 * 如果值为布尔值，直接返回。如果为字符串且等于'true'(忽略大小写)返回true,
	 * 否则返回false。如果为整型值0返回false，非0返回true。
	 * 其它情况全部返回false。
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的布尔值, 如果map参数为null将返回<code>false</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午9:51:27
	 */
	public static <K> boolean getBooleanValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getBooleanValue(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的字节值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的字节值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的字节值, 如果map参数为null将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:21:00
	 */
	public static <K> byte getByteValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getByteValue(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的短整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的短整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的短整型值, 如果map参数为null将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:27:06
	 */
	public static <K> short getShortValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getShortValue(map, key);
    }

	/**
	 * Gets an int from a Map in a null-safe manner.
	 * <p>
	 * The int is obtained from the results of {@link #getNumber(Map,Object)}.
	 * 
	 * @param map the map to use
	 * @param key 要查找的key
	 * @return the value in the Map as an int, <code>0</code> if null map input
	 */
	
	/**
	 * <p>
	 * 从Map中取得指定key的值的整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的整型值, 如果map参数为null将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:30:14
	 */
	public static <K> int getIntValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getIntValue(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的长整型值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的长整型值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的长整型值, 如果map参数为null将返回0L
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:30:34
	 */
	public static <K> long getLongValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getLongValue(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的浮点值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的浮点值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的浮点值, 如果map参数为null将返回0.0F
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:31:34
	 */
	public static <K> float getFloatValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getFloatValue(map, key);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的值的双精度浮点值，空安全
	 * </p>
	 * 
	 * <p>
	 * 结果为{@link #getNumber(Map,Object)}的值的双精度浮点值
	 * </p>
	 * 
	 * @param map 要获取值的map，可以为null
	 * @param key 要查找的key
	 * @return 指定key的值的双精度浮点值, 如果map参数为null将返回0.0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:32:54
	 */
	public static <K> double getDoubleValue(final Map<K, ?> map, final K key) {
    	return MapUtils.getDoubleValue(map, key);
    }

	// Type safe primitive getters with default values
	// -------------------------------------------------------------------------
	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的Boolean值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * <p>
	 * 如果值为布尔值，直接返回。如果为字符串且等于'true'(忽略大小写)返回true,
	 * 否则返回false。如果为整型值0返回false，非0返回true。
	 * 其它情况全部返回指定的默认值。
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的Boolean值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:56:07
	 */
	public static <K> boolean getBooleanValue(final Map<K, ?> map, final K key, boolean defaultValue) {
    	return MapUtils.getBooleanValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的byte值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的byte值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> byte getByteValue(final Map<K, ?> map, final K key, byte defaultValue) {
    	return MapUtils.getByteValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的short值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的short值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> short getShortValue(final Map<K, ?> map, final K key, short defaultValue) {
    	return MapUtils.getShortValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的int值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的int值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> int getIntValue(final Map<K, ?> map, final K key, int defaultValue) {
    	return MapUtils.getIntValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的long值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的long值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> long getLongValue(final Map<K, ?> map, final K key, long defaultValue) {
    	return MapUtils.getLongValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的float值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的float值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> float getFloatValue(final Map<K, ?> map, final K key, float defaultValue) {
    	return MapUtils.getFloatValue(map, key, defaultValue);
    }

	/**
	 * <p>
	 * 从Map中取得指定key的对象，返回该对象的double值，如果转换失败返回指定的默认值
	 * </p>
	 * 
	 * @param map 要查找的Map，可以为null
	 * @param key 要查找的key
	 * @param defaultValue 对应key的值为null或转换失败时要返回的默认值
	 * @return map中key对应的值的double值，如果map为null或转换失败时返回指定的默认值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午10:58:31
	 */
	public static <K> double getDoubleValue(final Map<K, ?> map, final K key, double defaultValue) {
    	return MapUtils.getDoubleValue(map, key, defaultValue);
    }

	// Conversion methods
	// -------------------------------------------------------------------------
	/**
	 * <p>
	 * 将Map转为Properties，传入null将返回空的Properties对象
	 * </p>
	 * 
	 * @param map 要转化为Properties的Map, 可以为null
	 * @return Properties对象
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:27:46
	 */
	public static Properties toProperties(final Map<?, ?> map) {
    	return MapUtils.toProperties(map);
    }

	/**
	 * <p>
	 * 将ResourceBundle转为Map，传入null将返回空的Map对象
	 * </p>
	 * 
	 * @param resourceBundle 要转化为Map的ResourceBundle, 可以为null
	 * @return HashMap
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:31:39
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, ?> toMap(final ResourceBundle resourceBundle) {
		if(resourceBundle == null) {
			return Collections.EMPTY_MAP;
		}
    	return MapUtils.toMap(resourceBundle);
    }

	// Printing methods
	// -------------------------------------------------------------------------
	/**
	 * <p>
	 * 将指定的Map的内容分行打印
	 * </p>
	 * 
	 * <p>
	 * 该方法打印出Map的良好格式的字符串描述。
	 * 每个Map实体将打印出key和value。当值为Map时，该行为将递归。
	 * </p>
	 * 
	 * <p>
	 * 该方法不是线程安全的。您必须手动同步该类或请求的流。
	 * </p>
	 * 
	 * @param out 打印要输出的流, 不能为null
	 * @param label 要使用的标签, 可以为null. 为null该标签将不被输出. 它经常代表bean(或类似)的属性名
	 * @param map 要打印的map, 可以为null，如果为null将输出"null"
	 * @throws NullPointerException 如果stream参数为<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:39:24
	 */
	public static void verbosePrint(PrintStream out, Object label, Map<?, ?> map) {
		MapUtils.verbosePrint(out, label, map);
	}

	/**
	 * <p>
	 * 将指定的Map的内容分行打印
	 * </p>
	 * 
	 * <p>
	 * 该方法打印出Map的良好格式的字符串描述。
	 * 每个Map实体将打印出key、value和类名。当值为Map时，该行为将递归。
	 * </p>
	 * 
	 * <p>
	 * 该方法不是线程安全的。您必须手动同步该类或请求的流。
	 * </p>
	 * 
	 * @param out 打印要输出的流, 不能为null
	 * @param label 要使用的标签, 可以为null. 为null该标签将不被输出. 它经常代表bean(或类似)的属性名
	 * @param map 要打印的map, 可以为null，如果为null将输出"null"
	 * @throws NullPointerException 如果stream参数为<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-9 下午11:39:24
	 */
	public static void debugPrint(PrintStream out, Object label, Map<?, ?> map) {
		MapUtils.debugPrint(out, label, map);
	}

	// Misc
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 反转Map。返回一个指定Map的key和value对换过的新HashMap。
	 * </p>
	 * 
	 * <p>
	 * 该方法假设要反转的Map是定义良好的。如果输入的map有多个
	 * 相同值映射到不同key的实体，返回的map将只映射其中一个key
	 * 到该value，但是具体是哪一个key是不确定的。
	 * </p>
	 * 
	 * @param map 要反转的Map, 可以为null
	 * @return 一个包含反转后的数据的新HashMap，转入的map为null将返回空的HashMap
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午9:00:25
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<V, K> invertMap(Map<K, V> map) {
		if(map == null) {
			return new HashMap<V, K>(0);
		}
		return MapUtils.invertMap(map);
	}

	// -----------------------------------------------------------------------
	/**
	 * Protects against adding null values to a map.
	 * 
	 * <p>
	 * 该方法检查要添加到Map的值，如果为null将被替换为空串
	 * </p>
	 * 
	 * <p>
	 * 这个方法在map不能接受null值，或在从一个可能提交null或空串(它在map中保持原样)
	 * 的源接收数据时非常有用。
	 * map中的key不会被校验。
	 * <p>
	 * 
	 * @param map 要添加元素的Map, 可以为null，为null将不做任何操作
	 * @param key map的key
	 * @param value map的value，null将被转换为空串
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:04:59
	 */
	public static <K> void safeAddToMap(Map<K, ?> map, K key, Object value) {
		if(map != null) {
			MapUtils.safeAddToMap(map, key, value);	
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将指定数组中的所有key和value放到map中
	 * </p>
	 * 
	 * <p>
	 * 该方法是{@link java.util.Map#putAll(java.util.Map)}方法及Map的构造方法的另一种选择。
	 * 它允许您从一个各种可能类型的数组创建Map。
	 * </p>
	 * 
	 * <p>
	 * 如果数组中第一个元素实现{@link java.util.Map.Entry} 或 {@link KeyValue}，
	 * 那么key和value将从该元素添加到Map中。如果数组中第一个元素也是一个数组，
	 * 那么它假设子数组中0下标的元素为key，1下标的元素为value。
	 * 否则，输入的数组的元素将被间隔的当作key或value。
	 * </p>
	 * 
	 * <p>
	 * 例如，下面的代码创建一个颜色Map:
	 * 
	 * <pre>
	 * Map colorMap = MapUtils.putAll(new HashMap(), new String[][] { { &quot;RED&quot;, &quot;#FF0000&quot; }, { &quot;GREEN&quot;, &quot;#00FF00&quot; },
	 * 		{ &quot;BLUE&quot;, &quot;#0000FF&quot; } });
	 * </pre>
	 * 
	 * 或:
	 * 
	 * <pre>
	 * Map colorMap = MapUtils.putAll(new HashMap(), new String[] { &quot;RED&quot;, &quot;#FF0000&quot;, &quot;GREEN&quot;, &quot;#00FF00&quot;, &quot;BLUE&quot;, &quot;#0000FF&quot; });
	 * </pre>
	 * 
	 * 或:
	 * 
	 * <pre>
	 * Map colorMap = MapUtils.putAll(new HashMap(), new Map.Entry[] { new DefaultMapEntry(&quot;RED&quot;, &quot;#FF0000&quot;),
	 * 		new DefaultMapEntry(&quot;GREEN&quot;, &quot;#00FF00&quot;), new DefaultMapEntry(&quot;BLUE&quot;, &quot;#0000FF&quot;) });
	 * </pre>
	 * 
	 * @param map 要添加到的Map, 可以为null，为null将会新建一个LinkedHashMap
	 * @param array an array to populate from, null ignored
	 * @return 输入的map，当其为null时，该方法会新建一个LinkedHashMap
	 * @throws IllegalArgumentException 如果子数组或使用的匹配的实体的条目某一个项是无效的
	 * @throws ClassCastException 如果数组的内容混合的
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:30:33
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> putAll(Map<K, V> map, Object[] array) {
		if(map == null) {
			map = new LinkedHashMap<K, V>();
		}
		return MapUtils.putAll(map, array);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查指定的map是否为null或空
	 * </p>
	 * 
	 * @param map 待检查的Map, 可以为null
	 * @return true 指定的map为null或空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:34:33
	 */
	public static boolean isEmpty(Map<?, ?> map) {
		return MapUtils.isEmpty(map);
	}

	/**
	 * <p>
	 * 检查指定的map是否非null且非空
	 * </p>
	 * 
	 * @param map 待检查的Map, 可以为null
	 * @return true 指定的map为非null且非空
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:34:33
	 */
	public static boolean isNotEmpty(Map<?, ?> map) {
		return MapUtils.isNotEmpty(map);
	}

	// Map decorators
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定的Map的同步Map
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * Map m = MapUtils.synchronizedMap(myMap);
	 * Set s = m.keySet(); // 同步块之外
	 * synchronized (m) { // 同步Map
	 * 	Iterator i = s.iterator();
	 * 	while (i.hasNext()) {
	 * 		process(i.next());
	 * 	}
	 * }
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link java.util.Collections}的实现。
	 * </p>
	 * 
	 * @param map 要同步的Map, 不能为null
	 * @return 同步的Map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:44:23
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> synchronizedMap(Map<K, V> map) {
		return MapUtils.synchronizedMap(map);
	}

	/**
	 * <p>
	 * 返回一个不可修改的Map
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param map 要置为不可修改的Map, 不能为null
	 * @return 一个不可修改的Map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:48:34
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map) {
		return MapUtils.unmodifiableMap(map);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的Map
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的Map中. 试图添加一个无效的对象将产生IllegalArgumentException异常. <br>
	 * 更为重要的是, 在调用该方法后, 不要使用原来的Map, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param map 要检查的Map, 不能为null
	 * @param keyPred 使用的key条件, null意味着不检查
	 * @param valuePred 使用的value条件, null意味着不检查
	 * @return 满足指定条件的Map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:52:13
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> predicatedMap(Map<K, V> map, Predicate keyPred, Predicate valuePred) {
		return MapUtils.predicatedMap(map, keyPred, valuePred);
	}

	/**
	 * <p>
	 * 返回指定类型的Map
	 * </p>
	 * 
	 * <p>
	 * 只有为指定Key类型和Value类型的实体会被添加到返回的Map中.
	 * </p>
	 * 
	 * @param map 要限制类型的Map, 不能为null
	 * @param keyType 允许添加到Map的key的类型, 不能为null
	 * @param valueType 允许添加到Map的value的类型, 不能为null
	 * @return 满足指定类型的实体组成的Map
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午10:55:58
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> typedMap(Map<?, ?> map, Class<K> keyType, Class<V> valueType) {
		return MapUtils.typedMap(map, keyType, valueType);
	}

	/**
	 * <p>
	 * 转换给定的Map
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个将转换任何添加到它中去的新实体的新Map(装饰传入的Map)。
	 * 原Map中存在的实体不会被转换，如果您需要这样的行为，请查看{@link TransformedMap#decorateTransform}.
	 * </p>
	 * 
	 * <p>
	 * 每一个通过转换器的元素都将添加到返回的Map中. 更为重要的是, 在调用该方法后, 
	 * 不要使用原来的Map, 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * <p>
	 * 如果Map中的元素已经是被装饰过的，那么它们将不会被转换。
	 * </p>
	 * 
	 * @param map 要转换的Map, 不能为null，通常为空
	 * @param keyTransformer 用来转换key的转换器, null意味着不转换
	 * @param valueTransformer 用来转换value的转换器, null意味着不转换
	 * @return 转换后的Map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-10 下午11:07:50
	 */
	@SuppressWarnings("rawtypes")
	public static Map transformedMap(Map<?, ?> map, Transformer keyTransformer, Transformer valueTransformer) {
		return MapUtils.transformedMap(map, keyTransformer, valueTransformer);
	}

	/**
	 * <p>
	 * 返回给定Map的一个固定大小的Map。
	 * Map不能移除和添加元素， 但是已经存在Map中的元素可以被改变(例如，
	 * 通过{@link Map#put(Object,Object)}方法)
	 * </p>
	 * 
	 * @param list 要固定大小的Map, 不能为null
	 * @return 给定Map的一个固定大小的Map
	 * @throws IllegalArgumentException 如果指定的Map为null
	 * @throws UnsupportedOperationException 如果试图从返回的Map中添加或移除元素
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 上午10:31:26
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> fixedSizeMap(Map<K, V> map) {
		return MapUtils.fixedSizeMap(map);
	}

	/**
	 * <p>
	 * 返回一个"懒惰"的Map， 它的值将被按需加载
	 * </p>
	 * 
	 * <p>
	 * 当传给返回的map的{@link Map#get(Object)}方法的key未在map中出现时，
	 * 指定的工厂将创建一个新对象， 并将key和该对象放入Map
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * 
	 * <pre>
	 * Factory factory = new Factory() {
	 *     public Object create() {
	 *         return new Date();
	 *     }
	 * }
	 * Map lazyMap = MapUtils.lazyMap(new HashMap(), factory);
	 * Object obj = lazyMap.get("test");
	 * </pre>
	 * 
	 * 当上面的代码被执行时，<code>obj</code>将包含一个新的<code>Date</code>实例。
	 * 而且， 这个<code>Date</code>实例为Map中key为 <code>"test"</code>关联的对象。
	 * </p>
	 * 
	 * @param map 要设置为"懒惰"的map, 不能为null
	 * @param factory 创建新对象的工厂, 不能为null
	 * @return 指定Map的"懒惰"map
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 上午10:38:14
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> lazyMap(Map<K, V> map, Factory factory) {
		return MapUtils.lazyMap(map, factory);
	}

	/**
	 * <p>
	 * 返回一个"懒惰"的Map， 它的值将被按需加载
	 * </p>
	 * 
	 * <p>
	 * 当传给返回的map的{@link Map#get(Object)}方法的key未在map中出现时，
	 * 指定的工厂将创建一个新对象， 并将key和该对象放入Map.
	 * 这里的工厂是一个{@link Transformer}， 它被传入要转换为value的key。
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * 
	 * <pre>
	 * Transformer factory = new Transformer() {
	 *     public Object transform(Object mapKey) {
	 *         return new File(mapKey);
	 *     }
	 * }
	 * Map lazyMap = MapUtils.lazyMap(new HashMap(), factory);
	 * Object obj = lazyMap.get("C:/dev");
	 * </pre>
	 * 
	 * 当上面的代码被执行时，<code>obj</code>将包含一个新的<code>File</code>实例。
	 * 而且， 这个<code>File</code>实例为Map中key为 <code>"C:/dev"</code>关联的对象。
	 * <p>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 如果一个"懒惰"的map被一个同步的map包装，结果将是一个简单的缓存。
	 * 当要获取的对象不在缓存中时，缓存将自己调用转换工厂来创建该对象，
	 * 所有操作都是在同步块中完成的。
	 * </p>
	 * 
	 * @param map 要设置为"懒惰"的map, 不能为null
	 * @param transformerFactory 创建新对象的工厂, 不能为null
	 * @return 指定Map的"懒惰"map
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午12:53:19
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> lazyMap(Map<K, V> map, Transformer transformerFactory) {
		return MapUtils.lazyMap(map, transformerFactory);
	}

	/**
	 * <p>
	 * 返回给定Map的一个维护key顺序(自然顺序)的新Map
	 * </p>
	 * 
	 * <p>
	 * 如果一个key被添加两次，顺序决定于第一次。
	 * 顺序可通过keySet, value和entrySet观察。
	 * </p>
	 * 
	 * @param set 要排序的Map, 不能为null
	 * @return 排过序的Map
	 * @throws IllegalArgumentException 如果指定的Map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午6:02:22
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> orderedMap(Map<K, V> map) {
		return MapUtils.orderedMap(map);
	}

	/**
	 * <p>
	 * 创建一个多值的map，它支持将单值加入(通过调用Map.put(key, value))到key对应的集合中
	 * </p>
	 * 
	 * @param map 要装饰的Map
	 * @return 一个多值的Map，它的value的类型为ArrayList
	 * @see MultiValueMap
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午6:25:10
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, ArrayList<V>> multiValueMap(Map<K, ? extends Collection<? extends V>> map) {
		return MapUtils.multiValueMap(map);
	}
	
	/**
	 * <p>
	 * 创建一个多值的map，它支持将单值加入(通过调用Map.put(key, value))到key对应的集合中
	 * </p>
	 * 
	 * @param map 要装饰的Map
	 * @param collectionClass 返回的map的值的类型(必须包含public的无参构造器并且实现Collection接口) 
	 * @return 一个多值的Map，它的value的类型为指定的collectionClass
	 * @see MultiValueMap
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午7:09:37
	 */
	@SuppressWarnings("unchecked")
	public static <K, V, C extends Collection<V>> Map<K, C> multiValueMap(Map<K, ? extends Collection<? extends V>> map, Class<C> collectionClass) {
		return MapUtils.multiValueMap(map, collectionClass);
	}
	
	/**
	 * <p>
	 * 创建一个多值的map，它支持将单值加入(通过调用Map.put(key, value))到key对应的集合中
	 * 集合将由指定的集合工厂创建
	 * </p>
	 * 
	 * @param map 要装饰的Map
	 * @param collectionFactory 创建返回的Map的值的集合的工厂
	 * @return 一个多值的Map，它的value的类型为指定的集合工厂创建的集合
	 * @see MultiValueMap
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午7:32:57
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, ? extends Collection<V>> multiValueMap(Map<K, ? extends Collection<V>> map, Factory collectionFactory) {
		return MapUtils.multiValueMap(map, collectionFactory);
	}
	
	// SortedMap decorators
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定的Map的同步有序Map
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * Map m = MapUtils.synchronizedSortedMap(myMap);
	 * Set s = m.keySet(); // outside synchronized block
	 * synchronized (m) { // synchronized on MAP!
	 * 	Iterator i = s.iterator();
	 * 	while (i.hasNext()) {
	 * 		process(i.next());
	 * 	}
	 * }
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link java.util.Collections}的实现.
	 * </p>
	 * 
	 * @param map 要同步的map, 不能为null
	 * @return 指定map的同步map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午7:45:30
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> synchronizedSortedMap(SortedMap<K, V> map) {
		return MapUtils.synchronizedSortedMap(map);
	}

	/**
	 * <p>
	 * 返回一个不可修改的有序Map
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param map 要转换为不可修改的有序Map, 不能为null
	 * @return 一个不可修改的有序Map
	 * @throws IllegalArgumentException 如果指定的map为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午7:48:05
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> unmodifiableSortedMap(SortedMap<K, V> map) {
		return MapUtils.unmodifiableSortedMap(map);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的有序Map
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的Map中. 试图添加一个无效的对象将产生IllegalArgumentException异常. <br>
	 * 更为重要的是, 在调用该方法后, 不要使用原来的Map, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param set 要检查的有序Map, 不能为null
	 * @param keyPred 使用的key查找条件, 不能为null
	 * @param valuePred 使用的value查找条件, 不能为null
	 * @return 满足指定条件的有序Map
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午8:08:53
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> predicatedSortedMap(SortedMap<K, V> map, Predicate keyPred, Predicate valuePred) {
		return MapUtils.predicatedSortedMap(map, keyPred, valuePred);
	}

	/**
	 * <p>
	 * 返回指定key类型和value类型的有序Map
	 * </p>
	 * 
	 * <p>
	 * 只有指定key类型和value类型的实体会被添加到返回的有序Map中.
	 * </p>
	 * 
	 * @param map 要限制类型的有序Map, 不能为null
	 * @param keyType 可以添加到返回的map中的key的类型, 不能为null
	 * @param valueType 可以添加到返回的map中的value的类型, 不能为null
	 * @return 指定key类型和value类型的有序Map
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午9:38:11
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> typedSortedMap(SortedMap<?, ?> map, Class<K> keyType, Class<V> valueType) {
		return MapUtils.typedSortedMap(map, keyType, valueType);
	}

	/**
	 * <p>
	 * 转换给定的Map
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的有序map(装饰指定的map)， 它将转换任何新添加到该map的实体。
	 * 已经存在的实体不会被转换，如果您想要此行为，请参考{@link TransformedSortedMap#decorateTransform}.
	 * </p>
	 * 
	 * <p>
	 * 每一个添加到返回的Map中的实体都将被传给转换器. 更为重要的是, 在调用该方法后, 
	 * 不要使用原来的Map, 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * @param map 要被转换的Map, 不能为null
	 * @param keyTransformer 使用的key转换器, 不能为null
	 * @param keyTransformer 使用的value转换器, 不能为null
	 * @return 包含指定map的元素的新的具有转换功能的map
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午9:58:30
	 */
	@SuppressWarnings("rawtypes")
	public static SortedMap transformedSortedMap(SortedMap map, Transformer keyTransformer, Transformer valueTransformer) {
		return MapUtils.transformedSortedMap(map, keyTransformer, valueTransformer);
	}

	/**
	 * <p>
	 * 返回给定有序Map的一个固定大小的有序Map。
	 * 该有序map不能移除和添加元素， 但是已经存在map中的元素可以被改变(例如，
	 * 通过{@link Map#put(Object,Object)}方法)
	 * </p>
	 * 
	 * @param map 要固定大小的有序Map, 不能为null
	 * @return 给定列表的一个固定大小的有序Map
	 * @throws IllegalArgumentException 如果指定的有序Map为null
	 * @throws UnsupportedOperationException 如果试图从返回的有序Map中添加或移除元素
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:04:35
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> map) {
		return MapUtils.fixedSizeSortedMap(map);
	}

	/**
	 * <p>
	 * 返回一个"懒惰"的有序Map， 它的值将被按需加载
	 * </p>
	 * 
	 * <p>
	 * 当传给返回的map的{@link Map#get(Object)}方法的key未在map中出现时，
	 * 指定的工厂将创建一个新对象， 并将key和该对象放入Map
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * 
	 * <pre>
	 * Factory factory = new Factory() {
	 *     public Object create() {
	 *         return new Date();
	 *     }
	 * }
	 * SortedMap lazy = MapUtils.lazySortedMap(new TreeMap(), factory);
	 * Object obj = lazy.get("test");
	 * </pre>
	 * 
	 * 当上面的代码被执行时，<code>obj</code>将包含一个新的<code>Date</code>实例。
	 * 而且， 这个<code>Date</code>实例为Map中key为 <code>"test"</code>关联的对象。
	 * </p>
	 * 
	 * @param map 要设置为"懒惰"的有序map, 不能为null
	 * @param factory 创建新对象的工厂, 不能为null
	 * @return 指定Map的"懒惰"map
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:09:00
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> lazySortedMap(SortedMap<K, V> map, Factory factory) {
		return MapUtils.lazySortedMap(map, factory);
	}

	/**
	 * <p>
	 * 返回一个"懒惰"的有序Map， 它的值将被按需加载
	 * </p>
	 * 
	 * <p>
	 * 当传给返回的map的{@link Map#get(Object)}方法的key未在map中出现时，
	 * 指定的工厂将创建一个新对象， 并将key和该对象放入Map.
	 * 这里的工厂是一个{@link Transformer}， 它被传入要转换为value的key。
	 * </p>
	 * 
	 * <p>
	 * 例如:
	 * 
	 * <pre>
	 * Transformer factory = new Transformer() {
	 *     public Object transform(Object mapKey) {
	 *         return new File(mapKey);
	 *     }
	 * }
	 * SortedMap lazy = MapUtils.lazySortedMap(new TreeMap(), factory);
	 * Object obj = lazy.get("C:/dev");
	 * </pre>
	 * 
	 * 当上面的代码被执行时，<code>obj</code>将包含一个新的<code>File</code>实例。
	 * 而且， 这个<code>File</code>实例为Map中key为 <code>"C:/dev"</code>关联的对象。
	 * <p>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 如果一个"懒惰"的map被一个同步的map包装，结果将是一个简单的缓存。
	 * 当要获取的对象不在缓存中时，缓存将自己调用转换工厂来创建该对象，
	 * 所有操作都是在同步块中完成的。
	 * </p>
	 * 
	 * @param map 要设置为"懒惰"的有序map, 不能为null
	 * @param transformerFactory 创建新对象的工厂, 不能为null
	 * @return 指定Map的"懒惰"有序map
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-11 下午10:11:51
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> SortedMap<K, V> lazySortedMap(SortedMap<K, V> map, Transformer transformerFactory) {
		return MapUtils.lazySortedMap(map, transformerFactory);
	}
	
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.collections.MapUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	
}

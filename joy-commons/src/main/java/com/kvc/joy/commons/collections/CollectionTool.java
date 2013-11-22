package com.kvc.joy.commons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.BoundedCollection;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import com.kvc.joy.commons.bean.BeanTool;
import com.kvc.joy.commons.bean.IEntity;
import com.kvc.joy.commons.exception.SystemException;
import com.kvc.joy.commons.lang.ArrayTool;
import com.kvc.joy.commons.lang.reflect.MethodTool;
import com.kvc.joy.commons.lang.string.StringTool;

/**
 * Collection工具类
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class CollectionTool {

//	protected static final Log logger = LogFactory.getLog(CollectionTool.class);

	private CollectionTool() {
	}

	/**
	 * <p>
	 * 将实体容器转化成id和实体的Map
	 * </p>
	 * 
	 * @param entities 实体容器, 可以为null, 为null将返回空的map
	 * @return Map<id，实体对象>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @date 2012-5-28 下午02:12:54
	 */
	public static <E extends IEntity<T>, T> Map<T, E> toEntityMap(Collection<? extends E> entities) {
		if (isEmpty(entities)) {
			return new HashMap<T, E>(0);
		}
		Map<T, E> idMap = new LinkedHashMap<T, E>(entities.size());
		for (E entity : entities) {
			idMap.put(entity.getId(), entity);
		}
		return idMap;
	}

	/**
	 * <p>
	 * 将实体容器转化成指定属性和实体的Map
	 * </p>
	 * 
	 * @param entities 实体容器, 可以为null, 为null将返回空的map
	 * @param propertyName 属性名称, 不能为null
	 * @return Map<属性值，实体对象>
	 * @throws IllegalArgumentException 如果propertyName参数为空
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NoSuchMethodException 如果找不到指定的可访问的方法 <br>
	 * 						InvocationTargetException 对被调用方法的包装异常 <br>
	 * 						IllegalAccessException 如果请求的方法不能通过反射访问
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @date 2012-5-28 下午02:41:25
	 */
	public static <E> Map<Object, E> toEntityMap(Collection<? extends E> entities, String propertyName) {
		if (isEmpty(entities)) {
			return new HashMap<Object, E>(0);
		}
		if (StringTool.isBlank(propertyName)) {
			throw new IllegalArgumentException("propertyName参数不能为空!");
		}
		Map<Object, E> map = new LinkedHashMap<Object, E>(entities.size());
		for (E entity : entities) {
			Object value = MethodTool.invokeGetter(entity, propertyName);
			map.put(value, entity);
		}
		return map;
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 吸收并整理SpringSide项目Collections类的几个方法
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 提取容器中的对象的两个属性(通过Getter方法), 组合成Map.
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回空的map
	 * @param keyPropertyName 要提取为Map中的Key值的属性名, 不能为null
	 * @param valuePropertyName 要提取为Map中的Value值的属性名, 不能为null
	 * @return Map<参数keyPropertyName对应的值, 参数valuePropertyName对应的值>
	 * @throws IllegalArgumentException 如果两个属性名之一为空
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 *             NoSuchMethodException 如果找不到指定的可访问的方法 <br>
	 *             InvocationTargetException 对被调用方法的包装异常 <br>
	 *             IllegalAccessException 如果请求的方法不能通过反射访问
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:10:36
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map extractToMap(Collection<?> collection, String keyPropertyName, String valuePropertyName) {
		if (isEmpty(collection)) {
			return new HashMap(0);
		}
		if (StringTool.isBlank(keyPropertyName)) {
			throw new IllegalArgumentException("参数keyPropertyName不能为空!");
		}
		if (StringTool.isBlank(valuePropertyName)) {
			throw new IllegalArgumentException("参数valuePropertyName不能为空!");
		}

		Map map = new HashMap(collection.size());
		try {
			for (Object obj : collection) {
				map.put(PropertyUtils.getProperty(obj, keyPropertyName),
						PropertyUtils.getProperty(obj, valuePropertyName));
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		return map;
	}

	/**
	 * <p>
	 * 提取容器中的对象的一个属性(通过Getter方法), 组合成List.
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回空的List
	 * @param propertyName 要提取的属性名, 不能为null
	 * @return List<指定属性名的值>
	 * @throws IllegalArgumentException 如果属性名为空
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 *             NoSuchMethodException 如果找不到指定的可访问的方法 <br>
	 *             InvocationTargetException 对被调用方法的包装异常 <br>
	 *             IllegalAccessException 如果请求的方法不能通过反射访问
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:15:24
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List extractToList(Collection<?> collection, String propertyName) {
		if (isEmpty(collection)) {
			return new ArrayList(0);
		}
		if (StringTool.isBlank(propertyName)) {
			throw new IllegalArgumentException("参数propertyName不能为空!");
		}
		List list = new ArrayList(collection.size());

		try {
			for (Object obj : collection) {
				list.add(BeanTool.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw new SystemException(e);
		}

		return list;
	}

	/**
	 * <p>
	 * 提取容器中的对象的一个属性(通过Getter方法), 组合成由分割符分隔的字符串.
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回空串
	 * @param propertyName 要提取的属性名, 不能为null
	 * @param separator 分隔符, 可以为null, 为null将被当作空串
	 * @return 用分隔符连接的指定属性的各个toString值
	 * @throws IllegalArgumentException 如果属性名为空
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:22:23
	 */
	public static String extractToString(Collection<?> collection, String propertyName, String separator) {
		if (isEmpty(collection)) {
			return "";
		}
		if (StringTool.isBlank(propertyName)) {
			throw new IllegalArgumentException("参数propertyName不能为空!");
		}
		if (separator == null) {
			separator = "";
		}

		List<?> list = extractToList(collection, propertyName);
		return StringTool.join(list, separator);
	}

	/**
	 * <p>
	 * 转换Collection所有元素(通过toString())为String, 中间以separator分隔。
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回空串
	 * @param separator 分隔符, 可以为null, 为null将被当作空串
	 * @return 用分隔符连接的每个元素的toString值
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:25:42
	 */
	public static String convertToString(Collection<?> collection, String separator) {
		if (isEmpty(collection)) {
			return "";
		}
		if (separator == null) {
			separator = "";
		}
		return StringTool.join(collection, separator);
	}

	/**
	 * <p>
	 * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>mymessage</div>。
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回空串
	 * @param prefix 要添加的前缀, 可以为null, 为null将被当作空串
	 * @param postfix 要添加的后缀, 可以为null, 为null将被当作空串
	 * @return 加上前缀和后缀的每个元素的toString值的连接串
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:28:56
	 */
	public static String convertToString(Collection<?> collection, String prefix, String postfix) {
		if (isEmpty(collection)) {
			return "";
		}
		if (prefix == null) {
			prefix = "";
		}
		if (postfix == null) {
			postfix = "";
		}
		StringBuilder builder = new StringBuilder();
		for (Object o : collection) {
			builder.append(prefix).append(o).append(postfix);
		}
		return builder.toString();
	}

	/**
	 * <p>
	 * 取得Collection的第一个元素，如果collection为空返回null.
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回null
	 * @return 指定容器的第一个元素
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:30:45
	 */
	public static <T> T getFirst(Collection<? extends T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		return collection.iterator().next();
	}

	/**
	 * <p>
	 * 获取Collection的最后一个元素 ，如果collection为空返回null.
	 * </p>
	 * 
	 * @param collection 来源容器, 可以为null, 为null将返回null
	 * @return 指定容器的最后一个元素
	 * @author calvin
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午10:32:03
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getLast(Collection<? extends T> collection) {
		if (isEmpty(collection)) {
			return null;
		}

		// 当类型为List时，直接取得最后一个元素 。
		if (collection instanceof List) {
			List<T> list = (List<T>) collection;
			return list.get(list.size() - 1);
		}

		// 其他类型通过iterator滚动到最后一个元素.
		Iterator<T> iterator = (Iterator<T>) collection.iterator();
		while (true) {
			T current = iterator.next();
			if (!iterator.hasNext()) {
				return current;
			}
		}
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 吸收并整理SpringSide项目Collections类的几个方法
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.collections.CollectionUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 返回两个容器的并集 <br>
	 * 返回的容器的每个元素的基数为该元素在给定的两个容器中的最大基数
	 * </p>
	 * 
	 * @param a 第一个容器, 不能为null
	 * @param b 第二个容器, 不能为null
	 * @return 两个容器的并集
	 * @see Collection#addAll
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-27 下午8:56:31
	 */
	@SuppressWarnings("rawtypes")
	public static Collection union(Collection<?> a, Collection<?> b) {
		return CollectionUtils.union(a, b);
	}

	/**
	 * <p>
	 * 返回两个容器的交集 <br>
	 * 返回的容器的每个元素的基数为该元素在给定的两个容器中的最小基数
	 * </p>
	 * 
	 * @param a 第一个容器, 不能为null
	 * @param b 第二个容器, 不能为null
	 * @return 两个容器的交集
	 * @throws NullPointerException 如果任意参数为null
	 * @see Collection#retainAll
	 * @see #containsAny
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-27 下午8:59:58
	 */
	@SuppressWarnings("rawtypes")
	public static Collection intersection(Collection<?> a, Collection<?> b) {
		return CollectionUtils.intersection(a, b);
	}

	/**
	 * <p>
	 * 返回两个容器交集的补集
	 * </p>
	 * 
	 * <p>
	 * 返回的容器的第个元素的基数将等于 <tt>max(cardinality(<i>e</i>,<i>a</i>),cardinality(<i>e</i>,<i>b</i>)) 减去 
	 * 	min(cardinality(<i>e</i>,<i>a</i>),cardinality(<i>e</i>,<i>b</i>))</tt>
	 * </p>
	 * 
	 * <p>
	 * 该方法的结果相当于 <tt>{@link #subtract subtract}({@link #union union(a,b)},{@link #intersection intersection(a,b)})</tt>
	 * 或 <tt>{@link #union union}({@link #subtract subtract(a,b)},{@link #subtract subtract(b,a)})</tt>.
	 * </p>
	 * 
	 * @param a 第一个容器, 不能为null
	 * @param b 第二个容器, 不能为null
	 * @return 两个容器交集的补集
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午9:56:35
	 */
	@SuppressWarnings("rawtypes")
	public static Collection disjunction(Collection<?> a, Collection<?> b) {
		return CollectionUtils.disjunction(a, b);
	}

	/**
	 * <p>
	 * 返回两个容器的差集
	 * </p>
	 * 
	 * <p>
	 * 返回的容器的元素的基数将等于容器a的基数减去容器b的基数, 或0, 较大者为准
	 * </p>
	 * 
	 * @param a 被减的容器, 不能为空
	 * @param b 要减掉的容器, 不能为空
	 * @return 两个容器的差集
	 * @see Collection#removeAll
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:05:28
	 */
	@SuppressWarnings("rawtypes")
	public static Collection subtract(Collection<?> a, Collection<?> b) {
		return CollectionUtils.subtract(a, b);
	}

	/**
	 * <p>
	 * 检测两个容器的差集是否不为空
	 * </p>
	 * 
	 * @param coll1 第一个容器, 不能为null
	 * @param coll2 第二个容器, 不能为null
	 * @return <code>true</code> 如果两个容器的差集不为空
	 * @see #intersection
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:07:27
	 */
	public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
		return CollectionUtils.containsAny(coll1, coll2);
	}

	/**
	 * <p>
	 * 返回容器中每一个相同的元素出现的次数
	 * </p>
	 * 
	 * @param coll 要计算相同元素出现次数的容器, 不能为null
	 * @return Map<容器中的元素, 出现的次数>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:14:46
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<T, Integer> getCardinalityMap(Collection<? extends T> coll) {
		return CollectionUtils.getCardinalityMap(coll);
	}

	/**
	 * <p>
	 * 检测容器a是否为容器b的子集
	 * </p>
	 * 
	 * @param a 第一个容器(可能是子容器), 不能为空
	 * @param b 第二个容器(可能是父容器), 不能为空
	 * @return <code>true</code> 如果容器a是容器b的子集
	 * @see #isProperSubCollection
	 * @see Collection#containsAll
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:18:35
	 */
	public static boolean isSubCollection(Collection<?> a, Collection<?> b) {
		return CollectionUtils.isSubCollection(a, b);
	}

	/**
	 * <p>
	 * 检测容器a是否为容器b的真子集
	 * </p>
	 * 
	 * <p>
	 * 该实现假设
	 * <ul>
	 * <li><code>a.size()</code> 和 <code>b.size()</code> 代表容器 <i>a</i> 和 <i>b</i>的总个数</li>
	 * <li><code>a.size() < Integer.MAXVALUE</code></li>
	 * </ul>
	 * 
	 * @param a 第一个容器(可能是子容器), 不能为空
	 * @param b 第二个容器(可能是父容器), 不能为空
	 * @return <code>true</code> 如果容器a是容器b的真子集
	 * @see #isSubCollection
	 * @see Collection#containsAll
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:29:44
	 */
	public static boolean isProperSubCollection(Collection<?> a, Collection<?> b) {
		return CollectionUtils.isProperSubCollection(a, b);
	}

	/**
	 * <p>
	 * 检测两个容器的大小及其包含的元素是否全部相等
	 * </p>
	 * 
	 * 
	 * @param a 第一个容器, 不能为null
	 * @param b 第二个容器, 不能为null
	 * @return <code>true</code> 如果两个容器的大小及其包含的元素全部相等
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:32:45
	 */
	public static boolean isEqualCollection(Collection<?> a, Collection<?> b) {
		return CollectionUtils.isEqualCollection(a, b);
	}

	/**
	 * <p>
	 * 返回指定对象在容器中出现的次数
	 * </p>
	 * 
	 * @param obj 要查找的对象
	 * @param coll 被查找的容器
	 * @return 指定对象在容器中出现的次数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:33:52
	 */
	public static int cardinality(Object obj, Collection<?> coll) {
		return CollectionUtils.cardinality(obj, coll);
	}

	/**
	 * <p>
	 * 在容器中查找满足给定条件的第一个对象
	 * </p>
	 * 
	 * @param collection 要查找的容器, 可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @return 满足给定条件的第一个对象, 如果任意参数为null, 或没有找到, 将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:38:57
	 */
	@SuppressWarnings("unchecked")
	public static <T> T find(Collection<? extends T> collection, Predicate predicate) {
		return (T) CollectionUtils.find(collection, predicate);
	}

	/**
	 * <p>
	 * 对容器中的每个元素执行指定的操作
	 * </p>
	 * 
	 * <p>
	 * 如果任意参数为null, 将什么也没发生
	 * </p>
	 * 
	 * @param collection 要操作的容器, 可以为null
	 * @param closure 要执行的操作, 可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:43:10
	 */
	public static void forAllDo(Collection<?> collection, Closure closure) {
		CollectionUtils.forAllDo(collection, closure);
	}

	/**
	 * <p>
	 * 对容器中的每个元素应用条件进行过滤, 如果条件返回false, 从容器中移除对应的元素
	 * </p>
	 * 
	 * <p>
	 * 如果任意参数为null, 将什么也没发生
	 * </p>
	 * 
	 * @param collection 要操作的容器, 可以为null
	 * @param predicate 过滤条件, 可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:46:10
	 */
	public static void filter(Collection<?> collection, Predicate predicate) {
		CollectionUtils.filter(collection, predicate);
	}

	/**
	 * <p>
	 * 为容器的每个元素应用指定的转换器
	 * </p>
	 * 
	 * <p>
	 * 如果任意参数为null, 将什么也没发生
	 * </p>
	 * 
	 * <p>
	 * 该方法一般用于List, 因为它的set()方法被用作替换操作. 对于其它容器, clear() 和 addAll() 方法被用于替换元素.
	 * </p>
	 * 
	 * <p>
	 * 如果传入的容器控制着其输入, 如Set, 并且如果指定的转换器创建了重复的元素, 调用该方法后, 容器的大小将可能变小.
	 * </p>
	 * 
	 * @param collection 要操作的容器, 可以为null
	 * @param transformer 转换器, 可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:54:56
	 */
	public static void transform(Collection<?> collection, Transformer transformer) {
		CollectionUtils.transform(collection, transformer);
	}

	/**
	 * <p>
	 * 计算容器中满足指定条件的元素个数
	 * </p>
	 * 
	 * <p>
	 * 任意参数为null, 将返回0
	 * </p>
	 * 
	 * @param inputCollection 待检测的容器, 可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @return 器中满足指定条件的元素个数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:56:58
	 */
	public static int countMatches(Collection<?> inputCollection, Predicate predicate) {
		return CollectionUtils.countMatches(inputCollection, predicate);
	}

	/**
	 * <p>
	 * 检测容器中是否存在满足条件的元素
	 * </p>
	 * 
	 * <p>
	 * 任意参数为null, 将返回false.
	 * </p>
	 * 
	 * @param collection 待检测的容器, 可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @return true: 存在满足条件的元素
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午10:58:32
	 */
	public static boolean exists(Collection<?> collection, Predicate predicate) {
		return CollectionUtils.exists(collection, predicate);
	}

	/**
	 * <p>
	 * 返回容器中所有满足指定条件的元素
	 * </p>
	 * 
	 * <p>
	 * 条件参数为null将返回空容器
	 * </p>
	 * 
	 * @param inputCollection 待检测的容器, 不可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @return 一个包含匹配的元素的新容器
	 * @throws NullPointerException 如果输入的容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:00:57
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> select(Collection<? extends T> inputCollection, Predicate predicate) {
		return CollectionUtils.select(inputCollection, predicate);
	}

	/**
	 * <p>
	 * 将容器中所有满足指定条件的元素添加到输出容器中
	 * </p>
	 * 
	 * <p>
	 * 如果输入容器或条件为null, 将什么也不做
	 * </p>
	 * 
	 * @param inputCollection 输入的容器, 可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @param outputCollection 输出的容器, 不可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:03:20
	 */
	public static void select(Collection<?> inputCollection, Predicate predicate, Collection<?> outputCollection) {
		CollectionUtils.select(inputCollection, predicate, outputCollection);
	}

	/**
	 * <p>
	 * 返回容器中所有不满足指定条件的元素
	 * </p>
	 * 
	 * <p>
	 * 条件参数为null将返回空容器
	 * </p>
	 * 
	 * @param inputCollection 待检测的容器, 不可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @return 一个包含未匹配的元素的新容器
	 * @throws NullPointerException 如果输入的容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:05:47
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> selectRejected(Collection<? extends T> inputCollection, Predicate predicate) {
		return CollectionUtils.selectRejected(inputCollection, predicate);
	}

	/**
	 * <p>
	 * 将容器中所有不满足指定条件的元素添加到输出容器中
	 * </p>
	 * 
	 * <p>
	 * 如果输入容器或条件为null, 将什么也不做
	 * </p>
	 * 
	 * @param inputCollection 待检测的容器, 可以为null
	 * @param predicate 使用的条件, 可以为null
	 * @param outputCollection 输出的容器, 不可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:06:58
	 */
	public static void selectRejected(Collection<?> inputCollection, Predicate predicate, Collection<?> outputCollection) {
		CollectionUtils.selectRejected(inputCollection, predicate, outputCollection);
	}

	/**
	 * <p>
	 * 返回一个由输入容器经转换器转换后的元素所组成的新的容器
	 * </p>
	 * 
	 * <p>
	 * 转换器参数为null将返回空容器
	 * </p>
	 * 
	 * @param inputCollection 输入的容器, 不可以为null
	 * @param transformer 使用的转换器, 可以为null
	 * @return 转换后的新容器
	 * @throws NullPointerException 如果输入的容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:10:09
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Collection collect(Collection<?> inputCollection, Transformer transformer) {
		return CollectionUtils.collect(inputCollection, transformer);
	}

	/**
	 * <p>
	 * 返回一个由输入的迭代器经转换器转换后的元素所组成的容器
	 * </p>
	 * 
	 * <p>
	 * 转换器参数为null将返回空容器
	 * </p>
	 * 
	 * @param inputIterator 迭代器, 可以为null
	 * @param transformer 使用的转换器, 可以为null
	 * @return 转换后的容器
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:12:14
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Collection collect(Iterator<?> inputIterator, Transformer transformer) {
		return CollectionUtils.collect(inputIterator, transformer);
	}

	/**
	 * <p>
	 * 转换容器中的每一个元素, 并添加到指定的输出容器
	 * </p>
	 * 
	 * <p>
	 * 如果输入容器或转换器为null, 将什么也不做
	 * </p>
	 * 
	 * @param inputCollection 作为输入的容器, 可以为null
	 * @param transformer 使用的转换器, 可以为null
	 * @param outputCollection 输出的容器, 不可以为null
	 * @return 输出的容器
	 * @throws NullPointerException 如果输出容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:17:14
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> collect(Collection<?> inputCollection, Transformer transformer,
			Collection<? extends T> outputCollection) {
		return CollectionUtils.collect(inputCollection, transformer, outputCollection);
	}

	/**
	 * <p>
	 * 转换迭代器中的每一个元素, 并添加到指定的输出容器
	 * </p>
	 * 
	 * <p>
	 * 如果输入迭代器或转换器为null, 将什么也不做
	 * </p>
	 * 
	 * @param inputIterator 作为输入的迭代器, 可以为null
	 * @param transformer 使用的转换器, 可以为null
	 * @param outputCollection 输出的容器, 不可以为null
	 * @return 输出的容器
	 * @throws NullPointerException 如果输出容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:18:40
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> collect(Iterator<?> inputIterator, Transformer transformer,
			Collection<? extends T> outputCollection) {
		return CollectionUtils.collect(inputIterator, transformer, outputCollection);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 添加一个元素到指定的容器, 除非该元素为null
	 * </p>
	 * 
	 * @param collection 被添加的容器, 不能为null
	 * @param object 要添加的元素, 如果为null将不会被添加
	 * @return true: 如果容器有被改变
	 * @throws NullPointerException 如果容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:20:58
	 */
	public static <T> boolean addIgnoreNull(Collection<? extends T> collection, T object) {
		return CollectionUtils.addIgnoreNull(collection, object);
	}

	/**
	 * <p>
	 * 将迭代器中的所有元素添加到指定容器
	 * </p>
	 * 
	 * @param collection 要添加到的目标容器, 不能为null
	 * @param iterator 要添加的元素的迭代器, 不能为null
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:26:40
	 */
	public static <T> void addAll(Collection<? extends T> collection, Iterator<T> iterator) {
		CollectionUtils.addAll(collection, iterator);
	}

	/**
	 * <p>
	 * 添加枚举中所有元素到指定的容器
	 * </p>
	 * 
	 * @param collection 要添加到的目标容器, 不能为null
	 * @param enumeration 要添加的元素的枚举, 不能为null
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:28:22
	 */
	public static <T> void addAll(Collection<? extends T> collection, Enumeration<T> enumeration) {
		CollectionUtils.addAll(collection, enumeration);
	}

	/**
	 * <p>
	 * 添加数组中所有元素到指定的容器
	 * </p>
	 * 
	 * @param collection 要添加到的目标容器, 不能为null
	 * @param elements 要添加的元素的数组, 可以为null， 为null将什么也不做
	 * @throws NullPointerException 如果容器参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:29:03
	 */
	public static <T> void addAll(Collection<? extends T> collection, T... elements) {
		if(ArrayTool.isNotEmpty(elements)) {
			CollectionUtils.addAll(collection, elements);
		}
	}

	/**
	 * <p>
	 * 返回指定下标的元素. 如果没有对应的元素存在将抛<code>IndexOutOfBoundsException</code>异常, 如果指定对象不支持通过下标索引元素的操作时, 将抛
	 * <code>IllegalArgumentException</code>异常
	 * </p>
	 * 
	 * <p>
	 * 支持的类型及其相关语法如下:
	 * <ul>
	 * <li>Map -- 返回的值为map的<code>entrySet</code>迭代器中指定位置上的<code>Map.Entry, 如果存在这样的元素的话</code></li>
	 * <li>List -- 与list的get()方法相同</li>
	 * <li>Array -- 返回指定下标对应位置上的元素, 如果存在这样的元素的话; 否则将抛<code>IndexOutOfBoundsException</code>异常.</li>
	 * <li>Collection -- 返回容器默认迭代器对应位置上的元素, 如果存在这样的元素的话</li>
	 * <li>Iterator or Enumeration -- 返回容器默认迭代器或枚举对应位置上的元素, 如果存在这样的元素的话.</li>
	 * </ul>
	 * 
	 * @param object 要获取的值所在的对象
	 * @param index 下标
	 * @return 指定下标的对象
	 * @throws IndexOutOfBoundsException 如果下标非法
	 * @throws IllegalArgumentException 如果对象的类型不支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:42:50
	 */
	public static Object get(Object object, int index) {
		return CollectionUtils.get(object, index);
	}

	/**
	 * <p>
	 * 取得容器的大小
	 * </p>
	 * 
	 * <p>
	 * 该方法支持的对象类型如下:
	 * <ul>
	 * <li>Collection - 容器的大小
	 * <li>Map - map的大小
	 * <li>Array - 数组的大小
	 * <li>Iterator - 迭代器中的无数个数
	 * <li>Enumeration - 枚举中的无数个数
	 * </ul>
	 * 
	 * @param object 要获取大小的对象, 不能为null
	 * @return 指定对象的大小
	 * @throws IllegalArgumentException 如果对象的类型不支持或对象为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:45:04
	 */
	public static int size(Object object) {
		return CollectionUtils.size(object);
	}

	/**
	 * <p>
	 * 检查指定的容器是否为空
	 * </p>
	 * 
	 * <p>
	 * 该方法支持的对象类型如下:
	 * <ul>
	 * <li>Collection - 通过容器的isEmpty方法
	 * <li>Map - 通过map的isEmpty方法
	 * <li>Array - 通过数组的length属性
	 * <li>Iterator - 通过hasNext方法
	 * <li>Enumeration - 通过hasMoreElements方法
	 * </ul>
	 * 
	 * <p>
	 * 注意: 该方法这样命名是为了不与 {@link #isEmpty(Collection)} 方法冲突.
	 * </p>
	 * 
	 * @param object 要获取大小的对象, 不能为null
	 * @return true: 如果为空
	 * @throws IllegalArgumentException 如果对象的类型不支持或对象为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:49:42
	 */
	public static boolean sizeIsEmpty(Object object) {
		return CollectionUtils.sizeIsEmpty(object);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检测容器是否为空, null安全
	 * </p>
	 * 
	 * <p>
	 * null将返回true
	 * </p>
	 * 
	 * @param coll 待检查的容器, 可以为null
	 * @return true: 如果为空或null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:51:28
	 */
	public static boolean isEmpty(Collection<?> coll) {
		return CollectionUtils.isEmpty(coll);
	}

	/**
	 * <p>
	 * 检测容器是否非空, null安全
	 * </p>
	 * 
	 * <p>
	 * null将返回false
	 * </p>
	 * 
	 * @param coll 待检查的容器, 可以为null
	 * @return true: 如果非空或非null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:52:17
	 */
	public static boolean isNotEmpty(Collection<?> coll) {
		return CollectionUtils.isNotEmpty(coll);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回数组
	 * </p>
	 * 
	 * @param array 待反转的数组(调用后本身会被反转)，可以为null，为null将什么也不做
	 * @return 反转后的数组(与调用后的参数同一数组)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 上午11:52:53
	 */
	public static Object[] reverseArray(Object[] array) {
		if(ArrayTool.isNotEmpty(array)) {
			CollectionUtils.reverseArray(array);	
		}
		return array;
	}

	/**
	 * <p>
	 * 检测容器是否已满(不能再添加元素)
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link BoundedCollection}接口来确定容器是否已满. 如果容器未实现该接口, 将返回false.
	 * </p>
	 * 
	 * <p>
	 * 容器一不定要直接实现该接口. 如果容器已经被装饰器子包修饰, 那么这些将被移除以访问BoundedCollection.
	 * </p>
	 * 
	 * @param coll 要检查的容器, 不能为null
	 * @return true: 如果容器已满
	 * @throws NullPointerException 如果容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:00:39
	 */
	public static boolean isFull(Collection<?> coll) {
		return CollectionUtils.isFull(coll);
	}

	/**
	 * <p>
	 * 返回容器能容纳的最大元素个数
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link BoundedCollection}接口来确定容器大小. 如果容器未实现该接口, 将返回-1.
	 * </p>
	 * 
	 * <p>
	 * 容器一不定要直接实现该接口. 如果容器已经被装饰器子包修饰, 那么这些将被移除以访问BoundedCollection.
	 * </p>
	 * 
	 * @param coll 要检查的容器, 不能为null
	 * @return 容器能容纳的最大元素个数, 如果没有返回-1
	 * @throws NullPointerException 如果容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:03:42
	 */
	public static int maxSize(Collection<?> coll) {
		return CollectionUtils.maxSize(coll);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回一个容器, 它的元素存在于<code>collection</code>中同时也在<code>retain</code>中. 返回的容器的元素的基数与<code>collection</code>元素的基数一样, 除非
	 * <code>retain</code>没有包含这个元素, 此时, 基数为0. 该方法在你想更改一个容器但又不能调用该容器的retainAll(retain)方法时非常有用.
	 * </p>
	 * 
	 * @param collection 其内容为#retailAll操作的目标的容器
	 * @param retain 返回的容器中可能包含的元素的容器
	 * @return 一个由所有在<code>collection</code>中同时至少出现一次在<code>retain</code>的元素组成的容器
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:15:15
	 */
	public static Collection<?> retainAll(Collection<?> collection, Collection<?> retain) {
		return CollectionUtils.retainAll(collection, retain);
	}

	/**
	 * <p>
	 * 从<code>collection</code>中移除<code>remove</code>中的元素. 也就是说, 该方法返回一个由不在<code>remove</code>中元素组成的容器. 
	 * 返回的容器的元素的基数与<code>collection</code>元素的基数一样, 除非<code>remove</code>包含这个元素, 此时, 基数为0.
	 * 该方法在你想更改一个容器但又不能调用该容器的removeAll(retain)方法时非常有用.
	 * </p>
	 * 
	 * @param collection 要被移除元素的容器
	 * @param remove 要移除的元素的容器
	 * @return 一个由<code>collection</code>容器中所有除了在<code>remove</code>出现的元素组成的容器
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:21:29
	 */
	@SuppressWarnings("rawtypes")
	public static Collection removeAll(Collection<?> collection, Collection<?> remove) {
		return CollectionUtils.removeAll(collection, remove);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定的容器的同步容器
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * Collection c = CollectionUtils.synchronizedCollection(myCollection);
	 * synchronized (c) {
	 * 	Iterator i = c.iterator();
	 * 	while (i.hasNext()) {
	 * 		process(i.next());
	 * 	}
	 * }
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param collection 要同步的容器, 不能为null
	 * @return 同步的容器
	 * @throws IllegalArgumentException 如果容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:25:11
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> synchronizedCollection(Collection<? extends T> collection) {
		return CollectionUtils.synchronizedCollection(collection);
	}

	/**
	 * <p>
	 * 返回一个不可修改的容器
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param collection 要置为不可修改的容器, 不能为null
	 * @return 一个不可修改的容器
	 * @throws IllegalArgumentException 如果容器为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:26:59
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> unmodifiableCollection(Collection<? extends T> collection) {
		return CollectionUtils.unmodifiableCollection(collection);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的容器
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的容器中. 试图添加一个无效的对象将产生IllegalArgumentException异常. 更为重要的是, 在调用该方法后, 不要使用原来的容器, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param collection 要检查的容器, 不能为null
	 * @param predicate 使用的条件, 不能为null
	 * @return 满足指定条件的容器
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:32:23
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> predicatedCollection(Collection<? extends T> collection, Predicate predicate) {
		return CollectionUtils.predicatedCollection(collection, predicate);
	}

	/**
	 * <p>
	 * 返回指定类型的容器
	 * </p>
	 * 
	 * <p>
	 * 只有指定类型的对象会被添加到返回的容器中.
	 * </p>
	 * 
	 * @param collection 要限制类型的容器, 不能为null
	 * @param type 返回容器的元素类型
	 * @return 指定类型的容器
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:35:38
	 */
	@SuppressWarnings("unchecked")
	public static <T> Collection<T> typedCollection(Collection<?> collection, Class<T> type) {
		return CollectionUtils.typedCollection(collection, type);
	}

	/**
	 * <p>
	 * 转换给定的容器
	 * </p>
	 * 
	 * <p>
	 * 
	 * 每一个新添加到容器中的元素都将被传递给转换器进行转换. 更为重要的是, 在调用该方法后, 不要使用原来的容器, 
	 * 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * @param collection 要被转换的容器, 不能为null
	 * @param transformer 使用的转换器, 不能为null
	 * @return 转换后的容器
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午12:38:59
	 */
	@SuppressWarnings("rawtypes")
	public static Collection transformedCollection(Collection<?> collection, Transformer transformer) {
		return CollectionUtils.transformedCollection(collection, transformer);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.collections.CollectionUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
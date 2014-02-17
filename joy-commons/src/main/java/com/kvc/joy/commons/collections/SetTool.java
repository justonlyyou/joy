package com.kvc.joy.commons.collections;

import com.kvc.joy.commons.lang.ArrayTool;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.SetUtils;
import org.apache.commons.collections.Transformer;

import java.util.*;

/**
 * Set工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-6 上午10:12:53
 */
public class SetTool {

	private SetTool() {
	}
	
	/**
	 * <p>
	 * 根据指定的可变数组，创建HashSet
	 * </p>
	 * 
	 * @param elems 可变数组，可以为null, 为null将返回空的集合
	 * @return 由可变数组元素组成的HashSet
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-7 下午9:29:08
	 */
	public static <T> Set<T> newHashSet(T... elems) {
		if(ArrayTool.isEmpty(elems)) {
			return new HashSet<T>(0); 
		}
		Set<T> set = new HashSet<T>(elems.length);
        Collections.addAll(set, elems);
		return set;
	}
	
	/**
	 * <p>
	 * 根据指定的可变数组，创建LinkedHashSet
	 * </p>
	 * 
	 * @param elems 可变数组，可以为null, 为null将返回空的集合
	 * @return 由可变数组元素组成的LinkedHashSet
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-7 下午9:32:08
	 */
	public static <T> Set<T> newLinkedHashSet(T... elems) {
		if(ArrayTool.isEmpty(elems)) {
			return new LinkedHashSet<T>(0); 
		}
		Set<T> set = new LinkedHashSet<T>(elems.length);
        Collections.addAll(set, elems);
		return set;
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.collections.SetUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 测试两个集合是否相等， 包括包含的元素和集合大小
	 * </p>
	 * 
	 * <p>
	 * 该方法对于不能通过继承AbstractSet类实现<code>Set</code>时非常有用。
	 * 该方法参数类型为Collection是为了确保其它容器类型能够使用集合的实现算法。
	 * <p>
	 * 该方法比较两个集合元素的相等性。当且仅当两个集合的大小相等，并且第一个集合中的元素在
	 * 第二个集合中都存在. 该定义确保equals方法在同<tt>Set</tt>的实现中都能正确工作。
	 * </p>
	 * 
	 * <p>
	 * 该实现首先检查两个集合是否为相同对象，如果是返回true；
	 * 然后检查它们的大小是否相等，如果不是返回false，如果相等返回a.containsAll((Collection) b)的结果
	 * </p>
	 * 
	 * @see java.util.Set
	 * @param set1 第一个集合, 可以为null
	 * @param set2 第二个集合, 可以为null
	 * @return 是否两个集合相等
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:08:11
	 */
	public static boolean isEqualSet(Collection<?> set1, Collection<?> set2) {
		return SetUtils.isEqualSet(set1, set2);
	}

	/**
	 * <p>
	 * 为指定集合生成哈希值(使用{@link java.util.Set#hashCode()}指定的算法)
	 * </p>
	 * 
	 * <p>
	 * 该方法对于不能通过继承AbstractSet类实现<code>Set</code>时非常有用。
	 * 该方法参数类型为Collection是为了确保其它容器类型能够使用集合的实现算法。
	 * </p>
	 * 
	 * @see java.util.Set#hashCode()
	 * @param set 要生成哈希值的集合， 可以为null
	 * @return 指定集合的哈希值，如果集合为null将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:09:30
	 */
	public static int hashCodeForSet(Collection<?> set) {
		return SetUtils.hashCodeForSet(set);
	}

	/**
	 * <p>
	 * 返回指定的集合的同步集合
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * Set s = SetUtils.synchronizedSet(mySet);
	 * synchronized (s) {
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
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param set 要同步的集合, 不能为null
	 * @return 同步的集合
	 * @throws IllegalArgumentException 如果集合为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:13:15
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> synchronizedSet(Set<? extends T> set) {
		return SetUtils.synchronizedSet(set);
	}

	/**
	 * <p>
	 * 返回一个不可修改的集合
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param set 要置为不可修改的集合, 不能为null
	 * @return 一个不可修改的集合
	 * @throws IllegalArgumentException 如果集合为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:13:24
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> unmodifiableSet(Set<? extends T> set) {
		return SetUtils.unmodifiableSet(set);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的集合
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的集合中. 试图添加一个无效的对象将产生IllegalArgumentException异常. <br>
	 * 更为重要的是, 在调用该方法后, 不要使用原来的集合, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param set 要检查的集合, 不能为null
	 * @param predicate 使用的条件, 不能为null
	 * @return 满足指定条件的集合
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:14:42
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> predicatedSet(Set<? extends T> set, Predicate predicate) {
		return SetUtils.predicatedSet(set, predicate);
	}

	/**
	 * <p>
	 * 返回指定类型的集合
	 * </p>
	 * 
	 * <p>
	 * 只有指定类型的对象会被添加到返回的集合中.
	 * </p>
	 * 
	 * @param set 要限制类型的集合, 不能为null
	 * @param type 返回集合的元素类型
	 * @return 指定类型的集合
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:16:52
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> typedSet(Set<?> set, Class<T> type) {
		return SetUtils.typedSet(set, type);
	}

	/**
	 * <p>
	 * 转换给定的集合
	 * </p>
	 * 
	 * <p>
	 * 每一个新添加到集合中的元素都将被传递给转换器进行转换. 更为重要的是, 在调用该方法后, 
	 * 不要使用原来的集合, 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * @param list 要被转换的集合, 不能为null
	 * @param transformer 使用的转换器, 不能为null
	 * @return 转换后的集合
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:18:08
	 */
	@SuppressWarnings("rawtypes")
	public static Set transformedSet(Set<?> set, Transformer transformer) {
		return SetUtils.transformedSet(set, transformer);
	}

	/**
	 * <p>
	 * 返回给定集合的一个维护元素顺序(自然顺序)的新集合
	 * </p>
	 * 
	 * <p>
	 * 如果一个元素被添加两次，顺序决定于第一次。
	 * 顺序可通过迭代器或toArray观察。
	 * </p>
	 * 
	 * @param set 要排序的集合, 不能为null
	 * @return 排过序的集合
	 * @throws IllegalArgumentException 如果指定的集合为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:30:58
	 */
	@SuppressWarnings("unchecked")
	public static <T> Set<T> orderedSet(Set<? extends T> set) {
		return SetUtils.orderedSet(set);
	}
	
	// -----------------------------------------------------------------------
	
	/**
	 * <p>
	 * 返回指定的集合的同步有序集合
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * SortedSet s = SetUtils.synchronizedSortedSet(mySet);
	 * synchronized (s) {
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
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param set 要同步的有序集合, 不能为null
	 * @return 同步的有序集合
	 * @throws IllegalArgumentException 如果集合为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:32:54
	 */
	@SuppressWarnings("unchecked")
	public static <T> SortedSet<T> synchronizedSortedSet(SortedSet<? extends T> set) {
		return SetUtils.synchronizedSortedSet(set);
	}

	/**
	 * <p>
	 * 返回一个不可修改的有序集合
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param set 要置为不可修改的有序集合, 不能为null
	 * @return 一个不可修改的有序集合
	 * @throws IllegalArgumentException 如果集合为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:35:10
	 */
	@SuppressWarnings("unchecked")
	public static <T> SortedSet<T> unmodifiableSortedSet(SortedSet<? extends T> set) {
		return SetUtils.unmodifiableSortedSet(set);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的有序集合
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的集合中. 试图添加一个无效的对象将产生IllegalArgumentException异常. <br>
	 * 更为重要的是, 在调用该方法后, 不要使用原来的集合, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param set 要检查的有序集合, 不能为null
	 * @param predicate 使用的条件, 不能为null
	 * @return 满足指定条件的有序集合
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:35:16
	 */
	@SuppressWarnings("unchecked")
	public static <T> SortedSet<T> predicatedSortedSet(SortedSet<? extends T> set, Predicate predicate) {
		return SetUtils.predicatedSortedSet(set, predicate);
	}

	/**
	 * <p>
	 * 返回指定类型的有序集合
	 * </p>
	 * 
	 * <p>
	 * 只有指定类型的对象会被添加到返回的有序集合中.
	 * </p>
	 * 
	 * @param set 要限制类型的有序集合, 不能为null
	 * @param type 返回集合的元素类型
	 * @return 指定类型的有序集合
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:36:16
	 */
	@SuppressWarnings("unchecked")
	public static <T> SortedSet<T> typedSortedSet(SortedSet<?> set, Class<T> type) {
		return SetUtils.typedSortedSet(set, type);
	}

	/**
	 * <p>
	 * 转换给定的集合
	 * </p>
	 * 
	 * <p>
	 * 每一个新添加到集合中的元素都将被传递给转换器进行转换. 更为重要的是, 在调用该方法后, 
	 * 不要使用原来的集合, 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * @param set 要被转换的集合, 不能为null
	 * @param transformer 使用的转换器, 不能为null
	 * @return 转换后的集合
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-8 下午11:37:20
	 */
	@SuppressWarnings("rawtypes")
	public static SortedSet transformedSortedSet(SortedSet<?> set, Transformer transformer) {
		return SetUtils.transformedSortedSet(set, transformer);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.collections.SetUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}

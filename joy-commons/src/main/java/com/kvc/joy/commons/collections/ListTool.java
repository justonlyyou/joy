package com.kvc.joy.commons.collections;

import com.kvc.joy.commons.lang.ArrayTool;
import org.apache.commons.collections.Factory;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import java.util.*;

/**
 * List工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-6 下午1:39:07
 */
public class ListTool {

	private ListTool() {
	}

	/**
	 * <p>
	 * 根据指定的可变数组，创建ArrayList
	 * </p>
	 * 
	 * @param elems 可变数组，可以为null, 为null将返回空的列表
	 * @return 由可变数组元素组成的ArrayList
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-7 下午8:45:14
	 */
	public static <T> List<T> newArrayList(T... elems) {
		if (ArrayTool.isEmpty(elems)) {
			return new ArrayList<T>(0);
		}
		List<T> list = new ArrayList<T>(elems.length);
        Collections.addAll(list, elems);
		return list;
	}

	/**
	 * <p>
	 * 根据指定的可变数组，创建LinkedList
	 * </p>
	 * 
	 * @param elems 可变数组，可以为null, 为null将返回空的列表
	 * @return 由可变数组元素组成的LinkedList
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-7 下午8:55:11
	 */
	public static <T> List<T> newLinkedList(T... elems) {
		if (ArrayTool.isEmpty(elems)) {
			return new LinkedList<T>();
		}
		List<T> list = new LinkedList<T>();
        Collections.addAll(list, elems);
		return list;
	}
	
	/**
	 * 反转列表，返回新的列表，原列表不变
	 * 
	 * @param list 待反转的列表
	 * @return 反转后的列表
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年10月14日 下午9:05:42
	 */
	public static <T> List<T> reverse(List<T> list) {
		List<T> result = new ArrayList<T>(list.size());
		for (int i = list.size() - 1; i >= 0; i--) {
			result.add(list.get(i));
		}
		return result;
	}
	
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.collections.ListUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 返回两个容器的交集 <br>
	 * 返回的容器的每个元素的基数为该元素在给定的两个容器中的最小基数
	 * </p>
	 * 
	 * @param list1 第一个容器, 不能为null
	 * @param list2 第二个容器, 不能为null
	 * @return 两个容器的交集
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-7 下午9:17:00
	 */
	@SuppressWarnings("rawtypes")
	public static List intersection(List<?> list1, List<?> list2) {
		return ListUtils.intersection(list1, list2);
	}

	/**
	 * <p>
	 * 返回两个容器的差集
	 * </p>
	 * 
	 * <p>
	 * 该方法与{@link List#removeAll(Collection)}的差别在于它的基数： 如果<Code>list1</Code>包含两个
	 * <Code>null</Code>，<Code>list2</Code>只包含一个， 那么返回的列表将包含一个<Code>null</Code>。
	 * </p>
	 * 
	 * @param list1 被减的容器, 不能为空
	 * @param list2 要减掉的容器, 不能为空
	 * @throws NullPointerException 如果任意参数为null
	 * @return 一个包含两个容器的差集的新列表
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-5 上午10:05:28
	 */
	@SuppressWarnings("rawtypes")
	public static List subtract(List<?> list1, List<?> list2) {
		return ListUtils.subtract(list1, list2);
	}

	/**
	 * <p>
	 * 返回两个列表的和(相同元素只保留一个). 结果是它们的并集减去它们的交集(相当于将两个列表的元素放入一个Set)。
	 * </p>
	 * 
	 * @param list1 第一个列表, 不能为null
	 * @param list2 第二个列表, 不能为null
	 * @return 一个包含两个列表元素的和的新列表
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午8:31:15
	 */
	@SuppressWarnings("rawtypes")
	public static List sum(List<?> list1, List<?> list2) {
		return ListUtils.sum(list1, list2);
	}

	/**
	 * <p>
	 * 返回两个列表的并集. 第二个列表的元素将接在第一个列表的后面，并返回新的列表。
	 * </p>
	 * 
	 * @param list1 第一个列表, 不能为null
	 * @param list2 第二个列表, 不能为null
	 * @return 一个包含两个列表并集元素的新列表
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午8:40:25
	 */
	@SuppressWarnings("rawtypes")
	public static List union(List<?> list1, List<?> list2) {
		return ListUtils.union(list1, list2);
	}

	/**
	 * <p>
	 * 测试两个列表是否相等， 包括包含的元素、元素顺序和列表大小
	 * </p>
	 * 
	 * <p>
	 * 该方法对于不能通过继承AbstractList类实现<code>List</code>时非常有用。
	 * 该方法参数类型为Collection是为了确保其它容器类型能够使用列表的实现算法。
	 * </p>
	 * 
	 * <p>
	 * 该方法比较两个列表元素的相等性。当且仅当两个列表的大小相等，并且两个列表中 所有对应元素相等时返回<tt>true</tt>。两个元素
	 * <tt>e1</tt> 和 <tt>e2</tt>当
	 * <tt>(e1==null ? e2==null : e1.equals(e2))为true时是相等的。换句话说，
	 * 如果它们包含相同元素并且顺序是相同的时返回true. 该定义确保equals方法在
	 * 不同<tt>List</tt>的实现中都能正确工作。
	 * </p>
	 * 
	 * <p>
	 * <b>注意:</b> 如果在相等比较的过程中列表被修改， 那么该方法的行为将是不确定的。
	 * </p>
	 * 
	 * @see java.util.List
	 * @param list1 第一个列表, 可以为null
	 * @param list2 第二个列表, 可以为null
	 * @return 是否两个列表相等
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:08:29
	 */
	public static boolean isEqualList(Collection<?> list1, Collection<?> list2) {
		return ListUtils.isEqualList(list1, list2);
	}

	/**
	 * <p>
	 * 为指定列表生成哈希值(使用{@link java.util.List#hashCode()}指定的算法)
	 * </p>
	 * 
	 * <p>
	 * 该方法对于不能通过继承AbstractList类实现<code>List</code>时非常有用。
	 * 该方法参数类型为Collection是为了确保其它容器类型能够使用列表的实现算法。
	 * </p>
	 * 
	 * @see java.util.List#hashCode()
	 * @param list 要生成哈希值的列表， 可以为null
	 * @return 指定列表的哈希值，如果列表为null将返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:13:51
	 */
	public static int hashCodeForList(Collection<?> list) {
		return ListUtils.hashCodeForList(list);
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 返回一个列表, 它的元素存在于<code>collection</code>中同时也在<code>retain</code>中.
	 * 返回的容器的元素的基数与<code>collection</code>元素的基数一样, 除非 <code>retain</code>
	 * 没有包含这个元素, 此时, 基数为0. 该方法在你想更改一个容器但又不能调用该容器的retainAll(retain)方法时非常有用.
	 * </p>
	 * 
	 * @param collection 其内容为#retailAll操作的目标的容器
	 * @param retain 返回的容器中可能包含的元素的容器
	 * @return 一个由所有在<code>collection</code>中同时至少出现一次在<code>retain</code>
	 *         的元素组成的列表
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:20:14
	 */
	@SuppressWarnings("rawtypes")
	public static List retainAll(Collection<?> collection, Collection<?> retain) {
		return ListUtils.retainAll(collection, retain);
	}

	/**
	 * <p>
	 * 从<code>collection</code>中移除<code>remove</code>中的元素. 也就是说, 该方法返回一个由不在
	 * <code>remove</code>中元素组成的列表. 返回的容器的元素的基数与 <code>collection</code>元素的基数一样,
	 * 除非<code>remove</code>包含这个元素, 此时, 基数为0.
	 * 该方法在你想更改一个容器但又不能调用该容器的removeAll(retain)方法时非常有用.
	 * </p>
	 * 
	 * @param collection 要被移除元素的容器
	 * @param remove 要移除的元素的容器
	 * @return 一个由<code>collection</code>容器中所有除了在<code>remove</code>出现的元素组成的列表
	 * @throws NullPointerException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:20:25
	 */
	@SuppressWarnings("rawtypes")
	public static List removeAll(Collection<?> collection, Collection<?> remove) {
		return ListUtils.removeAll(collection, remove);
	}

	/**
	 * <p>
	 * 返回指定的列表的同步列表
	 * </p>
	 * 
	 * <p>
	 * 您必须手动同步返回的缓冲区的迭代器，以避免不确定性的行为:
	 * 
	 * <pre>
	 * List list = ListUtils.synchronizedList(myList);
	 * synchronized (list) {
	 * 	Iterator i = list.iterator();
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
	 * @param list 要同步的列表, 不能为null
	 * @return 同步的列表
	 * @throws IllegalArgumentException 如果列表为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:23:23
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> synchronizedList(List<? extends T> list) {
		return ListUtils.synchronizedList(list);
	}

	/**
	 * <p>
	 * 返回一个不可修改的列表
	 * </p>
	 * 
	 * <p>
	 * 该方法使用在装饰器子包中的实现.
	 * </p>
	 * 
	 * @param list 要置为不可修改的列表, 不能为null
	 * @return 一个不可修改的列表
	 * @throws IllegalArgumentException 如果列表为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:24:13
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> unmodifiableList(List<? extends T> list) {
		return ListUtils.unmodifiableList(list);
	}

	/**
	 * <p>
	 * 返回一个满足指定条件的列表
	 * </p>
	 * 
	 * <p>
	 * 只有通过测试的元素可以添加到要返回的列表中. 试图添加一个无效的对象将产生IllegalArgumentException异常. <br>
	 * 更为重要的是, 在调用该方法后, 不要使用原来的列表, 因为它是一个可以添加无效对象的后门.
	 * </p>
	 * 
	 * @param list 要检查的列表, 不能为null
	 * @param predicate 使用的条件, 不能为null
	 * @return 满足指定条件的列表
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:27:14
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> predicatedList(List<? extends T> list, Predicate predicate) {
		return ListUtils.predicatedList(list, predicate);
	}

	/**
	 * <p>
	 * 返回指定类型的列表
	 * </p>
	 * 
	 * <p>
	 * 只有指定类型的对象会被添加到返回的列表中.
	 * </p>
	 * 
	 * @param list 要限制类型的列表, 不能为null
	 * @param type 返回列表的元素类型
	 * @return 指定类型的列表
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:28:19
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> typedList(List<?> list, Class<T> type) {
		return ListUtils.typedList(list, type);
	}

	/**
	 * <p>
	 * 转换给定的列表
	 * </p>
	 * 
	 * <p>
	 * 每一个新添加到列表中的元素都将被传递给转换器进行转换. 更为重要的是, 在调用该方法后, 
	 * 不要使用原来的列表, 因为它是一个可以添加未转换的对象的后门.
	 * </p>
	 * 
	 * @param list 要被转换的列表, 不能为null
	 * @param transformer 使用的转换器, 不能为null
	 * @return 转换后的列表
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:31:38
	 */
	@SuppressWarnings("rawtypes")
	public static List transformedList(List<?> list, Transformer transformer) {
		return ListUtils.transformedList(list, transformer);
	}

	/**
	 * <p>
	 * 返回一个"懒惰"的列表， 它的元素将被按需加载
	 * </p>
	 * 
	 * <p>
	 * 当传给返回列表的{@link List#get(int) get}方法的下标参数大于当前列表大小时，
	 * 指定的工厂将创建一个新对象， 并将它插到列表的指定下标处。
	 * </p>
	 * 
	 * <p>
	 * 例如：
	 * 
	 * <pre>
	 * Factory factory = new Factory() {
	 *     public Object create() {
	 *         return new Date();
	 *     }
	 * }
	 * List lazy = ListUtils.lazyList(new ArrayList(), factory);
	 * Object obj = lazy.get(3);
	 * </pre>
	 * 
	 * 当上面的代码被执行时，<code>obj</code>将包含一个新的<code>Date</code>实例。
	 * 而且， 这个<code>Date</code>实例为列表中的第四个元素。前三个元素将被置为<code>null</code>。
	 * </p>
	 * 
	 * @param list 要设置为"懒惰"的列表, 不能为null
	 * @param factory 创建新对象的工厂, 不能为null
	 * @return 指定列表的"懒惰"列表
	 * @throws IllegalArgumentException 如果任意参数null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:41:55
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> lazyList(List<? extends T> list, Factory factory) {
		return ListUtils.lazyList(list, factory);
	}

	/**
	 * <p>
	 * 返回给定列表的一个固定大小的列表。
	 * 列表不能移除和添加元素， 但是已经存在列表中的元素可以被改变(例如，
	 * 通过{@link List#set(int,Object)}方法)
	 * </p>
	 * 
	 * @param list 要固定大小的列表, 不能为null
	 * @return 给定列表的一个固定大小的列表
	 * @throws IllegalArgumentException 如果指定的列表为null
	 * @throws UnsupportedOperationException 如果试图从返回的列表中添加或移除元素
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-8 下午9:45:01
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> fixedSizeList(List<? extends T> list) {
		return ListUtils.fixedSizeList(list);
	}
	
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.collections.ListUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}

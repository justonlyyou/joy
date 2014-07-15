package org.joy.commons.lang;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.CloneFailedException;

import java.util.Comparator;

/**
 * 对象处理工具类
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-9 下午8:44:04
 */
public class ObjectTool {

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.ObjectUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	// Defaulting
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 如果传入的对象为{@code null}返回指定的默认值
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.defaultIfNull(null, null)      = null
	 * ObjectUtils.defaultIfNull(null, "")        = ""
	 * ObjectUtils.defaultIfNull(null, "zz")      = "zz"
	 * ObjectUtils.defaultIfNull("abc", *)        = "abc"
	 * ObjectUtils.defaultIfNull(Boolean.TRUE, *) = Boolean.TRUE
	 * </pre>
	 * 
	 * @param <T> 对象的类型
	 * @param object 要测试的 {@code Object}, 可以为 {@code null}
	 * @param defaultValue 默认值, 可以为 {@code null}
	 * @return 如果不是{@code null}, 返回{@code object} , 否则返回默认值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:01:47
	 */
	public static <T> T defaultIfNull(T object, T defaultValue) {
		return org.apache.commons.lang3.ObjectUtils.defaultIfNull(object, defaultValue);
	}

	/**
	 * <p>
	 * 返回数组中第一个不为{@code null}的对象. 如果数组中的所有值全部为{@code null}或数组本身为{@code null}或空数组,
	 * 将返回{@code null}
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.firstNonNull(null, null)      = null
	 * ObjectUtils.firstNonNull(null, "")        = ""
	 * ObjectUtils.firstNonNull(null, null, "")  = ""
	 * ObjectUtils.firstNonNull(null, "zz")      = "zz"
	 * ObjectUtils.firstNonNull("abc", *)        = "abc"
	 * ObjectUtils.firstNonNull(null, "xyz", *)  = "xyz"
	 * ObjectUtils.firstNonNull(Boolean.TRUE, *) = Boolean.TRUE
	 * ObjectUtils.firstNonNull()                = null
	 * </pre>
	 * 
	 * @param <T> 数组元素的类型
	 * @param values 要测试的数组, 可以为 {@code null} 或空数组
	 * @return {@code values} 中的第一个非 {@code null}对象, 或没有非空对象时返回{@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:04:58
	 */
	public static <T> T firstNonNull(T... values) {
		return org.apache.commons.lang3.ObjectUtils.firstNonNull(values);
	}

	// Null-safe equals/hashCode
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 比较两个对象是否相等, 它们或者其中之一可以为{@code null}
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.equals(null, null)                  = true
	 * ObjectUtils.equals(null, "")                    = false
	 * ObjectUtils.equals("", null)                    = false
	 * ObjectUtils.equals("", "")                      = true
	 * ObjectUtils.equals(Boolean.TRUE, null)          = false
	 * ObjectUtils.equals(Boolean.TRUE, "true")        = false
	 * ObjectUtils.equals(Boolean.TRUE, Boolean.TRUE)  = true
	 * ObjectUtils.equals(Boolean.TRUE, Boolean.FALSE) = false
	 * </pre>
	 * 
	 * @param object1 第一个对象, 可以为 {@code null}
	 * @param object2 第二个对象, 可以为 {@code null}
	 * @return {@code true}: 如果两个对象相等
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:07:55
	 */
	public static boolean equals(Object object1, Object object2) {
		return org.apache.commons.lang3.ObjectUtils.equals(object1, object2);
	}

	/**
	 * <p>
	 * 比较两个对象是否不相等, 它们或者其中之一可以为{@code null}
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.notEqual(null, null)                  = false
	 * ObjectUtils.notEqual(null, "")                    = true
	 * ObjectUtils.notEqual("", null)                    = true
	 * ObjectUtils.notEqual("", "")                      = false
	 * ObjectUtils.notEqual(Boolean.TRUE, null)          = true
	 * ObjectUtils.notEqual(Boolean.TRUE, "true")        = true
	 * ObjectUtils.notEqual(Boolean.TRUE, Boolean.TRUE)  = false
	 * ObjectUtils.notEqual(Boolean.TRUE, Boolean.FALSE) = true
	 * </pre>
	 * 
	 * @param object1 第一个对象, 可以为 {@code null}
	 * @param object2 第二个对象, 可以为 {@code null}
	 * @return {@code true}: 如果两个对象不相等
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:08:45
	 */
	public static boolean notEqual(Object object1, Object object2) {
		return org.apache.commons.lang3.ObjectUtils.notEqual(object1, object2);
	}

	/**
	 * <p>
	 * 取得指定对象的哈希值, 对象为{@code null}时将返回0
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.hashCode(null)   = 0
	 * ObjectUtils.hashCode(obj)    = obj.hashCode()
	 * </pre>
	 * 
	 * @param obj 要计算哈希值的对象, 可以为 {@code null}
	 * @return 对象的哈希值, 对象为{@code null}时将返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:10:03
	 */
	public static int hashCode(Object obj) {
		return org.apache.commons.lang3.ObjectUtils.hashCode(obj);
	}

	/**
	 * <p>
	 * 取得多个对象的哈希值
	 * </p>
	 * 
	 * <p>
	 * 单个对象的哈希值与{@link #hashCode(Object)}不同. 
	 * 多个对象的哈希值与这些对象放入{@code ArrayList}后对这个列表计算的哈希值一样.
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.hashCodeMulti()                 = 1
	 * ObjectUtils.hashCodeMulti((Object[]) null)  = 1
	 * ObjectUtils.hashCodeMulti(a)                = 31 + a.hashCode()
	 * ObjectUtils.hashCodeMulti(a,b)              = (31 + a.hashCode()) * 31 + b.hashCode()
	 * ObjectUtils.hashCodeMulti(a,b,c)            = ((31 + a.hashCode()) * 31 + b.hashCode()) * 31 + c.hashCode()
	 * </pre>
	 * 
	 * @param objects 要计算哈希值的对象可变数组, 可以为 {@code null}
	 * @return 多个对象的哈希值, 对象为{@code null}时将返回0
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:14:47
	 */
	public static int hashCodeMulti(Object... objects) {
		return org.apache.commons.lang3.ObjectUtils.hashCodeMulti(objects);
	}

	// Identity ToString
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 获取对象的toString, 如果一个类没有重写toString方法将产生一个. {@code null} 将返回 {@code null}.
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.identityToString(null)         = null
	 * ObjectUtils.identityToString("")           = "java.lang.String@1e23"
	 * ObjectUtils.identityToString(Boolean.TRUE) = "java.lang.Boolean@7fa"
	 * </pre>
	 * 
	 * @param object 要创建toString的对象, 可以为 {@code null}
	 * @return 默认的toString文本, {@code null} 将 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:20:31
	 */
	public static String identityToString(Object object) {
		return org.apache.commons.lang3.ObjectUtils.identityToString(object);
	}

	/**
	 * <p>
	 * 拼接一个对象的toString, 如果一个类没有重写toString方法将产生一个. {@code null} 将返回 {@code null}.
	 * 其中一个参数为{@code null} 将抛出 NullPointerException 异常
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.identityToString(buf, "")            = buf.append("java.lang.String@1e23"
	 * ObjectUtils.identityToString(buf, Boolean.TRUE)  = buf.append("java.lang.Boolean@7fa"
	 * ObjectUtils.identityToString(buf, Boolean.TRUE)  = buf.append("java.lang.Boolean@7fa")
	 * </pre>
	 * 
	 * @param buffer 要拼接的字符串缓存
	 * @param object 要创建toString的对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:23:36
	 */
	public static void identityToString(StringBuffer buffer, Object object) {
		org.apache.commons.lang3.ObjectUtils.identityToString(buffer, object);
	}

	// ToString
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 取得一个对象的toString, 如果对象为{@code null}或空串时返回空串
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.toString(null)         = ""
	 * ObjectUtils.toString("")           = ""
	 * ObjectUtils.toString("bat")        = "bat"
	 * ObjectUtils.toString(Boolean.TRUE) = "true"
	 * </pre>
	 * 
	 * @see StringUtils#defaultString(String)
	 * @see String#valueOf(Object)
	 * @param obj 要获取 {@code toString}的对象, 可以为 null
	 * @return 对象的toString, 对象为{@code null}或空串时返回空串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:26:29
	 */
	public static String toString(Object obj) {
		return org.apache.commons.lang3.ObjectUtils.toString(obj);
	}

	/**
	 * <p>
	 * 取得一个对象的toString, 如果对象为{@code null}返回指定的字符串
	 * </p>
	 * 
	 * <pre>
	 * ObjectUtils.toString(null, null)           = null
	 * ObjectUtils.toString(null, "null")         = "null"
	 * ObjectUtils.toString("", "null")           = ""
	 * ObjectUtils.toString("bat", "null")        = "bat"
	 * ObjectUtils.toString(Boolean.TRUE, "null") = "true"
	 * </pre>
	 * 
	 * @see StringUtils#defaultString(String,String)
	 * @see String#valueOf(Object)
	 * @param obj 要获取 {@code toString}的对象, 可以为 null
	 * @param nullStr 对象为null时返回的字符串, 可以为 null
	 * @return 对象的toString, 对象为{@code null}返回指定的字符串
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:28:22
	 */
	public static String toString(Object obj, String nullStr) {
		return org.apache.commons.lang3.ObjectUtils.toString(obj, nullStr);
	}

	// Comparable
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 取最小的对象(根据Comparable进行比较)
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param values 待比较的对象可变数组, 可以为null
	 * @return <ul>
	 *         <li>如果所有对象非null且不相等, 返回最小的对象
	 *         <li>如果所有对象非null且相等, 返回第一个的对象
	 *         <li>如果任意对象为null, 返回非null对象中的最小对象
	 *         <li>如果所有对象都为null, 返回null
	 *         </ul>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:32:16
	 */
	public static <T extends Comparable<? super T>> T min(T... values) {
		return org.apache.commons.lang3.ObjectUtils.min(values);
	}

	/**
	 * <p>
	 * 取最大的对象(根据Comparable进行比较)
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param values 待比较的对象可变数组, 可以为null
	 * @return <ul>
	 *         <li>如果所有对象非null且不相等, 返回最大的对象
	 *         <li>如果所有对象非null且相等, 返回第一个的对象
	 *         <li>如果任意对象为null, 返回非null对象中的最大对象
	 *         <li>如果所有对象都为null, 返回null
	 *         </ul>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:33:33
	 */
	public static <T extends Comparable<? super T>> T max(T... values) {
		return org.apache.commons.lang3.ObjectUtils.max(values);
	}

	/**
	 * <p>
	 * 比较两个对象(根据Comparable进行比较, 空安全). {@code null} 值 被认为小于 非 {@code null} 值.
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param c1 第一个待比较的对象, 可以为null
	 * @param c2 第一个待比较的对象, 可以为null
	 * @return c1 < c2返回负数, c1 = c2返回0, c1 > c2返回正数
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:37:42
	 */
	public static <T extends Comparable<? super T>> int compare(T c1, T c2) {
		return org.apache.commons.lang3.ObjectUtils.compare(c1, c2);
	}

	/**
	 * <p>
	 * 比较两个对象(根据Comparable进行比较, 空安全), 可以指定null对象的比较规则
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param c1 第一个待比较的对象, 可以为null
	 * @param c2 第一个待比较的对象, 可以为null
	 * @param nullGreater true: {@code null}值被认为小于非{@code null}值, <br>
	 *  				false: {@code null}值被认为大于非{@code null}值, <br>
	 * @return c1 < c2返回负数, c1 = c2返回0, c1 > c2返回正数
	 * @see java.util.Comparator#compare(Object, Object)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:41:31
	 */
	public static <T extends Comparable<? super T>> int compare(T c1, T c2, boolean nullGreater) {
		return org.apache.commons.lang3.ObjectUtils.compare(c1, c2, nullGreater);
	}

	/**
	 * <p>
	 * 查找多个对象"中间"对象
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param items 待比较的对象可变数组, 不能为null
	 * @return "中间"对象
	 * @throws NullPointerException 如果数组为 {@code null}
	 * @throws IllegalArgumentException 如果数组为空或包含 {@code null} 值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:45:42
	 */
	public static <T extends Comparable<? super T>> T median(T... items) {
		return org.apache.commons.lang3.ObjectUtils.median(items);
	}

	/**
	 * <p>
	 * 查找多个对象"中间"对象, 使用指定的比较器
	 * </p>
	 * 
	 * @param <T> 比较的对象的类型
	 * @param comparator 比较器
	 * @param items 待比较的对象可变数组, 不能为null
	 * @return "中间"对象
	 * @throws NullPointerException 如果数组或比较器为 {@code null}
	 * @throws IllegalArgumentException 如果数组为空或包含 {@code null} 值
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:47:20
	 */
	public static <T> T median(Comparator<T> comparator, T... items) {
		return org.apache.commons.lang3.ObjectUtils.median(comparator, items);
	}

	// Mode
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找出现次数最多的对象
	 * </p>
	 * 
	 * @param <T> 要查找的对象的类型
	 * @param items 要查找的对象可变数组
	 * @return 出现次数最多的对象, 返回{@code null}如果对象惟一或数组参数为空或null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:53:37
	 */
	public static <T> T mode(T... items) {
		return org.apache.commons.lang3.ObjectUtils.mode(items);
	}

	// cloning
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 克隆指定的对象, 对象必须实现{@link Cloneable}接口
	 * </p>
	 * 
	 * @param <T> 要克隆的对象类型
	 * @param obj 要克隆的对象, null 将返回 null
	 * @return 克隆后的对象, null 将返回 null
	 * @throws CloneFailedException 克隆操作失败时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午7:56:28
	 */
	public static <T extends Cloneable> T clone(final T obj) {
		return org.apache.commons.lang3.ObjectUtils.clone(obj);
	}

	/**
	 * <p>
	 * 克隆指定的对象, 如果可能的话
	 * </p>
	 * 
	 * <p>
	 * 该方法与{@link #clone(Object)}类似, 但是如果指定的对象没有实现cloneable接口, 将返回原对象.
	 * 当调用者使用不同的实现(如: 一个service)并且某些实现不允许并发操作或有状态时将更方便.
	 * 这时, 该方法简单提供一个适当的克隆实现并且调用者的代码不需要改变.
	 * </p>
	 * 
	 * @param <T> 要克隆的对象类型
	 * @param obj 要克隆的对象, null 将返回 null
	 * @return 如果对象有实现{@link Cloneable}接口返回克隆后的对象, 否则返回对象本身
	 * @throws CloneFailedException 如果对象有实现{@link Cloneable}接口且克隆操作失败
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-1 下午8:03:43
	 */
	public static <T> T cloneIfPossible(final T obj) {
		return org.apache.commons.lang3.ObjectUtils.cloneIfPossible(obj);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.ObjectUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}

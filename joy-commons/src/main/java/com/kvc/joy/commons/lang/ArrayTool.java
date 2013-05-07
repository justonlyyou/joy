package com.kvc.joy.commons.lang;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.kvc.joy.commons.exception.SystemException;

/**
 * 数组操作工具类
 *
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-29 下午5:20:40
 */
public class ArrayTool {


	private ArrayTool() {
	}

	/**
	 * <p>
	 * 把Map转换为二维数组，每行两个元素，按顺序分别为map的key和value
	 * </p>
	 * 
	 * @param map 待转换的Map
	 * @return 二维数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:46:58
	 */
	public static Object[][] mapToArrOfArr(Map<Object, Object> map) {
		Object[][] arr = null;
		if (map != null && map.isEmpty() == false) {
			arr = new String[map.size()][2];
			int i = 0;
			Set<Entry<Object, Object>> entrySet = map.entrySet();
			for (Entry<Object, Object> entry : entrySet) {
				arr[i][0] = entry.getKey();
				arr[i][1] = entry.getValue();
				i++;
			}
		}
		return arr == null ? new Object[0][0] : arr;
	}
	
	/**
	 * 将Object类型的数组转换为指定类型的数组，并填充给定的数组 <br>
	 * 主要用途是将用List.toArray()方法得到的Object类型的数组适配为原来类型的数组 <br>
	 * 特别注意：objs数组的元素的类型必须能强化转换为指令的空数组的元素类型
	 * 
	 * @param nullArrays 指定的有长度但元素都是null的数组
	 * @param objs 待转换的数组
	 * @return 填充后的数组
	 * @throws SystemException  nullArrays数组为null或两个数组的长度不一致 <br>
	 * 			ClassCastException 两个数组元素的类型不匹配
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月9日 下午11:44:16
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] cast(T[] nullArrays, Object[] objs) {
		if(nullArrays == null) {
			throw new SystemException("nullArrays数组不能为null");
		}
		if(isNotEmpty(objs)) {
			if(nullArrays.length != objs.length) {
				throw new SystemException("两个数组的长度必须一致");
			}
			for (int i = 0; i < objs.length; i++) {
				nullArrays[i] = (T) objs[i];
			}
		}
		return nullArrays;
	}
	
	/**
	 * 将列表转换为指定类型的数组，并填充给定的数组 <br>
	 * 主要用途是将用List.toArray()方法得到的Object类型的数组适配为原来类型的数组 <br>
	 * 特别注意：objs元素的类型必须能强化转换为指令的空数组的元素类型
	 * 
	 * @param nullArrays 指定的有长度但元素都是null的数组
	 * @param objs 待转换的列表
	 * @return 填充后的数组
	 * @throws SystemException  nullArrays数组为null或两个数组的长度不一致 <br>
	 * 			ClassCastException 两个数组元素的类型不匹配
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013年10月9日 下午11:44:16
	 */
	public static <T> T[] cast(T[] nullArrays, List<T> objs) {
		return cast(nullArrays, objs.toArray());
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.ArrayUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	// Basic methods handling multi-dimensional arrays
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 获取一个数组的字符串值, {@code null}将被当作空数组
	 * </p>
	 * 
	 * <p>
	 * 支持多维数组,包括多维的基本类型数组
	 * </p>
	 * 
	 * <p>
	 * 输出的格式如<code>{a,b}</code>.
	 * </p>
	 * 
	 * @param array 数组, 可以为{@code null}
	 * @return 数组的字符串表示, 如果传入的数组参数为null将返回'{}'
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午5:20:40
	 */
	public static String toString(Object array) {
		return org.apache.commons.lang3.ArrayUtils.toString(array);
	}

	/**
	 * <p>
	 * 获取一个数组的字符串值, 数组为null时将返回指定的字符串参数
	 * </p>
	 * 
	 * <p>
	 * 支持多维数组,包括多维的基本类型数组
	 * </p>
	 * 
	 * <p>
	 * 输出的格式如<code>{a,b}</code>.
	 * </p>
	 * 
	 * @param array 数组, 可以为{@code null}
	 * @param stringIfNull 数组为null时将要返回字符串
	 * @return 数组的字符串表示
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午5:26:54
	 */
	public static String toString(Object array, String stringIfNull) {
		return org.apache.commons.lang3.ArrayUtils.toString(array, stringIfNull);
	}

	/**
	 * <p>
	 * 获取数组的哈希值
	 * </p>
	 * 
	 * <p>
	 * 支持多维数组,包括多维的基本类型数组
	 * </p>
	 * 
	 * @param array 要获取哈希值的数组, 数组为{@code null}将返回0
	 * @return 数组的哈希值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午5:28:58
	 */
	public static int hashCode(Object array) {
		return org.apache.commons.lang3.ArrayUtils.hashCode(array);
	}

	/**
	 * <p>
	 * 使用equals()方法比较两个数组
	 * </p>
	 * 
	 * <p>
	 * 支持多维数组,包括多维的基本类型数组
	 * </p>
	 * 
	 * @param array1 作为左操作数数组, 可以为{@code null}
	 * @param array2 作为右操作数数组, 可以为{@code null}
	 * @return {@code true} 如果两个数组相等
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午5:34:56
	 */
	public static boolean isEquals(Object array1, Object array2) {
		return org.apache.commons.lang3.ArrayUtils.isEquals(array1, array2);
	}

	// To map
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 转化给定的数组为 {@link java.util.Map}. 数组的每个元素必须为 {@link java.util.Map.Entry} 或一个包含至少两个元素的数组, 第一个元素被当作key,第二个元素被当作value.
	 * </p>
	 * 
	 * <p>
	 * 该方法可以被用于做初始化操作:
	 * </p>
	 * 
	 * <pre>
	 * // 创建一个颜色映射的Map
	 * Map colorMap = ArrayUtils.toMap(new String[][] {{
	 *     {"RED", "#FF0000"},
	 *     {"GREEN", "#00FF00"},
	 *     {"BLUE", "#0000FF"}});
	 * </pre>
	 * 
	 * <p>
	 * 数组为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 一个元素为{@link java.util.Map.Entry} 或一个包含至少两个元素的数组, 可以为{@code null}
	 * @return 从数组创建的{@code Map}
	 * @throws IllegalArgumentException 当数组的元素也为数组,但是包含的元素少于两个时
	 * @throws IllegalArgumentException 当数组的元素即不是{@link java.util.Map.Entry}也不是一个Array时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午5:45:22
	 */
	public static Map<Object, Object> toMap(Object[] array) {
		return org.apache.commons.lang3.ArrayUtils.toMap(array);
	}

	// Generic array
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 创建一个类型安全的泛型数组
	 * </p>
	 * 
	 * <p>
	 * java语言本身不允许创建一个泛型数组:
	 * </p>
	 * 
	 * <pre>
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * 	return new T[size]; // 这里编译时将出错
	 * }
	 * 
	 * public static &lt;T&gt; T[] createAnArray(int size) {
	 * 	return (T[]) new Object[size]; // 运行时将抛ClassCastException异常
	 * }
	 * </pre>
	 * 
	 * <p>
	 * 因此,该方法用于弥补这一缺陷.例如,一个字符串数组可以这样创建:
	 * </p>
	 * 
	 * <pre>
	 * String[] array = ArrayUtils.toArray(&quot;1&quot;, &quot;2&quot;);
	 * String[] emptyArray = ArrayUtils.&lt;String&gt; toArray();
	 * </pre>
	 * 
	 * <p>
	 * 这个方法的经典使用场景为: 当调用者本身使用了泛型,而它们必须被合并到一个数组时.
	 * </p>
	 * 
	 * <p>
	 * 请注意，此方法只有当提供相同类型的参数时才有意义, 这样编译器可以推断类型数组本身。 虽然可以像这样明确地选择类型:
	 * <code>Number[] array = ArrayUtils.&lt;Number&gt;toArray(Integer.valueOf(42), Double.valueOf(Math.PI))</code> ,
	 * 但相比下面的用法没有什么真正的优点: <code>new Number[] {Integer.valueOf(42), Double.valueOf(Math.PI)}</code>.
	 * </p>
	 * 
	 * @param <T> 数组元素的类型
	 * @param items 可变数组, 可以为null
	 * @return 数组, 不会为null,除非传入的数组为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午6:05:14
	 */
	public static <T> T[] toArray(final T... items) {
		return org.apache.commons.lang3.ArrayUtils.toArray(items);
	}

	// Clone
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 浅克隆一个数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组里的对象不会被克隆, 因此, 不支持多维数组.
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param <T> 数组元素的类型
	 * @param array 要被浅克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:03:26
	 */
	public static <T> T[] clone(T[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个long元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:05:45
	 */
	public static long[] clone(long[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个int元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:06:15
	 */
	public static int[] clone(int[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个short元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:06:25
	 */
	public static short[] clone(short[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个char元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:06:35
	 */
	public static char[] clone(char[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个byte元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:06:45
	 */
	public static byte[] clone(byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个double元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:07:00
	 */
	public static double[] clone(double[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个float元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:07:10
	 */
	public static float[] clone(float[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	/**
	 * <p>
	 * 克隆一个boolean元素的数组, 返回一个类型转换后的结果
	 * </p>
	 * 
	 * <p>
	 * 数组参数为{@code null}将返回{@code null}.
	 * </p>
	 * 
	 * @param array 要被克隆的数组, 可以为{@code null}
	 * @return 克隆后的数组, 数组参数为{@code null}将返回{@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:07:19
	 */
	public static boolean[] clone(boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.clone(array);
	}

	// nullToEmpty
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:19:32
	 */
	public static Object[] nullToEmpty(Object[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:19:37
	 */
	public static String[] nullToEmpty(String[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:19:42
	 */
	public static long[] nullToEmpty(long[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:19:52
	 */
	public static int[] nullToEmpty(int[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:20:12
	 */
	public static short[] nullToEmpty(short[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:20:22
	 */
	public static char[] nullToEmpty(char[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:20:28
	 */
	public static byte[] nullToEmpty(byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:20:37
	 */
	public static double[] nullToEmpty(double[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:20:44
	 */
	public static float[] nullToEmpty(float[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:21:12
	 */
	public static boolean[] nullToEmpty(boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:21:21
	 */
	public static Long[] nullToEmpty(Long[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:21:29
	 */
	public static Integer[] nullToEmpty(Integer[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:21:31
	 */
	public static Short[] nullToEmpty(Short[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:24:30
	 */
	public static Character[] nullToEmpty(Character[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:24:36
	 */
	public static Byte[] nullToEmpty(Byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:24:45
	 */
	public static Double[] nullToEmpty(Double[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:24:53
	 */
	public static Float[] nullToEmpty(Float[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	/**
	 * <p>
	 * 使用防御性编程技术, 将一个{@code null}的数组转化为一个空的数组。
	 * </p>
	 * 
	 * <p>
	 * 数组参数如果为{@code null}将返回一个空的数组.
	 * </p>
	 * 
	 * <p>
	 * 作为一项内存优化技术, 传入一个空的数组将被该类中的通过{@code public static}定义的空数组覆盖
	 * </p>
	 * 
	 * @param array 要检查 {@code null} 或 空的数组
	 * @return 传入的数组. 如果传入的数组为null或空, 将返回通过{@code public static}定义的空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:24:59
	 */
	public static Boolean[] nullToEmpty(Boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.nullToEmpty(array);
	}

	// Subarrays
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 产生一个新的数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * <p>
	 * 子数组的元素类型与原数组是一样的. 因此, 如果输入的数组元素类型为 {@code Date}, 下面的用法是意料中的事:
	 * </p>
	 * 
	 * <pre>
	 * Date[] someDates = (Date[]) ArrayUtils.subarray(allDates, 2, 5);
	 * </pre>
	 * 
	 * @param <T> 数组元素的类型
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:35:50
	 */
	public static <T> T[] subarray(T[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code long}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:06
	 */
	public static long[] subarray(long[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code int}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:10
	 */
	public static int[] subarray(int[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code short}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:13
	 */
	public static short[] subarray(short[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code char}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:17
	 */
	public static char[] subarray(char[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code byte}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:25
	 */
	public static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code double}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:36
	 */
	public static double[] subarray(double[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code float}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:37:40
	 */
	public static float[] subarray(float[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	/**
	 * <p>
	 * 产生一个新的{@code boolean}元素数组, 它包含原数组的从start下标开始到end下标的元素.
	 * </p>
	 * 
	 * <p>
	 * 开始下标有被包括,而结束下标则没有.如果传入的数组为null, 将返回null.
	 * </p>
	 * 
	 * @param array 数组
	 * @param startIndexInclusive 开始下标(包括). 小于0当作0处理, 大于数组长度将返回一个空的数组
	 * @param endIndexExclusive 结束下标(不包括). 小于开始下标将返回空数组, 大于数组长度当数组长度处理
	 * @return 一个包含原数组的从start下标开始到end下标的元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:38:06
	 */
	public static boolean[] subarray(boolean[] array, int startIndexInclusive, int endIndexExclusive) {
		return org.apache.commons.lang3.ArrayUtils.subarray(array, startIndexInclusive, endIndexExclusive);
	}

	// Is same length
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * <p>
	 * 数组中的任何多维元素将被忽略.
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:22
	 */
	public static boolean isSameLength(Object[] array1, Object[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:29
	 */
	public static boolean isSameLength(long[] array1, long[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:39
	 */
	public static boolean isSameLength(int[] array1, int[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:49
	 */
	public static boolean isSameLength(short[] array1, short[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:55
	 */
	public static boolean isSameLength(char[] array1, char[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:43:59
	 */
	public static boolean isSameLength(byte[] array1, byte[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:44:07
	 */
	public static boolean isSameLength(double[] array1, double[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:44:17
	 */
	public static boolean isSameLength(float[] array1, float[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	/**
	 * <p>
	 * 检测两个数组的长度是否一致, {@code null}将当作空数组
	 * </p>
	 * 
	 * @param array1 第一个数组, 可以为 {@code null}
	 * @param array2 第二个数组, 可以为 {@code null}
	 * @return {@code true} 如果两个数组的长度一致, {@code null}将当作空数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:44:25
	 */
	public static boolean isSameLength(boolean[] array1, boolean[] array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameLength(array1, array2);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定数组的长度. 该方法可以处理{@code Object} 数组 and 基本类型的数组.
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为null, 将返回0
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.getLength(null)            = 0
	 * ArrayUtils.getLength([])              = 0
	 * ArrayUtils.getLength([null])          = 1
	 * ArrayUtils.getLength([true, false])   = 2
	 * ArrayUtils.getLength([1, 2, 3])       = 3
	 * ArrayUtils.getLength(["a", "b", "c"]) = 3
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @return 数组的长度, 如果传入的数组为null, 将返回0
	 * @throws IllegalArgumentException 如果传入的参数不是数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:48:35
	 */
	public static int getLength(Object array) {
		return org.apache.commons.lang3.ArrayUtils.getLength(array);
	}

	/**
	 * <p>
	 * 检查是否两个数组是相同类型的，同时考虑到多维数组。
	 * </p>
	 * 
	 * @param array1 第一个数组, 不能为 {@code null}
	 * @param array2 第二个数组, 不能为 {@code null}
	 * @return {@code true} 如果数组的类型匹配
	 * @throws IllegalArgumentException 如果任意一个数组为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:52:56
	 */
	public static boolean isSameType(Object array1, Object array2) {
		return org.apache.commons.lang3.ArrayUtils.isSameType(array1, array2);
	}

	// Reverse
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 没有对多维数组作特别的处理
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:54:45
	 */
	public static void reverse(Object[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:08
	 */
	public static void reverse(long[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:16
	 */
	public static void reverse(int[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:21
	 */
	public static void reverse(short[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:27
	 */
	public static void reverse(char[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:33
	 */
	public static void reverse(byte[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:39
	 */
	public static void reverse(double[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:46
	 */
	public static void reverse(float[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	/**
	 * <p>
	 * 反转数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的参数为null, 将什么也不做
	 * </p>
	 * 
	 * @param array 要反转的数组, 可以为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:55:53
	 */
	public static void reverse(boolean[] array) {
		org.apache.commons.lang3.ArrayUtils.reverse(array);
	}

	// IndexOf search
	// ----------------------------------------------------------------------

	// Object IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定对象在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param objectToFind 要查找的对象, 可以为 {@code null}
	 * @return 给定对象在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午7:59:34
	 */
	public static int indexOf(Object[] array, Object objectToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, objectToFind);
	}

	/**
	 * <p>
	 * 查找给定对象在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param objectToFind 要查找的对象, 可以为 {@code null}
	 * @param startIndex 查找的起始下标
	 * @return 给定对象在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:02:29
	 */
	public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, objectToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定对象在数组中的最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param objectToFind 要查找的对象, 可以为 {@code null}
	 * @return 给定对象在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:04:55
	 */
	public static int lastIndexOf(Object[] array, Object objectToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, objectToFind);
	}

	/**
	 * <p>
	 * 查找给定对象在数组中的最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param objectToFind 要查找的对象, 可以为 {@code null}
	 * @param startIndex 开始查找的下标
	 * @return 给定对象在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:11:12
	 */
	public static int lastIndexOf(Object[] array, Object objectToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, objectToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的对象
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param objectToFind 要查找的对象, 可以为 {@code null}
	 * @return {@code true} 如果数组包含给定的对象
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:12:59
	 */
	public static boolean contains(Object[] array, Object objectToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, objectToFind);
	}

	// long IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:16:10
	 */
	public static int indexOf(long[] array, long valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:20:42
	 */
	public static int indexOf(long[] array, long valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(long[] array, long valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(long[] array, long valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(long[] array, long valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// int IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:16:20
	 */
	public static int indexOf(int[] array, int valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:20:49
	 */
	public static int indexOf(int[] array, int valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(int[] array, int valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(int[] array, int valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(int[] array, int valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// short IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:16:30
	 */
	public static int indexOf(short[] array, short valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:20:57
	 */
	public static int indexOf(short[] array, short valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(short[] array, short valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(short[] array, short valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(short[] array, short valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// char IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:16:40
	 */
	public static int indexOf(char[] array, char valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:21:12
	 */
	public static int indexOf(char[] array, char valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(char[] array, char valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(char[] array, char valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(char[] array, char valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// byte IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:16:50
	 */
	public static int indexOf(byte[] array, byte valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:21:22
	 */
	public static int indexOf(byte[] array, byte valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(byte[] array, byte valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(byte[] array, byte valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(byte[] array, byte valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// double IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:17:10
	 */
	public static int indexOf(double[] array, double valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 在指定的容差之内查找给定值在数组中的下标, 此方法将返回介于该区域(从valueToFind-容差值到valueToFind+容差值)的第一个值的索引
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param tolerance 容差
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:21:44
	 */
	public static int indexOf(double[] array, double valueToFind, double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, tolerance);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:21:32
	 */
	public static int indexOf(double[] array, double valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 在指定的容差之内从开始下标查找给定值在数组中的下标, 此方法将返回介于该区域(从valueToFind-容差值到valueToFind+容差值)的第一个值的索引
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @param tolerance 容差
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:21:44
	 */
	public static int indexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex, tolerance);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(double[] array, double valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 在指定的容差之内查找给定值在数组中最后一次出现的下标, 此方法将返回介于该区域(从valueToFind-容差值到valueToFind+容差值)的最后一个值的索引
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param tolerance 容差
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:21:44
	 */
	public static int lastIndexOf(double[] array, double valueToFind, double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, tolerance);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(double[] array, double valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 在指定的容差之内, 从开始下标查找给定值在数组中最后一次出现的下标, 此方法将返回介于该区域(从valueToFind-容差值到valueToFind+容差值)的最后一个值的索引
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @param tolerance 容差
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:21:44
	 */
	public static int lastIndexOf(double[] array, double valueToFind, int startIndex, double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex, tolerance);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(double[] array, double valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	/**
	 * <p>
	 * 检查数组在指定的容差之内是否包含给定的值, 即数组是否包含一个在从valueToFind-容差值到valueToFind+容差值范围中的一个值.
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param tolerance 容差值
	 * @return {@code true} 如果数组包含容差范围中的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:36:44
	 */
	public static boolean contains(double[] array, double valueToFind, double tolerance) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind, tolerance);
	}

	// float IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:17:20
	 */
	public static int indexOf(float[] array, float valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:21:42
	 */
	public static int indexOf(float[] array, float valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(float[] array, float valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(float[] array, float valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(float[] array, float valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// boolean IndexOf
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 查找给定值在数组中的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:17:30
	 */
	public static int indexOf(boolean[] array, boolean valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将被当作0. startIndex如果大于数组长度将返回{@link #INDEX_NOT_FOUND} ({@code -1}).
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:21:52
	 */
	public static int indexOf(boolean[] array, boolean valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.indexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:26:35
	 */
	public static int lastIndexOf(boolean[] array, boolean valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind);
	}

	/**
	 * <p>
	 * 查找给定值在数组中最后一次出现的下标, 从指定下标开始查起
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@link #INDEX_NOT_FOUND} ({@code -1})
	 * </p>
	 * 
	 * <p>
	 * startIndex为负数将返回{@link #INDEX_NOT_FOUND} ({@code -1}). startIndex如果大于数组长度将从数组尾部查起.
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @param startIndex 开始查找的下标
	 * @return 给定值在数组中最后一次出现的下标, 如果没有找到或传入的数组为{@code null}, 将返回 {@link #INDEX_NOT_FOUND} ({@code -1})
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午8:30:45
	 */
	public static int lastIndexOf(boolean[] array, boolean valueToFind, int startIndex) {
		return org.apache.commons.lang3.ArrayUtils.lastIndexOf(array, valueToFind, startIndex);
	}

	/**
	 * <p>
	 * 检查数组是否包含给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code false}
	 * </p>
	 * 
	 * @param array 被查找的数组, 可以为 {@code null}
	 * @param valueToFind 要查找的值
	 * @return {@code true} 如果数组包含给定的值
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:07:44
	 */
	public static boolean contains(boolean[] array, boolean valueToFind) {
		return org.apache.commons.lang3.ArrayUtils.contains(array, valueToFind);
	}

	// Primitive/Object array converters
	// ----------------------------------------------------------------------

	// Character array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Character的数组转化为一个char的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Character}数组, 可以为 {@code null}
	 * @return {@code char} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static char[] toPrimitive(Character[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Character的数组转化为一个char的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Character}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code char} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static char[] toPrimitive(Character[] array, char valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个char的数组转化为一个Character的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code char} 数组
	 * @return {@code Character} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Character[] toObject(char[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Long array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Long的数组转化为一个long的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Long}数组, 可以为 {@code null}
	 * @return {@code long} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static long[] toPrimitive(Long[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Long的数组转化为一个long的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Long}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code long} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static long[] toPrimitive(Long[] array, long valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个long的数组转化为一个Long的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code long} 数组
	 * @return {@code Long} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Long[] toObject(long[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Int array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Integer的数组转化为一个int的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Integer}数组, 可以为 {@code null}
	 * @return {@code int} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static int[] toPrimitive(Integer[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Integer的数组转化为一个int的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Integer}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code int} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static int[] toPrimitive(Integer[] array, int valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个int的数组转化为一个Integer的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code int} 数组
	 * @return {@code Integer} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Integer[] toObject(int[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Short array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Short的数组转化为一个short的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Short}数组, 可以为 {@code null}
	 * @return {@code short} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static short[] toPrimitive(Short[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Short的数组转化为一个short的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Short}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code short} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static short[] toPrimitive(Short[] array, short valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个short的数组转化为一个Short的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code short} 数组
	 * @return {@code Short} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Short[] toObject(short[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Byte array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Byte的数组转化为一个byte的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Byte}数组, 可以为 {@code null}
	 * @return {@code byte} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static byte[] toPrimitive(Byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Byte的数组转化为一个byte的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Byte}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code byte} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static byte[] toPrimitive(Byte[] array, byte valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个byte的数组转化为一个Byte的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code byte} 数组
	 * @return {@code Byte} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Byte[] toObject(byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Double array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Double的数组转化为一个double的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Double}数组, 可以为 {@code null}
	 * @return {@code double} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static double[] toPrimitive(Double[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Double的数组转化为一个double的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Double}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code double} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static double[] toPrimitive(Double[] array, double valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个double的数组转化为一个Double的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code double} 数组
	 * @return {@code Double} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Double[] toObject(double[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Float array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Float的数组转化为一个float的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Float}数组, 可以为 {@code null}
	 * @return {@code float} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static float[] toPrimitive(Float[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Float的数组转化为一个float的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Float}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code float} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static float[] toPrimitive(Float[] array, float valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个float的数组转化为一个Float的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code float} 数组
	 * @return {@code Float} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Float[] toObject(float[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// Boolean array converters
	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个Boolean的数组转化为一个boolean的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Boolean}数组, 可以为 {@code null}
	 * @return {@code boolean} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @throws NullPointerException 如果数组元素为 {@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:42:02
	 */
	public static boolean[] toPrimitive(Boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array);
	}

	/**
	 * <p>
	 * 将一个Boolean的数组转化为一个boolean的数组, 如果数组中有{@code null}元素, 用指定值替换
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code Boolean}数组, 可以为 {@code null}
	 * @param valueForNull 要替换{@code null}元素的值
	 * @return {@code boolean} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:45:02
	 */
	public static boolean[] toPrimitive(Boolean[] array, boolean valueForNull) {
		return org.apache.commons.lang3.ArrayUtils.toPrimitive(array, valueForNull);
	}

	/**
	 * <p>
	 * 将一个boolean的数组转化为一个Boolean的数组
	 * </p>
	 * 
	 * <p>
	 * 如果传入的数组为{@code null}, 将返回{@code null}
	 * </p>
	 * 
	 * @param array {@code boolean} 数组
	 * @return {@code Boolean} 数组, 如果传入的数组为{@code null}, 将返回{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午9:49:08
	 */
	public static Boolean[] toObject(boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.toObject(array);
	}

	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(Object[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(long[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(int[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(short[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(char[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(double[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(float[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否为空或{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组为空或{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:09:54
	 */
	public static boolean isEmpty(boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.isEmpty(array);
	}

	// ----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static <T> boolean isNotEmpty(T[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(long[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(int[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(short[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(char[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(byte[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(double[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(float[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 检查数组是否不为空且不为{@code null}
	 * </p>
	 * 
	 * @param array 要检测的数组
	 * @return {@code true} 如果数组不为空且不为{@code null}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:12:57
	 */
	public static boolean isNotEmpty(boolean[] array) {
		return org.apache.commons.lang3.ArrayUtils.isNotEmpty(array);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(null, null)     = null
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * ArrayUtils.addAll([null], [null]) = [null, null]
	 * ArrayUtils.addAll(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
	 * </pre>
	 * 
	 * @param <T> 数组元素的类型
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组. {@code null} 如果两个数组都为 {@code null}. 新数组的类型为第一个数组的类型, 除非第一个数组为null, 这样新数组的类型就为第二个数组的类型.
	 * @throws IllegalArgumentException 如果数组类型不匹配
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:25:57
	 */
	public static <T> T[] addAll(T[] array1, T... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static boolean[] addAll(boolean[] array1, boolean... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static char[] addAll(char[] array1, char... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static byte[] addAll(byte[] array1, byte... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static short[] addAll(short[] array1, short... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static int[] addAll(int[] array1, int... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static long[] addAll(long[] array1, long... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static float[] addAll(float[] array1, float... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 将给定数组的所有元素添加到一个新数组中
	 * </p>
	 * 
	 * <p>
	 * 新的数组包含{@code array1}和{@code array2}中的所有元素.总是返回一个新的数组.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.addAll(array1, null)   = cloned copy of array1
	 * ArrayUtils.addAll(null, array2)   = cloned copy of array2
	 * ArrayUtils.addAll([], [])         = []
	 * </pre>
	 * 
	 * @param array1 第一个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @param array2 第二个它的元素要添加到新数组的数组, 可以为 {@code null}
	 * @return 新的数组, 如果两个数组都为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午10:31:26
	 */
	public static double[] addAll(double[] array1, double... array2) {
		return org.apache.commons.lang3.ArrayUtils.addAll(array1, array2);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的元素
	 * </p>
	 * 
	 * <p>
	 * 新数组的类型和输入的数组一样(不为null的话)
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素, 新数组元素的类型和指定的元素的类型一样, 除非该元素本身为null, 这时新数组的类型为Object[]
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, null)      = [null]
	 * ArrayUtils.add(null, "a")       = ["a"]
	 * ArrayUtils.add(["a"], null)     = ["a", null]
	 * ArrayUtils.add(["a"], "b")      = ["a", "b"]
	 * ArrayUtils.add(["a", "b"], "c") = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的元素的新数组. 新数组的类型和输入的数组一样(不为null的话), 如果输入的数组为null, 新数组的类型为指定元素的类型. 如果两个输入参数都为null,
	 *         将抛出IllegalArgumentException异常.
	 * @throws IllegalArgumentException 如果两个输入参数都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:03:57
	 */
	public static <T> T[] add(T[] array, T element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, true)          = [true]
	 * ArrayUtils.add([true], false)       = [true, false]
	 * ArrayUtils.add([true, false], true) = [true, false, true]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:07:38
	 */
	public static boolean[] add(boolean[] array, boolean element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:08:38
	 */
	public static byte[] add(byte[] array, byte element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, '0')       = ['0']
	 * ArrayUtils.add(['1'], '0')      = ['1', '0']
	 * ArrayUtils.add(['1', '0'], '1') = ['1', '0', '1']
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:08:58
	 */
	public static char[] add(char[] array, char element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:09:38
	 */
	public static double[] add(double[] array, double element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:10:38
	 */
	public static float[] add(float[] array, float element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:11:38
	 */
	public static int[] add(int[] array, int element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:11:58
	 */
	public static long[] add(long[] array, long element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 拷贝给定的数组到新数组, 并在最后添加给定的值
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的值
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0)   = [0]
	 * ArrayUtils.add([1], 0)    = [1, 0]
	 * ArrayUtils.add([1, 0], 1) = [1, 0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 添加到最后的元素, 可以为 {@code null}
	 * @return 一个包含所有给定数组元素及放在最后的指定的值的新数组.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-29 下午11:12:11
	 */
	public static short[] add(short[] array, short element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素. 新数组的元素类型和输入数组的元素类型总是一样.
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0, null)      = [null]
	 * ArrayUtils.add(null, 0, "a")       = ["a"]
	 * ArrayUtils.add(["a"], 1, null)     = ["a", null]
	 * ArrayUtils.add(["a"], 1, "b")      = ["a", "b"]
	 * ArrayUtils.add(["a", "b"], 3, "c") = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param <T> 数组元素的类型
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static <T> T[] add(T[] array, int index, T element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0, true)          = [true]
	 * ArrayUtils.add([true], 0, false)       = [false, true]
	 * ArrayUtils.add([false], 1, true)       = [false, true]
	 * ArrayUtils.add([true, false], 1, true) = [true, true, false]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static boolean[] add(boolean[] array, int index, boolean element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add(null, 0, 'a')            = ['a']
	 * ArrayUtils.add(['a'], 0, 'b')           = ['b', 'a']
	 * ArrayUtils.add(['a', 'b'], 0, 'c')      = ['c', 'a', 'b']
	 * ArrayUtils.add(['a', 'b'], 1, 'k')      = ['a', 'k', 'b']
	 * ArrayUtils.add(['a', 'b', 'c'], 1, 't') = ['a', 't', 'b', 'c']
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static char[] add(char[] array, int index, char element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1], 0, 2)         = [2, 1]
	 * ArrayUtils.add([2, 6], 2, 3)      = [2, 6, 3]
	 * ArrayUtils.add([2, 6], 0, 1)      = [1, 2, 6]
	 * ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static byte[] add(byte[] array, int index, byte element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1], 0, 2)         = [2, 1]
	 * ArrayUtils.add([2, 6], 2, 10)     = [2, 6, 10]
	 * ArrayUtils.add([2, 6], 0, -4)     = [-4, 2, 6]
	 * ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static short[] add(short[] array, int index, short element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1], 0, 2)         = [2, 1]
	 * ArrayUtils.add([2, 6], 2, 10)     = [2, 6, 10]
	 * ArrayUtils.add([2, 6], 0, -4)     = [-4, 2, 6]
	 * ArrayUtils.add([2, 6, 3], 2, 1)   = [2, 6, 1, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static int[] add(int[] array, int index, int element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1L], 0, 2L)           = [2L, 1L]
	 * ArrayUtils.add([2L, 6L], 2, 10L)      = [2L, 6L, 10L]
	 * ArrayUtils.add([2L, 6L], 0, -4L)      = [-4L, 2L, 6L]
	 * ArrayUtils.add([2L, 6L, 3L], 2, 1L)   = [2L, 6L, 1L, 3L]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static long[] add(long[] array, int index, long element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1.1f], 0, 2.2f)               = [2.2f, 1.1f]
	 * ArrayUtils.add([2.3f, 6.4f], 2, 10.5f)        = [2.3f, 6.4f, 10.5f]
	 * ArrayUtils.add([2.6f, 6.7f], 0, -4.8f)        = [-4.8f, 2.6f, 6.7f]
	 * ArrayUtils.add([2.9f, 6.0f, 0.3f], 2, 1.0f)   = [2.9f, 6.0f, 1.0f, 0.3f]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static float[] add(float[] array, int index, float element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 插入指定的元素到数组中指定的位置, 指定位置及右边的所有元素都将右移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含所有指定数组的元素, 加上在指定位置插入的元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 返回的新数组只包含给定的元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.add([1.1], 0, 2.2)              = [2.2, 1.1]
	 * ArrayUtils.add([2.3, 6.4], 2, 10.5)        = [2.3, 6.4, 10.5]
	 * ArrayUtils.add([2.6, 6.7], 0, -4.8)        = [-4.8, 2.6, 6.7]
	 * ArrayUtils.add([2.9, 6.0, 0.3], 2, 1.0)    = [2.9, 6.0, 1.0, 0.3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 插入的位置
	 * @param element 要插入的元素
	 * @return 一个包含输入数组所有元素及指定元素的数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index > array.length).
	 * @throws IllegalArgumentException 如果输入的数组和元素都为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午09:44:11
	 */
	public static double[] add(double[] array, int index, double element) {
		return org.apache.commons.lang3.ArrayUtils.add(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素, 新数组的元素类型和输入数组的元素类型总是一样.
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove(["a"], 0)           = []
	 * ArrayUtils.remove(["a", "b"], 0)      = ["b"]
	 * ArrayUtils.remove(["a", "b"], 1)      = ["a"]
	 * ArrayUtils.remove(["a", "b", "c"], 1) = ["a", "c"]
	 * </pre>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static <T> T[] remove(T[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素, 新数组的元素类型和输入数组的元素类型总是一样.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, "a")            = null
	 * ArrayUtils.removeElement([], "a")              = []
	 * ArrayUtils.removeElement(["a"], "b")           = ["a"]
	 * ArrayUtils.removeElement(["a", "b"], "a")      = ["b"]
	 * ArrayUtils.removeElement(["a", "b", "a"], "a") = ["b", "a"]
	 * </pre>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static <T> T[] removeElement(T[] array, Object element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([true], 0)              = []
	 * ArrayUtils.remove([true, false], 0)       = [false]
	 * ArrayUtils.remove([true, false], 1)       = [true]
	 * ArrayUtils.remove([true, true, false], 1) = [true, false]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static boolean[] remove(boolean[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, true)                = null
	 * ArrayUtils.removeElement([], true)                  = []
	 * ArrayUtils.removeElement([true], false)             = [true]
	 * ArrayUtils.removeElement([true, false], false)      = [true]
	 * ArrayUtils.removeElement([true, false, true], true) = [false, true]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static boolean[] removeElement(boolean[] array, boolean element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1], 0)          = []
	 * ArrayUtils.remove([1, 0], 0)       = [0]
	 * ArrayUtils.remove([1, 0], 1)       = [1]
	 * ArrayUtils.remove([1, 0, 1], 1)    = [1, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static byte[] remove(byte[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1)        = null
	 * ArrayUtils.removeElement([], 1)          = []
	 * ArrayUtils.removeElement([1], 0)         = [1]
	 * ArrayUtils.removeElement([1, 0], 0)      = [1]
	 * ArrayUtils.removeElement([1, 0, 1], 1)   = [0, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static byte[] removeElement(byte[] array, byte element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove(['a'], 0)           = []
	 * ArrayUtils.remove(['a', 'b'], 0)      = ['b']
	 * ArrayUtils.remove(['a', 'b'], 1)      = ['a']
	 * ArrayUtils.remove(['a', 'b', 'c'], 1) = ['a', 'c']
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static char[] remove(char[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 'a')            = null
	 * ArrayUtils.removeElement([], 'a')              = []
	 * ArrayUtils.removeElement(['a'], 'b')           = ['a']
	 * ArrayUtils.removeElement(['a', 'b'], 'a')      = ['b']
	 * ArrayUtils.removeElement(['a', 'b', 'a'], 'a') = ['b', 'a']
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static char[] removeElement(char[] array, char element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1.1], 0)           = []
	 * ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
	 * ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
	 * ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static double[] remove(double[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1.1)            = null
	 * ArrayUtils.removeElement([], 1.1)              = []
	 * ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
	 * ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
	 * ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static double[] removeElement(double[] array, double element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1.1], 0)           = []
	 * ArrayUtils.remove([2.5, 6.0], 0)      = [6.0]
	 * ArrayUtils.remove([2.5, 6.0], 1)      = [2.5]
	 * ArrayUtils.remove([2.5, 6.0, 3.8], 1) = [2.5, 3.8]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static float[] remove(float[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1.1)            = null
	 * ArrayUtils.removeElement([], 1.1)              = []
	 * ArrayUtils.removeElement([1.1], 1.2)           = [1.1]
	 * ArrayUtils.removeElement([1.1, 2.3], 1.1)      = [2.3]
	 * ArrayUtils.removeElement([1.1, 2.3, 1.1], 1.1) = [2.3, 1.1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static float[] removeElement(float[] array, float element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1], 0)         = []
	 * ArrayUtils.remove([2, 6], 0)      = [6]
	 * ArrayUtils.remove([2, 6], 1)      = [2]
	 * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static int[] remove(int[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1)      = null
	 * ArrayUtils.removeElement([], 1)        = []
	 * ArrayUtils.removeElement([1], 2)       = [1]
	 * ArrayUtils.removeElement([1, 3], 1)    = [3]
	 * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static int[] removeElement(int[] array, int element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1], 0)         = []
	 * ArrayUtils.remove([2, 6], 0)      = [6]
	 * ArrayUtils.remove([2, 6], 1)      = [2]
	 * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static long[] remove(long[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1)      = null
	 * ArrayUtils.removeElement([], 1)        = []
	 * ArrayUtils.removeElement([1], 2)       = [1]
	 * ArrayUtils.removeElement([1, 3], 1)    = [3]
	 * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static long[] removeElement(long[] array, long element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中的指定位置移除对应的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.remove([1], 0)         = []
	 * ArrayUtils.remove([2, 6], 0)      = [6]
	 * ArrayUtils.remove([2, 6], 1)      = [2]
	 * ArrayUtils.remove([2, 6, 3], 1)   = [2, 3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param index 要移除的元素的下标
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午10:14:02
	 */
	public static short[] remove(short[] array, int index) {
		return org.apache.commons.lang3.ArrayUtils.remove(array, index);
	}

	/**
	 * <p>
	 * 从数组中移除第一个出现的指定元素, 其后的元素依次左移. 如果数组中没有包含指定的元素, 不会有任何元素从数组中移除.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了第一个出现的指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElement(null, 1)      = null
	 * ArrayUtils.removeElement([], 1)        = []
	 * ArrayUtils.removeElement([1], 2)       = [1]
	 * ArrayUtils.removeElement([1, 3], 1)    = [3]
	 * ArrayUtils.removeElement([1, 3, 1], 1) = [3, 1]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param element 要移除的元素
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:18:36
	 */
	public static short[] removeElement(short[] array, short element) {
		return org.apache.commons.lang3.ArrayUtils.removeElement(array, element);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素, 新数组的元素类型和输入数组的元素类型总是一样.
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll(["a", "b", "c"], 0, 2) = ["b"]
	 * ArrayUtils.removeAll(["a", "b", "c"], 1, 2) = ["a"]
	 * </pre>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static <T> T[] removeAll(T[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素, 新数组的元素类型和输入数组的元素类型总是一样.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, "a", "b")            = null
	 * ArrayUtils.removeElements([], "a", "b")              = []
	 * ArrayUtils.removeElements(["a"], "b", "c")           = ["a"]
	 * ArrayUtils.removeElements(["a", "b"], "a", "c")      = ["b"]
	 * ArrayUtils.removeElements(["a", "b", "a"], "a")      = ["b", "a"]
	 * ArrayUtils.removeElements(["a", "b", "a"], "a", "a") = ["b"]
	 * </pre>
	 * 
	 * @param <T> 数组元素类型
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static <T> T[] removeElements(T[] array, T... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static byte[] removeAll(byte[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static byte[] removeElements(byte[] array, byte... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static short[] removeAll(short[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static short[] removeElements(short[] array, short... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static int[] removeAll(int[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static int[] removeElements(int[] array, int... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static char[] removeAll(char[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static char[] removeElements(char[] array, char... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static long[] removeAll(long[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static long[] removeElements(long[] array, long... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static float[] removeAll(float[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static float[] removeElements(float[] array, float... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([1], 0)             = []
	 * ArrayUtils.removeAll([2, 6], 0)          = [6]
	 * ArrayUtils.removeAll([2, 6], 0, 1)       = []
	 * ArrayUtils.removeAll([2, 6, 3], 1, 2)    = [2]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 2)    = [6]
	 * ArrayUtils.removeAll([2, 6, 3], 0, 1, 2) = []
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static double[] removeAll(double[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, 1, 2)      = null
	 * ArrayUtils.removeElements([], 1, 2)        = []
	 * ArrayUtils.removeElements([1], 2, 3)       = [1]
	 * ArrayUtils.removeElements([1, 3], 1, 2)    = [3]
	 * ArrayUtils.removeElements([1, 3, 1], 1)    = [3, 1]
	 * ArrayUtils.removeElements([1, 3, 1], 1, 1) = [3]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static double[] removeElements(double[] array, double... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	/**
	 * <p>
	 * 从数组中移除所有指定位置的元素, 其后的元素依次左移
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定位置外的所有元素
	 * </p>
	 * 
	 * <p>
	 * 如果输入的数组为{@code null}, 将抛出IndexOutOfBoundsException异常,因此这样没有可用的位置可以指定.
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeAll([true, false, true], 0, 2) = [false]
	 * ArrayUtils.removeAll([true, false, true], 1, 2) = [true]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param indices 要移除的元素的下标可变数组
	 * @return 一个包含原数组除了指定位置对应元素外的所有元素的新数组
	 * @throws IndexOutOfBoundsException 如果下标越界 (index < 0 || index >= array.length), 或如果数组参数为 {@code null}.
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:30:21
	 */
	public static boolean[] removeAll(boolean[] array, int... indices) {
		return org.apache.commons.lang3.ArrayUtils.removeAll(array, indices);
	}

	/**
	 * <p>
	 * 从数组中移除所有出现的指定元素, 其后的元素依次左移.如果指定要移除的元素不在数组中,将只移除存在数组中的元素
	 * </p>
	 * 
	 * <p>
	 * 该方法返回一个新的数组, 它包含指定数组的除了指定元素外的所有元素
	 * </p>
	 * 
	 * <pre>
	 * ArrayUtils.removeElements(null, true, false)               = null
	 * ArrayUtils.removeElements([], true, false)                 = []
	 * ArrayUtils.removeElements([true], false, false)            = [true]
	 * ArrayUtils.removeElements([true, false], true, true)       = [false]
	 * ArrayUtils.removeElements([true, false, true], true)       = [false, true]
	 * ArrayUtils.removeElements([true, false, true], true, true) = [false]
	 * </pre>
	 * 
	 * @param array 数组, 可以为 {@code null}
	 * @param values 要从数组中移除的值
	 * @return 一个包含原数组除了要移除的元素外的所有元素的新数组
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-30 上午11:38:01
	 */
	public static boolean[] removeElements(boolean[] array, boolean... values) {
		return org.apache.commons.lang3.ArrayUtils.removeElements(array, values);
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.ArrayUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

package com.kvc.joy.commons.lang.reflect;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.*;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 类型工具类
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-2 下午10:59:49
 */
public class TypeTool {
	
	private TypeTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.reflect.TypeUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 检查一个类型是否能被在遵循java类型规则下隐式地转化成另一个类型. <br>
	 * 如果两个类型都为{@link Class}对象, 该方法返回{@link ClassUtils#isAssignable(Class, Class)}的结果.
	 * </p>
	 * 
	 * @param type 被赋值给目标类型的主题类型
	 * @param toType 目标类型
	 * @return <code>true</code> 如果 <code>type</code> 能够被赋值给 <code>toType</code>.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午6:24:58
	 */
	public static boolean isAssignable(Type type, Type toType) {
		return org.apache.commons.lang3.reflect.TypeUtils.isAssignable(type, toType);
	}

	/**
	 * <p>
	 * 获取指定参数化类型的所有类型参数, 包括所有者体系的参数, 如: <code>Outer<K,V>.Inner<T>.DeepInner<E></code> .<br> 
	 * 参数将以{@link Map}的形式返回, key为{@link TypeVariable}
	 * </p>
	 * 
	 * @param type 指定的要获取类型参数的主题参数化类型
	 * @return 类型参数的map
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午6:35:42
	 */
	public static Map<TypeVariable<?>, Type> getTypeArguments(ParameterizedType type) {
		return org.apache.commons.lang3.reflect.TypeUtils.getTypeArguments(type);
	}

	/**
	 * <p>
	 * 获取基于子类型的类或接口的类型参数. 例如, 该方法将确定{@link Map}接口的两个参数都是子类型{@link java.util.Properties Properties}的对象,
	 * 即使子类型没有直接实现<code>Map</code>接口.
	 * </p>
	 * 
	 * <p>
	 * 该方法返回<code>null</code>如果<code>type</code>不能赋值给<code>toClass</code>.
	 * 返回空的Map如果继承体系中没有任何的类或接口指定任何类型参数.
	 * </p>
	 * 
	 * <p>
	 * 该方法的一个副作用为, 它同样会获取作为<code>type</code> 和 <code>toClass</code>层次结构的一部分类和接口的类型参数. <br>
	 * 所以对于上述的例子, 该方法将同样确定{@link java.util.Hashtable Hashtable}的类型参数是否都为<code>Object</code>. <br>
	 * 在一些情况下, <code>toClass</code>指定的接口(间接)被实现不只一次(如: <code>toClass</code>指定为{@link java.lang.Iterable Iterable}
	 * 接口, <code>type</code>指定一个实现{@link java.util.Set Set}和{@link java.util.Collection Collection}的参数化类型)<br>
	 * 该方法将只在继承体系结构中查找一个实现或子类；遇到的第一个接口将不是<code>type</code> 到 <code>toClass</code>层次结构中的一个子接口.
	 * </p>
	 * 
	 * @param type the type from which to determine the type parameters of toclass
	 * @param toClass the class whose type parameters are to be determined based on the subtype <code>type</code>
	 * @return a map of the type assignments for the type variables in each type in the inheritance hierarchy from
	 *         <code>type</code> to <code>toClass</code> inclusive.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午7:08:57
	 */
	public static Map<TypeVariable<?>, Type> getTypeArguments(Type type, Class<?> toClass) {
		return org.apache.commons.lang3.reflect.TypeUtils.getTypeArguments(type, toClass);
	}
	
	/**
	 * <p>
	 * 试图确定一个基于父类的参数化类型的类型参数
	 * {@link #getTypeArguments(Type, Class)}正好与该方法相反, 它获取一个类或接口的基于子类型的类型参数. <br>
	 * 它在为主题的类的类型变量确定类型参数方面有更多限制, 它只能确定那些从主题{@link Class}对象映射到子类型的参数.
	 * </p>
	 * <p>
	 * 例如: {@link java.util.TreeSet TreeSet} 设置它的参数作为{@link java.util.NavigableSet NavigableSet}的参数, <br>
	 * 这将反过来又设置{@link java.util.SortedSet}的参数, 这将反过来又设置{{@link Set}}的参数, 这将反过来又设置<br>
	 * {@link java.util.Collection}的参数, {@link java.lang.Iterable}的参数.因为<code>TreeSet</code>的参数映射为<br>
	 * <code>Iterable</code>的参数, 它将可以确定基于父类型<code>Iterable<? extends Map<Integer,? extends Collection<?>>></code>, <br> 
	 * <code>TreeSet</code>的参数为<code>? extends Map<Integer,? extends Collection<?>></code>.
	 * </p>
	 * 
	 * @param cls the class whose type parameters are to be determined
	 * @param superType the super type from which <code>cls</code>'s type arguments are to be determined
	 * @return a map of the type assignments that could be determined for the type variables in each type in the
	 *         inheritance hierarchy from <code>type</code> to <code>toClass</code> inclusive.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午7:23:41
	 */
	public static Map<TypeVariable<?>, Type> determineTypeArguments(Class<?> cls, ParameterizedType superType) {
		return org.apache.commons.lang3.reflect.TypeUtils.determineTypeArguments(cls, superType);
	}

	/**
	 * <p>
	 * 检查是否给定的对象能按java的类型规则被赋值给目标类型
	 * </p>
	 * 
	 * @param value 要检查的对象
	 * @param type 目标类型
	 * @return true 如果<code>value</code> 为 <code>type</code>的一个实例
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:02:45
	 */
	public static boolean isInstance(Object value, Type type) {
		return org.apache.commons.lang3.reflect.TypeUtils.isInstance(value, type);
	}

	/**
	 * <p>
	 * 此方法在类型变量类型和通配符类型（或带通配符的类型，如果有多个上限被允许)上去除冗余的上限类型 。
	 * </p>
	 * 
	 * <p>
	 * 例如: 变量类型申明如下:
	 * 
	 * <pre>
	 * &lt;K extends java.util.Collection&lt;String&gt; &amp;
	 * java.util.List&lt;String&gt;&gt;
	 * </pre>
	 * 
	 * 因为 <code>List</code> 是 <code>Collection</code>的一个子接口, 该方法返回的边界如下:
	 * 
	 * <pre>
	 * &lt;K extends java.util.List&lt;String&gt;&gt;
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param bounds 代表通配符或类型变量的上限类型的数组
	 * @return 一个包含去除了冗余的上限类型的类型数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:12:13
	 */
	public static Type[] normalizeUpperBounds(Type[] bounds) {
		return org.apache.commons.lang3.reflect.TypeUtils.normalizeUpperBounds(bounds);
	}

	/**
	 * <p>
	 * 返回一个包含{@link Object}的范围类型的数组, 如果{@link TypeVariable#getBounds()}返回一个空数组. 否则, 返回将
	 * <code>TypeVariable.getBounds()传给{@link #normalizeUpperBounds}方法的结果.
	 * </p>
	 * 
	 * @param typeVariable 主题类型变量 
	 * @return 一个包含类型变量边界的非空数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:18:17
	 */
	public static Type[] getImplicitBounds(TypeVariable<?> typeVariable) {
		return org.apache.commons.lang3.reflect.TypeUtils.getImplicitBounds(typeVariable);
	}

	/**
	 * <p>
	 * 返回一个包含惟一{@link Object}值的数组, 如果{@link WildcardType#getUpperBounds()}返回一个空数组. <br>
	 * 否则, 返回将<code>WildcardType.getUpperBounds()</code>传给{@link #normalizeUpperBounds}方法的结果.
	 * </p>
	 * 
	 * @param wildcardType 主题通配符类型
	 * @return 一个包含通配符的上限边界的非空数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:22:26
	 */
	public static Type[] getImplicitUpperBounds(WildcardType wildcardType) {
		return org.apache.commons.lang3.reflect.TypeUtils.getImplicitUpperBounds(wildcardType);
	}

	/**
	 * <p>
	 * 返回一个包含<code>null</code>单一值的数组, 如果{@link WildcardType#getLowerBounds()}返回一个空数组.
	 * 否则, 返回<code>WildcardType.getLowerBounds()</code>的结果.
	 * </p>
	 * 
	 * @param wildcardType 主题通配符类型
	 * @return 一个包含通配符的下限边界的非空数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:24:03
	 */
	public static Type[] getImplicitLowerBounds(WildcardType wildcardType) {
		return org.apache.commons.lang3.reflect.TypeUtils.getImplicitLowerBounds(wildcardType);
	}

	/**
	 * <p>
	 * 确定是否指定类型满足它们映射的类型变量的边界. 当一个类型参数继承另一个(如<code><T, S extends T></code>), <br>
	 * 使用另一个当一个类型参数(如<code><T, S extends Comparable<T></code>), 或依赖于另一个指定的类型变量, 该依赖必须<br>
	 * 在被包含在 <code>typeVarAssigns</code> 中.
	 * </p>
	 * 
	 * @param typeVarAssigns 指定的要被分配给类型变量的潜在类型
	 * @return 是否指定的类型能被赋值给各自的类型变量 
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:32:15
	 */
	public static boolean typesSatisfyVariables(Map<TypeVariable<?>, Type> typeVarAssigns) {
		return org.apache.commons.lang3.reflect.TypeUtils.typesSatisfyVariables(typeVarAssigns);
	}

	/**
	 * <p>
	 * 从上下文获取java的原始类型. 主要用于{@link TypeVariable}和{@link GenericArrayType}, 或当你不知道<code>type</code>的 <br>
	 * 运行时类型时: 如果你知道你有一个{@link Class}实例, 它已经是原始类型；如果你知道你有一个{@link ParameterizedType}, <br>
	 * 它的原始类型只有一个方法调用.
	 * </p>
	 * 
	 * @param type to resolve
	 * @param assigningType type to be resolved against
	 * @return the resolved <code>Class</code> object or <code>null</code> if the type could not be resolved
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:52:38
	 */
	public static Class<?> getRawType(Type type, Type assigningType) {
		return org.apache.commons.lang3.reflect.TypeUtils.getRawType(type, assigningType);
	}

	/**
	 * <p>
	 * 检测指定的类型是否为数组类型
	 * </p>
	 * 
	 * @param type 要检测的类型
	 * @return <code>true</code> 如果 <code>type</code> 为数组类型 或 {@link GenericArrayType}.
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:53:54
	 */
	public static boolean isArrayType(Type type) {
		return org.apache.commons.lang3.reflect.TypeUtils.isArrayType(type);
	}

	/**
	 * <p>
	 * 获取数组的元素类型
	 * </p>
	 * 
	 * @param type 要检测的类型
	 * @return 元素类型或如果指定的类型不是一个数组类型时返回null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-4 下午8:55:35
	 */
	public static Type getArrayComponentType(Type type) {
		return org.apache.commons.lang3.reflect.TypeUtils.getArrayComponentType(type);
	}
	
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.reflect.TypeUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

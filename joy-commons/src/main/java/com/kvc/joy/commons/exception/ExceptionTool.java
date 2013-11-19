package com.kvc.joy.commons.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

/**
 * <p>
 * 异常处理工具类
 * </p>
 * 
 * @since 1.0.0
 * @author <b>唐玮琳</b>
 */
public class ExceptionTool {

	protected static final Log logger = LogFactory.getLog(ExceptionTool.class);

	/**
	 * <p>
	 * 当调用的方法栈里不含指定类名时，把栈信息打印到日志中.
	 * 该方法可用于信息跟踪，如：跟踪资源是否被关闭。
	 * 该方法只有在日志级别为DEBUG时才有效
	 * </p>
	 * 
	 * @param clazz 类，为null将什么也不做
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午7:36:59
	 */
	public static void printStackTraceOnNotCallByClass(Class<?> clazz) {
		if(clazz == null) {
			return ;
		}
		if (logger.isDebugEnabled()) {
			boolean find = false;
			StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
			for (StackTraceElement elem : stackTrace) {
				if (elem.getClassName().equals(clazz.getName())) {
					find = true;
					break;
				}
			}
			if (find == false) {
				logger.warn("方法栈里不含指定类: " + clazz);
				for (StackTraceElement elem : stackTrace) {
					logger.warn(elem.toString());
				}
			}
		}
	}

	/**
	 * <p>
	 * 抛出异常，打印方法调用栈日志.
	 * 该方法只有在日志级别为DEBUG时才有效
	 * </p>
	 * 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午7:38:59
	 */
	public static void printStackTrace() {
		if (logger.isDebugEnabled()) {
			StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
			for (StackTraceElement elem : stackTrace) {
				logger.debug(elem.toString());
			}
		}
	}

	/**
	 * <p>
	 * 从方法调用栈里，找出首次出现类名包含给定字符串的栈轨迹元素(不包含ExceptionUtils类本身和指定的类).
	 * </p>
	 * 
	 * @param str 全类名的子串, 可以为null，为null或空将返回null
	 * @param excludeClasses 排除的类，为null或空将不排除任何类
	 * @return 首次出现类名包含给定字符串的栈轨迹元素，找不到或指定的类名为null或空将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午7:40:46
	 */
	public static StackTraceElement findFirstStackTraceElem(String str, Class<?>... excludeClasses) {
		if (StringTool.isBlank(str)) {
			return null;
		}
		if (excludeClasses == null) {
			excludeClasses = new Class[0];
		}
		Set<String> classNames = new HashSet<String>(excludeClasses.length + 1);
		classNames.add(ExceptionTool.class.getName());
		for (Class<?> clazz : excludeClasses) {
			classNames.add(clazz.getName());
		}

		StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
		for (int i = 1; i < stackTrace.length; i++) {
			StackTraceElement elem = stackTrace[i];
			String className = elem.getClassName();
			if (className.contains(str) && !classNames.contains(className)) {
				return elem;
			}
		}
		return null;
	}
	
	/**
	 * <p>
	 * 解包装SystemException异常(取得最近一个不是SystemException的异常)
	 * </p>
	 * 
	 * @param e 异常，可以为null，为null将返回null
	 * @return 最近一个不是SystemException的异常，指定异常为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-5 下午11:49:20
	 */
	public static Throwable unwrapSystemException(Throwable e) {
		while(e instanceof SystemException) {
			e = e.getCause();
		}
		return e;
	}

	/**
	 * <p>
	 * 将需要强制捕获的异常包装为RuntimeException.
	 * </p>
	 * 
	 * @param e 异常，可以为null，为null将返回null
	 * @return 包装了指定异常的RuntimeException，指定异常为null将返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午7:53:35
	 */
	public static RuntimeException toRuntimeException(Throwable e) {
		if(e == null) {
			return null;
		}
		if (e instanceof RuntimeException) {
			return (RuntimeException) e;
		} else {
			return new RuntimeException(e);
		}
	}

	/**
	 * <p>
	 * 判断异常是否由某些底层的异常引起.
	 * </p>
	 * 
	 * @param ex 异常, 可以为null，为null将返回null
	 * @param causeExceptionClasses 底层异常的可变数组, 可以为null，为null或为空将返回null
	 * @return true: 指定的异常是由指定的底层异常之一引起的， 否则为false
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午7:54:24
	 */
	public static boolean isCausedBy(Throwable ex, Class<? extends Throwable>... causeExceptionClasses) {
		if(ex != null && causeExceptionClasses != null) {
			Throwable cause = ex.getCause();
			while (cause != null) {
				for (Class<? extends Throwable> causeClass : causeExceptionClasses) {
					if (causeClass.isInstance(cause)) {
						return true;
					}
				}
				cause = cause.getCause();
			}
		}
		return false;
	}
	
	// ----------------------------------------------------------------------------
	// 封装org.apache.commons.lang3.exception.ExceptionUtils
	// ----------------------------------------------------------------------------

	/**
	 * <p>
	 * 返回指定异常的根异常
	 * </p>
	 * 
	 * <p>
	 * 该方法查找异常链上的最后一个元素，树的根。使用{@link #getCause(Throwable)}方法
	 * 返回该异常。
	 * </p>
	 * 
	 * @param throwable 要查找根异常的异常, 可以为null
	 * @return 指定异常的根异常, 如果没有找到或输入null则返回null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:03:22
	 */
	public static Throwable getRootCause(Throwable throwable) {
		return ExceptionUtils.getRootCause(throwable);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 计算异常链中出现的异常个数。
	 * </p>
	 * 
	 * <p>
	 * 没有底层异常时将返回1.
	 * 含有一个底层异常时将返回2，依此类推。
	 * 输入为null将返回0
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null
	 * @return 异常数量, 输入null将返回0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:19:45
	 */
	public static int getThrowableCount(Throwable throwable) {
		return ExceptionUtils.getThrowableCount(throwable);
	}

	/**
	 * <p>
	 * 以数组的形式返回异常链中的所有异常
	 * </p>
	 * 
	 * @see #getThrowableList(Throwable)
	 * @param throwable 要检查的异常，可以为null，为null时将返回空数组
	 * @return 异常数组，不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:22:26
	 */
	public static Throwable[] getThrowables(Throwable throwable) {
		return ExceptionUtils.getThrowables(throwable);
	}

	/**
	 * <p>
	 * 以列表的形式返回异常链中的所有异常
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null，为null时将返回空列表
	 * @return 异常列表，不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:23:26
	 */
	public static List<Throwable> getThrowableList(Throwable throwable) {
		return ExceptionUtils.getThrowableList(throwable);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回异常链中第一个匹配(精确)指定类型的异常的下标(从0开始)。
	 * 指定类型的子类型不匹配，若要匹配子类型，请参考{@link #indexOfType(Throwable, Class)}.
	 * </p>
	 * 
	 * <p>
	 * 指定的异常或类型为null或找不到匹配的异常都将返回-1。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null
	 * @param clazz 要查找的异常类型, 子类型不匹配, null将返回-1 
	 * @return 匹配的异常在异常链中的下标, 任意参数为null或没找到都将返回-1
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午7:29:52
	 */
	public static int indexOfThrowable(Throwable throwable, Class<?> clazz) {
		return ExceptionUtils.indexOfThrowable(throwable, clazz);
	}

	/**
	 * <p>
	 * 返回异常链中从指定下标开始，第一个匹配(精确)指定类型的异常的下标(从0开始)。
	 * 指定类型的子类型不匹配，若要匹配子类型，请参考{@link #indexOfType(Throwable, Class, int)}.
	 * </p>
	 * 
	 * <p>
	 * 指定的异常或类型为null或找不到匹配的异常都将返回-1。
	 * 指定的下标为负数时将当作0，大于异常链中异常总个数时返回-1。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null
	 * @param clazz 要查找的异常类型, 子类型不匹配, null将返回-1 
	 * @param fromIndex 开始查找的异常链的下标，负数时将当作0，大于异常链中异常总个数时返回-1
	 * @return 匹配的异常在异常链中的下标, 任意参数为null或开始查找的下标大于异常链中异常总个数或没找到都将返回-1
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午7:40:31
	 */
	public static int indexOfThrowable(Throwable throwable, Class<?> clazz, int fromIndex) {
		return ExceptionUtils.indexOfThrowable(throwable, clazz, fromIndex);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回异常链中第一个匹配指定类型或子类型的异常的下标(从0开始)。
	 * 会匹配指定类型的子类型，若不想匹配子类型，请参考{@link #indexOfThrowable(Throwable, Class)}.
	 * </p>
	 * 
	 * <p>
	 * 指定的异常或类型为null或找不到匹配的异常都将返回-1。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null
	 * @param type 要查找的异常类型, 匹配子类型, null将返回-1 
	 * @return 匹配的异常在异常链中的下标, 任意参数为null或没找到都将返回-1
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午9:28:27
	 */
	public static int indexOfType(Throwable throwable, Class<?> type) {
		return ExceptionUtils.indexOfType(throwable, type);
	}

	/**
	 * <p>
	 * 返回异常链中从指定下标开始的第一个匹配指定类型或子类型的异常的下标(从0开始)。
	 * 会匹配指定类型的子类型，若不想匹配子类型，请参考{@link #indexOfThrowable(Throwable, Class, int)}.
	 * </p>
	 * 
	 * <p>
	 * 指定的异常或类型为null或找不到匹配的异常都将返回-1。
	 * 指定的下标为负数时将当作0，大于异常链中异常总个数时返回-1。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null
	 * @param type 要查找的异常类型, 匹配子类型, null将返回-1
	 * @param 开始查找的异常链的下标，负数时将当作0，大于异常链中异常总个数时返回-1
	 * @return 匹配的异常在异常链中的下标, 任意参数为null或开始查找的下标大于异常链中异常总个数或没找到都将返回-1
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午9:30:49
	 */
	public static int indexOfType(Throwable throwable, Class<?> type, int fromIndex) {
		return ExceptionUtils.indexOfType(throwable, type, fromIndex);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 打印指定异常的根异常的压缩版轨迹到<code>System.err</code>
	 * </p>
	 * 
	 * <p>
	 * 从根异常开始，打印堆栈帧直到异常被捕获并再次被包装的地方。
	 * 然后打印包装的异常，并继续打印栈帧，直到包装异常被捕获并再次被包装，依此进行。
	 * </p>
	 * 
	 * <p>
	 * 该方法的输出在不同版本的jdk上是一致的。
	 * 需要注意的是这与jdk1.4的显示顺序相反。
	 * </p>
	 * 
	* <p>
	 * 该方法等效于打印没有嵌套异常的<code>printStackTrace</code>方法
	 * </p>
	 * 
	 * @param throwable 要输出的异常, 可以为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午9:55:53
	 */
	public static void printRootCauseStackTrace(Throwable throwable) {
		ExceptionUtils.printRootCauseStackTrace(throwable);
	}

	/**
	 * <p>
	 * 打印指定异常的根异常的压缩版轨迹
	 * </p>
	 * 
	 * <p>
	 * 从根异常开始，打印堆栈帧直到异常被捕获并再次被包装的地方。
	 * 然后打印包装的异常，并继续打印栈帧，直到包装异常被捕获并再次被包装，依此进行。
	 * </p>
	 * 
	 * <p>
	 * 该方法的输出在不同版本的jdk上是一致的。
	 * 需要注意的是这与jdk1.4的显示顺序相反。
	 * </p>
	 * 
	* <p>
	 * 该方法等效于打印没有嵌套异常的<code>printStackTrace</code>方法
	 * </p>
	 * 
	 * @param throwable 要输出的异常, 可以为null
	 * @param stream 要输出到的流, 不能为null
	 * @throws IllegalArgumentException 如果stream参数为<code>null</code> 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午9:53:20
	 */
	public static void printRootCauseStackTrace(Throwable throwable, PrintStream stream) {
		ExceptionUtils.printRootCauseStackTrace(throwable, stream);
	}

	/**
	 * <p>
	 * 打印指定异常的根异常的压缩版轨迹
	 * </p>
	 * 
	 * <p>
	 * 从根异常开始，打印堆栈帧直到异常被捕获并再次被包装的地方。
	 * 然后打印包装的异常，并继续打印栈帧，直到包装异常被捕获并再次被包装，依此进行。
	 * </p>
	 * 
	 * <p>
	 * 该方法的输出在不同版本的jdk上是一致的。
	 * 需要注意的是这与jdk1.4的显示顺序相反。
	 * </p>
	 * 
	 * <p>
	 * 该方法等效于打印没有嵌套异常的<code>printStackTrace</code>方法
	 * </p>
	 * 
	 * @param throwable 要输出的异常, 可以为null
	 * @param writer 要输出到的writer, 不能为null
	 * @throws IllegalArgumentException 如果writer参数为<code>null</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-14 下午9:52:03
	 */
	public static void printRootCauseStackTrace(Throwable throwable, PrintWriter writer) {
		ExceptionUtils.printRootCauseStackTrace(throwable, writer);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 为指定的异常的根异常创建一个压缩版的栈轨迹信息
	 * </p>
	 * 
	 * <p>
	 * 该方法的输出在不同版本的jdk上是一致的。
	 * 它由根异常及每一个它的包装异常(由[wrapped]分隔)组成。
	 * 需要注意的是这与jdk1.4的显示顺序相反。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null，为null时返回空数组
	 * @return 栈轨迹帧数组, 不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午10:21:47
	 */
	public static String[] getRootCauseStackTrace(Throwable throwable) {
		return ExceptionUtils.getRootCauseStackTrace(throwable);
	}

	/**
	 * <p>
	 * 从causeFrames中移除和wrapperFrames相同的帧
	 * </p>
	 * 
	 * @param causeFrames 底层异常的栈轨迹
	 * @param wrapperFrames 包装异常的栈轨迹
	 * @throws IllegalArgumentException 如果任意参数为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午9:47:07
	 */
	public static void removeCommonFrames(List<String> causeFrames, List<String> wrapperFrames) {
		ExceptionUtils.removeCommonFrames(causeFrames, wrapperFrames);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定异常的栈轨迹的字符串表示
	 * </p>
	 * 
	 * <p>
	 * 此方法的结果根据JDK版本不同而不同，因为它使用{@link Throwable#printStackTrace(java.io.PrintWriter)}。
	 * 在JDK1.3和早期的版本，产生的异常将不会被显示出来，除非指定Throwable改变了printStackTrace。
	 * </p>
	 * 
	 * @param throwable 要检查的异常
	 * @return 由异常的<code>printStackTrace(PrintWriter)</code>方法生成的栈轨迹 
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午9:44:05
	 */
	public static String getStackTrace(Throwable throwable) {
		return ExceptionUtils.getStackTrace(throwable);
	}

	/**
	 * <p>
	 * 捕获指定异常对象关联的栈轨迹，把它分解成堆栈帧的数组
	 * </p>
	 * 
	 * <p>
	 * 此方法的结果根据JDK版本不同而不同，因为它使用{@link Throwable#printStackTrace(java.io.PrintWriter)}。
	 * 在JDK1.3和早期的版本，产生的异常将不会被显示出来，除非指定Throwable改变了printStackTrace。
	 * </p>
	 * 
	 * @param throwable 要检查的异常，可以为null，为null时将返回空数组
	 * @return 堆栈帧的数组，不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午9:21:35
	 */
	public static String[] getStackFrames(Throwable throwable) {
		return ExceptionUtils.getStackFrames(throwable);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回异常的摘要信息
	 * </p>
	 * 
	 * <p>
	 * 返回信息的格式是: 没有包名的类名:异常消息
	 * </p>
	 * 
	 * @param th 要获取消息的异常，为null将返回空串
	 * @return 异常消息，不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:38:16
	 */
	public static String getMessage(Throwable th) {
		return ExceptionUtils.getMessage(th);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回根异常的摘要信息
	 * </p>
	 * 
	 * <p>
	 * 返回信息的格式是: 没有包名的类名:异常消息
	 * </p>
	 * 
	 * @param th 要获取消息的异常，为null将返回空串
	 * @return 异常消息，不会为null
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-13 下午8:38:16
	 */
	public static String getRootCauseMessage(Throwable th) {
		return ExceptionUtils.getRootCauseMessage(th);
	}
	
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.exception.ExceptionUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}
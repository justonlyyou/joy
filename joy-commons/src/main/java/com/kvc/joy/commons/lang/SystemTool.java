package com.kvc.joy.commons.lang;

import java.io.File;

/**
 * 系统工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-9 下午9:04:55
 */
public class SystemTool {

	private SystemTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.lang3.SystemUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 获取java home目录, 并以{@code File}返回
	 * </p>
	 * 
	 * @return 目录
	 * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
	 * @see System#getProperty(String)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:52:45
	 */
	public static File getJavaHome() {
		return org.apache.commons.lang3.SystemUtils.getJavaHome();
	}

	/**
	 * <p>
	 * 获取IO临时目录, 并以{@code File}返回
	 * </p>
	 * 
	 * @return 目录
	 * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
	 * @see System#getProperty(String)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:53:40
	 */
	public static File getJavaIoTmpDir() {
		return org.apache.commons.lang3.SystemUtils.getJavaIoTmpDir();
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 获取用户目录, 并以{@code File}返回
	 * </p>
	 * 
	 * @return 目录
	 * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
	 * @see System#getProperty(String)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:55:22
	 */
	public static File getUserDir() {
		return org.apache.commons.lang3.SystemUtils.getUserDir();
	}

	/**
	 * <p>
	 * 获取用户home目录, 并以{@code File}返回
	 * </p>
	 * 
	 * @return 目录
	 * @throws SecurityException 如果安全管理器存在并且它的 {@code checkPropertyAccess} 方法不允许访问特别的系统属性
	 * @see System#getProperty(String)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:55:30
	 */
	public static File getUserHome() {
		return org.apache.commons.lang3.SystemUtils.getUserHome();
	}

	/**
	 * 检测 {@link #JAVA_AWT_HEADLESS} 値是否为 {@code true}.
	 * 
	 * @return {@code true} 如果 {@code JAVA_AWT_HEADLESS} 为 {@code "true"}, 否则返回 {@code false}.
	 * @see #JAVA_AWT_HEADLESS
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:56:37
	 */
	public static boolean isJavaAwtHeadless() {
		return org.apache.commons.lang3.SystemUtils.isJavaAwtHeadless();
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.lang3.SystemUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

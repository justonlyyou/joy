package org.joy.commons.io;

import java.net.URL;

/**
 * 路径工具类
 *
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-21 下午10:14:45
 */
public class PathTool {

	private PathTool() {
	}

	/**
	 * <p>
	 * 获取类路径
	 * </p>
	 * 
	 * @return 类路径
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-21 下午10:14:45
	 */
	public static String getClassesPath() {
		URL url = PathTool.class.getProtectionDomain().getCodeSource().getLocation();
		String path = url.toString();
		int index = path.indexOf("classes");
		if(index > 0) {
			return url.getPath();
		} else {
			return FilenameTool.normalize(url.getPath() + "/..");
		}
	}

	/**
	 * <p>
	 * 获取web工程的WEB-INF目录的路径,
	 * 该实现假设WEB-INF目录是类路径的父目录，如果不是，请不要使用该方法
	 * </p>
	 * 
	 * @return web工程的WEB-INF目录的路径,
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-21 下午10:16:05
	 */
	public static String getWebInfPath() {
		String path = getClassesPath();
		path += "..";
		return FilenameTool.normalize(path);
	}

	/**
	 * <p>
	 * 获取web工程的根目录的路径,
	 * 该实现假设web工程根目录是WEB-INF目录的父目录，且WEB-INF目录是类路径的父目录，
	 * 如果不是，请不要使用该方法
	 * </p>
	 * 
	 * @return web工程根目录的路径,
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-21 下午10:16:05
	 */
	public static String getWebRootPath() {
		String path = getWebInfPath();
		path += "..";
		return FilenameTool.normalize(path);
	}

}
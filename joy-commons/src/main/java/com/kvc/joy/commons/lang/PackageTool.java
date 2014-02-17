package com.kvc.joy.commons.lang;

import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * java包工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-10 下午9:01:06
 */
public class PackageTool {

	protected static final Log logger = LogFactory.getLog(PackageTool.class);

	private PackageTool() {
	}

	/**
	 * <p>
	 * 获取指定包名下的所有类
	 * </p>
	 * 
	 * @param pkg 以"."分隔的java标准包名
	 * @param recursive 是否循环迭代
	 * @return Set<类>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-10 下午9:23:38
	 */
	public static Set<Class<?>> getClassesInPackage(String pkg, boolean recursive) {
		Action action = new Action(true);
		find(pkg, recursive, action);
		return action.getClasses();
	}
	
	/**
	 * <p>
	 * 根据正则表达式获取匹配的所有包 <br>
	 * 包的开头部分必须明确指定
	 * </p>
	 * 
	 * @param pkgPattern 包正则表达式
	 * @param recursive 是否递归地获取子包
	 * @return Set<包名>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-4-11 下午11:06:40
	 */
	public static Set<String> getPackages(String pkgPattern, boolean recursive) {
		Action action = new Action(false);
		String packagePrefix = getPackagePrefix(pkgPattern);
		find(packagePrefix, recursive, action);
		Set<String> pkgs = action.getPkgs();
		Set<String> packs = new LinkedHashSet<String>();
		String regExp = pkgPattern.replaceAll("\\*", ".*");
		for (String pack : pkgs) {
			if(pack.matches(regExp)) {
				packs.add(pack);
			}
		}
		return packs;
	}
	
	private static String getPackagePrefix(String pkgPattern) {
		StringBuilder pkgPrefix = new StringBuilder();
		String[] pkgElems = pkgPattern.split("\\.");
		for (String pkgElem : pkgElems) {
			if(pkgElem.contains("*")) {
				break;
			} else {
				pkgPrefix.append(pkgElem).append(".");
			}
		}
		return pkgPrefix.toString().replaceFirst("\\.$", "");
	}

	private static void find(String packagePrefix, boolean recursive, Action action) {
		// 获取包的名字 并进行替换
		String packageName = packagePrefix;
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs;
		try {
			dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
			// 循环迭代下去
			while (dirs.hasMoreElements()) {
				// 获取下一个元素
				URL url = dirs.nextElement();
				// 得到协议的名称
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					// 以文件的方式扫描整个包下的文件 并添加到集合中
					findAndAddClassesInPackageByFile(packageName, filePath, recursive, action);
				} else if ("jar".equals(protocol)) {
					// 如果是jar包文件
					// 定义一个JarFile
					findAndAddClassesInPackageByJar(packageName, packageDirName, url, recursive, action);
				}
			}
		} catch (IOException e) {
			logger.error(e);
		}
	}

	private static void findAndAddClassesInPackageByJar(String packageName, String packageDirName, URL url,
			final boolean recursive, Action action) {
		JarFile jar;
		try {
			// 获取jar
			jar = ((JarURLConnection) url.openConnection()).getJarFile();
			// 从此jar包 得到一个枚举类
			Enumeration<JarEntry> entries = jar.entries();
			// 同样的进行循环迭代
			while (entries.hasMoreElements()) {
				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				// 如果是以/开头的
				if (name.charAt(0) == '/') {
					// 获取后面的字符串
					name = name.substring(1);
				}
				// 如果前半部分和定义的包名相同
				if (name.startsWith(packageDirName)) {
					int idx = name.lastIndexOf('/');
					// 如果以"/"结尾 是一个包
					if (idx != -1) {
						// 获取包名 把"/"替换成"."
						packageName = name.substring(0, idx).replace('/', '.');
					}
					// 如果可以迭代下去 并且是一个包
					if ((idx != -1) || recursive) {
						if(action.isRetrieveClass()) {
							// 如果是一个.class文件 而且不是目录
							if (name.endsWith(".class") && !entry.isDirectory()) {
								// 去掉后面的".class" 获取真正的类名
								String className = name.substring(packageName.length() + 1, name.length() - 6);
								try {
									// 添加到classes
									action.addClass(Class.forName(packageName + '.' + className));
								} catch (ClassNotFoundException e) {
									logger.error(e);
								}
							}
						} else { // 获取包名
							if (entry.isDirectory()) {
								action.addPackage(packageName);
							}
						}
					}
				}
			}
		} catch (IOException e) {
			// log.error("在扫描用户定义视图时从jar包获取文件出错");
			logger.error(e);
		}
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 */
	private static void findAndAddClassesInPackageByFile(String packageName, String packagePath,
			final boolean recursive, Action action) {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (dir.exists() && dir.isDirectory()) {
			if(action.isRetrieveClass() == false) {
				action.addPackage(packageName);
			}
		} else {
//			 logger.warn("用户定义包名 " + packageName + " 下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, action);
			} else {
				if(action.isRetrieveClass()) {
					// 如果是java类文件 去掉后面的.class 只留下类名
					String className = file.getName().substring(0, file.getName().length() - 6);
					try {
						// 添加到集合中去
						action.addClass(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
					} catch (ClassNotFoundException e) {
						logger.error(e);
					}
				}
			}
		}
	}

	private static class Action {

		private boolean retrieveClass = true;
		private final Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		private final Set<String> pkgs = new LinkedHashSet<String>();

		public Action(boolean retrieveClass) {
			this.retrieveClass = retrieveClass;
		}

		public void addClass(Class<?> clazz) {
			classes.add(clazz);
		}

		public void addPackage(String pkg) {
			pkgs.add(pkg);
		}

		public boolean isRetrieveClass() {
			return retrieveClass;
		}

		public Set<Class<?>> getClasses() {
			return classes;
		}

		public Set<String> getPkgs() {
			return pkgs;
		}

	}
	
}

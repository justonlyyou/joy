package com.kvc.joy.commons.io;

import org.apache.commons.io.FileSystemUtils;

import com.kvc.joy.commons.exception.ServiceException;

/**
 * <p>
 * 文件系统工具类
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-6 上午10:40:07
 */
public class FileSystemTool {

	private FileSystemTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.io.FileSystemUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 通过调用命令行，获取某驱动器或卷的剩余空间(单位：KB)
	 * </p>
	 * 
	 * <pre>
	 * FileSystemUtils.freeSpaceKb(&quot;C:&quot;); // Windows
	 * FileSystemUtils.freeSpaceKb(&quot;/volume&quot;); // *nix
	 * </pre>
	 * 
	 * <p>
	 * 剩余空间是通过命令行计算的。它在Windows下使用'dir /-c', 在AIX/HP-UX
	 * 下使用'df -kP', 在Unix下使用'df -k'.
	 * </p>
	 * 
	 * @param path 要计算剩余空间的路径, 不能为null, 在Unix上不能为空串
	 * @return 剩余空间(单位：KB)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br> 
	 * 						IllegalArgumentException 如果路径无效
	 * 						IllegalStateException 如果初始化时发生错误
	 * 						IOException 如果查找剩余空间时出现io异常
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-18 下午11:49:24
	 */
	public static Long freeSpaceKb(String path) {
		try {
			return FileSystemUtils.freeSpaceKb(path);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 通过调用命令行，获取某驱动器或卷的剩余空间(单位：KB)
	 * </p>
	 * 
	 * <pre>
	 * FileSystemUtils.freeSpaceKb(&quot;C:&quot;); // Windows
	 * FileSystemUtils.freeSpaceKb(&quot;/volume&quot;); // *nix
	 * </pre>
	 * 
	 * <p>
	 * 剩余空间是通过命令行计算的。它在Windows下使用'dir /-c', 在AIX/HP-UX
	 * 下使用'df -kP', 在Unix下使用'df -k'.
	 * </p>
	 * 
	 * @param path 要计算剩余空间的路径, 不能为null, 在Unix上不能为空串
	 * @param timeout 计算超时时间(毫秒)， 为0或负数代表不超时
	 * @return 剩余空间(单位：KB)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br> 
	 * 						IllegalArgumentException 如果路径无效
	 * 						IllegalStateException 如果初始化时发生错误
	 * 						IOException 如果查找剩余空间时出现io异常
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-18 下午11:49:24
	 */
	public static Long freeSpaceKb(String path, long timeout) {
		try {
			return FileSystemUtils.freeSpaceKb(path, timeout);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 返回工作目录的磁盘剩余空间
	 * </p>
	 * 
	 * <p>
	 * 相当于:
	 * 
	 * <pre>
	 * freeSpaceKb(new File(&quot;.&quot;).getAbsolutePath())
	 * </pre>
	 * 
	 * @return 剩余空间(单位：KB)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br> 
	 * 						IllegalStateException 如果初始化时发生错误
	 * 						IOException 如果查找剩余空间时出现io异常
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-18 下午11:55:09
	 */
	public static Long freeSpaceKb() {
		try {
			return FileSystemUtils.freeSpaceKb();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 返回工作目录的磁盘剩余空间
	 * </p>
	 * 
	 * <p>
	 * 相当于:
	 * 
	 * <pre>
	 * freeSpaceKb(new File(&quot;.&quot;).getAbsolutePath())
	 * </pre>
	 * 
	 * @param timeout 计算超时时间(毫秒)， 为0或负数代表不超时
	 * @return 剩余空间(单位：KB)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br> 
	 * 						IllegalStateException 如果初始化时发生错误
	 * 						IOException 如果查找剩余空间时出现io异常
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-18 下午11:55:09
	 */
	public static Long freeSpaceKb(long timeout) {
		try {
			return FileSystemUtils.freeSpaceKb(timeout);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.io.FileSystemUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

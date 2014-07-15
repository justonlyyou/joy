package org.joy.commons.io;

import org.joy.commons.exception.SystemException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.joy.commons.exception.SystemException;

import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Checksum;

/**
 * <p>
 * 文件工具类
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-4-6 上午10:37:31
 */
public class FileTool {

	private FileTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.io.FileUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 从指定的父目录和名字集构造一个文件对象
	 * </p>
	 * 
	 * @param directory 父目录
	 * @param names 名字可变数组
	 * @return 文件对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:21:50
	 */
	public static File getFile(File directory, String... names) {
		return FileUtils.getFile(directory, names);
	}

	/**
	 * <p>
	 * 从指定的名字集构造一个文件对象
	 * </p>
	 * 
	 * @param names 名字可变数组
	 * @return 文件对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:21:50
	 */
	public static File getFile(String... names) {
		return FileUtils.getFile(names);
	}

	/**
	 * <p>
	 * 获取系统临时目录
	 * </p>
	 * 
	 * @return 系统临时目录
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:25:18
	 */
	public static String getTempDirectoryPath() {
		return FileUtils.getTempDirectoryPath();
	}

	/**
	 * <p>
	 * 获取系统临时目录
	 * </p>
	 * 
	 * @return 系统临时目录文件对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:25:18
	 */
	public static File getTempDirectory() {
		return FileUtils.getTempDirectory();
	}

	/**
	 * <p>
	 * 获取系统用户根目录
	 * </p>
	 * 
	 * @return 系统用户根目录
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:25:18
	 */
	public static String getUserDirectoryPath() {
		return FileUtils.getUserDirectoryPath();
	}

	/**
	 * <p>
	 * 获取系统用户根目录
	 * </p>
	 * 
	 * @return 系统用户根目录文件对象
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:25:18
	 */
	public static File getUserDirectory() {
		return FileUtils.getUserDirectory();
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 为指定的文件对象打开一个{@link FileInputStream}， 比简单地调用
	 * <code>new FileInputStream(file)</code>提供更好的错误信息。
	 * </p>
	 * 
	 * <p>
	 * 在方法的结尾，要么流被成功打开，要么抛出一个异常。
	 * <p>
	 * 
	 * <p>
	 * 如果指定的文件不存在，将抛出一个异常。
	 * 如果文件存在，但是是一个目录也将抛出异常。
	 * 如果文件存在，但是没法被读取也将抛出异常。
	 * </p>
	 * 
	 * @param file 待打开为输入流的文件对象, 不能为 {@code null}
	 * @return 指定文件的一个新的{@link FileInputStream}
	 * @throws org.joy.commons.exception.SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 指定的参数为null时
	 * 						FileNotFoundException 如果指定的文件不存在时
     * 						IOException 如果文件对象是一个目录时
     * 						IOException 如果文件没法被读取时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:37:31
	 */
	public static FileInputStream openInputStream(File file) {
		try {
			if(file == null) {
				throw new IllegalArgumentException("参数file不能为null!");
			}
			return FileUtils.openInputStream(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 为指定的文件对象打开一个{@link FileOutputStream}， 检查并创建父目录如果它不存在的话。
	 * </p>
	 * 
	 * <p>
	 * 在方法的结尾，要么流被成功打开，要么抛出一个异常。
	 * </p>
	 * 
	 * <p>
	 * 父目录不存在时将被创建。文件如果不存在也将被创建
	 * 如果文件存在，但是是一个目录也将抛出异常。
	 * 如果文件存在，但是没法被写入也将抛出异常。
	 * 如果父目录没法被创建也将抛出异常。
	 * </p>
	 * 
	 * @param file 待打开为输入流的文件对象, 不能为 {@code null}
	 * @return 指定文件的一个新的{@link FileOutputStream}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 指定的参数为null时
	 * 						IOException 如果文件对象是一个目录时
     * 						IOException 如果文件没法被写入时
     * 						IOException 如果父目录没法被创建时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:43:48
	 */
	public static FileOutputStream openOutputStream(File file) {
		try {
			if(file == null) {
				throw new IllegalArgumentException("参数file不能为null!");
			}
			return FileUtils.openOutputStream(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 为指定的文件对象打开一个{@link FileOutputStream}， 检查并创建父目录如果它不存在的话。
	 * </p>
	 * 
	 * <p>
	 * 在方法的结尾，要么流被成功打开，要么抛出一个异常。
	 * </p>
	 * 
	 * <p>
	 * 父目录不存在时将被创建。文件如果不存在也将被创建
	 * 如果文件存在，但是是一个目录也将抛出异常。
	 * 如果文件存在，但是没法被写入也将抛出异常。
	 * 如果父目录没法被创建也将抛出异常。
	 * </p>
	 * 
	 * @param file 待打开为输入流的文件对象, 不能为 {@code null}
	 * @param append {@code true}: 字节将被添加到文件的末尾而不是覆盖该文件
	 * @return 指定文件的一个新的{@link FileOutputStream}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 指定的参数为null时
	 * 						IOException 如果文件对象是一个目录时
     * 						IOException 如果文件没法被写入时
     * 						IOException 如果父目录没法被创建时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:47:22
	 */
	public static FileOutputStream openOutputStream(File file, boolean append) {
		try {
			if(file == null) {
				throw new IllegalArgumentException("参数file不能为null!");
			}
			return FileUtils.openOutputStream(file, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回文件大小的可读版本，输入参数代表字节数
	 * </p>
	 * 
	 * <p>
	 * 如果大小超过1GB，返回整个GB数。
	 * 即大小是向下调整至最接近的GB边界。
	 * </p>
	 * 
	 * <p>
	 * 同样的，1MB，1KB边界。
	 * </p>
	 * 
	 * @param size 字节数
	 * @return 可读的大小 (单位包括- EB, PB, TB, GB, MB, KB 和 bytes)
	 * @see <a href="https://issues.apache.org/jira/browse/IO-226">IO-226 -
	 *      should the rounding be changed?</a>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:59:24
	 */
	public static String byteCountToDisplaySize(BigInteger size) {
		return FileUtils.byteCountToDisplaySize(size);
	}
	
	/**
	 * <p>
	 * 返回文件大小的可读版本，输入参数代表字节数
	 * </p>
	 * 
	 * <p>
	 * 如果大小超过1GB，返回整个GB数。
	 * 即大小是向下调整至最接近的GB边界。
	 * </p>
	 * 
	 * <p>
	 * 同样的，1MB，1KB边界。
	 * </p>
	 * 
	 * @param size 字节数
	 * @return 可读的大小 (单位包括- EB, PB, TB, GB, MB, KB 和 bytes)
	 * @see <a href="https://issues.apache.org/jira/browse/IO-226">IO-226 -
	 *      should the rounding be changed?</a>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午9:59:24
	 */
	public static String byteCountToDisplaySize(long size) {
		return FileUtils.byteCountToDisplaySize(size);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 实现Unix上"touch"的相同行为。它创建一个大小为0的新文件，或者如果指定的文件存在，
	 * 它将不修改文件地打开、关闭它，但是有更新文件日期和时间。
	 * </p>
	 * 
	 * @param file 待处理的文件
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IllegalArgumentException 指定的参数为null时
	 * 						IOException 产生io异常时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午10:04:13
	 */
	public static void touch(File file) {
		try {
			if(file == null) {
				throw new IllegalArgumentException("参数file不能为null!");
			}
			FileUtils.touch(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将包含java.io.File实例的容器转换为数组。这样做主要是为了处理
	 * File.listFiles() 和 FileTool.listFiles() 返回值类型不同的问题
	 * </p>
	 * 
	 * @param files 包含java.io.File实例的容器
	 * @return 包含java.io.File实例的数组
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午10:07:00
	 */
	public static File[] convertFileCollectionToFileArray(Collection<File> files) {
		return FileUtils.convertFileCollectionToFileArray(files);
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 查找指定目录及其子目录下的文件，所有找到的文件将由IOFileFilter过滤。
	 * </p>
	 * 
	 * <p>
	 * 如果您需要递归搜索子目录，您可以为目录传入一个IOFileFilter。
	 * 您不需要绑定一个DirectoryFileFilter(通过逻辑与)到该过滤器上。该方法已经为您完成了。
	 * </p>
	 * 
	 * <p>
	 * 例如：如果您想查找所有叫"temp"的目录，那么您传入<code>FileFilterTool.nameFileFilter("temp")</code>
	 * </p>
	 * 
	 * <p>
	 * 该方法另一个常见的用途是在目录树中查找文件，但是忽略CVS生成的目录。
	 * 您可以简单的传入<code>FileFilterTool.makeCVSAware(null)</code>
	 * </p>
	 * 
	 * @param directory 要检索的目录
	 * @param fileFilter 查找文件时要应用的过滤器
	 * @param dirFilter 查找子目录时可选的过滤器。如果为null，将不搜索子目录。 TrueFileFilter.INSTANCE将匹配所有目录
	 * @return 匹配的文件对应容器
	 * @see FileFilterTool
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:32:00
	 */
	public static Collection<File> listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.listFiles(directory, fileFilter, dirFilter);
	}

	/**
	 * <p>
	 * 在给定的目录(子目录可选)中查找文件
	 * </p>
	 * 
	 * <p>
	 * 包含子目录的所有容器
	 * <p>
	 * 
	 * @see FileTool#listFiles
	 * 
	 * @param directory 要查找的目录
	 * @param fileFilter 查找时使用的过滤器
	 * @param dirFilter 查找子目录时可选的过滤器。如果为null，将不搜索子目录。 TrueFileFilter.INSTANCE将匹配所有目录
	 * @return 匹配的文件对应容器
	 * @see FileFilterTool
	 * @see org.apache.commons.io.filefilter.NameFileFilter
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:40:08
	 */
	public static Collection<File> listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.listFilesAndDirs(directory, fileFilter, dirFilter);
	}

	/**
	 * <p>
	 * 允许迭代指定目录中的文件(子目录可选)
	 * </p>
	 * 
	 * <p>
	 * 所有找到的文件将通过IOFileFilter过滤。该方法基于
	 * {@link #listFiles(File, IOFileFilter, IOFileFilter)}, 它提供可迭代的功能('foreach' 循环).
	 * <p>
	 * 
	 * @param directory 要查找的目录
	 * @param fileFilter 查找时使用的过滤器
	 * @param dirFilter 查找子目录时可选的过滤器。如果为null，将不搜索子目录。 TrueFileFilter.INSTANCE将匹配所有目录
	 * @return  匹配的文件的迭代器
	 * @see FileFilterTool
	 * @see org.apache.commons.io.filefilter.NameFileFilter
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:44:51
	 */
	public static Iterator<File> iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.iterateFiles(directory, fileFilter, dirFilter);
	}

	/**
	 * <p>
	 * 允许迭代指定目录中的文件(子目录可选)
	 * </p>
	 * 
	 * <p>
	 * 所有找到的文件将通过IOFileFilter过滤。该方法基于
	 * {@link #listFiles(File, IOFileFilter, IOFileFilter)}, 它提供可迭代的功能('foreach' 循环).
	 * <p>
	 * 
	 * <p>
	 * 结果包含子目录的迭代器
	 * <p>
	 * 
	 * @param directory 要查找的目录
	 * @param fileFilter 查找时使用的过滤器
	 * @param dirFilter 查找子目录时可选的过滤器。如果为null，将不搜索子目录。 TrueFileFilter.INSTANCE将匹配所有目录
	 * @return 匹配的文件的迭代器
	 * @see FileFilterTool
	 * @see org.apache.commons.io.filefilter.NameFileFilter
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:55:37
	 */
	public static Iterator<File> iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter) {
		return FileUtils.iterateFilesAndDirs(directory, fileFilter, dirFilter);
	}

	// -----------------------------------------------------------------------

	/**
	 * <p>
	 * 查找指定目录(子目录是可选的)中匹配扩展名的文件
	 * </p>
	 * 
	 * @param directory 要查找的目录
	 * @param extensions 扩展名数组, 如： {"java","xml"}. 为null将返回所有文件
	 * @param recursive true将查找所有子目录
	 * @return 匹配的文件对象的容器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:59:42
	 */
	public static Collection<File> listFiles(File directory, String[] extensions, boolean recursive) {
		return FileUtils.listFiles(directory, extensions, recursive);
	}

	/**
	 * <p>
	 * 查找指定目录(子目录是可选的)中匹配扩展名的文件.
	 * 该方法基于{@link #listFiles(File, String[], boolean)}，它提供可迭代的功能('foreach' 循环).
	 * </p>
	 * 
	 * @param directory 要查找的目录
	 * @param extensions 扩展名数组, 如： {"java","xml"}. 为null将返回所有文件
	 * @param recursive true将查找所有子目录
	 * @return 匹配的文件对象的迭代器
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 上午11:59:50
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:00:57
	 */
	public static Iterator<File> iterateFiles(File directory, String[] extensions, boolean recursive) {
		return FileUtils.iterateFiles(directory, extensions, recursive);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查两个文件的内容是否相等
	 * </p>
	 * 
	 * <p>
	 * 该方法检查两个文件的长度是否不同，或是否指向相同的文件，
	 * 最后才逐字节的比较它们的内容。
	 * </p>
	 * 
	 * @param file1 第一个文件
	 * @param file2 第二个文件
	 * @return true：如果它们的内容都相同或两个文件都不存在，否则返回false
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:06:52
	 */
	public static Boolean contentEquals(File file1, File file2) {
		try {
			return FileUtils.contentEquals(file1, file2);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查两个文件的内容是否相等
	 * </p>
	 * 
	 * <p>
	 * 该方法检查两个文件是否指向相同的文件，
	 * 最后才逐行的比较它们的内容。
	 * </p>
	 * 
	 * @param file1 第一个文件
	 * @param file2 第二个文件
	 * @param charsetName 字符编码. 为null将使用平台的默认编码
	 * @return true：如果它们的内容都相同或两个文件都不存在，否则返回false
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 出现io错误
	 * @see IOTool#contentEqualsIgnoreEOL(Reader, Reader)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:09:54
	 */
	public static Boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName) {
		try {
			return FileUtils.contentEqualsIgnoreEOL(file1, file2, charsetName);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>URL</code> 转换为一个 <code>File</code>
	 * </p>
	 * 
	 * @param url 待转换的url, {@code null} 将返回 {@code null}
	 * @return 等效的<code>File</code> 对象, 如果url协议不是<code>file</code>则返回 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:12:15
	 */
	public static File toFile(URL url) {
		return FileUtils.toFile(url);
	}

	/**
	 * <p>
	 * 将每一个<code>URL</code> 都转换为 <code>File</code>
	 * </p>
	 * 
	 * <p>
	 * 返回跟输入数组相同大小的数组。如果输入为null将返回空数组。
	 * 如果输入的数组包含null元素，输出数组的对应元素也是null。
	 * </p>
	 * 
	 * <p>
	 * 该方法将对URL进行编码。语法如：
	 * <code>file:///my%20docs/file.txt</code> 将被正确的编码为
	 * <code>/my docs/file.txt</code>.
	 * </p>
	 * 
	 * @param urls 要转换为File对象的url数组, {@code null} 将返回空数组
	 * @return 一个非null的数组
	 *         
	 * @throws IllegalArgumentException 如果任何url不是file
	 * @throws IllegalArgumentException 如果任何url不能被正确编码
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:19:24
	 */
	public static File[] toFiles(URL[] urls) {
		return FileUtils.toFiles(urls);
	}

	/**
	 * <p>
	 * 将每一个<code>File</code> 都转换为 <code>URL</code>
	 * </p>
	 * 
	 * <p>
	 * 返回跟输入数组相同大小的数组。
	 * </p>
	 * 
	 * @param files 待转化的File数组, 不能为 {@code null}
	 * @return 转换后的URL数组
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 文件不能被转化时
	 * 						NullPointerException 如果参数为null
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:22:15
	 */
	public static URL[] toURLs(File[] files) {
		try {
			return FileUtils.toURLs(files);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 拷贝一个文件到指定目录，并保留文件的日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝源文件的内容到指定目录的相同名字的文件。
	 * 目标目录如果不存在将被创建。如果目标文件存在，它将被覆盖。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcFile 一个已存在的待拷贝的文件, 不能为 {@code null}
	 * @param destDir 要存放拷贝后的文件的目标目录, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null
	 * 						IOException 如果源或目标无效
	 * 						IOException 如果出现io错误
	 * @see #copyFile(File, File, boolean)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午12:29:52
	 */
	public static void copyFileToDirectory(File srcFile, File destDir) {
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝一个文件到指定目录，可以指定是否保留文件的日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝源文件的内容到指定目录的相同名字的文件。
	 * 目标目录如果不存在将被创建。如果目标文件存在，它将被覆盖。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcFile 一个已存在的待拷贝的文件, 不能为 {@code null}
	 * @param destDir 要存放拷贝后的文件的目标目录, 不能为 {@code null}
	 * @param preserveFileDate 是否保留文件原日期
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null
	 * 						IOException 如果源或目标无效
	 * 						IOException 如果出现io错误
	 * @see #copyFile(File, File, boolean)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午3:10:35
	 */
	public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate) {
		try {
			FileUtils.copyFileToDirectory(srcFile, destDir, preserveFileDate);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝一个文件到新的位置，并保留文件的日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝源文件的内容到指定目录的相同名字的文件。
	 * 目标目录如果不存在将被创建。如果目标文件存在，它将被覆盖。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcFile 一个已存在的待拷贝的文件, 不能为 {@code null}
	 * @param destFile 新的文件, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null
	 * 						IOException 如果源或目标无效
	 * 						IOException 如果拷贝时出现io错误
	 * @see #copyFileToDirectory(File, File)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午3:15:36
	 */
	public static void copyFile(File srcFile, File destFile) {
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝一个文件到新的位置，可以指定是否保留文件的日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝源文件的内容到指定目录的相同名字的文件。
	 * 目标目录如果不存在将被创建。如果目标文件存在，它将被覆盖。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcFile 一个已存在的待拷贝的文件, 不能为 {@code null}
	 * @param destFile 新的文件, 不能为 {@code null}
	 * @param preserveFileDate 是否保留文件原日期
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null
	 * 						IOException 如果源或目标无效
	 * 						IOException 如果拷贝时出现io错误
	 * @see #copyFileToDirectory(File, File, boolean)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午3:15:36
	 */
	public static void copyFile(File srcFile, File destFile, boolean preserveFileDate) {
		try {
			FileUtils.copyFile(srcFile, destFile, preserveFileDate);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 从指定的文件拷贝字节到<code>OutputStream</code>
	 * </p>
	 * 
	 * <p>
	 * 该方法在内部已经有对输入进行缓存，所以没必要在外部使用<code>BufferedInputStream</code>
	 * </p>
	 * 
	 * @param input 要读取数据的文件对象
	 * @param output 要写入的 <code>OutputStream</code>
	 * @return 拷贝的字节数
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午3:20:18
	 */
	public static Long copyFile(File input, OutputStream output) {
		try {
			return FileUtils.copyFile(input, output);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 拷贝一个目录到目标目录下，保留文件日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝源目标和其所有内容到目标目录下的一个同名目标
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 如果目标目录存在，将合并源目录和目标目录的内容，源目标的内容覆盖目标目录的内容。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcDir 一个存在的待拷贝的目录, 不能为 {@code null}
	 * @param destDir 放置拷贝后的源目标的目标目录，不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null <br>
	 * 						IOException 如果源目录或目标目录无效 <br>
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午3:54:11
	 */
	public static void copyDirectoryToDirectory(File srcDir, File destDir) {
		try {
			FileUtils.copyDirectoryToDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝整个目录到一个新的位置，保留文件日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝指定的目录及其所有子目录和文件到目标目录。
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 如果目标目录存在，将合并源目录和目标目录的内容，源目标的内容覆盖目标目录的内容。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcDir 一个存在的待拷贝的目录, 不能为 {@code null}
	 * @param destDir 新的目录, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null <br>
	 * 						IOException 如果源目录或目标目录无效 <br>
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:00:05
	 */
	public static void copyDirectory(File srcDir, File destDir) {
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝整个目录到一个新的位置，可以指定是否保留文件的日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝指定的目录及其所有子目录和文件到目标目录。
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 如果目标目录存在，将合并源目录和目标目录的内容，源目标的内容覆盖目标目录的内容。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * @param srcDir 一个存在的待拷贝的目录, 不能为 {@code null}
	 * @param destDir 新的目录, 不能为 {@code null}
	 * @param preserveFileDate 是否保留文件原日期
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为null <br>
	 * 						IOException 如果源目录或目标目录无效 <br>
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:00:05
	 */
	public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate) {
		try {
			FileUtils.copyDirectory(srcDir, destDir, preserveFileDate);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝一个过滤过的目录到一个新的位置，保留文件日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝指定的目录及其所有子目录和文件到目标目录。
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 如果目标目录存在，将合并源目录和目标目录的内容，源目标的内容覆盖目标目录的内容。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * <h4>例子: 只拷贝目录</h4>
	 * 
	 * <pre>
	 * // 只拷贝目录结构
	 * FileTool.copyDirectory(srcDir, destDir, DirectoryFileFilter.DIRECTORY);
	 * </pre>
	 * 
	 * <h4>例子: 拷贝目录和txt文件</h4>
	 * 
	 * <pre>
	 * // 创建一个&quot;.txt&quot;文件的过滤器
	 * IOFileFilter txtSuffixFilter = FileFilterTool.suffixFileFilter(&quot;.txt&quot;);
	 * IOFileFilter txtFiles = FileFilterTool.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);
	 * 
	 * // 创建一个过滤目录或&quot;.txt&quot;文件的过滤器
	 * FileFilter filter = FileFilterTool.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
	 * 
	 * // 使用过滤器进行拷贝
	 * FileTool.copyDirectory(srcDir, destDir, filter);
	 * </pre>
	 * 
	 * @param srcDir 一个存在的待拷贝的目录, 不能为 {@code null}
	 * @param destDir 新的目录, 不能为 {@code null}
	 * @param filter 要使用的过滤器, null表示拷贝所有目录和文件
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果源目录或目标目录为null <br>
	 * 						IOException 如果源目录或目标目录无效 <br>
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:07:42
	 */
	public static void copyDirectory(File srcDir, File destDir, FileFilter filter) {
		try {
			FileUtils.copyDirectory(srcDir, destDir, filter);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 拷贝一个过滤过的目录到一个新的位置，可以指定是否保留文件日期
	 * </p>
	 * 
	 * <p>
	 * 该方法拷贝指定的目录及其所有子目录和文件到目标目录。
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 如果目标目录存在，将合并源目录和目标目录的内容，源目标的内容覆盖目标目录的内容。
	 * </p>
	 * 
	 * <p>
	 * <strong>注意:</strong> 该方法试图使用{@link File#setLastModified(long)}保留文件最后修改的日期/时间，
	 * 然而，它不保证该操作一定能成功。如果修改操作失败，将没有任何迹象。
	 * </p>
	 * 
	 * <h4>例子: 只拷贝目录</h4>
	 * 
	 * <pre>
	 * // 只拷贝目录结构
	 * FileTool.copyDirectory(srcDir, destDir, DirectoryFileFilter.DIRECTORY);
	 * </pre>
	 * 
	 * <h4>例子: 拷贝目录和txt文件</h4>
	 * 
	 * <pre>
	 * // 创建一个&quot;.txt&quot;文件的过滤器
	 * IOFileFilter txtSuffixFilter = FileFilterTool.suffixFileFilter(&quot;.txt&quot;);
	 * IOFileFilter txtFiles = FileFilterTool.andFileFilter(FileFileFilter.FILE, txtSuffixFilter);
	 * 
	 * // 创建一个过滤目录或&quot;.txt&quot;文件的过滤器
	 * FileFilter filter = FileFilterTool.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
	 * 
	 * // 使用过滤器进行拷贝
	 * FileTool.copyDirectory(srcDir, destDir, filter);
	 * </pre>
	 * 
	 * @param srcDir 一个存在的待拷贝的目录, 不能为 {@code null}
	 * @param destDir 新的目录, 不能为 {@code null}
	 * @param filter 要使用的过滤器, null表示拷贝所有目录和文件
	 * @param preserveFileDate 是否保留文件日期
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果源目录或目标目录为null <br>
	 * 						IOException 如果源目录或目标目录无效 <br>
	 * 						IOException 如果拷贝时出现io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:07:42
	 */
	public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate) {
		try {
			FileUtils.copyDirectory(srcDir, destDir, filter, preserveFileDate);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从URL中按字节拷贝其内容到一个文件
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 目标文件如果存在将被覆盖。
	 * </p>
	 * 
	 * <p>
	 * 警告: 该方法没有设置一个连接或读取超时，因此可以永远阻塞。
	 * 要防止这种情况的出现，请使用{@link #copyURLToFile(URL, File, int, int)}
	 * 
	 * @param source 要拷贝字节的<code>URL</code>，不能为 {@code null}
	 * @param destination 要写入字节的非目录<code>File</code>(可能覆盖), 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果源URL不能被打开 <br>
	 * 						IOException 如果目标是一个目录 <br>
	 * 						IOException 如果目标文件不能被写入 <br>
	 * 						IOException 如果目标需要被创建但是又创建不了 <br>
	 * 						IOException 如果拷贝时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:51:48
	 */
	public static void copyURLToFile(URL source, File destination) {
		try {
			FileUtils.copyURLToFile(source, destination);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 从URL中按字节拷贝其内容到一个文件
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 目标文件如果存在将被覆盖。
	 * </p>
	 * 
	 * @param source 要拷贝字节的<code>URL</code>，不能为 {@code null}
	 * @param destination 要写入字节的非目录<code>File</code>(可能覆盖), 不能为 {@code null}
	 * @param connectionTimeout 如果<code>source</code>没有连接生成的超时毫秒数 
	 * @param readTimeout 如果没有数据可以从<code>source</code>读取的超时毫秒数
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果源URL不能被打开 <br>
	 * 						IOException 如果目标是一个目录 <br>
	 * 						IOException 如果目标文件不能被写入 <br>
	 * 						IOException 如果目标需要被创建但是又创建不了 <br>
	 * 						IOException 如果拷贝时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午4:51:48
	 */
	public static void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout) {
		try {
			FileUtils.copyURLToFile(source, destination, connectionTimeout, readTimeout);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 从{@link InputStream}拷贝字节到一个文件
	 * </p>
	 * 
	 * <p>
	 * 目标目录如果不存在将被创建。
	 * 目标文件如果存在将被覆盖。
	 * </p>
	 * 
	 * @param source 拷贝数据的来源<code>InputStream</code>, 不能为 {@code null}
	 * @param destination 要写入字节的非目录<code>File</code>(可能覆盖), 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果目标是一个目录 <br>
	 * 						IOException 如果目标文件不能被写入 <br>
	 * 						IOException 如果目标需要被创建但是又创建不了 <br>
	 * 						IOException 如果拷贝时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:02:57
	 */
	public static void copyInputStreamToFile(InputStream source, File destination) {
		try {
			FileUtils.copyInputStreamToFile(source, destination);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 递归删除一个目录
	 * </p>
	 * 
	 * @param directory 待删除的目录
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 删除操作失败时 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:03:07
	 */
	public static void deleteDirectory(File directory) {
		try {
			FileUtils.deleteDirectory(directory);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 删除一个文件，不会抛出异常。如果为目录，删除它及其所有子目录
	 * </p>
	 * 
	 * <p>
	 * 这个方法和File.delete()的差别在于：
	 * <ul>
	 * <li>要删除的目录不需要是一个空目录</li>
	 * <li>当一个目录或一个文件无法被删除时不会抛出异常</li>
	 * </ul>
	 * 
	 * @param file 要删除的文件或目录, 可以为 {@code null}
	 * @return {@code true}： 如果文件或目录被成功删除，否则返回 {@code false}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:08:18
	 */
	public static boolean deleteQuietly(File file) {
		return FileUtils.deleteQuietly(file);
	}

	/**
	 * <p>
	 * 检查父目录是否包含子目录(或文件)
	 * </p>
	 * 
	 * <p>
	 * 比较之前文件不会被标准化。
	 * </p>
	 * 
	 * 边缘情况:
	 * <ul>
	 * <li>父目录必须不为null: 如果为null将抛出IllegalArgumentException异常</li>
	 * <li>{@code directory} 必须为一个目录: 如果不是目录，将抛出IllegalArgumentException异常</li>
	 * <li>一个目录不包含它自己：返回false</li>
	 * <li>一个null的child不会包含于任何父目录: 返回false</li>
	 * </ul>
	 * 
	 * @param directory 父目录
	 * @param child 子文件或子目录
	 * @return true：父目录包含子目录或文件，否则返回false
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 检查文件时出现io错误
	 * @see FilenameTool#directoryContains(String, String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:23:06
	 */
	public static Boolean directoryContains(final File directory, final File child) {
		try {
			return FileUtils.directoryContains(directory, child);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 清空指定目录，而不是删除它
	 * </p>
	 * 
	 * @param directory 待清空的目录
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 清除不成功时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:24:21
	 */
	public static void cleanDirectory(File directory) {
		try {
			FileUtils.cleanDirectory(directory);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 等待一个文件的创建，实行超时
	 * </p>
	 * 
	 * <p>
	 * 该方法重复检查{@link File#exists()}直到它在超时时间内返回true
	 * </p>
	 * 
	 * @param file 要检查的文件, 不能为 {@code null}
	 * @param seconds 等待的最大秒数
	 * @return true: 如果文件存在
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果文件为 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:28:21
	 */
	public static boolean waitFor(File file, int seconds) {
		return FileUtils.waitFor(file, seconds);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 读取一个文件的内容为字符串。该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @return 文件内容，不会为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果文件为 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:31:12
	 */
	public static String readFileToString(File file, Charset encoding) {
		try {
			return FileUtils.readFileToString(file, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取一个文件的内容为字符串。该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @return 文件内容，不会为 {@code null}
	 * @in case of an I/O error
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * 						UnsupportedCharsetException 指定的编码不被支持时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:33:03
	 */
	public static String readFileToString(File file, String encoding) {
		try {
			return FileUtils.readFileToString(file, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 读取一个文件的内容为字符串，使用虚拟机的默认编码，该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @return 文件的内容, never {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午5:35:44
	 */
	public static String readFileToString(File file) {
		try {
			return FileUtils.readFileToString(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 从指定的文件读取内容到字节数组. 该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @return 文件内容的字节数组, 不会为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:15:15
	 */
	public static byte[] readFileToByteArray(File file) {
		try {
			return FileUtils.readFileToByteArray(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 逐行读取指定文件的内容到字符串列表。该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @return 字符串列表，每一个元素代表文件中的每一行, 不会为{@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:17:25
	 */
	public static List<String> readLines(File file, Charset encoding) {
		try {
			return FileUtils.readLines(file, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 逐行读取指定文件的内容到字符串列表。该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @return 字符串列表，每一个元素代表文件中的每一行, 不会为{@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * 						UnsupportedCharsetException 如果编码不被支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:17:25
	 */
	public static List<String> readLines(File file, String encoding) {
		try {
			return FileUtils.readLines(file, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 逐行读取指定文件的内容到字符串列表，使用虚拟机默认的编码，该文件总是关闭着。
	 * </p>
	 * 
	 * @param file 要读取的文件, 不能为 {@code null}
	 * @return 字符串列表，每一个元素代表文件中的每一行, 不会为{@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:17:25
	 */
	public static List<String> readLines(File file) {
		try {
			return FileUtils.readLines(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 返回文件中每行的迭代器
	 * </p>
	 * 
	 * <p>
	 * 该方法为指定的文件打开一个<code>InputStream</code>。当您完成迭代后，必须
	 * 关闭流以便释放内部资源。这可以通过调用{@link LineIterator#close()}或
	 * {@link LineIterator#closeQuietly(LineIterator)}方法来完成。
	 * </p>
	 * 
	 * <p>
	 * 使用例子:
	 * 
	 * <pre>
	 * LineIterator it = FileTool.lineIterator(file, &quot;UTF-8&quot;);
	 * try {
	 * 	while (it.hasNext()) {
	 * 		String line = it.nextLine();
	 * 		// / 对line的处理
	 * 	}
	 * } finally {
	 * 	LineIterator.closeQuietly(iterator);
	 * }
	 * </pre>
	 * </p>
	 * 
	 * <p>
	 * 如果在创建迭代器的过程中产生异常，底层的流将被关闭。
	 * </p>
	 * 
	 * @param file 要读取的文件对象, 不能为 {@code null}
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @return 代表文件中每行的迭代器, 不会为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:24:28
	 */
	public static LineIterator lineIterator(File file, String encoding) {
		try {
			return FileUtils.lineIterator(file, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 返回文件中每行的迭代器, 使用虚拟机的默认编码
	 * </p>
	 * 
	 * @param file 要读取的文件对象, 不能为 {@code null}
	 * @return 代表文件中每行的迭代器, 不会为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @see #lineIterator(File, String)
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:25:32
	 */
	public static LineIterator lineIterator(File file) {
		try {
			return FileUtils.lineIterator(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个字符串写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data, Charset encoding) {
		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符串写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data, String encoding) {
		try {
			FileUtils.writeStringToFile(file, data, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符串写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param append 字符序列是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data, Charset encoding, boolean append) {
		try {
			FileUtils.writeStringToFile(file, data, encoding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符串写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param append 字符序列是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data, String encoding, boolean append) {
		try {
			FileUtils.writeStringToFile(file, data, encoding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符串写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data) {
		try {
			FileUtils.writeStringToFile(file, data);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符串写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param append 字符串是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeStringToFile(File file, String data, boolean append) {
		try {
			FileUtils.writeStringToFile(file, data, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data) {
		try {
			FileUtils.write(file, data);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param append 字符序列是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data, boolean append) {
		try {
			FileUtils.write(file, data, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data, Charset encoding) {
		try {
			FileUtils.write(file, data, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data, String encoding) {
		try {
			FileUtils.write(file, data, encoding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param append 字符序列是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data, Charset encoding, boolean append) {
		try {
			FileUtils.write(file, data, encoding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字符序列写入到文件中，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param append 字符序列是否被拼接到文件末尾，而不是覆盖原来文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void write(File file, CharSequence data, String encoding, boolean append) {
		try {
			FileUtils.write(file, data, encoding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字节数组写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeByteArrayToFile(File file, byte[] data) {
		try {
			FileUtils.writeByteArrayToFile(file, data);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将一个字节数组写入到文件中，使用虚拟机默认的编码，如果文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param data 要写入到文件的内容
	 * @param append 数据是否被拼接到文件末尾，而不是替换原文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:27:40
	 */
	public static void writeByteArrayToFile(File file, byte[] data, boolean append) {
		try {
			FileUtils.writeByteArrayToFile(file, data, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用指定的编码和默认的行结束符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:49:39
	 */
	public static void writeLines(File file, String encoding, Collection<?> lines) {
		try {
			FileUtils.writeLines(file, encoding, lines);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用指定的编码和默认的行结束符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param append 数据是否被拼接到文件末尾，而不是替换原文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午6:49:39
	 */
	public static void writeLines(File file, String encoding, Collection<?> lines, boolean append) {
		try {
			FileUtils.writeLines(file, encoding, lines, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用虚拟机默认的编码和默认的行结束符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:01:39
	 */
	public static void writeLines(File file, Collection<?> lines) {
		try {
			FileUtils.writeLines(file, lines);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用虚拟机默认的编码和默认的行结束符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件 to
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param append 数据是否被拼接到文件末尾，而不是替换原文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:06:52
	 */
	public static void writeLines(File file, Collection<?> lines, boolean append) {
		try {
			FileUtils.writeLines(file, lines, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用指定的编码和行分隔符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件 to
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param lineEnding 行分隔符, {@code null}将使用系统默认的行分隔符
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:09:03
	 */
	public static void writeLines(File file, String encoding, Collection<?> lines, String lineEnding) {
		try {
			FileUtils.writeLines(file, encoding, lines, lineEnding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用指定的编码和行分隔符。
	 * </p>
	 * 
	 * <p>
	 * 文件不存在将被创建
	 * </p>
	 * 
	 * @param file 要写入的文件 to
	 * @param encoding 要使用的编码, {@code null} 将使用平台默认的编码
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param lineEnding 行分隔符, {@code null}将使用系统默认的行分隔符
	 * @param append 数据是否被拼接到文件末尾，而不是替换原文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * 						UnsupportedEncodingException 如果指定的编码不被虚拟机支持
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:09:03
	 */
	public static void writeLines(File file, String encoding, Collection<?> lines, String lineEnding, boolean append) {
		try {
			FileUtils.writeLines(file, encoding, lines, lineEnding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用虚拟机默认的编码和指定的行分隔符。
	 * </p>
	 * 
	 * @param file 要写入的文件 to
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param lineEnding 行分隔符, {@code null}将使用系统默认的行分隔符
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:12:33
	 */
	public static void writeLines(File file, Collection<?> lines, String lineEnding) {
		try {
			FileUtils.writeLines(file, lines, lineEnding);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中每个元素的toString()值逐行地写入到指定的文件。
	 * 使用虚拟机默认的编码和指定的行分隔符。
	 * </p>
	 * 
	 * @param file 要写入的文件 to
	 * @param lines 要写入的容器, {@code null} 将写入空行
	 * @param lineEnding 行分隔符, {@code null}将使用系统默认的行分隔符
	 * @param append 数据是否被拼接到文件末尾，而不是替换原文件的内容
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果发生io错误 <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:12:33
	 */
	public static void writeLines(File file, Collection<?> lines, String lineEnding, boolean append) {
		try {
			FileUtils.writeLines(file, lines, lineEnding, append);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 删除一个文件。如果为目录，删除它及其所有子目录
	 * </p>
	 * 
	 * <p>
	 * 该方法与File.delete()的差别为：
	 * <ul>
	 * <li>要删除的目录可以为空</li>
	 * <li>当一个目录或文件不能被删除时将抛出异常。(java.io.File的方法将返回一个布尔值)</li>
	 * </ul>
	 * 
	 * @param file 要删除的文件或目录, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果目录或文件为{@code null} <br>
	 * 						FileNotFoundException 如果目录或文件找不到 <br>
	 * 						IOException 删除操作失败时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:27:09
	 */
	public static void forceDelete(File file) {
		try {
			FileUtils.forceDelete(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 当java虚拟机退出时，删除指定的文件或目录。如果是目录，删除该目录及其所有子目录。
	 * </p>
	 * 
	 * @param file 要删除的文件或目录, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果目录或文件为{@code null} <br>
	 * 						IOException 删除操作失败时
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:30:55
	 */
	public static void forceDeleteOnExit(File file) {
		try {
			FileUtils.forceDeleteOnExit(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 创建目录，包含任何需要但不存在的父目录。
	 * 如果指定名称的文件已经存在，但是不是一个目录，将抛出IOException异常。
	 * </p>
	 * 
	 * <p>
	 * 如果目录不能被创建(或存在但不是一个目录)时将抛出IOException异常
	 * </p>
	 * 
	 * @param directory 要创建的目录, 不能为 {@code null}
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果目录为{@code null} <br>
	 * 						IOException 如果目录不能被创建或存在但不是一个目录
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:40:50
	 */
	public static void forceMkdir(File directory) {
		try {
			FileUtils.forceMkdir(directory);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回指定文件或目录的大小。
	 * 如果提供的{@link File}是一个正规的文件，文件的长度将被返回。
	 * 如果参数是目录，将递归计算目录的大小。如果目录或子目录有安全限制，
	 * 它的大小将不被包括。
	 * </p>
	 * 
	 * @param file 要返回大小的正规文件或目录，(不能为{@code null}).
	 * @return 文件的长度, 或目录的递归大小(字节数)
	 * @throws NullPointerException 如果参数为{@code null} <br>
	 * @throws IllegalArgumentException 如果文件不存在
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:49:26
	 */
	public static long sizeOf(File file) {
		return FileUtils.sizeOf(file);
	}

	/**
	 * <p>
	 * 返回指定文件或目录的大小。
	 * 如果提供的{@link File}是一个正规的文件，文件的长度将被返回。
	 * 如果参数是目录，将递归计算目录的大小。如果目录或子目录有安全限制，
	 * 它的大小将不被包括。
	 * </p>
	 * 
	 * @param file 要返回大小的正规文件或目录，(不能为{@code null}).
	 * @return 文件的长度, 或目录的递归大小(字节数)
	 * @throws NullPointerException 如果参数为{@code null} <br>
	 * @throws IllegalArgumentException 如果文件不存在
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:49:26
	 */
	public static BigInteger sizeOfAsBigInteger(File file) {
		return FileUtils.sizeOfAsBigInteger(file);
	}

	/**
	 * <p>
	 * 递归计算目录的大小(所有文件的大小之和)
	 * </p>
	 * 
	 * @param directory 待检查的目录, 不能为 {@code null}
	 * @return 目录的大小(字节数), 如果目录有安全限制返回0, 当总大小大于{@link Long#MAX_VALUE}时返回一个负数
	 * @throws NullPointerException 如果参数为{@code null} <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:52:38
	 */
	public static long sizeOfDirectory(File directory) {
		return FileUtils.sizeOfDirectory(directory);
	}

	/**
	 * <p>
	 * 递归计算目录的大小(所有文件的大小之和)
	 * </p>
	 * 
	 * @param directory 待检查的目录, 不能为 {@code null}
	 * @return 目录的大小(字节数), 如果目录有安全限制返回0
	 * @throws NullPointerException 如果参数为{@code null} <br>
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午7:52:38
	 */
	public static BigInteger sizeOfDirectoryAsBigInteger(File directory) {
		return FileUtils.sizeOfDirectoryAsBigInteger(directory);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查指定的第一个文件是否比第二文件新(修改日期)
	 * </p>
	 * 
	 * @param file 第一个文件, 不能为 {@code null}
	 * @param reference 第二个文件, 不能为 {@code null}
	 * @return true：如果文件存在并且比第二个文件新
	 * @throws IllegalArgumentException 如果参数为{@code null} <br>
	 * @throws IllegalArgumentException 如果文件不存在
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:01:12
	 */
	public static boolean isFileNewer(File file, File reference) {
		return FileUtils.isFileNewer(file, reference);
	}

	/**
	 * <p>
	 * 检查指定的文件的修改日期是否比指定的日期新
	 * </p>
	 * 
	 * @param file 待比较的文件, 不能为 {@code null}
	 * @param date 日期, 不能为 {@code null}
	 * @return true：如果文件存在并且在指定日期之后被修改
	 * @throws IllegalArgumentException 如果文件为 {@code null}
	 * @throws IllegalArgumentException 如果日期 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:07:51
	 */
	public static boolean isFileNewer(File file, Date date) {
		return FileUtils.isFileNewer(file, date);
	}

	/**
	 * <p>
	 * 检查指定的文件的修改日期是否比指定的日期新
	 * </p>
	 * 
	 * @param file 待比较的文件, 不能为 {@code null}
	 * @param timeMillis 日期的毫秒表示
	 * @return true：如果文件存在并且在指定日期之后被修改
	 * @throws IllegalArgumentException 如果文件为 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:10:37
	 */
	public static boolean isFileNewer(File file, long timeMillis) {
		return FileUtils.isFileNewer(file, timeMillis);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查指定的第一个文件是否比第二文件旧(修改日期)
	 * </p>
	 * 
	 * @param file 第一个文件, 不能为 {@code null}
	 * @param reference 第二个文件, 不能为 {@code null}
	 * @return true：如果文件存在并且比第二个文件旧
	 * @throws IllegalArgumentException 如果参数为{@code null}
	 * @throws 	IllegalArgumentException 如果文件不存在
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:01:12
	 */
	public static boolean isFileOlder(File file, File reference) {
		return FileUtils.isFileOlder(file, reference);
	}

	/**
	 * <p>
	 * 检查指定的文件的修改日期是否比指定的日期旧
	 * </p>
	 * 
	 * @param file 待比较的文件, 不能为 {@code null}
	 * @param date 日期, 不能为 {@code null}
	 * @return true：如果文件存在并且在指定日期之前被修改
	 * @throws IllegalArgumentException 如果文件为 {@code null}
	 * @throws IllegalArgumentException 如果日期 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:07:51
	 */
	public static boolean isFileOlder(File file, Date date) {
		return FileUtils.isFileOlder(file, date);
	}

	/**
	 * <p>
	 * 检查指定的文件的修改日期是否比指定的日期旧
	 * </p>
	 * 
	 * @param file 待比较的文件, 不能为 {@code null}
	 * @param timeMillis 日期的毫秒表示
	 * @return true：如果文件存在并且在指定日期之前被修改
	 * @throws IllegalArgumentException 如果文件为 {@code null}
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:10:37
	 */
	public static boolean isFileOlder(File file, long timeMillis) {
		return FileUtils.isFileOlder(file, timeMillis);
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 计算文件的校验和，使用CRC32校验和算法。返回校验和。
	 * </p>
	 * 
	 * @param file 待计算校验和的文件, 不能为 {@code null}
	 * @return 校验和
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 *  					NullPointerException 如果文件或校验和为 {@code null} <br>
	 * 						IllegalArgumentException 如果指定的文件是一个目录 <br>
	 * 						IOException 读取文件时发生io异常
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:18:00
	 */
	public static Long checksumCRC32(File file) {
		try {
			return FileUtils.checksumCRC32(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 使用指定的校验和对象计算文件的校验和。
	 * 多个文件可以被同一个<code>Checksum</code>对象校验。例如：
	 * 
	 * <pre>
	 * long csum = FileTool.checksum(file, new CRC32()).getValue();
	 * </pre>
	 * 
	 * @param file 要计算校验和的文件, 不能为 {@code null}
	 * @param checksum 使用的校验和对象, 不能为 {@code null}
	 * @return 指定的校验和对象，已经由文件的内容更新过
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						IllegalArgumentException 如果指定的文件是一个目录 <br>
	 * 						IOException 读取文件时发生io异常
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:52:02
	 */
	public static Checksum checksum(File file, Checksum checksum) {
		try {
			return FileUtils.checksum(file, checksum);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 移动一个目录到另一个目录
	 * </p>
	 * 
	 * <p>
	 * 当目标目录在另一个文件系统时，执行拷贝和删除
	 * </p>
	 * 
	 * @param srcDir 要移动的目录
	 * @param destDir 目标目录
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						FileExistsException 如果目标目录存在 <br>
	 * 						IOException 源或目标不可用时 <br>
	 * 						IOException 如果移动时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:54:57
	 */
	public static void moveDirectory(File srcDir, File destDir) {
		try {
			FileUtils.moveDirectory(srcDir, destDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 移动一个目录到另一个目录
	 * </p>
	 * 
	 * <p>
	 * 当目标目录在另一个文件系统时，执行拷贝和删除
	 * </p>
	 * 
	 * @param srcDir 要移动的目录
	 * @param destDir 目标目录
	 * @param createDestDir {@code true}：创建目录, {@code false} : 将抛出IOException异常
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						FileExistsException 如果目标目录存在 <br>
	 * 						IOException 源或目标不可用时 <br>
	 * 						IOException 如果移动时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:54:57
	 */
	public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir) {
		try {
			FileUtils.moveDirectoryToDirectory(src, destDir, createDestDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 移动一个文件
	 * </p>
	 * 
	 * <p>
	 * 当目标文件在另一个文件系统时，执行拷贝和删除
	 * </p>
	 * 
	 * @param srcFile 要移动的文件
	 * @param destFile 目标文件
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						FileExistsException 如果目标文件存在 <br>
	 * 						IOException 源或目标不可用时 <br>
	 * 						IOException 如果移动时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:54:57
	 */
	public static void moveFile(File srcFile, File destFile) {
		try {
			FileUtils.moveFile(srcFile, destFile);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 移动一个文件
	 * </p>
	 * 
	 * <p>
	 * 当目标文件在另一个文件系统时，执行拷贝和删除
	 * </p>
	 * 
	 * @param srcFile 要移动的文件
	 * @param destDir 目标目录
	 * @param createDestDir {@code true}：创建目录, {@code false} : 将抛出IOException异常
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						FileExistsException 如果目标文件存在 <br>
	 * 						IOException 源或目标不可用时 <br>
	 * 						IOException 如果移动时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:54:57
	 */
	public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir) {
		try {
			FileUtils.moveFileToDirectory(srcFile, destDir, createDestDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 移动一个文件或目录到目标目录
	 * </p>
	 * 
	 * <p>
	 * 当目标在另一个文件系统时，执行拷贝和删除
	 * </p>
	 * 
	 * @param src 要移动的文件或目录
	 * @param destDir 目标目录
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						NullPointerException 如果任意参数为 {@code null} <br>
	 * 						FileExistsException 如果文件或目录存在于目标目录中 <br>
	 * 						IOException 源或目标不可用时 <br>
	 * 						IOException 如果移动时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午8:54:57
	 */
	public static void moveToDirectory(File src, File destDir, boolean createDestDir) {
		try {
			FileUtils.moveToDirectory(src, destDir, createDestDir);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	/**
	 * <p>
	 * 确定指定的文件是否是一个符号链接，而不是一个实际的文件。
	 * </p>
	 * 
	 * <p>
	 * 如果在路径中的任意位置有一个符号链接将返回false. 只有当指定的文件是一个实际文件时才返回true.
	 * </p>
	 * 
	 * <p>
	 * <b>注意:</b> 如果当前使用的系统由{@link FilenameTool#isSystemWindows()}检测到是Windows,
	 * 那么当前的实现总是返回{@code false}
	 * </p>
	 * 
	 * @param file 要检查的文件
	 * @return true：如果文件是一个符号链接
	 * @throws SystemException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException 如果检查时发生io错误
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013-5-19 下午9:16:04
	 */
	public static Boolean isSymlink(File file) {
		try {
			return FileUtils.isSymlink(file);
		} catch (Exception e) {
			throw new SystemException(e);
		}
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.io.FileUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

}

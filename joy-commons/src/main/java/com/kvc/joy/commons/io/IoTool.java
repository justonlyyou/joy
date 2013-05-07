package com.kvc.joy.commons.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.kvc.joy.commons.exception.ServiceException;

/**
 * <p>
 * io操作工具类
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-6 上午10:37:58
 */
public class IoTool {

	private IoTool() {
	}

	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
	// 封装org.apache.commons.io.IoUtils
	// vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

	/**
	 * <p>
	 * 关闭一个URLConnection
	 * </p>
	 * 
	 * @param conn 要关闭的连接
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:18:56
	 */
	public static void close(URLConnection conn) {
		IOUtils.close(conn);
	}

	/**
	 * <p>
	 * 无条件地关闭一个<code>Reader</code>
	 * </p>
	 * 
	 * <p>
	 * 相当于{@link Reader#close()}， 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * char[] data = new char[1024];
	 * Reader in = null;
	 * try {
	 * 	in = new FileReader(&quot;foo.txt&quot;);
	 * 	in.read(data);
	 * 	in.close(); // 关闭时错误将被处理
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(in);
	 * }
	 * </pre>
	 * 
	 * @param input 要关闭的<code>Reader</code>, 可以为null或已经关闭的<code>Reader</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:22:47
	 */
	public static void closeQuietly(Reader input) {
		IOUtils.closeQuietly(input);
	}

	/**
	 * <p>
	 * 无条件地关闭一个<code>Writer</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link Writer#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * Writer out = null;
	 * try {
	 * 	out = new StringWriter();
	 * 	out.write(&quot;Hello World&quot;);
	 * 	out.close(); // 关闭时错误将被处理
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(out);
	 * }
	 * </pre>
	 * 
	 * @param output 要关闭的<code>Writer</code>, 可以为null或已经关闭的<code>Writer</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:24:25
	 */
	public static void closeQuietly(Writer output) {
		IOUtils.closeQuietly(output);
	}

	/**
	 * <p>
	 * 无条件地关闭一个<code>InputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link InputStream#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * byte[] data = new byte[1024];
	 * InputStream in = null;
	 * try {
	 * 	in = new FileInputStream(&quot;foo.txt&quot;);
	 * 	in.read(data);
	 * 	in.close(); // 关闭时错误将被处理
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(in);
	 * }
	 * </pre>
	 * 
	 * @param input 要关闭的<code>InputStream</code>, 可以为null或已经关闭的<code>InputStream</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:38:20
	 */
	public static void closeQuietly(InputStream input) {
		IOUtils.closeQuietly(input);
	}

	/**
	 * <p>
	 * 无条件地关闭一个 <code>OutputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link OutputStream#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * byte[] data = &quot;Hello, World&quot;.getBytes();
	 * 
	 * OutputStream out = null;
	 * try {
	 * 	out = new FileOutputStream(&quot;foo.txt&quot;);
	 * 	out.write(data);
	 * 	out.close(); // 关闭时错误将被处理
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(out);
	 * }
	 * </pre>
	 * 
	 * @param output 要关闭的<code>OutputStream</code>, 可以为null或已经关闭的<code>OutputStream</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:41:27
	 */
	public static void closeQuietly(OutputStream output) {
		IOUtils.closeQuietly(output);
	}

	/**
	 * <p>
	 * 无条件地关闭一个 <code>Closeable</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link Closeable#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * Closeable closeable = null;
	 * try {
	 * 	closeable = new FileReader(&quot;foo.txt&quot;);
	 * 	// process closeable
	 * 	closeable.close();
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(closeable);
	 * }
	 * </pre>
	 * 
	 * @param closeable 要关闭的<code>Closeable</code>, 可以为null或已经关闭的<code>Closeable</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:42:23
	 */
	public static void closeQuietly(Closeable closeable) {
		IOUtils.closeQuietly(closeable);
	}

	/**
	 * <p>
	 * 无条件地关闭一个 <code>Socket</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link Socket#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * Socket socket = null;
	 * try {
	 * 	socket = new Socket(&quot;http://www.foo.com/&quot;, 80);
	 * 	// process socket
	 * 	socket.close();
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(socket);
	 * }
	 * </pre>
	 * 
	 * @param sock 要关闭的<code>Socket</code>, 可以为null或已经关闭的<code>Socket</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:43:10
	 */
	public static void closeQuietly(Socket sock) {
		IOUtils.closeQuietly(sock);
	}

	/**
	 * <p>
	 * 无条件地关闭一个 <code>Selector</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link Selector#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * Selector selector = null;
	 * try {
	 * 	selector = Selector.open();
	 * 	// process socket
	 * 
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(selector);
	 * }
	 * </pre>
	 * 
	 * @param selector 要关闭的<code>Selector</code>, 可以为null或已经关闭的<code>Selector</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:43:55
	 */
	public static void closeQuietly(Selector selector) {
		IOUtils.closeQuietly(selector);
	}

	/**
	 * <p>
	 * 无条件地关闭一个 <code>ServerSocket</code>.
	 * </p>
	 * 
	 * <p>
	 * 相当于 {@link ServerSocket#close()}, 除了任何异常将被忽略外。
	 * 常用在finally块中
	 * </p>
	 * 
	 * <p>
	 * 示例代码:
	 * 
	 * <pre>
	 * ServerSocket socket = null;
	 * try {
	 * 	socket = new ServerSocket();
	 * 	// process socket
	 * 	socket.close();
	 * } catch (Exception e) {
	 * 	// 错误处理
	 * } finally {
	 * 	IOUtils.closeQuietly(socket);
	 * }
	 * </pre>
	 * 
	 * @param sock 要关闭的<code>ServerSocket</code>, 可以为null或已经关闭的<code>ServerSocket</code>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:44:42
	 */
	public static void closeQuietly(ServerSocket sock) {
		IOUtils.closeQuietly(sock);
	}

	/**
	 * <p>
	 * 获取整个<code>InputStream</code>的内容，并用相同的数据当作结果InputStream
	 * </p>
	 * 
	 * <p>
	 * 该方法在以下的地方有用：
	 * <ul>
	 * <li>源InputStream很慢.</li>
	 * <li>它关联网络资源，因此我们不能让它打开太久</li>
	 * <li>它关联可能超时网络.</li>
	 * </ul>
	 * 它可以作为{@link #toByteArray(InputStream)}的参数，因为它避免不必要的资源分配和字节数组拷贝。
	 * <br>
	 * 该方法内容有缓存，所以没有必要在使用<code>BufferedInputStream</code>
	 * </p>
	 * 
	 * @param input 要被完全缓存的Stream
	 * @return 被完全缓存的Stream
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:56:06
	 */
	public static InputStream toBufferedInputStream(InputStream input) {
		try {
			return IOUtils.toBufferedInputStream(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 如果指定的reader是{@link BufferedReader}，直接返回，否则，
	 * 为指定的reader创建一个BufferedReader并返回
	 * </p>
	 * 
	 * @param reader 要被包装或直接返回的Reader
	 * @return 指定的Reader 或 指定的Reader的一个新的 {@link BufferedReader}
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午9:59:23
	 */
	public static BufferedReader toBufferedReader(Reader reader) {
		return IOUtils.toBufferedReader(reader);
	}

	// read toByteArray
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>byte[]</code>
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的 <code>InputStream</code>
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static byte[] toByteArray(InputStream input) {
		try {
			return IOUtils.toByteArray(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>byte[]</code>.
	 * 当知道<code>InputStream</code>的大小时，使用该方法代替<code>toByteArray(InputStream)</code>方法。
	 * </p>
	 * 
	 * <p>
	 * <b>注意:</b> 该方法在使用{@link IOTool#toByteArray(java.io.InputStream, int)}读入
	 * 字节数组前， 检查长度能否被安全地转为int型(毕竟数组的长度不能超过Integer.MAX_VALUE)
	 * </p>
	 * 
	 * @param input 要读取的 <code>InputStream</code>
	 * @param size <code>InputStream</code>的大小
	 * @return 请求的字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException io错误发生或<code>InputStream</code>的大小与size参数不一致时 <br>					
	 * 						IllegalArgumentException 如果size参数小于0或大于Integer.MAX_VALUE
	 * @see IOTool#toByteArray(java.io.InputStream, int)
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午7:38:49
	 */
	public static byte[] toByteArray(InputStream input, long size) {
		try {
			return IOUtils.toByteArray(input, size);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>byte[]</code>.
	 * 当知道<code>InputStream</code>的大小时，使用该方法代替<code>toByteArray(InputStream)</code>方法。
	 * </p>
	 * 
	 * @param input 要读取的 <code>InputStream</code>
	 * @param size <code>InputStream</code>的大小
	 * @return 请求的字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 						IOException io错误发生或<code>InputStream</code>的大小与size参数不一致时 <br>					
	 * 						IllegalArgumentException 如果size参数小于0
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午7:41:04
	 */
	public static byte[] toByteArray(InputStream input, int size) {
		try {
			return IOUtils.toByteArray(input, size);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>Reader</code>的内容为<code>byte[]</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的 <code>Reader</code>
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static byte[] toByteArray(Reader input) {
		try {
			return IOUtils.toByteArray(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>Reader</code>的内容为<code>byte[]</code>，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的 <code>Reader</code>
	 * @param encoding 编码
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static byte[] toByteArray(Reader input, Charset encoding) {
		try {
			return IOUtils.toByteArray(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>Reader</code>的内容为<code>byte[]</code>，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的 <code>Reader</code>
	 * @param encoding 编码
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static byte[] toByteArray(Reader input, String encoding) {
		try {
			return IOUtils.toByteArray(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URI</code>指向的内容为<code>byte[]</code>
	 * </p>
	 * 
	 * @param uri 要读取的内容的<code>URI</code>
	 * @return 请求的字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午7:53:30
	 */
	public static byte[] toByteArray(URI uri) {
		try {
			return IOUtils.toByteArray(uri);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URL</code>指向的内容为<code>byte[]</code>
	 * </p>
	 * 
	 * @param url 要读取的内容的<code>URL</code>
	 * @return 请求的字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午7:53:30
	 */
	public static byte[] toByteArray(URL url) {
		try {
			return IOUtils.toByteArray(url);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URLConnection</code>指向的内容为<code>byte[]</code>
	 * </p>
	 * 
	 * @param urlConn 要读取的内容的<code>URLConnection</code>
	 * @return 请求的字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午7:53:30
	 */
	public static byte[] toByteArray(URLConnection urlConn) {
		try {
			return IOUtils.toByteArray(urlConn);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// read char[]
	// -----------------------------------------------------------------------
	
	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>char[]</code>.
	 * 使用平台默认的编码。
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param is 要读取的 <code>InputStream</code>
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static char[] toCharArray(InputStream is) {
		try {
			return IOUtils.toCharArray(is);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>char[]</code>.
	 * 使用指定的编码。
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param is 要读取的 <code>InputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static char[] toCharArray(InputStream is, Charset encoding) {
		try {
			return IOUtils.toCharArray(is, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为<code>char[]</code>.
	 * 使用指定的编码。
	 * </p>
	 * 
	 * <p>
	 * 字符编码可以在这里找到： 
	 * <a href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param is 要读取的 <code>InputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 指定编码不被支持时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static char[] toCharArray(InputStream is, String encoding) {
		try {
			return IOUtils.toCharArray(is, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>Reader</code>的内容为<code>char[]</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的 <code>Reader</code>
	 * @return 字节数组
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-19 下午10:02:33
	 */
	public static char[] toCharArray(Reader input) {
		try {
			return IOUtils.toCharArray(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// read toString
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串.
	 * 使用平台默认的编码。
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @return 请求的字符串
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:15:29
	 */
	public static String toString(InputStream input) {
		try {
			return IOUtils.toString(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	
	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串.
	 * 使用指定的编码。
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 请求的字符串
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:15:29
	 */
	public static String toString(InputStream input, Charset encoding) {
		try {
			return IOUtils.toString(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串.
	 * 使用指定的编码。
	 * </p>
	 * 
	 * <p>
	 * 字符编码可以在这里找到： 
	 * <a href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 请求的字符串
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 指定的编码不被支持时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:17:30
	 */
	public static String toString(InputStream input, String encoding) {
		try {
			return IOUtils.toString(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>Reader</code>的内容为字符串.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @return 请求的字符串
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:19:14
	 */
	public static String toString(Reader input) {
		try {
			return IOUtils.toString(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URI</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param uri URI源
	 * @return URI指向的内容的字符串表示
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:23:06
	 */
	public static String toString(URI uri) {
		try {
			return IOUtils.toString(uri);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URI</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param uri URI源
	 * @param encoding URL指向的内容的编码名称
	 * @return URI指向的内容的字符串表示
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:23:14
	 */
	public static String toString(URI uri, Charset encoding) {
		try {
			return IOUtils.toString(uri, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URI</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param uri URI源
	 * @param encoding URL指向的内容的编码名称
	 * @return URI指向的内容的字符串表示
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:29:19
	 */
	public static String toString(URI uri, String encoding) {
		try {
			return IOUtils.toString(uri, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URL</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param url URL源
	 * @return URL指向的内容的字符串表示
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:29:27
	 */
	public static String toString(URL url) {
		try {
			return IOUtils.toString(url);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URL</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param url URL源
	 * @param encoding URL指向的内容的编码名称
	 * @return URL指向的内容的字符串表示
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:29:35
	 */
	public static String toString(URL url, Charset encoding) {
		try {
			return IOUtils.toString(url, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>URL</code>指向的内容为字符串.
	 * </p>
	 * 
	 * @param url URL源
	 * @param encoding URL指向的内容的编码名称
	 * @return URI指向的内容的字符串表示
	 * @if an I/O exception occurs.
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:30:53
	 */
	public static String toString(URL url, String encoding) {
		try {
			return IOUtils.toString(url, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>byte[]</code>的内容为字符串，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * @param input 要读取的字节数组
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 请求的字符串
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:35:56
	 */
	public static String toString(byte[] input, String encoding) {
		try {
			return IOUtils.toString(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// readLines
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串列表，
	 * 每行一个实体，使用平台默认的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>, 不能为null
	 * @return 字符串列表，不会为null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:50:30
	 */
	public static List<String> readLines(InputStream input) {
		try {
			return IOUtils.readLines(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串列表，
	 * 每行一个实体，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>, 不能为null
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 字符串列表，不会为null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:56:53
	 */
	public static List<String> readLines(InputStream input, Charset encoding) {
		try {
			return IOUtils.readLines(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串列表，
	 * 每行一个实体，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>, 不能为null
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 字符串列表，不会为null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:57:32
	 */
	public static List<String> readLines(InputStream input, String encoding) {
		try {
			return IOUtils.readLines(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取<code>InputStream</code>的内容为字符串列表，每行一个实体
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>, 不能为null
	 * @return 字符串列表，不会为null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午8:48:03
	 */
	public static List<String> readLines(Reader input) {
		try {
			return IOUtils.readLines(input);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// lineIterator
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 返回<code>Reader</code>的一个行迭代器
	 * </p>
	 * 
	 * <p>
	 * <code>LineIterator</code> 持有的打开的<code>Reader</code>的引用。
	 * 当您完成迭代时，您必须关闭<code>Reader</code>以便释放内部的资源。
	 * 这可以通过直接关闭<code>Reader</code>，或调用{@link LineIterator#close()}，
	 * 或调用{@link LineIterator#closeQuietly(LineIterator)}方法。
	 * </p>
	 * 
	 * <p>
	 * 建议的使用模式为：
	 * 
	 * <pre>
	 * try {
	 * 	LineIterator it = IOUtils.lineIterator(reader);
	 * 	while (it.hasNext()) {
	 * 		String line = it.nextLine();
	 * 		// / 对line的处理
	 * 	}
	 * } finally {
	 * 	IOUtils.closeQuietly(reader);
	 * }
	 * </pre>
	 * 
	 * @param reader 要读取的<code>Reader</code>, 不能为null
	 * @return an Iterator of the lines in the reader, never null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IllegalArgumentException 如果参数为null <br>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:10:02
	 */
	public static LineIterator lineIterator(Reader reader) {
		return IOUtils.lineIterator(reader);
	}

	/**
	 * <p>
	 * 返回<code>InputStream</code>的一个行迭代器，使用指定的编码(null为平台默认编码)
	 * </p>
	 * 
	 * <p>
	 * <code>LineIterator</code> 持有的打开的<code>InputStream</code>的引用。
	 * 当您完成迭代时，您必须关闭<code>InputStream</code>以便释放内部的资源。
	 * 这可以通过直接关闭<code>InputStream</code>，或调用{@link LineIterator#close()}，
	 * 或调用{@link LineIterator#closeQuietly(LineIterator)}方法。
	 * </p>
	 * 
	 * <p>
	 * 建议的使用模式为：
	 * 
	 * <pre>
	 * try {
	 * 	LineIterator it = IOUtils.lineIterator(stream, charset);
	 * 	while (it.hasNext()) {
	 * 		String line = it.nextLine();
	 * 		// / 对line的处理
	 * 	}
	 * } finally {
	 * 	IOUtils.closeQuietly(stream);
	 * }
	 * </pre>
	 * 
	 * @param input 要读取的<code>InputStream</code>, 不能为null
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return an Iterator of the lines in the reader, never null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IllegalArgumentException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:11:43
	 */
	public static LineIterator lineIterator(InputStream input, Charset encoding) {
		try {
			return IOUtils.lineIterator(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 返回<code>InputStream</code>的一个行迭代器，使用指定的编码(null为平台默认编码)
	 * </p>
	 * 
	 * <p>
	 * <code>LineIterator</code> 持有的打开的<code>InputStream</code>的引用。
	 * 当您完成迭代时，您必须关闭<code>InputStream</code>以便释放内部的资源。
	 * 这可以通过直接关闭<code>InputStream</code>，或调用{@link LineIterator#close()}，
	 * 或调用{@link LineIterator#closeQuietly(LineIterator)}方法。
	 * </p>
	 * 
	 * <p>
	 * 建议的使用模式为：
	 * 
	 * <pre>
	 * try {
	 * 	LineIterator it = IOUtils.lineIterator(stream, &quot;UTF-8&quot;);
	 * 	while (it.hasNext()) {
	 * 		String line = it.nextLine();
	 * 		// / 对line的处理
	 * 	}
	 * } finally {
	 * 	IOUtils.closeQuietly(stream);
	 * }
	 * </pre>
	 * 
	 * @param input 要读取的<code>InputStream</code>, 不能为null
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return an Iterator of the lines in the reader, never null
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IllegalArgumentException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:12:21
	 */
	public static LineIterator lineIterator(InputStream input, String encoding) {
		try {
			return IOUtils.lineIterator(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 转换指定的CharSequence为InputStream，使用平台默认的编码
	 * </p>
	 * 
	 * @param input 待转换的CharSequence
	 * @return 输入流
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:14:11
	 */
	public static InputStream toInputStream(CharSequence input) {
		return IOUtils.toInputStream(input);
	}

	/**
	 * <p>
	 * 转换指定的CharSequence为InputStream，使用指定的编码
	 * </p>
	 * 
	 * @param input 待转换的CharSequence
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 输入流
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:14:34
	 */
	public static InputStream toInputStream(CharSequence input, Charset encoding) {
		return IOUtils.toInputStream(input, encoding);
	}

	/**
	 * <p>
	 * 转换指定的CharSequence为InputStream，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * 
	 * @param input 待转换的CharSequence
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 输入流
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:15:14
	 */
	public static InputStream toInputStream(CharSequence input, String encoding) {
		try {
			return IOUtils.toInputStream(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 转换指定的字符串为InputStream，使用平台默认的编码
	 * </p>
	 * 
	 * @param input 待转换的字符串
	 * @return 输入流
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:17:06
	 */
	public static InputStream toInputStream(String input) {
		return IOUtils.toInputStream(input);
	}

	/**
	 * <p>
	 * 转换指定的字符串为InputStream，使用指定的编码
	 * </p>
	 * 
	 * @param input 待转换的字符串
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 输入流
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:17:12
	 */
	public static InputStream toInputStream(String input, Charset encoding) {
		return IOUtils.toInputStream(input, encoding);
	}

	/**
	 * <p>
	 * 转换指定的字符串为InputStream，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * 
	 * @param input 待转换的字符串
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @return 输入流
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:17:20
	 */
	public static InputStream toInputStream(String input, String encoding) {
		try {
			return IOUtils.toInputStream(input, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// write byte[]
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>byte[]</code>的内容写入一个<code>OutputStream</code>
	 * </p>
	 * 
	 * @param data 待写入的字节数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的 <code>OutputStream</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:20:06
	 */
	public static void write(byte[] data, OutputStream output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>byte[]</code>的内容写入一个<code>Writer</code>,
	 * 使用平台的默认编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link String#String(byte[])}.
	 * </p>
	 * 
	 * @param data 待写入的字节数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:23:36
	 */
	public static void write(byte[] data, Writer output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>byte[]</code>的内容写入一个<code>Writer</code>,
	 * 使用指定的默认编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#String(byte[], String)}.
	 * </p>
	 * 
	 * @param data 待写入的字节数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:24:34
	 */
	public static void write(byte[] data, Writer output, Charset encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>byte[]</code>的内容写入一个<code>Writer</code>,
	 * 使用指定的默认编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#String(byte[], String)}.
	 * </p>
	 * 
	 * @param data 待写入的字节数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:25:18
	 */
	public static void write(byte[] data, Writer output, String encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// write char[]
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>char[]</code>的内容写入一个<code>Writer</code>,
	 * 使用平台的默认编码
	 * </p>
	 * 
	 * 
	 * @param data 待写入的字符数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:26:54
	 */
	public static void write(char[] data, Writer output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>char[]</code>的内容写入一个<code>OutputStream</code>,
	 * 使用平台的默认编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用{@link String#String(char[])}和{@link String#getBytes()}.
	 * </p>
	 * 
	 * @param data 待写入的字符数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:23:36
	 */
	public static void write(char[] data, OutputStream output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>char[]</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#String(char[])} 和 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的字符数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:30:37
	 */
	public static void write(char[] data, OutputStream output, Charset encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>char[]</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * <p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#String(char[])} 和 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的字符数组, 在输出时不会被修改, null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:31:41
	 */
	public static void write(char[] data, OutputStream output, String encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// write CharSequence
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>Writer</code>
	 * </p>
	 * 
	 * @param data 待写入的<code>CharSequence</code>， 为null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:35:47
	 */
	public static void write(CharSequence data, Writer output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用平台默认的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes()}.
	 * </p>
	 * 
	 * @param data 待写入的<code>CharSequence</code>， 为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:36:55
	 */
	public static void write(CharSequence data, OutputStream output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的<code>CharSequence</code>， 为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:37:31
	 */
	public static void write(CharSequence data, OutputStream output, Charset encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的<code>CharSequence</code>， 为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:38:03
	 */
	public static void write(CharSequence data, OutputStream output, String encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// write String
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将一个<code>String</code>的内容写入一个<code>Writer</code>
	 * </p>
	 * 
	 * @param data 待写入的<code>String</code>，为null将什么也不做
	 * @param output 要写入的<code>Writer</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:39:36
	 */
	public static void write(String data, Writer output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用平台默认的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes()}.
	 * </p>
	 * 
	 * @param data 待写入的<code>String</code>，为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:40:09
	 */
	public static void write(String data, OutputStream output) {
		try {
			IOUtils.write(data, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的<code>String</code>，为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:40:46
	 */
	public static void write(String data, OutputStream output, Charset encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将一个<code>CharSequence</code>的内容写入一个<code>OutputStream</code>,
	 * 使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link String#getBytes(String)}.
	 * </p>
	 * 
	 * @param data 待写入的<code>String</code>，为null将什么也不做
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:41:14
	 */
	public static void write(String data, OutputStream output, String encoding) {
		try {
			IOUtils.write(data, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// write StringBuffer
	// -----------------------------------------------------------------------

	// writeLines
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将容器中的每个一元素的toString()结果逐行写入到<code>OutputStream</code>中，
	 * 使用指定的行分隔符和平台默认的编码。
	 * </p>
	 * 
	 * @param lines 要写入的行，null的实体产生空白行
	 * @param lineEnding 要使用的行分隔符，null将用系统默认行分隔符
	 * @param output 要写入的<code>OutputStream</code>, 不能为null, 不能是已关闭的
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时           
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:46:15
	 */
	public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output) {
		try {
			IOUtils.writeLines(lines, lineEnding, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中的每个一元素的toString()结果逐行写入到<code>OutputStream</code>中，
	 * 使用指定的行分隔符和编码。
	 * </p>
	 * 
	 * @param lines 要写入的行，null的实体产生空白行
	 * @param lineEnding 要使用的行分隔符，null将用系统默认行分隔符
	 * @param output 要写入的<code>OutputStream</code>, 不能为null, 不能是已关闭的
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:46:22
	 */
	public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, Charset encoding) {
		try {
			IOUtils.writeLines(lines, lineEnding, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中的每个一元素的toString()结果逐行写入到<code>OutputStream</code>中，
	 * 使用指定的行分隔符和编码。
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * 
	 * @param lines 要写入的行，null的实体产生空白行
	 * @param lineEnding 要使用的行分隔符，null将用系统默认行分隔符
	 * @param output 要写入的<code>OutputStream</code>, 不能为null, 不能是已关闭的
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:46:30
	 */
	public static void writeLines(Collection<?> lines, String lineEnding, OutputStream output, String encoding) {
		try {
			IOUtils.writeLines(lines, lineEnding, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将容器中的每个一元素的toString()结果逐行写入到<code>Writer</code>中
	 * </p>
	 * 
	 * @param lines 要写入的行，null的实体产生空白行
	 * @param lineEnding 要使用的行分隔符，null将用系统默认行分隔符
	 * @param writer 要写入的<code>Writer</code>, 不能为null, 不能是已关闭的
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:47:11
	 */
	public static void writeLines(Collection<?> lines, String lineEnding, Writer writer) {
		try {
			IOUtils.writeLines(lines, lineEnding, writer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// copy from InputStream
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 从<code>InputStream</code>中拷贝字节到<code>OutputStream</code>中
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 大的流(超过2GB)在完成拷贝后，将返回一个<code>-1</code>的字节拷贝值，
	 * 因为无法返回正确的整型字节数。大的流对象的拷贝应该使用
	 * <code>copyLarge(InputStream, OutputStream)</code> 方法.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @return 拷贝的字节数, 如果大于Integer.MAX_VALUE返回-1 
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果任意参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:52:22
	 */
	public static Integer copy(InputStream input, OutputStream output) {
		try {
			return IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从一个大的(超过2GB)<code>InputStream</code>中拷贝字节到<code>OutputStream</code>中
	 * </p>
	 *
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @return 拷贝的字节数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:55:58
	 */
	public static Long copyLarge(InputStream input, OutputStream output) {
		try {
			return IOUtils.copyLarge(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从一个大的(超过2GB)<code>InputStream</code>中拷贝字节到<code>OutputStream</code>中
	 * </p>
	 * 
	 * <p>
	 * 该方法使用提供的缓存, 因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @param buffer 拷贝时要使用的缓存
	 * @return 拷贝的字节数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午9:58:46
	 */
	public static Long copyLarge(InputStream input, OutputStream output, byte[] buffer) {
		try {
			return IOUtils.copyLarge(input, output, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从一个大的(超过2GB)<code>InputStream</code>中拷贝所有或部分字节到<code>OutputStream</code>中，
	 * 可以选择跳过某些输入的字节
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @param inputOffset : 拷贝前从输入跳过的字节数，负数将拷贝所有
	 * @param length : 要拷贝的字节数. 负数将拷贝所有
	 * @return 拷贝的字节数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:03:24
	 */
	public static Long copyLarge(InputStream input, OutputStream output, long inputOffset, long length) {
		try {
			return IOUtils.copyLarge(input, output, inputOffset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从一个大的(超过2GB)<code>InputStream</code>中拷贝所有或部分字节到<code>OutputStream</code>中，
	 * 可以选择跳过某些输入的字节
	 * </p>
	 * 
	 * <p>
	 * 该方法使用提供的缓存, 因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @param inputOffset 拷贝前从输入跳过的字节数，负数将拷贝所有
	 * @param length 要拷贝的字节数. 负数将拷贝所有
	 * @param buffer 拷贝时要使用的缓存
	 * @return 拷贝的字节数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:05:47
	 */
	public static Long copyLarge(InputStream input, OutputStream output, final long inputOffset, final long length,
			byte[] buffer) {
		try {
			return IOUtils.copyLarge(input, output, inputOffset, length, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>InputStream</code>的内容拷贝到<code>Writer</code>，使用平台的默认编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link InputStreamReader}.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>Writer</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:07:40
	 */
	public static void copy(InputStream input, Writer output) {
		try {
			IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>InputStream</code>的内容拷贝到<code>Writer</code>，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link InputStreamReader}.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>Writer</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:08:30
	 */
	public static void copy(InputStream input, Writer output, Charset encoding) {
		try {
			IOUtils.copy(input, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>InputStream</code>的内容拷贝到<code>Writer</code>，使用指定的编码
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedInputStream</code>.
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link InputStreamReader}.
	 * </p>
	 * 
	 * @param input 要读取的<code>InputStream</code>
	 * @param output 要写入的<code>Writer</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:09:12
	 */
	public static void copy(InputStream input, Writer output, String encoding) {
		try {
			IOUtils.copy(input, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// copy from Reader
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 将字符从<code>Reader</code> 拷贝到 <code>Writer</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * <p>
	 * 大的流(超过2GB)在完成拷贝后，将返回一个<code>-1</code>的字节拷贝值，
	 * 因为无法返回正确的整型字节数。大的流对象的拷贝应该使用
	 * <code>copyLarge(Reader, Writer)</code> 方法.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>Writer</code>
	 * @return 拷贝的字符数，如果大于Integer.MAX_VALUE将返回-1
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:11:56
	 */
	public static Integer copy(Reader input, Writer output) {
		try {
			return IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将字符从大的(超过2GB)<code>Reader</code> 拷贝到 <code>Writer</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>Writer</code>
	 * @return 拷贝的字符数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:14:47
	 */
	public static Long copyLarge(Reader input, Writer output) {
		try {
			return IOUtils.copyLarge(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将字符从大的(超过2GB)<code>Reader</code> 拷贝到 <code>Writer</code>.
	 * </p>
	 * 
	 * <p>
	 * 该方法使用提供的缓存, 因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>Writer</code>
	 * @param buffer 拷贝时使用的缓存
	 * @return 拷贝的字符数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:16:00
	 */
	public static Long copyLarge(Reader input, Writer output, char[] buffer) {
		try {
			return IOUtils.copyLarge(input, output, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将所有或部分字符从大的(超过2GB)<code>Reader</code> 拷贝到 <code>Writer</code>，
	 * 可以选择跳过部分字符。
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>Writer</code>
	 * @param inputOffset : 拷贝前从输入跳过的字符数，负数将拷贝所有
	 * @param length : 要拷贝的字符数. 负数将拷贝所有
	 * @return 拷贝的字符数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:21:08
	 */
	public static Long copyLarge(Reader input, Writer output, final long inputOffset, final long length) {
		try {
			return IOUtils.copyLarge(input, output, inputOffset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将所有或部分字符从大的(超过2GB)<code>Reader</code> 拷贝到 <code>Writer</code>，
	 * 可以选择跳过部分字符。
	 * </p>
	 * 
	 * <p>
	 * 该方法使用提供的缓存, 因此没有必要使用<code>BufferedReader</code>.
	 * <p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>Writer</code>
	 * @param inputOffset : 拷贝前从输入跳过的字符数，负数将拷贝所有
	 * @param length : 要拷贝的字符数. 负数将拷贝所有
	 * @param buffer 拷贝时使用的缓存
	 * @return 拷贝的字符数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:20:48
	 */
	public static Long copyLarge(Reader input, Writer output, final long inputOffset, final long length, char[] buffer) {
		try {
			return IOUtils.copyLarge(input, output, inputOffset, length, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>Reader</code> 的内容拷贝到 <code>OutputStream</code>，
	 * 使用平台默认的编码，并调用flush
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * <p>
	 * 由于OutputStreamWriter的实现，该方法可以执行flush
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link OutputStreamWriter}.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:28:09
	 */
	public static void copy(Reader input, OutputStream output) {
		try {
			IOUtils.copy(input, output);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>Reader</code> 的内容拷贝到 <code>OutputStream</code>，
	 * 使用指定的编码，并调用flush
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * <p>
	 * 由于OutputStreamWriter的实现，该方法可以执行flush
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link OutputStreamWriter}.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:29:38
	 */
	public static void copy(Reader input, OutputStream output, Charset encoding) {
		try {
			IOUtils.copy(input, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 将<code>Reader</code> 的内容拷贝到 <code>OutputStream</code>，
	 * 使用指定的编码，并调用flush
	 * </p>
	 * 
	 * <p>
	 * 该方法内部有对输入进行缓存，因此没有必要使用<code>BufferedReader</code>.
	 * </p>
	 * 
	 * <p>
	 * 字符编码名称可以在这里找到： <a
	 * href="http://www.iana.org/assignments/character-sets">IANA</a>.
	 * </p>
	 * 
	 * <p>
	 * 由于OutputStreamWriter的实现，该方法可以执行flush
	 * </p>
	 * 
	 * <p>
	 * 该方法使用 {@link OutputStreamWriter}.
	 * </p>
	 * 
	 * @param input 要读取的<code>Reader</code>
	 * @param output 要写入的<code>OutputStream</code>
	 * @param encoding 使用的编码，null表示平台默认的编码
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果input 或 output参数为null <br>
	 * 					IOException io错误发生时 <br>
	 * 					UnsupportedCharsetException 如果指定的编码不被支持
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:30:42
	 */
	public static void copy(Reader input, OutputStream output, String encoding) {
		try {
			IOUtils.copy(input, output, encoding);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// content equals
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * 检查两个输入流的内容是否相等
	 * </p>
	 * 
	 * <p>
	 * 如果输入没有缓存，该方法将在内部使用<code>BufferedInputStream</code>对输入进行缓存
	 * </p>
	 * 
	 * @param input1 第一个输入流
	 * @param input2 第二个输入流
	 * @return true：两个输入流的内容相等，或它们都不存在，否则返回false
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:37:25
	 */
	public static Boolean contentEquals(InputStream input1, InputStream input2) {
		try {
			return IOUtils.contentEquals(input1, input2);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 检查两个Reader的内容是否相等
	 * </p>
	 * 
	 * <p>
	 * 如果输入没有缓存，该方法将在内部使用<code>BufferedReader</code>对输入进行缓存
	 * </p>
	 * 
	 * @param input1 第一个reader
	 * @param input2 第二个reader
	 * @return true：两个reader的内容相等，或它们都不存在，否则返回false
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:39:04
	 */
	public static Boolean contentEquals(Reader input1, Reader input2) {
		try {
			return IOUtils.contentEquals(input1, input2);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 检查两个Reader的内容是否相等，忽略EOL字符
	 * </p>
	 * 
	 * <p>
	 * 如果输入没有缓存，该方法将在内部使用<code>BufferedReader</code>对输入进行缓存
	 * </p>
	 * 
	 * @param input1 第一个reader
	 * @param input2 第二个reader
	 * @return true：两个reader的内容相等(忽略EOL字符)，或它们都不存在，否则返回false
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					NullPointerException 如果参数为null <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:40:37
	 */
	public static Boolean contentEqualsIgnoreEOL(Reader input1, Reader input2) {
		try {
			return IOUtils.contentEqualsIgnoreEOL(input1, input2);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字节流中跳过部分字节。该实现保证会在放弃之前读取尽可能多的字节，
	 * 这与它的子类{@link Reader}不同。
	 * </p>
	 * 
	 * @param input 待跳过的字节流
	 * @param toSkip 跳过的字节数
	 * @return 实际跳过的字节数
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IllegalArgumentException 如果toSkip参数为负数 <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:45:02
	 */
	public static Long skip(InputStream input, long toSkip) {
		try {
			return IOUtils.skip(input, toSkip);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字符流中跳过部分字符。该实现保证会在放弃之前读取尽可能多的字符，
	 * 这与它的子类{@link Reader}不同。
	 * </p>
	 * 
	 * @param input 待跳过的字节流
	 * @param toSkip 跳过的字节数
	 * @return 实际跳过的字节数
	 * 
	 * @see Reader#skip(long)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IllegalArgumentException 如果toSkip参数为负数 <br>
	 * 					IOException io错误发生时
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-20 下午10:47:13
	 */
	public static Long skip(Reader input, long toSkip) {
		try {
			return IOUtils.skip(input, toSkip);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 跳过请求的字节数，或如果没有足够的字节数将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link InputStream#skip(long)}可以不跳过指定的参数那么多的字节
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要跳过的流
	 * @param toSkip 要跳过的字节数，不能为负数
	 * @see InputStream#skip(long)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字节数为负数 <br>
	 * 					EOFException 如果要跳过的字节数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:25:38
	 */
	public static void skipFully(InputStream input, long toSkip) {
		try {
			IOUtils.skipFully(input, toSkip);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 跳过请求的字符数，或如果没有足够的字符数将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link Reader#skip(long)}可以不跳过指定的参数那么多的字符
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要跳过的流
	 * @param toSkip 要跳过的字符数，不能为负数
	 * @see Reader#skip(long)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字符数为负数 <br>
	 * 					EOFException 如果要跳过的字符数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:27:40
	 */
	public static void skipFully(Reader input, long toSkip) {
		try {
			IOUtils.skipFully(input, toSkip);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字符流中读取字符。该实现保证在放弃前尽可能多的读取字符。
	 * 这与它的子类{@link Reader}不同。
	 * </p>
	 * 
	 * @param input 要读入字符的字符流
	 * @param buffer 目标
	 * @param offset 初始读入缓冲区的偏移量
	 * @param length 要读取的长度, 必须 >= 0
	 * @return 实际读取的长度; 可能比请求的小(如果到达文件末尾)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:32:39
	 */
	public static Integer read(Reader input, char[] buffer, int offset, int length) {
		try {
			return IOUtils.read(input, buffer, offset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字符流中读取字符。该实现保证在放弃前尽可能多的读取字符。
	 * 这与它的子类{@link Reader}不同。
	 * </p>
	 * 
	 * @param input 要读入字符的字符流
	 * @param buffer 目标
	 * @return 实际读取的长度; 可能比请求的小(如果到达文件末尾)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:33:34
	 */
	public static Integer read(Reader input, char[] buffer) {
		try {
			return IOUtils.read(input, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字节流中读取字节。该实现保证在放弃前尽可能多的读取字节。
	 * 这与它的子类{@link InputStream}不同。
	 * </p>
	 * 
	 * @param input 要读入字符的字节流
	 * @param buffer 目标
	 * @param offset 初始读入缓冲区的偏移量
	 * @param length 要读取的长度, 必须 >= 0
	 * @return 实际读取的长度; 可能比请求的小(如果到达文件末尾)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:35:01
	 */
	public static Integer read(InputStream input, byte[] buffer, int offset, int length) {
		try {
			return IOUtils.read(input, buffer, offset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 从字节流中读取字符。该实现保证在放弃前尽可能多的读取字节。
	 * 这与它的子类{@link InputStream}不同。
	 * </p>
	 * 
	 * @param input 要读入字符的字节流
	 * @param buffer 目标
	 * @return 实际读取的长度; 可能比请求的小(如果到达文件末尾)
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:33:34
	 */
	public static Integer read(InputStream input, byte[] buffer) {
		try {
			return IOUtils.read(input, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取请求数量的字符，或如果没有足够数量的字符时将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link Reader#read(char[], int, int)}可以不跳过指定的参数那么多的字符
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要读入字符的字节流
	 * @param buffer 目标
	 * @param offset 初始读入缓冲区的偏移量
	 * @param length 要读取的长度, 必须 >= 0
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字符数为负数 <br>
	 * 					EOFException 如果要跳过的字符数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:41:36
	 */
	public static void readFully(Reader input, char[] buffer, int offset, int length) {
		try {
			IOUtils.read(input, buffer, offset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取请求数量的字符，或如果没有足够数量的字符时将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link Reader#read(char[], int, int)}可以不跳过指定的参数那么多的字符
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要读入字符的字节流
	 * @param buffer 目标
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字符数为负数 <br>
	 * 					EOFException 如果要跳过的字符数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:41:36
	 */
	public static void readFully(Reader input, char[] buffer) {
		try {
			IOUtils.readFully(input, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取请求数量的字节，或如果没有足够数量的字节时将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link InputStream#read(char[], int, int)}可以不跳过指定的参数那么多的字节
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要读入字节的字节流
	 * @param buffer 目标
	 * @param offset 初始读入缓冲区的偏移量
	 * @param length 要读取的长度, 必须 >= 0
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字节数为负数 <br>
	 * 					EOFException 如果要跳过的字节数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:41:36
	 */
	public static void readFully(InputStream input, byte[] buffer, int offset, int length) {
		try {
			IOUtils.readFully(input, buffer, offset, length);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * <p>
	 * 读取请求数量的字节，或如果没有足够数量的字节时将失败
	 * </p>
	 * 
	 * <p>
	 * 该方法允许{@link InputStream#read(char[], int, int)}可以不跳过指定的参数那么多的字节
	 * (最可能的原因是到达文件末尾)
	 * </p>
	 * 
	 * @param input 要读入字节的字节流
	 * @param buffer 目标
	 * @throws ServiceException 该异常是对下面几种异常的可能包装, 要得知真正的异常请获取该异常的cause: <br>
	 * 					IOException 读取时发生错误 <br>
	 * 					IllegalArgumentException 如果指定的字节数为负数 <br>
	 * 					EOFException 如果要跳过的字节数不正确
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-21 下午7:41:36
	 */
	public static void readFully(InputStream input, byte[] buffer) {
		try {
			IOUtils.readFully(input, buffer);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
	// 封装org.apache.commons.io.IoUtils
	// ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
}

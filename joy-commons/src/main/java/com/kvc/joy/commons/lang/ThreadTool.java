package com.kvc.joy.commons.lang;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.kvc.joy.commons.lang.string.StringTool;
import com.kvc.joy.commons.log.Log;
import com.kvc.joy.commons.log.LogFactory;

/**
 * 线程相关工具类
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @version 2013-01-15
 */
public class ThreadTool {
	
	protected static final Log logger = LogFactory.getLog(StringTool.class);
	
	private ThreadTool() {
	}

	/**
	 * <p>
	 * 让当前线程休眠指定的毫秒数, 并忽略InterruptedException.
	 * </p>
	 * 
	 * @param millis 休眠的毫秒数
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午10:59:55
	 */
	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	/**
	 * <p>
	 * 让当前线程休眠指定的时间, 并忽略InterruptedException.
	 * </p>
	 * 
	 * @param duration 休眠的时间值
	 * @param unit 休眠的时间单位
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午11:03:08
	 */
	public static void sleep(long duration, TimeUnit unit) {
		try {
			Thread.sleep(unit.toMillis(duration));
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	/**
	 * <p>
	 * 按照ExecutorService JavaDoc示例代码编写的Graceful Shutdown方法. 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务. 
	 * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数. 如果仍超時，則強制退出. 
	 * 另对在shutdown时线程本身被调用中断做了处理.
	 * </p>
	 * 
	 * @param pool 线程池
	 * @param shutdownTimeout 关闭超时时间
	 * @param shutdownNowTimeout 现在关闭超时时间
	 * @param timeUnit 时间单位
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午11:05:31
	 */
	public static void gracefulShutdown(ExecutorService pool, int shutdownTimeout, int shutdownNowTimeout,
			TimeUnit timeUnit) {
		pool.shutdown(); // Disable new tasks from being submitted
		try {
			// Wait a while for existing tasks to terminate
			if (!pool.awaitTermination(shutdownTimeout, timeUnit)) {
				pool.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(shutdownNowTimeout, timeUnit)) {
					logger.warn("线程池未结束!");
				}
			}
		} catch (InterruptedException ie) {
			logger.error(ie);
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
	}

	/**
	 * <p>
	 * 直接调用shutdownNow的方法, 有timeout控制. 取消在workQueue中Pending的任务, 并中断所有阻塞函数. 
	 * </p>
	 * 
	 * @param pool 线程池
	 * @param timeout 超时时间
	 * @param timeUnit 时间单位
	 * @since 1.0.0
	 * @author 唐玮琳
	 * @time 2013-5-1 下午11:09:48
	 */
	public static void normalShutdown(ExecutorService pool, int timeout, TimeUnit timeUnit) {
		try {
			pool.shutdownNow();
			if (!pool.awaitTermination(timeout, timeUnit)) {
				logger.warn("线程池未结束!");
			}
		} catch (InterruptedException ie) {
			logger.error(ie);
			Thread.currentThread().interrupt();
		}
	}

}

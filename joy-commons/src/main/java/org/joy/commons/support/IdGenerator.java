package org.joy.commons.support;

import java.util.UUID;

/**
 * ID生成器
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013年12月2日 下午10:28:49
 */
public class IdGenerator {
	
	private IdGenerator() {
	}
	
	/**
	 * 生成不带分隔符的32位UUID(大写)
	 * 
	 * @return
	 * @since 1.0.0
	 * @author Kevice
	 * @time 2013年12月2日 下午10:29:41
	 */
	public static String gen32Uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

}

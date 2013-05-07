/**
 * 
 */
package com.kvc.joy.commons.lang.string;

import org.junit.Test;

/**
 * <p>
 * 
 * </p>
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-1 下午9:02:53
 */
public class RandomStringToolTest {

	@Test
	public void demo() {
		System.out.println("uuid: " + RandomStringTool.uuid());
		System.out.println("randomLong:  " + RandomStringTool.randomLong());
		System.out.println("randomBase62:" + RandomStringTool.randomBase62(7));
	}
	
}

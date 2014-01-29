/**
 * 
 */
package com.kvc.joy.commons.lang;

import junit.framework.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-4-30 上午11:48:06
 */
public class ArrayToolTest {

	@Test
	public void testMapToArrOfArr() {
		Map<Object, Object> map = new LinkedHashMap<Object, Object>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		Object[][] arrOfArr = ArrayTool.mapToArrOfArr(map);

		Assert.assertEquals(3, arrOfArr.length);
		Assert.assertEquals("k1", arrOfArr[0][0]);
		Assert.assertEquals("v1", arrOfArr[0][1]);
		Assert.assertEquals("k2", arrOfArr[1][0]);
		Assert.assertEquals("v2", arrOfArr[1][1]);
		Assert.assertEquals("k3", arrOfArr[2][0]);
		Assert.assertEquals("v3", arrOfArr[2][1]);
	}

}

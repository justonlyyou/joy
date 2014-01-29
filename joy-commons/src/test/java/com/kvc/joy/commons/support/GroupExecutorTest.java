package com.kvc.joy.commons.support;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 唐玮琳
 * @time 2013-4-2 下午10:51:20
 */
public class GroupExecutorTest {

	/**
	 * Test method for
	 * {@link com.kvc.joy.commons.support.GroupExecutor#execute()}.
	 */
	@Test
	public void testExecute() {
		List<Integer> elems = new ArrayList<Integer>();
		for (int i = 1; i <= 50; i++) {
			elems.add(i);
		}

		final StringBuilder sb = new StringBuilder();
		new GroupExecutor<Integer>(elems, 10) {

			@Override
			protected void groupExecute(List<Integer> subList) {
				if(subList.isEmpty() == false) {
					sb.append(subList.get(0) + ",");	
				}
			}

		}.execute();

		Assert.assertEquals("1,11,21,31,41,", sb.toString());
	}
}

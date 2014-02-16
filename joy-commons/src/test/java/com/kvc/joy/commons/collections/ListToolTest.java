package com.kvc.joy.commons.collections;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-7 下午8:58:08
 */
public class ListToolTest {
	
	
	@Test
	@SuppressWarnings("unchecked")
	public void newArrayList() {
		List<?> arrayList = ListTool.newArrayList("1", 2, 3.0);
		Assert.assertTrue(arrayList instanceof ArrayList);
		Assert.assertEquals(3, arrayList.size());
		Assert.assertEquals("1", arrayList.get(0));
		Assert.assertEquals(2, arrayList.get(1));
		Assert.assertEquals(3.0, arrayList.get(2));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void newLinkedList() {
		List<?> linkedList = ListTool.newLinkedList("1", 2, 3.0);
		Assert.assertTrue(linkedList instanceof LinkedList);
		Assert.assertEquals(3, linkedList.size());
		Assert.assertEquals("1", linkedList.get(0));
		Assert.assertEquals(2, linkedList.get(1));
		Assert.assertEquals(3.0, linkedList.get(2));
	}

}

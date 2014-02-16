package com.kvc.joy.commons.collections;

import junit.framework.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 
 * @since 1.0.0
 * @author 唐玮琳
 * @time 2013-5-7 下午9:32:30
 */
public class SetToolTest {
	
	@Test
	@SuppressWarnings("unchecked")
	public void newHashSet() {
		Set<?> hashSet = SetTool.newHashSet("1", 2, 3.0);
		Assert.assertTrue(hashSet instanceof HashSet);
		Assert.assertEquals(3, hashSet.size());
		Assert.assertTrue(hashSet.contains("1"));
		Assert.assertTrue(hashSet.contains(2));
		Assert.assertTrue(hashSet.contains(3.0));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void newLinkedSet() {
		Set<?> linkedHashSet = SetTool.newLinkedHashSet("1", 2, 3.0);
		Assert.assertTrue(linkedHashSet instanceof LinkedHashSet);
		Assert.assertEquals(3, linkedHashSet.size());
		Iterator<?> iterator = linkedHashSet.iterator();
		Assert.assertEquals("1", iterator.next());
		Assert.assertEquals(2, iterator.next());
		Assert.assertEquals(3.0, iterator.next());
	}

}

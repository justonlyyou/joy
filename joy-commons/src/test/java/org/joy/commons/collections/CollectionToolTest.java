package org.joy.commons.collections;

import junit.framework.Assert;
import org.joy.commons.bean.IEntity;
import org.joy.commons.bean.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 
 * </p>
 * 
 * @since 1.0.0
 * @author Kevice
 * @time 2013-5-5 下午10:36:16
 */
public class CollectionToolTest {

	private List<Object> list;
	private List<TestBean> testBeanlist;

	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Object>();
		list.add(new Pair<String, Integer>("1", 1));
		list.add(new Pair<String, Integer>("2", 2));
		list.add(new TestBean(1, "name1"));
		list.add(new TestBean(2, null));
		list.add(new TestBean(1, "name1"));
		
		testBeanlist = new ArrayList<TestBean>(2);
		testBeanlist.add(new TestBean(1, "name1"));
		testBeanlist.add(new TestBean(2, "name2"));
	}

//	@Test
//	public void containsProperty() {
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		boolean result = CollectionTool.contains(list, propertyName, propertyValue);
//		Assert.assertTrue(result);
//
//		// 找不到
//		propertyValue = "would not find";
//		result = CollectionTool.contains(list, propertyName, propertyValue);
//		Assert.assertFalse(result);
//
//		// 容器为null
//		result = CollectionTool.contains(null, propertyName, propertyValue);
//		Assert.assertFalse(result);
//
//		// propertyName为null
//		try {
//			result = CollectionTool.contains(list, null, propertyValue);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		result = CollectionTool.contains(list, propertyName, null);
//		Assert.assertTrue(result);
//	}
//
//	@Test
//	public void containsProperties() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("first", "1");
//		map.put("second", 1);
//
//		// 能找到
//		Assert.assertTrue(CollectionTool.contains(list, map));
//
//		// 找不到
//		map.put("second", "would not find");
//		Assert.assertFalse(CollectionTool.contains(list, map));
//
//		// 容器为null
//		Assert.assertFalse(CollectionTool.contains(null, map));
//
//		// map为null
//		try {
//			CollectionTool.contains(list, null);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//	}
//
//	@Test
//	public void getMatchPropertyBean() {
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		Object matchBean = CollectionTool.getMatchBean(list, propertyName, propertyValue);
//		Assert.assertTrue(matchBean == list.get(1));
//
//		// 找不到
//		propertyValue = "would not find";
//		matchBean = CollectionTool.getMatchBean(list, propertyName, propertyValue);
//		Assert.assertNull(matchBean);
//
//		// 容器为null
//		matchBean = CollectionTool.getMatchBean(null, propertyName, propertyValue);
//		Assert.assertNull(matchBean);
//
//		// propertyName为null
//		try {
//			CollectionTool.getMatchBean(list, null, propertyValue);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		matchBean = CollectionTool.getMatchBean(list, propertyName, null);
//		Assert.assertTrue(matchBean == list.get(3));
//	}
//
//	@Test
//	public void getMatchPropertiesBean() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		Object matchBean = CollectionTool.getMatchBean(list, map);
//		Assert.assertTrue(matchBean == list.get(2));
//
//		// 找不到
//		map.put("name", "would not find");
//		matchBean = CollectionTool.getMatchBean(list, map);
//		Assert.assertNull(matchBean);
//
//		// 容器为null
//		matchBean = CollectionTool.getMatchBean(null, map);
//		Assert.assertNull(matchBean);
//
//		// map为null
//		try {
//			CollectionTool.getMatchBean(list, null);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		matchBean = CollectionTool.getMatchBean(list, map);
//		Assert.assertTrue(matchBean == list.get(3));
//	}
//
//	@Test
//	public void getMatchPropertyBeans() {
//		String propertyName = "name";
//		Object propertyValue = "name1";
//
//		// 能找到
//		List<?> matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		Assert.assertTrue(matchBeans.size() >= 2);
//		if (matchBeans.size() >= 2) {
//			Assert.assertTrue(matchBeans.get(0) == list.get(2));
//			Assert.assertTrue(matchBeans.get(1) == list.get(4));
//		}
//
//		// 找不到
//		propertyValue = "would not find";
//		matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		Assert.assertTrue(matchBeans.isEmpty());
//
//		// 容器为null
//		matchBeans = CollectionTool.getMatchBeans(null, propertyName, propertyValue);
//		Assert.assertTrue(matchBeans.isEmpty());
//
//		// map为null
//		try {
//			CollectionTool.getMatchBeans(list, null, propertyValue);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// map的value为null
//		propertyValue = null;
//		matchBeans = CollectionTool.getMatchBeans(list, propertyName, propertyValue);
//		if (matchBeans.isEmpty() == false) {
//			Assert.assertTrue(matchBeans.get(0) == list.get(3));
//		}
//	}
//
//	@Test
//	public void getMatchPropertiesBeans() {
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		List<?> matchBeans = CollectionTool.getMatchBeans(list, map);
//		Assert.assertTrue(matchBeans.size() >= 2);
//		if (matchBeans.size() >= 2) {
//			Assert.assertTrue(matchBeans.get(0) == list.get(2));
//			Assert.assertTrue(matchBeans.get(1) == list.get(4));
//		}
//
//		// 找不到
//		map.put("name", "would not find");
//		matchBeans = CollectionTool.getMatchBeans(list, map);
//		Assert.assertTrue(matchBeans.isEmpty());
//
//		// 容器为null
//		matchBeans = CollectionTool.getMatchBeans(null, map);
//		Assert.assertTrue(matchBeans.isEmpty());
//
//		// map为null
//		try {
//			CollectionTool.getMatchBeans(list, null);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		matchBeans = CollectionTool.getMatchBeans(list, map);
//		if (matchBeans.isEmpty() == false) {
//			Assert.assertTrue(matchBeans.get(0) == list.get(3));
//		}
//	}
//
//	@Test
//	public void removeMatchPropertyBean() {
//		List<Object> list = BeanTool.deepClone((ArrayList<Object>) this.list);
//		String propertyName = "first";
//		Object propertyValue = "2";
//
//		// 能找到
//		int count = CollectionTool.removeMatchBean(list, propertyName, propertyValue);
//		Assert.assertTrue(count >= 1);
//		Assert.assertFalse(CollectionTool.contains(list, propertyName, propertyValue));
//
//		// 找不到
//		propertyValue = "would not find";
//		count = CollectionTool.removeMatchBean(list, propertyName, propertyValue);
//		Assert.assertTrue(count == 0);
//
//		// 容器为null
//		count = CollectionTool.removeMatchBean(null, propertyName, propertyValue);
//		Assert.assertTrue(count == 0);
//
//		// propertyName为null
//		try {
//			CollectionTool.removeMatchBean(list, null, propertyValue);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// propertyValue为null
//		propertyName = "name";
//		count = CollectionTool.removeMatchBean(list, propertyName, null);
//		Assert.assertTrue(count >= 1);
//		Assert.assertFalse(CollectionTool.contains(list, propertyName, null));
//	}
//
//	@Test
//	public void removeMatchPropertiesBean() {
//		List<Object> list = BeanTool.deepClone((ArrayList<Object>) this.list);
//		Map<String, Object> map = new HashMap<String, Object>(2);
//		map.put("id", 1);
//		map.put("name", "name1");
//
//		// 能找到
//		int count = CollectionTool.removeMatchBean(list, map);
//		Assert.assertTrue(count >= 1);
//		Assert.assertFalse(CollectionTool.contains(list, map));
//
//		// 找不到
//		map.put("name", "would not find");
//		count = CollectionTool.removeMatchBean(list, map);
//		Assert.assertTrue(count == 0);
//
//		// 容器为null
//		count = CollectionTool.removeMatchBean(null, map);
//		Assert.assertTrue(count == 0);
//
//		// map为null
//		try {
//			CollectionTool.removeMatchBean(list, null);
//			Assert.assertFalse(true);
//		} catch (IllegalArgumentException e) {
//			Assert.assertTrue(true);
//		}
//
//		// map的value为null
//		map = new HashMap<String, Object>(1);
//		map.put("id", 2);
//		map.put("name", null);
//		count = CollectionTool.removeMatchBean(list, map);
//		Assert.assertTrue(count >= 1);
//		Assert.assertFalse(CollectionTool.contains(list, map));
//	}
	
	@Test
	public void toEntityMapAll() {
		Map<Integer, TestBean> entityMap = CollectionTool.toEntityMap(testBeanlist);
		Assert.assertEquals(2, entityMap.size());
		Assert.assertTrue(testBeanlist.get(0) == entityMap.get(1));
		Assert.assertTrue(testBeanlist.get(1) == entityMap.get(2));
		
		entityMap = CollectionTool.toEntityMap(null);
		Assert.assertTrue(entityMap.isEmpty());
	}
	
	@Test
	public void toEntityMap() {
		Map<Object, TestBean> entityMap = CollectionTool.toEntityMap(testBeanlist, "name");
		Assert.assertEquals(2, entityMap.size());
		Assert.assertTrue(testBeanlist.get(0) == entityMap.get("name1"));
		Assert.assertTrue(testBeanlist.get(1) == entityMap.get("name2"));
		
		// 容器为null
		entityMap = CollectionTool.toEntityMap(null, "name");
		Assert.assertTrue(entityMap.isEmpty());
		
		// propertyName为null
		try {
			CollectionTool.toEntityMap(testBeanlist, null);
			Assert.assertFalse(true);
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
		
		// propertyName非法
		try {
			CollectionTool.toEntityMap(testBeanlist, "invalidProperty");
			Assert.assertFalse(true);
		} catch (Exception e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void convertElementPropertyToString() {
		TestBean bean1 = new TestBean();
		bean1.setId(1);
		TestBean bean2 = new TestBean();
		bean2.setId(2);

		List<Object> list = new ArrayList<Object>();
		list.add(bean1);
		list.add(bean2);

		Assert.assertEquals("1,2", CollectionTool.extractToString(list, "id", ","));
	}

	@Test
	@SuppressWarnings("unchecked")
	public void convertElementPropertyToList() {
		TestBean bean1 = new TestBean();
		bean1.setId(1);
		TestBean bean2 = new TestBean();
		bean2.setId(2);

		List<Object> list = new ArrayList<Object>();
		list.add(bean1);
		list.add(bean2);
		List<Integer> result = CollectionTool.extractToList(list, "id");
		Assert.assertEquals(2, result.size());
		Assert.assertEquals(1, result.get(0).intValue());
	}

	@Test
	public void convertCollectionToString() {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		String result = CollectionTool.convertToString(list, ",");
		Assert.assertEquals("aa,bb", result);

		result = CollectionTool.convertToString(list, "<li>", "</li>");
		Assert.assertEquals("<li>aa</li><li>bb</li>", result);
	}

	public static class TestBean implements IEntity<Integer> {

		private static final long serialVersionUID = -6135354531806554241L;
		private Integer id;
		private String name;

		public TestBean() {
		}

		public TestBean(Integer id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}
	}

}

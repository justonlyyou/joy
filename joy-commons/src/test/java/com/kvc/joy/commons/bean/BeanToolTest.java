package com.kvc.joy.commons.bean;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * 
 * @author 唐玮琳
 * @time 2013-4-2 下午10:31:15
 */
public class BeanToolTest {

	private Person person;

	@Before
	public void setUp() throws Exception {
		Address address = new Address();
		address.setProvince("hunan");
		address.setCity("changsha");
		address.setStreet("wuyilu");
		address.setZipcode("410000");

		List<String> goods = new ArrayList<String>();
		goods.add("sporting");
		goods.add("singing");
		goods.add("dancing");

		Map<String, String> contact = new LinkedHashMap<String, String>();
		contact.put("student", "Tom");
		contact.put("teacher", "Lucy");

		person = new Person();
		person.setId("id");
		person.setName("Mike");
		person.setSex("male");
		person.setAge(25);
		person.setBirthday(new Date(60528873600000L));
		person.setAddress(address);
		person.setGoods(goods);
		person.setContact(contact);
	}

	@Test
	public void testCloneBean() {
		Person p = BeanTool.shallowClone(person);

		Assert.assertEquals(person, p);
		Assert.assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
	}
	
	@Test
	public void testDeepClone() {
		Person p = BeanTool.deepClone(person);
		
		Assert.assertEquals(person, p);
		Assert.assertTrue(person.getAddress() != p.getAddress()); // 证明是深克隆
	}

	@Test
	public void testCopyProperties() {
		Person p = new Person();

		BeanTool.copyProperties(person, p);

		Assert.assertEquals(person, p);
		Assert.assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
	}

	@Test
	public void testCopyProperty() {
		BeanTool.copyProperty(person, "address.zipcode", "361000");

		Assert.assertEquals("361000", person.getAddress().getZipcode());
	}

	@Test
	public void testCopyPropertiesWithoutCast() {
		BeanTool.copyProperty(person, "address.zipcode", "361000");

		Assert.assertEquals("361000", person.getAddress().getZipcode());
	}

	@Test
	public void testExtract() {
		Map<String, Object> map = BeanTool.extract(person);

		Assert.assertEquals(person.getAge(), map.get("age"));
		Assert.assertEquals(person.getName(), map.get("name"));
		Assert.assertEquals(person.getSex(), map.get("sex"));
		Assert.assertTrue(person.getAddress() == map.get("address"));
		Assert.assertTrue(person.getBirthday() == map.get("birthday"));
		Assert.assertTrue(person.getContact() == map.get("contact"));
		Assert.assertTrue(person.getGoods() == map.get("goods"));
	}
	
	@Test
	public void testGetProperty() {
		Assert.assertEquals(person.getAge(), BeanTool.getProperty(person, "age"));
		Assert.assertEquals(person.getGoods().get(0), BeanTool.getProperty(person, "goods[0]"));
		Assert.assertEquals(person.getContact().get("student"), BeanTool.getProperty(person, "contact(student)"));
		Assert.assertEquals(person.getAddress().getProvince(), BeanTool.getProperty(person, "address.province"));
	}
	
	@Test
	@SuppressWarnings("rawtypes")
	public void testCopyPropertiesByMap() {
		 Class<Pair> destClass = Pair.class;
		 Pair<String, List<String>> srcObj = new Pair<String, List<String>>();
		 srcObj.setLeft("left");
		 List<String> list = new ArrayList<String>();
		 list.add("1");
		 list.add("2");
		 srcObj.setRight(list);
		 
		 Map<String, String> propertyMap = new HashMap<String, String>();
		 propertyMap.put("left", "right"); // left属性的值拷贝到right
		 propertyMap.put("right[0]", "left"); // right属性的值拷贝到left
		
		 Pair dest = BeanTool.copyProperties(destClass, srcObj, propertyMap);
		 
		 Assert.assertEquals(srcObj.getLeft(), dest.getRight());
		 Assert.assertEquals(srcObj.getRight().get(0), dest.getLeft());
	}
	
	@Test
	public void testCopyPropertiesToClassInstance() {
		Person p = BeanTool.copyProperties(person, Person.class);

		Assert.assertEquals(person, p);
		Assert.assertTrue(person.getAddress() == p.getAddress()); // 证明是浅克隆
	}
	
	@Test
	public void testCopyPropertiesExcludeId() {
		Person dest = new Person();
		
		BeanTool.copyPropertiesExcludeId(person, dest);
		
		Assert.assertEquals(person.getAge(), dest.getAge());
		Assert.assertTrue(person.getAddress() == dest.getAddress());
		Assert.assertNull(dest.getId());
	}
	
	@Test
	public void testcopyPropertiesExclude() {
		Person p = new Person();
		
		BeanTool.copyPropertiesExclude(person, p, "age", "address");
		
		Assert.assertEquals(person.getId(), p.getId());
		Assert.assertEquals(0, p.getAge());
		Assert.assertNull(p.getAddress());
		Assert.assertTrue(person.getGoods() == p.getGoods()); // 浅克隆
	}
	
	@Test
	public void testResetPropertiesExcludeId() {
		Person p = BeanTool.shallowClone(person);

		BeanTool.resetPropertiesExcludeId(p);
		
		Assert.assertNull(p.getName());
		Assert.assertNull(p.getAddress());
		Assert.assertEquals(person.getId(), p.getId());
	}
	
}

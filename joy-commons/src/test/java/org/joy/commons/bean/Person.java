package org.joy.commons.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Kevice
 * @time 2013-4-3 下午8:32:42
 */
public class Person implements IEntity<String> {

	private static final long serialVersionUID = -4651767804549188044L;
	private String id;
	private String name;
	private String sex;
	private int age;
	private Date birthday;
	private Address address;
	private List<String> goods;
	private Map<String, String> contact;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, String sex) {
		this.name = name;
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getGoods() {
		return this.goods;
	}

	public void setGoods(List<String> goods) {
		this.goods = goods;
	}

	public Map<String, String> getContact() {
		return this.contact;
	}

	public void setContact(Map<String, String> contact) {
		this.contact = contact;
	}

	public void sayHello() {
		System.out.println("Hello World!");
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void f(String str) {
		System.out.println("Person.f()..." + str);
	}

	public String toString() {
		return "Person.toString()...";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + age;
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((goods == null) ? 0 : goods.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((sex == null) ? 0 : sex.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (age != other.age)
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.toString().equals(other.birthday.toString()))
			return false;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.toString().equals(other.contact.toString()))
			return false;
		if (goods == null) {
			if (other.goods != null)
				return false;
		} else if (!goods.toString().equals(other.goods.toString()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sex == null) {
			if (other.sex != null)
				return false;
		} else if (!sex.equals(other.sex))
			return false;
		return true;
	}

}

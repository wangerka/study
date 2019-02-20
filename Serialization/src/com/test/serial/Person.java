package com.test.serial;

import java.io.Serializable;

public class Person implements Serializable{
//	private static final long serialVersionUID = -5809782578272943999L;
	int age;
	String name;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

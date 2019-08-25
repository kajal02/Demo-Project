/**
 * 
 */
package com.studentrecords;

public class Student {
	private Integer age;
	private String name;
	private Integer id;

	public Student() {

	}

	public Student(Integer age, String name, Integer id) {
		super();
		this.age = age;
		this.name = name;
		this.id = id;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
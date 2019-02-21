package com.galdino.functional.interfaces;

public class Person {
	String firstName;
	String lastName;
	int age;
	
	
	public Person() {
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Person(String firstName, int age) {
		this.firstName = firstName;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return super.toString() + " First Name ---> " + this.firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

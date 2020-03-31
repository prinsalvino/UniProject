package javaFx;

import javaFx.AccessType;

public class Student extends Person {
	String classStudent;

	public Student(String firstName, String lastName, String classStudent, String password, String email) {
		super(firstName,lastName, password, email, AccessType.BASIC);
		this.classStudent = classStudent;
	}

	

}

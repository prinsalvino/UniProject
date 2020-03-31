package javaFx;

public class Teacher extends Person {

	public Teacher(String firstName, String lastName, String password, String email) {
		super(firstName, lastName, password, email, AccessType.EDITOR);
	}

}
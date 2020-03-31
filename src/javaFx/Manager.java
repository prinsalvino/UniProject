package javaFx;


public class Manager extends Person{

	public Manager(String firstName, String lastName, String password,String email) {
		super(firstName, lastName, password,email,AccessType.ADMIN);
	}
	
}
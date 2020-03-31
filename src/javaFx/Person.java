package javaFx;

public abstract class Person {
	 private String firstName;
	 private String lastName;
	 private String password;
	 private String email;
	 AccessType type;
	
	public Person(String firstName, String lastName, String password, String email, AccessType type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.type = type;
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public String setEmail(String email) {
		return this.email = email;
	}
}

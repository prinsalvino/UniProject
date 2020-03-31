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
	
	public String getfirstName() {
		return firstName;
	}
	public String setfirstName(String fname) {
		return firstName = fname;
	}
	
	public String getlastName() {
		return lastName;
	}
	public String setlastName(String lname) {
		return lastName = lname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	public String setEmail(String email) {
		return this.email = email;
	}
}

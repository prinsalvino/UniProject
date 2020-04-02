package javaFx;

public class Teacher extends Person {
	double salary;

	public Teacher(String firstName, String lastName, String password, String email,double salary) {
		super(firstName, lastName, password, email, AccessType.EDITOR);
		this.salary = salary;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}

}
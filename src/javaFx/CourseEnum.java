package javaFx;

public enum CourseEnum {
	JAVA(1, "Java"), PHP(2, "PHP"), CSharp(3, "CSharp");
	
	private int code;
	private String name;

	CourseEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int GetCode(String name) {
		return this.code;
	}

}
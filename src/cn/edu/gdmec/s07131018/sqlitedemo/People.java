package cn.edu.gdmec.s07131018.sqlitedemo;

public class People {
	int id = -1;
	String name;
	int age;
	float height;
	public People(int id, String name, int age, float height) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.height = height;
	}
	public People() {
		super();
	}
	@Override
	public String toString() {
		return "People [id=" + id + ", name=" + name + ", age=" + age
				+ ", height=" + height + "]";
	}
	
}

package com.csus.csc133;

public class StudentNonstop extends Student{
	//Constructor
	public StudentNonstop() {
		super("StudentNonStop");
	}
	
	//student timeRemain stays at 0 after a student collision
	public void studentCollide(Student s1, Student s2) {
		setTimeRemain(0);
	}
}

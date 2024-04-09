package com.csus.csc133;

public class StudentNonstop extends Student{
	//Constructor
	public StudentNonstop() {
		super("StudentNonStop");
	}
	
	//student timeRemain stays at 0 after a student collision
	public void studentCollide(Student s) {
		s.handleCollide(this);
		setTimeRemain(0);
	}
	
	public void move(ViewMap viewMap, double MsToSec, double timeSecond) {
		setTimeRemain(0);
		super.move(viewMap, MsToSec, timeSecond);
	}
}

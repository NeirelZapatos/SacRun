package com.csus.csc133;


public class StudentAngry extends Student{
	//Constructor
	public StudentAngry() {
		super("StudentAngry");
		newStats();
	}
	
	//manipulates student fields
	public void newStats() {
		setTalkiveLevel(getTalkiveLevel() * 2);
	}
}

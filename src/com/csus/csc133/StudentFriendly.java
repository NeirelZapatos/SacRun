package com.csus.csc133;

public class StudentFriendly extends Student{
	//Constructor
	public StudentFriendly() {
		super("StudentFriendly");
		newStats();
	}
	
	//manipulates student fields
	public void newStats() {
		setTalkiveLevel(getTalkiveLevel() / 2);
	}
}

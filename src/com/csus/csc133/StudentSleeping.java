package com.csus.csc133;

public class StudentSleeping extends Student{
	//Constructor
	public StudentSleeping() {
		super("StudentSleeping");
	}
	
	//sets student sweating rate to 0
	public void move() {
		setSweatingRate(0);
	}
	
	
}

package com.csus.csc133;

public class StudentSleeping extends Student{
	//Constructor
	public StudentSleeping() {
		super("StudentSleeping");
		setSweatingRate(0);
	}
	
	public void move(ViewMap viewMap, double MsToSec, double timeSecond) {	
		super.move(viewMap, MsToSec, timeSecond);
		setSpeed(0);
	}	
}

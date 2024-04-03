package com.csus.csc133;

public class StudentRunning extends Student{
	//Constructor
	public StudentRunning() {
		super("StudentRunnning");
		newStats();
	}
	
	//manipulates student fields
	public void newStats() {
		setSweatingRate(getSweatingRate() * 2);
	}
}

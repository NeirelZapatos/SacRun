package com.csus.csc133;

public class StudentBiking extends Student{
	//Constructor
	public StudentBiking() {
		super("StudentBiking");
		newStats();
	}
	
	//manipulates student fields
	public void newStats() {
		setSweatingRate(getSweatingRate() * 2);
		setSpeed(getSpeed() * 3);
	}
}

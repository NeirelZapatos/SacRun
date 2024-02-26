package com.csus.csc133;

public class StudentCar extends Student{
	//Constructor
	public StudentCar() {
		super("StudentCar");
		newStats();
	}
	
	//manipulates student fields
	public void newStats() {
		setSpeed(getSpeed() * 5);
		setSweatingRate(getSweatingRate() * 0);
		if(isCloser(getHead())){
			setHead(90);
		}
		else {
			setHead(270);
		}
		
	}
	
	//returns true if number is closer to 90
	public boolean isCloser(double head) {
		double diff1 = Math.abs(90 - head);
		double diff2 = Math.abs(270 - head);
		
		return diff1 < diff2;
	}
}

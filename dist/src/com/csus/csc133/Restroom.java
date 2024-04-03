package com.csus.csc133;

public class Restroom extends Facility{
	//Constructor
	public Restroom() {
		super("Restroom");
		setSize(90);
	}
	
	//when student collides student waterIntake = 0
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		s.useRestroom();
	}
}

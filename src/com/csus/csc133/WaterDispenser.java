package com.csus.csc133;

public class WaterDispenser extends Facility {
	//Constructor
	public WaterDispenser() {
		super("WaterDispenser");
	}
	
	//increases student hydration and waterIntake after student collides
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		s.drinkWater();
	}
}

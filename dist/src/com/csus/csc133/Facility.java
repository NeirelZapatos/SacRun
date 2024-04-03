package com.csus.csc133;

public abstract class Facility extends GameObject {
	//Initializing fields
	private String name;
	
	//Constructors
	public Facility(String name) {
		this.name = name;
	}
	
	//getter method to retrieve private fields
	public String getName() {
		return name;
	}
	
	//handles collides
	@Override
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		
	}
	
	//displays facility info
	public void displayInfo() {
		System.out.println(getName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + ")");
	}
}

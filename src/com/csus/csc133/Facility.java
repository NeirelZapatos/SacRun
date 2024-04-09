package com.csus.csc133;
import com.codename1.ui.Graphics;

public abstract class Facility extends GameObject {
	//Initializing fields
	private String name;
	
	//Constructors
	public Facility(String name) {
		this.name = name;
	}
	
	//getter method to retrieve private fields
	public String getClassName() {
		return name;
	}
	
	//handles collides
	@Override
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		
	}
	
	//displays facility info
	public void displayInfo() {
		System.out.println(getClassName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + ")");
	}
	
	//draw objects
	abstract public void draw(Graphics g, int mapX, int mapY);
}

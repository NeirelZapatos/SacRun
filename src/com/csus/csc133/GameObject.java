package com.csus.csc133;
import java.util.*;

public abstract class GameObject {
	//Initializing fields
	private Random random = new Random();
	
	private double x = random.nextInt(1001);
	private double y = random.nextInt(801);
	
	//getter methods to retrieve private variables
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	//setter methods to set values
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	//handles object collide
	public abstract void handleCollide (Student s);
	
	//handles displaying info
	public abstract void displayInfo();
}

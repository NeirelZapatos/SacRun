package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	//Initializing fields
	private Random random = new Random();
	
	private double x;
	private double y;
	
	private int size;
	private int color  = ColorUtil.rgb(255, 0, 0);
	
	//getter methods to retrieve private variables
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getColor() {
		return color;
	}
	
	//setter methods to set values
	public void setX(double newX) {
		x = newX;
	}
	
	public void setY(double newY) {
		y = newY;
	}
	
	public void setSize(int newSize) {
		size = newSize;
	}
	
	public void setColor(int newColor) {
		color = newColor;
	}
	
	//initializes position
	public void initPos(int screenWidth, int screenHeight) {
		x = random.nextDouble() * screenWidth + 1;
		y = random.nextDouble() * screenHeight + 1;
		
	}
	
	//handles object collide
	public abstract void handleCollide (Student s);
	
	//handles displaying info
	public abstract void displayInfo();
}

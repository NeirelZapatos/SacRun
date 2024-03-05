package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
	//Initializing fields
	private Random random = new Random();
	
	private double x = random.nextInt(1001);
	private double y = random.nextInt(801);
	
	private int size = ColorUtil.rgb(255, 0, 0);
	private int color;
	
	//Constructor
	public GameObject() {
		
	}
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
	
	//setter methods to 	set values
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
	
	//handles object collide
	public abstract void handleCollide (Student s);
	
	//handles displaying info
	public abstract void displayInfo();
}

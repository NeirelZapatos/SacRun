package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public abstract class GameObject {
	//Initializing fields
	private Random random = new Random();
	
	private double x;
	private double y;
	
	private int size;
	private int color  = ColorUtil.rgb(255, 0, 0);
	
	private int xColMin;
	private int xColMax;
	private int yColMin;
	private int yColMax;
	
	private boolean isColliding = false;
	private Vector<GameObject> collidingObjects = new Vector<>();
	
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
	
	public boolean getIsColliding() {
		return isColliding;
	}
	
	public void setIsColliding(boolean isColliding) {
		this.isColliding = isColliding;
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
	
	public void setXColMin(int xColMin) {
		this.xColMin = xColMin;
	}
	
	public void setXColMax(int xColMax) {
		this.xColMax = xColMax;
	}
	
	public void setYColMin(int xColMin) {
		this.yColMin = xColMin;
	}
	
	public void setYColMax(int yColMax) {
		this.yColMax = yColMax;
	}
	
	public int getXColMin() {
		return xColMin;
	}
	
	public int getXColMax() {
		return xColMax;
	}
	
	public int getYColMin() {
		return yColMin;
	}
	
	public int getYColMax() {
		return yColMax;
	}
	
	public Vector<GameObject> getCollidingObjects() {
		return collidingObjects;
	}
	
	//initializes position
	public void initPos(int screenWidth, int screenHeight) {
		x = random.nextDouble() * screenWidth + 1;
		y = random.nextDouble() * screenHeight + 1;
//		x = 1000;
//		y = 800;
	}
	
	public void setAABB(int xPos, int yPos) {
		xColMin = xPos;
		xColMax = xPos + size;
		yColMin = yPos;
		yColMax = yPos + size;
	}
	
	public void addCollidingObject(GameObject collidingObject) {
			collidingObjects.add(collidingObject);
	}
	
	public void removeCollidingObject(GameObject collidingObject) {
			collidingObjects.remove(collidingObject);
	}
	
	public abstract String getClassName();
	
	//handles object collide
	public abstract void handleCollide (Student s);
	
	//handles displaying info
	public abstract void displayInfo();
	
	public abstract void draw(Graphics g, int mapX, int mapY);
}

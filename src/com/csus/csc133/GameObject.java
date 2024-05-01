package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;
import com.codename1.ui.Transform.NotInvertibleException;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.geom.Shape;

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
	private boolean isSelected = false;
		
	private Vector<GameObject> collidingObjects = new Vector<>();
	
	private Transform translateForm = Transform.makeIdentity();
	private Transform rotateForm = Transform.makeIdentity();
	private Transform drawForm = Transform.makeIdentity();
	
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
	
	public boolean getIsSelected() {
		return isSelected;
	}
	
	public Vector<GameObject> getCollidingObjects() {
		return collidingObjects;
	}
	
	public Transform getTranslateForm() {
		return translateForm;
	}
	
	public Transform getRotateForm() {
		return rotateForm;
	}
	
	public Transform getDrawForm() {
		return drawForm;
	}
	
	//setter methods to set values
	public void setIsColliding(boolean isColliding) {
		this.isColliding = isColliding;
	}
	
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
	
	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	//initializes position
	public void initPos(int screenWidth, int screenHeight) {
		x = random.nextDouble() * screenWidth;
		y = random.nextDouble() * screenHeight;
//		x = 1000;
//		y = 800;
		translateForm.translate((float) x, (float) y);
		rotateForm.rotate((float) Math.toRadians(180), 0, 0);
		rotateForm.scale(-1, 1);
	}
	
	// calculates the AABB
	public void setAABB(int xPos, int yPos) {
		xColMin = xPos;
		xColMax = xPos + size;
		yColMin = yPos;
		yColMax = yPos + size;
	}
	
	
	// checks if (x, y) is in shape
//	public boolean contains(int x, int y) {
//		if(xColMin <= x && x <= xColMax && yColMin <= y && y <= yColMax) {
//			return true;
//		}
//		return false;
//		
//	}
	
	public boolean contains(float[] pt, int pAbsX, int pAbsY) {
		try {
			float[] ptInv = {0, 0};
			Transform inverse = Transform.makeIdentity();
			drawForm.getInverse(inverse);
			inverse.transformPoint(pt, ptInv);
			ptInv[0] -= pAbsX;
			ptInv[1] -= pAbsY;
			return (ptInv[0] * ptInv[0] + ptInv[1] * ptInv[1] < size * size);
		}
		catch(NotInvertibleException e) {
			e.getStackTrace();
		}
		return false;
	}
		
	// adds object to collision vector
	public void addCollidingObject(GameObject collidingObject) {
			collidingObjects.add(collidingObject);
	}
	
	// removes object from collision vector
	public void removeCollidingObject(GameObject collidingObject) {
			collidingObjects.remove(collidingObject);
	}
	
	//checks the bounds
	public void checkInbounds(ViewMap viewMap) {
		if(translateForm.getTranslateX() >= viewMap.getWidth()){
			translateForm.setTranslation(viewMap.getWidth(), translateForm.getTranslateY());
//			setX(viewMap.getWidth());
		}
		if(translateForm.getTranslateX() <= 0) {
			translateForm.setTranslation(0, translateForm.getTranslateY());
//			setX(0);
		}
		if(translateForm.getTranslateY() >= viewMap.getHeight()) {
			translateForm.setTranslation(translateForm.getTranslateX(), viewMap.getHeight());
//			setY(viewMap.getHeight());
		}
		if(translateForm.getTranslateY() <= 0) {
			translateForm.setTranslation(translateForm.getTranslateX(), 0);
//			setY(0);
		}
	}
	
	// displays the class name
	public abstract String getClassName();
	
	//handles object collide
	public abstract void handleCollide (Student s);
	
	//handles displaying info
	public abstract void displayInfo();
	
	//draws the shape
	public abstract void draw(Graphics g, int mapX, int mapY);
}

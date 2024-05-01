package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

public abstract class Student extends GameObject implements IMoveable {
	//initializing fields
	private Random random = new Random();
	private String className;
	
	private int DEFAULT_SPEED = 200;
	private int DEFAULT_TALKING_TIME = 2;
	
	private int head = random.nextInt(360);
	private int speed = DEFAULT_SPEED;
	private int talkiveLevel = DEFAULT_TALKING_TIME;
	private int timeRemain = 0;
	private int hydration = 200;
	private int waterIntake = 0;
	private int sweatingRate = 3;
	private int absenceTime = 0;
	
	private int[] xPt, yPt;
	
	//Constructor
	public Student(String className){
		this.className = className;
		setSize(random.nextInt(21) + 40);
		
		xPt = new int[] {0, - getSize() / 4, getSize() / 4};
		yPt = new int[] {getSize() / 2, - getSize() / 2, - getSize() / 2};
	}
	
	//calls studentCollide method if student collides
	@Override
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		studentCollide(s, this);
	}
	
	//increases students hydration and waterIntake
	public void drinkWater() {
		hydration += 50;
		waterIntake += 50;
	}
	
	//waterIntake set to 0
	public void useRestroom() {
		waterIntake = 0;
	}
	
	//sets the timeRemain for both students to the max talkiveLevel between two the students after a collision
	public void studentCollide(Student s1, Student s2) {
		int timeTalking = Math.max(s1.talkiveLevel, s2.talkiveLevel);
		s1.timeRemain = timeTalking;
		s2.timeRemain = timeTalking;
	}
	
	//if timeRemain is <=0 Calculates new student position, calls checkInbounds(), calls checkHead(), and decreases hydration by the sweatingRate
	public void move(ViewMap viewMap, double MsToSec, double timeSecond) {
		if(timeRemain > 0) {
			if(timeSecond >= 1) {
				timeRemain--;
			}
		}
		else {
			double headingInDegrees = 90 - head;
			double headingInRadians = Math.toRadians(headingInDegrees);
			Transform translateForm = getTranslateForm();
			Transform rotateForm = getRotateForm();
			translateForm.translate((float) (Math.cos(headingInRadians) * (speed * MsToSec)), (float) (Math.sin(headingInRadians) * (speed * MsToSec)));
			rotateForm.setRotation((float) (headingInRadians - Math.toRadians(90)), 0, 0);
		}
		
		if(timeSecond >= 1) {
			hydration -= sweatingRate;
		}
		
		checkInbounds(viewMap);
		checkHead();
	}
	
	//checks id student is in bounds
	public void checkInbounds(ViewMap viewMap) {
		boolean turnHead = false;
		Transform translateForm = getTranslateForm();
		
		if(translateForm.getTranslateX() >= viewMap.getWidth()){
			translateForm.setTranslation(viewMap.getWidth() - 1, translateForm.getTranslateY());
			turnHead = true;
		}
		if(translateForm.getTranslateX() <= 0) {
			translateForm.setTranslation(1, translateForm.getTranslateY());
			turnHead = true;
		}
		if(translateForm.getTranslateY() >= viewMap.getHeight()) {
			translateForm.setTranslation(translateForm.getTranslateX(), viewMap.getHeight() - 1);
			turnHead = true;
		}
		if(translateForm.getTranslateY() <= 0) {
			translateForm.setTranslation(translateForm.getTranslateX(), 1);
			turnHead = true;
		}
		if(turnHead) {
			head += 180;
		}
	}
	
	//checks if head is within 0 - 360
	public void checkHead() {
		if(head > 359) {
			head -= 360;
		}
		if(head < 0) {
			head += 360;
		}
	}
	
	//Displays Student info
	public void displayInfo() {
		System.out.println(getClassName() + ", pos(" + Math.round(getTranslateForm().getTranslateX()) + ", " + Math.round(getTranslateForm().getTranslateY()) + "), head: " + getHead() + ", speed: " + getSpeed() + ", hydration: " + getHydration() + ", talkiveLevel: " + getTalkiveLevel() + ", timeRemain: " + getTimeRemain() + ", Absence: " + getAbsenceTime() + ", WaterIntake: " + getWaterIntake());
    }
	
	//sets color if timeRemain is greater than 0
	public void checkTimeRemain() {
		if(timeRemain > 0) {
			setColor(ColorUtil.rgb(255, 192, 203));
		}
		else {
			setColor(ColorUtil.rgb(255, 0, 0));
		}
	}
	
	// draws student object
	public void draw(Graphics g, int mapX, int mapY) {
		Transform xForm = Transform.makeIdentity();
		Transform oldXForm = Transform.makeIdentity();
		
		g.getTransform(xForm);
		oldXForm = xForm.copy();
		
		xForm.translate(mapX, mapY);
		xForm.concatenate(getTranslateForm());
		xForm.concatenate(getRotateForm());
		xForm.translate(- mapX, - mapY);
		
		g.setTransform(xForm);
		
		g.setColor(getColor());
		
		g.getTransform(getDrawForm());
		
		g.drawPolygon(xPt, yPt, 3);
		
		if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(- getSize() / 4, - getSize() / 2, getSize() / 2, getSize());
    	}
		
		setAABB((int) getTranslateForm().getTranslateX(), (int) getTranslateForm().getTranslateY());
		
		g.setTransform(oldXForm);
	}
	
	// sets the AABB
	public void setAABB(int xPos, int yPos) {
		setXColMin(xPos);
		setXColMax(xPos + getSize() / 2);
		setYColMin(yPos);
		setYColMax(yPos + getSize());
	}
	
	//initializes position
	public void initPos(int screenWidth, int screenHeight) {
		super.initPos(screenWidth, screenHeight);
			
	}
	
	//getter methods to retrieve private fields
	public int getTalkiveLevel() {
		return talkiveLevel;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getSweatingRate() {
		return sweatingRate;
	}
	
	public int getHead() {
		return head;
	}
	
	public int getTimeRemain() {
		return timeRemain;
	}
	
	public int getHydration() {
		return hydration;
	}
	
	public int getWaterIntake() {
		return waterIntake;
	}
	
	public int getAbsenceTime() {
		return absenceTime;
	}
	
	public int getDEFAULT_SPEED() {
		return DEFAULT_SPEED;
	}
	
	public String getClassName() {
		return className;
	}
	
	public int[] getXPt() {
		return xPt;
	}
	
	public int[] getYPt() {
		return yPt;
	}
	
	//setter methods to set new values for private fields
	public void setTalkiveLevel(int newTalkiveLevel) {
		talkiveLevel = newTalkiveLevel;
	}
	
	public void setSweatingRate(int newSweatingRate) {
		sweatingRate = newSweatingRate;
	}
	
	public void setSpeed(int newSpeed) {
		speed = newSpeed;
	}
	
	public void setHead(int newHead) {
		head = newHead;
	}
	
	public void setTimeRemain(int newTimeRemain) {
		timeRemain = newTimeRemain;
	}
	
	public void setAbsenceTime(int newAbsenceTime) {
		absenceTime = newAbsenceTime;
	}
}

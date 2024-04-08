package com.csus.csc133;
import java.util.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;

public abstract class Student extends GameObject implements IMoveable {
	//initializing fields
	private Random random = new Random();
	private String className;
	private Student collidingStudent;
	
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
	
	//Constructor
	public Student(String className){
		this.className = className;
		setSize(random.nextInt(21) + 40);
	}
	
	//calls studentCollide method if student collides
	@Override
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		studentCollide(s, this);
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
			setX(Math.round(getX() + Math.cos(headingInRadians) * (speed * MsToSec))); // (speed * (elapsed time / 1000))
			setY(Math.round(getY() + Math.sin(headingInRadians) * (speed * MsToSec)));			
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
		if(getX() >= viewMap.getWidth()){
			setX(viewMap.getWidth());
			turnHead = true;
		}
		if(getX() <= 0) {
			setX(0);
			turnHead = true;
		}
		if(getY() >= viewMap.getHeight()) {
			setY(viewMap.getHeight());
			turnHead = true;
		}
		if(getY() <= 0) {
			setY(0);
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
		System.out.println(getClassName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + "), head: " + getHead() + ", speed: " + getSpeed() + ", hydration: " + getHydration() + ", talkiveLevel: " + getTalkiveLevel() + ", timeRemain: " + getTimeRemain() + ", Absence: " + getAbsenceTime() + ", WaterIntake: " + getWaterIntake());
    }
	
	public void checkTimeRemain() {
		if(timeRemain > 0) {
			setColor(ColorUtil.rgb(255, 192, 203));
		}
		else {
			setColor(ColorUtil.rgb(255, 0, 0));
		}
	}
	
	public void draw(Graphics g, int mapX, int mapY) {
		g.setColor(getColor());
		int xPos1 = (int) getX() - getSize() / 4 + mapX;
		int xPos2 = (int) getX() + mapX;
		int xPos3 = (int) getX() + getSize() / 4 + mapX;
		int yPos1 = (int) getY() - getSize() / 2 + mapY;
		int yPos2 = (int) getY() + getSize() / 2 + mapY;
		int yPos3 = (int) getY() - getSize() / 2 + mapY;
		
		int[] xPos = {xPos1, xPos2, xPos3};
		int[] yPos = {yPos1, yPos2, yPos3};
		g.drawPolygon(xPos, yPos, 3);
		
		g.setColor(ColorUtil.BLACK);
		g.drawRect(xPos1, yPos1, getSize() / 2, getSize());
		
		setAABB(xPos1, yPos1);
	}
	
	public void setAABB(int xPos, int yPos) {
		setXColMin(xPos);
		setXColMax(xPos + getSize() / 2);
		setYColMin(yPos);
		setYColMax(yPos + getSize());
	}
}

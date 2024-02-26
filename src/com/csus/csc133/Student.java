package com.csus.csc133;
import java.util.*;

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
	
	//Constructor
	public Student(String className){
		this.className = className;
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
	public void move() {
			if(timeRemain > 0) {
				timeRemain--;
			}
			else {
				double headingInDegrees = 90 - head;
				double headingInRadians = Math.toRadians(headingInDegrees);
				setX(Math.round(getX() + Math.cos(headingInRadians) * speed));
				setY(Math.round(getY() + Math.sin(headingInRadians) * speed));			
			}
			hydration -= sweatingRate;
			checkInbounds();
			checkHead();
	}
	
	//checks id student is in bounds
	public void checkInbounds() {
		boolean turnHead = false;
		if(getX() >= 1000) {
			setX(1000);
			turnHead = true;
		}
		if(getX() <= 0) {
			setX(0);
			turnHead = true;
		}
		if(getY() >= 800) {
			setY(800);
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
		System.out.println(getClassName() + ", pos(" + getX() + ", " + getY() + "), head: " + getHead() + ", speed: " + getSpeed() + ", hydration: " + getHydration() + ", talkiveLevel: " + getTalkiveLevel() + ", timeRemain: " + getTimeRemain() + ", Absence: " + getAbsenceTime() + ", WaterIntake: " + getWaterIntake());
    }
}

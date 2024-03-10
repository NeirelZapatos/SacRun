package com.csus.csc133;
import java.util.*;

public class StudentStrategy extends Student{
	private Strategy currStrategy;
	private String currentStrategy;
	
	private Random random = new Random();
	
	
	public StudentStrategy() {
		super("StudentStrategy");
		setStrategy();
	}
	
	public void setStrategy() {
		int randomNum = random.nextInt(3);
		
		switch(randomNum) {
			case 0:
				currStrategy = new ConfusedStrategy(this);
				currentStrategy = "Confused Strategy";
				break;
			case 1:
				currStrategy = new VerticalStrategy(this);
				currentStrategy = "Vertical Strategy";
				break;
			case 2:
				currStrategy = new HorizontalStrategy(this);
				currentStrategy = "Horizontal Strategy";
				break;
		}
	}
	
	public void move() {
		currStrategy.apply();
		super.move();
	}
	
	public void displayInfo() {
		System.out.println(getClassName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + "), head: " + getHead() + ", speed: " + getSpeed() + ", hydration: " + getHydration() + ", talkiveLevel: " + getTalkiveLevel() + ", timeRemain: " + getTimeRemain() + ", Absence: " + getAbsenceTime() + ", WaterIntake: " + getWaterIntake() + ", Current Strategy: " + currentStrategy);
    }
}

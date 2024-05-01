package com.csus.csc133;
import java.util.*;

public class StudentStrategy extends Student{
	private Strategy currStrategy;
	private String currentStrategy;
	
	private Random random = new Random();
	
	//constructor
	public StudentStrategy() {
		super("StudentStrategy");
		setStrategy();
	}
	
	//sets strategy for Student
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
	
	//moves the the current strategy
	public void move(ViewMap viewMap, double MsToSec, double timeSecond) {
		currStrategy.apply();
		super.move(viewMap, MsToSec, timeSecond);
	}
	
	//displays info about student
	public void displayInfo() {
		System.out.println(getClassName() + ", pos(" + Math.round(getTranslateForm().getTranslateX()) + ", " + Math.round(getTranslateForm().getTranslateY()) + "), head: " + getHead() + ", speed: " + getSpeed() + ", hydration: " + getHydration() + ", talkiveLevel: " + getTalkiveLevel() + ", timeRemain: " + getTimeRemain() + ", Absence: " + getAbsenceTime() + ", WaterIntake: " + getWaterIntake() + ", Current Strategy: " + currentStrategy);
    }
}

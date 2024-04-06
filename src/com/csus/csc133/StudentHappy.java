package com.csus.csc133;
import java.util.*;

public class StudentHappy extends Student{
	Random random = new Random();
	
	//Constructor
	public StudentHappy() {
		super("StudentHappy");
	}
	
	//10% chance to increase the speed by 10 times for the next movement
	public void move(ViewMap viewMap, double MsToSec, double timeSecond) {
		int currentSpeed = getSpeed();
		if(random.nextInt(10) == 0) {
			setSpeed(getSpeed() * 10);
		}
		super.move(viewMap, MsToSec, timeSecond);
		setSpeed(currentSpeed);
	}
}

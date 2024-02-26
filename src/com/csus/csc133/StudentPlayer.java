package com.csus.csc133;

public class StudentPlayer extends Student{
	//Constructor
	public StudentPlayer() {
		super("StudentPlayer");
		setSpeed(0);
	}
	
	//sets player speed to default
	public void startMove() {
		setSpeed(getDEFAULT_SPEED());
		//super.move();
	}
	
	//sets player speed to 0
	public void stop() {
		setSpeed(0);
	}
	
	//decreases player head
	public void left() {
		setHead(getHead() - 5);
	}
	
	//increases player head
	public void right() {
		setHead(getHead() + 5);
	}
}

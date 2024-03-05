package com.csus.csc133;

public class Lecture {
	//initializing fields
	private int time = 5;
	
	//returns true if time <= 0
	public boolean checkIfLecture() {
		if(time <= 0) {
			return false;
		}
		return true;
	}
	
	//decreases time field
	public void decreaseTime() {
		time--;
	}
	
	public int getLectureTime() {
		return time;
	}
}

package com.csus.csc133;
import java.util.*;

public class StudentConfused extends Student {
	private Random random = new Random();
	
	//Constructor
	public StudentConfused(){
		super("StudentConfused");
	}
	
	//increases the head before moving
	public void move() {
		setHead(getHead() + random.nextInt(50));
		super.move();
	}
}

package com.csus.csc133;
import com.codename1.ui.Graphics;

public class StudentPlayer extends Student{
	private static StudentPlayer studentPlayer;
	
	//Constructor
	private StudentPlayer() {
		super("StudentPlayer");
		setSpeed(0);
	}
	
	//Singleton Design Pattern
	public static StudentPlayer getStudentPlayer() {
		if(studentPlayer == null) {
			studentPlayer = new StudentPlayer();
		}
		return studentPlayer;
	}
	
	//sets player speed to default
	public void startMove() {
		setSpeed(getDEFAULT_SPEED());
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
	
	public void draw(Graphics g, int mapX, int mapY) {
		g.setColor(getColor());
		int[] xPos = {(int) getX() - getSize() / 4 + mapX, (int) getX() + mapX, (int) getX() + getSize() / 4 + mapX};
		int[] yPos = {(int) getY() - getSize() / 2 + mapY,(int) getY() + getSize() / 2 + mapY, (int) getY() - getSize() / 2 + mapY};
		g.fillPolygon(xPos, yPos, 3);
	}
}

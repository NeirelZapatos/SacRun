package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
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
		setSpeed(getDEFAULT_SPEED() * 2);
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
		int xPos1 = (int) getX() - getSize() / 4 + mapX;
		int xPos2 = (int) getX() + mapX;
		int xPos3 = (int) getX() + getSize() / 4 + mapX;
		int yPos1 = (int) getY() - getSize() / 2 + mapY;
		int yPos2 = (int) getY() + getSize() / 2 + mapY;
		int yPos3 = (int) getY() - getSize() / 2 + mapY;
		
		int[] xPos = {xPos1, xPos2, xPos3};
		int[] yPos = {yPos1, yPos2, yPos3};
		g.fillPolygon(xPos, yPos, 3);
		
		g.setColor(ColorUtil.BLACK);
		g.drawRect(xPos1, yPos1, getSize() / 2, getSize());
		
		setAABB(xPos1, yPos1);
	}
}

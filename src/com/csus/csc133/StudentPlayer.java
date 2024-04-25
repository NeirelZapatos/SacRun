package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

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
		Transform xForm = Transform.makeIdentity();
		Transform oldXForm = Transform.makeIdentity();
		
		g.getTransform(xForm);
		oldXForm = xForm.copy();
		
		xForm.translate(mapX, mapY);
		xForm.concatenate(getTranslateForm());
		xForm.concatenate(getRotateForm());
		xForm.translate(- mapX, - mapY);
		
		g.setTransform(xForm);
		
		g.setColor(getColor());
//		int xPos1 = (int) getX() - getSize() / 4 ;
//		int xPos2 = (int) getX();
//		int xPos3 = (int) getX() + getSize() / 4;
//		int yPos1 = (int) getY() - getSize() / 2;
//		int yPos2 = (int) getY() + getSize() / 2;
//		int yPos3 = (int) getY() - getSize() / 2;
		
//		int[] xPos = {xPos1, xPos2, xPos3};
//		int[] yPos = {yPos1, yPos2, yPos3};
		g.fillPolygon(getXPt(), getYPt(), 3);
		
//		if(getIsSelected()) {
//    		g.setColor(ColorUtil.rgb(255, 0, 0));
//    		g.drawRect(xPos1, yPos1, getSize() / 2, getSize());
//    	}
//		
//		setAABB(xPos1, yPos1);
		
		g.setTransform(oldXForm);
	}
}

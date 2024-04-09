package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Restroom extends Facility{
	//Constructor
	public Restroom() {
		super("Restroom");
		setSize(90);
	}
	
	//when student collides student waterIntake = 0
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		s.useRestroom();
	}
	
	//draws restroom shape
	public void draw(Graphics g, int mapX, int mapY) {
		g.setColor(ColorUtil.rgb(0, 255, 0));
		int xPos = (int) getX() - getSize() / 2 + mapX;
		int yPos = (int) getY() - getSize() / 2 + mapY;
		g.fillRect(xPos, yPos, getSize(), getSize());
		
		if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(xPos, yPos, getSize(), getSize());
    	}
		
		setAABB(xPos, yPos);
	}
	
	//gets restroom name
	public String getClassName() {
		return super.getClassName();
	}
}

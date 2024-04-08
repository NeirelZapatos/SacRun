package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class WaterDispenser extends Facility {
	//Constructor
	public WaterDispenser() {
		super("WaterDispenser");
		setSize(40);
	}
	
	//increases student hydration and waterIntake after student collides
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		s.drinkWater();
	}
	
	public void draw(Graphics g, int mapX, int mapY) {
		g.setColor(ColorUtil.rgb(0, 0, 255));	
		int xPos = (int) getX() - getSize() / 2 + mapX;
		int yPos = (int) getY() - getSize() / 2 + mapY;
		g.fillArc(xPos, yPos, getSize(), getSize(), 0, 360);
		
		g.setColor(ColorUtil.rgb(0, 0, 0));
		g.drawRect(xPos, yPos, getSize(), getSize());
		
		setAABB(xPos, yPos);
	}
	
	public String getClassName() {
		return super.getClassName();
	}
}

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
	
	public void draw(Graphics g, int mapX, int mapY) {
		g.setColor(ColorUtil.rgb(0, 255, 0));
		g.fillRect((int) getX() - getSize() / 2 + mapX, (int) getY() - getSize() / 2 + mapY, getSize(), getSize());
	}
}

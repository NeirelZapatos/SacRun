package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

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
		Transform xForm = Transform.makeIdentity();
		Transform oldXForm = Transform.makeIdentity();
		
		g.getTransform(xForm);
		oldXForm = xForm.copy();
		
		xForm.translate(mapX, mapY);
		xForm.concatenate(getTranslateForm());
		xForm.translate(- mapX, - mapY);
		
		g.setTransform(xForm);
		
		g.setColor(ColorUtil.rgb(0, 255, 0));
		g.getTransform(getDrawForm());
		
		g.fillRect(- getSize() / 2, - getSize() / 2, getSize(), getSize());
		
		if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(- getSize() / 2, - getSize() / 2, getSize(), getSize());
    	}
		
		setAABB((int) getTranslateForm().getTranslateX(), (int) getTranslateForm().getTranslateY());
		
		g.setTransform(oldXForm);
	}
	
	//gets restroom name
	public String getClassName() {
		return super.getClassName();
	}
}

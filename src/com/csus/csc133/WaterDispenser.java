package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Transform;

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
	
	//draws the water dispenser 
	public void draw(Graphics g, int mapX, int mapY) {
		Transform xForm = Transform.makeIdentity();
		Transform oldXForm = Transform.makeIdentity();
		
		g.getTransform(xForm);
		oldXForm = xForm.copy();
		
		xForm.translate(mapX, mapY);
		xForm.concatenate(getTranslateForm());
		xForm.translate(- mapX, - mapY);
		
		g.setTransform(xForm);
		
		g.setColor(ColorUtil.rgb(0, 0, 255));	
		
		g.getTransform(getDrawForm());
		
		g.fillArc(- getSize() / 2, - getSize() / 2, getSize(), getSize(), 0, 360);
		
		if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(- getSize() / 2, - getSize() / 2, getSize(), getSize());
    	}
		
		setAABB((int) getTranslateForm().getTranslateX(), (int) getTranslateForm().getTranslateY());
		
		g.setTransform(oldXForm);
	}
	
	//gets water dispenser name
	public String getClassName() {
		return super.getClassName();
	}
}

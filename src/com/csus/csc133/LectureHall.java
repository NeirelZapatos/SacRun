package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.Transform;

public class LectureHall extends Facility{
	//initializing fields
	private Lecture lecture;
	
	//Constructor
	public LectureHall(String roomNum) {
		super("Lecture Hall " + roomNum);
		setSize(90);
		
	}
	
	//ends lecture of student collides
	@Override
	public void handleCollide(Student s) {
		// TODO Auto-generated method stub
		if(s instanceof StudentPlayer) {
			lecture = null;
		}
	}
	
	//creates a new lecture
	public void startLecture() {
		lecture = new Lecture();
	}
	
	//checks if lecture time <= 0
	public boolean checkLecture() {
		return lecture.checkIfLecture();
	}
	
	//decreases the lecture time
	public void decreaseLectureTime() {
		lecture.decreaseTime();
	}
	
	//sets lecture to null
	public void endLecture() {
		lecture = null;
	}
	
	//displays lectureHall info
    public void displayInfo() {
    	System.out.println(getClassName() + ", pos(" + Math.round(getTranslateForm().getTranslateX()) + ", " + Math.round(getTranslateForm().getTranslateY()) + "), Current Lecture: " + getLecture());
    }
    
    // draws lecture hall
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
    	
    	g.setColor(ColorUtil.rgb(0, 0, 255));	
    	
    	g.getTransform(getDrawForm());
    	
    	g.fillRect(- getSize() / 2, - getSize() / 2, getSize(), getSize());
    	g.drawString(getClassName(), - getSize() / 2,  getSize() / 2);
    	
    	if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(- getSize() / 2, - getSize() / 2, getSize(), getSize());
    	}

    	setAABB((int) getTranslateForm().getTranslateX(), (int) getTranslateForm().getTranslateY());
    	
    	g.setTransform(oldXForm);
    }
    
	//getter methods to retrieve private fields
	public Lecture getLecture() {
		return lecture;
	}
}

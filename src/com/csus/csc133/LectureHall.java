package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;

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
	
	//getter methods to retrieve private fields
	
	public Lecture getLecture() {
		return lecture;
	}
	
	//displays lectureHall info
    public void displayInfo() {
    	System.out.println(getClassName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + "), Current Lecture: " + getLecture());
    }
    
    public void draw(Graphics g, int mapX, int mapY) {
    	g.setColor(ColorUtil.rgb(0, 0, 255));
    	int xPos = (int) getX() - getSize() / 2 + mapX;
    	int yPos = (int) getY() - getSize() / 2 + mapY;
    	g.fillRect(xPos, yPos, getSize(), getSize());
    	g.drawString(getClassName(), xPos - getSize() / 2, yPos + getSize());
    	
    	if(getIsSelected()) {
    		g.setColor(ColorUtil.rgb(255, 0, 0));
    		g.drawRect(xPos, yPos, getSize(), getSize());
    	}
    	
    	setAABB(xPos, yPos);
    }
}

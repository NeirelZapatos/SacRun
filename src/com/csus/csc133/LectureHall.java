package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;

public class LectureHall extends Facility{
	//initializing fields
	private String name = "LectureHall RVR 101";
	
	private Lecture lecture = new Lecture();
	
	//Constructor
	public LectureHall() {
		super("LectureHall RVR 101");
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
	public String getClassName() {
		return name;
	}
	
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
    	g.drawString("Lecture Hall", xPos - getSize() / 2, yPos + getSize());
    	
    	//0x00000000
    	//ColorUtil.rgb(0, 0, 0)
    	g.setColor(0x00000000);
    	g.drawRect(xPos, yPos, getSize(), getSize());
    	
    	setAABB(xPos, yPos);
    }
}

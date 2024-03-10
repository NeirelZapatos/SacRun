package com.csus.csc133;

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
	public String getName() {
		return name;
	}
	
	public Lecture getLecture() {
		return lecture;
	}
	
	//displays lectureHall info
    public void displayInfo() {
    	System.out.println(getName() + ", pos(" + Math.round(getX()) + ", " + Math.round(getY()) + "), Current Lecture: " + getLecture());
    }
	
}

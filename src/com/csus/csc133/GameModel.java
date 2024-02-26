package com.csus.csc133;
import java.util.Vector;
import java.util.Random;

// Used to calculate and manipulate current game state
public class GameModel {
	//initializing fields
	private int gameTime = 0;
	private Restroom restroom;
	private WaterDispenser waterDispenser;
	private LectureHall lectureHall;
	private StudentPlayer studentPlayer;
	private Vector<GameObject> gameObjects = new Vector<>();
	
	private Random random = new Random();
	
	//Constructor
	public GameModel() {
		init();
	}
	
	// initializes the game objects and adds them to the game objects vector
	public void init(){	
		lectureHall = new LectureHall();
		waterDispenser = new WaterDispenser();
		restroom = new Restroom();
		studentPlayer = new StudentPlayer();
		
        StudentAngry angryStudent = new StudentAngry();
        StudentBiking bikingStudent = new StudentBiking();
        StudentCar carStudent = new StudentCar();
        StudentConfused confusedStudent = new StudentConfused();
        StudentFriendly friendlyStudent = new StudentFriendly();
        StudentHappy happyStudent = new StudentHappy();
        StudentNonstop nonStopStudent = new StudentNonstop();
        StudentRunning runningStudent = new StudentRunning();
        StudentSleeping sleepingStudent = new StudentSleeping();  
        
        gameObjects.add(studentPlayer);
        gameObjects.add(angryStudent);
        gameObjects.add(bikingStudent);
        gameObjects.add(carStudent);
        gameObjects.add(confusedStudent);
        gameObjects.add(friendlyStudent);
        gameObjects.add(happyStudent);
        gameObjects.add(nonStopStudent);
        gameObjects.add(runningStudent);
        gameObjects.add(sleepingStudent);
        gameObjects.add(lectureHall);
        gameObjects.add(waterDispenser);
        gameObjects.add(restroom);
	}
	
	//getter methods to retrieve the private fields
	public StudentPlayer getStudentPlayer() {
		return studentPlayer;
	}
	
	public Restroom getRestroom() {
		return restroom;
	}
	
	public WaterDispenser getWaterDispenser() {
		return waterDispenser;
	}
	
	public LectureHall getLectureHall() {
		return lectureHall;
	}
	
	//returns a randomStudent from gameObjects vector
	public Student getRandomStudent() {
		int vectorLength = gameObjects.size();
		boolean cont = true;
		Student randomStudent = null;
		while(cont) {
			int randomIndex = random.nextInt(vectorLength);
			GameObject selectedObject = gameObjects.get(randomIndex);
			if(selectedObject instanceof Student) {
				randomStudent = (Student) selectedObject;
				cont = false;
			}
		}
		return randomStudent;
	}
	
	//Calculates everything for the next game state and checks if it is game over
	public void nextFrame() {
		gameTime++;
		LectureHall lectureHall = null;
		IMoveable student = null;
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject selectedObject = gameObjects.get(i);
			//Manipulate lecture 
			if(selectedObject instanceof LectureHall) {
				lectureHall = (LectureHall) selectedObject;
				if(lectureHall.getLecture() == null || lectureHall.checkLecture() == false) {
					if(lectureHall.getLecture() != null) {
						studentPlayer.setAbsenceTime(studentPlayer.getAbsenceTime() + 1);
						lectureHall.endLecture();
					}
					if(random.nextInt(10) == 0) {
						lectureHall.startLecture();
					}
				}
				else {
					lectureHall.decreaseLectureTime();
				}
			}
			//checks to see of object is move able
			if(selectedObject instanceof IMoveable) {
				student = (IMoveable) selectedObject;
				student.move();	
			}
		}
		//checks if game over
		if(studentPlayer.getHydration() <= 0 || studentPlayer.getAbsenceTime() > 2 || studentPlayer.getWaterIntake() > 199) {
			System.out.println("Gameover. Time: " + gameTime + "\n");
		}
	}
	
	//Used to display the current game state
	public void displayGameState() {
		System.out.println("Time: " + gameTime);
		for(int i = 0; i < gameObjects.size(); i++) {
			GameObject selectedObject = gameObjects.get(i);
			selectedObject.displayInfo();
		}
		System.out.println();
	}	
}

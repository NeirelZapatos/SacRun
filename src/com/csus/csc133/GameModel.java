package com.csus.csc133;
import java.util.Random;
import java.util.Observable;
import com.codename1.ui.*;

// Used to calculate and manipulate current game state
public class GameModel extends Observable{
	//initializing fields
	private int gameTime = 0;
	private Restroom restroom;
	private WaterDispenser waterDispenser;
	private LectureHall lectureHall;
	private StudentPlayer studentPlayer;
	private GameObjectsCollection gameObjects;
	private Student selectedStudent;
	
	private ViewMap viewMap;
	private ViewMessage viewMessage;
	private ViewStatus viewStatus;
	
	private Random random = new Random();
	
	//Constructor
	public GameModel() {
		init();
	}
	
	public int getGameTime() {
		return gameTime;
	}
	
	// initializes the game objects and adds them to the game objects vector
	public void init(){	
		this.gameObjects = new GameObjectsCollection();
		viewMap = new ViewMap(this);
		addStudents();
		addFacility();
		addObservers();
		
	}
	
	public void addStudents() {
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentAngry angryStudent = new StudentAngry();
			angryStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(angryStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentBiking bikingStudent = new StudentBiking();
			gameObjects.add(bikingStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentCar carStudent = new StudentCar();
			carStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(carStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentConfused confusedStudent = new StudentConfused();
			confusedStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(confusedStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentFriendly friendlyStudent = new StudentFriendly();
			friendlyStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(friendlyStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentHappy happyStudent = new StudentHappy();
			happyStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(happyStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentNonstop nonStopStudent = new StudentNonstop();
			gameObjects.add(nonStopStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentRunning runningStudent = new StudentRunning();
			runningStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(runningStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentSleeping sleepingStudent = new StudentSleeping();
			sleepingStudent.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(sleepingStudent);
		}
		studentPlayer = new StudentPlayer();
		studentPlayer.initPos(viewMap.getWidth(), viewMap.getHeight());
		gameObjects.add(studentPlayer);
	}
	
	public void addFacility() {
		for(int i = 0; i <= random.nextInt(3) + 2; i++) {
			restroom = new Restroom();
			restroom.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(restroom);
		}
		for(int i = 0; i <= random.nextInt(3) + 2; i++) {
			waterDispenser = new WaterDispenser();
			waterDispenser.initPos(viewMap.getWidth(), viewMap.getHeight());
			gameObjects.add(waterDispenser);
		}
		lectureHall = new LectureHall();
		lectureHall.initPos(viewMap.getWidth(), viewMap.getHeight());
		gameObjects.add(lectureHall);
	}
	
	public void addObservers() {
		viewMessage = new ViewMessage();
		viewStatus = new ViewStatus(this);
		
		addObserver(viewMap);
		addObserver(viewMessage);
		addObserver(viewStatus);
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
	
	public ViewMap getViewMap() {
		return viewMap;
	}
	
	public ViewMessage getViewMessage() {
		return viewMessage;
	}
	
	public ViewStatus getViewStatus() {
		return viewStatus;
	}
	
	public GameObjectsCollection getGameObjectsCollection(){
		return gameObjects;
	}
	
	public Student getSelectedStudent() {
		return selectedStudent;
	}
	
	public void setSelectedStudent(Student newSelectedStudent) {
		selectedStudent = newSelectedStudent;
	}
	
	//returns a randomStudent from gameObjects vector
//	public Student getRandomStudent() {
//		int vectorLength = gameObjects.size();
//		boolean cont = true;
//		Student randomStudent = null;
//		while(cont) {
//			int randomIndex = random.nextInt(vectorLength);
//			GameObject selectedObject = gameObjects.get(randomIndex);
//			if(selectedObject instanceof Student) {
//				randomStudent = (Student) selectedObject;
//				cont = false;
//			}
//		}
//		return randomStudent;
//	}
	
	//Calculates everything for the next game state and checks if it is game over
	public void nextFrame() {
		GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
		gameTime++;
		LectureHall lectureHall = null;
		IMoveable moveable = null;
		Student student = null;
		
		while(objectIterator.hasNext()) {
			GameObject selectedObject = (GameObject) objectIterator.getNext();
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
				moveable = (IMoveable) selectedObject;
				moveable.move();	
			}
			
			//checks what color the student should be
			if(selectedObject instanceof Student) {
				student = (Student) selectedObject;
				student.checkTimeRemain();
			}
		}
		//checks if game over
		if(studentPlayer.getHydration() <= 0 || studentPlayer.getAbsenceTime() > 2 || studentPlayer.getWaterIntake() > 199) {
			Dialog lostDialog = new Dialog("You Lost!");
			Button exitButton = new Button("Exit");
			Label lostLabel = null;
			
			if(studentPlayer.getHydration() <= 0) {
				lostLabel = new Label("No Hydration ");
			}
			else if(studentPlayer.getHydration() > 2) {
				lostLabel = new Label("Too many Absences");
			}
			else if(studentPlayer.getWaterIntake() > 199) {
				lostLabel = new Label("Too much Water Intake");
			}
			
			exitButton.addActionListener(e -> {
				CN.exitApplication();
	        });
			
			lostDialog.add(lostLabel);;
			lostDialog.add(exitButton);
			lostDialog.show();	
		}
		setChanged();
		notifyObservers();
	}
	
	//Used to display the current game state
//	public void displayGameState() {
//		System.out.println("Time: " + gameTime);
//		for(int i = 0; i < gameObjects.size(); i++) {
//			GameObject selectedObject = gameObjects.get(i);
//			selectedObject.displayInfo();
//		}
//		System.out.println();
//	}	
}

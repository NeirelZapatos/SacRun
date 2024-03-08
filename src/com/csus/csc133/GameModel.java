package com.csus.csc133;
import java.util.Random;
import java.util.Observable;

// Used to calculate and manipulate current game state
public class GameModel extends Observable{
	//initializing fields
	private int gameTime = 0;
	private Restroom restroom;
	private WaterDispenser waterDispenser;
	private LectureHall lectureHall;
	private StudentPlayer studentPlayer;
	private GameObjectsCollection gameObjects;
//	private Vector<GameObject> gameObjects = new Vector<>();
	
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
		addStudents();
		addFacility();
		addObservers();
		
		
//		lectureHall = new LectureHall();
//		waterDispenser = new WaterDispenser();
//		restroom = new Restroom();
//		studentPlayer = new StudentPlayer();
		
		
//        StudentAngry angryStudent = new StudentAngry();
//        StudentBiking bikingStudent = new StudentBiking();
//        StudentCar carStudent = new StudentCar();
//        StudentConfused confusedStudent = new StudentConfused();
//        StudentFriendly friendlyStudent = new StudentFriendly();
//        StudentHappy happyStudent = new StudentHappy();
//        StudentNonstop nonStopStudent = new StudentNonstop();
//        StudentRunning runningStudent = new StudentRunning();
//        StudentSleeping sleepingStudent = new StudentSleeping();  
        
//        gameObjects.add(studentPlayer);
//        gameObjects.add(angryStudent);
//        gameObjects.add(bikingStudent);
//        gameObjects.add(carStudent);
//        gameObjects.add(confusedStudent);
//        gameObjects.add(friendlyStudent);
//        gameObjects.add(happyStudent);
//        gameObjects.add(nonStopStudent);
//        gameObjects.add(runningStudent);
//        gameObjects.add(sleepingStudent);
//        gameObjects.add(lectureHall);
//        gameObjects.add(waterDispenser);
//        gameObjects.add(restroom);
	}
	
	public void addStudents() {
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentAngry angryStudent = new StudentAngry();
			gameObjects.add(angryStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentBiking bikingStudent = new StudentBiking();
			gameObjects.add(bikingStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentCar carStudent = new StudentCar();
			gameObjects.add(carStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentConfused confusedStudent = new StudentConfused();
			gameObjects.add(confusedStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentFriendly friendlyStudent = new StudentFriendly();
			gameObjects.add(friendlyStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentHappy happyStudent = new StudentHappy();
			gameObjects.add(happyStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentNonstop nonStopStudent = new StudentNonstop();
			gameObjects.add(nonStopStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentRunning runningStudent = new StudentRunning();
			gameObjects.add(runningStudent);
		}
		for(int i = 0; i <= random.nextInt(2); i++) {
			StudentSleeping sleepingStudent = new StudentSleeping();
			gameObjects.add(sleepingStudent);
		}
		studentPlayer = new StudentPlayer();
		gameObjects.add(studentPlayer);
	}
	
	public void addFacility() {
		for(int i = 0; i <= random.nextInt(3) + 2; i++) {
			restroom = new Restroom();
			gameObjects.add(restroom);
		}
		for(int i = 0; i <= random.nextInt(3) + 2; i++) {
			waterDispenser = new WaterDispenser();
			gameObjects.add(waterDispenser);
		}
		lectureHall = new LectureHall();
		gameObjects.add(lectureHall);
	}
	
	public void addObservers() {
		viewMap = new ViewMap(this);
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
		IMoveable student = null;
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
				student = (IMoveable) selectedObject;
				student.move();	
			}
		}
		//checks if game over
		if(studentPlayer.getHydration() <= 0 || studentPlayer.getAbsenceTime() > 2 || studentPlayer.getWaterIntake() > 199) {
			System.out.println("Gameover. Time: " + gameTime + "\n");
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

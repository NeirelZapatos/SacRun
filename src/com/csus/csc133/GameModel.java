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
	
	private String latestMessage;
	
	private Random random = new Random();
	
	//Constructor
	public GameModel() {
		init();
	}
	
	// initializes the game objects and adds them to the game objects vector
	public void init(){	
		latestMessage = "Game Started";
		gameObjects = new GameObjectsCollection();
		viewMap = new ViewMap(this);
		addStudents();
		addFacility();
		addObservers();
		initObjPos();
		
		viewMap.displayGameState();
	}
	
	public void initObjPos() {
		GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
		GameObject selectedObject;
		while(objectIterator.hasNext()) {
			selectedObject = (GameObject) objectIterator.getNext();
			selectedObject.initPos(viewMap.getWidth(), viewMap.getHeight());
		}
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
		
		for(int i = 0; i < 3; i++) {
			StudentStrategy strategyStudent = new StudentStrategy();
			gameObjects.add(strategyStudent);
		}
		
		studentPlayer = StudentPlayer.getStudentPlayer();
		gameObjects.add(studentPlayer);
	}
	
	public void addFacility() {
		for(int i = 0; i < random.nextInt(3) + 2; i++) {
			restroom = new Restroom();
			gameObjects.add(restroom);
		}
		
		for(int i = 0; i < random.nextInt(3) + 2; i++) {
			waterDispenser = new WaterDispenser();
			gameObjects.add(waterDispenser);
		}
		lectureHall = new LectureHall();
		gameObjects.add(lectureHall);
	}
	
	public void addObservers() {
		viewMessage = new ViewMessage(this);
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
	
	public int getGameTime() {
		return gameTime;
	}
	
	public String getLatestMessage() {
		return latestMessage;
	}
	
	public void setSelectedStudent(Student newSelectedStudent) {
		selectedStudent = newSelectedStudent;
	}
	
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
			
			latestMessage = "Gameover. Gametime: " + gameTime;
		}
		
		latestMessage = "Next Frame";
		
		setChanged();
		notifyObservers();
	}
	
	public void startMovePlayer() {
		studentPlayer.startMove();
		latestMessage = "Player Started to Move";
	}
	
	public void stopPlayer() {
		studentPlayer.stop();
		latestMessage = "Player has Stopped";
	}
	
	public void leftPlayer() {
		studentPlayer.left();
		latestMessage = "Player has Turned Left";
	}
	
	public void rightPlayer() {
		studentPlayer.right();
		latestMessage = "Player has Turned Right";
	}
	
	public void lectureHallCollide() {
		lectureHall.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Lecture Hall";
	}
	
	public void restroomCollide() {
		restroom.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Restroom";
	}
	
	public void waterDispenserCollide() {
		waterDispenser.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Water Dispenser";
	}
	
	public void exit() {
		Dialog exitDialog = new Dialog("Do you want to exit?");
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		
		noButton.addActionListener(e -> {
            exitDialog.dispose();
        });
		
		yesButton.addActionListener(e -> {
			CN.exitApplication();
        });
		
		exitDialog.add(yesButton);
		exitDialog.add(noButton);
		exitDialog.show();
	}
	
	public void about() {
		Dialog aboutDialog = new Dialog("About");
		Label aboutLabel = new Label("A2, Neirel Zapatos, Spring 2024");
		Button closeButton = new Button("Confirm");
		
		closeButton.addActionListener(e -> {
            aboutDialog.dispose();
        });
		
		aboutDialog.add(aboutLabel);
		aboutDialog.add(closeButton);
		aboutDialog.show();
		
		latestMessage = "Displaying About";
	}
	
	public void changeStrategy() {
		GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
		GameObject selectedObject;
		StudentStrategy strategyStudent;
		
		while(objectIterator.hasNext()) {
			selectedObject = (GameObject) objectIterator.getNext();
			if(selectedObject instanceof StudentStrategy) {
				strategyStudent = (StudentStrategy) selectedObject;
				strategyStudent.setStrategy();
			}
		}
		latestMessage = "Changed Student Strategy";
	}
	
	public void selectStudent() {
		Dialog studentDialog = new Dialog("Select Student");
		Label studentLabel = new Label("0: Angry, 1: Biking, 2: Car, 3: Confused, 4: Friendly, 5: Happy, 6: Nonstop, 7: Sleeping, 8: Running, 9: Strategy ");
		TextField textFieldInput = new TextField();
		Button getTextButton = new Button("OK");
		
		getTextButton.addActionListener(e -> {
			String textInput = textFieldInput.getText();
			GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
			GameObject selectedGameObject;
			Student selectedStudent;
			
			switch(textInput) {
				case "0":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentAngry) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Angry Student Selected";
							break;
						}
					}
					break;
				case "1":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentBiking) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Biking Student Selected";
							break;
						}
					}
					break;
				case "2":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentCar) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Car Student Selected";								
							break;
						}
					}
					break;
				case "3":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentConfused) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Confused Student Selected";
							break;
						}
					}
					break;
				case "4":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentFriendly) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Friendly Student Selected";
							break;
						}
					}
					break;
				case "5":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentHappy) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Happy Student Selected";
							break;
						}
					}
					break;
				case "6":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentNonstop) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Nonstop Student Selected";
							break;
						}
					}
					break;
				case "7":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentSleeping) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Sleeping Student Selected";
							break;
						}
					}
					break;
				case "8":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentRunning) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Running Student Selected";
							break;
						}
					}
					break;
				case "9":
					while(objectIterator.hasNext()) {
						selectedGameObject = (GameObject) objectIterator.getNext();
						if(selectedGameObject instanceof StudentStrategy) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = "Strategy Student Selected";
							break;
						}
					}
					break;
				default:						
					latestMessage = "Invalid Input Selected";
			}
			studentDialog.dispose();
			
		});
		
		studentDialog.add(studentLabel);
		studentDialog.add(textFieldInput);
		studentDialog.add(getTextButton);
		studentDialog.show();
	}
	
	public void setChanged() {
		super.setChanged();
	}
	
	public void notifyObservers() {
		super.notifyObservers();
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
}

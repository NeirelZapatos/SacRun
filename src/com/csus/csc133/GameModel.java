package com.csus.csc133;
import java.util.Random;
import java.util.Vector;
import java.util.Observable;
import com.codename1.ui.*;

// Used to calculate and manipulate current game state
public class GameModel extends Observable{
	//initializing fields
	private int gameTime = 0;
	private double timeSecond = 0;
	private int elapsedTime;
	private boolean isPaused = false;
	private boolean lectureInSession = false;
	
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
	public GameModel(SacRun sacRun, int elapsedTime) {
		//init(sacRun);
		this.elapsedTime = elapsedTime;
		gameObjects = new GameObjectsCollection();
		viewMap = new ViewMap(this);
		viewMessage = new ViewMessage(this);
		viewStatus = new ViewStatus(this);
	}
	
	// initializes the game objects and adds them to the game objects vector
	public void init(SacRun sacRun){	
		latestMessage = "Game Started";
		
		addStudents();
		addFacility();
		addObservers();
		initObjPos();
		
		viewMap.displayGameState();
	}
	
	//initializes object positions 
	public void initObjPos() {
		IteratorInterface objectIterator = gameObjects.getIterator();
		GameObject selectedObject;
		while(objectIterator.hasNext()) {
			selectedObject = (GameObject) objectIterator.getNext();
			selectedObject.initPos(viewMap.getWidth(), viewMap.getHeight());
		}
	}
	
	//adds students to gameObjects
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
			sleepingStudent.checkInbounds(viewMap);
			gameObjects.add(sleepingStudent);
		}
		
		for(int i = 0; i < 3; i++) {
			StudentStrategy strategyStudent = new StudentStrategy();
			gameObjects.add(strategyStudent);
		}
		
		studentPlayer = StudentPlayer.getStudentPlayer();
		gameObjects.add(studentPlayer);
	}
	
	//adds facilities to gameObjects
	public void addFacility() {
		for(int i = 0; i < random.nextInt(3) + 2; i++) {
			restroom = new Restroom();
			restroom.checkInbounds(viewMap);
			gameObjects.add(restroom);
		}
		
		for(int i = 0; i < random.nextInt(3) + 2; i++) {
			waterDispenser = new WaterDispenser();
			waterDispenser.checkInbounds(viewMap);
			gameObjects.add(waterDispenser);
		}
		
		for(int i = 0; i < 3; i++) {
			lectureHall = new LectureHall("10" + i);
			lectureHall.checkInbounds(viewMap);
			gameObjects.add(lectureHall);
		}
	}
	
	//adds objects as observers
	public void addObservers() {
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
	
	public boolean getIsPaused() {
		return isPaused;
	}
	
	//Calculates everything for the next game state and checks if it is game over
	public void nextFrame() {
		if(!isPaused) {
			double MsToSec = (double) elapsedTime / 1000;
			timeSecond += MsToSec;
			
			IteratorInterface objectIterator = gameObjects.getIterator();
			LectureHall currLectureHall = null;
			IMoveable moveable = null;
			Student student = null;
			
			while(objectIterator.hasNext()) {
				GameObject selectedObject = objectIterator.getNext();
				//Manipulate lecture 
				if(selectedObject instanceof LectureHall) {
					currLectureHall = (LectureHall) selectedObject;
					if(currLectureHall.getLecture() == null || currLectureHall.checkLecture() == false) {
						if(currLectureHall.getLecture() != null && lectureInSession) {
							studentPlayer.setAbsenceTime(studentPlayer.getAbsenceTime() + 1);
							currLectureHall.endLecture();
							lectureInSession = false;
						}
						else if(random.nextInt(10) == 0 && timeSecond >= 1 && !lectureInSession) {
							currLectureHall.startLecture();
							lectureHall = currLectureHall;
							lectureInSession = true;
						}
					}
					if(timeSecond >= 1 && currLectureHall.getLecture() != null) {
						currLectureHall.decreaseLectureTime();
					}
				}
				//checks to see of object is move able
				if(selectedObject instanceof IMoveable) {
					moveable = (IMoveable) selectedObject;
					moveable.move(viewMap, MsToSec, timeSecond);	
				}
				
				//checks what color the student should be
				if(selectedObject instanceof Student) {
					student = (Student) selectedObject;
					student.checkTimeRemain();
				}
					
				//handles the collisions between objects
				IteratorInterface collisionIterator = gameObjects.getIterator();
				while(collisionIterator.hasNext()) {
					GameObject collisionObject = collisionIterator.getNext();					
					if(collisionObject instanceof Student && selectedObject != collisionObject) {
						Vector<GameObject> selectedCollide = selectedObject.getCollidingObjects();
						Vector<GameObject> collisionCollide = collisionObject.getCollidingObjects();
						boolean checkCollision = detectCollision(selectedObject, collisionObject);
						
						//checks if there is collision add to collision vectors
						if(checkCollision) {							
							if(!selectedObject.getIsColliding() || !collisionObject.getIsColliding()) {
								selectedObject.setIsColliding(true);
								collisionObject.setIsColliding(true);						
								if(!selectedCollide.contains(collisionObject) || !collisionCollide.contains(selectedObject)) {
									selectedObject.handleCollide((Student) collisionObject);
									latestMessage = selectedObject.getClassName() + " collided with " + collisionObject.getClassName();
									
									if(!selectedCollide.contains(collisionObject)) {
										selectedObject.addCollidingObject(collisionObject);
									}					
									if(!collisionCollide.contains(selectedObject)) {
										collisionObject.addCollidingObject(selectedObject);
									}
									
									if(selectedObject == currLectureHall && collisionObject instanceof StudentPlayer) {
										lectureHall.endLecture();
										lectureInSession = false;
									}
								}
							}		
						}
						// if their is not collision remove from collision vectors
						else if(selectedCollide.contains(collisionObject) && collisionCollide.contains(selectedObject)) {	
							if(selectedCollide.contains(collisionObject)) {
								selectedObject.removeCollidingObject(collisionObject);
							}
							if(collisionCollide.contains(selectedObject)) {
								collisionObject.removeCollidingObject(selectedObject);
							}
							if(selectedObject.getIsColliding() && collisionObject.getIsColliding()) {
								selectedObject.setIsColliding(false);
								collisionObject.setIsColliding(false);
							}
						}
					}	
				}
			}
			
			//checks if game over and displays dialog
			if(studentPlayer.getHydration() <= 0 || studentPlayer.getAbsenceTime() > 2 || studentPlayer.getWaterIntake() > 199) {
				Dialog lostDialog = new Dialog("You Lost!");
				Button exitButton = new Button("Exit");
				Label lostLabel = null;
				
				if(studentPlayer.getHydration() <= 0) {
					lostLabel = new Label("No Hydration ");
				}
				else if(studentPlayer.getAbsenceTime() > 2) {
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
			
			if(timeSecond >= 1) {
				viewMap.displayGameState();
				gameTime++;
				timeSecond = 0;
			}
			
			//updates observers
			setChanged();
			notifyObservers();
		}	
	}
	
	//detects a collision
	public boolean detectCollision(GameObject selectedObject, GameObject collisionObject) {
		if(collisionObject instanceof Student && selectedObject != collisionObject) {
			int sXColMin = selectedObject.getXColMin();
			int sXColMax = selectedObject.getXColMax();
			int sYColMin = selectedObject.getYColMin();
			int sYColMax = selectedObject.getYColMax();
			
			int cXColMin = collisionObject.getXColMin();
			int cXColMax = collisionObject.getXColMax();
			int cYColMin = collisionObject.getYColMin();
			int cYColMax = collisionObject.getYColMax();
				
			if(sXColMax >= cXColMin && sXColMin <= cXColMax) {
				if(sYColMax >= cYColMin && sYColMin <= cYColMax) {
					return true;
				}
			}
		}
		return false;
	}
	
	//moves Player
	public void startMovePlayer() {
		studentPlayer.startMove();
		latestMessage = "Player Started to Move";
	}
	
	//stops player
	public void stopPlayer() {
		studentPlayer.stop();
		latestMessage = "Player has Stopped";
	}
	
	//turns player left
	public void leftPlayer() {
		studentPlayer.left();
		latestMessage = "Player has Turned Left";
	}
	
	//turn player right
	public void rightPlayer() {
		studentPlayer.right();
		latestMessage = "Player has Turned Right";
	}
	
	//player collides with player
	public void lectureHallCollide() {
		lectureHall.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Lecture Hall";
	}
	
	//player collides with restroom
	public void restroomCollide() {
		restroom.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Restroom";
	}
	
	//player collides with water dispenser
	public void waterDispenserCollide() {
		waterDispenser.handleCollide(studentPlayer);
		latestMessage = "Player Collide with Water Dispenser";
	}
	
	//exits application
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
	
	//displays my information
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
	
	//changes the strategy of studentStrategy
	public void changeStrategy() {
		IteratorInterface objectIterator = gameObjects.getIterator();
		GameObject selectedObject;
		StudentStrategy strategyStudent;
		
		while(objectIterator.hasNext()) {
			selectedObject = objectIterator.getNext();
			if(selectedObject instanceof StudentStrategy) {
				strategyStudent = (StudentStrategy) selectedObject;
				strategyStudent.setStrategy();
			}
		}
		
		latestMessage = "Changed Student Strategy";
	}
	
	//allows user to select a student
	public void selectStudent() {
		Dialog studentDialog = new Dialog("Select Student");
		Label studentLabel = new Label("0: Angry, 1: Biking, 2: Car, 3: Confused, 4: Friendly, 5: Happy, 6: Nonstop, 7: Sleeping, 8: Running, 9: Strategy ");
		TextField textFieldInput = new TextField();
		Button getTextButton = new Button("Confirm");
		
		getTextButton.addActionListener(e -> {
			String textInput = textFieldInput.getText();
			IteratorInterface objectIterator = gameObjects.getIterator();
			GameObject selectedGameObject;
			Student selectedStudent;
			
			switch(textInput) {
				case "0":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentAngry) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "1":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentBiking) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "2":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentCar) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";								
							break;
						}
					}
					break;
				case "3":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentConfused) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "4":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentFriendly) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "5":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentHappy) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "6":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentNonstop) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "7":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentSleeping) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "8":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentRunning) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
							break;
						}
					}
					break;
				case "9":
					while(objectIterator.hasNext()) {
						selectedGameObject = objectIterator.getNext();
						if(selectedGameObject instanceof StudentStrategy) {
							selectedStudent = (Student) selectedGameObject;
							this.selectedStudent = selectedStudent;
							latestMessage = this.selectedStudent.getClassName() + " Selected";
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
	
	// changes if paused or not
	public void changePauseButton() {
		if(!isPaused) {
			isPaused = true;
		}
		else {
			isPaused = false;
		}
	}
	
	//used to update observers
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

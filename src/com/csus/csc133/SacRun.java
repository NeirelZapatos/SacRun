/*
* This file is the skeleton code of SacRun for the CSC133 assignment at the 
* Computer Science Department of California State University,
* Sacramento. Note that the CSC133 assignments are confidential and 
* copyrighted. It is not allowed to publish any CSC133 source code 
* to the public. Any existing source code for CSC133 assignments on the   
* internet represents that this student did/will disclose confidential  
* materials to the public and violate the designer's copyright.
*
*/

package com.csus.csc133;
import com.codename1.ui.layouts.*;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.Container;

public class SacRun extends Form{
	//initializing fields
	private GameModel gm;
	private StudentPlayer studentPlayer;
	private LectureHall lectureHall;
	private Restroom restroom;
	private WaterDispenser waterDispenser;
	
	//Constructor
	public SacRun(){
		super(new BorderLayout());
		
		this.gm = new GameModel();
		this.studentPlayer = gm.getStudentPlayer();
		this.restroom = gm.getRestroom();
		this.waterDispenser = gm.getWaterDispenser();
		this.lectureHall = gm.getLectureHall();
		
		A2();
		
		
//		A1();
			
//		gm.init();
		
	}
	
	private void A2() {	
		Dialog studentDialog = new Dialog("Dialog Title");
		studentDialog.add(new TextField());
		
		
		Button moveButton = new Button(new PlayerCommand(studentPlayer, "Move"));
		Button stopButton = new Button(new PlayerCommand(studentPlayer, "Stop"));
		Button leftButton = new Button(new PlayerCommand(studentPlayer, "Turn Left"));
		Button rightButton = new Button(new PlayerCommand(studentPlayer, "Turn Right"));
		Button changeButton = new Button("Change Strategy");
		Button lectureCollideButton = new Button(new CollideCommand(studentPlayer, lectureHall, "Lecture Hall"));
		Button restroomCollideButton = new Button(new CollideCommand(studentPlayer, restroom, "Restroom"));
		Button waterDispenserCollideButton = new Button(new CollideCommand(studentPlayer, waterDispenser, "Water Dispenser"));
//		Button studentCollideButton = new Button(new CollideCommand(studentPlayer, gm.getRandomStudent(), "Student"));
		//make sure that you change the it so it opens a dialog 
		Button nextFrameButton = new Button("Next Frame");
		
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));		
		buttonContainer.add(moveButton);
		buttonContainer.add(stopButton);
		buttonContainer.add(leftButton);
		buttonContainer.add(rightButton);
		buttonContainer.add(changeButton);
		buttonContainer.add(lectureCollideButton);
		buttonContainer.add(restroomCollideButton);
		buttonContainer.add(waterDispenserCollideButton);
//		buttonContainer.add(studentCollideButton);
		buttonContainer.add(nextFrameButton);
		
//		moveButton.addActionListener(e -> {
//			studentPlayer.startMove();
//		});
//		stopButton.addActionListener(e -> {
//			studentPlayer.stop();
//		});
//		leftButton.addActionListener(e -> {
//			studentPlayer.left();
//		});
//		rightButton.addActionListener(e -> {
//			studentPlayer.right();
//		});
//		changeButton.addActionListener(e -> {
//			
//		});
//		lectureCollideButton.addActionListener(e -> {
//			lectureHall.handleCollide(studentPlayer);
//		});
//		restroomCollideButton.addActionListener(e -> {
//			restroom.handleCollide(studentPlayer);
//		});
//		waterDispenserCollideButton.addActionListener(e -> {
//			waterDispenser.handleCollide(studentPlayer);
//		});
//		studentCollideButton.addActionListener(e -> {
//			studentPlayer.handleCollide(gm.getRandomStudent());
//		});//change it so it has a dialog box that accepts an input to pick a student instead of random
		nextFrameButton.addActionListener(e -> {
			gm.nextFrame();
		});
		
		
		ViewMap viewMap = gm.getViewMap();
		ViewStatus viewStatus = gm.getViewStatus();
		ViewMessage viewMessage = gm.getViewMessage();
//		gm.addObserver(viewMap);
//		gm.addObserver(viewStatus);
//		gm.addObserver(viewMap);
		
		Toolbar toolBar = new Toolbar();
		setToolbar(toolBar);
		Command changeStrategySide = new Command("Change Strategy");
		Command aboutSide = new Command("About");
		Command exitSide = new Command("Exit");
		Command rightLectureHall = new Command("Lecture Hall");
		Command rightAbout = new Command("About");
		toolBar.addCommandToSideMenu(changeStrategySide);
		toolBar.addCommandToSideMenu(aboutSide);
		toolBar.addCommandToSideMenu(exitSide);
		toolBar.addCommandToRightBar(rightLectureHall);
		toolBar.addCommandToRightBar(rightAbout);
		
		add(BorderLayout.EAST, viewStatus);
		add(BorderLayout.CENTER, viewMap);
		add(BorderLayout.WEST, buttonContainer);
		add(BorderLayout.SOUTH, viewMessage);
		
		show();		
	}
	
	//UI provided for A1 only, remove it in A2
//	private void A1() {
//		int worldWidth = 1000;
//		int worldHeight = 800;
//		Label myLabel=new Label("Enter a Command:");
//		TextField myTextField=new TextField();
//		this.add(myLabel).add(myTextField);
//		this.show();
//		myTextField.addActionListener(new ActionListener(){
//			public void actionPerformed(ActionEvent evt) {
//				String sCommand=myTextField.getText().toString();
//				myTextField.clear();
//				if(sCommand.isEmpty()) return;
//				handleInput(sCommand.charAt(0));
//			}
//		});
//	}

	//handles user inputs
//	private void handleInput(char key) {
//		//TODO: add code to handle different key command
//		switch(key) {
//			//player starts to move
//			case 'w':
//				studentPlayer.startMove();
//				break;
//			//player stops moving
//			case 's':
//				studentPlayer.stop();
//				break;
//			//-5 to the head of the player
//			case 'a':
//				studentPlayer.left();
//				break;
//			//+5 to the head of the player
//			case 'd':
//				studentPlayer.right();
//				break;
//			//ends lecture of player collides with lecture hall
//			case '1':
//				lectureHall.handleCollide(studentPlayer);
//				break;
//			//water intake = 0 if player collides with restroom
//			case '2':
//				restroom.handleCollide(studentPlayer);
//				break;
//			//hydration and water intake increase by 50 if player collides with water dispenser
//			case '3':
//				waterDispenser.handleCollide(studentPlayer);
//				break;
//			//player and student in collision timeRemain increases after colliding
//			case '4':
//				studentPlayer.handleCollide(gm.getRandomStudent());
//				break;
//			//continues the game state
//			case 'f':
//				gm.nextFrame();
//				break;
//			//displays the current game state
//			case 'm':
//				gm.displayGameState();
//				break;
//			//displays my name
//			case 'i':
//				System.out.println("Neirel Zapatos");
//				break;
//			//if an incorrect input is put in
//			default:
//				System.out.println("That is not a command");
//		}		
//	}
		
}

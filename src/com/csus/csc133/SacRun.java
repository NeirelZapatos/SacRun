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
//	private StudentPlayer studentPlayer;
//	private LectureHall lectureHall;
//	private Restroom restroom;
//	private WaterDispenser waterDispenser;
//	private GameObjectsCollection gameObjects;
	
	//Constructor
	public SacRun(){
		super(new BorderLayout());
		
		this.gm = new GameModel();
		
		A2();
		
		
//		A1();
			
//		gm.init();
		
	}
	
	private void A2() {			
		SpecialCommand aboutCommand = new SpecialCommand(gm, "About");
		CollideCommand lectureCommand = new CollideCommand(gm, "Lecture Hall");
		
		Button moveButton = new Button(new PlayerCommand(gm, "Move"));
		Button stopButton = new Button(new PlayerCommand(gm, "Stop"));
		Button leftButton = new Button(new PlayerCommand(gm, "Turn Left"));
		Button rightButton = new Button(new PlayerCommand(gm, "Turn Right"));
		Button changeButton = new Button(new SpecialCommand(gm, "Change Strategy"));
		Button lectureCollideButton = new Button(lectureCommand);
		Button restroomCollideButton = new Button(new CollideCommand(gm, "Restroom"));
		Button waterDispenserCollideButton = new Button(new CollideCommand(gm, "Water Dispenser"));
		Button studentButton = new Button(new SpecialCommand(gm, "Student"));
		Button nextFrameButton = new Button(new SpecialCommand(gm, "Next Frame"));
		
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
		buttonContainer.add(studentButton);
		buttonContainer.add(nextFrameButton);
			
		Toolbar toolBar = new Toolbar();
		setToolbar(toolBar);
		Command changeStrategySide = new Command("Change Strategy");
		SpecialCommand aboutSide = aboutCommand;
		SpecialCommand exitSide = new SpecialCommand(gm, "Exit");
		Command rightLectureHall = lectureCommand;
		SpecialCommand rightAbout = aboutCommand;
		toolBar.addCommandToSideMenu(changeStrategySide);
		toolBar.addCommandToSideMenu(aboutSide);
		toolBar.addCommandToSideMenu(exitSide);
		toolBar.addCommandToRightBar(rightLectureHall);
		toolBar.addCommandToRightBar(rightAbout);
		
		ViewMap viewMap = gm.getViewMap();
		ViewStatus viewStatus = gm.getViewStatus();
		ViewMessage viewMessage = gm.getViewMessage();
		
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

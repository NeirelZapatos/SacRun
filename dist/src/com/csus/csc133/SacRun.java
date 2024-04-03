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
	
	//Constructor
	public SacRun(){
		super(new BorderLayout());
		
		this.gm = new GameModel();
		
		A2();
		
		
//		A1();
			
//		gm.init();
		
	}
	
	//sets up GUI
	private void A2() {		
		//init commands
		SpecialCommand aboutCommand = new SpecialCommand(gm, "About");
		CollideCommand lectureCommand = new CollideCommand(gm, "Lecture Hall");
		SpecialCommand changeCommand = new SpecialCommand(gm, "Change Strategy");
		
		//init buttons with command parameters
		ModifiedButton moveButton = new ModifiedButton(new PlayerCommand(gm, "Move"));
		ModifiedButton stopButton = new ModifiedButton(new PlayerCommand(gm, "Stop"));
		ModifiedButton leftButton = new ModifiedButton(new PlayerCommand(gm, "Turn Left"));
		ModifiedButton rightButton = new ModifiedButton(new PlayerCommand(gm, "Turn Right"));
		ModifiedButton changeButton = new ModifiedButton(changeCommand);
		ModifiedButton lectureCollideButton = new ModifiedButton(lectureCommand);
		ModifiedButton restroomCollideButton = new ModifiedButton(new CollideCommand(gm, "Restroom"));
		ModifiedButton waterDispenserCollideButton = new ModifiedButton(new CollideCommand(gm, "Water Dispenser"));
		ModifiedButton studentButton = new ModifiedButton(new SpecialCommand(gm, "Student"));
		ModifiedButton nextFrameButton = new ModifiedButton(new SpecialCommand(gm, "Next Frame"));
		
		//creating container for buttons
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
			
		//creating toolbar
		Toolbar toolBar = new Toolbar();
		setToolbar(toolBar);
		
		//adding to side menu
		Command changeStrategySide = changeCommand;
		Command aboutSide = aboutCommand;
		Command exitSide = new SpecialCommand(gm, "Exit");
		toolBar.addCommandToSideMenu(changeStrategySide);
		toolBar.addCommandToSideMenu(aboutSide);
		toolBar.addCommandToSideMenu(exitSide);
		
		//adding to right side tool bar
		Command rightLectureHall = lectureCommand;
		Command rightAbout = aboutCommand;
		toolBar.addCommandToRightBar(rightLectureHall);
		toolBar.addCommandToRightBar(rightAbout);
		
		//getting and adding GUI containers
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

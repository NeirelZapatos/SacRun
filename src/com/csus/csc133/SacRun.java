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
import com.codename1.ui.util.UITimer;
import com.codename1.ui.*;
import com.codename1.ui.events.*;
import com.codename1.ui.Container;
import java.io.InputStream;
import com.codename1.media.*;

public class SacRun extends Form implements Runnable{
	//initializing fields
	private GameModel gm;
	private StudentPlayer studentPlayer;
	private UITimer gameTimer;
	private int elapsedTime = 20;
	
	//Constructor
	public SacRun(){
		super(new BorderLayout());

		this.gm = new GameModel(this, elapsedTime);
		this.gameTimer = new UITimer(this);
		gameTimer.schedule(elapsedTime, true, this);
		
		A2();
		gm.init(this);
		
		this.studentPlayer = gm.getStudentPlayer();
	}
	
	//sets up GUI
	private void A2() {		
		//init commands
		SpecialCommand aboutCommand = new SpecialCommand(gm, "About");
		SpecialCommand changeCommand = new SpecialCommand(gm, "Change Strategy");
		
		//init buttons with command parameters
		ModifiedButton moveButton = new ModifiedButton(new PlayerCommand(gm, "Move"));
		ModifiedButton stopButton = new ModifiedButton(new PlayerCommand(gm, "Stop"));
		ModifiedButton leftButton = new ModifiedButton(new PlayerCommand(gm, "Turn Left"));
		ModifiedButton rightButton = new ModifiedButton(new PlayerCommand(gm, "Turn Right"));
		ModifiedButton changeButton = new ModifiedButton(changeCommand);
		ModifiedButton pauseButton = new ModifiedButton(new SpecialCommand(gm, "Pause"));
		ModifiedButton changePosButton = new ModifiedButton(new SpecialCommand(gm, "Change Position"));

		//creating container for buttons
		Container buttonContainer = new Container();
		buttonContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));		
		buttonContainer.add(moveButton);
		buttonContainer.add(stopButton);
		buttonContainer.add(leftButton);
		buttonContainer.add(rightButton);
		buttonContainer.add(changeButton);
		buttonContainer.add(pauseButton);
		buttonContainer.add(changePosButton);
			
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
		
		Command rightAbout = aboutCommand;
		toolBar.addCommandToRightBar(rightAbout);
		
		//getting and adding GUI containers
		ViewMap viewMap = gm.getViewMap();
		ViewStatus viewStatus = gm.getViewStatus();
		ViewMessage viewMessage = gm.getViewMessage();
		add(BorderLayout.EAST, viewStatus);
		add(BorderLayout.SOUTH, viewMessage);
		add(BorderLayout.WEST, buttonContainer);
		add(BorderLayout.CENTER, viewMap);
			
		// changes pauseButton label if clicked
		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				if(gm.getIsPaused()) {
					pauseButton.setText("Play");
				}
				else {
					pauseButton.setText("Pause");
				}
			}
		});
		
		// sets changePos to true if clicked
		changePosButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// TODO Auto-generated method stub
				viewMap.setChangePosPressed(true);
			}
		});
			
		show();		
		
		viewMap.init();
	}

	// runs every 20 ms
	@Override
	public void run() {
		// TODO Auto-generated method stub
		gm.nextFrame();
	}
	
	// controls student player
	public void keyPressed(int keyCode) {
		switch(keyCode) {
			case 119:
				studentPlayer.startMove();
				break;
			case 97:
				studentPlayer.left();
				break;
			case 115:
				studentPlayer.stop();
				break;
			case 100:
				studentPlayer.right();
				break;
		}
	}	
}

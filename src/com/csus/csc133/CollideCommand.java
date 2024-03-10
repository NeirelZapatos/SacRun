package com.csus.csc133;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideCommand extends Command{
	private StudentPlayer studentPlayer;
	private GameModel gameModel;

	
	public CollideCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
		this.studentPlayer = this.gameModel.getStudentPlayer();	
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(getCommandName() == "Lecture Hall") {
			LectureHall lectureHall = gameModel.getLectureHall();
			lectureHall.handleCollide(studentPlayer);
			gameModel.setLatestMessage("Player Collide with Lecture Hall");
		}
		if(getCommandName() == "Restroom") {
			Restroom restroom = gameModel.getRestroom();
			restroom.handleCollide(studentPlayer);
			gameModel.setLatestMessage("Player Collide with Restroom");
			
		}
		if(getCommandName() == "Water Dispenser") {
			WaterDispenser waterDispenser = gameModel.getWaterDispenser();
			waterDispenser.handleCollide(studentPlayer);
			gameModel.setLatestMessage("Player Collide with Water Dispenser");
		}
		gameModel.setChanged();
		gameModel.notifyObservers();
	}
}
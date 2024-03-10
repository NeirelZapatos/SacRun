package com.csus.csc133;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideCommand extends Command{
	private GameModel gameModel;

	public CollideCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
	}
	
	public void actionPerformed(ActionEvent evt) {
		switch(getCommandName()) {
			case "Lecture Hall":
				gameModel.lectureHallCollide();
				break;
			case "Restroom":
				gameModel.restroomCollide();
				break;
			case "Water Dispenser":
				gameModel.waterDispenserCollide();
				break;
		}
		
		gameModel.setChanged();
		gameModel.notifyObservers();
	}
}
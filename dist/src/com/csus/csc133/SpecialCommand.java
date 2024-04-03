package com.csus.csc133;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.*;

public class SpecialCommand extends Command{
	private GameModel gameModel;
		
	//constructor
	public SpecialCommand(GameModel gameModel, String commandName){
		super(commandName);
		this.gameModel = gameModel;
	}
		
	//actions when command is called
	public void actionPerformed(ActionEvent evt) {
		switch(getCommandName()) {
			case "Student":
				gameModel.selectStudent();
				break;
			case "Next Frame":
				gameModel.nextFrame();
				break;
			case "About":
				gameModel.about();
				break;
			case "Exit":
				gameModel.exit();
				break;
			case "Change Strategy":
				gameModel.changeStrategy();
				break;
		}
		
		//updates observers
		gameModel.setChanged();
		gameModel.notifyObservers();
	}
}
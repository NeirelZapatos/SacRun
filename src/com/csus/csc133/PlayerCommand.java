package com.csus.csc133;
import com.codename1.ui.*;
import com.codename1.ui.events.*;

public class PlayerCommand extends Command{
	private GameModel gameModel;
	
	public PlayerCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
	}
	
	public void actionPerformed(ActionEvent evt) {
		switch(getCommandName()) {
			case "Move":
				gameModel.startMovePlayer();
				break;
			case "Stop":
				gameModel.stopPlayer();
				break;
			case "Turn Left":
				gameModel.leftPlayer();
				break;
			case "Turn Right":
				gameModel.rightPlayer();
				break;
		}
		gameModel.setChanged();
		gameModel.notifyObservers();
	}
}

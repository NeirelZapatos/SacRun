package com.csus.csc133;
import com.codename1.ui.*;
import com.codename1.ui.events.*;

public class PlayerCommand extends Command{
	private StudentPlayer studentPlayer;
	private GameModel gameModel;
	
	public PlayerCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
		this.studentPlayer = this.gameModel.getStudentPlayer();
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(getCommandName() == "Move") {
			studentPlayer.startMove();
		}
		if(getCommandName() == "Stop") {
			studentPlayer.stop();
		}
		if(getCommandName() == "Turn Left") {
			studentPlayer.left();
		}
		if(getCommandName() == "Turn Right") {
			studentPlayer.right();
		}
	}
}

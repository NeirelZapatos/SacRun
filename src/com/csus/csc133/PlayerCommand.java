package com.csus.csc133;
import com.codename1.ui.*;
import com.codename1.ui.events.*;

public class PlayerCommand extends Command{
	private StudentPlayer studentPlayer;
	private GameModel gameModel;
	private String commandName;
	
	public PlayerCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
		this.studentPlayer = this.gameModel.getStudentPlayer();
		this.commandName = commandName;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(commandName == "Move") {
			studentPlayer.startMove();
		}
		if(commandName == "Stop") {
			studentPlayer.stop();
		}
		if(commandName == "Turn Left") {
			studentPlayer.left();
		}
		if(commandName == "Turn Right") {
			studentPlayer.right();
		}
	}
}

package com.csus.csc133;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CollideCommand extends Command{
	private StudentPlayer studentPlayer;
	private GameObject gameObject;
	private String commandName;
	private GameModel gameModel;

	
	public CollideCommand(GameModel gameModel, String commandName) {
		super(commandName);
		this.gameModel = gameModel;
		this.studentPlayer = this.gameModel.getStudentPlayer();
		this.commandName = commandName;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(commandName == "Lecture Hall") {
			LectureHall lectureHall = gameModel.getLectureHall();
			lectureHall.handleCollide(studentPlayer);
		}
		if(gameObject instanceof Restroom) {
			Restroom restroom = gameModel.getRestroom();
			restroom.handleCollide(studentPlayer);
		}
		if(gameObject instanceof WaterDispenser) {
			WaterDispenser waterDispenser = gameModel.getWaterDispenser();
			waterDispenser.handleCollide(studentPlayer);
		}
	}
}
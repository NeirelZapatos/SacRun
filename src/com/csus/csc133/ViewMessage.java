package com.csus.csc133;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;

public class ViewMessage extends Container implements Observer{
	private GameModel gameModel;
	Label gameState;
	
	//constructor
	public ViewMessage(GameModel gameModel) {
		this.gameModel = gameModel;
		getAllStyles().setBorder(Border.createLineBorder(2, 0x000000));
		gameState = new Label(this.gameModel.getLatestMessage());
		add(gameState);
	}

	//updates message text when observers are notified
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		gameState.setText(gameModel.getLatestMessage());
	}
}

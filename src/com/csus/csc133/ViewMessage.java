package com.csus.csc133;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;

public class ViewMessage extends Container implements Observer{
	public ViewMessage() {
		getAllStyles().setBorder(Border.createLineBorder(2, 0x000000));
		Label gameState = new Label("Testing");
		add(gameState);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}

}

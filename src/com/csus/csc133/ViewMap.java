package com.csus.csc133;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;

public class ViewMap extends Container implements Observer {	
	private GameModel gameModel;
	
	public ViewMap(GameModel gameModel){
		this.gameModel = gameModel;
		getAllStyles().setBorder(Border.createLineBorder(2, 0xff0000));
		setWidth(1000);
		setHeight(800);
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		displayGameState();
	}
	
	public void displayGameState() {
		GameObjectsCollection gameObjects = gameModel.getGameObjectsCollection(); 
		GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
		System.out.println("Time: " + gameModel.getGameTime());
		while(objectIterator.hasNext()) {
			GameObject selectedObject = (GameObject) objectIterator.getNext();
			selectedObject.displayInfo();
		}
		System.out.println();
	}
}
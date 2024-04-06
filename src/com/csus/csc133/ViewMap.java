package com.csus.csc133;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.*;
import com.codename1.ui.plaf.Border;
import java.util.Observable;
import java.util.Observer;


public class ViewMap extends Container implements Observer {	
	private GameModel gameModel;
	
	//constructor
	public ViewMap(GameModel gameModel){
		this.gameModel = gameModel;
		getAllStyles().setBorder(Border.createLineBorder(2, 0xff0000));
		//setWidth(1000);
		//ssetHeight(800);
	}

	//called when observers are notified
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
//		displayGameState();
	}
	
	//displays game state to console
	public void displayGameState() {
		GameObjectsCollection gameObjects = gameModel.getGameObjectsCollection(); 
		IteratorInterface objectIterator = gameObjects.getIterator();
		System.out.println("Time: " + gameModel.getGameTime());
		while(objectIterator.hasNext()) {
			GameObject selectedObject = objectIterator.getNext();
			selectedObject.displayInfo();
		}
		System.out.println();
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		GameObjectsCollection gameObjects = gameModel.getGameObjectsCollection();
		IteratorInterface objectIterator = gameObjects.getIterator();
		while(objectIterator.hasNext()) {
			GameObject currentObject = objectIterator.getNext();
			currentObject.draw(g, getX(), getY());
		}
	}
}
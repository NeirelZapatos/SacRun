package com.csus.csc133;
import java.util.Vector;

public class GameObjectsCollection {
	private Vector<GameObject> gameObjects;
	
	//constructor
	public GameObjectsCollection() {
		this.gameObjects = new Vector<>();
	}
	
	//adds to gameObject
	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	//returns iterator
	public Iterator getIterator() {
		return new Iterator();
	}
	
	//iterator class
	protected class Iterator{
		private int currentIndex = -1;
		
		//checks if gameObjects has another object
		public boolean hasNext() {
			if(gameObjects.size() <= 0) {
				return false;
			}
			if(currentIndex == gameObjects.size() - 1) {
				return false;
			}
			return true;
		}
		
		//gets the next object
		public GameObject getNext() {
			currentIndex++;
			return gameObjects.get(currentIndex);
		}	
	}
}

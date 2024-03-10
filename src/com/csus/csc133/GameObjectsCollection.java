package com.csus.csc133;
import java.util.Vector;

public class GameObjectsCollection {
	private Vector<GameObject> gameObjects;
	
	public GameObjectsCollection() {
		this.gameObjects = new Vector<>();
	}
		
	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
	}
	
	public Iterator getIterator() {
		return new Iterator();
	}
	
	protected class Iterator{
		private int currentIndex = -1;
		
		public boolean hasNext() {
			if(gameObjects.size() <= 0) {
				return false;
			}
			if(currentIndex == gameObjects.size() - 1) {
				return false;
			}
			return true;
		}
		
		public Object getNext() {
			currentIndex++;
			return gameObjects.get(currentIndex);
		}	
	}
}

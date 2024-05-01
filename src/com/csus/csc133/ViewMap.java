package com.csus.csc133;
import com.codename1.ui.*;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Transform.NotInvertibleException;
import com.codename1.ui.plaf.Border;

import java.util.Observable;
import java.util.Observer;

public class ViewMap extends Container implements Observer {	
	private GameModel gameModel;
	private Transform VTM;
	private int screenWidth;
	private int screenHeight;
	private int viewL = 0;
	private int viewB = 0;
	private int viewWidth;
	private int viewHeight;
	private int pPrevDragLocX, pPrevDragLocY;
	private boolean changePosPressed = false;
	
	//constructor
	public ViewMap(GameModel gameModel){
		this.gameModel = gameModel;
		getAllStyles().setBorder(Border.createLineBorder(2, 0xff0000));
		//setWidth(1000);
		//ssetHeight(800);
	}
	
	// init's the VTM
	public void init() {
		viewWidth = screenWidth = getWidth();
		viewHeight = screenHeight = getHeight();
		updateVTM();
	}

	//called when observers are notified
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		repaint();
	}
	
	// updates the VTM
	public void updateVTM() {
		VTM = Transform.makeIdentity();
		Transform W2ND = Transform.makeScale(1.0f / viewWidth, 1.0f / viewHeight);
		W2ND.translate(-viewL, -viewB);
		Transform ND2D = Transform.makeTranslation(0, screenHeight);
		ND2D.scale(screenWidth, -screenHeight);
		VTM.concatenate(ND2D);
		VTM.concatenate(W2ND);
	}
	
	// controls the zoom function
	public void zoom(float factor) {
		float newWidth = viewWidth * factor;
		float newHeight = viewHeight * factor;
		
		if(newWidth < 400 || newHeight < 200 || newWidth > 8192 || newHeight > 4320) {
			return;
		}
			viewL += (viewWidth - newWidth) / 2;
			viewB += (viewHeight - newHeight) / 2;
			viewWidth = (int) newWidth;
			viewHeight = (int) newHeight;
			repaint();
	}
	
	// changes view for horizontal pan
	public void panHorizontal(double delta) {
		viewL += delta;
		repaint();
	}
	
	// changes view for vertical pan
	public void panVertical(double delta) {
		viewB += delta;
		repaint();
	}
	
	// controls pinch gesture
	public boolean pinch(float scale) {
		zoom(scale);
		return true;
	}
	
	// controls press gesture
	public void pointerPressed(int x, int y) {
		pPrevDragLocX = x;
		pPrevDragLocY = y;
	}
	
	// controls drag gesture
	public void pointerDragged(int x, int y) {
		double dx = pPrevDragLocX - x;
		double dy = pPrevDragLocY - y;
		
		dx *= (viewWidth / (float) screenWidth);
		dy *= -(viewHeight / (float) screenHeight);
		
		panHorizontal(dx);
		panVertical(dy);
		
		pPrevDragLocX = x;
		pPrevDragLocY = y;
	}
	
	// controls release gesture
	public void pointerReleased(int x, int y) {
		float[] pt = {x, y};
		int absX = getParent().getAbsoluteX();
		int absY = getParent().getAbsoluteY();
		
		IteratorInterface objectIterator = gameModel.getGameObjectsCollection().getIterator();
		while(objectIterator.hasNext()) {
			GameObject selectedObject = objectIterator.getNext();
			if(changePosPressed) {
				if(selectedObject.getIsSelected()) {
					try {
						float[] ptInv = {0, 0};
						Transform inverse = Transform.makeIdentity();
						VTM.getInverse(inverse);
						inverse.transformPoint(pt, ptInv);
						ptInv[0] =  ptInv[0] - (getX() + getParent().getAbsoluteX());
						ptInv[1] = ptInv[1] - (getY() + getParent().getAbsoluteY());
						selectedObject.getTranslateForm().setTranslation((float) ptInv[0], (float) ptInv[1]);
					}
					catch(NotInvertibleException e) {
						e.getStackTrace();
					}
				}				
			}
			if(selectedObject.contains(pt, absX, absY)) {
				selectedObject.setIsSelected(true);
			}
			else {
				selectedObject.setIsSelected(false);
			}
		}
		
		changePosPressed = false;
		
		repaint();
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
	
	// paints the objects
	public void paint(Graphics g) {
		super.paint(g);
		
		Transform myXForm = Transform.makeIdentity();
		myXForm.translate(getX(), getY());
		
		updateVTM();
		myXForm.concatenate(VTM);
		
		g.setTransform(myXForm);
		
		g.setColor(ColorUtil.rgb(0, 0, 0));
		g.drawRect(0, 0, screenWidth, screenHeight);
		
		GameObjectsCollection gameObjects = gameModel.getGameObjectsCollection();
		IteratorInterface objectIterator = gameObjects.getIterator();
		while(objectIterator.hasNext()) {
			GameObject currentObject = objectIterator.getNext();
			currentObject.draw(g, getParent().getAbsoluteX(), getParent().getAbsoluteY());
		}
		
		g.resetAffine();
	}
	
	// setter method
	public void setChangePosPressed(boolean changePosPressed) {
		this.changePosPressed = changePosPressed;
	}
}
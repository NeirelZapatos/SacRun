package com.csus.csc133;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.*;

public class SpecialCommand extends Command{
	private GameObjectsCollection gameObjects;
	private GameModel gameModel;
		
	public SpecialCommand(GameModel gameModel, String commandName){
		super(commandName);
		this.gameModel = gameModel;
		this.gameObjects = this.gameModel.getGameObjectsCollection();
	}
		
	public void actionPerformed(ActionEvent evt) {
		if(getCommandName() == "Student") {
			Dialog studentDialog = new Dialog("Select Student");
			TextField textFieldInput = new TextField();
			Button getTextButton = new Button("OK");
			
			getTextButton.addActionListener(e -> {
				String textInput = textFieldInput.getText();
				GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
				GameObject selectedGameObject;
				Student selectedStudent;
				
				switch(textInput) {
					case "0":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentAngry) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Angry Student Selected");
								break;
							}
						}
						break;
					case "1":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentBiking) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Biking Student Selected");
								break;
							}
						}
						break;
					case "2":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentCar) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Car Student Selected");
								break;
							}
						}
						break;
					case "3":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentConfused) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Confused Student Selected");
								break;
							}
						}
						break;
					case "4":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentFriendly) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Friendly Student Selected");
								break;
							}
						}
						break;
					case "5":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentHappy) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Happy Student Selected");
								break;
							}
						}
						break;
					case "6":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentNonstop) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Nonstop Student Selected");
								break;
							}
						}
						break;
					case "7":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentSleeping) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Sleeping Student Selected");
								break;
							}
						}
						break;
					case "8":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentRunning) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								System.out.println("Running Student Selected");
								break;
							}
						}
						break;
//					case "9":
//						while(objectIterator.hasNext()) {
//							selectedGameObject = (GameObject) objectIterator.getNext();
//							if(selectedGameObject instanceof StudentStrategy) {
//								selectedStudent = (StudentStrategy) selectedGameObject;
//								studentPlayer.handleCollide(selectedStudent);
//								break;
//							}
//						}
//						break;
					default:
						System.out.println("Incorrect Input");
				}
				System.out.println();
				studentDialog.dispose();	
			});
			
			studentDialog.add(textFieldInput);
			studentDialog.add(getTextButton);
			studentDialog.show();
		}
		
		if(getCommandName() == "Next Frame") {
			gameModel.nextFrame();
		}
		
		if(getCommandName() == "Change Strategy") {
			
		}
		
		if(getCommandName() == "About") {
			Dialog aboutDialog = new Dialog("About");
			Label aboutLabel = new Label("A2, Neirel Zapatos, Spring 2024");
			Button closeButton = new Button("Confirm");
			
			closeButton.addActionListener(e -> {
	            aboutDialog.dispose();
	        });
			
			aboutDialog.add(aboutLabel);
			aboutDialog.add(closeButton);
			
			aboutDialog.show();
			
		}
		
		if(getCommandName() == "Exit") {
			Dialog exitDialog = new Dialog("Do you want to exit?");
			Button yesButton = new Button("Yes");
			Button noButton = new Button("No");
			
			noButton.addActionListener(e -> {
	            exitDialog.dispose();
	        });
			
			yesButton.addActionListener(e -> {
				CN.exitApplication();
	        });
			
			exitDialog.add(yesButton);
			exitDialog.add(noButton);
			exitDialog.show();
		}
	}
}
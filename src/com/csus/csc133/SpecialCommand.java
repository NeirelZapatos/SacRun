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
			Label studentLabel = new Label("0: Angry, 1: Biking, 2: Car, 3: Confused, 4: Friendly, 5: Happy, 6: Nonstop, 7: Sleeping, 8: Running, 9: Strategy ");
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
								gameModel.setLatestMessage("Angry Student Selected");
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
								gameModel.setLatestMessage("Biking Student Selected");
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
								gameModel.setLatestMessage("Car Student Selected");								
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
								gameModel.setLatestMessage("Confused Student Selected");
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
								gameModel.setLatestMessage("Friendly Student Selected");
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
								gameModel.setLatestMessage("Happy Student Selected");
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
								gameModel.setLatestMessage("Nonstop Student Selected");
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
								gameModel.setLatestMessage("Sleeping Student Selected");
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
								gameModel.setLatestMessage("Running Student Selected");
								break;
							}
						}
						break;
					case "9":
						while(objectIterator.hasNext()) {
							selectedGameObject = (GameObject) objectIterator.getNext();
							if(selectedGameObject instanceof StudentStrategy) {
								selectedStudent = (Student) selectedGameObject;
								gameModel.setSelectedStudent(selectedStudent);
								gameModel.setLatestMessage("Strategy Student Selected");
								break;
							}
						}
						break;
					default:						
						gameModel.setLatestMessage("Invalid Input Selected");
				}
				studentDialog.dispose();
				
			});
			
			studentDialog.add(studentLabel);
			studentDialog.add(textFieldInput);
			studentDialog.add(getTextButton);
			studentDialog.show();
		}
		
		if(getCommandName() == "Next Frame") {
			gameModel.nextFrame();
			gameModel.setLatestMessage("Next Frame");
		}
		
		if(getCommandName() == "Change Strategy") {
			GameObjectsCollection.Iterator objectIterator = gameObjects.getIterator();
			GameObject selectedObject;
			StudentStrategy strategyStudent;
			
			while(objectIterator.hasNext()) {
				selectedObject = (GameObject) objectIterator.getNext();
				if(selectedObject instanceof StudentStrategy) {
					strategyStudent = (StudentStrategy) selectedObject;
					strategyStudent.setStrategy();
				}
			}
			gameModel.setLatestMessage("Changed Student Strategy");
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
			
			gameModel.setLatestMessage("Displaying About");
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
		
		gameModel.setChanged();
		gameModel.notifyObservers();
	}
}
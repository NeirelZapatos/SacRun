package com.csus.csc133;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Observable;
import java.util.Observer;

public class ViewStatus extends Container implements Observer{
	private String lectureName;
	private String lectureTime;
	private String gameTime;
	private String absence;
	private String hydration;
	private String waterIntake;
	private String timeRemain;
	
	private Label lectureNameLabel;
	private Label lectureTimeLabel;
	private Label gameTimeLabel;
	private Label absenceLabel;
	private Label hydrationLabel;
	private Label waterIntakeLabel;
	private Label timeRemainLabel;
	
	private GameModel gameModel;

	//constructor which creates the status information in GUI
	public ViewStatus(GameModel gameModel) {
		this.gameModel = gameModel;
		updateValues();
		
		setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		lectureNameLabel = new Label(lectureName);
		lectureTimeLabel = new Label("Lecture Time Remain: " + lectureTime);
		gameTimeLabel = new Label("Game Time: " + gameTime);
		absenceLabel = new Label("Absence: " + absence);
		hydrationLabel = new Label("Hydration: " + hydration);
		waterIntakeLabel = new Label("Water Intake: " + waterIntake);
		timeRemainLabel = new Label("Time Remain: " + timeRemain);

		add(lectureNameLabel);
		add(lectureTimeLabel);
		add(gameTimeLabel);
		add(absenceLabel);
		add(hydrationLabel);
		add(waterIntakeLabel);
		add(timeRemainLabel);
	}

	//updates status values when observers are notified
	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		updateValues();
		
		lectureTimeLabel.setText("Lecture Time Remain: " + lectureTime);
		gameTimeLabel.setText("Game Time: " + gameTime);
		absenceLabel.setText("Absence: " + absence);
		hydrationLabel.setText("Hydration: " + hydration);
		waterIntakeLabel.setText("Water Intake: " + waterIntake);
		timeRemainLabel.setText("Time Remain: " + timeRemain);
		
	}
	
	public void updateValues() {
		lectureName = gameModel.getLectureHall().getName();
		if(gameModel.getLectureHall().getLecture() == null) {
			lectureTime = "No Lecture in Session";
		}
		else {
			lectureTime = String.valueOf(gameModel.getLectureHall().getLecture().getLectureTime());
		}
		gameTime = String.valueOf(gameModel.getGameTime());
		absence = String.valueOf(gameModel.getStudentPlayer().getAbsenceTime());
		hydration = String.valueOf(gameModel.getStudentPlayer().getHydration());
		waterIntake = String.valueOf(gameModel.getStudentPlayer().getWaterIntake());
		timeRemain = String.valueOf(gameModel.getStudentPlayer().getTimeRemain());
	}
}

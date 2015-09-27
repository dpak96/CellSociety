package toolsForGui;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FlowControlBox extends HorizontalControlBoxes{
	
	private HBox myHBox;
	private GUI myGui;
	
	public FlowControlBox(GUI gui, Stage primaryStage, Simulation mySimulation){
		myGui = gui;
		myHBox = new HBox();
		myHBox.setPrefHeight(20);
		myHBox.setSpacing(20.0);
		myHBox.setAlignment(Pos.CENTER);
		
		StartButton save = new StartButton(myGui);
		PauseButton pause = new PauseButton(myGui);
		NextStepButton next = new NextStepButton(gui);
		RestartButton restart = new RestartButton(myGui);
		myHBox.getChildren().add(save.getButton());
		myHBox.getChildren().add(pause.getButton());
		myHBox.getChildren().add(next.getButton());
		myHBox.getChildren().add(restart.getButton());
	}
	
	@Override
	public HBox getBox(){
		return myHBox;
	}

}

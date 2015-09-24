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
		
		StartButton s = new StartButton(myGui);
		PauseButton p = new PauseButton(myGui);
		NextStepButton n = new NextStepButton(gui);
		RestartButton r = new RestartButton(myGui);
		myHBox.getChildren().add(s.getButton());
		myHBox.getChildren().add(p.getButton());
		myHBox.getChildren().add(n.getButton());
		myHBox.getChildren().add(r.getButton());
	}
	
	@Override
	public HBox getBox(){
		return myHBox;
	}

}

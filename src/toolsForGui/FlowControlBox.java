package toolsForGui;

import java.util.ArrayList;

import cellsociety_team05.GUI;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class FlowControlBox extends HorizontalControlBoxes{
	
	private HBox myHBox;
	private ArrayList<GuiButton> myButtons;
	private GUI myGui;
	
	public FlowControlBox(GUI gui, Stage primaryStage){
		myGui = gui;
		myButtons = new ArrayList<GuiButton>();
		myHBox = new HBox();
		myHBox.setPrefHeight(20);
		myHBox.setSpacing(20.0);
		myHBox.setAlignment(Pos.CENTER);
		StartButton s = new StartButton(myGui);
		PauseButton p = new PauseButton(myGui);
		RestartButton r = new RestartButton(myGui);
		NextStepButton n = new NextStepButton(gui);
		myHBox.getChildren().add(s.getButton());
		myHBox.getChildren().add(p.getButton());
		myHBox.getChildren().add(r.getButton());
		myHBox.getChildren().add(n.getButton());
	}
	
	@Override
	public HBox getBox(){
		/*
		for(GuiButton g: myButtons){
			myHBox.getChildren().add(g.getButton());
		}
		*/
		return myHBox;
	}

}

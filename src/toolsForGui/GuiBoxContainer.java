package toolsForGui;

import java.util.ArrayList;
import java.util.List;

import cellsociety_team05.GUI;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GuiBoxContainer{
	
	private GUI myGui;
	private VBox myVBox;
	private ArrayList<HorizontalControlBoxes> myBoxes;
	private Stage myStage;
	
	public GuiBoxContainer(GUI gui, Stage primaryStage){
		myGui = gui;
		createBoxContainer();
		myStage = primaryStage;
	}
	
	private void createBoxContainer(){
		myVBox = new VBox();
		myBoxes = new ArrayList<HorizontalControlBoxes>();
		myVBox.setSpacing(10.0);
		myVBox.setPrefHeight(50);
		FlowControlBox f = new FlowControlBox(myGui, myStage);
		SpeedControlBox s = new SpeedControlBox(5, myStage);
		myBoxes.add(f);
		myBoxes.add(s);
	}

	public VBox getVBox() {
		for(HorizontalControlBoxes box: myBoxes){
			myVBox.getChildren().add(box.getBox());
		}
		myVBox.getChildren().add(new HBox());
		return myVBox;
	}

}

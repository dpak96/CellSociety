package toolsForGui;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 
 * @author emanuele
 *
 */
public class SaveControlBox extends HorizontalControlBoxes {

	private HBox myHBox;
	private GUI myGui;

	public SaveControlBox(GUI gui, Stage primaryStage, Simulation mySimulation) {
		myGui = gui;
		myHBox = new HBox();
		myHBox.setPrefHeight(20);
		myHBox.setSpacing(20.0);
		myHBox.setAlignment(Pos.CENTER);
		ClearButton clearButton = new ClearButton(myGui);
		SaveButton saveButton = new SaveButton(myGui);
		LoadButton loadButton = new LoadButton(myGui);
		myHBox.getChildren().add(saveButton.getButton());
		myHBox.getChildren().add(loadButton.getButton());
		myHBox.getChildren().add(clearButton.getButton());
	}

	@Override
	public HBox getBox() {
		// TODO Auto-generated method stub
		return myHBox;
	}

}

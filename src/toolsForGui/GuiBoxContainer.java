package toolsForGui;

import java.util.ArrayList;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 * @author emanuele
 *
 */
public class GuiBoxContainer{
	
	private GUI myGui;
	private VBox myVBox;
	private ArrayList<HorizontalControlBoxes> myBoxes;
	private Stage myStage;
	private PopulationChartBox p;
	private Simulation mySimulation;
	
	public GuiBoxContainer(GUI gui, Stage primaryStage, Simulation sim){
		myGui = gui;
		createBoxContainer();
		myStage = primaryStage;
		mySimulation = sim;
	}
	
	private void createBoxContainer(){
		myVBox = new VBox();
		myBoxes = new ArrayList<HorizontalControlBoxes>();
		myVBox.setSpacing(10.0);
		myVBox.setPrefHeight(50);
		FlowControlBox f = new FlowControlBox(myGui, myStage, mySimulation);
		SpeedControlBox s = new SpeedControlBox(5, myGui);
		p = new PopulationChartBox(myGui, myStage);
		SaveControlBox save = new SaveControlBox(myGui, myStage, mySimulation);
		myBoxes.add(f);
		myBoxes.add(s);
		myBoxes.add(p);
		myBoxes.add(save);
	}

	public VBox getVBox() {
		myVBox.getChildren().add(new HBox());
		for(HorizontalControlBoxes box: myBoxes){
			myVBox.getChildren().add(box.getBox());
		}
		return myVBox;
	}
	
	public PopulationChartBox getPCB(){
		return p;
	}

}

package cellsociety_team05;

import javafx.scene.layout.GridPane;

public class Simulation {

	//changed it to protected so i could extend it 
	protected Grid myGrid;
	
	public Simulation(Setup mySetup, GridPane gridPane){
		//creates new simulation according to the setup
		myGrid = new Grid(gridPane);
	}
	
	public void start(){
		//initiates the timeline loop
	}
	
	public void changeFlow(){
		//if the timeline is running, pause it
		//if the timeline is paused, resume it 
	}
	
	public void nextStep(){
		
	}

}

package cellsociety_team05;

import javafx.scene.layout.GridPane;

public class SimulationTester extends Simulation{

	public SimulationTester(Setup mySetup, GridPane gridPane) {
		super(mySetup, gridPane);
		myGrid = new Grid(gridPane);
		myGrid.drawGridTester();
		myGrid.drawSquareGrid();
		System.out.println("A");
	}
	
	public void updateState(Cell cell){
		
	}

}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.GridPane;

public class Setup {
	private HashMap<String, Double> parameters; 
	private HashMap<String, Simulation> myPossibleSimulations;
	private Simulation mySimulation;
	private XMLReader xml;
	
	public Setup(String file, GUI gui, GridPane gp){
		GUI myGUI = gui; 
		GridPane myGridPane = gp;
		xml = new XMLReader(file);
		parameters = xml.getParams();

		myPossibleSimulations.put("segregation", new SegregationSimulation());

		//myPossibleSimulations.set("segregation", new SegregationSimulation());


	}
	
	public Grid initGrid(XMLReader xml){
		ArrayList<Cell> cells = xml.getCells();
		Grid myGrid = new Grid(xml.getGridWidth(), xml.getGridHeight(), cells);
		return myGrid;
		//Fill in with information of from XML file
	}
	
	//Need to find a way to change which simulation is being run w/o using too many if statements
	public void initSimulation(Grid grid){

		mySimulation = new SegregationSimulation(this);


		//Simulation mySimulation = new SegregationSimulation(this);

	}
	
	public void reset(String file){
		//Grid grid = initGrid();
		//initSimulation(grid);
	}
	
	public Simulation getSimulation(){
		return mySimulation; 
	}
}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;

public class Setup {
	private HashMap<String, Double> parameters; 
	
	private HashMap<String, Simulation> myPossibleSimulations;
	
	public Setup(String file){
		XMLReader xml = new XMLReader(file);
		parameters = xml.getParams();
		myPossibleSimulations.set("segregation", new SegregationSimulation());

	}
	
	public Grid initGrid(XMLReader xml){
		ArrayList<Cell> cells = xml.getCells();
		Grid myGrid = new Grid(xml.getGridWidth(), xml.getGridHeight(), cells);
		return myGrid;
		//Fill in with information of from XML file
	}
	
	//Need to find a way to change which simulation is being run w/o using too many if statements
	public void initSimulation(Grid grid){
		Simulation mySimulation = new SegregationSimulation(this);
	}
	
	public void reset(String file){
		//Grid grid = initGrid();
		//initSimulation(grid);
	}
}

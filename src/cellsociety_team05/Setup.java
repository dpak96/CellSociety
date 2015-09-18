package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.GridPane;

public class Setup {
	private HashMap<String, Double> parameters; 
	private HashMap<String, Simulation> myPossibleSimulations;
	private Simulation mySimulation;
	private XMLReader xml;
	private GUI myGUI;
	private GridPane myGridPane;
	private Grid myGrid;
	
	public Setup(String file, GUI gui, GridPane gp){
		myGUI = gui; 
		myGridPane = gp;
		xml = new XMLReader(file);
		parameters = xml.getParams();

		myPossibleSimulations.put("segregation", new SegregationSimulation());

		//myPossibleSimulations.set("segregation", new SegregationSimulation());


	}
	
	public Grid initGrid(){
		ArrayList<Cell> cells = xml.getCells();
		Grid myGrid = new Grid(xml.getGridWidth(), xml.getGridHeight(), cells, initSimulation());
		return myGrid;
		//Fill in with information of from XML file
	}
	
	//Need to find a way to change which simulation is being run w/o using too many if statements
	public Simulation initSimulation(){
		
		mySimulation = myPossibleSimulations.get(xml.getSimulation());
		mySimulation.setSimulation(myGridPane, myGUI, parameters);
		return mySimulation;
		//Simulation mySimulation = new SegregationSimulation(this);

	}
	
	public void reset(String file){
		//Grid grid = initGrid();
		//initSimulation(grid);
	}
	
	public Simulation getSimulation(){
		return mySimulation; 
	}
	
	public void startFireSimulation(){
	    HashMap<String, Double> map = new HashMap<String, Double>();
            map.put("probCatch", 0.5);
	    mySimulation = new FireSimulation(myGridPane,this,map);
	    myGrid = new Grid(4,4,mySimulation);
	    myGrid.linkGridPane(myGridPane);
	    mySimulation.start();
	}
	
	public void startGameOfLifeSimulation(){
	    mySimulation = new GameOfLifeSimulation(myGridPane,this,null);
	    myGrid = new Grid(4,4,mySimulation);
	    myGrid.linkGridPane(myGridPane);
	    mySimulation.start();
	}
	
	public void startSegregationSimulation(){
	    System.out.println("Start");
            HashMap<String, Double> map = new HashMap<String, Double>();
            map.put("similar", 0.3);
            map.put("ratio", 0.5);
            map.put("empty", 0.2);
            mySimulation = new SegregationSimulation(myGridPane,this,map);
            myGrid = new Grid(4,4,mySimulation);
            mySimulation.initGrid(myGrid);
            myGrid.linkGridPane(myGridPane);
            mySimulation.start();
	}
}

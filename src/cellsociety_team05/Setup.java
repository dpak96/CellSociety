package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.scene.layout.GridPane;

public class Setup {
	private HashMap<String, Double> parameters;
	private Simulation mySimulation;
	private XMLReader xml;
	private GUI myGUI;
	private GridPane myGridPane;
	private Grid myGrid;
	private int myHeight, myWidth;
	
	public Setup(String file, GUI gui, GridPane gp){
		myGUI = gui; 
		myGridPane = gp;
		xml = new XMLReader(file);
		parameters = xml.getParams();
		myHeight = xml.getGridHeight();
                myWidth = xml.getGridWidth();
		SimulationFactory sf = new SimulationFactory(myGridPane,myGUI,parameters,myHeight,myWidth);
		mySimulation = sf.makeSimulation(xml.getSimulation());
	}
	
	public Grid initGrid(){
		ArrayList<Cell> cells = xml.getCells();
		Grid myGrid = new Grid(myWidth, myHeight, cells, initSimulation());
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
	
        public void startPredatorPreySimulation(){
            HashMap<String, Double> map = new HashMap<String, Double>();
            map.put("preyreproductiontime", 5.0);
            map.put("predatorreproductiontime", 5.0);
            map.put("energy", 5.0);
            mySimulation = new PredatorPreySimulation(myGridPane,myGUI,map,myHeight,myWidth);
            myGrid = new Grid(4,4,mySimulation);
            myGrid.linkGridPane(myGridPane);
            mySimulation.start();
        }
	
	public void startFireSimulation(){
	    HashMap<String, Double> map = new HashMap<String, Double>();
            map.put("probCatch", 0.5);
	    mySimulation = new FireSimulation(myGridPane,myGUI,map,myHeight,myWidth);
	    myGrid = new Grid(4,4,mySimulation);
	    myGrid.linkGridPane(myGridPane);
	    mySimulation.start();
	}
	
	public void startGameOfLifeSimulation(){
	    mySimulation = new GameOfLifeSimulation(myGridPane,myGUI,null,myHeight,myWidth);
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
            mySimulation = new SegregationSimulation(myGridPane,myGUI,map,myHeight,myWidth);
            myGrid = new Grid(4,4,mySimulation);
            mySimulation.initGrid(myGrid);
            myGrid.linkGridPane(myGridPane);
            mySimulation.start();
	}
}

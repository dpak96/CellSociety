package cellsociety_team05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class SimulationFactory {
	private GridPane myGridPane;
	private GUI myGUI;
	private Map<String, Double> myMap;
	private int myHeight;
	private int myWidth;
	private List<CellInfo> myList;

	/**
	 * constructor for simulation factory
	 * 
	 * @param grid
	 * @param gui
	 * @param map
	 * @param list
	 * @param height
	 * @param width
	 */
	public SimulationFactory (GridPane grid,
			GUI gui,
			Map<String, Double> map,
			List<CellInfo> list,
			int height,
			int width) {
		myGridPane = grid;
		myGUI = gui;
		myMap = map;
		myHeight = height;
		myWidth = width;
		myList = list;
	}

	/**
	 * converts string for name of simulation into string for name of xml file to initialize given
	 * simulation
	 * 
	 * @param simulation
	 * @return
	 * @throws SimulationException 
	 */
	public String chooseFileFromUserInput (String simulation) throws SimulationException {
		if (simulation.equals("Segregation")) {
			return "XMLFiles/test.xml";
		}
		else if (simulation.equals("Fire")) {
			return "XMLFiles/fire_test2.xml";
		}
		else if (simulation.equals("PredatorPrey")) {
			return "XMLFiles/prey_test2.xml";
		}
		else if (simulation.equals("GameOfLife")) {
			return "XMLFiles/life_test.xml";
		}
		else if (simulation.equals("Sugar")){
			return "XMLFiles/sugar_test.xml";
		}
		else if (simulation.equals("AntForaging")){
		        return "XMLFiles/ant_test.xml";
		}
		else if (simulation.equals("Custom")){
			return "XMLFiles/custom.xml";
		}
		else{
			throw(new SimulationException("No such simulation found."));
		}
	}

	/**
	 * initializes simulation specified by input string
	 * 
	 * @param simulation
	 * @return
	 * @throws SimulationException 
	 */
	public Simulation makeSimulation (String simulation) throws SimulationException {
		if (simulation.equals("Segregation")) {
			return new SegregationSimulation(myGridPane, myGUI, myMap, myList, myHeight, myWidth, "rectangle");
		}
		else if (simulation.equals("Fire")) {
			return new FireSimulation(myGridPane, myGUI, myMap, myList, myHeight, myWidth, "rectangle");
		}
		else if (simulation.equals("PredatorPrey")) {
			return new PredatorPreySimulation(myGridPane, myGUI, myMap, myList, myHeight, myWidth,"rectangle");
		}
		else if (simulation.equals("GameOfLife")) {
			return new GameOfLifeSimulation(myGridPane, myGUI, myMap, myList, myHeight, myWidth, "rectangle");
		}
		else if (simulation.equals("Sugar")){
			return new SugarSimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth, "rectangle");
		}
		else if (simulation.equals("AntForaging")){
		        return new AntForagingSimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth, "rectangle");
		}
		else{
			throw(new SimulationException("No such simulation found."));
		}
	}

}

package cellsociety_team05;

import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SimulationFactory {
    private GridPane myGridPane;
    private GUI myGUI;
    private HashMap<String, Double> myMap;
    private int myHeight;
    private int myWidth;
    private List<CellInfo> myList;
    

    public SimulationFactory (GridPane grid, GUI gui, HashMap<String, Double> map,List<CellInfo> list, int height, int width) {
        myGridPane = grid;
        myGUI = gui;
        myMap = map;
        myHeight = height;
        myWidth = width;
        myList = list;
    }
    
    public String chooseFileFromUserInput(String simulation){
        if (simulation.equals("Segregation")){
            return "XMLFiles/test.xml";
        }
        else if (simulation.equals("Fire")){
            return "XMLFiles/fire_test.xml";
        }
        else if (simulation.equals("PredatorPrey")){
            return "XMLFiles/prey_test.xml";
        }
        else if (simulation.equals("GameOfLife")){
            return "XMLFiles/life_test.xml";
        }
        else return null;
    }
    
    public Simulation makeSimulation(String simulation){
        if (simulation.equals("Segregation")){
            return new SegregationSimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth);
        }
        else if (simulation.equals("Fire")){
            return new FireSimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth);
        }
        else if (simulation.equals("PredatorPrey")){
            return new PredatorPreySimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth);
        }
        else if (simulation.equals("GameOfLife")){
            return new GameOfLifeSimulation(myGridPane,myGUI,myMap,myList,myHeight,myWidth);
        }
        else return null;
    }

}

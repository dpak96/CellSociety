package cellsociety_team05;

import java.util.HashMap;
import javafx.scene.layout.GridPane;

public class SimulationFactory {
    private GridPane myGridPane;
    private GUI myGUI;
    private HashMap<String, Double> myMap;
    private int myHeight;
    private int myWidth;
    
    

    public SimulationFactory (GridPane grid, GUI gui, HashMap<String, Double> map, int height, int width) {
        myGridPane = grid;
        myGUI = gui;
        myMap = map;
        myHeight = height;
        myWidth = width;
    }
    
    public Simulation makeSimulation(String simulation){
        if (simulation.equals("Segregation")){
            return new SegregationSimulation(myGridPane,myGUI,myMap,myHeight,myWidth);
        }
        else if (simulation.equals("Fire")){
            return new FireSimulation(myGridPane,myGUI,myMap,myHeight,myWidth);
        }
        else if (simulation.equals("PredatorPrey")){
            return new PredatorPreySimulation(myGridPane,myGUI,myMap,myHeight,myWidth);
        }
        else if (simulation.equals("GameOfLife")){
            return new GameOfLifeSimulation(myGridPane,myGUI,myMap,myHeight,myWidth);
        }
        else return null;
    }

}

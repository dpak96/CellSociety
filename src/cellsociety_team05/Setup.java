package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class Setup {
    private Map<String, Double> parameters;
    private Simulation mySimulation;
    private XMLReader xml;
    private GUI myGUI;
    private GridPane myGridPane;
    private Grid myGrid;
    private int myHeight, myWidth;
    private List<CellInfo> myCells;

    public Setup (String simulation, GUI gui, GridPane gp) throws SimulationException {
        myGUI = gui;
        myGridPane = gp;
        SimulationFactory sf =
                new SimulationFactory(myGridPane, myGUI, parameters, myCells, myHeight, myWidth);
        String file = sf.chooseFileFromUserInput(simulation);
        xml = new XMLReader(file);
        xml.readFile();
        parameters = xml.getParams();
        myHeight = xml.getGridHeight();
        myWidth = xml.getGridWidth();
        myCells = xml.getCells();
        sf = new SimulationFactory(myGridPane, myGUI, parameters, myCells, myHeight, myWidth);
        System.out.println("hi"+xml.getSimulationName());
        mySimulation = sf.makeSimulation(xml.getSimulationName());
    }
   

    public Simulation getSimulation () {
        return mySimulation;
    }

}

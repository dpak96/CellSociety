package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SugarSimulation extends Simulation {
    int[][] myAgentSugars;
    int[][] mySugars;
    int[][] mySugarLimits;
    int[][] myVisions;
    int[][] myMetabolisms;
    int[][] myIntervals;
    int[][] myTimes;
    int myInterval;
    int myRate;

    public SugarSimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params,
                            List<CellInfo> list, int height, int width) {
        super(gridPane, gui, params, list, height, width);
        myAgentSugars = new int[width][height];
        mySugars = new int[width][height];
        mySugarLimits = new int[width][height];
        myVisions = new int[width][height];
        myMetabolisms = new int[width][height];
        myIntervals = new int[width][height];
        myTimes = new int[width][height];
    }

    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height, HashMap<String, Double> map) {
        return setUpRandomCells(grid,width,height,map,3);
    }

    @Override
    public Cell makeCell (int x, int y, int start, Grid g, HashMap<String, Double> map) {
        myGrid = g;
        SugarCell c = new SugarCell(x,y,start,g,this);
        return c;
    }

}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class PredatorPreySimulation extends Simulation {
    int PREY_REPRODUCTION_TIME;
    int PREDATOR_REPRODUCTION_TIME;
    int PREDATOR_ENERGY;
    int[][] myReproductionTimes;
    int[][] myEnergies;

    /**
     * constructor for predator prey simulation
     * @param gridPane
     * @param gui
     * @param params
     * @param list
     * @param height
     * @param width
     */
    public PredatorPreySimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params, List<CellInfo> list, int height, int width) {
        super(gridPane, gui, params,list, height, width);
        myReproductionTimes = new int[width][height];
        myEnergies = new int[width][height];
        PREY_REPRODUCTION_TIME = (int) Math.round(params.get("preyreproductiontime"));
        PREDATOR_REPRODUCTION_TIME = (int) Math.round(params.get("predatorreproductiontime"));
        PREDATOR_ENERGY = (int) Math.round(params.get("energylimit"));
    }
    
    /**
     * overrides super class method
     */
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, HashMap<String, Double> map) {
        myGrid = g;
        HashMap<String, Double> params = new HashMap<String, Double>();
        params.put("preyreproductiontime", map.get("preyreproductiontime"));
        params.put("predatorreproductiontime", map.get("predatorreproductiontime"));
        params.put("energylimit", map.get("energylimit"));
        PredatorPreyCell c = new PredatorPreyCell(x,y,start,params,myGrid,this);
        return c;
    }

    /**
     * overrides super class method
     */
    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height,HashMap<String, Double> map) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*3);
                Cell newcell = makeCell(i, j, state, grid,map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }
}

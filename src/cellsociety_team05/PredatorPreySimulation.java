package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class PredatorPreySimulation extends Simulation {
    private int PREY_REPRODUCTION_TIME;
    private int PREDATOR_REPRODUCTION_TIME;
    private int PREDATOR_ENERGY;
    private int[][] myReproductionTimes;
    private int[][] myEnergies;

    /**
     * constructor for predator prey simulation
     * 
     * @param gridPane
     * @param gui
     * @param params
     * @param list
     * @param height
     * @param width
     */
    public PredatorPreySimulation (GridPane gridPane,
                                   GUI gui,
                                   Map<String, Double> params,
                                   List<CellInfo> list,
                                   int height,
                                   int width) {
        super(gridPane, gui, params, list, height, width);
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
    public Cell makeCell (int x, int y, int start, Grid g, Map<String, Double> map) {
        myGrid = g;
        Map<String, Double> params = new HashMap<String, Double>();
        params.put("preyreproductiontime", map.get("preyreproductiontime"));
        params.put("predatorreproductiontime", map.get("predatorreproductiontime"));
        params.put("energylimit", map.get("energylimit"));
        PredatorPreyCell c = new PredatorPreyCell(x, y, start, params, myGrid, this);
        return c;
    }

    /**
     * overrides super class method
     */
    @Override
    public List<List<Cell>> setUpCells (Grid grid, int width, int height, Map<String, Double> map) {
        List<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i = 0; i < width; i++) {
            list.add(new ArrayList<Cell>());
            for (int j = 0; j < height; j++) {
                int state = (int) Math.floor(Math.random() * 3);
                Cell newcell = makeCell(i, j, state, grid, map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

    /**
     * @return the prey reproduction time
     */
    public int getPreyReproductionTime () {
        return PREY_REPRODUCTION_TIME;
    }

    /**
     * @param pREY_REPRODUCTION_TIME the pREY_REPRODUCTION_TIME to set
     */
    public void setPreyReproductionTime (int time) {
        PREY_REPRODUCTION_TIME = time;
    }

    /**
     * @return the pREDATOR_REPRODUCTION_TIME
     */
    public int getPredatorReproductionTime () {
        return PREDATOR_REPRODUCTION_TIME;
    }

    /**
     * @param pREDATOR_REPRODUCTION_TIME the pREDATOR_REPRODUCTION_TIME to set
     */
    public void setPredatorReproductionTime (int time) {
        PREDATOR_REPRODUCTION_TIME = time;
    }

    /**
     * @return the pREDATOR_ENERGY
     */
    public int getPredatorEnergy () {
        return PREDATOR_ENERGY;
    }

    /**
     * @param pREDATOR_ENERGY the pREDATOR_ENERGY to set
     */
    public void setPredatorEnergy (int energy) {
        PREDATOR_ENERGY = energy;
    }

    /**
     * @return the myReproductionTimes
     */
    public int[][] getMyReproductionTimes () {
        return myReproductionTimes;
    }

    /**
     * @param myReproductionTimes the myReproductionTimes to set
     */
    public void setMyReproductionTimes (int[][] myReproductionTimes) {
        this.myReproductionTimes = myReproductionTimes;
    }

    /**
     * @return the myEnergies
     */
    public int[][] getMyEnergies () {
        return myEnergies;
    }

    /**
     * @param myEnergies the myEnergies to set
     */
    public void setMyEnergies (int[][] myEnergies) {
        this.myEnergies = myEnergies;
    }
}

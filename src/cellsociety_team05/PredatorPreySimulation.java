package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class PredatorPreySimulation extends Simulation {
    private int PREY_REPRODUCTION_TIME;
    private int PREDATOR_REPRODUCTION_TIME;
    private int PREDATOR_ENERGY;
    private int[][] myReproductionTimes;
    private int[][] myEnergies;

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

    /**
     * @return the pREY_REPRODUCTION_TIME
     */
    public int getPREY_REPRODUCTION_TIME () {
        return PREY_REPRODUCTION_TIME;
    }

    /**
     * @param pREY_REPRODUCTION_TIME the pREY_REPRODUCTION_TIME to set
     */
    public void setPREY_REPRODUCTION_TIME (int pREY_REPRODUCTION_TIME) {
        PREY_REPRODUCTION_TIME = pREY_REPRODUCTION_TIME;
    }

    /**
     * @return the pREDATOR_REPRODUCTION_TIME
     */
    public int getPREDATOR_REPRODUCTION_TIME () {
        return PREDATOR_REPRODUCTION_TIME;
    }

    /**
     * @param pREDATOR_REPRODUCTION_TIME the pREDATOR_REPRODUCTION_TIME to set
     */
    public void setPREDATOR_REPRODUCTION_TIME (int pREDATOR_REPRODUCTION_TIME) {
        PREDATOR_REPRODUCTION_TIME = pREDATOR_REPRODUCTION_TIME;
    }

    /**
     * @return the pREDATOR_ENERGY
     */
    public int getPREDATOR_ENERGY () {
        return PREDATOR_ENERGY;
    }

    /**
     * @param pREDATOR_ENERGY the pREDATOR_ENERGY to set
     */
    public void setPREDATOR_ENERGY (int pREDATOR_ENERGY) {
        PREDATOR_ENERGY = pREDATOR_ENERGY;
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

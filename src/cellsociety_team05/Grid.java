package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public abstract class Grid {
    List<List<Cell>> myCells;
    Simulation mySimulation;
    
    /**
     * constructor for grid class
     * @param width
     * @param height
     * @param sim
     * @param map
     */
    public Grid(int width, int height, Simulation sim,HashMap<String, Double> map){
        mySimulation = sim;
        myCells = mySimulation.setUpCells(this, width, height,map);
        initNeighbors();
    }
    
    /**
     * initializes neighbors of cells in grid
     */
    protected abstract void initNeighbors();
    
    /**
     * Sets the next state of each cell in the grid.
     */
    public void preUpdateGrid(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                cell.preUpdateCell();
            }
        }
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                cell.myDirty = false;
            }
        }
    }
    
    /**
     * prints type of cells FOR DEBUGGING
     */
    public void printType(){
        System.out.println(myCells.get(0).get(0).getClass().toString());
    }
    
    /**
     * Switches the state of each cell in the grid to it's next state.
     */
    public void updateGrid(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                cell.updateCell();
            }
        }
    }
    
    /**
     * 
     * @return A list of the cells in the grid.
     */
    public List<List<Cell>> getCellMatrix(){
        return myCells;
    }
    
    public int getNumberOfStates(){
        return myCells.get(0).get(0).getMyColors().length;
    }
    
    public int[] getStats(){
        int[] stats = new int[3];
        for (int i=0;i<3;i++){
            int numberOfCells = 0;
            for (List<Cell> list:myCells){
                for (Cell cell:list){
                    if (cell.getMyCurrentState()==i){
                        numberOfCells+=1;
                    }
                }
            }
            stats[i]=numberOfCells;
        }
        return stats;
    }
}
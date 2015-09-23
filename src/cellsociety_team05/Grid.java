package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class Grid {
    
    List<List<Cell>> myCells;
    Simulation mySimulation;
    
    public Grid(int width, int height, Simulation sim,HashMap<String, Double> map){
        mySimulation = sim;
        myCells = mySimulation.setUpCells(this, width, height,map);
        //mySimulation.readCellList(list);
        initNeighbors();
    }
    /*
    public void linkGridPane(GridPane gp){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                gp.getChildren().add(cell.getSquare());
            }
        }
    }*/
    
    private void initNeighbors(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                cell.myParameters = mySimulation.myParameters;
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = {0,0,1,1,1,-1,-1,-1};
                int[] y = {1,-1,0,1,-1,0,1,-1};
                for (int i=0;i<x.length;i++){
                    int xCoordinate = cell.getX()+x[i];
                    int yCoordinate = cell.getY()+y[i];
                    if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myCells.size() && yCoordinate<myCells.get(0).size()){
                        neighbors.add(myCells.get(xCoordinate).get(yCoordinate));
                       
                    }
                }
                cell.setNeighbors(neighbors);
            }
        }
    }
    
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
                cell.hasRun = false;
            }
        }
    }
    
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

}
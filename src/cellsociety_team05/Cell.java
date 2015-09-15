package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

import javafx.scene.paint.Color;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class Cell {
    // Default Cell class could implement game of life for testing?
    protected List<Cell> myNeighbors;
    protected int myXCoordinate;
    protected int myYCoordinate;
    protected int myCurrentState;
    protected int myNextState;
    protected Grid myGrid;
    private final String[] myPossibleStates = {"Alive","Dead"};
    //alive = 0, dead = 1 (based on index)
    private final Color[] myColors = {Color.BLACK, Color.WHITE};
    //Simulation mySimulation;
    
    /**
     * Cell constructor
     * @param grid
     * @param xCoordinate
     * @param yCoordinate
     * @param startingState
     */
    public Cell(Grid grid, int xCoordinate, int yCoordinate,
                     int startingState/*, Simulation simulation*/){
        myGrid = grid;
        myXCoordinate = xCoordinate;
        myYCoordinate = yCoordinate;
        myCurrentState = startingState;
        //mySimulation = simulation;
    }
    
    public void initNeighbors(){
        myNeighbors = new ArrayList<Cell>();
        for(Cell cell: myGrid.myCells){
            if (cell.myXCoordinate==myXCoordinate-1 && cell.myYCoordinate==myYCoordinate-1 ||
                    cell.myXCoordinate==myXCoordinate-1 && cell.myYCoordinate==myYCoordinate ||
                    cell.myXCoordinate==myXCoordinate-1 && cell.myYCoordinate==myYCoordinate+1 ||
                    cell.myXCoordinate==myXCoordinate && cell.myYCoordinate==myYCoordinate-1 ||
                    cell.myXCoordinate==myXCoordinate && cell.myYCoordinate==myYCoordinate+1 ||
                    cell.myXCoordinate==myXCoordinate+1 && cell.myYCoordinate==myYCoordinate-1 ||
                    cell.myXCoordinate==myXCoordinate+1 && cell.myYCoordinate==myYCoordinate ||
                    cell.myXCoordinate==myXCoordinate+1 && cell.myYCoordinate==myYCoordinate+1){
                myNeighbors.add(cell);
            }
        }
    }
    
    /**
     * Gets this cells neighbors.
     * @return A list of this cell's neighbors
     */
    public List<Cell> getNeighbors(){
        return myNeighbors;
    }
    
    /**
     * Gets this cell's current state.
     * @return An integer representing this cell's current state
     */
    public int getCurrentState(){
        return myCurrentState;
    }
    
    public Color getCurrentColor(){
        return myColors[myCurrentState];
    }
    
    /**
     * Sets this cell's next state. 
     * @param state An integer representing the state this cell should be next
     */
    public void setNextState(int state) {
        myNextState = state;
    }
     /**
      * Determines this cell's next state based on rules of simulation.
      */
    public void preUpdateCell(){
        int liveNeighbors = 0;
        for (Cell neighbor: myNeighbors) {
            if (neighbor.myCurrentState==0){
                liveNeighbors+=1;
            }
        }
        myNextState = myCurrentState;
        if (myCurrentState==0){
            if (liveNeighbors<2){
                myNextState=1;
            }
            else if(liveNeighbors>3) {
                myNextState=1;
            }
        }
        else if (myCurrentState==1){
            if(liveNeighbors==3){
                myNextState=0;
            }
        }
    }
    
    /**
     * Switches this cell's state from it's current to it's next state.
     */
    public void updateCell(){
        myCurrentState = myNextState;
    }
 
    
    /**
     * returns the x coordinate of the cell
     */
    public int getX(){
    	return myXCoordinate;
    }
    
    /**
     * returns the y coordinate of the cell 
     */
    public int getY(){
    	return myYCoordinate;
    }
}	

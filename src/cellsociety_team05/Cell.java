package cellsociety_team05;

import java.util.List;

public class Cell {
    protected List<Cell> myNeighbors;
    protected int myXCoordinate;
    protected int myYCoordinate;
    protected int myCurrentState;
    protected int myNextState;
    protected Grid myGrid;
    protected String[] myPossibleStates;
    //Simulation mySimulation;
    
    public void Cell(Grid grid, int xCoordinate, int yCoordinate,
                     int startingState/*, Simulation simulation*/){
        myGrid = grid;
        myXCoordinate = xCoordinate;
        myYCoordinate = yCoordinate;
        myCurrentState = startingState;
        //mySimulation = simulation;
    }
    
    public List<Cell> getNeighbors(){
        return myNeighbors;
    }
    
    public int getCurrentState(){
        return myCurrentState;
    }
    
    public void preUpdateCell(){
        //mySimulation.preUpdateCell(this);
    }
    
    public void updateCell(){
        myCurrentState = myNextState;
    }
}

package cellsociety_team05;

import java.util.List;

public class Cell {
    List<Cell> myNeighbors;
    int myXCoordinate;
    int myYCoordinate;
    int myCurrentState;
    int myNextState;
    Grid myGrid;
    
    public void Cell(Grid grid, int xCoordinate, int yCoordinate, int startingState){
        myGrid = grid;
        myXCoordinate = xCoordinate;
        myYCoordinate = yCoordinate;
        myCurrentState = startingState;
    }
    
    public List<Cell> getNeighbors(){
        
    }
    
    public int getCurrentState(){
        return myCurrentState;
    }
    
    public void preUpdateCell(){
        
    }
    
    public void updateCell(){
        
    }
}

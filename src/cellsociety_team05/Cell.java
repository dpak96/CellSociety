package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public abstract class Cell {
    // Default Cell class could implement game of life for testing?
    protected List<Cell> myNeighbors;
    protected int myXCoordinate;
    protected int myYCoordinate;
    protected int myCurrentState;
    protected int myNextState;
    protected String[] myPossibleStates;
    protected Color[] myColors;
    protected boolean myDirty;
    //Simulation mySimulation;
    protected Rectangle mySquare;
    
    /**
     * Cell constructor
     * @param grid
     * @param xCoordinate
     * @param yCoordinate
     * @param startingState
     */
    public Cell(int xCoordinate, int yCoordinate, int startingState){
        //myGrid = grid;
        myXCoordinate = xCoordinate;
        myYCoordinate = yCoordinate;
        myCurrentState = startingState;
        //mySimulation = simulation;
        //this works only for an 8x8 simulation
    }
    
    public Rectangle getSquare(){
        return mySquare;
    }
    
    /**
     * Gets this cells neighbors.
     * @return A list of this cell's neighbors
     */
    public List<Cell> getNeighbors(){
        return myNeighbors;
    }
    
    public void setNeighbors(List<Cell> list){
        myNeighbors = list;
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
    
    public void setCurrentState(int state) {
        myCurrentState = state;
    }
    
    
     /**
      * Determines this cell's next state based on rules of simulation.
      */
    public abstract void preUpdateCell();
    
    /**
     * Switches this cell's state from it's current to it's next state.
     */
    public void updateCell(){
        myCurrentState = myNextState;
        mySquare.setFill(myColors[myCurrentState]);
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

    public String[] getPossibleStates () {
        return myPossibleStates;
    }
}   
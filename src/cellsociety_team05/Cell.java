package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public abstract class Cell {

    protected List<Cell> myNeighbors;
    protected int myXCoordinate;
    protected int myYCoordinate;
    protected int myCurrentState;
    protected int myNextState;
    protected String[] myPossibleStates;
    protected Color[] myColors;
    protected boolean myDirty;
    protected Rectangle mySquare;
    protected HashMap<String, Double> myParameters;
    
    /**
     * Cell constructor
     * @param grid
     * @param xCoordinate
     * @param yCoordinate
     * @param startingState
     */
    public Cell(int xCoordinate, int yCoordinate, int startingState){
        myXCoordinate = xCoordinate;
        myYCoordinate = yCoordinate;
        myCurrentState = startingState;
        myNextState = startingState;
        myDirty = false;
    }
    
    /**
     * sets value in parameter map
     * @param name string of key in parameter map
     * @param value to be stored at name
     */
    public void setParameter(String name, double value){
        myParameters.put(name, value);
    }
    
    /**
     * 
     * @return current color of this cell
     */
    public Color getCurrentColor(){
        return myColors[myCurrentState];
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
     * @return the x coordinate of the cell
     */
    public int getX(){
        return myXCoordinate;
    }
    
    /**
     * @return the y coordinate of the cell 
     */
    public int getY(){
        return myYCoordinate;
    }

    /**
     * 
     * @return array of cell's possible state names 
     */
    public String[] getPossibleStates () {
        return myPossibleStates;
    }

    /**
     * @return the myNeighbors
     */
    public List<Cell> getMyNeighbors () {
        return myNeighbors;
    }

    /**
     * @param myNeighbors the myNeighbors to set
     */
    public void setMyNeighbors (List<Cell> myNeighbors) {
        this.myNeighbors = myNeighbors;
    }

    /**
     * @param myXCoordinate the myXCoordinate to set
     */
    public void setMyXCoordinate (int myXCoordinate) {
        this.myXCoordinate = myXCoordinate;
    }

    /**
     * @param myYCoordinate the myYCoordinate to set
     */
    public void setMyYCoordinate (int myYCoordinate) {
        this.myYCoordinate = myYCoordinate;
    }

    /**
     * @return the myCurrentState
     */
    public int getMyCurrentState () {
        return myCurrentState;
    }

    /**
     * @param myCurrentState the myCurrentState to set
     */
    public void setMyCurrentState (int myCurrentState) {
        this.myCurrentState = myCurrentState;
    }

    /**
     * @return the myNextState
     */
    public int getMyNextState () {
        return myNextState;
    }

    /**
     * @param myNextState the myNextState to set
     */
    public void setMyNextState (int myNextState) {
        this.myNextState = myNextState;
    }

    /**
     * @return the myPossibleStates
     */
    public String[] getMyPossibleStates () {
        return myPossibleStates;
    }

    /**
     * @param myPossibleStates the myPossibleStates to set
     */
    public void setMyPossibleStates (String[] myPossibleStates) {
        this.myPossibleStates = myPossibleStates;
    }

    /**
     * @return the myColors
     */
    public Color[] getMyColors () {
        return myColors;
    }

    /**
     * @param myColors the myColors to set
     */
    public void setMyColors (Color[] myColors) {
        this.myColors = myColors;
    }

    /**
     * @return the myDirty
     */
    public boolean isMyDirty () {
        return myDirty;
    }

    /**
     * @param myDirty the myDirty to set
     */
    public void setMyDirty (boolean myDirty) {
        this.myDirty = myDirty;
    }

    /**
     * @return the mySquare
     */
    public Rectangle getMySquare () {
        return mySquare;
    }

    /**
     * @param mySquare the mySquare to set
     * 
     * @author emanuele
     * I modified setMySquare, as well as adding another method to allow for update of the cell status 
     */
    public void setMySquare (Rectangle mySquare) {
        this.mySquare = mySquare;
        mySquare.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> nextState());
    }
    
    private void nextState(){
    	myNextState = (myCurrentState + 1)%myColors.length;
    	updateCell();
    }

    /**
     * @return the myParameters
     */
    public Map<String, Double> getMyParameters () {
        return myParameters;
    }

    /**
     * @param myParameters the myParameters to set
     */
    public void setMyParameters (HashMap<String, Double> myParameters) {
        this.myParameters = myParameters;
    }
    /**
     * Get neighbors of specified state.
     * @param state
     * @return
     */
    public List<Cell> getStateNeighbors(int state){
    	List<Cell> temp = new ArrayList<Cell>();
    	for(Cell c: this.myNeighbors){
    		if(c.getMyCurrentState()==state){
    			temp.add(c);
    		}
    	}
    	return temp;
    }
    
}   
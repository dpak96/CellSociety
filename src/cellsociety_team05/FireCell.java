package cellsociety_team05;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends Cell {
    private double probCatch;
    
    /**
     * FireCell Constructor
     * @param xCoordinate
     * @param yCoordinate
     * @param startingState
     * @param sim
     */
    public FireCell (int xCoordinate, int yCoordinate, int startingState, Simulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        myColors = new Color[] {Color.WHITE, Color.GREEN, Color.RED};
        myPossibleStates = new String[] {"Empty", "Tree", "Burning"};
        mySquare = new Rectangle(553/sim.getMyHeight(), 553/sim.getMyHeight(), myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    
    /**
     * Sets probability for tree to catch fire
     * @param prob
     */
    public void setProb(double prob){
        probCatch = prob;
    }
    
    /**
     * checks if neighbor is burning
     * @return true if neighbor cell's current state is burning
     */
    private boolean neighborIsBurning(){
        boolean result = false;
        for (Cell cell: myNeighbors){
            if (cell.myCurrentState==2){
                result = true;
            }
        }
        return result;
    }

    /**
     * overrides super class method
     */
    @Override
    public void preUpdateCell () {
        if (myCurrentState==1){
            if (neighborIsBurning()){
                double ran = Math.random();
                if (ran<=probCatch){
                    myNextState = 2;
                }
                else {
                    myNextState = myCurrentState;
                }
            }
            else{
                myNextState = myCurrentState;
            }
        }
        else if (myCurrentState!=1) {
            myNextState = 0;
        }
    }
}

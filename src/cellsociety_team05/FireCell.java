package cellsociety_team05;

import java.util.List;
import javafx.scene.paint.Color;

public class FireCell extends Cell {
    private final String[] myPossibleStates = {"Empty", "Tree", "Burning"};
    private final Color[] myPossibleColors = {Color.WHITE, Color.GREEN, Color.RED};
    private double probCatch;
    private List<FireCell> myNeighbors;
    

    public FireCell (int xCoordinate, int yCoordinate, int startingState) {
        super(xCoordinate, yCoordinate, startingState);
        // TODO Auto-generated constructor stub
    }
    
    private boolean neighborIsBurning(){
        boolean result = false;
        for (FireCell cell: myNeighbors){
            if (cell.myCurrentState==2){
                result = true;
            }
        }
        return result;
    }

    @Override
    public void preUpdateCell () {
        myNextState = 0;
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
        }
    }

}

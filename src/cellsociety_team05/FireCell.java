package cellsociety_team05;

import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FireCell extends Cell {
    private double probCatch;
    

    public FireCell (int xCoordinate, int yCoordinate, int startingState) {
        super(xCoordinate, yCoordinate, startingState);
        myColors = new Color[] {Color.WHITE, Color.GREEN, Color.RED};
        myPossibleStates = new String[] {"Empty", "Tree", "Burning"};
        mySquare = new Rectangle(70.375, 70.375, myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    
    public void setProb(double prob){
        probCatch = prob;
    }
    
    private boolean neighborIsBurning(){
        boolean result = false;
        for (Cell cell: myNeighbors){
            if (cell.myCurrentState==2){
                result = true;
            }
        }
        return result;
    }

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

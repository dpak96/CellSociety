package cellsociety_team05;

import java.util.HashMap;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameOfLifeCell extends Cell {

    public GameOfLifeCell (int xCoordinate, int yCoordinate, int startingState,Simulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        myColors = new Color[] {Color.BLACK, Color.WHITE};
        myPossibleStates = new String[] {"Alive","Dead"};
        mySquare = new Rectangle(553/sim.getMyHeight(), 553/sim.getMyHeight(), myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }

    @Override
    public void preUpdateCell () {
        int liveNeighbors = 0;
        System.out.println("Cell: ("+getX()+","+getY()+")");
        for (Cell neighbor: myNeighbors) {
            System.out.println("neighbor: ("+neighbor.getX()+","+neighbor.getY()+") "+neighbor.getPossibleStates()[neighbor.getCurrentState()]);
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
        System.out.println("my current state: "+myPossibleStates[myCurrentState]);
        System.out.println("my live neighbors: "+liveNeighbors);
        System.out.println("my next state: "+myPossibleStates[myNextState]);
    }
/*
    @Override
    public void setCell (int xCoordinate,
                         int yCoordinate,
                         int startingState,
                         HashMap<String, Double> params,
                         Grid grid) {
        this.myXCoordinate = xCoordinate;
        this.myYCoordinate = yCoordinate;
        this.setCurrentState(startingState);
        
    }
*/
}

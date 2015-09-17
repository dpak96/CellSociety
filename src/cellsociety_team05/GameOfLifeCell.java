package cellsociety_team05;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {
    private final String[] myPossibleStates = {"Alive","Dead"};
    private final Color[] myColors = {Color.BLACK, Color.WHITE};

    public GameOfLifeCell (int xCoordinate, int yCoordinate, int startingState) {
        super(xCoordinate, yCoordinate, startingState);
        // TODO Auto-generated constructor stub
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

}

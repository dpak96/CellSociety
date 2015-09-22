package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class SegregationCell extends Cell {
    private final String[] myPossibleStates = {"X/Blue", "O/Red", "Empty"};
    private double mySatisfactionPercent;
    private Grid myGrid;
    
    /**
     * constructor for segregation cell
     * @param xCoordinate
     * @param yCoordinate
     * @param startingState
     * @param map
     * @param grid
     * @param sim
     */
    public SegregationCell (int xCoordinate, int yCoordinate, 
                            int startingState,
                            HashMap<String, Double> map,Grid grid,Simulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        mySatisfactionPercent = map.get("similar");
        myParameters = map;
        myGrid = grid;
        myDirty = false;
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
        mySquare = new Rectangle(553/sim.getMyHeight(), 553/sim.getMyHeight(), myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    
    /**
     * overrides super class method
     */
    @Override
    public void preUpdateCell() {
        mySatisfactionPercent = myParameters.get("similar");
        myNextState = myCurrentState;
        if (myCurrentState!=2 && myDirty!=true){
            int sameNeighbors = 0;
            for (Cell neighbor: myNeighbors) {
                if (neighbor.myCurrentState==myCurrentState){
                    sameNeighbors+=1;
                }
            }
            if (((double) sameNeighbors)/((double) myNeighbors.size())>=mySatisfactionPercent) {
                myNextState = myCurrentState;
            }
            else {
                List<Cell> empties = new ArrayList<Cell>();
                for (List<Cell> list: myGrid.getCellMatrix()) {
                    for (Cell cell: list){
                        if (cell.myCurrentState==2){
                            empties.add(cell);
                        }
                    }
                }
                if (empties.size()>0){
                    int randomIndex = (int) Math.floor(Math.random()*empties.size());
                    Cell switchCell = empties.get(randomIndex);
                    switchCell.setMyNextState(myCurrentState);
                    switchCell.setMyCurrentState(myCurrentState);
                    switchCell.myDirty = true;
                    myNextState = 2;
                    myCurrentState = 2;
                }
            }
        }
    }
}
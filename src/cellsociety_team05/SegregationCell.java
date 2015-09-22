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
                    switchCell.setNextState(myCurrentState);
                    switchCell.setCurrentState(myCurrentState);
                    switchCell.myDirty = true;
                    myNextState = 2;
                    myCurrentState = 2;
                }
            }
        }
    }
    
    /**
     * returns the color of the cell
     * @Emanuele
     */
    public Color getColor(){
    	return myColors[myCurrentState];
    }
}
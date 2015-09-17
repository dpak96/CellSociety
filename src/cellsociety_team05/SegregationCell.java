package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

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
                            double satisfaction,Grid grid) {
        super(xCoordinate, yCoordinate, startingState);
        mySatisfactionPercent = satisfaction;
        myGrid = grid;
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
    }
    
    @Override
    public void preUpdateCell() {
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
            int randomIndex = (int) Math.floor(Math.random()*empties.size());
            Cell switchCell = empties.get(randomIndex);
            switchCell.setNextState(myCurrentState);
            myNextState = switchCell.getCurrentState();
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
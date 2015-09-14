package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class SegregationCell extends Cell {
    private final String[] myPossibleStates = {"X/Blue", "O/Red", "Empty"};
    private double mySatisfactionPercent;

    public SegregationCell (Grid grid, int xCoordinate, int yCoordinate, 
                            int startingState/*, Simulation simulation*/,
                            double satisfaction) {
        super(grid, xCoordinate, yCoordinate, startingState/*, simulation*/);
        mySatisfactionPercent = satisfaction;
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
            for (Cell cell: myGrid.getGrid()) {
                if (cell.myCurrentState==2){
                    empties.add(cell);
                }
            }
            int randomIndex = (int) Math.floor(Math.random()*empties.size());
            Cell switchCell = empties.get(randomIndex);
            switchCell.setNextState(myCurrentState);
            myNextState = switchCell.getCurrentState();
        }
    }
}

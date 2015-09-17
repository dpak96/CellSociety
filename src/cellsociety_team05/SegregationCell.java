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
        System.out.println("Cell: ("+myXCoordinate+","+myYCoordinate+")"+myPossibleStates[myCurrentState]);
        if (myCurrentState!=2 && myDirty!=true){
            int sameNeighbors = 0;
            for (Cell neighbor: myNeighbors) {
                if (neighbor.myCurrentState==myCurrentState){
                    sameNeighbors+=1;
                    System.out.println("neighbor: ("+neighbor.myXCoordinate+","+neighbor.myYCoordinate+")");
                }
            }
            System.out.println(sameNeighbors);
            if (((double) sameNeighbors)/((double) myNeighbors.size())>=mySatisfactionPercent) {
                myNextState = myCurrentState;
                System.out.println("satisfied");
            }
            else {
                System.out.println("Unsatisfied");
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
                    System.out.println("SwitchCell: ("+switchCell.myXCoordinate+","+switchCell.myYCoordinate+")");
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
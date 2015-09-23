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
    private double mySatisfactionPercent;
    private Grid myGrid;
    private static int EMPTY = 2;
    private static String SIMILAR = "similar";
    
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
        mySatisfactionPercent = map.get(SIMILAR);
        myParameters = map;
        myGrid = grid;
        myDirty = false;
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
        
        /**
         * I added an event handler to the rectangle in the cell abstract class, since every cell will have this characteristic
         * I use set square to allow for update 
         * @author emanuele
         */
        this.setMySquare(new Rectangle(440/sim.getMyHeight(), 440/sim.getMyHeight(), myColors[startingState]));
        //mySquare = new Rectangle(440/sim.getMyHeight(), 440/sim.getMyHeight(), myColors[startingState]);
        /**
         * end of changes
         */
        
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    
    /**
     * overrides super class method
     */
    @Override
    public void preUpdateCell() {
        mySatisfactionPercent = myParameters.get("similar");
        myNextState = myCurrentState;
        if (myCurrentState!=EMPTY && !myDirty){
            int sameNeighbors = 0;
            for (Cell neighbor: myNeighbors) {
                if (neighbor.myCurrentState==myCurrentState){
                    sameNeighbors++;
                }
            }
            //Satisfied
            if (((double) sameNeighbors)/((double) myNeighbors.size())>=mySatisfactionPercent) {
                myNextState = myCurrentState;
            }
            //Unsatisfied
            //Maybe try to find more efficient way to collect empty cells? Wherever simulation is being run.. Then can remove grid.
            else {
                List<Cell> empties = new ArrayList<Cell>();
                for (List<Cell> list: myGrid.getCellMatrix()) {
                    for (Cell cell: list){
                        if (cell.myCurrentState==EMPTY){
                            empties.add(cell);
                        }
                    }
                }
                if (empties.size()>0){
                    System.out.println("switch");
                    int randomIndex = (int)Math.random()*empties.size();
                    //Empty cell that this cell will move to. 
                    Cell switchCell = empties.get(randomIndex);
                    switchCell.setMyNextState(myCurrentState);
                    switchCell.setMyCurrentState(myCurrentState);
                    switchCell.myDirty = true;
                    myNextState = EMPTY;
                    myCurrentState = EMPTY;
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
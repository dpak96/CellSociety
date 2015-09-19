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
                            HashMap<String, Double> map,Grid grid) {
        super(xCoordinate, yCoordinate, startingState);
        mySatisfactionPercent = map.get("similar");
        myParameters = map;
        myGrid = grid;
        myDirty = false;
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
        mySquare = new Rectangle(70.375, 70.375, myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    /*
    public SegregationCell(){
    	this(0, 0, 0, 0, null);
    }*/
    /*
    public void setCell(int xCoordinate, int yCoordinate, 
            int startingState,
            HashMap<String, Double> param,Grid grid){
    	this.myXCoordinate = xCoordinate;
    	this.myYCoordinate = yCoordinate;
    	this.setCurrentState(startingState);
    	mySatisfactionPercent = param.get("satisfaction");
    	myGrid = grid;
    }*/
    
    @Override
    public void preUpdateCell() {
        mySatisfactionPercent = myParameters.get("similar");
        System.out.println("Cell: ("+getX()+","+getY()+") "+myPossibleStates[myCurrentState]);
        System.out.println(mySatisfactionPercent);
        myNextState = myCurrentState;
        if (myCurrentState!=2 && myDirty!=true){
            int sameNeighbors = 0;
            for (Cell neighbor: myNeighbors) {
                System.out.println("Neighbor: ("+neighbor.getX()+","+neighbor.getY()+") "+myPossibleStates[neighbor.myCurrentState]);
                if (neighbor.myCurrentState==myCurrentState){
                    sameNeighbors+=1;
                }
            }
            System.out.println(sameNeighbors+" "+myNeighbors.size());
            if (((double) sameNeighbors)/((double) myNeighbors.size())>=mySatisfactionPercent) {
                myNextState = myCurrentState;
                System.out.println("satsfied");
            }
            else {
                System.out.println("unsatisfied");
                List<Cell> empties = new ArrayList<Cell>();
                for (List<Cell> list: myGrid.getCellMatrix()) {
                    for (Cell cell: list){
                        if (cell.myCurrentState==2){
                            empties.add(cell);
                        }
                    }
                }
                System.out.println(empties.size());
                if (empties.size()>0){
                    System.out.println("switch");
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
        System.out.println("Next: "+myPossibleStates[myNextState]);
    }
    
    /**
     * returns the color of the cell
     * @Emanuele
     */
    public Color getColor(){
    	return myColors[myCurrentState];
    }
}
package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class SugarCell extends Cell {
    private Grid myGrid;
    private SugarSimulation mySim;
    private int mySugarLimit;

    public SugarCell (int xCoordinate,
                      int yCoordinate,
                      int startingState,
                      Grid grid,
                      SugarSimulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        myGrid = grid;
        mySim = sim;
        myPossibleStates = new String[] {"Empty","1","2","3","4","5"};
        myColors = new Color[] {Color.WHITE, Color.YELLOW, Color.GOLD, Color.ORANGE, Color.RED, Color.BLACK};
        this.setMyShape(440/sim.getMyHeight(), myColors[startingState], sim.getShape());
    }

    @Override
    public void preUpdateCell () {
        if(mySim.myTimes[getX()][getY()]==0){
            if(myCurrentState==5){
                mySim.myAgentSugars[getX()][getY()]=4;
                mySugarLimit=4;
            }
            else{
                mySugarLimit = myCurrentState;
            }
        }
        initNeighbors(mySim.myVision);
        if (myCurrentState==5 && myDirty==false){
            if (mySim.myAgentSugars[getX()][getY()]==0){
                setMyNextState(0);
            }
            else {
                int maxSugar = 0;
                Cell moveCell = null;
                for (Cell cell: myNeighbors){
                    if (cell.getMyCurrentState()!=5){
                        int x = cell.getX();
                        int y = cell.getY();
                        if (mySim.mySugars[x][y] > maxSugar) {
                            maxSugar = mySim.mySugars[x][y];
                            moveCell = myGrid.getCellMatrix().get(x).get(y);
                        }
                    }
                }
                if (moveCell==null){
                    int ran = (int) Math.floor(Math.random()*myNeighbors.size());
                    moveCell = myNeighbors.get(ran);
                }
                System.out.println("("+moveCell.getX()+","+moveCell.getY()+") "+mySim.mySugars[moveCell.getX()][moveCell.getY()]);
                int x = moveCell.getX();
                int y = moveCell.getY();
                mySim.myAgentSugars[getX()][getY()]+=mySim.mySugars[x][y];
                mySim.mySugars[x][y]=0;
                mySim.mySugars[getX()][getY()]=0;
                setMyNextState(0);
                moveCell.setMyNextState(5);
                mySim.myAgentSugars[moveCell.getX()][moveCell.getY()]=mySim.myAgentSugars[getX()][getY()]-mySim.myMetabolism;
                moveCell.setMyDirty(true);
            }
        }
        if (mySim.myTimes[getX()][getY()] % mySim.myInterval == 0 && mySim.mySugars[getX()][getY()]<mySugarLimit && myNextState!=5) {
            mySim.mySugars[getX()][getY()] += mySim.myRate;
            setMyNextState(mySim.mySugars[getX()][getY()]);
        }
        mySim.myTimes[getX()][getY()] += 1;
    }

    /**
     * initializes neighbors for four compass direction
     */
    public void initNeighbors (int vision) {
        for (List<Cell> list : myGrid.getCellMatrix()) {
            for (Cell cell : list) {
                List<Cell> neighbors = new ArrayList<Cell>();
                for (int j=1;j<vision+1;j++){
                    int[] x = {0,0,j,-j};
                    int[] y = {j,-j,0,0};
                    for (int i=0;i<x.length;i++){
                        int xCoordinate = cell.getX()+x[i];
                        int yCoordinate = cell.getY()+y[i];
                        if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myGrid.getCellMatrix().size() && yCoordinate<myGrid.getCellMatrix().get(0).size()){
                            neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(yCoordinate));
                        }
                    }
                }
                cell.setMyNeighbors(neighbors);
            }
        }
    }

}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class SugarCell extends Cell {
    private Grid myGrid;
    private SugarSimulation mySim;

    public SugarCell (int xCoordinate, int yCoordinate, int startingState, Grid grid, SugarSimulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        myGrid = grid;
        mySim = sim;
        myPossibleStates = new String[] {"Agent", "Empty"};
        myColors = new Color[] {Color.RED, Color.WHITE};
    }

    @Override
    public void preUpdateCell () {
        initNeighbors(mySim.myVisions[getX()][getY()]);
        if (myCurrentState==0 && myDirty==false){
            int maxSugar = 0;
            Cell moveCell = null;
            for (Cell cell: myNeighbors){
                if (cell.getMyCurrentState()==1){
                    int x = cell.getX();
                    int y = cell.getY();
                    if (mySim.mySugars[x][y]>maxSugar){
                        maxSugar = mySim.mySugars[x][y];
                        moveCell = myGrid.getCellMatrix().get(x).get(y);
                    }
                }
            }
            int x = moveCell.getX();
            int y = moveCell.getY();
            mySim.myAgentSugars[getX()][getY()]+=mySim.mySugars[x][y];
            mySim.mySugars[x][y]=0;
            moveCell.setMyNextState(0);
            mySim.myAgentSugars[moveCell.getX()][moveCell.getY()]=mySim.myAgentSugars[getX()][getY()]-mySim.myMetabolisms[getX()][getY()];
            moveCell.setMyDirty(true);
            myNextState = 1;
        }
        if(mySim.myTimes[getX()][getY()]%mySim.myInterval==0){
            mySim.mySugars[getX()][getY()]+=mySim.myRate;
        }
        mySim.myTimes[getX()][getY()]+=1;
    }
    
    /**
     * initializes neighbors for four compass direction
     */
    public void initNeighbors(int vision){
        for (List<Cell> list: myGrid.getCellMatrix()){
            for (Cell cell: list){
                List<Cell> neighbors = new ArrayList<Cell>();
                for (int j=1;j<vision;j++){
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

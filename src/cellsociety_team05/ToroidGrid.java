package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToroidGrid extends Grid {

    public ToroidGrid (int width, int height, Simulation sim, HashMap<String, Double> map) {
        super(width, height, sim, map);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void initNeighbors () {
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = {0,0,1,1,1,-1,-1,-1};
                int[] y = {1,-1,0,1,-1,0,1,-1};
                for (int i=0;i<x.length;i++){
                    int xCoordinate = cell.getX()+x[i];
                    int yCoordinate = cell.getY()+y[i];
                    if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myCells.size() && yCoordinate<myCells.get(0).size()){
                        neighbors.add(myCells.get(xCoordinate).get(yCoordinate));
                    }
                    else if(xCoordinate>=myCells.size() && yCoordinate>=0 && yCoordinate<myCells.get(0).size()){
                        neighbors.add(myCells.get(0).get(yCoordinate));
                    }
                    else if(xCoordinate<0 && yCoordinate>=0 && yCoordinate<myCells.get(0).size()){
                        neighbors.add(myCells.get(myCells.size()-1).get(yCoordinate));
                    }
                    else if(yCoordinate>=myCells.get(0).size() && xCoordinate>=0 && xCoordinate<myCells.size()){
                        neighbors.add(myCells.get(xCoordinate).get(0));
                    }
                    else if(yCoordinate<0 && xCoordinate>=0 && xCoordinate<myCells.size()){
                        neighbors.add(myCells.get(xCoordinate).get(myCells.get(0).size()-1));
                    }
                }
                cell.setMyNeighbors(neighbors);
            }
        }
    }

}

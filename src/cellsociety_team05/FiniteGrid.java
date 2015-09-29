package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FiniteGrid extends Grid {

    public FiniteGrid (int width, int height, Simulation sim, Map<String, Double> map) {
        super(width, height, sim, map);
    }

    @Override
    protected void initNeighbors () {
        for (List<Cell> list : myCells) {
            for (Cell cell : list) {
                cell.myParameters = mySimulation.myParameters;
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = { 0, 0, 1, 1, 1, -1, -1, -1 };
                int[] y = { 1, -1, 0, 1, -1, 0, 1, -1 };
                for (int i = 0; i < x.length; i++) {
                    int xCoordinate = cell.getX() + x[i];
                    int yCoordinate = cell.getY() + y[i];
                    if (xCoordinate >= 0 && yCoordinate >= 0 && xCoordinate < myCells.size() &&
                        yCoordinate < myCells.get(0).size()) {
                        neighbors.add(myCells.get(xCoordinate).get(yCoordinate));

                    }
                }
                cell.setMyNeighbors(neighbors);
            }
        }
    }
}

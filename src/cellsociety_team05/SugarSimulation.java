package cellsociety_team05;

import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class SugarSimulation extends Simulation {
    int[][] myAgentSugars;
    int[][] mySugars;
    int mySugarLimit;
    int myVision;
    int myMetabolism;
    int myInterval;
    int[][] myTimes;
    int myRate;

    public SugarSimulation (GridPane gridPane,
                            GUI gui,
                            Map<String, Double> params,
                            List<CellInfo> list,
                            int height,
                            int width,
                            String shape) {
        super(gridPane, gui, params, list, height, width, shape);
        myAgentSugars = new int[width][height];

        try {
            mySugarLimit = (int) Math.round(params.get("sugarlimit"));
        }
        catch (Exception e) {
            try {
                mySugarLimit = 3;
            }
            catch (Exception ee) {
                return;
            }
        }
        try {
            myVision = (int) Math.round(params.get("vision"));
        }
        catch (Exception e) {
            try {
                myVision = 1;
            }
            catch (Exception ee) {
                return;
            }
        }
        try {
            myMetabolism = (int) Math.round(params.get("metabolism"));
        }
        catch (Exception e) {
            try {
                myMetabolism = 2;
            }
            catch (Exception ee) {
                return;
            }
        }
        try {
            myInterval = (int) Math.round(params.get("interval"));
        }
        catch (Exception e) {
            try {
                myInterval = 1;
            }
            catch (Exception ee) {
                return;
            }
        }
        try {
            myRate = (int) Math.round(params.get("rate"));
        }
        catch (Exception e) {
            try {
                myRate = 1;
            }
            catch (Exception ee) {
                return;
            }
        }
        myTimes = new int[width][height];
    }

    @Override
    public List<List<Cell>> setUpCells (Grid grid, int width, int height, Map<String, Double> map) {
        return setUpRandomCells(grid, width, height, map, 3);
    }

    @Override
    public Cell makeCell (int x, int y, int start, Grid g, Map<String, Double> map) {
        myGrid = g;
        SugarCell c = new SugarCell(x, y, start, g, this);
        return c;
    }

    /**
     * reads in list of cells specified in xml file and initializes grid
     *
     * @param list
     * @throws SimulationException
     */
    @Override
    public void readCellList (List<CellInfo> list) throws SimulationException {
        if (list != null) {
            int width = 0;
            int height = 0;
            for (CellInfo c : list) {
                if (c.getX() > width) {
                    width = c.getX();
                }
                if (c.getY() > height) {
                    height = c.getY();
                }
            }
            mySugars = new int[width + 1][height + 1];
            for (CellInfo cell : list) {
                Cell thisCell = myGrid.getCellMatrix().get(cell.getX()).get(cell.getY());
                thisCell.setMyCurrentState(cell.getState());
                thisCell.setMyNextState(cell.getState());
                thisCell.changeColor();
                // thisCell.mySquare.setFill(thisCell.myColors[thisCell.getMyCurrentState()]);
                if (thisCell.getMyCurrentState() != 5) {
                    mySugars[thisCell.getX()][thisCell.getY()] = thisCell.getMyCurrentState();
                }
                else {
                    mySugars[thisCell.getX()][thisCell.getY()] = 0;
                }
            }
        }
    }

    @Override
    public String getName () {
        return "Sugar";
    }

}

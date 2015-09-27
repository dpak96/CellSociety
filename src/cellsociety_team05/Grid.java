package cellsociety_team05;

import java.util.List;
import java.util.Map;


/**
 *
 * @author Lucas Donaldson
 *
 */
public abstract class Grid {
    protected List<List<Cell>> myCells;
    protected Simulation mySimulation;

    /**
     * constructor for grid class
     *
     * @param width
     * @param height
     * @param sim
     * @param map
     */
    public Grid (int width, int height, Simulation sim, Map<String, Double> map) {
        mySimulation = sim;
        myCells = mySimulation.setUpCells(this, width, height, map);
        initNeighbors();
    }

    /**
     * initializes neighbors of cells in grid
     */
    protected abstract void initNeighbors ();

    /**
     * Sets the next state of each cell in the grid.
     */
    public void preUpdateGrid () {
        for (List<Cell> list : myCells) {
            for (Cell cell : list) {
                cell.preUpdateCell();
            }
        }
        for (List<Cell> list : myCells) {
            for (Cell cell : list) {
                cell.myDirty = false;
            }
        }
    }

    /**
     * Switches the state of each cell in the grid to it's next state.
     */
    public void updateGrid () {
        for (List<Cell> list : myCells) {
            for (Cell cell : list) {
                cell.updateCell();
            }
        }
    }

    /**
     *
     * @return A list of the cells in the grid.
     */
    public List<List<Cell>> getCellMatrix () {
        return myCells;
    }

    public int getNumberOfStates () {
        return myCells.get(0).get(0).getMyColors().length;
    }

    /**
     * returns number of cells of each type for use in graph display
     *
     * @return
     */
    public int[] getStats () {
        int[] stats = new int[3];
        for (int i = 0; i < 3; i++) {
            int numberOfCells = 0;
            for (List<Cell> list : myCells) {
                for (Cell cell : list) {
                    if (cell.getMyCurrentState() == i) {
                        numberOfCells += 1;
                    }
                }
            }
            stats[i] = numberOfCells;
        }
        return stats;
    }
}

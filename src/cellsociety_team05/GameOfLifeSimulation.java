package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class GameOfLifeSimulation extends Simulation {

    /**
     * constructor for game of life simulation
     * 
     * @param gridPane
     * @param gui
     * @param params
     * @param list
     * @param height
     * @param width
     */
    public GameOfLifeSimulation (GridPane gridPane,
                                 GUI gui,
                                 Map<String, Double> params,
                                 List<CellInfo> list,
                                 int height,
                                 int width, String shape) {
        super(gridPane, gui, params, list, height, width, shape);

    }

    /**
     * overrides super class method
     */
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, Map<String, Double> map) {
        myGrid = g;
        GameOfLifeCell c = new GameOfLifeCell(x, y, start, this);
        return c;
    }

    /**
     * overrides super class method
     */
    @Override
    public List<List<Cell>> setUpCells (Grid grid, int width, int height, Map<String,Double> map) {
        return setUpRandomCells(grid,width,height,map,2);
    }
}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class FireSimulation extends Simulation {
    private double probCatch;

    /**
     * constructor for fire simulation class
     * 
     * @param gridPane
     * @param gui
     * @param params
     * @param list
     * @param height
     * @param width
     */
    public FireSimulation (GridPane gridPane,
                           GUI gui,
                           Map<String, Double> params,
                           List<CellInfo> list,
                           int height,
                           int width) {
        super(gridPane, gui, params, list, height, width);
        probCatch = params.get("probCatch");
    }

    /**
     * overrides super class method
     */
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, Map<String, Double> map) {
        FireCell c = new FireCell(x, y, start, this);
        c.setProb(0.5);
        return c;
    }

    /**
     * overrides super class method
     */
    @Override
    public List<List<Cell>> setUpCells (Grid grid, int width, int height, Map<String, Double> map) {
        List<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i = 0; i < width; i++) {
            list.add(new ArrayList<Cell>());
            for (int j = 0; j < height; j++) {
                int state = (int) (Math.random() * 3);
                Cell newcell = makeCell(i, j, state, grid, map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }
}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;


public class AntForagingSimulation extends Simulation {

    private ArrayList<Ant> myAnts;

    public AntForagingSimulation (GridPane gridPane,
                                  GUI gui,
                                  Map<String, Double> params,
                                  List<CellInfo> list,
                                  int height,
                                  int width) {
        super(gridPane, gui, params, list, height, width);
        // TODO Auto-generated constructor stub
    }

    @Override
    public AntCell makeCell (int x, int y, int start, Grid g, Map<String, Double> map) {
        // myGrid = g;
        AntCell c = new AntCell(x, y, start, map, myGrid, this);
        return c;
    }

    @Override
    public List<List<Cell>> setUpCells (Grid grid, int width, int height, Map<String, Double> map) {
        List<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i = 0; i < width; i++) {
            list.add(new ArrayList<Cell>());
            for (int j = 0; j < height; j++) {
                AntCell newcell = makeCell(i, j, 1, grid, map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

    public void moveAntsAround () {
        for (Ant ant : myAnts) {
            ant.forage();
        }
    }

    private void generateNewAnts () {

    }

}

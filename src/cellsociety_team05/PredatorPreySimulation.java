package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class PredatorPreySimulation extends Simulation {
    int PREY_REPRODUCTION_TIME;
    int PREDATOR_REPRODUCTION_TIME;
    int PREDATOR_ENERGY;

    public PredatorPreySimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params) {
        super(gridPane, gui, params);
        PREY_REPRODUCTION_TIME = (int) Math.round(params.get("preyreproductiontime"));
        PREDATOR_REPRODUCTION_TIME = (int) Math.round(params.get("predatorreproductiontime"));
        PREDATOR_ENERGY = (int) Math.round(params.get("energy"));
    }

    @Override
    public void setSimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params) {
        this.myGUI = gui;
        this.myGridPane = gridPane;
        this.myParameters = params;
    }

    @Override
    public Cell makeCell (int x, int y, int start, Grid g) {
        myGrid = g;
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("preyreproductiontime", (double) PREY_REPRODUCTION_TIME);
        map.put("predatorreproductiontime", (double) PREDATOR_REPRODUCTION_TIME);
        map.put("energy", (double) PREDATOR_ENERGY);
        PredatorPreyCell c = new PredatorPreyCell(x,y,start,map);
        return c;
    }

    @Override
    public void setCellType (Grid grid) {
        for (List<Cell> list: grid.getCellMatrix()){
            for (Cell cell: list){
                HashMap<String, Double> map = new HashMap<String, Double>();
                map.put("preyreproductiontime", (double) PREY_REPRODUCTION_TIME);
                map.put("predatorreproductiontime", (double) PREDATOR_REPRODUCTION_TIME);
                map.put("energy", (double) PREDATOR_ENERGY);
                cell = new PredatorPreyCell(cell.myXCoordinate,cell.myYCoordinate,0,map);
                System.out.println(cell.getClass().toString());
            }
        }
    }

    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*3);
                Cell newcell = makeCell(i, j, state, grid);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

}

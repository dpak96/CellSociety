package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class PredatorPreySimulation extends Simulation {
    int PREY_REPRODUCTION_TIME;
    int PREDATOR_REPRODUCTION_TIME;
    int PREDATOR_ENERGY;

    public PredatorPreySimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params, List<CellInfo> list, int height, int width) {
        super(gridPane, gui, params,list, height, width);
        PREY_REPRODUCTION_TIME = (int) Math.round(params.get("preyreproductiontime"));
        PREDATOR_REPRODUCTION_TIME = (int) Math.round(params.get("predatorreproductiontime"));
        PREDATOR_ENERGY = (int) Math.round(params.get("energylimit"));
        System.out.println("1 PREY_REPRODUCTION_TIME: "+PREY_REPRODUCTION_TIME);
        System.out.println("1 PREDATOR_REPRODUCTION_TIME: "+PREDATOR_REPRODUCTION_TIME);
        System.out.println("1 PREDATOR_ENERGY: "+PREDATOR_ENERGY);
    }
    
    @Override
    public Cell makeCell (int x, int y, int start, Grid g) {
        myGrid = g;
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("preyreproductiontime", (double) PREY_REPRODUCTION_TIME);
        map.put("predatorreproductiontime", (double) PREDATOR_REPRODUCTION_TIME);
        map.put("energylimit", (double) PREDATOR_ENERGY);
        PredatorPreyCell c = new PredatorPreyCell(x,y,start,map,myGrid);
        System.out.println(" "+c.myParameters.get("reproductiontime"));
        return c;
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
                System.out.println("  "+list.get(i).get(j).myParameters.get("reproductiontime"));
            }
        }
        return list;
    }

}

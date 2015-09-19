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
        //System.out.println("1 PREY_REPRODUCTION_TIME: "+PREY_REPRODUCTION_TIME);
        //System.out.println("1 PREDATOR_REPRODUCTION_TIME: "+PREDATOR_REPRODUCTION_TIME);
        //System.out.println("1 PREDATOR_ENERGY: "+PREDATOR_ENERGY);
    }
    
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, HashMap<String, Double> map) {
        myGrid = g;
        HashMap<String, Double> params = new HashMap<String, Double>();
        params.put("reproductiontime", map.get("reproductiontime"));
        params.put("energy", map.get("energy"));
        params.put("preyreproductiontime", map.get("preyreproductiontime"));
        params.put("predatorreproductiontime", map.get("predatorreproductiontime"));
        params.put("energylimit", map.get("energylimit"));
        PredatorPreyCell c = new PredatorPreyCell(x,y,start,params,myGrid,this);
        //System.out.println(" "+c.myParameters.get("reproductiontime"));
        return c;
    }

    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height,HashMap<String, Double> map) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*3);
                Cell newcell = makeCell(i, j, state, grid,map);
                list.get(i).add(newcell);
                //System.out.println("  "+list.get(i).get(j).myParameters.get("reproductiontime"));
            }
        }
        return list;
    }

}

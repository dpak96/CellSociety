package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class FireSimulation extends Simulation {
    private double probCatch;

    public FireSimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params,int height, int width) {
        super(gridPane, gui, params, height, width);
        probCatch = params.get("probCatch");
    }

    @Override
    public Cell makeCell (int x, int y, int start, Grid g) {
        myGrid = g;
        FireCell c = new FireCell(x,y,start);
        c.setProb(probCatch);
        return c;
    }

    @Override
    public void setCellType (Grid grid) {
        for (List<Cell> list: grid.getCellMatrix()){
            for (Cell cell: list){
                cell = new FireCell(cell.myXCoordinate,cell.myYCoordinate,0);
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

    @Override
    public void setSimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params) {
        this.myGUI = gui;
        this.myGridPane = gridPane;
        this.myParameters = params;
    }
}

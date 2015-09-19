package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class GameOfLifeSimulation extends Simulation {

    public GameOfLifeSimulation (GridPane gridPane, GUI gui, HashMap<String, Double> params,int height, int width) {
        super(gridPane, gui, params,height,width);

    }

    @Override
    public Cell makeCell (int x, int y, int start, Grid g) {
        myGrid = g;
        GameOfLifeCell c = new GameOfLifeCell(x,y,start);
        return c;
    }

    @Override
    public void setCellType (Grid grid) {
        for (List<Cell> list: grid.getCellMatrix()){
            for (Cell cell: list){
                cell = new GameOfLifeCell(cell.myXCoordinate,cell.myYCoordinate,0);
            }
        }

    }

    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*2);
                Cell newcell = makeCell(i, j, state, grid);
                list.get(i).add(newcell);
            }
        }
        return list;
    }

}

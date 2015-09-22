package cellsociety_team05;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
    private double similar; 
    private double ratio;
    private double empty;

    /**
     * constructor for segregation simulation
     * @param gridPane
     * @param gui
     * @param param
     * @param list
     * @param height
     * @param width
     */
    public SegregationSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> param, List<CellInfo> list, int height, int width){
        super(gridPane, gui, param, list, height, width);
        similar = param.get("similar");
        ratio = param.get("ratio");
        empty = param.get("empty");
        if (list==null){
            initGrid();
        }
    }

    /**
     * sets parameter for percent of similar neighbors required for satisfaction
     * @param x
     */
    public void setSimilar(double x){
        similar = x;
    }

    /**
     * initializes grid with appropriate number of blue, red, and empty cells
     */
    public void initGrid(){
        int num = myGrid.getCellMatrix().size()*myGrid.getCellMatrix().get(0).size();
        ArrayList<Integer> list = new ArrayList<Integer>();
        int numEmpty = (int) Math.floor(((double)num)*empty);
        num-= numEmpty;
        int num1 = (int) Math.floor(((double)num)*ratio);
        int num2 = num - num1;
        int k = 0;
        for (int i=0;i<numEmpty;i++){
            list.add(2);
        }
        for (int i=0;i<num1;i++){
            list.add(0);
        }
        for (int i=0;i<num2;i++){
            list.add(1);
        }
        for (List<Cell> cells: myGrid.getCellMatrix()){
            for (Cell cell: cells){
                int ran = (int) Math.floor(Math.random()*list.size());
                cell.setParameter("similar",similar);
                cell.setMyCurrentState(list.remove(ran));
                cell.getMySquare().setFill(cell.myColors[cell.getMyCurrentState()]);
            }
        }
    }

    /**
     * overrides super class method
     */
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, HashMap<String,Double> map) {
        myGrid = g;
        SegregationCell c = new SegregationCell(x,y,start,map,myGrid,this);
        return c;
    }

    /**
     * overrides super class method
     */
    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height, HashMap<String,Double> map) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*2);
                Cell newcell = makeCell(i, j, 1, grid,map);
                list.get(i).add(newcell);
            }
        }
        return list;
    }
}

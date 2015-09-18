package cellsociety_team05;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private double similar; 
	private double ratio;
	private double empty;
	
	public SegregationSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> param){
		super(gridPane, gui, param);
		similar = param.get("similar");
		ratio = param.get("ratio");
		empty = param.get("empty");
	}
	
	
	//Need a way to set similarity percentage
	public void setSimilar(double x){
		similar = x;
	}
	
	public void setCellType(Grid grid){
	    for (List<Cell> list: grid.getCellMatrix()){
	        for (Cell cell: list){
	            cell = new SegregationCell(cell.myXCoordinate,cell.myYCoordinate,0,similar,grid);
	            System.out.println(cell.getClass().toString());
	        }
	    }
	}
	
	public void initGrid(Grid grid){
	    int num = grid.getCellMatrix().size()*grid.getCellMatrix().get(0).size();
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
	    System.out.println("empty: "+numEmpty);
	    System.out.println("blue: "+num1);
	    System.out.println("red: "+num2);
	    for (List<Cell> cells: grid.getCellMatrix()){
                for (Cell cell: cells){
                    System.out.println(k++);
                    int ran = (int) Math.floor(Math.random()*list.size());
                    cell.setCurrentState(list.remove(ran));
                    cell.getSquare().setFill(cell.myColors[cell.getCurrentState()]);
                }
	    }
	}
	
	@Override
	public void updateState(Cell cell) {
	    myGrid.preUpdateGrid();
	}


    @Override
    public Cell makeCell (int x, int y, int start, Grid g) {
        myGrid = g;
        HashMap<String, Double> map = new HashMap<String, Double>();
        map.put("similar", similar);
        map.put("ratio", ratio);
        map.put("empty", empty);
        SegregationCell c = new SegregationCell(x,y,start,map.get("similar"),myGrid);
        return c;
    }


    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*2);
                Cell newcell = makeCell(i, j, 0, grid);
                list.get(i).add(newcell);
            }
        }
        return list;
        
    }

}

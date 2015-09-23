package cellsociety_team05;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private double similar; 
	private double ratio;
	private double empty;
	private static String SIMILAR = "similar";
	private static String RATIO = "ratio";
	private static String EMPTY = "empty";
	
	public SegregationSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> param, List<CellInfo> list, int height, int width){
		super(gridPane, gui, param, list, height, width);
		similar = param.get(SIMILAR);
		ratio = param.get(RATIO);
		empty = param.get(EMPTY);
		//Set random grid?
		if (list==null){
		    initGrid();
		}
	}

	/**
	 * Set the similarity percentage
	 * @param x
	 */
	public void setSimilar(double x){
		similar = x;
	}
	/**
	 * Initialization of grid with random setup
	 * 
	 */
	public void initGrid(){
	    int cellCount  = myGrid.getCellMatrix().size()*myGrid.getCellMatrix().get(0).size();
	    ArrayList<Integer> cellTypes = new ArrayList<Integer>();
	    int numEmpty = (int)(((double)cellCount )*empty);
	    cellCount -= numEmpty;
	    int numType1 = (int)(((double)cellCount )*ratio);
	    int numType2 = cellCount  - numType1;
	    for (int i=0;i<numEmpty;i++){
	        cellTypes.add(2);
	    }
	    for (int i=0;i<numType1;i++){
	        cellTypes.add(0);
	    }
	    for (int i=0;i<numType2;i++){
	        cellTypes.add(1);
	    }
	    for (List<Cell> cells: myGrid.getCellMatrix()){
                for (Cell cell: cells){
                    int ran = (int)(Math.random()*cellTypes.size());
                    cell.setParameter(SIMILAR,similar);
                    cell.setCurrentState(cellTypes.remove(ran));
                    cell.getSquare().setFill(cell.myColors[cell.getCurrentState()]);
                }
	    }
	}
	/**
	 *Make a new SimulationCell
	 */
    @Override
    public Cell makeCell (int x, int y, int start, Grid g, HashMap<String,Double> map) {
        myGrid = g;
        SegregationCell c = new SegregationCell(x,y,start,map,myGrid,this);
        return c;
    }

    /**
     * Initiate the grid with preset cells. 
     */
    @Override
    public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height, HashMap<String,Double> map) {
        ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            list.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                Cell newcell = makeCell(i, j, 1, grid,map);
                list.get(i).add(newcell);
            }
        }
        return list;       
    }

}

package cellsociety_team05;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
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
	    for (List<Cell> list: grid.getGrid()){
	        for (Cell cell: list){
	            cell = new SegregationCell(cell.myXCoordinate,cell.myYCoordinate,0,similar,grid);
	            System.out.println(cell.getClass().toString());
	        }
	    }
	}
	
	public void initGrid(Grid grid){
	    int num = grid.getGrid().size()*grid.getGrid().get(0).size();
	    System.out.println("num: "+num);
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
	    for (List<Cell> cells: grid.getGrid()){
                for (Cell cell: cells){
                    System.out.println(k++);
                    int ran = (int) Math.floor(Math.random()*list.size());
                    cell.setCurrentState(list.remove(ran));
                }
	    }
	}
	
	@Override
	public void updateState(Cell cell) {
		/*int sim = 0;
		int tot = 0;
		for(Cell nCell: cell.getNeighbors()){
			++tot;
			if(nCell.myCurrentState == cell.myCurrentState){
				++sim;
			}
		}
		if((double)sim/tot < similar){
			//make cell empty and move it elsewhere
		}*/
	    myGrid.preUpdateGrid();
	}

}

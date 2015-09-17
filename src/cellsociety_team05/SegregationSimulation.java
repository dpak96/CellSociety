package cellsociety_team05;


import java.util.HashMap;
import java.util.ArrayList;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
	private double similar; 
	private double ratio;
	private double empty;
	
	public SegregationSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> param){
		super(gridPane, gui, param);
		similar = param.get("similar");
	}
	
	
	//Need a way to set similarity percentage
	public void setSimilar(double x){
		similar = x;
	}
	
	public void setCellType(Grid grid){
	    for (Cell cell: grid.getGrid()){
	        cell = new SegregationCell(grid,cell.myXCoordinate,cell.myYCoordinate,0,similar);
	    }
	}
	
	public void initGrid(Grid grid){
	    int num = grid.getGrid().size();
	    ArrayList<Integer> list = new ArrayList<Integer>();
	    num-= (int) Math.floor(((double)num)*empty);
	    int num1 = (int) Math.floor(((double)num)*ratio);
	    int num2 = num - num1;
	    for (int i=0;i<empty;i++){
	        list.add(2);
	    }
	    for (int i=0;i<num1;i++){
	        list.add(0);
	    }
	    for (int i=0;i<num2;i++){
	        list.add(1);
	    }
	    for (int i=0;i<grid.getGrid().size();i++){
	        int ran = (int) Math.floor(Math.random()*list.size());
	        grid.getGrid().get(i).setCurrentState(list.remove(ran));
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

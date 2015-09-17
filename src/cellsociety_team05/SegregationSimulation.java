package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
	private double similar; 
	private double ratio;
	private double empty;

	public SegregationSimulation(Setup setup, GridPane gridPane, GUI gui, double sim, double rat,double emp,Grid grid){
		super(setup, gridPane, gui);
		similar = sim;
		ratio = rat;
		empty = emp;
		setCellType(grid);
		initGrid(grid);
	}
	
	public SegregationSimulation(Setup setup){
	        super(setup,null,null);
	}
	
	//Need a way to set similarity percentage
	public void setSimilar(double x){
		similar = x;
	}
	
	public void setCellType(Grid grid){
	    for (List<Cell> list: grid.getGrid()){
	        for (Cell cell: list){
	            cell = new SegregationCell(cell.myXCoordinate,cell.myYCoordinate,0,similar);
	        }
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

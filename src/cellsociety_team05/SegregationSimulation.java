package cellsociety_team05;

import java.util.HashMap;

import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
	private double similar; 

	public SegregationSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> param){
		super(gridPane, gui, param);
		similar = param.get("similar");
	}
	
	
	//Need a way to set similarity percentage
	public void setSimilar(double x){
		similar = x;
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

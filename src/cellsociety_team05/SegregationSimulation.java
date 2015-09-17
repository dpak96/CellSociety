package cellsociety_team05;

import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
	private double similar; 

	public SegregationSimulation(Setup setup, GridPane gridPane, GUI gui, double sim){
		super(setup, gridPane, gui);
		similar = sim;
	}
	
	public SegregationSimulation(Setup setup){
	        super(setup,null,null);
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

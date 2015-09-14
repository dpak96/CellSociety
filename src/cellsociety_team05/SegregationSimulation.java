package cellsociety_team05;

public class SegregationSimulation extends Simulation {
	private Grid myGrid;
	private double similar; 

	public SegregationSimulation(Grid grid){
		super(grid);
		similar = 0;
	}
	
	//Need a way to set similarity percentage
	public void setSimilar(double x){
		similar = x;
	}
	
	@Override
	public void updateState(Cell cell) {
		int sim = 0;
		int tot = 0;
		for(Cell nCell: cell.getNeighbors()){
			++tot;
			if(nCell.myCurrentState == cell.myCurrentState){
				++sim;
			}
		}
		if((double)sim/tot < similar){
			//make cell empty and move it elsewhere
		}	
	}

}

package cellsociety_team05;

public class Setup {
	private Simulation[] myPossibleSimulations =
		{
			new SegregationSimulation(this)
		};
	
	public Setup(String file){
		//Read in XML file using file
	}
	
	public Grid initGrid(){
		Grid myGrid = new Grid();
		return myGrid;
		//Fill in with information of from XML file
	}
	
	//Need to find a way to change which simulation is being run w/o using too many if statements
	public void initSimulation(Grid grid){
		Simulation mySimulation = new SegregationSimulation(this);
	}
	
	public void reset(String file){
		Grid grid = initGrid();
		initSimulation(grid);
	}
}

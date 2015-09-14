package cellsociety_team05;

public abstract class Simulation {
	private Grid myGrid;
	private Setup mySetup;
	private int mySpeed; 

	public Simulation(Setup setup){
		mySetup = setup;
	}
	
	public void start(){
		//initiates the timeline loop
	}
	
	public void changeFlow(){
		//if the timeline is running, pause it
		//if the timeline is paused, resume it 
	}
	
	public void updateSpeed(int speed){
		mySpeed = speed;	
	}

	//single step of simulation First step would be updateState 
	//Second step would be updateGrid
	public void step(){
		myGrid.updateGrid();
	}
	
	//run steps continuously (take a boolean?)
	public void run(boolean canRun){
		while(canRun){
			step(); 
		}
	}
	
	//Will be implemented in subclasses
	public abstract void updateState(Cell cell);
	
	//calls drawGrid, displays graphically the Grid
	public void showGrid(){
		myGrid.drawGrid();
	}

}

package cellsociety_team05;
import javafx.scene.layout.GridPane;

public abstract class Simulation {
	//changed it to protected so i could extend it 
	protected Grid myGrid;
	private Setup mySetup;
	private int mySpeed; 

	public Simulation(Setup setup, GridPane gridPane){
		mySetup = setup;
		myGrid = new Grid(gridPane);
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
		myGrid.drawSquareGrid();
	}

}

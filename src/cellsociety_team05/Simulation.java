package cellsociety_team05;

<<<<<<< HEAD
public abstract class Simulation {
	private Grid myGrid;
	private int mySpeed; 

	public Simulation(Grid grid){
		myGrid = grid;
=======
public class Simulation {
	
	public Simulation(Setup mySetup){
		//creates new simulation according to the setup
	}
	
	public void start(){
		//initiates the timeline loop
>>>>>>> master
	}
	
	public void changeFlow(){
		//if the timeline is running, pause it
		//if the timeline is paused, resume it 
	}
	
<<<<<<< HEAD
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
=======
	public void nextStep(){
		
	}

>>>>>>> master
}

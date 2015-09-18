package cellsociety_team05;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class Simulation {
	//changed it to protected so i could extend it 
	protected Grid myGrid;
	protected int mySpeed; 
	private GridPane myGridPane;
	private GUI myGUI;
	public static final int FRAMES_PER_SECOND = 60;
	private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	private Timeline animation;
	protected HashMap<String, Double> myParameters;

	public Simulation(GridPane gridPane, GUI gui, HashMap<String, Double> params){
		animation = new Timeline();
		myGUI = gui;
		myParameters = params;
	}
	
	public void setGrid(Grid g){
	    myGrid = g;
	}
	
	public abstract Cell makeCell(int x, int y, int start, Grid g);

	public void start(){
		// sets the game's loop
	        animation = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(1000),
				e -> this.step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	public void changeFlow(){
		if(animation.getStatus().equals(Animation.Status.RUNNING)){
			animation.pause();
		}
		else if (animation.getStatus().equals(Animation.Status.PAUSED)){
		    animation.play();
		}
	}

	public void updateSpeed(int speed){
		mySpeed = speed;	
	}

	//single step of simulation First step would be updateState 
	//Second step would be updateGrid
	public void step(){
	    myGrid.preUpdateGrid();
	    myGrid.updateGrid();
	}
	
	public void stopAnimation(){
	    animation.stop();
	}

	//Will be implemented in subclasses
	public abstract void updateState(Cell cell);
	
	public abstract void setCellType(Grid grid);
	
	public abstract ArrayList<List<Cell>> setUpCells(Grid grid, int width, int height);
	
	public void initGrid(Grid grid){
	    
	}
}

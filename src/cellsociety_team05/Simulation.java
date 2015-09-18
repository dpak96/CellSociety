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

/**
 * 
 * @author emanuele macchi
 *
 */

public abstract class Simulation {
	protected Grid myGrid;
	protected double mySpeed; 
	protected GridPane myGridPane;
	protected GUI myGUI;
	private static final int MILLISECOND_DELAY = 1000;
	protected Timeline animation;
	protected HashMap<String, Double> myParameters;
	private boolean paused;
	private int myWidth;
	private int myHeight;

	public Simulation(GridPane gridPane, GUI gui, HashMap<String, Double> params, int height, int width){
		myGrid = new Grid(height, width, this);
		myWidth = width;
		myHeight = height;
		myGridPane = gridPane;
		animation = new Timeline();
		myGUI = gui;
		myParameters = params;
		initializeGridPane();
	}
	
	public abstract void setSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> params);
	
	public void setGrid(Grid g){
	    myGrid = g;
	}
	
	public abstract Cell makeCell(int x, int y, int start, Grid g);

	public void start(){
		KeyFrame frame = new KeyFrame(Duration.millis(1000),
				e -> this.step());
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		paused = false;
	}

	public void changeFlow(){
		if(paused){
			animation.play();
		} else {
			animation.pause();
		}
		paused = !paused;
	}

	public void updateSpeed(double speed){
		mySpeed = speed;	
		animation.setRate(2*(speed/10));
	}
	
	public void step(){
	    myGrid.preUpdateGrid();
		myGrid.updateGrid();
	}
	
	public void restart(){
		animation.stop();
		myGrid = new Grid(myHeight, myWidth,this);
		initializeGridPane();
		animation = new Timeline();
		start();
	}

	protected void initializeGridPane(){
		int row = 0; 
		for(List<Cell> listCell: myGrid.getCellMatrix()){
			int col = 0;
			for(Cell cell: listCell){
                myGridPane.getChildren().add(cell.getSquare());
                col++;
			}
			row++;
		}
	    myGrid.updateGrid();
	}
	
	public void stopAnimation(){
	    animation.stop();
	}

	public void updateState (Cell cell) {
	        myGrid.preUpdateGrid();
	}
	
	public abstract void setCellType(Grid grid);
	
	public abstract ArrayList<List<Cell>> setUpCells(Grid grid, int width, int height);
	
	public void initGrid(Grid grid){
	    
	}
}

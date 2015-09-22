package cellsociety_team05;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
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
	private List<CellInfo> myInfoList;

	public Simulation(GridPane gridPane, GUI gui, HashMap<String, Double> params,List<CellInfo> list, int height, int width){
		myWidth = width;
		setMyHeight(height);
                myInfoList = list;
                myParameters = params;
                myGridPane = gridPane;
                myGrid = new Grid(getMyHeight(), myWidth, this,myParameters);
                readCellList(myInfoList);
		animation = new Timeline();
		myGUI = gui;
		initializeGridPane();
	}
	
	public void readCellList(List<CellInfo> list){
	    if (list!=null){
	        for (CellInfo cell: list){
	            Cell thisCell = myGrid.getCellMatrix().get(cell.getX()).get(cell.getY());
	            thisCell.setCurrentState(cell.getState());
	            thisCell.mySquare.setFill(thisCell.myColors[thisCell.getCurrentState()]);
	        }
	    }
	}

	public void start(){
		KeyFrame frame = new KeyFrame(Duration.millis(1000),
				e -> this.step());
		animation.getKeyFrames().add(frame);
		animation.setCycleCount(Timeline.INDEFINITE);
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

	protected void initializeGridPane(){
		for(List<Cell> listCell: myGrid.getCellMatrix()){
			for(Cell cell: listCell){
                            myGridPane.getChildren().add(cell.getSquare());
			}
		}
	}
	
	public void stopAnimation(){
	    animation.stop();
	}

	public abstract ArrayList<List<Cell>> setUpCells(Grid grid, int width, int height,HashMap<String, Double> map);

	public void initGrid(){}

	public abstract Cell makeCell(int x, int y, int start, Grid g,HashMap<String, Double> map);

	public int getMyHeight () {
	    return myHeight;
	}
	public void setMyHeight (int myHeight) {
	    this.myHeight = myHeight;
	}

}

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
	private List<CellInfo> myInfoList;

	public Simulation(GridPane gridPane, GUI gui, HashMap<String, Double> params,List<CellInfo> list, int height, int width){
		myWidth = width;
		myHeight = height;
                myInfoList = list;
                myParameters = params;
                myGrid = new Grid(myHeight, myWidth, this);
                readCellList(myInfoList);
		myGridPane = gridPane;
		animation = new Timeline();
		myGUI = gui;
		initializeGridPane();
	}
	/*
	public void setGrid(Grid g){
	    myGrid = g;
	}
	*/
	public void readCellList(List<CellInfo> list){
	    if (list!=null){
	        System.out.println("HIT");
	        for (CellInfo cell: list){
	            Cell thisCell = myGrid.getCellMatrix().get(cell.getX()).get(cell.getY());
	            thisCell.setCurrentState(cell.getState());
	            thisCell.mySquare.setFill(thisCell.myColors[thisCell.getCurrentState()]);
	        }
	    }
	}

	public void start(){
	        for (List<Cell> list: myGrid.getCellMatrix()){
	            for (Cell cell: list){
	                System.out.println("("+cell.getX()+","+cell.getY()+") "+cell.getCurrentState());
	            }
	        }
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
	    System.out.println("step");
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
		for(List<Cell> listCell: myGrid.getCellMatrix()){
			for(Cell cell: listCell){
                            myGridPane.getChildren().add(cell.getSquare());
			}
		}
	}
	
	public void stopAnimation(){
	    animation.stop();
	}
	/*
	public abstract void setCellType(Grid grid);
	*/
	public abstract ArrayList<List<Cell>> setUpCells(Grid grid, int width, int height);
	
	public void initGrid(){}
	
	public abstract Cell makeCell(int x, int y, int start, Grid g);
	
}

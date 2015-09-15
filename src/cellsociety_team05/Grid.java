package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class Grid {
    
    List<Cell> myCells;
    ArrayList<SegregationCell> mySegregationCells = new ArrayList<SegregationCell>();
    GridPane myGridPane;
    
    /**
     * Constructor method
     * @author Emanuele
     */
    public Grid(GridPane gridPane){
    	myGridPane = gridPane;
    }
    
    public Grid(int width, int height){
        myCells = new ArrayList<Cell>();
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*2);
                Cell newcell = new Cell(this,j,i,state);
                myCells.add(newcell);
            }
        }
        for (Cell cell: myCells){
            cell.initNeighbors();
        }
    }
    
    /**
     * Sets the next state of each cell in the grid.
     */
    public void preUpdateGrid(){
        for(Cell currentCell: myCells){
            currentCell.preUpdateCell();
        }
    }
    
    /**
     * Switches the state of each cell in the grid to it's next state.
     */
    public void updateGrid(){
        for(Cell currentCell: myCells){
            currentCell.updateCell();
        }
    }
    
    /**
     * 
     * @return A list of the cells in the grid.
     */
    public List<Cell> getGrid(){
        return myCells;
    }

    /**
     * Display the Grid
     * By calling the X and Y coordinates of the cell, it decides where to position them on the grid.
     * This will work only for squares, I am starting of thinking of flexibility 
     * to implement other shapes, say for example an L shaped area
     * @author Emanuele
     */
    
    public void drawSquareGrid(){
        //need communication with GUI
    	double squareSide = myGridPane.getHeight() / Math.sqrt(mySegregationCells.size());
    	ArrayList<Rectangle> newSquares = new ArrayList<Rectangle>();
    	for(SegregationCell cell: mySegregationCells){
    		Rectangle square = new Rectangle(squareSide, squareSide, cell.getColor());
    		GridPane.setConstraints(square, cell.getX(), cell.getY());
    		newSquares.add(square);
    	}
    	myGridPane.getChildren().addAll(newSquares);
    }
    
    /**
     * TESTER FOR DRAW GRID METHOD
     * @author Emanuele
     */
    public void drawGridTester(){
    	SegregationCell[] cellList = {new SegregationCell(this, 0, 0, 0, 1.0),new SegregationCell(this, 0, 1, 1, 1.0),new SegregationCell(this, 0, 2, 0, 1.0),
    			new SegregationCell(this, 1, 0, 1, 1.0), new SegregationCell(this, 1, 1, 0, 1.0), new SegregationCell(this, 1, 2, 1, 1.0),
    			new SegregationCell(this, 2, 0, 0, 1.0), new SegregationCell(this, 2, 1, 1, 1.0), new SegregationCell(this, 2, 2, 0, 1.0)};
    	for(SegregationCell c: cellList){
    		mySegregationCells.add(c);
    	}
    }
    

}

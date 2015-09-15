package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class Grid {
    
    List<Cell> myCells;
    
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
     * Draws this grid's cells on the GUI.
     */
    public void drawGrid(){
        //need communication with GUI
    }

}

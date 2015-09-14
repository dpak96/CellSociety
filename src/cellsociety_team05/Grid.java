package cellsociety_team05;

import java.util.List;

/**
 * 
 * @author Lucas Donaldson
 *
 */
public class Grid {
    List<Cell> myCells;
    
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

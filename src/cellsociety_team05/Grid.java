package cellsociety_team05;

import java.util.List;

public class Grid {
    List<Cell> myCells;
    
    public void updateGrid(){
        for(Cell currentCell: myCells){
            currentCell.preUpdateCell();
            //Simulation.preUpdateCell(currentCell);
        }
        for(Cell currentCell: myCells){
            currentCell.updateCell();
        }
    }
    
    public List<Cell> getGrid(){
        return myCells;
    }
    
    public void drawGrid(){
        //need communication with GUI
    }

}

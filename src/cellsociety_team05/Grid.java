package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    List<List<Cell>> myCells;
    GridPane myGridPane;
    
    public Grid(int width, int height, Simulation sim){
        myCells = sim.setUpCells(this, width, height);
        initNeighbors();
    }
    
    public Grid(int width, int height, List<Cell> list, Simulation sim){
        myCells = new ArrayList<List<Cell>>();
        for (int i=0;i<width;i++){
            myCells.add(new ArrayList<Cell>());
            for (int j=0;j<height;j++){
                int state = (int) Math.floor(Math.random()*2);
                Cell newcell = new GameOfLifeCell(i,j,state);
                myCells.get(i).add(newcell);
            }
        }
        initNeighbors();
        for (Cell cell: list){
            myCells.get(cell.getX()).set(cell.getY(), cell);
        }
    }
    
    public void linkGridPane(GridPane gp){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                gp.getChildren().add(cell.getSquare());
            }
        }
    }
    
    private void initNeighbors(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                //System.out.println("Cell: ("+cell.getX()+","+cell.getY()+")");
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = {0,0,1,1,1,-1,-1,-1};
                int[] y = {1,-1,0,1,-1,0,1,-1};
                for (int i=0;i<x.length;i++){
                    int xCoordinate = cell.getX()+x[i];
                    int yCoordinate = cell.getY()+y[i];
                    if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myCells.size() && yCoordinate<myCells.get(0).size()){
                        neighbors.add(myCells.get(xCoordinate).get(yCoordinate));
                        //System.out.println("neighbor: ("+myCells.get(xCoordinate).get(yCoordinate).getX()+","+myCells.get(xCoordinate).get(yCoordinate).getY()+")");
                    }
                }
                cell.setNeighbors(neighbors);
            }
        }
    }
    
    /**
     * Sets the next state of each cell in the grid.
     */
    public void preUpdateGrid(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                //System.out.println(cell.getClass().toString());
                cell.preUpdateCell();
            }
        }
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                //System.out.println(cell.getClass().toString());
                cell.myDirty = false;
            }
        }
    }
    
    public void printType(){
        System.out.println(myCells.get(0).get(0).getClass().toString());
    }
    
    /**
     * Switches the state of each cell in the grid to it's next state.
     */
    public void updateGrid(){
        for (List<Cell> list: myCells){
            for (Cell cell: list){
                cell.updateCell();
            }
        }
    }
    
    /**
     * 
     * @return A list of the cells in the grid.
     */
    public List<List<Cell>> getCellMatrix(){
        return myCells;
    }

    /**
     * Display the Grid
     * By calling the X and Y coordinates of the cell, it decides where to position them on the grid.
     * This will work only for squares, I am starting of thinking of flexibility 
     * to implement other shapes, say for example an L shaped area
     * @author Emanuele
     */
    
    /*public void drawSquareGrid(){
        //need communication with GUI
        double squareSide = myGridPane.getHeight() / Math.sqrt(mySegregationCells.size());
        ArrayList<Rectangle> newSquares = new ArrayList<Rectangle>();
        for(SegregationCell cell: mySegregationCells){
                Rectangle square = new Rectangle(squareSide, squareSide, cell.getColor());
                GridPane.setConstraints(square, cell.getX(), cell.getY());
                newSquares.add(square);
        }
        myGridPane.getChildren().addAll(newSquares);
    }*/
    
    


}
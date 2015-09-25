package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.GridPane;

public class AntForagingSimulation extends Simulation{
	
	private List<Ant> myAnts = new ArrayList<Ant>();
	private List<AntCell> myNests;
	private int numberOfAnts;
	private int maxNumberOfAnts = 1;

	public AntForagingSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> params, 
			List<CellInfo> list, int height,int width) {
		super(gridPane, gui, params, list, height, width);
		// TODO Auto-generated constructor stub
		numberOfAnts = 0;
	}
	
	public void initNeighbors(){
        for (List<Cell> list: myGrid.getCellMatrix()){
            for (Cell cell: list){
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = {0,0,1,-1,1,-1,1,-1};
                int[] y = {1,-1,0,0,1,-1,-1, 1};
                for (int i=0;i<x.length;i++){
                    int xCoordinate = cell.getX()+x[i];
                    int yCoordinate = cell.getY()+y[i];
                    if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myGrid.getCellMatrix().size() && yCoordinate<myGrid.getCellMatrix().get(0).size()){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(yCoordinate));
                    }
                    else if(xCoordinate>=myGrid.getCellMatrix().size()){
                        neighbors.add(myGrid.getCellMatrix().get(0).get(yCoordinate));
                    }
                    else if(xCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(myGrid.getCellMatrix().size()-1).get(yCoordinate));
                    }
                    else if(yCoordinate>=myGrid.getCellMatrix().get(0).size()){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(0));
                    }
                    else if(yCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(myGrid.getCellMatrix().get(0).size()-1));
                    }
                }
                cell.setMyNeighbors(neighbors);
            }
        }
    }
	private int count = 1;
	@Override
	public void step(){
		moveAnts();
	    myGrid.preUpdateGrid();
	    myGrid.updateGrid();
	    updateStats();
	    myGUI.updateGraph();
	    System.out.println(count + " - number of steps");
	    System.out.println(myAnts.size() + " - number of ants");
	    System.out.println();
	    count++;
	}
	

	@Override
	public AntCell makeCell (int x, int y, int start, Grid g, HashMap<String,Double> map) {
	        //myGrid = g;
	        AntCell c = new AntCell(x,y,start,map,myGrid,this);
	        return c;
	    }
	
	@Override
	public ArrayList<List<Cell>> setUpCells (Grid grid, int width, int height, HashMap<String,Double> map) {
	    ArrayList<List<Cell>> list = new ArrayList<List<Cell>>();
	    
	    for (int i=0;i<width;i++){
	        list.add(new ArrayList<Cell>());
	        for (int j=0;j<height;j++){
	            AntCell newcell = makeCell(i, j, 0, grid,map);
	            list.get(i).add(newcell);
	        }
	    }

	    return list;
	}
	
	public boolean checkIfNotTooManyAnts(){
		return (numberOfAnts < maxNumberOfAnts);
	}
	
	public void increaseNumOfAnts(){
		numberOfAnts++;
	}
	
	public void addAnt(Ant ant){
		increaseNumOfAnts();
		myAnts.add(ant);
	}
	
	private void moveAnts(){
		 for(Ant a: myAnts){
			 a.forage();
		 }
	}
	
}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.GridPane;

public class AntForagingSimulation extends Simulation{
	
	private ArrayList<Ant> myAnts;

	public AntForagingSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> params, List<CellInfo> list, int height,
			int width) {
		super(gridPane, gui, params, list, height, width);
		// TODO Auto-generated constructor stub
		AntGrid myGrid = new AntGrid(width, height, this, params);
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
	            AntCell newcell = makeCell(i, j, 1, grid,map);
	            list.get(i).add(newcell);
	        }
	    }
	    return list;
	}
	
	public void moveAntsAround(){
		for(Ant ant: myAnts){
			ant.forage();
		}
	}
	
	private void generateNewAnts(){
		
	}

}

package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.layout.GridPane;

public class AntForagingSimulation extends Simulation{

	public AntForagingSimulation(GridPane gridPane, GUI gui, HashMap<String, Double> params, List<CellInfo> list,
			int height, int width) {
		super(gridPane, gui, params, list, height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<List<Cell>> setUpCells(Grid grid, int width, int height, HashMap<String, Double> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cell makeCell(int x, int y, int start, Grid g, HashMap<String, Double> map) {
		// TODO Auto-generated method stub
		return null;
	}

}

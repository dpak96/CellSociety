package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;

public class SegregationSimulation extends Simulation {

	private double similar;
	private double ratio;
	private double empty;

	/**
	 * constructor for segregation simulation
	 *
	 * @param gridPane
	 * @param gui
	 * @param param
	 * @param list
	 * @param height
	 * @param width
	 */
	public SegregationSimulation(GridPane gridPane, GUI gui,
			Map<String, Double> param, List<CellInfo> list, int height,
			int width, String shape) {
		super(gridPane, gui, param, list, height, width, shape);
		try {
			similar = param.get("similar");
		} catch (Exception e) {
			try {
				similar = 0.5;
			} catch (Exception ee) {
				return;
			}
		}
		try {
			ratio = param.get("ratio");
		} catch (Exception e) {
			try {
				ratio = 0.5;
			} catch (Exception ee) {
				return;
			}
		}
		try {
			empty = param.get("empty");
		} catch (Exception e) {
			try {
				empty = 0.5;
			} catch (Exception ee) {
				return;
			}
		}
		if (list == null) {
			initGrid();
		}
	}

	/**
	 * sets parameter for percent of similar neighbors required for satisfaction
	 *
	 * @param x
	 */
	public void setSimilar(double x) {
		similar = x;
	}

	/**
	 * initializes grid randomly with appropriate number of blue, red, and empty
	 * cells
	 */
	@Override
	public void initGrid() {
		int num = myGrid.getCellMatrix().size()
				* myGrid.getCellMatrix().get(0).size();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int numEmpty = (int) Math.floor((num) * empty);
		num -= numEmpty;
		int num1 = (int) Math.floor((num) * ratio);
		int num2 = num - num1;
		for (int i = 0; i < numEmpty; i++) {
			list.add(2);
		}
		for (int i = 0; i < num1; i++) {
			list.add(0);
		}
		for (int i = 0; i < num2; i++) {
			list.add(1);
		}
		for (List<Cell> cells : myGrid.getCellMatrix()) {
			for (Cell cell : cells) {
				int ran = (int) Math.floor(Math.random() * list.size());
				cell.setParameter("similar", similar);
				cell.setMyCurrentState(list.remove(ran));
				try {
					cell.changeColor();
				} catch (SimulationException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * overrides super class method
	 */
	@Override
	public Cell makeCell(int x, int y, int start, Grid g,
			Map<String, Double> map) {
		myGrid = g;
		SegregationCell c = new SegregationCell(x, y, start, map, myGrid, this);
		return c;
	}

	/**
	 * Initiate the grid with preset cells.
	 */
	@Override
	public List<List<Cell>> setUpCells(Grid grid, int width, int height,
			Map<String, Double> map) {
		List<List<Cell>> list = new ArrayList<List<Cell>>();
		for (int i = 0; i < width; i++) {
			list.add(new ArrayList<Cell>());
			for (int j = 0; j < height; j++) {
				Cell newcell = makeCell(i, j, 1, grid, map);
				list.get(i).add(newcell);
			}
		}
		return list;
	}

	@Override
	public String getName() {
		return "Segregation";
	}
}

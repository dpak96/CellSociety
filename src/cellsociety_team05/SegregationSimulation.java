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
			int width) {
		super(gridPane, gui, param, list, height, width);
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
	public List<List<Cell>> setUpCells() {
		List<List<Cell>> list = new ArrayList<List<Cell>>();
		for (int i = 0; i < myWidth; i++) {
			list.add(new ArrayList<Cell>());
			for (int j = 0; j < myHeight; j++) {
				Cell newcell = makeCell(i, j, 1, myGrid, myParameters);
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

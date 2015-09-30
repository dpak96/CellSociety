package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.layout.GridPane;

public class FireSimulation extends Simulation {
	private double probCatch;

	/**
	 * constructor for fire simulation class
	 *
	 * @param gridPane
	 * @param gui
	 * @param params
	 * @param list
	 * @param height
	 * @param width
	 */
	public FireSimulation(GridPane gridPane, GUI gui,
			Map<String, Double> params, List<CellInfo> list, int height,
			int width) {
		super(gridPane, gui, params, list, height, width);
		try {
			probCatch = params.get("probCatch");
		} catch (Exception e) {
			try {
				probCatch = 0.5;
			} catch (Exception ee) {
				return;
			}
		}
	}

	/**
	 * overrides super class method
	 */
	@Override
	public Cell makeCell(int x, int y, int start, Grid g,
			Map<String, Double> map) {
		FireCell c = new FireCell(x, y, start, this);
		c.setProb(probCatch);
		return c;
	}

	/**
	 * overrides super class method
	 */
	@Override
	public List<List<Cell>> setUpCells() {
		List<List<Cell>> list = new ArrayList<List<Cell>>();
		for (int i = 0; i < myWidth; i++) {
			list.add(new ArrayList<Cell>());
			for (int j = 0; j < myHeight; j++) {
				int state = (int) (Math.random() * 3);
				Cell newcell = makeCell(i, j, state, myGrid, myParameters);
				list.get(i).add(newcell);
			}
		}
		return list;
	}

	@Override
	public String getName() {
		return "Fire";
	}
}

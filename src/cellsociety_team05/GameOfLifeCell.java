package cellsociety_team05;

import javafx.scene.paint.Color;

public class GameOfLifeCell extends Cell {

	/**
	 * constructor for game of life cell
	 *
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param startingState
	 * @param sim
	 */
	public GameOfLifeCell(int xCoordinate, int yCoordinate, int startingState,
			Simulation sim) {
		super(xCoordinate, yCoordinate, startingState);
		myColors = new Color[] { Color.BLACK, Color.WHITE };
		myPossibleStates = new String[] { "Alive", "Dead" };
		setMyShape(440 / sim.getMyHeight(), myColors[startingState],
				sim.getShape());
	}

	/**
	 * overrides super class method
	 */
	@Override
	public void preUpdateCell() {
		int liveNeighbors = 0;
		// System.out.println("Cell: ("+getX()+","+getY()+")");
		for (Cell neighbor : myNeighbors) {
			// System.out.println("neighbor: ("+neighbor.getX()+","+neighbor.getY()+")
			// "+neighbor.getPossibleStates()[neighbor.getCurrentState()]);
			if (neighbor.myCurrentState == 0) {
				liveNeighbors += 1;
			}
		}
		myNextState = myCurrentState;
		if (myCurrentState == 0) {
			if (liveNeighbors < 2) {
				myNextState = 1;
			} else if (liveNeighbors > 3) {
				myNextState = 1;
			}
		} else if (myCurrentState == 1) {
			if (liveNeighbors == 3) {
				myNextState = 0;
			}
		}
	}
}

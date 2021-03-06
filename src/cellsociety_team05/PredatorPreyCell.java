package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell {
	private Grid myGrid;
	private int PREY_REPRODUCTION_TIME;
	private int PREDATOR_REPRODUCTION_TIME;
	private int PREDATOR_ENERGY;
	private PredatorPreySimulation mySim;

	/**
	 * constructor for predator prey cell
	 *
	 * @param xCoordinate
	 * @param yCoordinate
	 * @param startingState
	 * @param param
	 * @param g
	 * @param sim
	 */
	public PredatorPreyCell(int xCoordinate, int yCoordinate,
			int startingState, Map<String, Double> param, Grid g,
			PredatorPreySimulation sim) {
		super(xCoordinate, yCoordinate, startingState);
		PREY_REPRODUCTION_TIME = (int) Math.round(param
				.get("preyreproductiontime"));
		PREDATOR_REPRODUCTION_TIME = (int) Math.round(param
				.get("predatorreproductiontime"));
		PREDATOR_ENERGY = (int) Math.round(param.get("energylimit"));
		myDirty = false;
		mySim = sim;
		myGrid = g;
		myPossibleStates = new String[] { "Fish/Blue", "Shark/Red", "Empty" };
		myColors = new Color[] { Color.BLUE, Color.RED, Color.WHITE };
		setMyShape(440 / sim.getMyHeight(), myColors[startingState],
				sim.getShape());
	}

	/**
	 * initializes neighbors for toroidal grid
	 */
	public void initNeighbors() {
		for (List<Cell> list : myGrid.getCellMatrix()) {
			for (Cell cell : list) {
				List<Cell> neighbors = new ArrayList<Cell>();
				int[] x = { 0, 0, 1, -1 };
				int[] y = { 1, -1, 0, 0 };
				for (int i = 0; i < x.length; i++) {
					int xCoordinate = cell.getX() + x[i];
					int yCoordinate = cell.getY() + y[i];
					if (xCoordinate >= 0
							&& yCoordinate >= 0
							&& xCoordinate < myGrid.getCellMatrix().size()
							&& yCoordinate < myGrid.getCellMatrix().get(0)
									.size()) {
						neighbors.add(myGrid.getCellMatrix().get(xCoordinate)
								.get(yCoordinate));
					} else if (xCoordinate >= myGrid.getCellMatrix().size()) {
						neighbors.add(myGrid.getCellMatrix().get(0)
								.get(yCoordinate));
					} else if (xCoordinate < 0) {
						neighbors.add(myGrid.getCellMatrix()
								.get(myGrid.getCellMatrix().size() - 1)
								.get(yCoordinate));
					} else if (yCoordinate >= myGrid.getCellMatrix().get(0)
							.size()) {
						neighbors.add(myGrid.getCellMatrix().get(xCoordinate)
								.get(0));
					} else if (yCoordinate < 0) {
						neighbors.add(myGrid.getCellMatrix().get(xCoordinate)
								.get(myGrid.getCellMatrix().get(0).size() - 1));
					}
				}
				cell.setMyNeighbors(neighbors);
			}
		}
	}

	/**
	 * transfers state of this cell to new cell
	 *
	 * @param cell
	 */
	private void moveTo(Cell cell) {
		cell.myNextState = myCurrentState;
		cell.setMyCurrentState(cell.myNextState);
		mySim.getMyReproductionTimes()[cell.getX()][cell.getY()] = mySim
				.getMyReproductionTimes()[getX()][getY()];
		mySim.getMyEnergies()[cell.getX()][cell.getY()] = mySim.getMyEnergies()[getX()][getY()];
		cell.myDirty = true;
	}

	/**
	 * transfers state of this cell to prey and resets energy
	 *
	 * @param prey
	 */
	private void eat(Cell prey) {
		moveTo(prey);
		mySim.getMyEnergies()[prey.getX()][prey.getY()] = 0;
		age(prey);
	}

	/**
	 * checks if given cell is out of energy
	 *
	 * @param cell
	 */
	private void checkDeath(Cell cell) {
		if (mySim.getMyEnergies()[cell.getX()][cell.getY()] >= PREDATOR_ENERGY + 1) {
			cell.myNextState = 2;
			cell.myCurrentState = 2;
			mySim.getMyReproductionTimes()[cell.getX()][cell.getY()] = 0;
			mySim.getMyEnergies()[cell.getX()][cell.getY()] = 0;
		}
	}

	/**
	 * increments cell's energy and reproduction timer by one
	 *
	 * @param cell
	 */
	private void age(Cell cell) {
		mySim.getMyEnergies()[cell.getX()][cell.getY()] = mySim.getMyEnergies()[cell
				.getX()][cell.getY()] + 1;
		mySim.getMyReproductionTimes()[cell.getX()][cell.getY()] = mySim
				.getMyReproductionTimes()[cell.getX()][cell.getY()] + 1;
	}

	/**
	 * clears state of current cell
	 */
	private void leave(int time) {
		if (myCurrentState != 2) {
			if (mySim.getMyReproductionTimes()[getX()][getY()] >= time) {
				myNextState = myCurrentState;
				mySim.getMyReproductionTimes()[getX()][getY()] = 0;
				mySim.getMyEnergies()[getX()][getY()] = 0;
			} else {
				myNextState = 2;
				mySim.getMyReproductionTimes()[getX()][getY()] = 0;
				mySim.getMyEnergies()[getX()][getY()] = 0;
			}
		} else {
			myNextState = 2;
			mySim.getMyReproductionTimes()[getX()][getY()] = 0;
			mySim.getMyEnergies()[getX()][getY()] = 0;
		}
		myCurrentState = myNextState;
	}

	/**
	 * overrides super class method
	 */
	@Override
	public void preUpdateCell() {
		myNextState = myCurrentState;
		if (!myDirty) {
			if (myCurrentState == 1) {
				List<Cell> fish = new ArrayList<Cell>();
				List<Cell> empties = new ArrayList<Cell>();
				for (Cell cell : myNeighbors) {
					if (cell.getMyCurrentState() == 0) {
						fish.add(cell);
					} else if (cell.getMyCurrentState() == 2) {
						empties.add(cell);
					}
				}
				if (fish.size() > 0) {
					int ran = (int) Math.floor(Math.random() * fish.size());
					Cell eatCell = fish.get(ran);
					eat(eatCell);
					if (mySim.getMyReproductionTimes()[getX()][getY()] >= PREDATOR_REPRODUCTION_TIME) {
						mySim.getMyReproductionTimes()[eatCell.getX()][eatCell
								.getY()] = 0;
					}
					leave(PREDATOR_REPRODUCTION_TIME);
					checkDeath(eatCell);
				} else if (empties.size() > 0) {
					int ran = (int) Math.floor(Math.random() * empties.size());
					Cell moveCell = empties.get(ran);
					moveTo(moveCell);
					if (mySim.getMyReproductionTimes()[getX()][getY()] >= PREDATOR_REPRODUCTION_TIME) {
						mySim.getMyReproductionTimes()[moveCell.getX()][moveCell
								.getY()] = 0;
					}
					leave(PREDATOR_REPRODUCTION_TIME);
					age(moveCell);
					checkDeath(moveCell);
				}
			}

			else if (myCurrentState == 0) {
				List<Cell> empties = new ArrayList<Cell>();
				for (Cell cell : myNeighbors) {
					if (cell.getMyCurrentState() == 2) {
						empties.add(cell);
					}
				}
				if (empties.size() > 0) {
					int ran = (int) Math.floor(Math.random() * empties.size());
					Cell moveCell = empties.get(ran);
					moveTo(moveCell);
					if (mySim.getMyReproductionTimes()[getX()][getY()] >= PREY_REPRODUCTION_TIME) {
						mySim.getMyReproductionTimes()[moveCell.getX()][moveCell
								.getY()] = 0;
					}
					leave(PREY_REPRODUCTION_TIME);
					age(moveCell);
				}
			}
		}
	}
}

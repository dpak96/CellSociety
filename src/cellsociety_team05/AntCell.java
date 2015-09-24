package cellsociety_team05;

import java.util.HashMap;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AntCell extends Cell {
	
	//three possible states: 0 = nothing, 1 = isNest, 2 = isFood, 3 = hasAnt;
	
	public final int  isNest = 1;
	public final int  isFood = 2;
	public final int  hasAnt = 3;
	private AntForagingSimulation mySimulation;
	
	public AntCell(int xCoordinate, int yCoordinate, int startingState, 
			HashMap<String, Double> map,Grid grid, AntForagingSimulation sim) {
		super(xCoordinate, yCoordinate, startingState);
		// TODO Auto-generated constructor stub
		myColors = new Color[] {Color.WHITE, Color.BROWN, Color.BLACK, Color.RED};
        myPossibleStates = new String[] {"Nothing", "isNest", "isFood", "hasAnt"};
		this.setMySquare(new Rectangle(440/sim.getMyHeight(), 440/sim.getMyHeight(), myColors[startingState]));
	}

	@Override
	public void preUpdateCell() {
		// TODO Auto-generated method stub
		if(this.getMyCurrentState() == hasAnt){
			mySquare.setFill(myColors[hasAnt]);
		} else if (this.getMyCurrentState() == isNest){
			mySquare.setFill(myColors[isNest]);
		} else if (this.getMyCurrentState() == isFood){
			mySquare.setFill(myColors[isFood]);
		} else {
			mySquare.setFill(myColors[0]);
		}
	}
	
	public void AntArrives(){
		this.setMyCurrentState(hasAnt);
	}

}

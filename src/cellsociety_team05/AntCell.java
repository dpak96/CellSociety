package cellsociety_team05;

import java.util.HashMap;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class AntCell extends Cell {
	
	//three possible states: 0 = nothing, 1 = isNest, 2 = isFood, 3 = hasAnt;
	
	public final int  isNest = 1;
	public final int  isFood = 3;
	public final int  hasAnt = 2;
	private int foodPheromones;
	private int homePheromones;
	private AntForagingSimulation mySimulation;
	private Ant currentAnt;
	
	public AntCell(int xCoordinate, int yCoordinate, int startingState, 
			HashMap<String, Double> map,Grid grid, AntForagingSimulation sim) {
		super(xCoordinate, yCoordinate, startingState);
		// TODO Auto-generated constructor stub
		mySimulation = sim;
		myColors = new Color[] {Color.WHITE, Color.BROWN, Color.BLACK, Color.RED};
        myPossibleStates = new String[] {"Nothing", "isNest", "isFood", "hasAnt"};
        
        //hardcoded height, we'll have to pass in the parameters
		this.setMySquare(new Rectangle(440/sim.getMyHeight(), 440/sim.getMyHeight(),myColors[startingState]));
		GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
	}

	@Override
	public void preUpdateCell() {
		
		if(this.myNextState == hasAnt && currentAnt == null){
			System.out.println("NOT WHAT I WNAN");
			AntArrives(new Ant(this));
		}
		
		if(this.myCurrentState == isNest && mySimulation.checkIfNotTooManyAnts()){
			System.out.println("NEW ANT SHOULD BE CREATED");
			createAnt();
		}
	}

	public void AntArrives(Ant ant){
		currentAnt = ant;
		this.setMyNextState(hasAnt);
		//System.out.println(this.getMyNextState()+ " - myCurrentState");
	}
	
	public void createAnt(){
		currentAnt = new Ant(this);
		mySimulation.addAnt(currentAnt);
		//this.setMyCurrentState(hasAnt);
	}
	
	public void antLeaves(){
		System.out.println("THE ANT LEFT");
		if(this.getMyCurrentState() != isNest){
			this.setMyNextState(0);
			currentAnt  = null;
		}
	}
	
	public int getHomePheromones(){
		return homePheromones;
	}
	
	public int getFoodPheromones(){
		return foodPheromones;
	}
	
	public boolean isFree(){
		return (this.getMyCurrentState() == 0);
	}

}

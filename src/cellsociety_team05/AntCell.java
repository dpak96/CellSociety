package cellsociety_team05;

import java.util.HashMap;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import java.util.Map;
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
			Map<String, Double> map,Grid grid, AntForagingSimulation sim) {
		super(xCoordinate, yCoordinate, startingState);
		// TODO Auto-generated constructor stub
		mySimulation = sim;
		myColors = new Color[] {Color.WHITE, Color.BROWN, Color.BLACK, Color.RED};
        myPossibleStates = new String[] {"Nothing", "isNest", "isFood", "hasAnt"};
        if(startingState == isNest){
        	homePheromones = 1000;
        } else if (startingState == isFood){
        	foodPheromones = 1000;
        } else {
        	homePheromones = 0;
        	foodPheromones = 0;
        }
		this.setMySquare(new Rectangle(440/sim.getMyHeight(), 440/sim.getMyHeight(),myColors[startingState]));
		GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
	}

    @Override
	public void preUpdateCell() {
		
		//safety check 
        if(myCurrentState == isNest){
        	homePheromones = 1000;
        } else if (myCurrentState == isFood){
        	foodPheromones = 1000;
        }
		if(this.myNextState == hasAnt && currentAnt == null){
			AntArrives(new Ant(this));
		}
		
		if(this.myCurrentState == isNest && mySimulation.checkIfNotTooManyAnts()){
			createAnt();
		}
	}

	public void AntArrives(Ant ant){
		if(this.myCurrentState == isNest){
			return;
		}
		currentAnt = ant;
		this.setMyNextState(hasAnt);

	}
	
	public void createAnt(){
		currentAnt = new Ant(this);
		mySimulation.addAnt(currentAnt);
	}
	
	public void antLeaves(){
		if(this.getMyCurrentState() != isNest && this.getMyCurrentState() != isFood){
			this.setMyNextState(0);
			currentAnt  = null;
		}
	}
	
	public void addHomePheromones(int value){
		if(value > homePheromones){
			homePheromones = value;
		}
	}
	
	public void addFoodPheromones(int value){
		if(value > foodPheromones){
			foodPheromones = value;
		}
	}
	
	public int getHomePheromones(){
		return homePheromones;
	}
	
	public int getFoodPheromones(){
		return foodPheromones;
	}
	
	public boolean isFree(){
		return !(this.getMyCurrentState() == hasAnt);
	}
}

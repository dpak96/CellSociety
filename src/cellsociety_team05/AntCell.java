package cellsociety_team05;

import java.util.Map;
import javafx.scene.paint.Color;


public class AntCell extends Cell {

    // three possible states: 0 = nothing, 1 = isNest, 2 = isFood, 3 = hasAnt;

    public final int isNest = 1;
    public final int hasAnt = 2;
    public final int isFood = 3;
    private int foodPheromones;
    private int homePheromones;
    private AntForagingSimulation mySimulation;
    private Ant currentAnt;

    public AntCell (int xCoordinate,
                    int yCoordinate,
                    int startingState,
                    Map<String, Double> map,
                    Grid grid,
                    AntForagingSimulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        mySimulation = sim;
        myColors = new Color[] { Color.WHITE, Color.BROWN, Color.BLACK, Color.RED };
        myPossibleStates = new String[] { "Nothing", "isNest", "isFood", "hasAnt" };
        if (startingState == isNest) {
            homePheromones = 1000;
        }
        else if (startingState == isFood) {
            foodPheromones = 1000;
            myNextState = startingState;
        }
        else {
            homePheromones = 0;
            foodPheromones = 0;
        }
        setMyShape(440 / sim.getMyHeight(), myColors[startingState], sim.getShape());
    }

    @Override
    public void preUpdateCell () {

        // safety check
        if (myCurrentState == isNest) {
            homePheromones = 1000;
            myNextState = myCurrentState;
        }
        else if (myCurrentState == isFood) {
            foodPheromones = 1000;
            myNextState = myCurrentState;
        }
        if (myNextState == hasAnt && currentAnt == null) {
            AntArrives(new Ant(this));
        }

        if (myCurrentState == isNest && mySimulation.checkIfNotTooManyAnts()) {
            createAnt();
        }
    }

    public void AntArrives (Ant ant) {
        if (myCurrentState == isNest) {
            return;
        }
        currentAnt = ant;
        setMyNextState(hasAnt);

    }

    public void createAnt () {
        currentAnt = new Ant(this);
        mySimulation.addAnt(currentAnt);
    }

    public void antLeaves () {
        if (getMyCurrentState() != isNest && getMyCurrentState() != isFood) {
            setMyNextState(0);
            currentAnt = null;
        }
    }

    public void addHomePheromones (int value) {
        if (value > homePheromones) {
            homePheromones = value;
        }
    }

    public void addFoodPheromones (int value) {
        if (value > foodPheromones) {
            foodPheromones = value;
        }
    }

    public int getPheromones (boolean food) {
        if (food) {
            return foodPheromones;
        }
        else {
            return homePheromones;
        }
    }

    public void addPheromones (boolean food, int value) {
        if (food) {
            addFoodPheromones(value);
        }
        else {
            addHomePheromones(value);
        }
    }

    public boolean isFree () {
        return (currentAnt == null);
    }
}

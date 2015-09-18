package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PredatorPreyCell extends Cell {
    private Grid myGrid;
    int PREY_REPRODUCTION_TIME;
    int PREDATOR_REPRODUCTION_TIME;
    int PREDATOR_ENERGY;
    int myReproductionTime = 0;
    int myEnergy = 0;
    
    public PredatorPreyCell (int xCoordinate, int yCoordinate, int startingState, HashMap<String, Double> param, Grid g) {
        super(xCoordinate, yCoordinate, startingState);
        myParameters = param;
        PREY_REPRODUCTION_TIME = (int) Math.round(param.get("preyreproductiontime"));
        PREDATOR_REPRODUCTION_TIME = (int) Math.round(param.get("predatorreproductiontime"));
        PREDATOR_ENERGY = (int) Math.round(param.get("energy"));
        myDirty = false;
        myGrid = g;
        myPossibleStates = new String[] {"Fish/Blue", "Shark/Red", "Empty"};
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
        mySquare = new Rectangle(70.375, 70.375, myColors[startingState]);
        GridPane.setConstraints(mySquare, myXCoordinate, myYCoordinate);
    }
    
    public void initNeighbors(){
        for (List<Cell> list: myGrid.getCellMatrix()){
            for (Cell cell: list){
                //System.out.println("Cell: ("+cell.getX()+","+cell.getY()+")");
                List<Cell> neighbors = new ArrayList<Cell>();
                int[] x = {0,0,1,-1};
                int[] y = {1,-1,0,0};
                for (int i=0;i<x.length;i++){
                    int xCoordinate = cell.getX()+x[i];
                    int yCoordinate = cell.getY()+y[i];
                    if(xCoordinate>=0 && yCoordinate>=0 && xCoordinate<myGrid.getCellMatrix().size() && yCoordinate<myGrid.getCellMatrix().get(0).size()){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(yCoordinate));
                        //System.out.println("neighbor: ("+myCells.get(xCoordinate).get(yCoordinate).getX()+","+myCells.get(xCoordinate).get(yCoordinate).getY()+")");
                    }
                    else if(xCoordinate>=myGrid.getCellMatrix().size()){
                        neighbors.add(myGrid.getCellMatrix().get(0).get(yCoordinate));
                    }
                    else if(xCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(myGrid.getCellMatrix().size()-1).get(yCoordinate));
                    }
                    else if(yCoordinate>=myGrid.getCellMatrix().get(0).size()){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(0));
                    }
                    else if(yCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(myGrid.getCellMatrix().get(0).size()-1));
                    }
                }
                cell.setNeighbors(neighbors);
            }
        }
    }
    
    private void moveTo(Cell prey){
        prey.myNextState = myCurrentState;
        prey.setCurrentState(prey.myNextState);
        prey.myParameters.put("reproductiontime", (double) myReproductionTime);
        prey.myParameters.put("energy", (double) myEnergy);
    }
    
    private void eat(Cell cell){
        moveTo(cell);
        myEnergy=0;
        age(cell);
    }
    
    private void checkDeath(Cell cell){
        if (myEnergy>=PREDATOR_ENERGY){
            cell.myNextState = 2;
            cell.myParameters.put("reproductiontime", 0.0);
            cell.myParameters.put("energy", 0.0);
        }
    }
    
    private void age(Cell cell){
        cell.myParameters.put("energy", cell.myParameters.get("energy")+1);
        cell.myParameters.put("reproductiontime", cell.myParameters.get("reproductiontime")+1);
    }
    
    private void leave(){
        if (myCurrentState==1){
            if (myReproductionTime>=PREDATOR_REPRODUCTION_TIME){
                myNextState = myCurrentState;
                myReproductionTime=0;
                myEnergy = 0;
            }
        }
        else if (myCurrentState==0){
            if (myReproductionTime>=PREY_REPRODUCTION_TIME){
                myNextState = myCurrentState;
                myReproductionTime=0;
                myEnergy = 0;
            }
        }
        else{
            myNextState = 2;
            myReproductionTime = 0;
            myEnergy = 0;
        }
        myCurrentState = myNextState;
    }

    @Override
    public void preUpdateCell (){
        initNeighbors();
        if(myCurrentState==1){
            List<Cell> fish = new ArrayList<Cell>();
            List<Cell> empties = new ArrayList<Cell>();
            for (Cell cell: myNeighbors){
                if (cell.getCurrentState()==0){
                    fish.add(cell);
                }
                else if (cell.getCurrentState()==2){
                    empties.add(cell);
                }
            }
            if (fish.size()>0){
                int ran = (int) Math.floor(Math.random()*fish.size());
                Cell eatCell = fish.get(ran);
                eat(eatCell);
                leave();
                checkDeath(eatCell);
            }
            else if (empties.size()>0){
                int ran = (int) Math.floor(Math.random()*empties.size());
                Cell moveCell = empties.get(ran);
                moveTo(moveCell);
                leave();
                age(moveCell);
                checkDeath(moveCell);
            }
        }
        
        else if(myCurrentState==0){
            List<Cell> empties = new ArrayList<Cell>();
            for (Cell cell: myNeighbors){
                if (cell.getCurrentState()==2){
                    empties.add(cell);
                }
            }
            if (empties.size()>0){
                int ran = (int) Math.floor(Math.random()*empties.size());
                Cell moveCell = empties.get(ran);
                moveTo(moveCell);
                leave();
                age(moveCell);
            }
        }
    }
/*
    @Override
    public void setCell (int xCoordinate,
                         int yCoordinate,
                         int startingState,
                         HashMap<String, Double> params,
                         Grid grid) {
        this.myXCoordinate = xCoordinate;
        this.myYCoordinate = yCoordinate;
        this.setCurrentState(startingState);
        myReproductionTime = (int) Math.round(params.get("reproduction"));
        myEnergy = (int) Math.round(params.get("energy"));
        myGrid = grid;
        
    }
*/
}

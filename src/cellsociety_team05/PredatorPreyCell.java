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
    
    public PredatorPreyCell (int xCoordinate, int yCoordinate, int startingState, HashMap<String, Double> param, Grid g,Simulation sim) {
        super(xCoordinate, yCoordinate, startingState);
        myParameters = param;
        myParameters.put("reproductiontime", 0.0);
        myParameters.put("energy", 0.0);
        PREY_REPRODUCTION_TIME = (int) Math.round(param.get("preyreproductiontime"));
        PREDATOR_REPRODUCTION_TIME = (int) Math.round(param.get("predatorreproductiontime"));
        PREDATOR_ENERGY = (int) Math.round(param.get("energylimit"));
        myDirty = false;
        myGrid = g;
        myPossibleStates = new String[] {"Fish/Blue", "Shark/Red", "Empty"};
        myColors = new Color[] {Color.BLUE, Color.RED, Color.WHITE};
        mySquare = new Rectangle(553/sim.getMyHeight(), 553/sim.getMyHeight(), myColors[startingState]);
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
                        //System.out.println("neighbor: ("+myGrid.getCellMatrix().get(xCoordinate).get(yCoordinate).getX()+","+myGrid.getCellMatrix().get(xCoordinate).get(yCoordinate).getY()+")");
                    }
                    else if(xCoordinate>=myGrid.getCellMatrix().size()){
                        neighbors.add(myGrid.getCellMatrix().get(0).get(yCoordinate));
                        //System.out.println("neighbor: ("+myGrid.getCellMatrix().get(0).get(yCoordinate).getX()+","+myGrid.getCellMatrix().get(0).get(yCoordinate).getY()+")");
                    }
                    else if(xCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(myGrid.getCellMatrix().size()-1).get(yCoordinate));
                        //System.out.println("neighbor: ("+myGrid.getCellMatrix().get(myGrid.getCellMatrix().size()-1).get(yCoordinate).getX()+","+myGrid.getCellMatrix().get(myGrid.getCellMatrix().size()-1).get(yCoordinate).getY()+")");
                    }
                    else if(yCoordinate>=myGrid.getCellMatrix().get(0).size()){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(0));
                        //System.out.println("neighbor: ("+myGrid.getCellMatrix().get(xCoordinate).get(0).getX()+","+myGrid.getCellMatrix().get(xCoordinate).get(0).getY()+")");
                    }
                    else if(yCoordinate<0){
                        neighbors.add(myGrid.getCellMatrix().get(xCoordinate).get(myGrid.getCellMatrix().get(0).size()-1));
                        //System.out.println("neighbor: ("+myGrid.getCellMatrix().get(xCoordinate).get(myGrid.getCellMatrix().get(0).size()-1).getX()+","+myGrid.getCellMatrix().get(xCoordinate).get(myGrid.getCellMatrix().get(0).size()-1).getY()+")");
                    }
                }
                cell.setNeighbors(neighbors);
            }
        }
    }
    
    private void moveTo(Cell prey){
        prey.myNextState = myCurrentState;
        prey.setCurrentState(prey.myNextState);
        System.out.println("moved: "+myPossibleStates[prey.myCurrentState]+" "+myPossibleStates[prey.myNextState]);
        prey.myParameters.put("reproductiontime", (double) myReproductionTime);
        prey.myParameters.put("energy", (double) myEnergy);
        prey.myDirty = true;
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
    	System.out.println("my energy: " + cell.myParameters.get("energy"));
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
            else{
                myNextState = 2;
                myReproductionTime = 0;
                myEnergy = 0;
            }
        }
        else if (myCurrentState==0){
            if (myReproductionTime>=PREY_REPRODUCTION_TIME){
                myNextState = myCurrentState;
                myReproductionTime=-1;
                myParameters.put("reproductiontime", -1.0);
                myEnergy = 0;
            }
            else{
                System.out.println("left");
                myNextState = 2;
                myReproductionTime = 0;
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
        myNextState = myCurrentState;
        initNeighbors();
        System.out.println("Cell: ("+getX()+","+getY()+")"+myPossibleStates[myCurrentState]+" "+myPossibleStates[myNextState]);
        myReproductionTime = (int) Math.round(myParameters.get("reproductiontime"));
        myEnergy = (int) Math.round(myParameters.get("energy"));
        System.out.println(myPossibleStates[myCurrentState]);
        System.out.println("Reproduction Time: "+myReproductionTime);
        System.out.println("Energy: "+myEnergy);
        System.out.println("PredatorReproductionTime: "+PREDATOR_REPRODUCTION_TIME);
        System.out.println("PreyReproductionTime: "+PREY_REPRODUCTION_TIME);
        if(!myDirty){
            if(myCurrentState==1){
                List<Cell> fish = new ArrayList<Cell>();
                List<Cell> empties = new ArrayList<Cell>();
                for (Cell cell: myNeighbors){
                    if (cell.getCurrentState()==0){
                        fish.add(cell);
                        System.out.println("fish: ("+cell.getX()+","+cell.getY()+")");
                    }
                    else if (cell.getCurrentState()==2){
                        empties.add(cell);
                        System.out.println("empty: ("+cell.getX()+","+cell.getY()+")");
                    }
                }
                if (fish.size()>0){
                    int ran = (int) Math.floor(Math.random()*fish.size());
                    Cell eatCell = fish.get(ran);
                    System.out.println("eat fish: ("+eatCell.getX()+","+eatCell.getY()+")");
                    eat(eatCell);
                    leave();
                    checkDeath(eatCell);
                }
                else if (empties.size()>0){
                    int ran = (int) Math.floor(Math.random()*empties.size());
                    Cell moveCell = empties.get(ran);
                    System.out.println("move empty: ("+moveCell.getX()+","+moveCell.getY()+")");
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
                        System.out.println("empty: ("+cell.getX()+","+cell.getY()+")");
                    }
                }
                if (empties.size()>0){
                    int ran = (int) Math.floor(Math.random()*empties.size());
                    Cell moveCell = empties.get(ran);
                    System.out.println("move empty: ("+moveCell.getX()+","+moveCell.getY()+")");
                    moveTo(moveCell);
                    if (myReproductionTime>=PREY_REPRODUCTION_TIME){               
                        moveCell.myParameters.put("reproductiontime", -1.0);
                    }
                    leave();
                    age(moveCell);
                }
            }
        }
        System.out.println("Cell exit: ("+getX()+","+getY()+")"+myPossibleStates[myCurrentState]+" "+myPossibleStates[myNextState]);
    }
}

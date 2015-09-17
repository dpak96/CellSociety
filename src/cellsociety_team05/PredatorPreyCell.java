package cellsociety_team05;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell {
    private final String[] myPossibleStates = {"Fish/Blue", "Shark/Red", "Empty"};
    private final Color[] myPossibleColors = {Color.BLUE, Color.RED, Color.WHITE};
    private Grid myGrid;
    List<PredatorPreyCell> myNeighbors;
    int PREY_REPRODUCTION_TIME;
    int PREDATOR_REPRODUCTION_TIME;
    int PREDATOR_ENERGY;
    int myReproductionTime = 0;
    int myEnergy = 0;
    
    public PredatorPreyCell (int xCoordinate, int yCoordinate, int startingState) {
        super(xCoordinate, yCoordinate, startingState);
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
    
    private void moveTo(PredatorPreyCell prey){
        prey.myNextState = myCurrentState;
        prey.setCurrentState(prey.myNextState);
        prey.myReproductionTime = myReproductionTime;
        prey.myEnergy = myEnergy;
    }
    
    private void eat(PredatorPreyCell cell){
        moveTo(cell);
        myEnergy=0;
        age(cell);
    }
    
    private void checkDeath(PredatorPreyCell cell){
        if (myEnergy>=PREDATOR_ENERGY){
            cell.myNextState = 2;
            cell.myReproductionTime = 0;
            cell.myEnergy = 0;
        }
    }
    
    private void age(PredatorPreyCell cell){
        cell.myEnergy+=1;
        cell.myReproductionTime+=1;
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
        if(myCurrentState==1){
            List<PredatorPreyCell> fish = new ArrayList<PredatorPreyCell>();
            List<PredatorPreyCell> empties = new ArrayList<PredatorPreyCell>();
            for (PredatorPreyCell cell: myNeighbors){
                if (cell.getCurrentState()==0){
                    fish.add(cell);
                }
                else if (cell.getCurrentState()==2){
                    empties.add(cell);
                }
            }
            if (fish.size()>0){
                int ran = (int) Math.floor(Math.random()*fish.size());
                PredatorPreyCell eatCell = fish.get(ran);
                eat(eatCell);
                leave();
                checkDeath(eatCell);
            }
            else if (empties.size()>0){
                int ran = (int) Math.floor(Math.random()*empties.size());
                PredatorPreyCell moveCell = empties.get(ran);
                moveTo(moveCell);
                leave();
                age(moveCell);
                checkDeath(moveCell);
            }
        }
        else if(myCurrentState==0){
            List<PredatorPreyCell> empties = new ArrayList<PredatorPreyCell>();
            for (PredatorPreyCell cell: myNeighbors){
                if (cell.getCurrentState()==2){
                    empties.add(cell);
                }
            }
            if (empties.size()>0){
                int ran = (int) Math.floor(Math.random()*empties.size());
                PredatorPreyCell moveCell = empties.get(ran);
                moveTo(moveCell);
                leave();
                age(moveCell);
            }
        }
    }

}

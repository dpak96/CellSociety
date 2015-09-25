package cellsociety_team05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.sun.org.apache.xml.internal.resolver.readers.XCatalogReader;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class Ant {
	
	private int[] orientation;
	private boolean hasFoodItem;
	private boolean atNest;
	private int xCoordinate; 
	private int yCoordinate;
	private AntCell currentCell;
	private boolean hasMoved;
	
	public Ant(AntCell startingCell){
		currentCell = startingCell;
		orientation = new int[]{0,1};
		hasFoodItem = false;
		forage();
		hasMoved = false;
	}
	
	
	public void forage(){
		if(hasFoodItem){
			returnToNest();
		} else {
			findFoodSource();
		}
	}
	
	private void returnToNest(){
		int max = 0;
		AntCell bestDirection;
		List<AntCell> possibleDirections = new ArrayList<AntCell>();
		for(Cell cell: currentCell.getMyNeighbors()){
			AntCell neighbor = (AntCell) cell;
			if(neighbor.isFree()){
				possibleDirections.add(neighbor);
				if(neighbor.getHomePheromones() > max){
					bestDirection = neighbor;
					max = neighbor.getHomePheromones();
				}
			}
		}
		if(max == 0){
			List<AntCell> bestPossibleDirections = new ArrayList<AntCell>();
			for(AntCell possibleCell: possibleDirections){
				if(isInOrientation(possibleCell)){
					bestPossibleDirections.add(possibleCell);
				}
			}
			Random rand = new Random();
			if(bestPossibleDirections.isEmpty()){
				int randomNum = rand.nextInt((possibleDirections.size() - 1) + 1);
			    bestDirection = possibleDirections.get(randomNum);
			} else {
				int randomNum = rand.nextInt((bestPossibleDirections.size() - 1) + 1);
			    bestDirection = bestPossibleDirections.get(randomNum);
			}
		} else {
			bestDirection = new AntCell(0,0,0,null,null,null);
		}
		orientation = computeNewOrientation(bestDirection);
		System.out.println(Arrays.toString(orientation));
		currentCell = bestDirection;
		currentCell.AntArrives(this);
	}
	
	private void findFoodSource(){
		
		//what to do when there are no possible directions?? 
		
		
		int max = 0;
		AntCell bestDirection;
		List<AntCell> possibleDirections = new ArrayList<AntCell>();
		for(Cell cell: currentCell.getMyNeighbors()){
			AntCell neighbor = (AntCell) cell;
			if(neighbor.isFree()){
				possibleDirections.add(neighbor);
				if(neighbor.getFoodPheromones() > max){
					bestDirection = neighbor;
					max = neighbor.getFoodPheromones();
				}
			}
		}
		if(max == 0){
			List<AntCell> bestPossibleDirections = new ArrayList<AntCell>();
			for(AntCell possibleCell: possibleDirections){
				if(isInOrientation(possibleCell)){
					bestPossibleDirections.add(possibleCell);
				}
			}
			Random rand = new Random();
			if(bestPossibleDirections.isEmpty()){
				int randomNum = rand.nextInt((possibleDirections.size() - 1) + 1);
			    bestDirection = possibleDirections.get(randomNum);
			} else {
				int randomNum = rand.nextInt((bestPossibleDirections.size() - 1) + 1);
			    bestDirection = bestPossibleDirections.get(randomNum);
			}
		} else {
			bestDirection = new AntCell(0,0,0,null,null,null);
		}
		orientation = computeNewOrientation(bestDirection);
		currentCell.antLeaves();
		currentCell = bestDirection;
		currentCell.AntArrives(this);
		System.out.println("I made a movement to " + currentCell.getX() + " " + currentCell.getY() );
	}
	
	private boolean isInOrientation(AntCell cell){
		int[] newCellOrientation = computeNewOrientation(cell);
		int distanceFromOrientation = Math.abs(orientation[0] - newCellOrientation[0]) + Math.abs(orientation[1] - newCellOrientation[1]);
		return (distanceFromOrientation <=1);
		
	}
	private int []computeNewOrientation(AntCell destination){
		int xChange = destination.getX() - currentCell.getX();
		int yChange = destination.getY() - currentCell.getY();
		return new int[]{xChange, yChange};
	}
	
	private void dropHomePheromones(){
		
	}
	
	private void dropFoodPheromones(){
		
	}
	
	private void moveInDirection(int xDirection, int yDirection){
		
	}

}

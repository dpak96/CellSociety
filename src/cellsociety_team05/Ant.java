package cellsociety_team05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ant {

	private int[] orientation;
	private boolean hasFoodItem;
	private AntCell currentCell;
	
	public Ant(AntCell startingCell){
		currentCell = startingCell;
		orientation = new int[]{0,1};
		hasFoodItem = false;
		forage();
		//homePheromones = 1000;
	}
	
	
	public void forage(){
		if(hasFoodItem){
			//System.out.println("I'M COMING HOME");
			returnToNest();
		} else {
			findFoodSource();
		}
	}
	
	private void returnToNest(){
		int max = 0;
		AntCell bestDirection = currentCell;
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
		}
		if(bestDirection.getMyCurrentState() != bestDirection.isNest){
			orientation = computeNewOrientation(bestDirection);
			currentCell.antLeaves();
			currentCell = bestDirection;
			currentCell.AntArrives(this);
			dropFoodPheromones();
			//dropPheromonoes(true);
		} else {
			System.out.println("WE RETURNED TO THE NEST");
			hasFoodItem = false;
		}
	}
	
	private void findFoodSource(){
		
		//what to do when there are no possible directions?? 

		int max = 0;
		AntCell bestDirection = currentCell;
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
		}
		if(bestDirection.getMyCurrentState() != bestDirection.isFood){
			orientation = computeNewOrientation(bestDirection);
			currentCell.antLeaves();
			currentCell = bestDirection;
			currentCell.AntArrives(this);
			dropHomePheromones();
			//dropPheromonoes(false);
		} else {
			hasFoodItem = true;
		}
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
	
	
//	private void dropPheromonoes(boolean food){
//		int max = 0; int currentPheromones;
//		if(food){
//			currentPheromones = currentCell.getFoodPheromones();
//		} else {
//			currentPheromones = currentCell.getHomePheromones();
//		}
//		for(Cell cell: currentCell.getMyNeighbors()){
//			AntCell neighbor = (AntCell) cell;
//			int pheromoneValue;
//			if(food){
//				pheromoneValue = neighbor.getFoodPheromones();
//			} else {
//				pheromoneValue = neighbor.getHomePheromones();
//			}
//			if(pheromoneValue > max){
//				max = pheromoneValue;
//			}
//		}
//		int DES = max - 2;
//		int D = DES - currentPheromones;
//		if(D > 0){
//			//move this if statement to another method
//			if(food){
//				currentCell.addFoodPheromones(D);
//			} else {
//				currentCell.addHomePheromones(D);
//			}
//		}
//	}


	private void dropFoodPheromones(){
		int max = 0;
		for(Cell cell: currentCell.getMyNeighbors()){
			AntCell neighbor = (AntCell) cell;
			if(neighbor.getFoodPheromones() > max){
				max = neighbor.getFoodPheromones();
			}
		}
		int DES = max - 2;
		int D = DES - currentCell.getFoodPheromones();
		if(D > 0){
			currentCell.addFoodPheromones(D);
		}
	}
	
	private void dropHomePheromones(){
		int max = 0;
		for(Cell cell: currentCell.getMyNeighbors()){
			AntCell neighbor = (AntCell) cell;
			if(neighbor.getHomePheromones() > max){
				max = neighbor.getHomePheromones();
			}
		}
		int DES = max - 2;
		int D = DES - currentCell.getHomePheromones();
		if(D > 0){
			currentCell.addHomePheromones(D);
		}
	}

}

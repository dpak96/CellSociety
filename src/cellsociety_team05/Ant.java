package cellsociety_team05;

import com.sun.org.apache.xml.internal.resolver.readers.XCatalogReader;

public class Ant {
	
	private int[] orientation;
	private boolean hasFoodItem;
	private boolean atNest;
	private int xCoordinate; 
	private int yCoordinate;
	
	public void forage(){
		if(hasFoodItem){
			returnToNest();
		} else {
			findFoodSource();
		}
	}
	
	private void returnToNest(){
		
	}
	
	private void findFoodSource(){
		
	}
	
	private void dropHomePheromones(){
		
	}
	
	private void dropFoodPheromones(){
		
	}
	
	private int[] moveInDirection(int xDirection, int yDirection){
		return new int[]{xCoordinate + xDirection, yCoordinate + yDirection};
	}
	
	

}

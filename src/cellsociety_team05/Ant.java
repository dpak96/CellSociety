package cellsociety_team05;

public class Ant {
	
	private int[] orientation;
	private boolean hasFoodItem;
	private boolean atNest;
	
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
		return new int[]{0,2};
	}
	
	

}

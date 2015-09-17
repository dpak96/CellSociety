package cellsociety_team05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SimulationTester extends Simulation{

		//changed it to protected so i could extend it 
		protected Grid myGrid;
		private Setup mySetup;
		private int mySpeed; 
		private GridPane myGridPane;
		private GUI myGUI;
		public static final int FRAMES_PER_SECOND = 60;
		private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
		private Timeline animation;
		protected HashMap<String, Double> myParameters;
		private double similar;
		private boolean paused;

		public SimulationTester( GridPane gridPane, GUI gui,HashMap<String, Double> params, double sim) {
			super(gridPane, gui, params);
			
			myGridPane = gridPane;
			myGUI = gui;
			similar = sim;
			myGrid = new Grid(8,8);
			foo();
			initializeGridPane();
		}
		
		private void initializeGridPane(){
			double constant = myGridPane.getHeight()/8;
			System.out.println(constant);
			int row = 0; 
			for(List<Cell> listCell: myGrid.getCellMatrix()){
				int col = 0;
				for(Cell cell: listCell){
                    myGridPane.getChildren().add(cell.getSquare());
                    col++;
				}
				row++;
			}
		}
		
		public void foo(){
		    System.out.println(myGrid.myCells.toString());
		}

		
		//Need a way to set similarity percentage
		public void setSimilar(double x){
			similar = x;
		}
		
		@Override
		public void updateState(Cell cell) {
		    myGrid.preUpdateGrid();
		}

		public void start(){	     
			animation = new Timeline();
			// sets the game's loop
			KeyFrame frame = new KeyFrame(Duration.millis(100),
					e -> this.step());
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
			System.out.println("Starts playing");
		}


		public void changeFlow(){
			if(paused){
				animation.play();
				System.out.println("Resumed");
			} else {
				animation.pause();
				System.out.println("Paused");
			}
			paused = !paused;
		}

		public void updateSpeed(int speed){
			mySpeed = speed;	
		}

		//single step of simulation First step would be updateState 
		//Second step would be updateGrid
		public void step(){
		    myGrid.preUpdateGrid();
			myGrid.updateGrid();
			//myGrid.displayGrid();
		}

		
		//run steps continuously (take a boolean?)
		public void run(boolean canRun){
			while(canRun){
				step(); 
			}
		}

        @Override
        public void setCellType (Grid grid) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void initGrid (Grid grid) {
            // TODO Auto-generated method stub
            
        }

}

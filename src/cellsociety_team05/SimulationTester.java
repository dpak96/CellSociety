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


		public static final int FRAMES_PER_SECOND = 60;
		private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
		
		private double similar;
		private boolean paused;

		public SimulationTester( GridPane gridPane, GUI gui,HashMap<String, Double> params, int height, int width, double sim) {
			super(gridPane, gui, params, height, width);
			similar = sim;
		}
		
		public void foo(){
		    System.out.println(myGrid.myCells.toString());
		}

		
		//Need a way to set similarity percentage
		public void setSimilar(double x){
			similar = x;
		}
		
}

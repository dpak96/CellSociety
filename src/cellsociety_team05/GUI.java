package cellsociety_team05;

import java.util.Arrays;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI {
	
	private Stage myStage;
	private BorderPane root;
	private final String FLOW_BOX = "f";
	private final String SPEED_BOX = "s";
	private final int DEFAULT_SPEED = 5;
	private final String[] simulationTypes = {"Segregation", "Game of Life", "Predator-Prey"};
	//private boolean simulationRunning = false;
	
	//want to get rid of this variable
	private long simulationSpeed;
	
	public GUI(Stage primaryStage){
		
		myStage = primaryStage;
		myStage.setTitle("Cell Society");
		myStage.setResizable(false);
		root = new BorderPane();
		Scene scene = new Scene(root, 640, 480, Color.WHITE);
		root.setTop(createTopMenu());
		root.setBottom(createFlowControlBox());
		root.setCenter(new GridPane());
		
		myStage.setScene(scene);
		myStage.show();
	}
	
	private MenuBar createTopMenu() {
		MenuBar topMenu = new MenuBar();
		Menu simulationMenu = new Menu("Simulation");
		
		for(String sim: simulationTypes){
			simulationMenu.getItems().add(new MenuItem(sim));
		}
		
		topMenu.getMenus().add(simulationMenu);
		topMenu.prefWidthProperty().bind(myStage.widthProperty());
		
		return topMenu;
	}
	
	private HBox createControlBox(String function){
		HBox hbox = new HBox();
		hbox.setSpacing(20.0);
		if(function.equals(SPEED_BOX)){
			updateSpeedBox(hbox);
		} else if (function.equals(FLOW_BOX)){
			updateFlowBox(hbox);
		}
		hbox.setAlignment(Pos.CENTER);
		hbox.prefWidthProperty().bind(myStage.widthProperty());
		return hbox;
	}
	
	private void updateSpeedBox(HBox hbox){
		Label sliderDescription = new Label("Speed");
		Label speedValue = new Label(Integer.toString(DEFAULT_SPEED));
		simulationSpeed = DEFAULT_SPEED;
		Slider slider = new Slider(0, 10, DEFAULT_SPEED);
		slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,Number old_val, Number new_val) {
            	long updatedSpeed = Math.round(slider.getValue());
            	speedValue.setText(Long.toString(updatedSpeed)); 
            	if(updatedSpeed != simulationSpeed){
            		updateSimulationSpeed(updatedSpeed);
            	}
            }
        });
		speedValue.setPrefWidth(20);
	    hbox.getChildren().addAll(sliderDescription, slider, speedValue);
	}
	
	private void updateFlowBox(HBox hbox){
		Button start = new Button("Start");
		Button stop = new Button("Pause");
		Button nextStep = new Button("Next step");
		Button restart = new Button("Restart");
		
		hbox.getChildren().addAll(start, stop, nextStep);
	}

	private VBox createFlowControlBox(){
		
		VBox vbox = new VBox();
		vbox.setSpacing(10.0);
		vbox.getChildren().add(createControlBox(FLOW_BOX));
		vbox.getChildren().add(createControlBox(SPEED_BOX));
		vbox.getChildren().add(new HBox());
		
		return vbox;
		
	}
	
	private void updateSimulationSpeed(long speed){
		System.out.println(speed);
		simulationSpeed = speed;
		//update the simulation speed
	}
	

	public void launchSetup(){
		
	}

	public void displayMainWindow(){
		
	}
	
	public void displayGrid(){
		
	}
	
	public void currentSetup(){
		
	}
	
	/*
	 * Incorporate these two methods in one (The only thing they actually do is to start/stop the timeline;
	 * i.e. just change the gameLoop state. The simulation class will deal with it. 
	 */
	
	private void changeSimulationFlow(){
		//stop or starts the sim according to whatever happens. 
	}
	/*
	private void stopSimulation(){
		simulationRunning = false;
	}
	
	private void startSimulation(){
		simulationRunning = true;
		
	}
	*/

}

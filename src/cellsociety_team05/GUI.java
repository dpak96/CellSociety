package cellsociety_team05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Dialog;
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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI {
	
	/**
	 * @author Emanuele Macchi
	 */
	
	private Stage myStage;
	private BorderPane root;
	private final String FLOW_BOX = "f";
	private final String SPEED_BOX = "s";
	private final int DEFAULT_SPEED = 5;
	private final String[] simulationTypes = {"Segregation", "Game of Life", "Predator-Prey"};
	private Setup mySetup;
	private Simulation mySimulation;
	private String currentSimulationName;
	private long simulationSpeed;
	private boolean paused;
	private Button nextStepButton;
	private Button flowButton;
	private GridPane myGridPane;
	//private guiButtons myButtons;
	
	public GUI(Stage primaryStage){
		
		myStage = primaryStage;
		myStage.setTitle("Cell Society");
		myStage.setResizable(false);
		root = new BorderPane();
		Scene scene = new Scene(root, 553, 640, Color.WHITE);
		root.setTop(createTopMenu());
		root.setBottom(createFlowControlBox());
		myGridPane = new GridPane();
		root.setCenter(myGridPane);
		createChoiceDialog();
		myStage.setScene(scene);
		myStage.show();
	}
	
	private void createChoiceDialog(){
		ArrayList<String> choices = new ArrayList<>(Arrays.asList(simulationTypes));
		ChoiceDialog<String> dialog = new ChoiceDialog<>(simulationTypes[0], choices);
		dialog.setTitle("Cell Society");
		dialog.setHeaderText("Please choose a simulation");
		dialog.setContentText("Simulation:");
		Node endApplication = dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION, event -> System.exit(0));
		dialog.showAndWait().ifPresent(letter -> loadSimulationValue(letter));
	}

	private void loadSimulationValue(String letter) {
		mySetup = new Setup(letter);
		//mySimulation = new Simulation(mySetup);
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
		hbox.setPrefHeight(20);
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
		start.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> startSimulation());
		flowButton = new Button("Pause");
		flowButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> changeSimulationFlow());
		nextStepButton = new Button("Next step");
		nextStepButton.setDisable(true);
		nextStepButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {/*mySimulation.nextStep();*/ System.out.println("next step");});
		Button restart = new Button("Restart");
		restart.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> restartSimulation());
		hbox.getChildren().addAll(start, flowButton, nextStepButton, restart);
	}
	
	private void startSimulation() {
		//mySimulation.start();
		paused = false;
		System.out.println("Start");
		mySimulation = new SimulationTester(mySetup, myGridPane);
	}
	
	private void restartSimulation(){
		
	}

	private VBox createFlowControlBox(){
		VBox vbox = new VBox();
		vbox.setSpacing(10.0);
		vbox.getChildren().add(createControlBox(FLOW_BOX));
		vbox.getChildren().add(createControlBox(SPEED_BOX));
		vbox.getChildren().add(new HBox());
		vbox.setPrefHeight(50);
		return vbox;
	}
	
	private void updateSimulationSpeed(long speed){
		System.out.println(speed);
		simulationSpeed = speed;
		//update the simulation speed
	}
	

	public void displayMainWindow(){
		
	}
	
	public void displayGrid(){
		
	}
	
	public void currentSetup(){
		
	}
	
	private void changeSimulationFlow(){
		//stop or starts the sim according to whatever happens. 
		//mySimulation.changeFlow();
		paused = !paused;
		if(paused){
			System.out.println("Paused");
			nextStepButton.setDisable(false);
			flowButton.setText("Resume");
		} else {
			System.out.println("Resumed");
			nextStepButton.setDisable(true);
			flowButton.setText("Pause");
		}
	}
}

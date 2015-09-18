package cellsociety_team05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import toolsForGui.GuiBoxContainer;
import javafx.animation.Animation;

public class GUI {
	
	/**
	 * @author Emanuele Macchi
	 */
	
	private Stage myStage;
	private BorderPane root;
	private final String[] simulationTypes = {"Segregation", "Game of Life", "Predator-Prey"};
	private Simulation mySimulation;
	private long simulationSpeed;
	//private Button startButton;
	private GridPane myGridPane;
	private Grid myGrid;
	private Timeline animation;
	//private guiButtons myButtons;
	private GuiBoxContainer myBoxContainer;
	
	public GUI(Stage primaryStage){
		
		myStage = primaryStage;
		myStage.setTitle("Cell Society");
		myStage.setResizable(false);
		root = new BorderPane();
		Scene scene = new Scene(root, 553, 640, Color.WHITE);
		root.setTop(createTopMenu());
		myBoxContainer = new GuiBoxContainer(this, myStage);
		root.setBottom(myBoxContainer.getVBox());
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
		//mySetup = new Setup(letter);
		//mySimulation = new SegregationSimulation(mySetup);
	}
	
	//handle menu somewhere else
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
	public void startSimulation(){
		System.out.println("Start");
		mySimulation = new SimulationTester(myGridPane, this, new HashMap<String, Double>(), 8, 8, 0.5);
		mySimulation.start();
	}
	public void step(){
	    System.out.println("step");
	    myGrid.preUpdateGrid();
	    myGrid.updateGrid();
	}
	
	public void restartSimulation(){
		System.out.println("RESTART");
	        mySimulation.stopAnimation();
	        myGridPane.getChildren().clear();
	        startSimulation();
		mySimulation.restart();
	}
	
	public void updateSimulationSpeed(double speed){
		System.out.println(speed);
		//simulationSpeed = speed;
		mySimulation.updateSpeed(speed);
	}
	
	public void dostuff(){
		System.out.println("HAHAHAH");
	}
	
	public void nextStep() {
		mySimulation.step();
	}

	public Simulation getCurrentSimulation(){
		return mySimulation;
	}
	
	public void changeSimulationFlow(){
		mySimulation.changeFlow();
	}
	
	public long getSimulationSpeed() {
		return simulationSpeed;
	}
}

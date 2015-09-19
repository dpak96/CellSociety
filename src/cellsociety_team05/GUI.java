package cellsociety_team05;

import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import toolsForGui.GuiBoxContainer;
import toolsForGui.GuiChoiceDialog;
import toolsForGui.TopMenu;

public class GUI {
	
	/**
	 * @author Emanuele Macchi
	 */
	
	private Stage myStage;
	private BorderPane root;
	private final String[] simulationTypes = {"Segregation", "GameOfLife", "PredatorPrey", "Fire"};
	private Simulation mySimulation;
	private long simulationSpeed;
	private GridPane myGridPane;
	private GuiBoxContainer myBoxContainer;
	private ResourceBundle myResources;
	
	public GUI(Stage primaryStage){
		GuiChoiceDialog myGuiChoiceDialog = new GuiChoiceDialog(this, simulationTypes);
		myResources = ResourceBundle.getBundle("resources.window");
		myStage = primaryStage;
		myStage.setTitle(myResources.getString("Title"));
		myStage.setResizable(false);
		root = new BorderPane();
		Scene scene = new Scene(root, 553, 640, Color.WHITE);
		TopMenu myTopMenu = new TopMenu(myStage, simulationTypes, this);
		root.setTop(myTopMenu.getMenuBar());
		myBoxContainer = new GuiBoxContainer(this, myStage);
		root.setBottom(myBoxContainer.getVBox());
		myGridPane = new GridPane();
		root.setCenter(myGridPane);
		myGuiChoiceDialog.display();
		myStage.setScene(scene);
		myStage.show();
	}
	
	public void loadSimulationValue(String letter){
		System.out.println(letter);
                Setup s = new Setup(letter,this,myGridPane);
                System.out.println("Start");
                mySimulation = s.getSimulation();
	}
	
	public void startSimulation(){
		mySimulation.start();
	}
	
	public void step(){
	    mySimulation.step();
	}
	
	public void restartSimulation(){
		myGridPane.getChildren().clear();
	}
	
	public void updateSimulationSpeed(double speed){
		System.out.println(speed);
		mySimulation.updateSpeed(speed);
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
	
	public void startNewSimulation(String simulation){
		if(mySimulation != null){
			mySimulation.stopAnimation();
		}
		System.out.println(simulation);
	}
}

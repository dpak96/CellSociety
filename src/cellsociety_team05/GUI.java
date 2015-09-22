package cellsociety_team05;

import java.awt.List;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;
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
	private String currentSimulationName;
	private Canvas testCanvas;
	
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
	
	/**
	 * this class is currently not implemented so we can test 
	 * the triangle layout 
	 */ 
	
	public void loadSimulationValue(String letter){
		/*
		System.out.println(letter);
		currentSimulationName = letter;
        Setup setup = new Setup(letter,this,myGridPane);
        System.out.println("Start");
        mySimulation = setup.getSimulation();
        */
	}
	
	/**
	 * The following two methods have been modified to show how the triangle display. 
	 */
	
	public void startSimulation(){
		//mySimulation.start();

		//test method
		testUpdateTriangle();
	}
	
	public void step(){
	    //mySimulation.step();
		stepCells();
	}
	
	/**
	 * End of modifications
	 */
	
	
	public void restartSimulation(){
		loadSimulationValue(currentSimulationName);
		startSimulation();
	}
	
	public void updateSimulationSpeed(double speed){
		System.out.println(speed);
		//simulationSpeed = speed;
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
		loadSimulationValue(currentSimulationName);
		startSimulation();
	}
	
	
	/**
	 * Testing the triangle grid
	 */
	
	private ArrayList<TriangleCell> myTriangleCells = new ArrayList<>();
	
	public void testUpdateTriangle(){
		for(int i=0; i < 8; i++){
			for(int k=0; k<8; k++){
				TriangleCell current = new TriangleCell(i, k, myGridPane, myGridPane.getHeight() / 8);
				myTriangleCells.add(current);
			}
		}
	}
	
	public void stepCells(){
		for(TriangleCell t: myTriangleCells){
			t.updateVisualCells();
		}
	}
}

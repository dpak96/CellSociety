package cellsociety_team05;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
	
	public GUI(Stage primaryStage){
		GuiChoiceDialog myGuiChoiceDialog = new GuiChoiceDialog(this, simulationTypes);
		myResources = ResourceBundle.getBundle("resources.window");
		myStage = primaryStage;
		myStage.setTitle(myResources.getString("Title"));
		myStage.setResizable(false);
		root = new BorderPane();
		
		Scene scene = new Scene(root, 720, 480, Color.WHITE);
		TopMenu myTopMenu = new TopMenu(myStage, simulationTypes, this);
		root.setTop(myTopMenu.getMenuBar());
		myGuiChoiceDialog.display();
		
		//different layout
		HBox h = new HBox();
		int height = 440;
		int length = 440;
		myGridPane = new GridPane();
		myGridPane.setMaxSize(440, 440);
		initializeEmptyGridPane(height, length);
		h.getChildren().add(myGridPane);
		myBoxContainer = new GuiBoxContainer(this, myStage);
		h.getChildren().add(myBoxContainer.getVBox());
		root.setCenter(h);
		
		myStage.setScene(scene);
		myStage.show();
		
		/** Alternative design - Second choice for now
		 * myBoxContainer = new GuiBoxContainer(this, myStage);
		 * root.setBottom(myBoxContainer.getVBox());
		 * myGridPane = new GridPane();
		 * root.setCenter(myGridPane);
		*/
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
		//testUpdateTriangle();
		testRowTriangle();
	}
	
	public void step(){
	    //mySimulation.step();
		stepCells();
		myBoxContainer.getPCB().AddToQueue();
		myBoxContainer.getPCB().addDataToSeries();
		
	}
	
	/**
	 * End of modifications
	 */
	
	
	public void restartSimulation(){
		loadSimulationValue(currentSimulationName);
		startSimulation();
		/**
		 * myGridPane.getChildren().clear();
		 * mySimulation.restart();
		 */
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
	 *	Testing the triangle grid
	 */
	
	private ArrayList<TriangleCell> myTriangleCells = new ArrayList<>();
	
	public void testUpdateTriangle(){
		myGridPane.getChildren().clear();
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
	
	private void initializeEmptyGridPane(int height, int length){
		Rectangle size = new Rectangle(height, length);
		myGridPane.getChildren().add(size);
	}
	
	private void testRowTriangle(){
		myGridPane.getChildren().clear();
		for(int i=0; i<8; i++){
			TriangleRow t = new TriangleRow(i, myGridPane, 8, 440);
		}
	}
}

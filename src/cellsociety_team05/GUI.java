package cellsociety_team05;

import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import toolsForGui.GuiBoxContainer;
import toolsForGui.InitialChoiceDialog;
import toolsForGui.TopMenu;


public class GUI {

    /**
     * @author Emanuele Macchi
     */

    private Stage myStage;
    private BorderPane root;
    private final String[] simulationTypes = {"Segregation", "GameOfLife", "PredatorPrey", "Fire", "Sugar", "AntForaging"};
    private Simulation mySimulation;
    private long simulationSpeed;
    private GridPane myGridPane;
    private GuiBoxContainer myBoxContainer;
    private ResourceBundle myResources;
    private String currentSimulationName;

    public GUI(Stage primaryStage) throws SimulationException{
   
        myResources = ResourceBundle.getBundle("resources.window");
        initializeStage(primaryStage);
        

        Scene myScene= initializeScene();
        InitialChoiceDialog myGuiChoiceDialog = new InitialChoiceDialog(this, simulationTypes);
        myGuiChoiceDialog.display();

        myStage.setScene(myScene);
        myStage.show();
    }


	private void initializeStage(Stage primaryStage) {
		myStage = primaryStage;
        myStage.setTitle(myResources.getString("Title"));
        myStage.setResizable(false);
	}


	private Scene initializeScene() {
		root = new BorderPane();
		Scene scene = new Scene(root, 720, 480, Color.WHITE);
        TopMenu myTopMenu = new TopMenu(myStage, simulationTypes, this);
        root.setTop(myTopMenu.getMenuBar());
        
        HBox modifiableElementsBox = new HBox();
        int gridSideLenght = 440;
        myGridPane = new GridPane();
        myGridPane.setMaxSize(440, 440);
        initializeEmptyGridPane(gridSideLenght, gridSideLenght);
        modifiableElementsBox.getChildren().add(myGridPane);
        myBoxContainer = new GuiBoxContainer(this, myStage, mySimulation);
        modifiableElementsBox.getChildren().add(myBoxContainer.getVBox());
        root.setCenter(modifiableElementsBox);
		return scene;
	}


    /**
     * Modified to test ant foraging 
     * @param letter
     */

    public void loadSimulationValue (String letter) {
        myGridPane.getChildren().clear();
        System.out.println(letter + "Sim type");
        currentSimulationName = letter;
        try{
        Setup setup = new Setup(letter, this, myGridPane);
        mySimulation = setup.getSimulation();

        }
        catch(SimulationException e){
        	e.printStackTrace();
        }
    }

    public void startSimulation(){
        mySimulation.start();

        //testUpdateTriangle();
        //testRowTriangle();
    }

    public void step () {
        mySimulation.step();
    }

    public void updateGraph(){
        myBoxContainer.getPCB().AddToQueue(mySimulation.getStats());
        myBoxContainer.getPCB().addDataToSeries();
    }


    public void restartSimulation(){
        loadSimulationValue(currentSimulationName);
        startSimulation();
    }

    public void updateSimulationSpeed (double speed) {
        System.out.println(speed);
        mySimulation.updateSpeed(speed);
    }

    public Simulation getCurrentSimulation () {
        return mySimulation;
    }

    public void changeSimulationFlow () {
        mySimulation.changeFlow();
    }

    public long getSimulationSpeed () {
        return simulationSpeed;
    }

    public void startNewSimulation (String simulation) {
        loadSimulationValue(simulation);
        startSimulation();
    }

    /**
     * The following methods show the functionality of two
     * different triangle grids
     */

    private void initializeEmptyGridPane (int height, int length) {
        Rectangle size = new Rectangle(height, length);
        myGridPane.getChildren().add(size);
    }

    private void testRowTriangle () {
        myGridPane.getChildren().clear();
        for (int i = 0; i < 8; i++) {
            TriangleRow t = new TriangleRow(i, myGridPane, 8, 440);
        }
    }


	public Object loadPersonalizedSimulation(boolean random, int noOfCells, String simulation, String shape) {
		
		//creates a new simulation with these two parameters 
		System.out.println(random + " " + noOfCells + " " + simulation + " " + shape);
		
		return null;
	}

}

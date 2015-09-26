package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;

public class InitialChoiceDialog {

	private GUI myGui;
	private String[] mySimulationTypes;
	private Dialog<String> myDialog;
	private GridPane myGrid;
	private boolean personalized;
	private String currentSimulation;
	private boolean random;
	private int noOfCells;
	private String cellShape;
	private String[] myCellShapes = {"rectangle", "circle"};
	
	public InitialChoiceDialog(GUI gui, String[] simulationTypes){
		myGui = gui;
		mySimulationTypes = simulationTypes;
		initializeSimulationParameters();
	}

	private void initializeSimulationParameters() {
		personalized = false;
		random = false;
		currentSimulation = mySimulationTypes[0];
		cellShape = myCellShapes[0];
	}
	
	public void display(){
		initializeGrid();
		initializeDialog();
		initializeBasicOptions();
		myDialog.showAndWait();
		
	}

	private void initializeDialog() {
		ResourceBundle myResources = ResourceBundle.getBundle("resources.window");
		myDialog = new Dialog<>();
		myDialog.setDialogPane(new DialogPane());
		myDialog.setTitle(myResources.getString("Title"));
		myDialog.setHeaderText(myResources.getString("ChoiceDialogHeader"));
		myDialog.getDialogPane().getButtonTypes().add(cancelButton());
		myDialog.getDialogPane().getButtonTypes().add(okButton());
		Node endApplication = myDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION, event -> System.exit(0));
		Node startApplication = myDialog.getDialogPane().lookupButton(ButtonType.OK);
		startApplication.addEventFilter(ActionEvent.ACTION, event -> loadSimulation());
	}

	private void initializeGrid() {
		myGrid = new GridPane();
		myGrid.setPrefSize(300, 200);
		myGrid.setHgap(30);
		myGrid.setVgap(10);
	}
	
	private void loadSimulation(){
		if(personalized){
			myGui.loadPersonalizedSimulation(random, noOfCells, currentSimulation, cellShape); 
		} else {
			myGui.loadSimulationValue(currentSimulation);
		}
	}

	private void initializeBasicOptions() {
		
		SimulationOption simulationOption = new SimulationOption(this, mySimulationTypes);
		addPersonalizationOption(simulationOption, 0);
		
		AllowPersonalizationOption allowPersonalizationOption = new AllowPersonalizationOption(this);
		addPersonalizationOption(allowPersonalizationOption, 1);
		
		myDialog.getDialogPane().setContent(myGrid);
		
	}
	
	private ButtonType cancelButton(){
		ButtonType cancel = ButtonType.CANCEL;
		return cancel;
	}
	
	private ButtonType okButton(){
		ButtonType ok = ButtonType.OK;
		return ok;
	}
	
	public void allowPersonalization(){
		if(!personalized){
			
			NumberOfCellsOption firstOption = new NumberOfCellsOption(this);
			addPersonalizationOption(firstOption, 2);
			RandomOption randomOption = new RandomOption(this);
			addPersonalizationOption(randomOption, 3);
			ShapeOption shapeOption = new ShapeOption(this, myCellShapes);
			addPersonalizationOption(shapeOption, 4);	
			
			myDialog.getDialogPane().setContent(myGrid);
			
			personalized = true;
			
		} else {
			
			myGrid.getChildren().clear();
			initializeBasicOptions();
			personalized = false;
		}
		
	}
	
	private void addPersonalizationOption(PersonalizationOption myOption, int row){
		myGrid.add(myOption.getOptionName(), 0, row);
		myGrid.add(myOption.getControl(), 1, row);
	}
	
	public void randomSim(){
		random = !random;
	}
	
	public void setNumberCells(int value){
		noOfCells = value;
	}
	
	public void setCellShape(String value){
		cellShape = value;
	}

	public void setSimulation(String newSimulation) {
		currentSimulation = newSimulation;
	}
}

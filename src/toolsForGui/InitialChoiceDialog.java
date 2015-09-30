// This entire file is part of my masterpiece.
// EMANUELE MACCHI


package toolsForGui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.GridPane;

/**
 * 
 * @author Emanuele Macchi
 *
 */
public class InitialChoiceDialog {

	private GUI myGui;
	private List<String> mySimulationTypes;
	private Dialog<String> myDialog;
	private GridPane myGrid;
	private boolean personalized;
	private String currentSimulation;
	private boolean random;
	private int noOfCells;
	private String cellShape;
	private String[] myCellShapes = {"rectangle", "circle"};
	private String myGridType;
	private List<PersonalizationOption> myPersonalizationOptions;
	private List<PersonalizationOption> myBasicOptions;
	private String PERSONALIZED_OPTIONS = "Personalized";
	private String BASIC_OPTIONS = "Basic";
	
	public InitialChoiceDialog(GUI gui, List<String> simulationTypes){
		myGui = gui;
		mySimulationTypes = simulationTypes;
		initializeSimulationParameters();
	}

	private void initializeSimulationParameters() {
		personalized = false;
		random = false;
		currentSimulation = mySimulationTypes.get(0);
		cellShape = myCellShapes[0];
		myGridType = "Normal";
	}
	
	public void display(){
		initializeGrid();
		initializeDialog();
		createOptions(BASIC_OPTIONS);
		myDialog.showAndWait();
		
	}

	private void initializeDialog() {
		myDialog = new Dialog<>();
		initializeDialogLayout();
		createDialogButtons();
	}

	private void createDialogButtons() {
		
		Node endApplication = myDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION, event -> System.exit(0));
		Node startApplication = myDialog.getDialogPane().lookupButton(ButtonType.OK);
		startApplication.addEventFilter(ActionEvent.ACTION, event -> loadSimulation());
	}
	private void loadSimulation(){
		if(personalized){
			myGui.loadPersonalizedSimulation(random, noOfCells, currentSimulation, cellShape, myGridType); 
		} else {
			myGui.loadSimulationValue(currentSimulation);
		}
	}
	
	private ButtonType cancelButton(){
		ButtonType cancel = ButtonType.CANCEL;
		return cancel;
	}
	private void initializeDialogLayout() {
		ResourceBundle myResources = ResourceBundle.getBundle("resources.window");
		myDialog.setDialogPane(new DialogPane());
		myDialog.setTitle(myResources.getString("Title"));
		myDialog.setHeaderText(myResources.getString("ChoiceDialogHeader"));
		myDialog.getDialogPane().getButtonTypes().add(cancelButton());
		myDialog.getDialogPane().getButtonTypes().add(okButton());
	}

	private void initializeGrid() {
		myGrid = new GridPane();
		myGrid.setPrefSize(300, 200);
		myGrid.setHgap(30);
		myGrid.setVgap(10);
	}
	
	
	
	private ButtonType okButton(){
		ButtonType ok = ButtonType.OK;
		return ok;
	}
	
	private void initializeBasicOptions(){
		myBasicOptions = new ArrayList<PersonalizationOption>();
		Collections.addAll(myBasicOptions, new SimulationOption(this, mySimulationTypes),
		new AllowPersonalizationOption(this));
	}
	
	private void initializePersonalizationOptions(){
		myPersonalizationOptions = new ArrayList<PersonalizationOption>();
		Collections.addAll(myPersonalizationOptions, new NumberOfCellsOption(this),
				new RandomOption(this), new ShapeOption(this, myCellShapes),
				new GridTypeOption(this));
	}
	
	private void createOptions(String optionType){
		if(optionType.equals(BASIC_OPTIONS)){
			initializeBasicOptions();
			addOptions(myBasicOptions, 0);
		} else if (optionType.equals(PERSONALIZED_OPTIONS)){
			initializePersonalizationOptions();
			addOptions(myPersonalizationOptions, 2);
		}
		myDialog.getDialogPane().setContent(myGrid);
	}
	
	public void allowPersonalization(){
		if(!personalized){
			createOptions(PERSONALIZED_OPTIONS);
			personalized = true;
			
		} else {
			
			myGrid.getChildren().clear();
			createOptions(BASIC_OPTIONS);
			personalized = false;
		}
		
	}
	
	private void addOptions(List<PersonalizationOption> myOptions, int row){
		for(PersonalizationOption extraOption: myOptions){
			myGrid.add(extraOption.getOptionName(), 0, row);
			myGrid.add(extraOption.getControl(), 1, row);
			row++;
		}
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

	public void setGridType(String string) {
		myGridType = string;
	}
}

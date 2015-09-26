package toolsForGui;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
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
	
	public InitialChoiceDialog(GUI gui, String[] simulationTypes){
		myGui = gui;
		mySimulationTypes = simulationTypes;
		personalized = false;
		random = false;
		currentSimulation = simulationTypes[0];
		
	}
	
	public void display(){
		ResourceBundle myResources = ResourceBundle.getBundle("resources.window");
		myDialog = new Dialog<>();
		myGrid = new GridPane();
		myGrid.setPrefSize(300, 200);
		myGrid.setHgap(30);
		myGrid.setVgap(20);
		myDialog.setDialogPane(new DialogPane());
		myDialog.getDialogPane().getButtonTypes().add(cancelButton());
		myDialog.getDialogPane().getButtonTypes().add(okButton());
		
		initializeGrid();
		
		myDialog.setTitle(myResources.getString("Title"));
		myDialog.setHeaderText(myResources.getString("ChoiceDialogHeader"));
		Node endApplication = myDialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION, event -> System.exit(0));
		Node startApplication = myDialog.getDialogPane().lookupButton(ButtonType.OK);
		startApplication.addEventFilter(ActionEvent.ACTION, event -> loadSimulation());
		myDialog.showAndWait();
		
	}
	
	private void loadSimulation(){
		if(personalized){
			myGui.loadPersonalizedSimulation(random, noOfCells, currentSimulation); 
		} else {
			myGui.loadSimulationValue(currentSimulation);
		}
	}

	private void initializeGrid() {
		Label sim = new Label("Simulation: ");
		ChoiceBox<String> myChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(mySimulationTypes));
		myChoiceBox.getSelectionModel().selectFirst();
		myGrid.add(sim, 0, 0);
		myGrid.add(myChoiceBox, 1, 0);
		Label allowPersonalize = new Label("Personalize: ");
		CheckBox personalizeCheckBox = new CheckBox();
		personalizeCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> allowPersonalization());
		myGrid.add(allowPersonalize, 0, 1);
		myGrid.add(personalizeCheckBox, 1, 1);
		myDialog.getDialogPane().setContent(myGrid);
		myChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>(){
					@Override
					public void changed(ObservableValue oc, Number value, Number newValue){
						currentSimulation = mySimulationTypes[newValue.intValue()];
					}
				}
		);
	}
	
	private ButtonType cancelButton(){
		ButtonType cancel = ButtonType.CANCEL;
		return cancel;
	}
	
	private ButtonType okButton(){
		ButtonType ok = ButtonType.OK;
		return ok;
	}
	
	private void allowPersonalization(){
		if(!personalized){
			Label label1 = new Label("Side Cells: ");
			Label label2 = new Label("Random Grid: ");
			TextField numberOfCells = new TextField();
			CheckBox randomCheckBox = new CheckBox();
					
			
			myGrid.add(label1, 0, 2);
			myGrid.add(numberOfCells, 1, 2);
			myGrid.add(label2, 0, 3);
			myGrid.add(randomCheckBox, 1, 3);
			
			myDialog.getDialogPane().setContent(myGrid);
			
			randomCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->  randomSim());
			numberOfCells.textProperty().addListener(new ChangeListener<String>() {

		        @Override
		        public void changed(ObservableValue<? extends String> observable,
		                String oldValue, String newValue) {
		            try {
		                noOfCells = Integer.parseInt(newValue);
		            } catch (NumberFormatException e) {
		                numberOfCells.setText("Please enter a number");
		            }
		        }
		});
			personalized = true;
		} else {
			myGrid.getChildren().clear();
			initializeGrid();
			personalized = false;
		}
		
	}
	
	private void randomSim(){
		random = !random;
	}
}

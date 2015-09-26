package toolsForGui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class SimulationOption extends PersonalizationOption{

	private InitialChoiceDialog myDialog; 
	private String[] mySimulationTypes;
	
	public SimulationOption(InitialChoiceDialog dialog, String[] simulationTypes){
		super("Simulation");
		myDialog = dialog;
		mySimulationTypes = simulationTypes;
		initializeOption();
	}
	
	private void initializeOption(){
		ChoiceBox<String> myChoiceBox = new ChoiceBox<String>(FXCollections.observableArrayList(mySimulationTypes));
		myChoiceBox.getSelectionModel().selectFirst();
		myChoiceBox.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener<Number>(){
					@Override
					public void changed(ObservableValue oc, Number value, Number newValue){
						myDialog.setSimulation(mySimulationTypes[newValue.intValue()]);
					}
				}
		);
		this.setControl(myChoiceBox);
	}

}

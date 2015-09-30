// This entire file is part of my masterpiece.
// EMANUELE MACCHI

package toolsForGui;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

/**
 * 
 * @author emanuele
 *
 */
public class SimulationOption extends PersonalizationOption{

	private InitialChoiceDialog myDialog; 
	private List<String> mySimulationTypes;
	
	public SimulationOption(InitialChoiceDialog dialog, List<String> simulationTypes){
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
						myDialog.setSimulation(mySimulationTypes.get(newValue.intValue()));
					}
				}
		);
		this.setControl(myChoiceBox);
	}

}

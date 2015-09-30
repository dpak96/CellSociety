package toolsForGui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import cellsociety_team05.SimulationException;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;

/**
 * 
 * @author emanuele
 *
 */
public class GuiChoiceDialog {
	private GUI myGui;
	private String[] mySimulationTypes;

	public GuiChoiceDialog(GUI gui, String[] simulationTypes) {
		myGui = gui;
		mySimulationTypes = simulationTypes;

	}

	public void display() throws SimulationException {
		ResourceBundle myResources = ResourceBundle
				.getBundle("resources.window");
		ArrayList<String> choices = new ArrayList<>(
				Arrays.asList(mySimulationTypes));
		ChoiceDialog<String> dialog = new ChoiceDialog<>(mySimulationTypes[0],
				choices);
		dialog.setTitle(myResources.getString("Title"));
		dialog.setHeaderText(myResources.getString("ChoiceDialogHeader"));
		dialog.setContentText(myResources.getString("Simulation"));
		Node endApplication = dialog.getDialogPane().lookupButton(
				ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION,
				event -> System.exit(0));
		dialog.showAndWait().ifPresent(
				letter -> myGui.loadSimulationValue(letter));

	}
}

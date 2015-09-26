package toolsForGui;

import javafx.scene.control.Control;
import javafx.scene.control.Spinner;

public class NumberOfCellsOption extends PersonalizationOption {
	
	private Control myControl;
	private int numberCells;
	private InitialChoiceDialog myDialog; 
	
	public NumberOfCellsOption(InitialChoiceDialog myChoiceDialog) {
		super("NoOfCells");
		myDialog = myChoiceDialog;
		numberCells = 50;
		myChoiceDialog.setNumberCells(numberCells);
		initializeOption();
	}
	
	private void initializeOption(){
		Spinner<Integer> spinnerNoOfCells = new Spinner(5,220,50);	
		spinnerNoOfCells.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			myDialog.setNumberCells(Integer.parseInt(newValue));
		});
		myControl = spinnerNoOfCells;
	}

	@Override
	public Control getControl() {
		return myControl;
	}
	
	public int numberOfCells(){
		return numberCells;
	}

}

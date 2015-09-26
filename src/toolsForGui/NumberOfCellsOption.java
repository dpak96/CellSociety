package toolsForGui;

import javafx.scene.control.Spinner;

public class NumberOfCellsOption extends PersonalizationOption {

	private int numberCells;
	private InitialChoiceDialog myDialog; 
	
	public NumberOfCellsOption(InitialChoiceDialog myChoiceDialog) {
		super("NoOfCells");
		myDialog = myChoiceDialog;
		numberCells = 50;
		myChoiceDialog.setNumberCells(numberCells);
		initializeControl();
	}
	
	private void initializeControl(){
		Spinner<Integer> spinnerNoOfCells = new Spinner(5,220,50);	
		spinnerNoOfCells.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
			myDialog.setNumberCells(Integer.parseInt(newValue));
		});
		this.setControl(spinnerNoOfCells);
	}

}

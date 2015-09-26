package toolsForGui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.input.MouseEvent;

public class RandomOption extends PersonalizationOption{

	private Control myControl;
	private InitialChoiceDialog myDialog; 
	
	public RandomOption(InitialChoiceDialog myChoiceDialog) {
		super("Random");
		myDialog = myChoiceDialog;
		initializeOption();
	}
	
	private void initializeOption(){
		CheckBox randomCheckBox = new CheckBox();
		randomCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->  myDialog.randomSim());
		myControl = randomCheckBox;
	}

	@Override
	public Control getControl() {
		return myControl;
	}


}

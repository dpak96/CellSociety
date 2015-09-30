package toolsForGui;

import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author emanuele
 *
 */
public class RandomOption extends PersonalizationOption {

	private InitialChoiceDialog myDialog;

	public RandomOption(InitialChoiceDialog myChoiceDialog) {
		super("Random");
		myDialog = myChoiceDialog;
		initializeOption();
	}

	private void initializeOption() {
		CheckBox randomCheckBox = new CheckBox();
		randomCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> myDialog.randomSim());
		this.setControl(randomCheckBox);
	}

}

package toolsForGui;

import java.util.ResourceBundle;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

/**
 * 
 * @author emanuele
 *
 */
public abstract class PersonalizationOption {

	private Label optionName;
	private Control myControl;

	public PersonalizationOption(String name) {
		ResourceBundle myResources = ResourceBundle
				.getBundle("resources.personalization");
		optionName = new Label(myResources.getString(name));
	}

	public Label getOptionName() {
		return optionName;
	}

	public Control getControl() {
		return myControl;
	}

	protected void setControl(Control control) {
		myControl = control;
	}
}

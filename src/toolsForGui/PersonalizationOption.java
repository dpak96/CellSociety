package toolsForGui;

import java.util.ResourceBundle;

import javafx.scene.control.Control;
import javafx.scene.control.Label;

public abstract class PersonalizationOption{
	
	private Label optionName;
	public abstract Control getControl();
	
	public PersonalizationOption(String name){
		ResourceBundle myResources = ResourceBundle.getBundle("resources.personalization");
		optionName = new Label(myResources.getString(name));
	}
	

	public Label getOptionName(){
		return optionName;
	}
}

package toolsForGui;

import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;

public class AllowPersonalizationOption extends PersonalizationOption {
	
	private InitialChoiceDialog myDialog; 
	
	public AllowPersonalizationOption(InitialChoiceDialog dialog){
		super("Personalize");
		myDialog = dialog;
		initializeOption();
	}
	
	private void initializeOption(){
		CheckBox personalizeCheckBox = new CheckBox();
		personalizeCheckBox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myDialog.allowPersonalization());
		this.setControl(personalizeCheckBox);
	}


}

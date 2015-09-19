package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class NextStepButton extends GuiButton{

	public NextStepButton(GUI gui) {
		super(gui);
		myButton = new Button(myResources.getString("NextButton"));
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.step());
	}
}

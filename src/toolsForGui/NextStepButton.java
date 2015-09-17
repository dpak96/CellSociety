package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class NextStepButton extends GuiButton{

	private Button myButton;

	public NextStepButton(GUI gui) {
		super(gui);
		myGui = gui;
		myButton = new Button("Start");
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.nextStep());
	}

	@Override
	public Button getButton() {
		return myButton;
	}
	
	

}

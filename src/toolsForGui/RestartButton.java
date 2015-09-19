package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class RestartButton extends GuiButton{
	
	public RestartButton(GUI gui) {
		super(gui);
		// TODO Auto-generated constructor stub
		myButton = new Button(myResources.getString("RestartButton"));
		myGui = gui;
		implementAction();
	}

	@Override
	protected void implementAction() {
		// TODO Auto-generated method stub
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.restartSimulation());
	}

}

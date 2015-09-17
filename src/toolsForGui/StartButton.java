package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartButton extends GuiButton{
	
	private Button myButton;

	public StartButton(GUI gui) {
		super(gui);
		myGui = gui;
		myButton = new Button("Start");
		this.implementAction();
		System.out.println("WHAt");
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.startSimulation());
	}

	@Override
	public Button getButton() {
		return myButton;
	}
	
	

}

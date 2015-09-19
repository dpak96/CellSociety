package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartButton extends GuiButton{

	public StartButton(GUI gui) {
		super(gui);
		this.setButton(myResources.getString("StartButton"));
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e -> this.getGui().startSimulation());
	}
	
}

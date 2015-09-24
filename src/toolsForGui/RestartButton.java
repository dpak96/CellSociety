package toolsForGui;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.scene.input.MouseEvent;

public class RestartButton extends GuiButton{
	
	public RestartButton(GUI gui) {
		super(gui);
		this.setButton(this.getResources().getString("RestartButton"));
		implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> this.getGui().restartSimulation());
	}

}

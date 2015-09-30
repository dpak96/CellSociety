package toolsForGui;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author emanuele
 *
 */
public class StartButton extends GuiButton {

	public StartButton(GUI gui) {
		super(gui);
		this.setButton(myResources.getString("StartButton"));
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> this.getGui().startSimulation());
	}

}

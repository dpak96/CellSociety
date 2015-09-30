package toolsForGui;

import javafx.scene.input.MouseEvent;
import cellsociety_team05.GUI;

public class ClearButton extends GuiButton {

	public ClearButton(GUI gui) {
		super(gui);
		this.setButton(this.getResources().getString("ClearButton"));
		implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> this.getGui().clearSimulation());
	}

}

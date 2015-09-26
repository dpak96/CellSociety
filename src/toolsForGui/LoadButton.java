package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.input.MouseEvent;

public class LoadButton extends GuiButton{
	
	public LoadButton(GUI gui) {
		super(gui);
		this.setButton(this.getResources().getString("LoadButton"));
		implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> this.getGui().loadSimulation());
	}

}

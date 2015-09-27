package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author emanuele
 *
 */
public class SaveButton extends GuiButton{

	public SaveButton(GUI gui) {
		super(gui);
		this.setButton(this.getResources().getString("SaveButton"));
		implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> this.getGui().saveSimulation());
	}

}

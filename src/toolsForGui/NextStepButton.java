package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author emanuele
 *
 */
public class NextStepButton extends GuiButton {

	public NextStepButton(GUI gui) {
		super(gui);
		// this.getButton() = new
		// Button(this.getResources().getString("NextButton"));
		this.setButton(this.getResources().getString("NextButton"));
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> this.getGui().step());
	}
}

package toolsForGui;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.scene.input.MouseEvent;

/**
 * 
 * @author emanuele
 *
 */
public class PauseButton extends GuiButton {

	private boolean paused;
	private GUI myGui;

	public PauseButton(GUI gui) {
		super(gui);
		myGui = gui;
		this.setButton(this.getResources().getString("PauseButton"));
		paused = false;
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED,
				e -> updateResumeButton());
	}

	private void updateResumeButton() {
		if (paused) {
			updateButtonText(this.getResources().getString("PauseButton"));
		} else {
			updateButtonText(this.getResources().getString("ResumeButton"));
		}
		paused = !paused;
		myGui.changeSimulationFlow();
	}

	private void updateButtonText(String s) {
		this.getButton().setText(s);
	}
}

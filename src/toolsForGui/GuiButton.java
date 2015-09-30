package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import cellsociety_team05.Simulation;
import javafx.scene.control.Button;

/**
 * 
 * @author emanuele
 *
 */
public abstract class GuiButton {

	private GUI myGui;
	protected ResourceBundle myResources;
	private Button myButton;

	public GuiButton(GUI gui) {
		myGui = gui;
		myResources = ResourceBundle.getBundle("resources.buttons");
	}

	public Button getButton() {
		return myButton;
	}

	public void setButton(String s) {
		myButton = new Button(s);
	}

	protected abstract void implementAction();

	public GUI getGui() {
		return myGui;
	}

	public ResourceBundle getResources() {
		return myResources;
	}

}

package toolsForGui;
import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PauseButton extends GuiButton{
	
	private boolean paused;

	public PauseButton(GUI gui) {
		super(gui);
		myButton = new Button(myResources.getString("PauseButton"));
		paused = false;
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.changeSimulationFlow());
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> updateResumeButton());
	}
	
	private void updateResumeButton(){
		paused = !paused;
		if(paused){
			myButton.setText(myResources.getString("PauseButton"));
		} else {
			myButton.setText(myResources.getString("ResumeButton"));
		}
	}
	
	
}

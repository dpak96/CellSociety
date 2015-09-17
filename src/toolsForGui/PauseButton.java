package toolsForGui;
import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PauseButton extends GuiButton{
	
	private Button myButton;
	private boolean paused;

	public PauseButton(GUI gui) {
		super(gui);
		myGui = gui;
		myButton = new Button("Pause");
		paused = false;
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.changeSimulationFlow());
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> updateResumeButton());
	}

	@Override
	public Button getButton() {
		return myButton;
	}
	
	private void updateResumeButton(){
		paused = !paused;
		if(paused){
			myButton.setText("Resume");
		} else {
			myButton.setText("Pause");
		}
	}
	
	
}

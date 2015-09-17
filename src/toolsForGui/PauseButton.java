package toolsForGui;
import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PauseButton extends GuiButton{
	
	private Button myButton;

	public PauseButton(GUI gui) {
		super(gui);
		myGui = gui;
		myButton = new Button("Pause");
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> myGui.changeSimulationFlow());
	}

	@Override
	public Button getButton() {
		return myButton;
	}
	
	
}

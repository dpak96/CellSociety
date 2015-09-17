package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;

public class RestartButton extends GuiButton{
	
	private Button myButton;

	public RestartButton(GUI gui) {
		super(gui);
		// TODO Auto-generated constructor stub
		myButton = new Button("Restart Button");
	}

	@Override
	protected void implementAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Button getButton() {
		// TODO Auto-generated method stub
		return myButton;
	}

}

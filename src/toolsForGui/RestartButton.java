package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class RestartButton extends GuiButton{
	
	public RestartButton(GUI gui) {
		super(gui);
		// TODO Auto-generated constructor stub
		this.setButton(this.getResources().getString("RestartButton"));
		implementAction();
	}

	@Override
	protected void implementAction() {
		// TODO Auto-generated method stub
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> this.getGui().restartSimulation());
	}

}

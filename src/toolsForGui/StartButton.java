package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class StartButton extends GuiButton{

	public StartButton(GUI gui) {
		super(gui);
		myButton = new Button(myResources.getString("StartButton"));
		this.implementAction();
		System.out.println("WHAt");
	}

	@Override
	protected void implementAction() {
		if(myGui != null){
			System.out.println("HO");
		}
		
		myButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> myGui.startSimulation());
	}
	
}

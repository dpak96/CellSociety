package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;

public abstract class GuiButton{

	protected GUI myGui;
	protected ResourceBundle myResources;
	protected Button myButton;
	
	public GuiButton(GUI gui){
		myGui = gui;
		myResources = ResourceBundle.getBundle("resources.buttons");
	}
	
	public Button getButton(){
		return myButton;
	}
	
	protected abstract void implementAction();
	
	
}

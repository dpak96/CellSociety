package cellsociety_team05;

import javafx.scene.control.Button;

public class guiButtons extends guiTools{
	/**
	 * WORK IN PROGRESS
	 */
	
	public final int START_BUTTON = 0;
	public final int PAUSE_BUTTON = 1;
	public final int RESTART_BUTTON = 2;
	public final int NEXT_STEP_BUTTON = 3;
	private String[] buttonNames = {"Start", "Pause", "Restart", "Next Step"};
	
	public guiButtons(GUI myGUI){
		
	}
	
	public Button createButton(int whichButton){
		Button myButton = new Button(buttonNames[whichButton]);
		return myButton;
	}
	
	
	

}

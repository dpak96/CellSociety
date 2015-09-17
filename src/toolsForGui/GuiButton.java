package toolsForGui;

import cellsociety_team05.GUI;
import javafx.scene.control.Button;

public abstract class GuiButton{
	/**
	 * WORK IN PROGRESS
	 */
	protected GUI myGui;
	private Button myButton;
	
	public GuiButton(GUI gui){
		myGui = gui;
		myButton = new Button();
	}
	
	protected abstract void implementAction();
	
	public abstract Button getButton();
	
	/*
	public final int START_BUTTON = 0;
	public final int PAUSE_BUTTON = 1;
	public final int RESTART_BUTTON = 2;
	public final int NEXT_STEP_BUTTON = 3;
	private String[] buttonNames = {"Start", "Pause", "Restart", "Next Step"};
	private final GUI myGUI;
	private Button nextStepButton;
	private Button flowButton;
	
	public guiButtons(GUI gui){
		myGUI = gui;
	}
	
	public Button createButton(int whichButton){
		Button myButton = new Button(buttonNames[whichButton]);
		return myButton;
	}
	
	
	private void startSimulation() {
		//mySimulation.start();
		paused = false;
		System.out.println("Start");
		myGUI.startSimulation();
	}
	
	private void changeSimulationFlow(){
		paused = !paused;
		if(paused){
			System.out.println("Paused");
			nextStepButton.setDisable(false);
			flowButton.setText("Resume");
		} else {
			System.out.println("Resumed");
			nextStepButton.setDisable(true);
			flowButton.setText("Pause");
		}
		myGUI.changeSimulationFlow();
	}
	
	private boolean paused;
	
	
	*/

}

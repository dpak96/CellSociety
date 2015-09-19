package toolsForGui;
import cellsociety_team05.GUI;
import javafx.scene.input.MouseEvent;

public class PauseButton extends GuiButton{
	
	private boolean paused;

	public PauseButton(GUI gui) {
		super(gui);
		this.setButton(this.getResources().getString("PauseButton"));
		paused = false;
		this.implementAction();
	}

	@Override
	protected void implementAction() {
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> this.getGui().changeSimulationFlow());
		this.getButton().addEventHandler(MouseEvent.MOUSE_CLICKED, e-> updateResumeButton());
	}
	
	private void updateResumeButton(){
		paused = !paused;
		if(paused){
			updateButtonText(this.getResources().getString("PauseButton"));
		} else {
			updateButtonText(this.getResources().getString("ResumeButton"));
		}
	}
	
	private void updateButtonText(String s){
		this.getButton().setText(s);
	}
	
	
}

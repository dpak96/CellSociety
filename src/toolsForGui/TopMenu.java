package toolsForGui;

import java.util.ResourceBundle;

import cellsociety_team05.GUI;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TopMenu {
	private MenuBar myMenu;
	private ResourceBundle myResources;
	
	public TopMenu(Stage myStage, String[] simulationTypes, GUI gui){
		myResources = ResourceBundle.getBundle("resources.window");
		myMenu = new MenuBar();
		Menu simulationMenu = new Menu(myResources.getString("ChooseSimulationMenu"));
		for(String sim: simulationTypes){
			MenuItem myMenuItem = new MenuItem(sim);
			myMenuItem.setOnAction(actionEvent -> gui.startNewSimulation(sim));
			simulationMenu.getItems().add(myMenuItem);
			
		}
		myMenu.getMenus().add(simulationMenu);
		myMenu.prefWidthProperty().bind(myStage.widthProperty());
	}
	
	public MenuBar getMenuBar(){
		return myMenu;
	}

}

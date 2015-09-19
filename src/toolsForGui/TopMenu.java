package toolsForGui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class TopMenu {
	private MenuBar myMenu;
	
	public TopMenu(Stage myStage, String[] simulationTypes){
		myMenu = new MenuBar();
		Menu simulationMenu = new Menu("Simulation");
		for(String sim: simulationTypes){
			simulationMenu.getItems().add(new MenuItem(sim));
		}
		myMenu.getMenus().add(simulationMenu);
		myMenu.prefWidthProperty().bind(myStage.widthProperty());
	}
	
	public MenuBar getMenuBar(){
		return myMenu;
	}

}

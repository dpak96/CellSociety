package cellsociety_team05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import toolsForGui.GuiBoxContainer;
import javafx.animation.Animation;

public class GUI {
	
	/**
	 * @author Emanuele Macchi
	 */
	
	private Stage myStage;
	private BorderPane root;
	private final String[] simulationTypes = {"Segregation", "Game of Life", "Predator-Prey"};
	private Simulation mySimulation;
	private long simulationSpeed;
	//private Button startButton;
	private GridPane myGridPane;
	private Grid myGrid;
	private Timeline animation;
	//private guiButtons myButtons;
	private GuiBoxContainer myBoxContainer;
	
	public GUI(Stage primaryStage){
		
		myStage = primaryStage;
		myStage.setTitle("Cell Society");
		myStage.setResizable(false);
		root = new BorderPane();
		Scene scene = new Scene(root, 553, 640, Color.WHITE);
		root.setTop(createTopMenu());
		myBoxContainer = new GuiBoxContainer(this, myStage);
		root.setBottom(myBoxContainer.getVBox());
		myGridPane = new GridPane();
		root.setCenter(myGridPane);
		createChoiceDialog();
		myStage.setScene(scene);
		myStage.show();
	}
	
	private void createChoiceDialog(){
		ArrayList<String> choices = new ArrayList<>(Arrays.asList(simulationTypes));
		ChoiceDialog<String> dialog = new ChoiceDialog<>(simulationTypes[0], choices);
		dialog.setTitle("Cell Society");
		dialog.setHeaderText("Please choose a simulation");
		dialog.setContentText("Simulation:");
		Node endApplication = dialog.getDialogPane().lookupButton(ButtonType.CANCEL);
		endApplication.addEventFilter(ActionEvent.ACTION, event -> System.exit(0));
		dialog.showAndWait().ifPresent(letter -> loadSimulationValue(letter));
	}

	private void loadSimulationValue(String letter) {
		//mySetup = new Setup(letter);
		//mySimulation = new SegregationSimulation(mySetup);
	}
	
	//handle menu somewhere else
	private MenuBar createTopMenu() {
		MenuBar topMenu = new MenuBar();
		Menu simulationMenu = new Menu("Simulation");
		for(String sim: simulationTypes){
			simulationMenu.getItems().add(new MenuItem(sim));
		}
		topMenu.getMenus().add(simulationMenu);
		topMenu.prefWidthProperty().bind(myStage.widthProperty());
		return topMenu;
	}
	
	private void anotherStartSimulation() {
	    //startButton.setDisable(true);
		System.out.println("Start");
		myGrid = new Grid(10,10);
                anotherTestGridPane();
                //mySimulation = new SegregationSimulation(new Setup(""),myGridPane,this,.5,.7,.1,myGrid);
                animation = new Timeline();
                KeyFrame frame = new KeyFrame(Duration.millis(1000),
                                              e -> this.step());
                              animation.setCycleCount(Timeline.INDEFINITE);
                              animation.getKeyFrames().add(frame);
                              animation.play();
	}
	
	private void step(){
	    System.out.println("step");
	    myGrid.preUpdateGrid();
	    myGrid.updateGrid();
	    updateDisplayedGrid();
	}
	
	public void startSimulation(){
		System.out.println("Start");
		anotherStartSimulation();
	}
	
	public void restartSimulation(){
		System.out.println("RESTART");
	    animation.stop();
	    myGridPane.getChildren().clear();
		anotherStartSimulation();
	}
	
	public void updateSimulationSpeed(long speed){
		System.out.println(speed);
		simulationSpeed = speed;
	}
	
	public void nextStep() {
		//to complete
	}
	
	
	public void dostuff(){
		System.out.println("HAHAHAH");
	}
	
	private void anotherTestGridPane(){
            System.out.println(myGridPane.getWidth());
            System.out.println(myGridPane.getHeight());
            double constant = myGridPane.getHeight()/4.0 - 5;
            for (int i=0;i<4;i++){
                for (int j=0;j<4;j++){
                    Rectangle add = new Rectangle(constant, constant, myGrid.getGrid().get(i).get(j).getCurrentColor());
                    GridPane.setConstraints(add, i, j);
                    myGridPane.getChildren().add(add);
                }
            }
	}
	
	public void updateDisplayedGrid(){
	    //System.out.println("update");
	    for (int k=0;k<myGrid.getGrid().size();k++){
	        List<Cell> cells = myGrid.getGrid().get(k);
	        for (Cell cell: cells){
	            Rectangle thisRec = (Rectangle) myGridPane.getChildren().get(k);
	            if(cell.myCurrentState==0){
	                thisRec.setFill(Color.BLACK);
	            }
	            else{
	                thisRec.setFill(Color.WHITE);
	            }
	        }
	        System.out.println("update");
	        for (int i=0;i<myGrid.getGrid().size();i++){
	            List<Cell> list = myGrid.getGrid().get(i);
	            for (int j=0;j<list.size();j++){
	                Rectangle thisRec = (Rectangle) myGridPane.getChildren().get((j*4)+i);
	                //System.out.println("GridPane: ("+i+","+j+")");
	                //System.out.println("Cell: ("+list.get(j).getX()+","+list.get(j).getY()+")");
	                if(list.get(j).myCurrentState==0){
	                    thisRec.setFill(Color.BLACK);
	                }
	                else{
	                    thisRec.setFill(Color.WHITE);
	                }
	            }
	        }
	    }
	}
	
	public Simulation getCurrentSimulation(){
		return mySimulation;
	}
	
	public void changeSimulationFlow(){
		if(animation.getStatus() == Animation.Status.RUNNING){
			animation.pause();
		} else {
			animation.play();
		}
	}
	
	public long getSimulationSpeed() {
		return simulationSpeed;
	}
}

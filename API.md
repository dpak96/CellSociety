#Simulation
public class Cell
	preUpdateCell applies the rules of the simulation for determining the cell’s next state but does not yet change the actual state of the cell.
	public methods preUpdateCell()
public class Simulation
	initGrid initializes the grid of cells based on an xml file
	public method initGrid()
	setUpCells changes the cells in the grid to a specific type based on the current simulation
public method setUpCells
public class Grid

#Configuration
public class XMLReader 
readFile reads in the XMLFile and will initialize the variables set up for parameters, simulation names, and cells
Getters will get variables: getParams, getCells, getSimulationName, getGridWidth, getGridHeight
public class XMLEditor
	readFile reads in the XMLFile to be edited
editFile will add nodes using addSimulation, addParameters, and addCells. Needs to run readFile first. 
public class Setup
	getSimulation gets the simulation type that is being setup
public class SimulationFactory
	chooseFileFromUserInput will choose a file to run based on user input,
makeSimulation initializes the simulation based on the file selected by chooseFileFromUserInput

#Visualization

```java
public class InitialChoiceDialog{
	//initializes the choice dialog to personalize the simulation 
	public void display(){}
}
public class GUI { 
//creates the gui
	public GUI(Stage primaryStage) throws SimulationException {}
//loads the default simulation for the given 
	public void startNewSimulation(String simulation) {}
//creates a new simulation with personalized options
	public Object loadPersonalizedSimulation(){}
//saves the simulation on an xml file - takes a snapshot of the current sim
	public void saveSimulation() {}
}
public class GuiChoiceDialog{
	//displays the current choice dialog
	display(){}
	//add personalization options 
	addPersonalizationOption(){}
}
public class PersonalizationOption{
	//gets the name of the personalization 
getOptionName(){}
//returns the Node object allowing the user to choose the option
getControl(){}
}

public class GuiButton {
	//returns the resource bundle that contains the info for the buttons 
getResources(){}
//returns the Button object
getButton(){}
//initializes the myButton variable creating a Button with label s 
setButton(String s){}
}
```

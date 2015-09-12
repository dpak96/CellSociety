#Design Document

Lucas Donaldson (lad47), Emanuele Macchi (em186), and Daniel Pak (drp21)

##Introduction

The goal of this project is to create a system to simulate different 
types of cellular automata (CA). The system should implement the 
logic behind different CA’s and display the results to the user. 
Our system should be most flexible in the implementation of new 
types of CA’s to allow it to expand easily. The design architecture 
we have planned involves a series of interconnected classes, some 
of which will be open to expansion and others which will be closed 
to modification. The different simulation types will be based upon 
an abstract class that will be extendable to allow implementation 
of new CA’s. The simulation will be closed to other types of 
modification. A grid of cells will be displayed and will be updated 
based on the rules of the given simulation. The user will be able 
to choose options from a GUI to control the type of simulation and 
the speed with which it updates. When switching simulations, the 
program will simply use a different extension of the general 
simulation class which will contain the rules for updating the state 
of that simulation. This way, new simulations can be added to the 
system by extending this general simulation class, while the rest 
of the program will require no modification.

##Overview

Our overall program will be designed in multiple classes. The first 
class to start everything up will be the Main class. Here it will 
start and run the entire program, including the classes for Setup 
and the GUI. The Setup and GUI classes will interact with each other 
by passing information. Initially, the Main class will call the GUI 
class, setting up an initial graphic interface (which has no 
simulation loaded yet). The GUI class will start running and allow 
for user inputs, including an information display of the simulation 
type and name, sliders to adjust the speed of the simulation, and 
buttons. The buttons will be there for stepping the simulation, 
running the simulation, pausing the simulation, and loading a new 
XML file for a different simulation. It will have methods to display 
these buttons/sliders, the simulation grid, and details. It will 
also have methods to take in user input from these buttons/sliders 
and pass on this information to the simulation. The GUI will also 
have to display the information from the simulation depending on 
which simulation is being run (for example, we might care to display 
the percentage of red/ blue cells, or which cells already caught 
fire). This will be displayed in the showCurrentInfo method of the 
GUI class. Once the user has inputted the XML file, the GUI will 
launch the Setup class, which will read the XML file and initiate 
the simulation, game, and cells. Each of these is its own class. 
The Setup class will initialize a grid with the cells in locations 
according to the information on the XML file. It will then create 
the appropriate Simulation from the hierarchy and pass the Grid to 
the simulation. The GUI class will allow the users to modify certain 
parameters of the simulation, such as the speed at which the cells 
are updated. Cells and Simulation will be a hierarchy of classes 
that differ in functions or rules depending on what type of 
simulation the XML file demands. Cells will be a class for each 
individual cell that contains an array of its neighbors, its 
coordinates, and its states. Cells will also have methods to 
getNeighbors. The mechanism for updating the state of a Cell will 
be divided in two steps. This is because we don’t want to update 
a cell instantly, as it will affect how the neighboring cells 
update their states. For different simulations, the cells may need 
different states. These states will also be associated with a color 
(depending on the simulation and on the state). Grid will be a class 
that contains all of the Cells in a data structure, like an 
ArrayList, as well as a method to update the grid after all the 
cells have had their states altered. Ultimately, the Simulation 
class will be stepping through the simulations. It will take the 
Grid initialized by Setup and take steps through the simulation or 
run through it depending on the user input to the GUI. As a result, 
the simulation class will also have the timeline simulation loop 
within it as well as methods to start, stop, and step the 
simulation. It will also have the updateState method that will 
take in the rules of each simulation and run through the grid to 
change cell states based on these rules. In order to increase 
flexibility of our program, we will be extending the Simulation 
class to multiple simulation types. At the moment, we have the 
four different types of simulations to consider, so we have four 
different extensions that will have different rules to run the 
updateState method. Each extension will have it’s own information 
that will pass to the GUI class to display. For example, the 
chance of burning in the fire example will be displayed in that 
simulation. As mentioned before the Cell class will also have 
these extensions due to the possibility of needing different states 
to change between. In order to accommodate the user wanting to 
switch simulations after initializing the program, the GUI and 
Setup classes will have methods to reset the simulation. After 
reading a user input to reset the simulation in the GUI, the Setup 
will proceed to reset the simulation and run a new one by clearing 
the Grid and initializing a new simulation according to the XML 
file given. In addition to these classes, we will be required to 
pass details to the GUI about the simulation itself. To do this, 
we will create a Details class that will contain the specific 
details about the simulation that was given to it by the XML file. 
These descriptions will be initialized by the Setup class and 
passed to the GUI to be displayed. Depending on which simulation 
is being run, there will be different parameters and conditions 
for the cells, which will be kept in the Details class to be 
printed by printDetails. 

*NEED TO ADD DIAGRAM OF CLASSES* 

##User Interface
The user will interact with the system through the onscreen GUI. 
The GUI will be comprised of sliders for controlling speed and 
other potential parameters of the system, and buttons for pausing 
and resuming the simulation. These buttons will be located below 
the grid area where the simulation is displayed. Users will also 
be able to change simulations by choosing options from a dropdown 
menu. The dropdown menu will be located above the grid area, 
immediately below the title bar. The user may opt to select an XML 
file containing information about the simulation they would like 
to run. This information includes the name of the simulation and 
any parameters specific to that simulation. If the user selects a 
file containing the name of a simulation that is not supported by 
the system, an error message will be generated. A similar message 
will be generated if the user specifies a parameter that is out of 
bounds or otherwise impossible to apply to the simulation. 

*NEED TO ADD IMAGE OF GUI* 

Design Details
Chronologically, the first class that will be called in our project 
is the Main class. This class is responsible for initializing the 
GUI and Setup classes. The GUI class will be responsible for 
selecting the simulation to run based on user input. The user will 
select an XML file, which will be passed to the Setup class. The 
Setup class will first parse the type of simulation, and then create 
a new instance of that Simulation. Setup will also initialize the 
Grid class based on the size specifications in the XML, and it will 
then pass this grid to the GUI to be displayed. The Grid must first 
create an array of Cells. These Cells will be extensions of the 
general Cell class and will be specific to the given simulation. 
This process of events will be carried out by Setup each time a new 
simulation is chosen. Once the Grid has been passed to the GUI and 
the Simulation, the Simulation will begin to update the system. Each 
extension of the Simulation class will have its own update 
functions, which will implement the “rules” specific to that 
simulation. The Simulation steps through the Cell array in Grid and 
determines each Cell’s next state. Once the entire array has been 
analyzed, the Grid will update each cell to its next state. The 
Simulation class is open to extension in order to contain new rules 
for new simulations. The Cell class is open to extension in order 
to account for different states required by new simulations. Each 
class was created in order to divide up the work required by the 
system. The Main class is required to start the program. The GUI 
class is necessary to display the results of the system. The Setup 
class is needed to instantiate the correct type of Simulation and 
to determine the size of the Grid. The Simulation class is required 
to control the functionality of the system and is needed in order 
to extend new simulations. The Grid class was designed to store the 
array of cells and to update the appearance of those cells. The 
Cell class was made to hold the current and next state of each 
cell, and to assist the Simulation class with determining their 
next state. In order to accommodate the different cases of 
simulations and cases, we took the following into consideration. 
For different cases of cells (center and edge), we will create a 
Cell class that contains an array of neighbors, which will help 
determine how to update the state of the cell. By calling the 
method updateState, the program will loop through the neighbors 
array, and then according to the number of live/dead neighbors, it 
will modify the variable nextState (this also depends on whether 
the state of the cell is dead or alive). The procedure for edge 
cells is similar to the previous case except for one detail: the 
array of neighboring cells will have less elements. When we want 
to update the cells in the simulation, we will have the Simulation 
class call updateState. Then Grid would call the method updateGrid. 
It loops through the cells and reached the middle cell. The cell 
then calls the method updateState. This would count the number of 
active neighboring cells, and update its parameter nextState. This 
allows us to update the state of all the cells correctly (without 
any conflicts with cells that have already been updated). Grid 
would then call update cells, and every cell would change its 
state to nextState, effectively also visually updating their 
position. The Simulation class would then call the method showGrid, 
which will display graphically the result. In order to set 
parameters for the simulations, the Setup class would create a new 
simulation (which will be an extension of the basic Simulation 
class), passing in the specific parameters. The specific simulation 
class will then update the rules of the Cell, by assigning the value 
of the parameter to a private final variable that will store this 
information (this variable will be used every time we create a new 
Cell class). This will also be passed to the Details class to be 
later displayed by the GUI. Lastly, to switch simulations, the user 
would have to choose a simulation from a drop down menu. The GUI 
class would then handle the user input and passing it through a 
resetSetup method (this method will be called by the currentSetup 
variable). 

####Classes

basic implementation package:

GUI (closed)
* createGUI
* currentSetup
* currentSimulation
* currentInfo: variables that we need to keep track of, depends on the simulation (*)
* updateInfo: updates information the simulation type and the name 
* showCurrentInfo, shows the information graphically 
* displayMainWindow(displays the window layout, no simulation or grid necessary. 
* displayGrid: display grid and partition cells according to quantity
* handle user input (sliders for speed, buttons for everything else)
* launchSetup: creates setup according to user input

GRID (closed)
* array Cells
* updateGrid: displaying the states of the cell - this is divided in two steps, first you update the state of each cell according to the rules (preUpdateCell), and then it finalizes the update in a second moment (updateCell)
* getGrid : returns the array of Cells
* drawGrid: it will display the grid, checking the state (and the color) of each cell 

CELL (open)
* Array neighbors: list of neighboring cells
* int coordinates: the position of the cell
* int state: Current state of the cell (this will also determine the color of the cell)
* int nextState: next state of the cell (after first step)
* getNeighbors: getting the neighbors of the cell
* getState: get the state of this cell
* preUpdateCell: update nextState of this cell (avoids affecting neighboring cells)
* updateCell: sets state = nextState

SIMULATION (set parameters in GRID and CELL) - open to extension
* Variable Rules: 
* Variable Grid
* Timeline - simulation loop 
* Simulation info
* startSimulation 
* stopSimulation
* updateSpeed
* step: single step of simulation 
First step would be updateState
Second step would be updateGrid
* run: run steps continuously (take a boolean?)
* updateState: determines next state of each cell according to rules (after first step), which will be overridden by each simulation subclass. 
* showGrid: calls drawGrid, displays graphically the Grid 

DETAILS (contains information about each simulation read from the XML file to be printed in the GUI)
* Variable Details: list of details that change depending on which subclass of DETAILS is being used
* printDetails: Prints out the details given from the XML file

SETUP 
* Read XML file and set up appropriate simulation 
* initializeGrid: Initializes the GRID/CELL based on XML file
* Passes GRID to appropriate Simulation
* resetSetup: t(input that determines which xml file to read) : Read in a new XML file and reinitialize

MAIN (open)
* Runs everything

###Use Cases
 
1. Apply the rules to a middle cell: set the next state of a cell to 
dead by counting its number of neighbors using the Game of Life rules 
for a cell in the middle (i.e., with all its neighbors)

The Cell class contains an array of neighbors, which will help 
determine how to update the state of the cell. By calling the method 
updateState, the program will loop through the neighbors array, and 
then according to the number of live/dead neighbors, it will modify 
the variable nextState (this also depends on whether the state of 
the cell is dead or alive)

2. Apply the rules to an edge cell: set the next state of a cell to 
live by counting its number of neighbors using the Game of Life 
rules for a cell on the edge (i.e., with some of its neighbors 
missing)

The procedure is similar to the previous case except for one detail: 
the array of neighboring cells will have less elements. 

3. Move to the next generation: update all cells in a simulation 
from their current state to their next state and display the result 
graphically

Simulation would call updateState. Then Grid would call the method 
updateGrid. It loops through the cells and reached the middle cell. 
The cell then calls the method updateState. This would count the 
number of active neighboring cells, and update its parameter 
nextState. This allows us to update the state of all the cells 
correctly (without any conflicts with cells that have already been 
updated). Grid would then call update cells, and every cell would 
change its state to nextState, effectively also visually updating 
their position. The Simulation class would then call the method 
showGrid, which will display graphically the result. 

4. Set a simulation parameter: set the value of a parameter, 
probCatch, for a simulation, Fire, based on the value given in an 
XML file

The Setup class would create a new fire simulation (which will be an 
extension of the Simulation class), passing in the parameter 
probCatch. The fire simulation class will then update the rules of 
the Cell, by assigning the value of probCatch to a private final 
variable that will store this information (this variable will be 
used every time we create a new Cell class). 

5. Switch simulations: use the GUI to change the current simulation 
from Game of Life to Wator

The user would have to choose a simulation from a drop down menu. 
The Gui class would then handle the user input and passing it 
through a resetSetup method (this method will be called by the 
currentSetup variable). 

##Design Considerations
The most obvious design consideration that will need to be 
consistently addressed is the need to keep the project flexible and 
open to extension. More specifically, we need to address a few 
low-level issues, such as how to format and parse XML files. We 
also need to refine some of the relationships between classes, 
specifically between Grid and Simulation and between Setup and GUI. 
These classes have well-defined responsibilities, but it is not yet 
entirely clear how information is passed between them. We want to 
avoid global variables at all costs while also passing information 
as parameters when possible. We are currently operating under the 
assumption that our GUI will not need to radically altered when 
extending the project to new simulations. For example, we do not 
plan to add new buttons or sliders to the GUI based on additional 
parameters of new simulations. We also want to prevent certain 
classes from containing all the functions and details, so we will 
try to find logical ways to separate the responsibilities into 
different classes. 

Team responsibilities
Emanuele - GUI / xml
Daniel - Simulation/Setup
Lucas - Cell/Grid
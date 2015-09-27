#Cell Society
Team Members: Lucas Donaldson, Emanuele Macchie, and Daniel Pak
Date Started: September 10, 2015
Date Finished: September 27, 2015

## Team Roles
While each team member worked together to fix bugs and connect different project components, each member also had their own area of focus when working on the project.
* Lucas Donaldson: Focused on simulation logic and manipulating grid elements.
* Emanuele Macchi: Focused on creating GUI and designing the appearance of the program.
* Daniel Pak: Focused on reading and writing to XMLs and setting up simulations from them.

##Resource files
1. button.properties contains the string that we need to display the buttons'
2. personalization.properties contains the string needed to create the personalization menu
3. window.properties contains the general information for the window layout, such as title
4. XML files contain pre determined configurations

##Instructions
To run the program is necessary to run the class Main.java, which will then create an interactive window that will 
allow the user to create the desired simulation. The user will not be able to personalize its own simulation, as
those features have not been fully implemented. 

##Known bugs
The personalized simulation does not work, as we weren't able to create a way to design a new one with certain 
parameters. Ant simulation has a strange behavior after some time, as all the ants gather around the nest and don't really move anymore. Furthermore, once the user tries to create a new any by pressing on a cell, it will create a problem.

##Features
The user can change the state of a cell by clicking on it. The buttons on the right allow to stop, pause and restart the simulation. It is also possible to change the speed of the simulation by utilizing the slider on the side. The user can acces the drop down menu on the top left to change the current type of simulation.

##Impressions/Suggestions
We thought this project was very interesting. In particular, the implementation of the simulations was very fascinating. The amount of work was fair. However, we did find the expectations, especially for the second part, rather unclear. It would have been helpful to have more clarity on that. 

##Simulation details

##Ant Foraging
Simulation Tag: AntForaging
Maximum number of ants: 20
Maximum home pheromones: 1000
Maximum food pheromones: 1000

## Segregation
Simulation Tag: Segregation
Satisfaction Tag: similar
Ratio Tag: ratio
Empty tag: empty

##Fire
Simulation Tag: Fire 
probCatch Tag: probCatch

##Predator Prey

Simulation Tag: PredatorPrey 
Predator Reproduction Rate Tag: predatorreproductiontime 
Prey Reproduction Rate Tag: preyreproductiontime Predator 
Energy Rate: energy

##Game Of Life
Simulation Tag: GameOfLife

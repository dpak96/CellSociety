###Discussion

##Sugar Simulation, Predator-Prey simulation, GameOfLimitation 
We factored the method setUpCells, pulling it out from each single simulation extension class. In particular, 
we noticed that the only thing that changed was a paramater that modified the math.random() value. We thus 
decided to implement the method in the Simulation abstract class. We thought it would be a much more effective
way to do this. 

##Predator Prey simulation
There was a lot of duplicate code, so we extracted a new method that had the same function, allowing up to save 
up space and make the code more readable. 
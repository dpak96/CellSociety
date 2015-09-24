# Discussion

Lucas Donaldson (lad47) and John Dai (rd122)

## PredatorPreyCell

In the PredatorPreyCell class, we removed some duplicated
code in the leave() method. We chose this section to refactor
because it was very noticeably duplicated and was easy to 
fix. The duplicated methods only differed in using two 
different constant ints, so we added a parameter to the 
method to deal with this method and just passed the 
appropriate int in each call to the method.

## GameOfLifeSimulation, SugarSimulation, and PredatorPreySimulation

In these classes, we removed some duplicated code in the
overridden setUpCells method. These three simulations 
differed in their implementation of this method by one int, 
so we changed it to a parameter and pulled the method back
to the super class. Then, each extension of the class just 
called the super class function with the particular int as
a parameter. 
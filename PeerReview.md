#Peer Code Review 
Code reviewed by Brandon Ho (bh147)

*myDirty variable is always set to false, so instead of setting it in subclasses, we set it in the abstract class for cells. 

*PredatorPreyCell uses a for loop to get neighbors of a specific state. Instead, we just added a helper method in the abstract class to get a list of neighbors of a specified state. Used this instead of the for loop to search for neighbors in the PredatorPreyCell. 

*PredatorPreyCell and SugarCell have similar structures for getting neighbors. Wanted to generalize methods, but there are other differences in the middle. Working on how to fix it. 
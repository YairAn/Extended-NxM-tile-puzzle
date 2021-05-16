# Extended-NxM-tile-puzzle

this project attempt to solve the Extended-NxM-tile-puzzle.
solve the problem means to return a path from the initial state to the goal state
with optimal cost or return that there is no path if the board have no solution.
the main differents of the extended problem here than the classic problem are:
* any size of board of n x m can be an input.
* an extention to not only one empty tile,could also be two empty tiles.
* the steps are whighted ,not all steps cost the same.
 
to solve this puzzle i implemented and used several algorithms each have its pros and cons.
- BFS
- DFID
- A*
- IDA*
- DFBnB

the huristic function i used for the last three algorithms above were "Manhattan distanse".
 

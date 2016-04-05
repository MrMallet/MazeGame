##Maze Game
##James Moloney 
##G00304978
##[https://github.com/MrMallet/MazeGame](https://github.com/MrMallet/MazeGame)
### Artificial Intelligence
### 4th Year BSC Software Development
### John Healy ~ Lecturer

####Brief
Create a game based on the Mazogs theme with the set of features as follows:
* The game should generate a random 60x60 maze with one or more exits. The player
character should be placed in the maze as far from the exit(s) as possible. The
objective of the game should be to find a way out of the maze.
* The game should be graphical and use arrow keys and other options to move around.
* The maze should be patrolled by spiders or other characters that should be
threaded, i.e. the creatures should move independently through the maze. Each
character should use one or more traversal / search algorithms to control its
movements.
* A number of weapons should be available in the maze. Users should be able to select
from different types of weapons, e.g. a sword, grenade or even an A-bomb (the latter
two could use a depth-limited DFS to destroy everything within a limit of n moves).
You could even use a depth-limited DFS as a "radar" to show where "life forms" exist
nearby.
* A number of prisoners or something similar should also be available in the maze.
Interacting with one of these should fire a heuristically informed algorithm that
displays a path through the maze for a limited amount of time or steps.

####Contents
1. About
2. Playing the game

####1 - About
This gameproject has been made for my Artifical Intelligence module in GMIT. The idea behindtheproject is to demostrate a working knowledge of the course completed throughout the semester. This games main character, "the fuzzy hero" attempts to save his "fuzzy girlfriend" form a maze. However there are demon gargoyles about and there will be some fuzzily diddly doodilies! 

####2 - Playing the game
The game uses jFuzzyLogic.jar and jFuzzyLogic_core.jar libraries. The user will run the GameRunner.java to play to load and play. 
The game game is played using the directional buttons on your keyboard and the 'z' key to zoom in and out. 
As the user makes his way around the game he'll see swords and fruit, both essential to beating the gargoyles. AS he collects both, his character will get stronger and more easily able to defeat the enemies. However the weaker he is the more damage the gargoyles will inflict. Unfortunately there are no working algorithms attached to the enemies, the clue feature or the building of the maze itself. 
In the Zoom out function the user can track their movements and plan ahead, the player is the yellow block and the girl the red. However the gargoyles will remain hidden in this view so be careful!!  

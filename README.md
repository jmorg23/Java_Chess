# Java_Chess
A Chess Engine Entirely written in Java. 
Uses Maven so make sure to build before ran.

In single player it is 50% chance that you will be white or black.
NOTE: new game button does nothing, and there are a few bugs

As of now there is only hard mode for single player that works as follows:

- The Ai takes the board as is and makes a copy
- It then tells each of its pieces to give their move set (From the class Move)
- Each piece grabs usefull infromation about each of there moves such as if it can check, if it can take a piece, if it can be taken and so on.
- The move also has a few that are in rounds ahead of it such as can be taken but can take back for more points.
- The Ai then takes the information and it ranks each move, giving each move a certain amount of points for each thing the move entails.
- Now the Ai finds the highest ranked points and plays it on the real board.

There is also a multiplayer function that is just pass and play.


Cell Society Design Plan
==============

Michael Li-mxl3
Daniel Rubinstein- dr148
Alex Blumenstock - ab546

Introduction 
=======

The goal of this program is to dynamically model different versions of Cellular Automata (Conway’s Game of Life, Fire, and Schelling’s Model of Segregation). In order to do so, certain parts of the program have to be particularly flexible. These flexible components include the ability to add new types of games (i.e. adding a Wator game), and the ability to account for different types of inputs (i.e. probCatch for fire vs. % segregation threshold for Schelling’s). This means we want to keep the types of games, their rules (as in autonomous rules of the model and different parameters that the user can choose), and respective types of cells for those games open and available for extension, and the rest of the program, such as the interface (the actual part that displays the cells once we have selected all our parameters and processed the cells) and the game updating/processing portion, closed.

Our program will work in a simple, linear fashion. First, the player chooses what type of Cellular Automata game they want to play. Then, they will decide what the cell-distribution will look like. Once this is decided, the player will no longer have any power, and the program will run on its own, as to be expected for cellular automata games.

Overview 
=======

Our program will have a central interface class that will generate the user interface and display the simulation.  This class will call on one of many “Game” subclasses, created from an abstract Super Class, to get the 2 dimensional array of cell locations.  These games classes will keep track of each cell’s location and determine where   Each game will also have a corresponding cell subclass from an abstract “Cell” superclass. 
The Cell class will keep track of the cell’s location and state, which the Game Class will use to calculate the appropriate next state.  There will also be a separate “Input Processor” class that will allow the user to choose which simulation they want to run, prompt them for information about the simulation and also extract that same information from an XML file.

![Components](https://i.imgur.com/msa1C1v.jpg)

User Interface
=======


![Layout](http://i.imgur.com/VhUr4QH.jpg)

Our user interface will use buttons to choose the type of game they want, and  sliders to describe parameters. Using the sliders, they can change the size of the grid, the ratio of certain cell states (distributed randomly), the % of empty cells (also distributed randomly), and initial parameters (like prob. catch for fire, or % segregation threshold for Schelling’s). Once this distribution is decided, the player starts the simulation by pressing a button, and the game plays on its own.

Erroneous situations include XML format mismatch. If the XML is formatted in an unexpected manner, then the program will not be able to read the file. An error could also occur if the file chosen by the user is not an xml file.

Design Details
=======

The interface class will first call the input processor to ask the user which game they want to play.  Based on which game they select, that particular game class will be initialized.   The interface class will create a GUI with sliders that the user can use to set parameters for the simulation (ex: probability of fire spreading, how many red/blue squares for the segregation game, etc.).  It will prompt them for the size of the grid, and other questions that will be included in that particular Game subclass.  The Game class will also contain default values if the user does not wish to customize the simulation.  There will also be an option to load an XML file, which will update the game accordingly.   Above the sliders, there will be a scene of the initial state of the simulation.  Users can click on any cells to alter their states, if they want to test different configurations. 
            The Game abstract class will have a public method called getNextState() which always return a double array of Cells for the next step of the simulation.  This method will call various private helper methods to evaluate each Cell and determine what its next state should be.  
	

Apply the rules to a middle cell: set the next state of a cell to dead by counting its number of neighbors using the Game of Life rules for a cell in the middle (i.e., with all its neighbors)
The Game class’s getNextState() method will iterate through the double array currentStates, and for each cell with position [i][j], it will examine the states of the cells at [i+1] [j], [i-1] [j], and so on to ensure that every neighbor is included.  The appropriate calculations will be done, and in a new double array nextStates, the Cell at position[i][j] will be assigned the correct state(alive or dead).  Once the rules have been applied to every cell, and nextStates is full, nextStates will be returned to the Interface class for display.


 Apply the rules to an edge cell: set the next state of a cell to live by counting its number of neighbors using the Game of Life rules for a cell on the edge (i.e., with some of its neighbors missing)
This process will be almost identical to the previous step.  The only difference is that, when checking neighbors, there will be an if statement:
if(i+1 !< currentStates.length), if(i-1>=0), and so on to avoid NullPointerExceptions.  Then the program will proceed as before to determine the next state of the cell, and return the double array.  



Move to the next generation: update all cells in a simulation from their current state to their next state and display the result graphically
This will also be done as outlined in the first case.  A second double array will be generated and returned to the interface class, which will iterate through the double array, determine what size to make the cells( based on the size of the array and the desired size of the scene), and draw them on the scene using colors acquired from the cell class.


 
Set a simulation parameter: set the value of a parameter, probCatch, for a simulation, Fire, based on the value given in an XML fire
The program would require the XML to have its largest tag be the name of the specific simulation it planned to run (ex: Fire) and then have each parameter be an element within that tag.  The Input processor class would read the file and determine what the initial states of the cells should be, which it would then send to the Game class.  probCatch would be an instance variable in the FireGame class, which would be set by calling a getter method from the input processor class.


Switch simulations: use the GUI to change the current simulation from Game of Life to Wator
The switch simulation feature would take place in the interface class, where it would create a new instance of the second Game class(in this case, Wator).  It would then iterate through the double array and replace each Cell of the old type with one of the new type.  Every simulation we’re building has an “empty” Cell, so empty LifeCells would be replaced by empty  WatorCells.  For this case, since LifeCells only have two states, any “alive” cells would be randomly assigned either Predator or Prey as their state,  but for FireCells and SegregationCells, which have 3 types, a one-to-one correspondence could be drawn (predators become fire, prey become trees). Once the double array is updated, the new Game class proceeds as normal and generates the next states.


Design Considerations 
=======

-We are separating the game input, the interface (which displays the game), and the game processor. This will allow us to reuse components and add new simulations more easily: for every type of game, the xml file that we read in is formatted in the same way, and the process of displaying the grid is the same for all types of games (since the image associated with each cell is contained within the corresponding entry in the array). 
The main difference for each model will be the rules of how the model works, and by isolating those into the game processing class, our program will be easily extensible.

-We are choosing to display the cells as a 2D array. Storing data this way is conducive to representing it as a grid in the interface; however, it will force us to work around out-of-bound errors when dealing with the cell's neighbors.

-We will store the cells as a 2D array of objects (cells). Each different type of cell will have it’s own class, which will contain among other things its appearance (the color associated with it) and the cell-types of its neighbors. By storing information within the cell, it will be easier to manipulate than for example having multiple arrays that each associate a cell with an index of the array and store a certain piece of information about the cell in that index of the array.

Team Responsibilities
=======

Michael will work on the user interface. Alex will work on the input processor. Daniel will work on constructing the different types of cells and modelizing the movement of the cells on a grid. We will work as a group on resolving large problems or difficulties experienced by team members. Also, each person will be expected to have a high-level understanding of everybody else’s code.


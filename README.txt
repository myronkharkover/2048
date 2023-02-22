/**********************************************************************
 *  Instructions for how to program      
 **********************************************************************/

execution: java Game

To play the game u have to utilize the w, a, s, and d keys on your keyboard
where w = up, a = left, s = down, d = right. Blocks randomly pop up on the
grid with values of 2 and 4, and which every direction u indicate the blocks 
to move, the blocks of like numbers will combine and form a new block containing
the sum of the 2 previous blocks, and the 2 previous blocks will disappear
leaving this new block. This happens until there are 16 blocks on the grid
with no moves left, or until a block reaches a value of 2048 and the user wins
 
 /**********************************************************************
 * A brief description of each file and its purpose              
 **********************************************************************/
 
Game.java - class that draws the background of the game and contains
            the main function to actually run the game
GameFunction.java - the class that implements the GameFunctionInterface
                    and contains all of the methods necessary to the
                    mechanics of the game
GameFunctionInterface.java - interface for GameFunction that defines the methods:
                                 boolean won(); boolean lost();
                                 void moveBlocks(char c);
                                 and void drawBlocks();
Block.java - class that implements the BlockInterface and that contains the 
             methods necessary for the blocks and necessary to utilize 
             information from the blocks
BlockInterface.java - interface for Block that defines the methods: void draw();
                      int getBlockValue(); void addBlocks(); 
                      boolean shouldAddBlock(Block b); int getXVal();
                      void setXVal(int x); void addToX(int x);    
                      int getYVal(); void setYVal(int y); 
                      and void addToY(int y);

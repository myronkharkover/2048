/**
 * Name: Myron Kharkover
 * Pennkey: myronk
 * Execution: none
 * 
 * Description: implements the GameFunctionInterface interface to implement
 *              every method necessary to the functionality of the game
 *              excluding restarting and drawing the outline of the grid as
 *              they are written in Game.java
 */
public class GameFunction implements GameFunctionInterface {
    
    private Block[][] blocks;
    
    private int moveCount;
    private int blockCount;
    
    public static final int[] XPOS = {95, 215, 335, 455};
    public static final int[] YPOS = {105, 225, 345, 465};
    
    /* Description: constructor that builds a new grid of blocks
     * Input: No inputs
     */
    public GameFunction() {
        this.blocks = new Block[4][4];
        this.moveCount = 0;
        addBlocksToGrid();
        addBlocksToGrid();
    }
    
    /* Description: getter method for movecount
     * Input: No inputs
     */
    public int getMoveCount() {
        return this.moveCount;
    }
    
    /* Description: draws all the blocks on the grid
     * Input: no inputs
     */
    public void drawBlocks() {
        PennDraw.setFontSize(33);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].draw();
                }
            }
        }
    }
    
    /* Description: adds a new block to the grid in a random location
     * Input: no inputs
     */
    private void addBlocksToGrid() {
        while (true) {
            int i = 0;
            double irandomNumber = Math.random();
            if (irandomNumber < .25) {
                i = 0;
            } else if (irandomNumber < .50) {
                i = 1;
            } else if (irandomNumber < .75) {
                i = 2;
            } else if (irandomNumber < 1) {
                i = 3;
            }
            
            int j = 0;
            double jrandomNumber = Math.random();
            if (jrandomNumber < .25) {
                j = 0;
            } else if (jrandomNumber < .50) {
                j = 1;
            } else if (jrandomNumber < .75) {
                j = 2;
            } else if (jrandomNumber < 1) {
                j = 3;
            }

            if (blocks[i][j] == null) {
                blocks[i][j] = new Block(XPOS[j], YPOS[i]);
                this.blockCount++;
                return;
            }
        }
    }
    
    /* Description: updates the x and y positions of the blocks to their
     *              the correct position
     * Input: no inputs
     */
    private void updatePosition() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setXVal(XPOS[j]);
                    blocks[i][j].setYVal(YPOS[i]);
                }
            }
        }
    }
    
    /* Description: method to check if it is possible for the block to move
     *              by checking it the block itself is null, if it will hit
     *              a wall of the grid, or if there is a preexisting block
     *              in the path
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: boolean true or false, true if the block can slide, 
     *         false if the block cant
     */
    private boolean canSlide(int i, int j, char c) {
        if (blocks[i][j] == null) {
            return false;
        }
        if (hitsWall(i, j, c)) {
            return false;
        }
        if (blockInPath(i, j, c)) {
            return false;
        }
        return true;
    }

    /* Description: checks if the key pressed by the user is a valid key
     *              to move the block (w, a, s, or d)
     * Input: char c that is the character of the key pressed by the user
     * Output: boolean true or false, true if the character pressed is 
     *         valid character to indicate a move
     */
    private boolean validMove(char c) {
        if (c == 'w' || c == 'a' || c == 's' || c == 'd') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (canSlide(i, j, c)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }    
    
    /* Description: returns the information of the block that is in the place
     *              of where the block will move based on the key pressed
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: the block next to the block at i and j given by a move
     *         determined by the character c
     */
    private Block newBlock(int i, int j, char c) {
        if (c == 'w') {
            if (i + 1 < 4) {
                return blocks[i + 1][j];
            }
        } else if (c == 's') {
            if (i - 1 >= 0) {
                return blocks[i - 1][j];
            }
        } else if (c == 'a') {
            if (j - 1 >= 0) {
                return blocks[i][j - 1];
            }
        } else if (c == 'd') {
            if (j + 1 < 4) {
                return blocks[i][j + 1];
            }
        }
        return null;
    }
    
    /* Description: checks if a block will either hit a wall or another block
     *              if it moves in the direction that char c indicates
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: true if the block will hit something, false if it won't
     */
    private boolean hitsSomething(int i, int j, char c) {
        return hitsWall(i, j, c) || (newBlock(i, j, c) != null);
    }
    
    /* Description: checks if a block has another block in its path
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: true if a block has another block in its path, false if not
     */
    private boolean blockInPath(int i, int j, char c) {
        if (newBlock(i, j, c) == null) {
            return false;
        }
        if (newBlock(i, j, c).shouldAddBlock(blocks[i][j])) {
            return false;
        }
        return true;
    }
    
    /* Description: checks if a block will hit a wall if it moves in the
     *              direction indicated by char c
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: true if a blocks path is blocked by a wall, false if not
     */
    private boolean hitsWall(int i, int j, char c) {
        if (c == 'w') {
            if (i >= 4 - 1) {
                return true;
            }
        }
        if (c == 's') {
            if (i <= 0) {
                return true;
            }
        }
        if (c == 'a') {
            if (j <= 0) {
                return true;
            }
        }

        if (c == 'd') {
            if (j >= 4 - 1) {
                return true;
            }
        }
        return false;
    }
    
    /* Description: moves blocks by moving them in the direction indicated
     *              by char c until there is an obstacle in the path
     *              (a wall or another block)
     * Input: char c for the key that was pressed
     */
    private void updateGrid(char c) {
        if (c == 'w') {
            for (int i = 4 - 1; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    int h = i;
                    while (!hitsSomething(h, j, c)) {
                        blocks[h + 1][j] = blocks[h][j];
                        blocks[h][j] = null;
                        h++;
                    }
                }
            }
        } else if (c == 's') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    int h = i;
                    while (!hitsSomething(h, j, c)) {
                        blocks[h - 1][j] = blocks[h][j];
                        blocks[h][j] = null;
                        h--;
                    }
                }
            }
        } else if (c == 'a') {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    int h = j;
                    while (!hitsSomething(i, h, c)) {
                        blocks[i][h - 1] = blocks[i][h];
                        blocks[i][h] = null;
                        h--;
                    }
                }
            }
        } else if (c == 'd') {
            for (int j = 4 - 1; j >= 0; j--) {
                for (int i = 0; i < 4; i++) {
                    int h = j;
                    while (!hitsSomething(i, h, c)) {
                        blocks[i][h + 1] = blocks[i][h];
                        blocks[i][h] = null;
                        h++;
                    }
                }
            }
        }
    }
    
    /* Description: checks if a block adjacent to another block in the
     *              direction indicated by the key pressed at char c contain
     *              equivalent values
     * Input: int i, int j, and char c. int i and j are the position of the block
     *        in the 2d array. char c is the character of the key that was
     *        presed.
     * Output: true if blocks can add together, flase in all other cases
     */
    private boolean canAdd(int i, int j, char c) {
        if (blocks[i][j] == null) {
            return false;
        }
        
        if (newBlock(i, j, c) == null) {
            return false;
        }
        return newBlock(i, j, c).shouldAddBlock(blocks[i][j]);
    }
    
    /* Description: adds eligible blocks together
     * Input: char c for the key that was pressed
     */
    private void sumBlocks(char c) {
        if (c == 'w') {
            for (int i = 4 - 1; i >= 0; i--) {
                for (int j = 0; j < 4; j++) {
                    if (canAdd(i, j, c)) {
                        newBlock(i, j, c).addBlocks(blocks[i][j]);
                        blocks[i][j] = null;
                        this.blockCount--;
                    }
                }
            }
        } else if (c == 's') {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (canAdd(i, j, c)) {
                        newBlock(i, j, c).addBlocks(blocks[i][j]);
                        blocks[i][j] = null;
                        this.blockCount--;
                    }
                }
            }
        } else if (c == 'a') {
            for (int j = 0; j < 4; j++) {
                for (int i = 0; i < 4; i++) {
                    if (canAdd(i, j, c)) {
                        newBlock(i, j, c).addBlocks(blocks[i][j]);
                        blocks[i][j] = null;
                        this.blockCount--;
                    }
                }
            }
        } else if (c == 'd') {
            for (int j = 4 - 1; j >= 0; j--) {
                for (int i = 0; i < 4; i++) {
                    if (canAdd(i, j, c)) {
                        newBlock(i, j, c).addBlocks(blocks[i][j]);
                        blocks[i][j] = null;
                        this.blockCount--;
                    }
                }
            }
        }
    }
    
    /* Description: if a block's location on the gird is not what it should
     *              be by its position in the 2d array, move the blocks to 
     *              the correct location.
     * Input: no inputs
     */
    private void blockAdjustment() {
        PennDraw.enableAnimation(50);
        
        int[][] xAdjustment = new int[4][4];
        int[][] yAdjustment = new int[4][4];
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    xAdjustment[i][j] = XPOS[j] - blocks[i][j].getXVal();
                    yAdjustment[i][j] = YPOS[i] - blocks[i][j].getYVal();
                }
            }   
        }       
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].addToX(xAdjustment[i][j]);
                    blocks[i][j].addToY(yAdjustment[i][j]);
                }
            }    
                if (PennDraw.hasNextKeyTyped()) {
                    return;
            }
            
            Game.drawBackGround();
            drawBlocks();
            PennDraw.advance();
        }
    }   
    
    /* Description: first checks if the key pressed is a valid move, then 
     *              updates the grid to mach the move and randomly places a
     *              block on the gird.
     * Input: char c for the key that was pressed
     */

    public void moveBlocks(char c) {
        if (validMove(c)) {
            updateGrid(c);
            blockAdjustment();
            
            sumBlocks(c);
            blockAdjustment();
            
            updateGrid(c);
            blockAdjustment();
            
            updatePosition();
            addBlocksToGrid();
            
            this.moveCount++;
        }
    }
    
    /* Description: checks if the user has won by going through every block
     *              in the 2d array grid and checking if any of them contain
     *              a value of 2048
     * Input: no inputs
     * Output: true if 2048 is the value of a block and the user won the game,
     *         false if not
     */
    public boolean won() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != null &&
                    blocks[i][j].getBlockValue() >= 2048) {
                    return true;
                }
            }
        }        
        return false;
    }
    
    /* Description: If the grid is full of blocks and there no possible 
     *              moves remaining
     * Input: no inputs
     * Output: true if there are less than 16 blocks on the grid and
     *         every block is next to a block with a differing value, false 
     *         in all other cases
     */
    public boolean lost() {
        if (this.blockCount <= 15) {
            return false;
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i + 1 < 4 && blocks[i][j].shouldAddBlock(blocks[i + 1][j])) {
                    return false;
                }
                if (j + 1 < 4 && blocks[i][j].shouldAddBlock(blocks[i][j + 1])) {
                    return false;
                }
            }
        }
        return true;
    }
    
}
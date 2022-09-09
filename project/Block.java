/**
 * Name: Myron Kharkover
 * Pennkey: myronk
 * Execution: none
 * 
 * Description: implements the BlockInterface interface to implement
 *              every method necessary to individual blocks in the game
 */
public class Block implements BlockInterface {

    private int x;
    private int y;
    private int blockVal;

    /* Description: constructor that randomly generates blocks with values
     *              of either 2 or 4
     * Input: integer x and integer y for the x and y positions of the blocks
     */
    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        if (Math.random() < 0.5) {
            blockVal = 2;
        } else {
            blockVal = 4;
        }
    }

    /* Description: draws the block
     * Input: no inputs
     */
    public void draw() {
        PennDraw.setPenRadius(.0033);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.square(this.x, this.y, 50);
        PennDraw.text(this.x, this.y, "" + blockVal);
    }
    
    /* Description: gets the value of a block
     * Input: no inputs
     * Output: int the number value on a given block
     */
    public int getBlockValue() {
        return this.blockVal;
    }

    /* Description: Doubles the value of a block
     * Input: no inputs
     * Output: void
     */
    public void addBlocks(Block b) {
        this.blockVal = this.blockVal + b.blockVal;
    }    
    
    /* Description: checks if the given block and Block b are equal
     * Input: Block b
     * Output: true if contains the same value as Block b, 
     *         false in all other cases
     */
    public boolean shouldAddBlock(Block b) {
        return this.blockVal == b.blockVal;
    }    

    /* Description: gets the x-coordinate of a block
     * Input: no inputs
     * Output: int x-coordinate of a block
     */
    public int getXVal() {
        return this.x;
    }
    
    /* Description: sets this block's x value to a new x value
     * Input: int x, the x coordinate that we want our block's x position
     *        to be updated to
     */
    public void setXVal(int x) {
        this.x = x;
    }

    /* Description: adds x to the x of the current block
     * Input: int x, the x we want to add to the x position
     */
    public void addToX(int x) {
        this.x += x;
    }

    /* Description: gets the y-coordinate of a block
     * Input: no inputs
     * Output: int y-coordinate of a block
     */
    public int getYVal() {
        return this.y;
    }

    /* Description: sets this block's y value to a new y value
     * Input: int y, the y coordinate that we want our block's y position
     *        to be updated to
     */
    public void setYVal(int y) {
        this.y = y;
    }

    /* Description: adds y to the y of the current block
     * Input: int y, the y we want to add to the y position
     */
    public void addToY(int y) {
        this.y += y;
    }
}
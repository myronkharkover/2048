public interface BlockInterface {

    /* Description: draws the block
     * Input: no inputs
     */
    void draw();

    /* Description: gets the value of a block
     * Input: no inputs
     * Output: int the number value on a given block
     */
    int getBlockValue();

    /* Description: Doubles the value of a block
     * Input: no inputs
     */
    void addBlocks(Block b);    
    
    /* Description: checks if Block b contains the same value as the 
     *              given block
     * Input: Block b
     * Output: true if contains the same value as Block b, 
     *         false in all other cases
     */
    boolean shouldAddBlock(Block b);

    /* Description: gets the x-coordinate of a block
     * Input: no inputs
     * Output: int x-coordinate of a block
     */
    int getXVal();

    /* Description: sets this block's x value to a new x value
     * Input: int x, the x coordinate that we want our block's x position
     *        to be updated to
     */
    void setXVal(int x);
    
    /* Description: adds x to the x of the current block
     * Input: int x, the x we want to add to the x position
     */
    void addToX(int x);    
    
    /* Description: gets the y-coordinate of a block
     * Input: no inputs
     * Output: int y-coordinate of a block
     */
     int getYVal();

    /* Description: sets this block's y value to a new y value
     * Input: int y, the y coordinate that we want our block's y position
     *        to be updated to
     */
    void setYVal(int y);

    /* Description: adds y to the y of the current block
     * Input: int y, the y we want to add to the y position
     */
    void addToY(int y);
}
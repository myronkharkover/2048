public interface GameFunctionInterface {
    
    /* Description: checks if the user has won the game by checking if
     *              there is a block with 2048 on the grid
     * Input: no inputs
     * Output: true or false: true if won, false if not won
     */
    boolean won();
    
    /* Description: checks if the user has lost the game
     * Input: no inputs
     * Output: true or false: true if lost, false is not lost
     */
    boolean lost();
    
    /* Description: updates the 2d array of blocks so the blocks now have their
     *              new positions dictated by the moves designated by the
     *              key pressed (char c). Then creates a new block and randomly
     *              places it within the grid.
     * Input: char c for the key pressed
     */
    void moveBlocks(char c);
    
    /* Description: updates the 2d array of blocks so the blocks now have their
     *              new positions dictated by the moves designated by the
     *              key pressed (char c). Then creates a new block and randomly
     *              places it within the grid.
     * Input: no inputs
     */
    void drawBlocks();
    
    /* Description: getter method for movecount
     * Input: No inputs
     */
    int getMoveCount();
}
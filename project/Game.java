/**
 * Name: Myron Kharkover
 * Pennkey: myronk
 * Execution: java Game
 * 
 * Description: executes the game itself by first drawing the background,
 *              then uses the restart methods to check if the game should be
 *              restarted, and finally runs the game in main
 */
public class Game {
    
    private static GameFunction g = new GameFunction();
    
    /* Description: Clears the background and draws a outer box, the grid, 
     *              move counter, and restart button
     * Input: No input
     */
    public static void drawBackGround() {
        PennDraw.clear();
        PennDraw.filledRectangle(425, 600, 50, 30);
        PennDraw.setFontSize(20);
        PennDraw.setPenColor(PennDraw.WHITE);
        PennDraw.text(425, 600, "Restart");
        PennDraw.setFontSize(16);
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.text(195, 600, "Moves:" + g.getMoveCount());
        PennDraw.setPenColor(PennDraw.BLACK);
        PennDraw.setPenRadius(.01);
        PennDraw.rectangle(275, 285, 240, 240);
        PennDraw.setPenRadius(.005);
        PennDraw.polyline(155, 525, 155, 45);
        PennDraw.polyline(275, 525, 275, 45);
        PennDraw.polyline(395, 525, 395, 45);
        PennDraw.polyline(35, 165, 515, 165);
        PennDraw.polyline(35, 285, 515, 285);
        PennDraw.polyline(35, 405, 515, 405);
    }    

    //main function
    public static void main(String[] args) {
        PennDraw.setCanvasSize(550, 650);
        PennDraw.setXscale(0, 550);
        PennDraw.setYscale(0, 650);
        
        PennDraw.enableAnimation(50);
        
        while (true) {
            drawBackGround();
            g.drawBlocks();
            
            if (PennDraw.mousePressed()) {
                double x = PennDraw.mouseX();
                double y = PennDraw.mouseY();
                if (x >= 375 && x <= 475 && y >= 570 && y <= 630) {
                    g = new GameFunction();
                }
                while (PennDraw.hasNextKeyTyped()) {
                    PennDraw.nextKeyTyped();
                }                
            }

            if (g.won()) {
                PennDraw.setPenColor(144, 238, 144);
                PennDraw.filledSquare(275, 325, 350);
                PennDraw.setFontSize(32);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(275, 375, "WINNER WINNER CHICKEN DINNER");
                PennDraw.setFontSize(16);
                PennDraw.text(275, 300, "Final Move Count: " + g.getMoveCount());
                PennDraw.filledRectangle(275, 200, 50, 30);
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.text(275, 200, "Restart");
                if (PennDraw.mousePressed()) {
                    double x = PennDraw.mouseX();
                    double y = PennDraw.mouseY();
                    if (x >= 225 && x <= 325 && y >= 170 && y <= 230) {
                        g = new GameFunction();
                    }
                    while (PennDraw.hasNextKeyTyped()) {
                        PennDraw.nextKeyTyped();
                    }
                }
            }

            if (g.lost()) {
                PennDraw.setPenColor(PennDraw.RED);
                PennDraw.filledSquare(275, 325, 350);
                PennDraw.setFontSize(40);
                PennDraw.setPenColor(PennDraw.BLACK);
                PennDraw.text(275, 475, "LOSERRRRR");
                PennDraw.setFontSize(16);
                PennDraw.text(275, 350, "Final Move Count: " + g.getMoveCount());
                PennDraw.filledRectangle(275, 200, 50, 30);
                PennDraw.setFontSize(20);
                PennDraw.setPenColor(PennDraw.WHITE);
                PennDraw.text(275, 200, "Restart");
                if (PennDraw.mousePressed()) {
                    double x = PennDraw.mouseX();
                    double y = PennDraw.mouseY();
                    if (x >= 225 && x <= 325 && y >= 170 && y <= 230) {
                        g = new GameFunction();
                    }
                    while (PennDraw.hasNextKeyTyped()) {
                        PennDraw.nextKeyTyped();
                    }
                }
            }

            if (PennDraw.hasNextKeyTyped()) {
                char c = PennDraw.nextKeyTyped();
                g.moveBlocks(c);
            }
            PennDraw.advance();
        }
    }
}

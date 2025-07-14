// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN131 assignment.
// You may not distribute it in any other way without permission.

/* Code for SWEN131
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

public class PatternDrawer{

    public static final double boardLeft = 50;   // Top left corner of the board
    public static final double boardTop = 50;
    public static final double boardSize = 300;  // The size of the board on the window

    /** Draw a square grid board with white squares.
     *  Asks the user for the number of squares on each side
     *
     * CORE
     */
    public void drawGridBoard(){
        UI.clearGraphics();
        int rows = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */
        int cols = rows;
        double length = boardSize / rows;
        
        int row = 0;
        while(row < rows) {
        	int col = 0;
        	
        	while(col < cols) {
        		double x = boardLeft + length * row;
        		double y = boardTop + length * col;
        		UI.drawRect(x, y, length, length);
        		
//        	UI.drawRect(50, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*2, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*3, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*4, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*5, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*6, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*7, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*8, boardTop, length, length);
//        	UI.drawRect(boardLeft+length*9, boardTop, length, length);
        		col++;
        }
        	row++;
    }
        
    
}

    /** Illusion
     * a pattern that makes dark circles appear in the intersections
     * when you look at it.
     **/
    public void drawIllusion(){
        UI.clearGraphics();
        int rows = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */
        int cols = rows;
        double size = boardSize / rows;
        
        int col = 0;
     
        	
        	int row = 0;
        	while(row < rows) {
        		double x = boardLeft + row * size;
        		double y = boardTop + col * size;
        		UI.drawRect(x, y, size, size);
        	}
        	row++;
        
        
    }

    /** Draw a checkered board with alternating black and white squares
     *    Asks the user for the number of squares on each side
     *
     * COMPLETION
     */
    public void drawCheckersBoard(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */

    }

    /** Draw a board made of concentric circles, 2 pixel apart
     *  Asks the user for the number of squares on each side
     */
    public void drawConcentricBoard(){
        UI.clearGraphics();
        int num = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */

    }


}


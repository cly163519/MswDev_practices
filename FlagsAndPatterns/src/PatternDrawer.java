// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN131 assignment.
// You may not distribute it in any other way without permission.

/* Code for SWEN131
 * Name:
 * Usercode:
 * ID:
 */

import java.awt.Color;

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
        //double cols = rows;
        double cell = boardSize / rows;
        
        for(int row = 0; row < rows; row++) {
        	for(int col = 0; col < rows - row; col++) {
        		double x = boardLeft + cell * col;
        		double y = boardTop + cell * row;
        		UI.setColor(Color.black);
        		UI.fillRect(x, y, cell, cell);
        		UI.setColor(Color.white);
        		UI.drawRect(x, y, cell, cell);
        		
        		
        		
        	}
        	
        }
  
    }

    /** Draw a checkered board with alternating black and white squares
     *    Asks the user for the number of squares on each side
     *
     * COMPLETION
     */
    public void drawCheckersBoard(){
        UI.clearGraphics();
        int rows = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */
        int row = 0;
        int col = 0;
        double cell= boardSize / rows;
        for(row = 0; row < rows; row++) {
        	
        	for(col = 0; col < rows; col++) {
        		double x = boardLeft + cell * col;
        		double y = boardTop + cell * row;
        		//UI.drawRect(x, y, cell, cell);
        		if((row + col) % 2 == 0) {
        			UI.setColor(Color.white);
        		}else {
        			UI.setColor(Color.black);	
        		}
        		UI.fillRect(x, y, cell, cell);
        		
        		UI.setColor(Color.black);
        		UI.drawRect(x, y, cell, cell);
        		
        	}
        }

    }

    /** Draw a board made of concentric circles, 2 pixel apart
     *  Asks the user for the number of squares on each side
     */
    public void drawConcentricBoard(){
        UI.clearGraphics();
        int rows = UI.askInt("How many rows:");
        /*# YOUR CODE HERE */
        double cell = boardSize / rows;
        double radius = cell / 2;
        
        for(int row = 0; row < rows; row++) {
        	for(int col = 0; col < rows; row++) {
        		double x = boardLeft + cell * col + radius;
        		double y = boardTop + cell * row + radius;
        		
        		UI.drawOval(x, y, radius * 2, radius * 2);
        		radius -= 1;
        		
        	}
        }
        
        
        
        
    }


}


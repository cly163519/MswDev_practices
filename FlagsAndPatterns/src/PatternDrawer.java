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
        double cell = boardSize / rows;
        
        for(int row = 0; row < rows; row++) {
        	for(int col = 0; col < cols; col++) {
        		UI.drawRect(boardLeft + cell * col, boardTop + cell * row, cell, cell);
        		
        	}
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
        double cell = boardSize / rows;
        for(int row = 0; row < rows; row++) {
        	for(int col = 0; col < rows - row; col++) {
        		double x = boardLeft + cell * col;
        		double y = boardTop + cell * row;
        		
        		UI.setColor(Color.red);
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
        double cellSize = boardSize / rows;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < rows; col++) {
                double x = boardLeft + col * cellSize + 1;
                double y = boardTop + row * cellSize + 1;
                drawConcentricCircles(x, y, cellSize - 2);
            }
        }
    }

    public void drawConcentricCircles(double x, double y, double size) {
        int count = (int)(size / 5);  // Control the number of concentric circles
        for (int i = 0; i < count; i++) {
            double inset = i * 2;
            UI.drawOval(x + inset, y + inset, size - 2 * inset, size - 2 * inset);
        }
    }
        
        
        
        
        
    }





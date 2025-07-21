// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN131 assignment.
// You may not distribute it in any other way without permission.

/* Code for SWEN131
 * Name:
 * Usercode:
 * ID:
 */



import ecs100.*;
import java.awt.Color;
import javax.swing.JColorChooser;

public class FlagDrawer {

    public static final double width = 200;
    public static final double height = 133;
    public static final double x = 0;
    public static final double y = 0;
    
    /**
     * asks user for a position and three colors, then calls the
     * drawThreeColourFlagCore method, passing the appropriate arguments
     *
     * CORE
     */
    public void testCore(){
        double left = UI.askDouble("left of flag");
        double top = UI.askDouble("top of flag");
        UI.println("Now choose the colours");
        Color stripe1 = JColorChooser.showDialog(null, "First Stripe", Color.white);
        Color stripe2 = JColorChooser.showDialog(null, "Second Stripe", Color.white);
        Color stripe3 = JColorChooser.showDialog(null, "Third Stripe", Color.white);
        //Uncomment this line when you start work on the core:
        this.drawThreeColourFlagCore(left, top, stripe1, stripe2, stripe3 );
       // UI.println("You need to uncomment the line above this in testCore.");
    }

    /**
     * draws a three color flag consisting of three vertical equal-width stripes
     * at the given position
     *
     * CORE
     */
    public void drawThreeColourFlagCore(/*# YOUR CODE HERE */double x, double y, Color color_1, Color color_2, Color color_3){
        /*# YOUR CODE HERE */
    	double stripeWidth = width / 3;
    	UI.setColor(color_1);
    	UI.fillRect(x, y, stripeWidth, height);
    	
    	UI.setColor(color_2);
    	
    	UI.setColor(color_3);
    	UI.fillRect(x + stripeWidth * 2, y, stripeWidth, height);
    }

    /**
     * draws multiple flag made up of three equal size stripes by calling the
     * drawThreeColourFlagCompletion method, passing the appropriate arguments
     *
     * COMPLETION
     */
    public void testCompletion(){
        double left = 100;
        double top = 20;
        //// Uncomment these lines when you start working on the completion:
        this.drawThreeColourFlagCompletion(true, 20, 50, Color.black, Color.yellow, Color.red);               // Belgium
        this.drawThreeColourFlagCompletion(false, 250, 100, Color.black, Color.red, Color.yellow);            // Germany
        this.drawThreeColourFlagCompletion(true, 140, 430, Color.blue, Color.white, Color.red);               // France
        this.drawThreeColourFlagCompletion(false, 470, 30, Color.red, Color.white, Color.blue);               // The Netherlands
        this.drawThreeColourFlagCompletion(false, 50, 250, Color.white, Color.blue, Color.red);               // Russia
        this.drawThreeColourFlagCompletion(true, 290, 270, Color.red, Color.yellow, Color.green.darker());    // Guinea
        UI.println("You need to uncomment the lines above this in testCompletion.");
    }

    /**
     * draws a three color flag consisting of three equal-size stripes
     * at the given position
     * The stripes can be either vertical or horizontal
     *
     * COMPLETION
     */
    public void drawThreeColourFlagCompletion(/*# YOUR CODE HERE */boolean vertical, double x, double y, Color color_1, Color color_2, Color color_3){
        /*# YOUR CODE HERE */
    	//boolean vertical = true;
    	double stripeWidth = width / 3;
    	double stripeHeight = height / 3;
    	
    	if(vertical) {
    		UI.setColor(color_1);
    		UI.fillRect(x, y, stripeWidth, height);
    		
    		UI.setColor(color_2);
    		UI.fillRect(x + stripeWidth, y, stripeWidth, height);
    		
    		UI.setColor(color_3);
    		UI.fillRect(x + stripeWidth * 2, y, stripeWidth, height);	
    	}else {
    		UI.setColor(color_1);
    		UI.fillRect(x, y, width, stripeHeight);
    		
    		UI.setColor(color_2);
    		UI.fillRect(x, y + stripeHeight, width, stripeHeight);
    		
    		UI.setColor(color_3);
    		UI.fillRect(x, y + stripeHeight * 2, width, stripeHeight);
    	}
    }
    
    
    
    ///Challenge of drawing different kinds of flags
    ///The process of challenge:
    //1. Click run → drawFlagChallenge()
    //2. The user enters the position, number of stripes, and direction
    //3. The user selects colors in turn → stores them in the array colours[]
    //4. Call drawStripes(...) → starts drawing background stripes
    //5. Return to the main method and ask what kind of decoration you want to add
    //6. Based on your choice → call the corresponding decoration method
    //7. Draw the decoration pattern (rectangle, ellipse, small flag)
    //8. A complete custom flag is completed!
    
    	public void drawFlagChallenge(){
    	    double left = UI.askDouble("Left of flag:");
    	    double top = UI.askDouble("Top of flag:");
    	    int stripeCount = (int) UI.askDouble("Number of stripes:");
    	    boolean vertical = UI.askBoolean("Vertical stripes? (true/false)");

    	    // Choose color
    	    Color[] colours = new Color[stripeCount];
    	    for (int i = 0; i < stripeCount; i++) {
    	        colours[i] = JColorChooser.showDialog(null, "Color " + (i + 1), Color.white);
    	    }

    	    // Draw background of stripes
    	    this.drawStripes(stripeCount, vertical, left, top, colours);

    	    // Ask user to input data
    	    UI.println("Choose decoration:");
    	    UI.println("1 - Top-left rectangle");
    	    UI.println("2 - Center oval");
    	    UI.println("3 - Top-left small flag");
    	    int choice = (int) UI.askDouble("Enter 1 / 2 / 3");

    	    // Draw decoration icons
    	    if (choice == 1) {
    	        drawTopLeftBlock(left, top);
    	    } else if (choice == 2) {
    	        drawCenterShape(left, top);
    	    } else if (choice == 3) {
    	        drawMiniFlag(left, top);
    	    } else {
    	        UI.println("Invalid choice");
    	    }
    	}
    	
    	//Draw stripes specific method
    	public void drawStripes(int count, boolean vertical, double x, double y, Color[] colours){
    	    if (vertical){
    	        double stripeWidth = width / count;
    	        for (int i = 0; i < count; i++) {
    	            UI.setColor(colours[i]);
    	            UI.fillRect(x + i * stripeWidth, y, stripeWidth, height);
    	        }
    	    } else {
    	        double stripeHeight = height / count;
    	        for (int i = 0; i < count; i++) {
    	            UI.setColor(colours[i]);
    	            UI.fillRect(x, y + i * stripeHeight, width, stripeHeight);
    	        }
    	    }
    	}

    	//Draw top left blocks
    	public void drawTopLeftBlock(double x, double y){
    	    Color color = JColorChooser.showDialog(null, "Decoration Color", Color.black);
    	    UI.setColor(color);
    	    UI.fillRect(x, y, width / 5, height / 5);
    	}
    	
    	//Draw oval or rect in the middle
    	public void drawCenterShape(double x, double y){
    	    Color color = JColorChooser.showDialog(null, "Decoration Color", Color.blue);
    	    UI.setColor(color);
    	    double w = width / 3;
    	    double h = height / 3;
    	    double cx = x + (width - w) / 2;
    	    double cy = y + (height - h) / 2;
    	    boolean isOval = UI.askBoolean("Oval shape? (true = oval, false = rectangle)");
    	    if (isOval) {
    	        UI.fillOval(cx, cy, w, h);
    	    } else {
    	        UI.fillRect(cx, cy, w, h);
    	    }
    	}

    	//Draw top left 1/4 mini flags
    	public void drawMiniFlag(double x, double y){
    	    Color c1 = JColorChooser.showDialog(null, "Mini Flag Color 1", Color.red);
    	    Color c2 = JColorChooser.showDialog(null, "Mini Flag Color 2", Color.white);
    	    Color c3 = JColorChooser.showDialog(null, "Mini Flag Color 3", Color.blue);

    	    double smallX = x;
    	    double smallY = y;
    	    double smallW = width / 2;
    	    double smallH = height / 2;

    	    double stripeW = smallW / 3;

    	    UI.setColor(c1);
    	    UI.fillRect(smallX, smallY, stripeW, smallH);

    	    UI.setColor(c2);
    	    UI.fillRect(smallX + stripeW, smallY, stripeW, smallH);

    	    UI.setColor(c3);
    	    UI.fillRect(smallX + stripeW * 2, smallY, stripeW, smallH);
    	}


    

}

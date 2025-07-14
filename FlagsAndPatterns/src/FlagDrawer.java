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
        UI.println("You need to uncomment the line above this in testCore.");
    }

    /**
     * draws a three color flag consisting of three vertical equal-width stripes
     * at the given position
     *
     * CORE
     */
    public void drawThreeColourFlagCore(double x, double y, Color color_1, Color color_2, Color color_3){
        /*# YOUR CODE HERE */
    	double stripeWidth = width / 3; 
    	UI.setColor(color_1);
    	UI.fillRect(x, y, stripeWidth, height );
    	
    	UI.setColor(color_2);
    	UI.fillRect(x + stripeWidth, y, stripeWidth, height);
    	
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
    public void drawThreeColourFlagCompletion(boolean vertical, double x, double y, Color first, Color second, Color third){
        /*# YOUR CODE HERE */
    	//boolean vertical = true;
    	//double stripeWidth = width / 3;
    	//double stripeHeight = height / 3;
    	
    	if(vertical) {
    		double stripeWidth = width / 3;
    		UI.setColor(first);
    		UI.fillRect(x, y, stripeWidth, height);
    		
    		UI.setColor(second);
    		UI.fillRect(x + stripeWidth, y, stripeWidth, height);
    		
    		UI.setColor(third);
    		UI.fillRect(x + stripeWidth * 2, y, stripeWidth, height);
    	}else {
    		double stripeHeight = height / 3;
    		UI.setColor(first);
    		UI.fillRect(x, y, width, stripeHeight);
    		
    		UI.setColor(second);
    		UI.fillRect(x, y + stripeHeight, width, stripeHeight);
    		
    		UI.setColor(third);
    		UI.fillRect(x, y + stripeHeight * 2, width, stripeHeight);
    	}
    	
    }


}

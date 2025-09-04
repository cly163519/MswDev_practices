package userinterface;

import java.awt.Color;

public interface Shape {
	void draw();//Draw a shape
	boolean contains(double px, double py);//Determine whether the click is within the graphic
	void moveBy(double dx, double dy);//Used for dragging graphics
	void setEnd(double x, double y);  // Update the shape's end point, used to move the shape vs change the shape's size
	
    Color getColor(); //Get the current color of the shape
    void setColor(Color color);//Set the shape's color
    void drawToPixels(int[][] pixels);//Array used to store graphics on the canvas
    
    double getX();//The following parameters are used for modifying shape logic
    double getY();
    double getWidth();
    double getHeight();
	void setSize(double newWidth, double newHeight);

}

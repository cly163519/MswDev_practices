package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Rectangle implements Shape{
	private double x; //X position of the rectangle shape
	private double y; //Y position of the rectangle shape
	private double height; //Height value of rectangle
	private double width; //Width value of rectangle
	private Color color; //Save the current shape's color
	
	public Rectangle(double x, double y, double width, double height, Color color) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.color = color;
				
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getHeight() {
		return height;
	}
	
	public double getWidth() {
		return width;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		UI.setColor(color);
		UI.fillRect(x, y, width, height);
		UI.drawRect(x, y, width, height);
	}

	@Override
	public boolean contains(double px, double py) {
		// TODO Auto-generated method stub
		return (px <= x + width && px >= x && py <= y + height && py >= y);// Return true if the point (px, py) is inside the rectangle:
																			// Check if px is between left (x) and right (x + width),
																			// and py is between top (y) and bottom (y + height)
	}

	@Override
	public void moveBy(double dx, double dy) {
		// TODO Auto-generated method stub
		x += dx; // Move the rectangle to the right by dx pixels
		y += dy; //Move the rectangle downward by dy pixels
	}


	@Override
	public void setEnd(double x2, double y2) {// Update the shape's width, height, and position based on a new end point (x2, y2)
		// TODO Auto-generated method stub
		this.width = Math.abs(x2 - x);// Calculate the absolute width between the starting x and the new x2
		this.height = Math.abs(y2 - y);// Calculate the absolute width between the starting y and the new y2
		if(x2 < x) {// If the new x2 is to the left of the original x
			this.x = x2;//Move the shape's x to x2, so x is always the left edge
		}
		if(y2 < y) {// If the new y2 is to the left of the original y
			this.y = y2;//Move the shape's y to y2, so y is always the left edge
		}
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}


	public void drawToPixels(int[][] pixels) {//How to convert the graphics drawn by the user on the panel into .pgm pixel format
		// TODO Auto-generated method stub
		//(int) is a forced type conversion, which means converting the decimal into an integer, because the pixel array must be an integer.
		    int x0 = (int) x;//Top left X coordinate
		    int y0 = (int) y;//Top left Y coordinate
		    int w = (int) width;//Shape's width pixels
		    int h = (int) height;//Shape's height pixels

		    for (int i = y0; i < y0 + h && i < pixels.length; i++) {// Outer loop: iterate through each row (y coordinate) of the shape
		        for (int j = x0; j < x0 + w && j < pixels[0].length; j++) {// Inner loop: iterate through each column (x coordinate) of the shape
		            if (i >= 0 && j >= 0) {// Prevent accessing negative index
		                pixels[i][j] = 0; // Black
		            }
		        }
		    }
		}

	@Override
	public void setSize(double newWidth, double newHeight) {
		// TODO Auto-generated method stub
		this.width = newWidth;
		this.height = newHeight;
	}

}

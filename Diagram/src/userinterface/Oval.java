package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Oval implements Shape{
	private double x;//X position of oval
	private double y;//Y position of oval
	private double height;//Height value of oval
	private double width; //Width value of oval
	private Color color; //Save the current shape's color
	
	public Oval(double x, double y, double width, double height, Color color) {
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
		UI.fillOval(x, y, width, height);
		UI.drawOval(x, y, width, height);
	}

	@Override
	public boolean contains(double px, double py) {
		// TODO Auto-generated method stub
		
		//Approximate the oval to a rectangle and determine whether the mouse falls in the area of the rectangle to simplify the logic
	    return (px >= x && px <= x + width && py >= y && py <= y + height);
	}

	@Override
	public void moveBy(double dx, double dy) {
		// TODO Auto-generated method stub
		x += dx; // Move the oval to the right by dx pixels
		y += dy; //Move the oval downward by dy pixels
	}

	@Override
	public void setEnd(double x2, double y2) {
		// TODO Auto-generated method stub
		this.width = Math.abs(x2 - x);
        this.height = Math.abs(y2 - y);
        if (x2 < x) {
        	this.x = x2;
        }
        if (y2 < y) {
        	this.y = y2;
        }
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}


	@Override
	public void drawToPixels(int[][] pixels) {// Convert this oval shape into pixel data for saving as a PGM image(Search the solution from online resources)
		// TODO Auto-generated method stub
		int x0 = (int) x;//Change x y to int type, left edge X coordinate of the oval's bound
	    int y0 = (int) y;
	    int w = (int) width;//The whole width/height of the oval
	    int h = (int) height;

	    double rx = w / 2.0;   // Half width, horizontal radius of the oval
	    double ry = h / 2.0;   // Half height, vertical radius of the oval
	    double cx = x0 + rx;   // X coordinate of the oval center
	    double cy = y0 + ry;   // Y coordinate of the oval center

	    for (int row = y0; row < y0 + h && row < pixels.length; row++) {//Outer loop: traverse over each row (y coordinate)
	        for (int col = x0; col < x0 + w && col < pixels[0].length; col++) {//Inner loop, traverse over each column (x coordinate)
	            if (row >= 0 && col >= 0) {//Make sure not access negative index
	                double dx = (col - cx) / rx;
	                double dy = (row - cy) / ry;
	                if (dx * dx + dy * dy <= 1.0) { // Oval formula
	                    pixels[row][col] = 0; // Black
	                    
	                }
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

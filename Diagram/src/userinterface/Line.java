package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Line implements Shape{
	private double x1;
	private double x2;
	private double y1;
	private double y2;
	private Color color;
	
	public Line(double x1, double y1, double x2, double y2, Color color) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
	}
	
	public void setEnd(double x, double y) {
//		this.x2 = x;
//		this.y2 = y;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		UI.drawLine(x1, y1, x2, y2);
	}

	@Override
	public boolean contains(double px, double py) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveBy(double dx, double dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawToPixels(int[][] pixels) {//Do not implement this logic
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setSize(double newWidth, double newHeight) {
		// TODO Auto-generated method stub
		
	}

}

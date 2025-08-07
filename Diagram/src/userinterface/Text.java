package userinterface;

import java.awt.Color;

import ecs100.UI;

public class Text implements Shape{

	private double x, y;
    private String text;
    private Color color;
    
    public Text(double x, double y, String text, Color color) {
    	this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
    }
	@Override
	public void draw() {
		// TODO Auto-generated method stub
		UI.setColor(color);
		UI.drawString(text, x, y);
	}

	@Override
	public boolean contains(double px, double py) {
		// TODO Auto-generated method stub
		return px >= x && px <= x + text.length() * 7 && py >= y - 12 && py <= y;
	}

	@Override
	public void moveBy(double dx, double dy) {
		// TODO Auto-generated method stub
		this.x += dx;
        this.y += dy;
	}

	@Override
	public void setEnd(double x, double y) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void drawToPixels(int[][] pixels) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return text.length() * 7;
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 15;
	}

	@Override
	public void setSize(double newWidth, double newHeight) {
		// TODO Auto-generated method stub
		
	}
	public String toText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

}

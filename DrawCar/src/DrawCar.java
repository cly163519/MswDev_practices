import java.awt.Color;

import ecs100.*;

public class DrawCar {
	// If you need any constants or fields, put them here
	public static final double x = 300;
	public static final double y = 180;
	public static final double length = 240;
	public static final double height = 110;
	public static final double bottom = 210;
	
	public DrawCar() {
		// Set up your buttons here
		UI.addButton("Draw Cars", this::drawLots);
		UI.addButton("Draw Windows", this::drawWins);
		
	}
	
	// Add your methods here...
	
	public void drawLots() {
		this.drawCars(x, y, length, height);
		this.drawCars(700, 180, 240, 110);
		
	}
	
	public void drawCars(double x, double y, double length, double height) {
		double left = x - length;
		double top = y - height;
		double radius = bottom - y;
		
		String color = UI.askString("What color: ");
		
		if(color.equals("red")) {
			UI.setColor(Color.red);
		}else if(color.equals("blue")){
			UI.setColor(Color.blue);
		}else if(color.equals("green")) {
			UI.setColor(Color.green);
		}else {
			UI.setColor(Color.yellow);
		}
		
		UI.fillRect(left, top, length * 0.8, height);
		UI.fillRect(left, y - height * 0.5, length, height * 0.5);
		
		UI.setColor(Color.black);
		UI.fillOval(x - length - radius, bottom - 2 * radius, radius * 2, radius * 2);
		UI.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
		
		
	}
	
	public void drawWins() {
		this.drawWindow(x, y, length, height);
		this.drawWindow(700, 180, 240, 110);
		
			
	}
	
	
	public void drawWindow(double x, double y, double length, double height) {
		double radius = bottom - y;
		double left = x - length + radius/2;
		double top = y - height + radius/2;
		
		UI.setColor(Color.white);
		UI.fillRect(left, top, length * 0.6, height * 0.3);
		
	}
	
	
	
	public static void main(String[] args) {
		UI.initialise();
		new DrawCar();
	}

}

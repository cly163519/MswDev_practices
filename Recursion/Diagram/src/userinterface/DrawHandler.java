package userinterface;

import java.awt.Color;

import ecs100.UI;

public class DrawHandler implements MouseModeHandler{
	private UserInterface ui;// Pass in the current ui instance.Save a reference to the main class.
	private double x1, y1;
	private boolean drawing = false;
	private Color color;

	public DrawHandler(UserInterface ui) {
		this.ui = ui;
	}
	
	@Override
	public void handle(String action, double x, double y) {
		// TODO Auto-generated method stub
				//1. Begin drawing, record the starting point
		        if (!drawing && action.equals("pressed")) {//Mouse event processing logic, judging the current operation based on the mouse operation type (pressed, dragged, released)
		            x1 = x;
		            y1 = y;
		            drawing = true;
		            //2.Clear canvas, draw a preview line
		        } else if (drawing && action.equals("dragged")) {
		            UI.clearGraphics();
		            ui.drawShapes(); // Redraw existing graphics
		            UI.drawLine(x1, y1, x, y); // Temporary preview line (not stored) 
		        } else if (drawing && action.equals("released")) {
		        	//3.Release the mouse, create a line and put into the list
		            Line line = new Line(x1, y1, x, y, color);
		            ui.shapes.add(line);
		          //4.Redraw all the shapes(include the new line)
		            UI.clearGraphics();
		            ui.drawShapes();
		          //5. Reset the status
		            drawing = false;
		        }
	}

}

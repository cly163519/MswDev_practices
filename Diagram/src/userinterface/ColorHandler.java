package userinterface;

import java.awt.Color;

import javax.swing.JColorChooser;

import ecs100.UI;

public class ColorHandler implements MouseModeHandler{//Used for changing color of the shapes on canvas
	private UserInterface ui;//Define a variable to reference the UserInterface variable

    public ColorHandler(UserInterface ui) {
        this.ui = ui;
    }
	@Override
	public void handle(String action, double x, double y) {
		// TODO Auto-generated method stub
		if (action.equals("pressed")) {//If user's button click the shape
            for (int i = ui.shapes.size() - 1; i >= 0; i--) {//Traverse from the last position of the list, which means top of the shape on canvas
                Shape shape = ui.shapes.get(i);//Get the current shape
                if (shape.contains(x, y)) {//Determine whether the point clicked by the mouse falls on the graph
                    Color newColor = JColorChooser.showDialog(null, "Pick Color", shape.getColor());//ColorChooser jump out
                        shape.setColor(newColor);//Change the color
                        UI.clearGraphics();//Clear the canvas
                        ui.drawShapes();//Redraw the shapes, change the color
                        break;//Only change one shape
                }
            }
        }
    }
	

}

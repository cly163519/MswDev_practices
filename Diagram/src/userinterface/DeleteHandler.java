package userinterface;

import java.util.List;

import ecs100.UI;

public class DeleteHandler implements MouseModeHandler{
	private UserInterface ui;

    public DeleteHandler(UserInterface ui) {
        this.ui = ui;
    }

	@Override
	public void handle(String action, double x, double y) {
		// TODO Auto-generated method stub
		if (action.equals("pressed")) {//Determine the mouse action
            List<Shape> shapes = ui.shapes;//Create the shapes list
            for (int i = shapes.size() - 1; i >= 0; i--) {//Traverse the shape list
                Shape s = shapes.get(i);//Get current shape 
                if (s.contains(x, y)) {//If current mouse click fall on the shape
                    shapes.remove(i);  // Delete the selected shape
                    UI.clearGraphics();//Clear canvas
                    ui.drawShapes();//Redraw the rest shapes
                    break;//Delete one shape
                }
            }
        }
    }
	
}

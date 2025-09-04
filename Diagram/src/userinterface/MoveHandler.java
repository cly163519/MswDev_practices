package userinterface;

import ecs100.UI;

public class MoveHandler implements MouseModeHandler{// Pass in the current ui instance.Save a reference to the main class.
	private UserInterface ui;
//	private Shape selectedShape = null;
//    private boolean resizing = false;
//    private double prevX, prevY;

    public MoveHandler(UserInterface ui) {
        this.ui = ui;
    }

	@Override
	public void handle(String action, double x, double y) {
		// TODO Auto-generated method stub
		if (action.equals("pressed")) {//If the mouse is pressed, try to select a graphic
            for (int i = ui.shapes.size() - 1; i >= 0; i--) {// Start from the last graphic in the list and traverse forward
                Shape shape = ui.shapes.get(i);//Get the i-th graphics object
                
                if (shape.contains(x, y)) {//If the current shape contains the point where the mouse was clicked, the shape has been clicked.
                    ui.selectedShape = shape;//Set this shape as the "selected" shape.
                  //  resizing = false;
                    ui.prevX = x;//Record the current click point as a reference for subsequent drag calculations.
                    ui.prevY = y;
                }
            }
        } else if (action.equals("dragged") && ui.selectedShape != null) {//If the mouse is in drag mode and a shape is selected.
            	double dx = x - ui.prevX; //Calculate the distance the mouse has moved
            	double dy = y - ui.prevY;//Move the selected shape by this distance
            	ui.selectedShape.moveBy(dx, dy);
            	ui.prevX = x;//Update the reference point for the next drag operation
            	ui.prevY = y;
         
            UI.clearGraphics();//Clear the canvas
            ui.drawShapes();//Redraw all shapes, including the one with the updated position
        } else if (action.equals("released")) {//The mouse is released, indicating that the drag is complete
            ui.selectedShape = null;//Clear the selected shape next operations
        }
    }
		
}



package userinterface;

	import ecs100.UI;

	public class ModifyHandler implements MouseModeHandler {

	    private UserInterface ui;

	    public ModifyHandler(UserInterface ui) {
	        this.ui = ui;
	    }

	    @Override
	    public void handle(String action, double x, double y) {
	        if (action.equals("pressed")) {
	            for (int i = ui.shapes.size() - 1; i >= 0; i--) { // Determine from the top of the shape
	                Shape shape = ui.shapes.get(i);
	                if (shape.contains(x, y)) { // If click the current shape
	                	 double newWidth = shape.getWidth() + 20;
	                     double newHeight = shape.getHeight() + 20;
	                     shape.setSize(newWidth, newHeight);   // Reset the size of the shape

	                     ui.shapes.remove(i);//Remove the original size shape
	                     ui.shapes.add(shape);//Add new size shape
	                     
	                     UI.clearGraphics();      // Clear canvas
	                     ui.drawShapes();         // Redraw all the shapes
	                     break;                   // Only change the top of the shape
	                }
	            }
	        }
	    }
}


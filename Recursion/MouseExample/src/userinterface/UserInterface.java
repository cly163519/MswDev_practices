package userinterface;

import ecs100.UI;

public class UserInterface {
	
	double x1;
	double y1;
	boolean drawing = false;

	public void addLine() {
		UI.setMouseMotionListener(this::doMouse);
	}
	
	public void doMouse(String action, double x, double y) {
		if(!drawing && action.equals("pressed")) {
			this.x1 = x;
			this.y1 = y;
			this.drawing = true;
		} else if(drawing && action.equals("dragged")) {
			UI.drawLine(x1, y1, x, y);
			this.x1 = x;
			this.y1 = y;
		} else if (drawing && action.equals("released")) {
			this.drawing = false;
		}
	}

	public UserInterface() {
		UI.initialise();
		UI.addButton("Add Line", this::addLine);
	}

	public static void main(String[] args) {
		new UserInterface();

	}

}

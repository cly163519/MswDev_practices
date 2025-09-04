package userinterface;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JColorChooser;

import ecs100.UI;
import ecs100.UIFileChooser;



public class UserInterface {
	double x1;//The shapes beginner x on canvas
	double y1;//The shapes beginner y on canvas
	boolean drawing = false;//Default no drawing on canvas
	List<Shape> shapes = new ArrayList<>();//Shape list
	//String mode = "DRAW";  // "DRAW" mode means draw line,"MOVE" mode means move the shapes which on canvas(When I use handlers to deal with mode switching, this line of code is no long needed.)
	
	MouseModeHandler currentHandler;//Current Mode Handler(idea from online searching)
	Shape selectedShape = null;//Default selected shape is null
	double prevX, prevY;//Record the last mouse position
	
	public UserInterface() {
		UI.initialise();
		UI.addButton("Add Oval", this::addOval);
		UI.addButton("Add Rectangle", this::addRectangle);
		UI.addButton("Add Text", this::addText);

		//UI.addButton("Draw", this::drawShapes);
		UI.addButton("Draw Lines", this::setDrawMode);
		//UI.addButton("Resize", this::setResizeMode);
		UI.addButton("Change Color", this::setColorMode);
		UI.addButton("Modify Size", this::setModifyMode);
		UI.addButton("Move Shape", this::setMoveMode);
		UI.addButton("Delete Shape", this::setDeleteMode);
		UI.addButton("Save file", this::saveFileHandler);
		UI.addButton("Load file", this::loadFileHandler);
		UI.addButton("Quit", UI::quit);
	}
		
	public void doMouse(String action, double x, double y) {//I think the doMouse() method is complicated if I want to do more actions onto the different shapes.
															//The idea of decoupling this method is my own, but the specific code implementation is based on teacher's codes and online resources.
		if (currentHandler != null) {
	        currentHandler.handle(action, x, y);
	    }
		
//		if(mode.equals("DRAW")) {//Choose the mode
//				if(!drawing && action.equals("pressed")) {
//				//1. Begin drawing, record the begin point
//				this.x1 = x;
//				this.y1 = y;
//				this.drawing = true;
//				
//			} else if(drawing && action.equals("dragged")) {
//				 //2. Clear canvas, draw a preview line
//				UI.clearGraphics();
//				drawShapes();//Redraw existing graphics
//		        UI.drawLine(x1, y1, x, y);//Temporary preview line (not stored)    
//		        
//			} else if (drawing && action.equals("released")) {
//				//3.Release the mouse, create a line and put into the list
//				Line line = new Line(x1, y1, x, y);
//				shapes.add(line);
//				//4.Redraw all the shapes(include the new line)
//				UI.clearGraphics();
//				drawShapes();
//				//5. Reset the status
//				this.drawing = false;
//			}
//			
//		}else if(mode.equals("MOVE")) {//This part of ideas comes from materials that I searched online. I can understand every line of code, but I can't design the ideas from a blank board.
//			if(action.equals("pressed")) {
//				for(int i = shapes.size() - 1; i >= 0; i--) {//Traverse the whole shapes list from the last to the first
//					Shape shape = shapes.get(i);//Get every shape in the list
//					if(shape.contains(x, y)) {//Determines whether the shape "contains" the mouse click position (x, y)
//						selectedShape = shape;//If the shape contains the mouse point, store it in the variable selectedShape, which means "you selected
//						prevX = x;//Take the current position as the starting position and start recording the mouse trajectory
//						prevY = y;//Same as previous line of code
//						break;
//					}
//				}
//			}else if(action.equals("dragged") && selectedShape != null) {
//				double dx = x - prevX;
//				double dy = y - prevY;
//				selectedShape.moveBy(dx, dy);
//				prevX = x;
//				prevY = y;
//				
//				UI.clearGraphics();
//				drawShapes();
//			}else if(action.equals("released")) {
//				selectedShape = null;
//			}
//		}

	}
	
	public void addRectangle() {
		Color color = JColorChooser.showDialog(null, "Choose Rectangle Color", Color.red);//Pop up a color picker to let users select a color
		double x = UI.askDouble("Enter X coordinate:");//Ask user to enter x-coordinate
		double y = UI.askDouble("Enter Y coordinate:");//Ask user to enter y-coordinate
		double width = UI.askDouble("Enter width:");//Ask user to enter width
		double height = UI.askDouble("Enter height:");//Ask user to enter height
			
		Rectangle rect = new Rectangle(x, y, width, height, color);//Create a new rectangle shape
		shapes.add(rect);//Add it into the shape list
		drawShapes();//Draw shape on the canvas
	}

	public void addOval() {
		Color color = JColorChooser.showDialog(null, "Choose Oval Color", Color.blue);
		double x = UI.askDouble("Enter X coordinate:");
		double y = UI.askDouble("Enter the y coordinate:");
		double width = UI.askDouble("Enter width:");
		double height = UI.askDouble("Enter height:");
		
		Oval oval = new Oval(x, y, width, height,color);
		shapes.add(oval);
		drawShapes();
	}

	public void addText() {
		String text = UI.askString("Enter text:");
	    double x = UI.askDouble("X:");
	    double y = UI.askDouble("Y:");
	    
	    Text textShape = new Text(x, y, text, Color.black);
	    shapes.add(textShape);
	    drawShapes();
	}
	
	public void drawShapes() {
		for(int i = 0; i < shapes.size(); i++) {//Traverse the list
			shapes.get(i).draw();//Get all the shapes in the list and draw shape on canvas
		}
	}
	
//	public void drawLine() {
//		mode = "DRAW";//Switch back to line drawing mode
//		UI.setMouseMotionListener(this::doMouse);
//	}
	
	public void setDrawMode() {//Click the button and change the mode
		/*
		 * Create a new DrawHandler object. 
		 * Pass it a reference to the current main class UserInterface (this).
		 * Set this DrawHandler as the current handler. 
		 * This will handle all future mouse events (execute the drawing logic).
		 */
	    currentHandler = new DrawHandler(this);  
	    UI.setMouseMotionListener(this::doMouse);//Setting the initial listener
	}
	
	public void setMoveMode() {//Click the button and change to move mode
		//mode = "MOVE";
		currentHandler = new MoveHandler(this);
		UI.setMouseMotionListener(this::doMouse);//Setting the initial listener
	}
	
	public void setColorMode() {//Click the button and change to move mode
		//mode = "MOVE";
		currentHandler = new ColorHandler(this);
		UI.setMouseMotionListener(this::doMouse);//Setting the initial listener
	}
	
	public void setDeleteMode() {
	    currentHandler = new DeleteHandler(this);  // Switch to delete mode
	    UI.setMouseMotionListener(this::doMouse);
	}

	public void setModifyMode() {
		currentHandler = new ModifyHandler(this);  // Switch to modify mode
	    UI.setMouseMotionListener(this::doMouse);
	}
	
	private void saveFileHandler() {//Save .pgm file logic
		 	int width = 500, height = 500;//Setting up canvas size
		    int[][] pixels = new int[height][width];//Create an pixel array

		    // Fill the background with white
		    for (int i = 0; i < height; i++) {//Out loop for each row (y-coordinate)
		        for (int j = 0; j < width; j++) {//Inner loop for each column (x-coordinate)
		            pixels[i][j] = 255;//Set all the background pixels to white
		        }
		    }
		    
		    for (int i = 0; i < shapes.size(); i++) { // Traverse all the shapes in the list
		        Shape shape = shapes.get(i);//Get current shape
		        shape.drawToPixels(pixels); // Let the shape draw itself onto the pixel array
		    }

		    // Save the file into .pgm format
		    try {
		        PrintWriter pw = new PrintWriter(new File("output.pgm"));// Create a writer for the output file
		        pw.println("P2");//Skip P2 in the file
		        pw.println(width + " " + height);//Image dimension
		        pw.println("255");//Max grayscale value
		        
		        for (int i = 0; i < height; i++) {//Write the pixel data
		            for (int j = 0; j < width; j++) {
		                pw.print(pixels[i][j] + " ");//Print the grayvalue
		            }
		            pw.println();//Line break
		        }
		        pw.close();//Close the file writer
		        UI.println("Saved as .pgm！");//Notify users
		    } catch (IOException e) {
		        UI.println("Failed to save." + e.getMessage());
		}
	}
	
	public void loadFileHandler() {
		 try {
			 
			 String fileName = UIFileChooser.open("Choose a PGM file");//Open file to choose
			 if (fileName == null) return;//If user cancels, exit the method
			 Scanner scanner = new Scanner(new File(fileName));//Create scanner to read the file
			 
		        scanner.next();  // Skip "P2"

		        int width = scanner.nextInt();//Read numbers of the file
		        int height = scanner.nextInt();
		        int maxGray = scanner.nextInt();

		        int[][] pixels = new int[height][width];//Create an array to store the pixel values
		       //Read the pixel value from file
		        for (int row = 0; row < height; row++) {
		            for (int col = 0; col < width; col++) {
		                pixels[row][col] = scanner.nextInt();//Store pixel value into the array
		            }
		        }

		        UI.clearGraphics();
		        drawPixelsToCanvas(pixels, maxGray);//Load the shape to canvas
		        scanner.close();
		    } catch (IOException e) {
		        UI.println("Error loading file: " + e.getMessage());
		    }
	}
	
	private void drawPixelsToCanvas(int[][] pixels, int maxGray) {
	    for (int row = 0; row < pixels.length; row++) {// Loop over each row (y-coordinate)
	        for (int col = 0; col < pixels[0].length; col++) {// Loop over each column (x-coordinate)
	            int grayValue = pixels[row][col]; // Get the grayscale value at this pixel
	            float gray = (float) grayValue / maxGray;//Normalize grayscale value to 0.0–1.0 range
	            Color c = new Color(gray, gray, gray);//Create a grayscale Color object
	            UI.setColor(c);// Set UI drawing color
	            UI.fillRect(col, row, 1, 1); // Draw a 1x1 pixel square at the corresponding position

	        }
	    }
	}

	public static void main(String[] args) {
		new UserInterface();

	}

}

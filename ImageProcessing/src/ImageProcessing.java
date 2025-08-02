import ecs100.UI;
import ecs100.UIFileChooser;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ImageProcessing {
	private int[][] imagePixels;   // 灰度图像像素
	private int width;
	private int height;
	private int colorDepth;



	public ImageProcessing() {
		UI.initialise();
		
		UI.addButton("Load file", this::loadFileHandler);
        UI.addButton("Invert", this::invertImage);
        UI.addButton("Save PGM", this::savePGM);

		UI.setMouseMotionListener(this::mouseMotionListener);
		
	}
	
	private void loadFileHandler() {
		UI.setColor(new Color(/* red */ 1.0f, /* green */ 1.0f, /* blue */ 1.0f));
		UI.fillRect(0, 0, 1000, 1000);
		
		// *** YOUR CODE HERE ***
		
		try {
			String fileName = UIFileChooser.open("Choose a .pgm file");
			File myFile = new File(fileName);
			Scanner scanner = new Scanner(myFile);
			scanner.next();//Skip P2
			this.width = scanner.nextInt();
			this.height = scanner.nextInt();
			this.colorDepth = scanner.nextInt();
			
			this.imagePixels = new int[height][width];
			
			for (int row = 0; row < height; row++) {//为什么行是高,列是宽?
	            for (int col = 0; col < width; col++) {
	                imagePixels[row][col] = scanner.nextInt();
	            }
	        }
	        scanner.close();
	      
		}catch(IOException e) {
			UI.println("File loaded.");
		}
		drawImageArrayToScreen();
	}
	
	private void drawImageArrayToScreen() {
	    if (imagePixels == null) return;

	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            int pixel = imagePixels[row][col];
	            float gray = (float) pixel / colorDepth;
	            Color c = new Color(gray, gray, gray);
	            UI.setColor(c);
	            UI.fillRect(col, row, 1, 1);
	        }
	    }

	}
	
	private void invertImage() {
	    if (imagePixels == null) return;

	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            imagePixels[row][col] = colorDepth - imagePixels[row][col];
	        }
	    }
	    drawImageArrayToScreen();
	}

	private void savePGM() {
	    if (imagePixels == null) return;

	    try {
	        String filename = UIFileChooser.save("Save image as .pgm");
	        PrintWriter pw = new PrintWriter(new File(filename));

	        pw.println("P2");
	        pw.println(width + " " + height);
	        pw.println(colorDepth);

	        for (int row = 0; row < height; row++) {
	            for (int col = 0; col < width; col++) {
	                pw.print(imagePixels[row][col] + " ");
	            }
	            pw.println();
	        }

	        pw.close();
	        UI.println("Saved image to " + filename);
	    } catch (IOException e) {
	        UI.println("Failed to save: " + e);
	    }
	}

	
	public static void main(String[] args) {
		new ImageProcessing();
	}

	private void mouseMotionListener(String action, double x, double y) {
		// You might need to use this for some of the extension. Don't worry about
		// it until then.
		// Documentation is at
		// https://ecs.victoria.ac.nz/foswiki/pub/Main/JavaResources/UI.html#setMouseMotionListener(ecs100.UIMouseListener)
	}
	
}

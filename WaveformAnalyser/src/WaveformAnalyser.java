// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a SWEN501 exercise.
// You may not distribute it in any other way without permission.

import ecs100.*;
import java.io.*;
import java.util.*;
import java.awt.Color;

/**
 * This program will read the data from a file into an ArrayList, which means
 * that it can analyse the numbers more easily and in more different ways. It
 * can also cope with much larger sets of numbers. The numbers are guaranteed to
 * be integers but the values can be negative and the signal swings above and
 * below zero.
 *
 * There are 11 methods you are to complete, all focusing on the ArrayList of
 * data.
 * 
 * CORE
 * 
 * doRead: reads numbers into an ArrayList.
 * 
 * doDisplay: displays the waveform.
 * 
 * doReportDistortion: prints out the fraction of time the signal is
 * distorted.
 * 
 * COMPLETION
 * 
 * doSpread: displays the spread with two horizontal lines
 * and returns its value.
 * 
 * doDisplayDistortion: shows in red the distorted part
 * of the signal.
 * 
 * doHighlightPeaks: plots the peaks with small green circles.
 * 
 * CHALLENGE
 * 
 * doNormalise: normalises all the values down so there is no
 * distortion.
 * 
 * upperEnvelope: displays the upper envelope.
 * 
 * lowerEnvelope: displays the lower envelope.
 * 
 * doSave: saves the current waveform values into a
 * file.
 * 
 * select and edit: let the user select a regions of the waveform with the
 * mouse to remove them. Add a save button to save the edited values.
 */

public class WaveformAnalyser {
	// Fields
	private ArrayList<Double> waveform; // the field to hold the ArrayList of
										// values

	// Constant: the threshold for the distortionLevel and showDistortion
	// methods
	public static final double THRESHOLD = 200;

	// Constants: the dimensions of the graph for the displayWaveform method
	public static final int GRAPH_LEFT = 10;
	public static final int ZERO_LINE = 360;

	// Constant fields holding the size of the circles for the highlightPeaks
	// method
	public static final int SIZE_CIRCLE = 10;

	/**
	 * [CORE] Clears the panes, Creates an ArrayList stored in a field, then
	 * Asks user for a waveform file (eg waveform1.txt) Reads data from the file
	 * into the ArrayList
	 */
	public void doRead() {
		try {
			UI.clearPanes();
			
			// These variables will be useful (give them real values!):
			String fileName = UIFileChooser.open();
			
			File myFile = new File(fileName);
			
			Scanner scanner = new Scanner(myFile);
			
			// Create a new ArrayList
			waveform = new ArrayList<Double>();
			
			/* # YOUR CODE HERE */
			
			
			// * Ask user for a file to read
			// * Open that file
			// * As long as there's another Double to read from it:
			//   - Take that double from the file
			//   - And put it into the list
			
			/* # YOUR CODE HERE */
			while (scanner.hasNextDouble()) {
	            double value = scanner.nextDouble();
	            waveform.add(value);
	        }
			
			UI.println("Read " + this.waveform.size() + " data points from " + fileName);
			scanner.close();
		} catch (FileNotFoundException e) {
			// The line above should be marked as an error when you start out,
			// but it will go away once you fill in your code above.
			e.printStackTrace();
			UI.println("File not found");
		}
	}

	/**
	 * [CORE] Displays the waveform as a line graph, The n'th value in waveform
	 * is displayed at x-position is GRAPH_LEFT + n y-position is ZERO_LINE -
	 * the value Plots a line graph of all the points with a blue line between
	 * each pair of adjacent points Draw the horizontal line representing the
	 * value zero. Uses GRAPH_LEFT and ZERO_LINE for the dimensions and
	 * positions of the graph. Don't worry if the lines go off the window
	 */
	public void doDisplay() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		UI.clearGraphics();

		// draw x axis (showing where the value 0 will be)
		UI.setColor(Color.black);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size(), ZERO_LINE);

		// plot points: blue line between each pair of values
		/* # YOUR CODE HERE */
		UI.setColor(Color.blue);
	    for (int i = 1; i < waveform.size(); i++) {
	        double x1 = GRAPH_LEFT + i - 1;
	        double y1 = ZERO_LINE - waveform.get(i - 1);
	        double x2 = GRAPH_LEFT + i;
	        double y2 = ZERO_LINE - waveform.get(i);
	        UI.drawLine(x1, y1, x2, y2);
	    }
	}

	/**
	 * [CORE] Computes and prints out the fraction of time the signal is
	 * distorted. This fraction of time is defined as the number of distorted
	 * values, divided by the number of values. A distorted value is defined as
	 * one whose absolute value is greater than the value of THRESHOLD [Hint]
	 * You may find Math.abs() useful for this method - it computes the absolute
	 * value
	 */
	public void doReportDistortion() {
		if (this.waveform == null) { // there is no data to analyse
			UI.println("No signal to analyse and report on");
			return;
		}
		//double fraction = 0;
		/* # YOUR CODE HERE */
		 int count = 0;
		    for (double val : waveform) {
		        if (Math.abs(val) > THRESHOLD) {
		            count++;
		        }
		    }
		    double fraction = (double) count / waveform.size();
		UI.printf("Fraction of time the signal is distorted %4.3f\n", fraction);
	}

	/**
	 * [COMPLETION] The spread is the difference between the maximum and minimum
	 * values of the waveform. Finds the maximum and minimum values, then
	 * Displays the spread by drawing two horizontal lines on top of the
	 * waveform: one green line for the maximum value, and one red line for the
	 * minimum value.
	 */
	public void doSpread() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		this.doDisplay();
		/* # YOUR CODE HERE */
		double min = waveform.get(0);
	    double max = waveform.get(0);

	    for (double val : waveform) {
	        if (val > max) max = val;
	        if (val < min) min = val;
	    }
	    UI.setColor(Color.green);
	    UI.drawLine(GRAPH_LEFT, ZERO_LINE - max, GRAPH_LEFT + waveform.size(), ZERO_LINE - max);
	    UI.setColor(Color.red);
	    UI.drawLine(GRAPH_LEFT, ZERO_LINE - min, GRAPH_LEFT + waveform.size(), ZERO_LINE - min);
	    UI.println("Spread: " + (max - min));
	}

	

	/**
	 * [COMPLETION] [Fancy version of doDisplay] Display the waveform as a line
	 * graph. Draw a line between each pair of adjacent points
	 * 
	 * * If neither of the points is distorted, the line is BLUE
	 * * If either of the two end points is distorted, the line is RED
	 * 
	 * Draw the horizontal lines representing the value zero and thresholds values.
	 * Uses THRESHOLD to determine distorted values.
	 * 
	 * Uses GRAPH_LEFT and ZERO_LINE for the dimensions and positions of the graph.
	 * 
	 * [Hint] You may find Math.abs(int a) useful for this method.
	 * You may assume that all the values are between -250 and +250.
	 */
	public void doDisplayDistortion() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		UI.clearGraphics();

		// draw zero axis
		UI.setColor(Color.black);
		UI.drawLine(GRAPH_LEFT, ZERO_LINE, GRAPH_LEFT + this.waveform.size(), ZERO_LINE);

		// draw thresholds
		/* # YOUR CODE HERE */
		 UI.drawLine(GRAPH_LEFT, ZERO_LINE - THRESHOLD, GRAPH_LEFT + waveform.size(), ZERO_LINE - THRESHOLD);
		    UI.drawLine(GRAPH_LEFT, ZERO_LINE + THRESHOLD, GRAPH_LEFT + waveform.size(), ZERO_LINE + THRESHOLD);

		    for (int i = 1; i < waveform.size(); i++) {
		        double val1 = waveform.get(i - 1);
		        double val2 = waveform.get(i);
		        double x1 = GRAPH_LEFT + i - 1;
		        double y1 = ZERO_LINE - val1;
		        double x2 = GRAPH_LEFT + i;
		        double y2 = ZERO_LINE - val2;
		        if (Math.abs(val1) > THRESHOLD || Math.abs(val2) > THRESHOLD) {
		            UI.setColor(Color.red);
		        } else {
		            UI.setColor(Color.blue);
		        }
		        UI.drawLine(x1, y1, x2, y2);
		    }
	}

	/**
	 * [COMPLETION] Plots the peaks with small green circles. A peak is defined
	 * as a value that is greater or equals to both its neighbouring values.
	 * Note the size of the circle is in the constant SIZE_CIRCLE
	 * 
	 * You may assume
	 * that peak values differ from their neighbouring points.
	 */
	public void doHighlightPeaks() {
		this.doDisplayDistortion(); // use doDisplay if doDisplayDistortion
									// isn't complete
		/* # YOUR CODE HERE */
		UI.setColor(Color.green);
	    for (int i = 1; i < waveform.size() - 1; i++) {
	        double prev = waveform.get(i - 1);
	        double curr = waveform.get(i);
	        double next = waveform.get(i + 1);
	        if (curr > prev && curr > next) {
	            double x = GRAPH_LEFT + i;
	            double y = ZERO_LINE - curr;
	            UI.fillOval(x - SIZE_CIRCLE / 2, y - SIZE_CIRCLE / 2, SIZE_CIRCLE, SIZE_CIRCLE);
	        }
	    }
	}

	/**
	 * [CHALLENGE] Finds the largest value (positive or negative) in the
	 * waveform, and normalises all the values down so that the largest value is
	 * now equal to the distortion threshold. Then redraws the waveform.
	 */
	public void doNormalise() {
		/* # YOUR CODE HERE */
		double max = 0;
	    for (double val : waveform) {
	        if (Math.abs(val) > max) {
	            max = Math.abs(val);
	        }
	    }
	    for (int i = 0; i < waveform.size(); i++) {
	        double newVal = waveform.get(i) * THRESHOLD / max;
	        waveform.set(i, newVal);
	    }
	    
	    
		this.doDisplayDistortion(); // use doDisplay if doDisplayDistortion
									// isn't complete
		
	}

	public void doEnvelope() {
		if (this.waveform == null) { // there is no data to display
			UI.println("No waveform to display");
			return;
		}
		this.doDisplay(); // display the waveform
		this.upperEnvelope();
		this.lowerEnvelope();
	}

	/**
	 * [CHALLENGE] Displays the upper envelope with GREEN lines connecting all
	 * the peaks. A peak is defined as a point that is greater or equal to
	 * *both* neighbouring points. DO NOT clear the graphics as we also want to
	 * view the waveform.
	 */
	public void upperEnvelope() {
		/* # YOUR CODE HERE */
		 UI.setColor(Color.green);
		    int prev = -1;
		    for (int i = 1; i < waveform.size() - 1; i++) {
		        double curr = waveform.get(i);
		        if (curr >= waveform.get(i - 1) && curr >= waveform.get(i + 1)) {
		            if (prev != -1) {
		                UI.drawLine(GRAPH_LEFT + prev, ZERO_LINE - waveform.get(prev),
		                            GRAPH_LEFT + i, ZERO_LINE - curr);
		            }
		            prev = i;
		        }
		    }
	}

	/**
	 * [CHALLENGE] Displays the lower envelope with RED lines connecting all the
	 * "negative" peaks. A "negative" peak is defined as a point that is smaller
	 * or equal to *both* neighbouring points. DO NOT clear the graphics as we
	 * also want to view the waveform.
	 */
	public void lowerEnvelope() {
		/* # YOUR CODE HERE */
		UI.setColor(Color.red);
	    int prev = -1;
	    for (int i = 1; i < waveform.size() - 1; i++) {
	        double curr = waveform.get(i);
	        if (curr <= waveform.get(i - 1) && curr <= waveform.get(i + 1)) {
	            if (prev != -1) {
	                UI.drawLine(GRAPH_LEFT + prev, ZERO_LINE - waveform.get(prev),
	                            GRAPH_LEFT + i, ZERO_LINE - curr);
	            }
	            prev = i;
	        }
	    }
	}

	/**
	 * [CHALLENGE] Saves the current waveform values into a file
	 */
	public void doSave() {
		/* # YOUR CODE HERE */
		String fileName = UIFileChooser.save();
	    try {
	        PrintStream ps = new PrintStream(new File(fileName));
	        for (double val : waveform) {
	            ps.println(val);
	        }
	        ps.close();
	        UI.println("Saved to " + fileName);
	    } catch (IOException e) {
	        UI.println("Failed to save file");
	    }
	}

	private int index1;

	/**
	 * [CHALLENGE] Lets user select a region of the waveform with the mouse and
	 * deletes that section of the waveform.
	 */
	public void doMouse(String action, double x, double y) {
		/* # YOUR CODE HERE */
		 if (action.equals("pressed")) {
		        index1 = (int)(x - GRAPH_LEFT);
		    } else if (action.equals("released")) {
		        int index2 = (int)(x - GRAPH_LEFT);
		        if (index1 < index2) {
		            waveform.subList(index1, index2).clear();
		        } else {
		            waveform.subList(index2, index1).clear();
		        }
		        doDisplay();
		    }
	}

	/** ---------- The code below is already written for you ---------- **/

	/**
	 * Constructor: Set up the ten buttons and mouselistener
	 */
	public WaveformAnalyser() {
		// core
		UI.addButton("Read Data", this::doRead);
		UI.addButton("Display Waveform", this::doDisplay);
		UI.addButton("Report Distortion", this::doReportDistortion);
		// completion
		UI.addButton("Spread", this::doSpread);
		UI.addButton("Display Distortion", this::doDisplayDistortion);
		UI.addButton("Peaks", this::doHighlightPeaks);
		// challenge
		UI.addButton("Normalise", this::doNormalise);
		UI.addButton("Envelope", this::doEnvelope);
		UI.addButton("Save", this::doSave);
		UI.addButton("Quit", UI::quit);
		UI.setMouseListener(this::doMouse); // only for challenge

	}

	// Main
	public static void main(String[] arguments) {
		new WaveformAnalyser();
	}

}

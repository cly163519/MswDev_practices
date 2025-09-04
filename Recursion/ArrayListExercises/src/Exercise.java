// This program is copyright VUW.
// You are granted permission to use it for a SWEN131 exercise.
// You may not distribute it in any other way without permission.
import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Three methods that use ArrayLists
 *
 * plotNumbers():
 *      read numbers from file into an ArrayList of numbers
 *      then access values.
 *  Uses an ArrayList. 
 *
 * readAndReverse():
 *      read tokens from file into an ArrayList of Strings
 *      then access values in reverse order
 *  Uses an ArrayList. 
 *
 * sieve():
 *      Make an ArrayList of integers containing 1 .. 100
 *      Let the use choose a number then remove all multiples of it from the list
 *  Uses an ArrayList. 
 *
 */

public class Exercise{

    /**
     * Constructor to set up an interface with buttons to call all the methods
     */
    public Exercise(){
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Show file", this::showFile);
        UI.addButton("Plot Numbers", this::plotNumbers);
        UI.addButton("ReadAndReverse", this::readAndReverse);
        UI.addButton("Sieve", this::sieve);
        UI.addButton("Quit", UI::quit);
    }

    /**
     * Reads a sequence of numbers from the file "numbers.txt" into an ArrayList
     * Finds the number half way through the sequence of numbers
     * [if the size of the list is n, the middle number is at index (n-1)/2 ]
     * Then plots the numbers as a sequence of rectangles of width = 5 and
     * height = the number being plotted,
     * The color is
     *    green if the number is less than the middle number,
     *    black if it is equal to the middle number, 
     *    red otherwise.
     * Note: it has to read all the numbers before it can plot any of them.
     */
    
    public void plotNumbers(){
        double base = 400;
        UI.drawLine(0, base, 600, base);     // draws the base line (x1,y1,x2,y2)
        ArrayList<Double> nums = new ArrayList<Double>(); // the ArrayList of numbers

        try{
            Scanner sc = new Scanner(new File("numbers.txt"));
            // read numbers from the file into the ArrayList
            while (sc.hasNextDouble()) {
                double d = sc.nextDouble();
                /*# YOUR CODE HERE */
                nums.add(d);                 
            }
            UI.println(nums);
            sc.close();
        } catch(IOException e){UI.println("Fail: " + e);}
        
        // plot the numbers, 
        double x = 0; // x position of first bar (increment by 6 each time)
        /*# YOUR CODE HERE */
        int size = nums.size();
        double middle = nums.get((size - 1)/2);
        
        for(int i = 0; i < size; i++) {
        	double currentNum = nums.get(i);//I don't understand this sentence.
        	//Set the colors
        	if(currentNum < middle) {
            	UI.setColor(Color.green);
            }else if (currentNum == middle){ //(Math.abs(currentNum - middle) < 0.0001)
            	UI.setColor(Color.black);
            }else {
            	UI.setColor(Color.red);
            }
        	
        	//Set the columns
        	if(currentNum < 0) {
        		UI.fillRect(x, base, 5, -currentNum);
        		UI.drawRect(x, base, 5, -currentNum);
        	
        	}else {
        		UI.fillRect(x, base - currentNum, 5, currentNum);
        		UI.drawRect(x, base - currentNum, 5, currentNum);
        	}
        	
        	x += 6;
        	UI.printf("Middle: %.10f, Current: %.10f\n", middle, currentNum);
        }
        
        

    }

    /** 
     * Asks the user for a file, and reads each token from the file, storing
     * them in an ArrayList.
     *     It prints out the number of tokens it read and then
     * prints the tokens in reverse order.
     */
    public void readAndReverse(){
        ArrayList<String> tokens = new ArrayList<String>(); // the ArrayList of tokens
        try{
            Scanner sc = new Scanner(new File(UIFileChooser.open()));
            while (sc.hasNext()) {
                String nextWord = sc.next();
                /*# YOUR CODE HERE */
                tokens.add(nextWord);
            }
            for(int i = tokens.size() - 1; i >= 0; i--) {
                UI.println(tokens.get(i));
            }
            UI.println(tokens);
        } catch(IOException e){UI.println("File reading failed");}
    }

    /**
     * Creates a list of integers, and adds each number from 1 to 100 to the list.
     * Prints them out.
     * Then repeatedly asks the user for a number, and removes all multiples of that
     * number (except for the number itself) from the list, and prints this list out again.
     * For example, if the user selects 5, it will remove 10, 15, 20, 25, 30, etc from the list
     * It should quit asking when the user selects 0.
     * Hints:
     *    you should work backwards down the list to remove multiples, not forwards.
     *    if m is a multiple of n, then m%n == 0  (the remainder) 
     */
    public void sieve (){
        ArrayList <Integer> numbers = new ArrayList<Integer>(); 
        /*# YOUR CODE HERE */
        for(int i = 1; i <= 100; i++) {
        	numbers.add(i);
        }
        
        UI.println(numbers);
        
        while(true) {
        	int number = UI.askInt("Number: ");
        	if(number == 0) {
        		break;
        	}
        	
        	for(int i = numbers.size() - 1; i > 0; i--) {//Why delete items should from the end of the array?
        		int num = numbers.get(i);        		
        		if((num % number) == 0 && num != number) {
        		numbers.remove(i);
        	}
        	
        }
        	UI.println(numbers);
        }
        
        
    }


    //=========================================================================

    /**
     * Utility method to list the contents of a file.
     */
    public void showFile(){
        String fname = UIFileChooser.open();
        UI.println("Contents of " + fname+":\n----------------");
        try{
            Scanner sc = new Scanner(new File(fname));
            while (sc.hasNextLine()) {
                UI.println(sc.nextLine());
            }
            sc.close();
            UI.println("--------------");
        } catch(IOException e){System.out.printf("Fail: %s\n", e);}
    }


    public static void main(String[] args){
        new Exercise();
    }        

}

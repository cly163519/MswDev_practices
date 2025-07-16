import java.util.ArrayList;

import ecs100.UI;

/*
 * The program should allow the user to:
1.Enter positive numbers until the user enters a negative number
2.Display all entered numbers, starting with a line that tells the user how many numbers are stored
3.Delete all stored numbers
4.Calculate the average of all the stored numbers
5.Change all stored numbers into negative numbers (e.g. x = -x)
6.Double all the stored numbers (e.g. x = 2*x)
*/

public class Numbers {
	private ArrayList<Integer> allNums = new ArrayList<Integer>();
	
	public Numbers() {
		// YOUR CODE HERE...
		UI.initialise();
		//Click New button, it will display the counts of the arraylist?
		UI.addButton("New", this::newList);//These lines all need to make a method to call.
		UI.addButton("Add", this::addNum);
		//UI.addButton("List", this::displayList);//Same thing as the "Add Button".
		UI.addButton("DeleteAll", this::deleteAll);
		UI.addButton("Average", this::averageNums);
		UI.addButton("Negative", this::changeToNeg);
		UI.addButton("Double", this::doubleNums);
		UI.addButton("Quit", UI::quit);//This line don't need a method to call.
		
	}
	
		public void addNum() {
			
			while(true) {
				Integer number = UI.askInt("Enter number: ");
				if(number < 0) {
					break;
				}
				this.allNums.add(number);
			}
			this.displayList();
		}
		
		public void displayList() {
			UI.clearText();//What does this line mean?
			UI.println(this.allNums.size());
			for(int i = 0; i < this.allNums.size(); i++) {
				UI.println(this.allNums.get(i));
			}
			UI.println("----------");
		}
		
	// YOUR CODE HERE...
		public void deleteAll() {//Focus on learning this logic.
			
			for (int i = allNums.size() - 1; i >= 0; i--) {
				//int num = UI.askInt("Number: ");
				//Integer removeNum = nums.get(num).remove(); 
				this.allNums.remove(i);
			}
			//this.allNums.clear();
			this.displayList();	
		    UI.println("All numbers deleted");
		    
		}
		
		public void averageNums() {
			int sum = 0;
			for(int i = 0; i < allNums.size(); i++) {
				sum += allNums.get(i);
			}
			double average = (double)sum / allNums.size();
			UI.println("The average is: " + average);
		}
		
		public void changeToNeg() {
			for(int i = 0; i < allNums.size(); i++) {
				int value = allNums.get(i);
				allNums.set(i, value * (-1));
			}
			for(int i = 0; i < allNums.size(); i++) {
				UI.println(allNums.get(i));
			}
			
			UI.println("All numbers are negative!");
		}
		
		public void doubleNums() {
			for(int i = 0; i < allNums.size(); i++) {
				int value = allNums.get(i);
				allNums.set(i, value * 2);
			}
			for(int i = 0; i < allNums.size(); i++) {
				UI.println(allNums.get(i));
			}
		}
	
		public void newList() {
			this.allNums = new ArrayList<Integer>();//
			this.displayList();
		}
		
		
	// The code below is pre-written for you and you do not need to change it. 
	public static void main(String[] args) {
		new Numbers();
	}
}

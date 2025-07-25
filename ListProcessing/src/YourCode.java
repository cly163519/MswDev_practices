import java.util.ArrayList;
import java.util.Random;

import ecs100.UI;

public class YourCode {
	// Return an ArrayList that contains twice each of the numbers
	// in the input list.
	//
	// For example, if input contained [1,2,10] this method should
	// return a list with [2,4,20].
	//
	// You should not modify the input ArrayList.
	public ArrayList<Double> mapTwice(ArrayList<Double> input) {
		/// YOUR CODE HERE
		ArrayList<Double> output = new ArrayList<>();//Create a new arrayList
		
		for(int i = 0; i < input.size(); i++) {//Traverse the whole list
			double num = input.get(i);//Pick up every item in the list
			double twice = 2 * num;//Double every item in the list
			output.add(twice);//Add the new items in the new arrayList which called output
		}
		return output;//Return the result of the output
	}

	// Return an ArrayList that contains the strings from the
	// input list, in the same order, converted to upper case.
	//
	// Hint: The toUpperCase() method on String returns an
	// uppercased version of the string.
	//
	// You should not modify the input ArrayList.
	public ArrayList<String> mapToUpper(ArrayList<String> input) {
		/// YOUR CODE HERE
		ArrayList<String> output = new ArrayList<>();//Create a new arrayList
		
		for(int i = 0; i < input.size(); i++) {//Traverse the whole list
			String str = input.get(i);//Create a variable called str,pick up them from the input list
			String toUpper = str.toUpperCase();//Change every picked up items to upper case
			output.add(toUpper);//Add them into the output list
		}		
		return output;//Return the outcome
	}

	// Return true if word is a palindrome, reading the same
	// backwards and forwards.
	//
	// Hint: the substring(int beginIndex, int endIndex)
	// method gives the part of the string from beginIndex
	// (inclusive) to endIndex (exclusive).
	// Hint: charAt(int index) gives the character at the
	// given position in a string as a char, which is a
	// primitive type that can be used as an integer.
	// Hint: Strings have a length() method.

		public boolean isPalindrome(String word) {
			/// YOUR CODE HERE
		    int beginIndex = 0;//The first index of the "word" String
		    int endIndex = word.length() - 1;//The last index of the "word" String 
		    
		    while (beginIndex < endIndex) {//Check the index
		        if (word.charAt(beginIndex) != word.charAt(endIndex)) {//If beginIndex not equals endIndex
		            return false;//Return not a Palindrome
		        }
		        beginIndex++;//Move the index of the beginIndex from left, increment of it
		        endIndex--;//Move the index of the endIndex from right,decrement of it
		    }
		    
		    return true;
		}

	// Return an ArrayList that contains the strings from the
	// input list starting with the given prefix, in the same
	// order they appeared.
	//
	// Hint: the startsWith(String prefix) method on String returns
	// true if and only if the string starts with the prefix.
	//
	// You should not modify the input ArrayList.
	public ArrayList<String> filterStarts(ArrayList<String> input, String prefix) {
		/// YOUR CODE HERE
		
		    ArrayList<String> output = new ArrayList<String>();//Create a new arrayList "output" to store the new words with the given prefix
		    String word = "";//Create a new word string
		    for (int i = 0; i < input.size(); i++) {//Traverse the input arraylist
		        if (word.startsWith(prefix)) {//If the word starts with the given prefix
		            output.add(word);//Add them into the new arrayList output
		        }
		    }
		    
		    return output;//Return the result
		}


	// Return the sum of all numbers in the input list.
	//
	// For example, if input contained [1,2,10] this method should
	// return 13.
	//
	// You should not modify the input ArrayList.
	public double foldSum(ArrayList<Double> input) {
		/// YOUR CODE HERE
		int sum = 0;//Create a sum variable
		for(int i = 0; i < input.size(); i++) {//Traverse every item in the whole "input" list
			sum += input.get(i);//Plus every item get from the list
		}
		UI.println("The sum is: " + sum);//Print the result of the sum of the list
		return sum;//Return the result
	}

	// Return whether the list is ordered from lowest to highest.
	//
	// For example, if input contained [1,2,3] this method should
	// return true, while if input contained [1,3,2] it should
	// return false.
	//
	// You should not modify the input ArrayList.
	public boolean isOrderedAscending(ArrayList<Double> input) {
		/// YOUR CODE HERE
		for(int i = 0; i < input.size(); i++) {//Traverse the whole list
			if(input.get(i) > input.get(i + 1)) {//If the item with the smaller index is bigger than the item with bigger index, which means the order of the list is Descending
				return false;//Return judgment result
			}
		}
		
		return true;//Return judgment result
	}

	// Return an ArrayList that contains the words from
	// list input1 that are in the same position in
	// input2.
	//
	// Remember: compare Strings with .equals(other)
	//
	// You should not modify the input ArrayLists.
	
	public ArrayList<String> matchingWords(
	        ArrayList<String> input1,
	        ArrayList<String> input2) {
		/// YOUR CODE HERE
	    ArrayList<String> output = new ArrayList<>();//Create a new arrayList
	    int minList = input1.size();//Create a new variable to determine the size of the list
		if (input2.size() < minList) {//Make a judgment about which one is smaller
			minList = input2.size();//Put the smaller number to the variable
		}

		for (int i = 0; i < minList; i++) {//Traverse the smaller list
			if (input1.get(i).equals(input2.get(i))) {//If there's a same word
				output.add(input1.get(i));//Add to a new list
			}
		}
	    return output;
	}


	// Return an ArrayList that contains all the words in
	// the input1 list that are also in the input2 list.
	//
	// Hint: You will use nested loops.
	// Hint: You will probably need a boolean variable.
	//
	// You should not modify the input ArrayLists.
	public ArrayList<String> intersection(
			ArrayList<String> input1,
			ArrayList<String> input2) {
		/// YOUR CODE HERE
		ArrayList<String> output = new ArrayList<>();//Create new arraylist
		
		for (int i = 0; i < input1.size(); i++) {//traverse the input1 list
			String word1 = input1.get(i);//Create new String variable word2
			boolean match = false;//Create boolean match variabel
			for (int j = 0; j < input2.size(); j++) {//Traverse the input2 list
				String word2 = input2.get(j);//Create new variable of word2
				if (word1.equals(word2)) {//Make a judgment of word1 and word2
					match = true;//If match,then return true;
					break;//Break the loop
				}
			}
			if (match) {
				output.add(word1);//add the match word to the list
			}
		}

		return output;
	
	}

	// Return an ArrayList that contains all the words in
	// the input1 list with any duplicate occurrences
	// removed. Keep the first time any duplicate word
	// appears.
	//
	// Hint: You will use nested loops.
	// Hint: Remember that everything is equal to itself.
	//
	// You should not modify the input ArrayList.
	public ArrayList<String> noDuplicates(
			ArrayList<String> input) {
		/// YOUR CODE HERE
		ArrayList<String> output = new ArrayList<>();//Create a new arraylist
		for (int i = 0; i < input.size(); i++) {//Traverse the input list
			String word = input.get(i);//Create a new string called word
			boolean match = false;//Create a boolean variable called match
			for (int j = 0; j < output.size(); j++) {//Traverse the list
				if (word.equals(output.get(j))) {//If the word in the list
					match = true;//Break the loop
					break;
				}
			}
			if (!match) {//If not in the list
				output.add(word);//Add to the new list
			}
		}
		return output;


	}

	// Return an ArrayList that contains all the words in
	// the input1 list that are NOT in the input2 list.
	// Duplicated words in input1 that are not in input2
	// will appear more than once.
	//
	// You should not modify the input ArrayLists.
	public ArrayList<String> difference(
			ArrayList<String> input1,
			ArrayList<String> input2) {
		/// YOUR CODE HERE
		ArrayList<String> output = new ArrayList<>();//Create a new arrayList
		
		for(int i = 0; i < input2.size(); i++) {//Traverse the input2 list
			boolean match = false;//Create a boolean variable match
			String word2 = input2.get(i);//Create a string variable word2
			for(int j = 0; j < input1.size(); j++) {//Traverse the input1 list
				String word1 = input1.get(i);//Create the new string variable word1
				if(word2.equals(word1)) {//If word2 equals word2
				match = true;//They are matched and break the loop to check the next item
				break;
				}
			}
			if(!match) {//If the two words doesn't match, add it into the new list
				output.add(word2);//Add into a new list
			}
		}
		return output;//Return the result
	}

	// Return an ArrayList that contains all the words that
	// are in exactly one of the input lists. Duplicated words
	// in each list should appear appropriately.
	//
	// You should not modify the input ArrayLists.
	public ArrayList<String> symmetricDifference(
			ArrayList<String> input1,
			ArrayList<String> input2) {
		/// YOUR CODE HERE
		ArrayList<String> output = new ArrayList<>();//Create the new list
		for (int i = 0; i < input1.size(); i++) {// Traverse the input1 list
			String word1 = input1.get(i);//Create the new String variable word1
			boolean found = false;//Create new boolean variable 
			for (int j = 0; j < input2.size(); j++) {//Traverse the input2 list
				if (word1.equals(input2.get(j))) {//If the the words are the same
					found = true;//Give the true value to the boolean variable
					break;//Break the loop
				}
			}
			if (!found) {// If the word1 in the in the input2, put it into output
				output.add(word1);//Add word1 into the output list
			}
		}
		return output;
	}

	// Reverse the input list, in place.
	//
	// Hint: The set(index, value) method on ArrayLists updates
	// the value stored at a given index.
	public void reverse(ArrayList<String> input) {
		/// YOUR CODE HERE
		int size = input.size();//Create the size variable to install the size of the input list
		for (int i = 0; i < size / 2; i++) {//Traverse the half of the list
			String temp = input.get(i);//Create a temp variable to install the first position item of the input list
			input.set(i, input.get(size - 1 - i));//Put the first position the last item of original list
			input.set(size - 1 - i, temp);//Put the temp value to the last position of the original list
		}
	}

	// Shuffle the input list, in place, by swapping the element
	// at each position with one at a randomly-chosen equal or
	// later index
	//
	// Hint: You can generate a random integer from 0 to n - 1 with
	// (int)(Math.random() * n), and one from x to n - 1 with
	// x + (int)(Math.random() * (n - x)).
	public void shuffle(ArrayList<Double> input) {
		/// YOUR CODE HERE
		Random rand = new Random();//Create a new random object
		for(int i = 0; i < input.size(); i++) {//Traverse the whole list
			
		}
		
		
		
	}

	// Return true if word1 and word2 are anagrams of one
	// another. One word is an anagram of another if it
	// has all the letters of the other word in a different
	// order.
	//
	// Hint: the substring(int beginIndex, int endIndex)
	// method gives the part of the string from beginIndex
	// (inclusive) to endIndex (exclusive).
	// Hint: charAt(int index) gives the character at the
	// given position in a string as a char, which is a
	// primitive type that can be used as an integer.
	public boolean isAnagram(String word1, String word2) {
		if(word1.length() != word2.length()) {//If the length of the words are not same, return false
			return false;
		}
		for(int i = 0; i < word1.length(); i++) {//Traverse the word1 list
			
			for(int j = 0; j < word2.length(); j++) {//Traverse the word2 list
				
			}
		}
		
		
		return true;
	}

	// Return an ArrayList with a rolling five-item Olympic average
	// of the numbers in the input list.
	//
	// An Olympic average of five numbers is the average of the three
	// numbers left after ignoring the lowest and highest numbers. For
	// example, the Olympic average of [9,7,5,6,8] is the average of
	// 7, 6, and 8 (which is 7: (7 + 6 + 8) / 3 = 21 / 3).
	//
	// A rolling average over a list of numbers is a list of the averages
	// of consecutive numbers in the first list centred around each
	// possible point in the first list.
	//
	// Your method should return an Olympic average of each five-item
	// sequence of consecutive numbers in the list.
	//
	// You should not modify the input ArrayList.
	public ArrayList<Double> olympicAverages(ArrayList<Double> input) {
		/// YOUR CODE HERE
		ArrayList<Double> output = new ArrayList<>();//Create a new arraylist output
		
		
		
		return null;
	}

	// Order all the numbers in the input list from lowest to highest.
	//
	// Hint: You can loop over the list repeatedly until it is in order,
	// swapping adjacent items that are out of order.
	public void orderAscending(ArrayList<Double> input) {
		/// YOUR CODE HERE
		
		boolean swapped = true;//Create a new boolean variable
		while (swapped) {//Use while loop
			swapped = false;
			for (int i = 0; i < input.size() - 1; i++) {//Traverse the whole input list
				if (input.get(i) > input.get(i + 1)) {//If the later index value is smaller than the before one
					Double temp = input.get(i);//Create a temp variable and put before index value into it
					input.set(i, input.get(i + 1));//Set the latter index value as the before one
					input.set(i + 1, temp);//Set the temp value to the later one, in order to reverse the position of i and i+1 value, and i+1 value is bigger than i value
					swapped = true;//Finished changing the order of the list
				}
			}
		}
	}

		
	

	// The previous method takes up to n^2 steps for a list with size n.
	// It could be faster. This method should do the same thing, but
	// taking many fewer steps.
	public void orderAscendingFaster(ArrayList<Double> input) {
		/// YOUR CODE HERE
		
		
		
	}

	// Reverse a string correctly.
	public String reverseString(String input) {
		/// YOUR CODE HERE
		String reversed = "";//Create an empty string
		for (int i = input.length() - 1; i >= 0; i--) {//Traverse the whole string from the last position
			reversed += input.charAt(i);//Pick up every char in the string and assemble them into a reversed new string
		}
		return reversed;//Return the new string
	}

	public static void main(String[] args) {
		new UserInterface();
	}
}

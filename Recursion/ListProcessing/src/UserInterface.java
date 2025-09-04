import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import ecs100.UI;
import ecs100.UIFileChooser;

public class UserInterface {

	private YourCode yc;
	private ArrayList<String> wordList;
	private ArrayList<String> wordList2;
	private ArrayList<Double> numList;

	public ArrayList<String> readWordFile(String filename) {
		ArrayList<String> words = new ArrayList<String>();
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNext()) {
				String nextWord = scanner.next();
				words.add(nextWord);
			}
			scanner.close();
		} catch (IOException ex) {

		}
		return words;
	}

	/// ********************************************************
	/// The code below is provided for you, and sets up the user
	/// interface to use the methods you wrote above.
	public void loadWordFile() {
		String filename = UIFileChooser.open();
		wordList = readWordFile(filename);
		UI.println("Loaded " + wordList.size() + " words from " + filename + ".");
	}

	public void loadWordFile2() {
		String filename = UIFileChooser.open();
		wordList2 = readWordFile(filename);
		UI.println("Loaded " + wordList2.size() + " words from " + filename + ".");
	}

	public void listWords() {
		UI.clearText();
		for (int i = 0; i < wordList.size(); i++) {
			UI.println(wordList.get(i));
		}
	}

	public void listNumbers() {
		UI.clearText();
		for (int i = 0; i < numList.size(); i++) {
			UI.println(numList.get(i));
		}
	}

	public void enterNumbers() {
		UI.clearText();
		String text = UI.askString("Enter numbers, separated by spaces:");
		numList = new ArrayList<Double>();
		for (String s : text.split(" "))
			try {
				numList.add(Double.parseDouble(s));
			} catch (NumberFormatException ignored) {
				UI.println("Bad number '" + s + "'.");
			}
		UI.println("Loaded " + numList.size() + " numbers.");
	}

	public void enterWords() {
		UI.clearText();
		String text = UI.askString("Enter words, separated by spaces:");
		wordList = new ArrayList<String>();
		for (String s : text.split(" "))
			wordList.add(s);
		UI.println("Loaded " + wordList.size() + " words.");
		text = UI.askString("Enter words for second list, separated by spaces:");
		wordList2 = new ArrayList<String>();
		for (String s : text.split(" "))
			wordList2.add(s);
		UI.println("Loaded " + wordList2.size() + " words in secondary list.");
	
	}

	interface ListFunc<T> {
		public ArrayList<T> run(ArrayList<T> input);
	}

	interface ListFunc1<T> {
		public ArrayList<T> run(ArrayList<T> input, T start);
	}

	interface ListFuncRet<T,R> {
		public R run(ArrayList<T> input);
	}

	interface ListsFunc<T> {
		public ArrayList<T> run(ArrayList<T> input1,
				ArrayList<T> input2);
	}

	public <T> void processList(ArrayList<T> input,
			ListFunc<T> func) {
		Iterator<T> iter = input.iterator();
		ArrayList<T> out = func.run(input);
		UI.clearText();
		iter.next();
		if (out == null) {
			UI.println("(not implemented)");
			return;
		}
		for (T d : out)
			if (d instanceof Double)
				UI.printf("%.2f\n", d);
			else
				UI.println(d);
	}

	public <T> void processListArg(ArrayList<T> input,
			T arg,
			ListFunc1<T> func) {
		Iterator<T> iter = input.iterator();
		ArrayList<T> out = func.run(input, arg);
		UI.clearText();
		iter.next();
		if (out == null) {
			UI.println("(not implemented)");
			return;
		}
		for (T d : out)
			UI.println(d);
	}

	public <T,R> void processListRet(ArrayList<T> input,
			ListFuncRet<T,R> func) {
		Iterator<T> iter = input.iterator();
		R out = func.run(input);
		UI.clearText();
		iter.next();
		if (out == null) {
			UI.println("(not implemented)");
			return;
		}
		UI.println(out);
	}

	public <T> void processLists(ArrayList<T> input1,
			ArrayList<T> input2,
			ListsFunc<T> func) {
		Iterator<T> iter1 = input1.iterator();
		Iterator<T> iter2 = input2.iterator();
		ArrayList<T> out = func.run(input1, input2);
		UI.clearText();
		iter1.next();
		iter2.next();
		if (out == null) {
			UI.println("(not implemented)");
			return;
		}
		for (T d : out)
			UI.println(d);
	}

	public void mapTwice() {
		this.processList(numList, yc::mapTwice);
	}

	public void mapToUpper() {
		processList(wordList, yc::mapToUpper);
	}

	public void isPalindrome() {
		UI.clearText();
		String word = UI.askString("Enter a word to check for being a palindrome:");
		boolean ret = yc.isPalindrome(word);
		UI.println(ret ? "yes" : "no");
	}

	public void filterStarts() {
		UI.clearText();
		String start = UI.askString("Prefix to filter: ");
		processListArg(wordList, start, yc::filterStarts);
	}

	public void foldSum() {
		UI.clearText();
		processListRet(numList, yc::foldSum);
	}

	public void isOrderedAscending() {
		UI.clearText();
		processListRet(numList, yc::isOrderedAscending);
	}

	public void matchingWords() {
		processLists(wordList, wordList2, yc::matchingWords);
	}

	public void intersection() {
		processLists(wordList, wordList2, yc::intersection);
	}

	public void noDuplicates() {
		processList(wordList, yc::noDuplicates);
	}

	public void difference() {
		processLists(wordList, wordList2, yc::difference);
	}

	public void symmetricDifference() {
		processLists(wordList, wordList2, yc::symmetricDifference);
	}

	public void reverse() {
		yc.reverse(wordList);
		UI.clearText();
		for (String d : wordList)
			UI.println(d);
	}

	public void shuffle() {
		yc.shuffle(numList);
		UI.clearText();
		for (Double d : numList)
			UI.println(d);
	}

	public void isAnagram() {
		UI.clearText();
		String word1 = UI.askString("Enter the first word to check:");
		String word2 = UI.askString("Enter the second word to check:");
		UI.println(yc.isAnagram(word1, word2) ? "yes" : "no");
	}

	public void olympicAverages() {
		processList(numList, yc::olympicAverages);
	}

	public void orderAscending() {
		yc.orderAscending(numList);
		UI.clearText();
		for (double d : numList)
			UI.println(d);
	}

	public void orderAscendingFaster() {
		yc.orderAscendingFaster(numList);
		UI.clearText();
		for (double d : numList)
			UI.println(d);
	}

	public void reverseString() {
		UI.clearText();
		String text = UI.askString("Enter the string to reverse:");
		UI.println(yc.reverseString(text));
	}

	public ArrayList<Double> generateDoubleList() {
		ArrayList<Double> nums = new ArrayList<Double>();
		int rd = 1433 * 17;
		for (int i = 0; i < 100; i++) {
			rd = (rd * 2347 + 1741) % 1000;
			nums.add(rd / 10.0);
		}
		return nums;
	}
	
	boolean buttonSet1 = false;
	
	public void swapButtons() {
		UI.initialise();
		UI.setDivider(1.0);
		buttonSet1 = !buttonSet1;
		if (buttonSet1) {
			UI.addButton("Swap to buttons 2/2", this::swapButtons);
			UI.addButton("Load words file", this::loadWordFile);
			UI.addButton("Load secondary words file", this::loadWordFile2);
			UI.addButton("List words", this::listWords);
			UI.addButton("List numbers", this::listNumbers);
			UI.addButton("Enter new numbers", this::enterNumbers);
			UI.addButton("Enter new words", this::enterWords);
			UI.addButton("Call mapTwice", this::mapTwice);
			UI.addButton("Call mapToUpper", this::mapToUpper);
			UI.addButton("Call isPalindrome", this::isPalindrome);
			UI.addButton("Call filterStarts", this::filterStarts);
			UI.addButton("Call foldSum", this::foldSum);
			UI.addButton("Call isOrderedAscending", this::isOrderedAscending);
		} else {
			UI.addButton("Swap to buttons 1/2", this::swapButtons);
			UI.addButton("Call matchingWords", this::matchingWords);
			UI.addButton("Call intersection", this::intersection);
			UI.addButton("Call noDuplicates", this::noDuplicates);
			UI.addButton("Call difference", this::difference);
			UI.addButton("Call symmetricDifference", this::symmetricDifference);
			UI.addButton("Call reverse", this::reverse);
			UI.addButton("Call shuffle", this::shuffle);
			UI.addButton("Call isAnagram", this::isAnagram);
			UI.addButton("Call olympicAverages", this::olympicAverages);
			UI.addButton("Call orderAscending", this::orderAscending);
			UI.addButton("Call orderAscendingFaster", this::orderAscendingFaster);
			UI.addButton("Call reverseString", this::reverseString);
		}
	}

	public UserInterface() {
		yc = new YourCode();
		UI.initialise();
		UI.setDivider(1.0);
		UI.addButton("Load words file", this::loadWordFile);
		UI.addButton("Load secondary words file", this::loadWordFile2);
		UI.addButton("List words", this::listWords);
		UI.addButton("List numbers", this::listNumbers);
		UI.addButton("Enter new numbers", this::enterNumbers);
		UI.addButton("Enter new words", this::enterWords);
		UI.addButton("Call mapTwice", this::mapTwice);
		UI.addButton("Call mapToUpper", this::mapToUpper);
		UI.addButton("Call isPalindrome", this::isPalindrome);
		UI.addButton("Call filterStarts", this::filterStarts);
		UI.addButton("Call foldSum", this::foldSum);
		UI.addButton("Call isOrderedAscending", this::isOrderedAscending);
		UI.addButton("Call matchingWords", this::matchingWords);
		UI.addButton("Call intersection", this::intersection);
		UI.addButton("Call noDuplicates", this::noDuplicates);
		UI.addButton("Call difference", this::difference);
		UI.addButton("Call symmetricDifference", this::symmetricDifference);
		UI.addButton("Call reverse", this::reverse);
		UI.addButton("Call shuffle", this::shuffle);
		UI.addButton("Call isAnagram", this::isAnagram);
		UI.addButton("Call olympicAverages", this::olympicAverages);
		UI.addButton("Call orderAscending", this::orderAscending);
		UI.addButton("Call orderAscendingFaster", this::orderAscendingFaster);
		UI.addButton("Call reverseString", this::reverseString);
		swapButtons();
		wordList = readWordFile("defaultwordsa.txt");
		UI.println("Loaded default words file with "
				+ wordList.size() + " words.");
		wordList2 = readWordFile("defaultwordsb.txt");
		UI.println("Loaded default secondary words file with "
				+ wordList2.size() + " words.");
		numList = generateDoubleList();
		UI.println("Loaded default number list.");
		UI.println("");
		UI.println("If the button for your function is not visible");
		UI.println("even after clicking the \"Swap buttons\" button,");
		UI.println("you may need to comment out some lines in the");
		UI.println("constructor of UserInterface at the bottom of the");
		UI.println("UserInterface file to remove some of the buttons");
		UI.println("you are not using.");
	}

}

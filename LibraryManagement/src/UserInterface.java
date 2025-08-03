import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import ecs100.UI;

public class UserInterface {

	public UserInterface() {
		UI.initialise();
		UI.addButton("List authors", this::listAuthors);
		UI.addButton("List all books", this::listBooks);
		UI.addButton("List books of author", this::listAuthorBooks);
		UI.addButton("Look up book by title", this::lookUpBook);
		UI.addButton("Issue book", this::issueBook);
		
		try {
			Scanner scan = new Scanner(new File("books.txt"));
			// Write code here to load books.txt into objects
			// and lists you design. You will need to make
			// classes, write loops to load the file,
			// and store objects made from your classes into
			// lists.
			//
			// The file is organised in blocks by author.
			// The first line is an author's name, then
			// either one or two numbers for birth and death years.
			// Following that, two lines for each book containing
			// the title and the year of publication.
			// A line containing "---" is the end of a block,
			// and then the next line (if any) starts the next block.
			while (scan.hasNext()) {
				String s = scan.nextLine();
				UI.println(s);
			}
		} catch (IOException e) {
			UI.println("File error: " + e);
			e.printStackTrace();
		}
	}
	
	public void listAuthors() {
		// List names, lifetimes, and number of books
		// in the library for all authors
	}
	
	public void listBooks() {
		// List titles of all books
	}
	
	public void listAuthorBooks() {
		// List titles of all books by a chosen author
	}
	
	public void lookUpBook() {
		// Show book, publication date, and author information
	}
	
	public void issueBook() {
		// Issue a book to a patron.
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}

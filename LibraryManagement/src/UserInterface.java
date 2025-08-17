import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ecs100.UI;

public class UserInterface {
	List<Author> authors = new ArrayList<>();
	List<Book> books = new ArrayList<>();//泛型里面的类,为什么不用String?尖括号里什么时候空白?什么时候填东西?
	
	public UserInterface() {
		UI.initialise();
		UI.addButton("List authors", this::listAuthors);
		UI.addButton("List all books", this::listBooks);
		UI.addButton("List books of author", this::listAuthorBooks);
		UI.addButton("Look up book by title", this::lookUpBook);
		UI.addButton("Issue book", this::issueBook);
		
		try {
            Scanner sc = new Scanner(new File("books.txt"));

            while (sc.hasNextLine()) {
                String authorName = sc.nextLine().trim();
                if (authorName.length() == 0) {
                    continue;
                }
                if (authorName.equals("---")) {
                    continue;
                }

                // 读年份
                if (!sc.hasNextLine()) break;
                String yearsLine = sc.nextLine().trim();
                String[] parts = yearsLine.split("\\s+");//按照**空白符（空格/Tab）**来分割。\\s+ 表示“一个或多个空白字符”。"1899 1961".split("\\s+") → ["1899", "1961"]如果只有一个年份 "1960"，那结果就是 ["1960"]。

                int birth = Integer.parseInt(parts[0]);
                int death = -1;
                if (parts.length > 1) {
                    death = Integer.parseInt(parts[1]);
                }
                Author author = new Author(authorName, birth, death);

                // 读书籍直到遇到 ---
                while (sc.hasNextLine()) {
                    String title = sc.nextLine().trim();
                    if (title.length() == 0) {
                        continue;
                    }
                    if (title.equals("---")) {
                        break;
                    }
                    if (!sc.hasNextLine()) {
                        break;
                    }
                    String yearLine = sc.nextLine().trim();
                   
                    int pubYear = Integer.parseInt(yearLine);
                    Book book = new Book(title, pubYear, author);
                    author.addBook(book);
                    books.add(book);
                }

                authors.add(author);
            }

            sc.close();
        } catch (IOException e) {
            System.out.println("File error: " + e);
        }
    }

	
	public void listAuthors() {
		// List names, lifetimes, and number of books
		// in the library for all authors
		for(int i = 0; i < authors.size(); i++) {
			Author a = authors.get(i);
			String years = "";
			String lifetime = "";
			
			if(a.getBirthYear() != -1) {
				years = "" + a.getBirthYear();
				if(a.getDeathYear() != -1) {
					years = years + "-" + a.getDeathYear();
					
					int age = a.getDeathYear() - a.getBirthYear();
					lifetime = ", lifetime=" + age;
				}
			}
			UI.println((i + 1) + ". " + a.getName() + " (" + years + lifetime + ") books=" + a.getBookName().size()); 
		}
	}
	
	public void listBooks() {
		// List titles of all books
		for(int i = 0; i < books.size(); i++) {
			//Book book = new Book(title, publishYear, author);//这里不能new book?
			Book book = books.get(i);
			UI.println((i + 1) + "." + book.getTitle());
		}
	}
	
	public void listAuthorBooks() {
		// List titles of all books by a chosen author
		UI.println("Enter author name: ");
		String name = UI.askString("<<");
//		for(int i = 0; i < books.size(); i++) {
//			Book book = books.get(i);
//			if(book.getAuthor().getName().equalsIgnoreCase(name)) {//得到这本书的作者对象 → 再拿到作者的名字（字符串）
//				UI.println(book.getTitle() + "(" + book.getPublishYear() + ")");
//			}
//		}
		for(int i = 0; i < authors.size(); i++) {
			Author author = authors.get(i);
			if(author.getName().equalsIgnoreCase(name));{
				List<Book> books = author.getBookName();
				for(int j = 0; j < books.size(); j++) {
					Book book = books.get(j);
					UI.println((j + 1) + "." + book.getTitle() + book.getPublishYear());
				}
				
			}
		}
	}
	
	public void lookUpBook() {
		// Show book, publication date, and author information
		 // 输入书名
	    UI.println("Enter book title:");
	    String title = UI.askString(">> ");

	    // 遍历查找
	    for (int i = 0; i < books.size(); i++) {
	        Book b = books.get(i);
	        if (b.getTitle().equalsIgnoreCase(title)) {
	            UI.println("Title: " + b.getTitle());
	            UI.println("Published: " + b.getPublishYear());
	            UI.println("Author: " + b.getAuthor().getName());
	            return; // 找到就退出
	        }
	    }
	    UI.println("Book not found.");
	}
	
	public void issueBook() {
		// Issue a book to a patron.
		UI.println("Enter book title to issue:");
	    String title = UI.askString(">> ");

	    for (int i = 0; i < books.size(); i++) {
	        Book b = books.get(i);
	        if (b.getTitle().equalsIgnoreCase(title)) {
	            if (!b.isIssued()) {
	                UI.println("Enter patron name:");
	                String patron = UI.askString(">> ");
	                b.isIssued();
	                b.getIssuedTo(patron);
	                UI.println("Book issued to " + patron);
	            } else {
	                UI.println("Book is already issued to " + b.getIssuedTo());
	            }
	            return;
	        }
	    }
	    UI.println("Book not found.");
	}
	
	public static void main(String[] args) {
		new UserInterface();
	}
}

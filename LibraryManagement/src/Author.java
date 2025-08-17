import java.util.ArrayList;
import java.util.List;

public class Author {
	String name;
	int birthYear;
	int deathYear;
	List<Book> bookName;
	
	public Author(String name, int birthYear, int deathYear) {
		this.name = name;
		//this.bookName = bookName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.bookName = new ArrayList<Book>();//这句不懂
	}
	
	public void addBook(Book b) {//这段不懂,用法?
		this.bookName.add(b);
	}
	
	public String getName() {
		return name;
	}
	
	public List<Book> getBookName(){
		return bookName;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public int getDeathYear() {
		return deathYear;
	}
}

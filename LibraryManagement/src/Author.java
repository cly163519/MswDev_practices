//import java.sql.Date;
import java.util.List;

public class Author {
	String name;
	int birthYear;
	int deathYear;
	List<String> bookName;
	
	public Author(String name, int birthYear, int deathYear) {
		this.name = name;
		//this.bookName = bookName;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
	}
	
	public String name() {
		return name;
	}
	
	public List<String> getBookName(){
		return bookName;
	}
	
	public int getBirthYear() {
		return birthYear;
	}
	
	public int getDeathYear() {
		return deathYear;
	}
}

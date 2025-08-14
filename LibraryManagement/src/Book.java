//import java.sql.Date;


public class Book {
	String title;//String bookName;不同类不要取一样名字?
	Author author;//我用的String author
	int publishYear;
	boolean issued;//没想出
	String issuedTo;//没想出
	
	public Book(String title, int publishYear, Author author) {//不用把所有成员变量都包含进来?
		this.title = title;
		this.author = author;
		this.publishYear = publishYear;
		this.issued = false;
		this.issuedTo = null;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Author getAuthor() {
		return author;
	}
	
	public int getPublishYear() {
		return publishYear;
	}
	
	public boolean isIssued() {
		return issued;
	}
	
	public String getIssuedTo() {//issuedTo和issued有什么区别?
		return issuedTo;
	}
	
	public void issuedTo(String name) {//这段啥意思?看不懂
		this.issued = true;
		this.issuedTo = name;
	}
}

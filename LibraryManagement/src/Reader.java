import java.util.List;

public class Reader {
	String readerName;
	List<String> issuedBook;
	
	public Reader(String readerName, List<String> issuedBook) {
		this.readerName = readerName;
		this.issuedBook = issuedBook;
	}
	
	public String getReaderName() {
		return readerName;
	}
	
	public List<String> getIssuedBook(){
		return issuedBook;
	}
}

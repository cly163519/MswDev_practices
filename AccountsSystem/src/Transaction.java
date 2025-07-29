import java.time.LocalDate;

public class Transaction {
	public String item;
	public double amount;
	public LocalDate date;
	
	public Transaction(String item, double amount) {
		this.item = item;
		this.amount = amount;
		this.date = LocalDate.now();//这句啥意思?
		
	}
	
	public String getItem() {
		return item;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public LocalDate getDate() {
		return date;
	}
}

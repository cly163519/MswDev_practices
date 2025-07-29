import java.util.List;

public interface Contact {
	
	String getName();
	String getDetails();
	void addTransaction(Transaction t);//写这行要求添加transaction类,为啥其他方法就没要求?
	List<Transaction> getTransactions();//始终不明白这行,list是接口?API用法?
	double getBalance();
	boolean isBirthdayToday();
	boolean isGSTRegistered();
	String getGSTNumber();
	
}

import java.sql.Date;
import java.util.List;

public interface Contact {
	String getName();
	String getDetails();//这里取什么名字,如getName or getDetails和主函数main里的方法名没关系?
	double getBalance();
	void recordTransaction(String type, double amount);
	List<String> getTransactions();//主类里写contact,这里又写string,矛盾吗?
	void setGSTRegistration(boolean registered,String gstNumber);
	String getGSTNumber();
	boolean isPerson();
	boolean hasBirthday(Date date);
}

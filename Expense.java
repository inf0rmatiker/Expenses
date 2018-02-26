
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Expense {
	/* 0 for Person 1, 1 for Person 2 */
	public int person;
	public double expenseAmount;
	public String description;
	public boolean paid;
	private String name1;
	private String name2;
	private String date;
	
	
	public Expense() {
		this(0, 0.0, "Person 1", "Person 2");
	}
	
	public Expense(int person, double amount, String name1, String name2){
		this.person = person;
		this.expenseAmount = amount;
		this.name1 = name1;
		this.name2 = name2;
	}
	
	public void addDescription(String description) {
		this.description = description;
	}
	
	public void addPaid(boolean paid) {
		this.paid = paid;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public String toString () {
		String person;
		String str = "";
		if (this.person == 0) {
			person = name1;
		}
		else person = name2;
		
		str += person + " | $" + this.expenseAmount + " | " + this.description + " | " + this.paid + " | " + this.date;
		
		return str;
	}

	public String getName2() {
		return name2;
	}

	public String getName1() {
		return name1;
	}
}

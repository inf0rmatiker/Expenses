
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Expense {
	/* 1 for Grace, 0 for Caleb */
	public int person;
	
	public double expenseAmount;
	
	public String description;
	
	public boolean paid;
	
	public Date date;
	
	public SimpleDateFormat dateFormat;
	
	
	public Expense(int person, double amount){
		this.person = person;
		this.expenseAmount = amount;
		this.dateFormat = new SimpleDateFormat("MM/dd/yy");
	}
	
	public void addDescription(String description) {
		this.description = description;
	}
	
	public void addPaid(boolean paid) {
		this.paid = paid;
	}
	
	
	public void parseDate (String date) throws ParseException {
			this.date = dateFormat.parse(date);
	}

	public String toString () {
		String person;
		String str = "";
		if (this.person == 0) {
			person = "Caleb";
		}
		else person = "Grace";
		
		str += person + " | $" + this.expenseAmount + " | " + this.description + " | " + this.paid + " | " + dateFormat.format(date);
		
		return str;
	}
}

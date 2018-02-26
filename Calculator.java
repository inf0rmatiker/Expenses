import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;

public class Calculator {

	private String name1;
	private String name2;
	protected List<Expense> expenses;
	public double difference;

	public Calculator() {
		expenses = new ArrayList<Expense>();
		difference = 0;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// 0 for Person 1, 1 for Person 2
		BasicUI basicUI = new BasicUI();

	}

	public void calculate() {
		
		// Calculate what is owed
		double person1Total = 0;
		double person2Total = 0;

		for (Expense expense : expenses) {
			if (expense.person == 0) {
				// add to person1Total
				if (!expense.paid) {
					person1Total += expense.expenseAmount;
				}
			} else {
				// add to person2Total
				if (!expense.paid) {
					person2Total += expense.expenseAmount;
				}
			}
		}
		difference = person1Total - person2Total;
			
	}

	public void readFile(File file) throws FileNotFoundException, ParseException {

		Scanner scan = new Scanner(file);
		
		// First line is descriptions
		String[] titleLine = scan.nextLine().split(",");

		this.name1 = (titleLine[0].length() > 0) ? titleLine[0] : "Person 1";
		this.name2 = (titleLine[1].length() > 0) ? titleLine[1] : "Person 2";

		while (scan.hasNextLine()) {
			double amount = 0.0;
			String line = scan.nextLine().trim();
			if (line != null) {
				// create expense with new line
				// System.out.println(line);
				String[] expenseLine = line.split(",");
				
				Expense expense;
				amount = (expenseLine[2].charAt(0) == '$') ? Double.parseDouble(expenseLine[2].substring(1)) : Double.parseDouble(expenseLine[2]);
				if (expenseLine[0].trim().isEmpty()) {
					// Person 1 Paid
					expense = new Expense(1, amount, name1, name2);
				} else {
					// Person 2 Paid
					expense = new Expense(0, amount, name1, name2);
				}

				expense.addPaid(!expenseLine[4].trim().isEmpty());
				expense.addDescription(expenseLine[3]);
				// if it has a date attached
				if (expenseLine.length > 4) {
					if (!expenseLine[5].trim().isEmpty())
						expense.setDate(expenseLine[5]);
				}
				
				expenses.add(expense);
			}

		}

		scan.close();
	}
	
	public String getName1() {
		return name1;
	}
	
	public String getName2() {
		return name2;
	}
	
	public String getDescription() {
		String str = "";
		for (Expense expense: expenses) {
			str += String.format("%s\n", expense.toString());
		}
		System.out.println(str);
		return str;
	}

}

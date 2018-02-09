import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.io.FileNotFoundException;


public class Calculator {

	public static void main(String[] args) throws FileNotFoundException, ParseException {
		// 0 for Caleb, 1 for Grace
		
		
		ArrayList<Expense> expenses = new ArrayList<Expense>();
		readFile(args[0], expenses);
		
		for (Expense expense : expenses) {
			System.out.println(expense.toString());
		}
		
		// Calculate what is owed
		double graceTotal = 0;
		double calebTotal = 0;
		double difference = 0;
		for (Expense expense : expenses) {
			
			if (expense.person == 0) {
				//add to Caleb's total
				if (!expense.paid) {
					calebTotal += expense.expenseAmount;
				}
			}
			else {
				//add to Grace's total
				if (!expense.paid) {
					graceTotal += expense.expenseAmount;
				}
			}
		}
		difference = calebTotal - graceTotal;
		if (difference < 0) {
			System.out.printf("Caleb owes Grace: $%.2f", Math.abs(difference)/2);
		}
		else System.out.printf("Grace owes Caleb: $%.2f", difference/2);
		

	}
	
	public static void readFile(String fileName, ArrayList<Expense> expenses) throws FileNotFoundException, ParseException{
		
		Scanner scan = new Scanner(new File(fileName));
		//First line is descriptions
		scan.nextLine();
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
			if (line != null) {
				//create expense with new line
				//System.out.println(line);
				String[] expenseLine = line.split(",");
				Expense expense;
				if (expenseLine[0].trim().isEmpty()) {
					//Grace Paid
					expense = new Expense(1, Double.parseDouble(expenseLine[2]));
				}
				else {
					//Caleb Paid
					expense = new Expense(0, Double.parseDouble(expenseLine[2]));
				}
				
				expense.addPaid(!expenseLine[4].trim().isEmpty());
				expense.addDescription(expenseLine[3]);
				//if it has a date attached
				if (!expenseLine[5].trim().isEmpty()) expense.parseDate(expenseLine[5]);
				expenses.add(expense);
			}
			
		}
		
		scan.close();
	}

}

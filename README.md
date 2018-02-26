# Expenses

This program allows you to keep track of expenses paid and calculate amounts owed between 2 sharing mutual purchases (e.g. Roommates purchasing toilet paper, splitting the cost of a meal, or paying utilities). Useful if you and another person consistently are sharing purchases or expenses, and want to keep track of what you owe each other.

Instructions for use:

1. Download ExpenseCalculator.jar, make sure you have an updated version of Java installed.
      - Go to https://java.com/en/download/ to install the latest version of Java.
2. Set up a new Google Sheet as follows:
   - First row should be formatted as: Name 1 | Name 2 | Amount | Description | Paid | Date
   - Each following row is an expense item
   - Each expense item should be formatted as: 
        - "x" in tile for who paid
        - decimal amount paid
        - optional description
        - "x" in paid tile if mutual expense has been settled
        - date in format MM/DD/YYYY
3. Download google sheet as .csv file by going to File > Download As > Comma-Separated Values (.csv)
4. Double click ExpenseCalculator.jar (run with Java SE)
      - Open With > Browse Files > java.exe
5. Click "Choose File" and choose the .csv google sheet you downloaded.
6. Done! Results are displayed on the interface window! You may choose a different file or exit the program.

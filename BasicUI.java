import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class BasicUI extends JFrame implements ActionListener {

	public static final long serialVersionUID = 9001;
	
	private JFrame basicFrame;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JPanel topPanel;
	private JLabel filePath;
	private JButton fileSelect;
	private JButton exitButton;
	private JButton helpButton;
	private boolean helpToggle;
	
	public BasicUI() {
		helpToggle = false;
		this.setupButtons();
		this.setupLabels();
		this.setupPanels();
		this.setupFrame();

	}
	
	public void setupFrame () {
		
		basicFrame = new JFrame();
		basicFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		basicFrame.setTitle("Expense Calculator");
		basicFrame.setSize(550, 650);
		basicFrame.setLocationRelativeTo(null);	
		
		basicFrame.add(topPanel, BorderLayout.NORTH);
		basicFrame.add(middlePanel, BorderLayout.CENTER);
		basicFrame.add(bottomPanel, BorderLayout.SOUTH);
		
		// Last thing to do, display the JFrame
		basicFrame.setVisible(true);
	}
	
	public void setupLabels() {
		
		filePath = new JLabel("File Name: No File Selected!");
		filePath.setFont(new Font("Arial", Font.ITALIC, 20));
		
		
	}
	
	public void setupButtons() {
		
		fileSelect = new JButton("Choose File");
		exitButton = new JButton("Exit");
		helpButton = new JButton("Help");
		
		helpButton.setToolTipText("Usage Information");
		fileSelect.setToolTipText("Browse for a File");
		exitButton.setToolTipText("Exit Program");
		
		
		exitButton.addActionListener(this);
		fileSelect.addActionListener(this);
		helpButton.addActionListener(this);
		
	}

	
	public void setupPanels() {
		
		middlePanel = new JPanel();
		topPanel = new JPanel();
		bottomPanel = new JPanel();
		
		bottomPanel.add(fileSelect);
		bottomPanel.add(exitButton);
		bottomPanel.add(helpButton);
        middlePanel.setBackground(new Color(100,180,100));
        middlePanel.setLayout(new BorderLayout());
        topPanel.add(filePath);
	}
	
	
	private void exit() {
		middlePanel.removeAll();
		this.setVisible(false);
		this.dispose();
		System.exit(0);
	}
	private void help() {
		if (!helpToggle) {
			middlePanel.removeAll();
			String[] helpList = {"Usage Instructions:",
					"Download and import Google Sheets containing expenses as .csv file",
					"Format of Google Sheets:",
					"    - Row 1: Name 1, Name 2, Expense Amount, Description, Paid, Date",
					"    - All other rows: Mark who paid, and if the expense has been settled",
					"    - Date format must be MM/DD/YYYY",
					"To import Google Sheets:",
					"    - Google Sheets > File > Download As > .csv file",
			"    - Choose File > filename.csv"};

			JList<String> usageList = new JList<String>(helpList);
			//		usageList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
			usageList.setLayoutOrientation(JList.VERTICAL);
			//		usageList.setVisibleRowCount(20);
			usageList.setFixedCellHeight(30);
			//		usageList.setFixedCellWidth(450);
			usageList.setBackground(new Color(150, 200, 150));
			usageList.setFont(new Font("Arial", Font.BOLD, 15));
			middlePanel.add(usageList, BorderLayout.CENTER);
			middlePanel.updateUI();
			helpToggle = true;
		} else {
			middlePanel.removeAll();
			middlePanel.updateUI();
			helpToggle = false;
			
		}
	}
	
	private void browseFiles() throws ParseException {
		
		File file = new File(".");
		
		// File browser dialog object
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("File Browser");
		
		int fcVal = fc.showOpenDialog(this);
		
		if (fcVal == JFileChooser.APPROVE_OPTION) {
			file = fc.getSelectedFile();
			String filename = file.getName();
			System.out.println("Opening: " + file.getAbsolutePath());
			if (filename.substring(filename.length()-4, filename.length()).equals(".csv")) {
				//DO SOMETHING
				filePath.setText("File Name: " + filename);
				Calculator calculator = new Calculator();
				try {
					calculator.readFile(file);
					calculator.calculate();
					
				} catch (IOException e) {
					System.err.println("Unable to open file!");
					System.exit(1);
				}

				printResults(calculator);
				
			}
			else {
				System.out.println("Only .csv files allowed!");
			}
			
		}
		else {
			System.out.println("User cancelled operation.");
		}
		
	}
	
	
	public void printResults(Calculator calc) {
		
		middlePanel.removeAll();
		
		String[] list = new String[calc.expenses.size()];
		for (int i = 0; i < list.length; i++) list[i] = calc.expenses.get(i).toString();
		
		JList<String> report = new JList<String>(list);
		String resultStr = (calc.difference < 0) ? String.format("%s owes %s $%.2f", calc.getName1(), calc.getName2(), Math.abs(calc.difference)/2) : String.format("%s owes %s $%.2f", calc.getName2(), calc.getName1(), Math.abs(calc.difference)/2);
		JLabel result = new JLabel(resultStr);
		result.setFont(new Font("Arial", Font.BOLD, 20));
		result.setSize(450, 30);
		report.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		report.setLayoutOrientation(JList.VERTICAL);
		report.setVisibleRowCount(20);
		report.setFixedCellHeight(30);
		report.setFixedCellWidth(450);
		report.setBackground(new Color(150, 200, 150));
		report.setFont(new Font("Arial", Font.ITALIC, 18));
		
		JScrollPane listScroller = new JScrollPane(report);
		listScroller.setPreferredSize(new Dimension(250, 80));
		middlePanel.add(listScroller, BorderLayout.CENTER);
		middlePanel.add(result, BorderLayout.SOUTH);
		middlePanel.updateUI();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		// TODO Auto-generated method stub
		if (action.getSource() == exitButton) {
			exit();
		}
		else if (action.getSource() == fileSelect) {
			try {
				browseFiles();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			help();
		}
	}	

}

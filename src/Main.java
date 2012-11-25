import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;

public class Main {
	
	private static Borrower borrower;
	private static Book book;
	
	private JFrame frame;
	private JTabbedPane tabbedPane;
	private JComponent clerkTab;
	private JComponent borrowerTab;
	private JComponent librarianTab;
	
	public Main() {
		frame = new JFrame("Library Database");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabbedPane = new JTabbedPane();
		clerkTab = makeClerkTab();
		borrowerTab = makeBorrowerTab();
		librarianTab = makeLibrarianTab();
		tabbedPane.add("Clerk", clerkTab);
		tabbedPane.add("Borrower", borrowerTab);
		tabbedPane.add("Librarian", librarianTab);
	}
	
	public void showProgram() {
		frame.setSize(800,400);
		frame.add(tabbedPane, BorderLayout.CENTER);
		frame.setVisible(true);
	}
	
	private JComponent makeClerkTab() {
		JButton button1 = new JButton("Add New Borrower");
		JButton button2 = new JButton("Check out Items");
		JButton button3 = new JButton("Process Return");
		JButton button4 = new JButton("Check Overdue Items");
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1,1,1,1));
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField bid = new JTextField(5);
				JTextField bName = new JTextField(5);
				JTextField bPassword = new JTextField(5);
				JTextField bType = new JTextField(5);
				JTextField bAddress = new JTextField(5);
				JTextField bPhone = new JTextField(5);
				JTextField bEmail = new JTextField(5);
				JTextField bSinOrStN = new JTextField(5);
				JTextField bExpiryDate = new JTextField(5);
				
				JPanel panel = new JPanel(false);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(new JLabel("Borrower ID:"));
				panel.add(bid);
				panel.add(new JLabel("Borrower Name:"));
				panel.add(bName);
				panel.add(new JLabel("Borrower Password:"));
				panel.add(bPassword);
				panel.add(new JLabel("Borrower Type:"));
				panel.add(bType);
				panel.add(new JLabel("Borrower Address:"));
				panel.add(bAddress);
				panel.add(new JLabel("Borrower Phone:"));
				panel.add(bPhone);
				panel.add(new JLabel("Borrower Email:"));
				panel.add(bEmail);
				panel.add(new JLabel("Borrower Sin/Student Number:"));
				panel.add(bSinOrStN);
				panel.add(new JLabel("Borrower Account Expiry Date"));
				panel.add(bExpiryDate);
				
				int result = JOptionPane.showConfirmDialog(null, panel, "Add Borrower", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					if (bid.getText().equals("") || bPassword.getText().equals("") || bName.getText().equals("") || bType.getText().equals("") || bAddress.getText().equals("") || bPhone.getText().equals("") || bEmail.getText().equals("") || bSinOrStN.getText().equals("") || bExpiryDate.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Fill in all Parameters!", "Error", JOptionPane.PLAIN_MESSAGE);
					}
					else {
					String message = "";
					String expiryDate = bExpiryDate.getText();
					Date dExpireDate = null;
						try {
							dExpireDate = new SimpleDateFormat("MMMM d yyyy", Locale.ENGLISH).parse(expiryDate);
						} catch (ParseException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}			
						java.sql.Date sqlDate = new java.sql.Date(dExpireDate.getTime());
						try {
						message = borrower.insertBorrower(Integer.parseInt(bid.getText()), bPassword.getText(), bName.getText(), bAddress.getText(), Integer.parseInt(bPhone.getText()), bEmail.getText(), Integer.parseInt(bSinOrStN.getText()), sqlDate, bType.getText());
					} catch (SQLException e1) {
						message = "SQL Exception Caught";
						e1.printStackTrace();
					} catch (IOException e1) {
						message = "IO Exception Caught";
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, message, "Insert Status", JOptionPane.PLAIN_MESSAGE);
					}
				}
			}
			
		});
		
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				 
			}
			
		});
		return panel;
	}
	
	private JComponent makeBorrowerTab() {
		JButton button1 = new JButton("Search for Book");
		JButton button2 = new JButton("Check Account");
		JButton button3 = new JButton("Place Hold Request");
		JButton button4 = new JButton("Pay a Fine");
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1,1,1,1));
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		return panel;
	}
	
	private JComponent makeLibrarianTab() {
		JButton button1 = new JButton("Add a New Book");
		JButton button2 = new JButton("Add New Book Copy");
		JButton button3 = new JButton("Generate Report for Checked Out Items");
		JButton button4 = new JButton("Generate Report for Most Popular Items");
		JPanel panel = new JPanel(false);
		panel.setLayout(new GridLayout(1,1,1,1));
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField bIsbn = new JTextField(5);
				JTextField bCall = new JTextField(5);
				JTextField bTitle = new JTextField(5);
				JTextField bMauthor = new JTextField(5);
				JTextField bPublisher = new JTextField(5);
				JTextField bYear = new JTextField(5);
				
				JPanel panel = new JPanel(false);
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.add(new JLabel("Call Number:"));
				panel.add(bCall);
				panel.add(new JLabel("ISBN:"));
				panel.add(bIsbn);
				panel.add(new JLabel("Title:"));
				panel.add(bTitle);
				panel.add(new JLabel("Main Author:"));
				panel.add(bMauthor);
				panel.add(new JLabel("Publisher"));
				panel.add(bPublisher);
				panel.add(new JLabel("Year"));
				panel.add(bYear);
				
				int result = JOptionPane.showConfirmDialog(null, panel, "Add Book", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE);
				if (result == JOptionPane.OK_OPTION) {
					String message = "";
					try {
						System.out.println(Integer.parseInt(bIsbn.getText()));
						System.out.println(bCall.getText());
						System.out.println(bTitle.getText());
						System.out.println(bMauthor.getText());
						System.out.println(bPublisher.getText());
						System.out.println(Integer.parseInt(bYear.getText()));
						message = book.insertBook(Integer.parseInt(bIsbn.getText()), bCall.getText(), bTitle.getText(), bMauthor.getText(), bPublisher.getText(), Integer.parseInt(bYear.getText()));	
					} catch (SQLException e) {
						message = "SQL Exception";
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						message = "IO Exception";
					}
					JOptionPane.showMessageDialog(null, message, "Insert Status", JOptionPane.PLAIN_MESSAGE);
				}

			}
			
		});
		return panel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBConnect db = new DBConnect();
		book = new Book(db);
		borrower = new Borrower(db);
		Main applicationGUI = new Main();
		applicationGUI.showProgram();

	}

}

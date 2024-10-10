package main;

import user.UserRepository;

import ui.Ui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.Box;
import java.awt.Color;

public class Main {
	public static void main(String[] args) {
		// User examples
		UserRepository.createUser(1, "John", "Doe", "ID", "12345", "john@example.com", "555-1234", "password123", true);
    	UserRepository.createUser(2, "Jane", "Smith", "ID", "54321", "jane@example.com", "555-5678", "mypassword", true);
    	UserRepository.createUser(3, "Alice", "Johnson", "Passport", "98765", "alice@example.com", "555-8765", "securepass", true);
    	UserRepository.createUser(4, "Bob", "Brown", "ID", "67890", "bob@example.com", "555-4321", "letmein", true);
    	JFrame loginWindow = Ui.createWindow("Inventory Control App", 300, 400);
		JFrame mainWindow = Ui.createWindow("Inventory Control App", 600, 800);

		JPanel welcomeMessagePanel = Ui.createPanel();
		JPanel loginPanel = Ui.createPanel();

		// Login window
		loginPanel.add(Box.createVerticalGlue());
		JLabel loginLabel = Ui.createLabel("Inventory Control App", loginPanel, "Arial", 48, true, 90);

		JLabel emailTextFieldLabel = Ui.createLabel("Email Address", loginPanel, "Arial", 18, false, 10);
		JTextField emailTextField = Ui.createTextField(loginPanel, 60, 400, 30, 25);

		JLabel passwordTextFieldLabel = Ui.createLabel("Password", loginPanel, "Arial", 18, false, 10);
		JTextField passwordTextField = Ui.createPasswordField(loginPanel, 60, 400, 100, 25);

		JLabel errorMessageLabel = Ui.createLabel("", loginPanel, "Arial", 16, true, 30);
		Color red = new Color(235, 87, 87);
		errorMessageLabel.setForeground(red);

		JButton loginButton = Ui.createButton(loginPanel, "Login");
		loginPanel.add(Box.createVerticalGlue());

		// Main Interface window
		welcomeMessagePanel.add(Box.createVerticalGlue());

		JLabel welcomeMessageLabel = Ui.createLabel("", welcomeMessagePanel, "Arial", 48, true, 60);
		JButton logoutButton = Ui.createButton(welcomeMessagePanel, "Logout");

		welcomeMessagePanel.add(Box.createVerticalGlue());

		// Action listeners
		Ui.addLoginActionListener(loginButton, loginPanel, emailTextField, passwordTextField, errorMessageLabel, welcomeMessageLabel, loginWindow, mainWindow);
		Ui.addLogoutActionListener(logoutButton, loginWindow, mainWindow);

		mainWindow.add(welcomeMessagePanel);

		loginWindow.add(loginPanel);	
		loginWindow.setVisible(true);
	}
}

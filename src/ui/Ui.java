package ui;

import user.UserService;
import user.UserRepository;
import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ui {
	public static JFrame createWindow(String windowTitle, int width, int height) {
		JFrame window = new JFrame(windowTitle);
		window.setSize(width, height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setUndecorated(true);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		return window;
	}

	public static JPanel createPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}
	
	public static JLabel createLabel(String text, JPanel panel, String font, int fontSize, boolean bold, int verticalStrut) {
		JLabel label = new JLabel(text, JLabel.CENTER);

		if (bold) {
			label.setFont(new Font(font, Font.BOLD, fontSize));
		} else {
			label.setFont(new Font(font, Font.PLAIN, fontSize));
		}

		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(label);
		panel.add(Box.createVerticalStrut(verticalStrut));

		return label;
	}

	public static JPasswordField createPasswordField(JPanel panel, int width, int maximumWidth, int maximumHeight, int verticalStrut)  {
		JPasswordField passwordField = new JPasswordField(width);
		passwordField.setMaximumSize(new Dimension(maximumWidth, maximumHeight));
		passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
		passwordField.setBorder(new EmptyBorder(10, 10, 10, 10));
		passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(passwordField);
		panel.add(Box.createVerticalStrut(verticalStrut));
		return passwordField;
	}

	public static JTextField createTextField(JPanel panel, int width, int maximumWidth, int maximumHeight, int verticalStrut)  {
		JTextField textField = new JTextField(width);
		textField.setMaximumSize(new Dimension(maximumWidth, maximumHeight));
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		textField.setBorder(new EmptyBorder(10, 10, 10, 10));
		textField.setFont(new Font("Arial", Font.PLAIN, 18));
		panel.add(textField);
		panel.add(Box.createVerticalStrut(verticalStrut));
		return textField;
	}

	public static JButton createButton(JPanel panel, String text) {
		JButton button = new JButton(text);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setContentAreaFilled(true); 
    	button.setBorderPainted(false); 
    	button.setFocusPainted(false); 
		Color violet = new Color(175, 128, 232);
		button.setBackground(violet); 
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 24));
		panel.add(button);
		return button;
	}

	public static void addLoginActionListener(JButton button, JPanel panel, JTextField emailTextField, JTextField passwordTextField, JLabel errorMessageLabel, JLabel welcomeMessageLabel, JFrame loginWindow, JFrame mainWindow) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String emailAddress = emailTextField.getText();
				String password = passwordTextField.getText();
                
				boolean isValid = UserService.validateUser(emailAddress, password);
                
				if (isValid) {
					loginWindow.dispose();
					String name = UserRepository.searchUserByEmail(emailAddress).getName();
					String lastName = UserRepository.searchUserByEmail(emailAddress).getLastName();
					welcomeMessageLabel.setText("¡Welcome, " + name + " " + lastName + "!");
					emailTextField.setText("");
          			passwordTextField.setText("");
					errorMessageLabel.setText("");
					mainWindow.setVisible(true);
				} else {
						errorMessageLabel.setText("Invalid email or password.");
				}
			}
    	});
	}

	public static void addLogoutActionListener(JButton button, JFrame loginWindow, JFrame mainWindow) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainWindow.dispose();
				loginWindow.setVisible(true);
			}
    	});
	}
}

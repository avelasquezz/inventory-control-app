package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.UserService;

public class LoginView extends JFrame {
    private JLabel titleLabel;
    private JLabel emailAddressTextFieldLabel;
    private JTextField emailAddressTextField;
    private JLabel passwordTextFieldLabel;
    private JPasswordField passwordTextField;
    private JButton loginButton;
    private JLabel errorMessageLabel;
    private UserService userService;

    public LoginView(UserService userService) {
        this.userService = userService;

        // Window config
        setTitle("Inventory Control App | Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        this.titleLabel = new JLabel("Inventory Control App");
        this.titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        this.titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.emailAddressTextFieldLabel = new JLabel("Email Address");
        this.emailAddressTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.emailAddressTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.emailAddressTextField = new JTextField(60);
        this.emailAddressTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.emailAddressTextField.setMaximumSize(new Dimension(400, 40));
        this.emailAddressTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.passwordTextFieldLabel = new JLabel("Password");
        this.passwordTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.passwordTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.passwordTextField = new JPasswordField(60);
        this.passwordTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.passwordTextField.setMaximumSize(new Dimension(400, 40));
        this.passwordTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.loginButton = new JButton("Login");
        this.loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.loginButton.setContentAreaFilled(true); 
    	this.loginButton.setBorderPainted(false); 
    	this.loginButton.setFocusPainted(false); 
        this.loginButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.loginButton.setForeground(Color.WHITE);
        this.loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add component to panel
        loginPanel.add(Box.createVerticalGlue());
        loginPanel.add(this.titleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        loginPanel.add(this.emailAddressTextFieldLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(this.emailAddressTextField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(this.passwordTextFieldLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(this.passwordTextField);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        loginPanel.add(this.errorMessageLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        loginPanel.add(this.loginButton);
        loginPanel.add(Box.createVerticalGlue());

        add(loginPanel);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAddress = emailAddressTextField.getText();
                String password = new String(passwordTextField.getPassword());

                if (LoginView.this.userService.validateUser(emailAddress, password)) {
                    errorMessageLabel.setText("¡Succesfull login!");
                } else {
                    errorMessageLabel.setText("Incorrect password or email :(");
                }
            }
        });
    }

    public void showLoginWindow() {
        setVisible(true);
    }
}

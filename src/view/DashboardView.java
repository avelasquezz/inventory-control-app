package view;

import javax.swing.*;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import repository.UserRepository;
import service.UserService;

public class DashboardView extends JFrame {
    private JLabel welcomeMessageLabel;
    private JButton manageUsersButton;
    private JButton manageProductsButton;
    private JButton manageSuppliersButton;
    private JButton logOutButton;
    private UserService userService;
    private UserRepository userRepository;

    public DashboardView(String welcomeMessage, UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        
        // Window config
        setTitle("Inventory Control App | Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        this.welcomeMessageLabel = new JLabel(welcomeMessage);
        this.welcomeMessageLabel.setFont(new Font("Arial", Font.BOLD, 48));
        this.welcomeMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.manageUsersButton = new JButton("Manage Users");
        this.manageUsersButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.manageUsersButton.setContentAreaFilled(true); 
    	this.manageUsersButton.setBorderPainted(false); 
    	this.manageUsersButton.setFocusPainted(false); 
        this.manageUsersButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.manageUsersButton.setForeground(Color.WHITE);
        this.manageUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.manageProductsButton = new JButton("Manage Products");
        this.manageProductsButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.manageProductsButton.setContentAreaFilled(true); 
    	this.manageProductsButton.setBorderPainted(false); 
    	this.manageProductsButton.setFocusPainted(false); 
        this.manageProductsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.manageProductsButton.setForeground(Color.WHITE);
        this.manageProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.manageSuppliersButton = new JButton("Manage Suppliers");
        this.manageSuppliersButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.manageSuppliersButton.setContentAreaFilled(true); 
    	this.manageSuppliersButton.setBorderPainted(false); 
    	this.manageSuppliersButton.setFocusPainted(false); 
        this.manageSuppliersButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.manageSuppliersButton.setForeground(Color.WHITE);
        this.manageSuppliersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.logOutButton = new JButton("Log Out");
        this.logOutButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.logOutButton.setContentAreaFilled(true); 
    	this.logOutButton.setBorderPainted(false); 
    	this.logOutButton.setFocusPainted(false); 
        this.logOutButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.logOutButton.setForeground(Color.WHITE);
        this.logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        homePanel.add(Box.createVerticalGlue());

        homePanel.add(this.welcomeMessageLabel);
        homePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        homePanel.add(this.manageUsersButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        homePanel.add(this.manageProductsButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        homePanel.add(this.manageSuppliersButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        homePanel.add(this.logOutButton);

        homePanel.add(Box.createVerticalGlue());

        add(homePanel);

        // Button actions
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView(DashboardView.this.userService, DashboardView.this.userRepository);
                loginView.showWindow();
            }
        });

        manageProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManageProductsView manageProductsView = new ManageProductsView(DashboardView.this.userService, DashboardView.this.userRepository);
                manageProductsView.showWindow();
            }
        });
    }

    public void showWindow() {
        setVisible(true);
    }
}
package view;

import service.*;
import model.User;
import view.manageUsersDialogs.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageUsersView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    
    private JLabel usersTableTitle;
    private DefaultTableModel usersTableModel;
    private JTable usersTable;
    private JButton backButton;
    private JButton addUserButton;
    private JButton modifyUserButton;
    private JButton removeUserButton;

    public ManageUsersView(String userAccesLevel, String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;

        // Window config
        setTitle("MasterStock | Usuarios");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.usersTableTitle = new JLabel("Lista de usuarios");
        this.usersTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.usersTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Tipo de ID", "Número de ID", "Nombre", "Apellido", "Teléfono", "Correo electrónico", "Contraseña", "Estado", "Nivel de acceso"};
        this.usersTableModel = new DefaultTableModel(tableColumns, 0);
        this.usersTable = new JTable(usersTableModel);

        JTableHeader usersTableHeader = this.usersTable.getTableHeader();
        usersTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.usersTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        tablePanel.add(new JScrollPane(this.usersTable));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Create subpanel to organize buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        this.backButton = new JButton("Volver");
        this.backButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.backButton.setContentAreaFilled(true); 
    	this.backButton.setBorderPainted(false); 
    	this.backButton.setFocusPainted(false); 
        this.backButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.backButton.setForeground(Color.WHITE);
        this.backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.addUserButton = new JButton("Agregar");
        this.addUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.addUserButton.setContentAreaFilled(true); 
    	this.addUserButton.setBorderPainted(false); 
    	this.addUserButton.setFocusPainted(false); 
        this.addUserButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.addUserButton.setForeground(Color.WHITE);
        this.addUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifyUserButton = new JButton("Modificar");
        this.modifyUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.modifyUserButton.setContentAreaFilled(true); 
    	this.modifyUserButton.setBorderPainted(false); 
    	this.modifyUserButton.setFocusPainted(false); 
        this.modifyUserButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifyUserButton.setForeground(Color.WHITE);
        this.modifyUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.removeUserButton = new JButton("Remover");
        this.removeUserButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.removeUserButton.setContentAreaFilled(true); 
    	this.removeUserButton.setBorderPainted(false); 
    	this.removeUserButton.setFocusPainted(false); 
        this.removeUserButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.removeUserButton.setForeground(Color.WHITE);
        this.removeUserButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.addUserButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifyUserButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.removeUserButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        ManageUsersView.this.userService.updateTable((DefaultTableModel) usersTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage, ManageUsersView.this.userService, ManageUsersView.this.productService, ManageUsersView.this.supplierService, ManageUsersView.this.movementService, ManageUsersView.this.inventoryService);
                dashboardView.showWindow();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserDialog addUserDialog = new AddUserDialog(usersTable, ManageUsersView.this.userService);
                addUserDialog.showDialog();
            }
        });

        modifyUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageUsersView.this.usersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifyUserDialog modifyUserDialog = new ModifyUserDialog(ManageUsersView.this.usersTable, ManageUsersView.this.userService);
                    modifyUserDialog.showDialog();
                }
            }
        });

        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageUsersView.this.usersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    User userToRemove = ManageUsersView.this.userService.getUserRepository().searchUserByEmailAddress(usersTableModel.getValueAt(selectedRow, 5).toString());
                    ManageUsersView.this.userService.getUserRepository().removeUser(userToRemove);
                    ManageUsersView.this.usersTableModel.removeRow(selectedRow);
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

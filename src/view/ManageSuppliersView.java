package view;

import repository.*;
import service.*;

import model.Supplier;
import view.manageSuppliersDialogs.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageSuppliersView extends JFrame {
    private UserRepository userRepository;
    private UserService userService;
    private ProductRepository productRepository;
    private ProductService productService;
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;
    private MovementRepository movementRepository;
    private MovementService movementService;
    
    private JLabel suppliersTableTitle;
    private DefaultTableModel suppliersTableModel;
    private JTable suppliersTable;
    private JButton backButton;
    private JButton addSupplierButton;
    private JButton modifySupplierButton;
    private JButton removeSupplierButton;

    public ManageSuppliersView(String welcomeMessage, UserRepository userRepository, UserService userService, ProductRepository productRepository, ProductService productService, SupplierRepository supplierRepository, SupplierService supplierService, MovementRepository movementRepository, MovementService movementService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
        this.movementRepository = movementRepository;
        this.movementService = movementService;

        // Window config
        setTitle("Inventory Control App | Manage Suppliers");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.suppliersTableTitle = new JLabel("Suppliers List");
        this.suppliersTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.suppliersTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Id", "Name", "Address", "Phone Number"};
        this.suppliersTableModel = new DefaultTableModel(tableColumns, 0);
        this.suppliersTable = new JTable(suppliersTableModel);

        JTableHeader suppliersTableHeader = this.suppliersTable.getTableHeader();
        suppliersTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.suppliersTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.suppliersTable));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Create subpanel to organize buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        this.backButton = new JButton("←");
        this.backButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.backButton.setContentAreaFilled(true); 
    	this.backButton.setBorderPainted(false); 
    	this.backButton.setFocusPainted(false); 
        this.backButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.backButton.setForeground(Color.WHITE);
        this.backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.addSupplierButton = new JButton("Add Supplier");
        this.addSupplierButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.addSupplierButton.setContentAreaFilled(true); 
    	this.addSupplierButton.setBorderPainted(false); 
    	this.addSupplierButton.setFocusPainted(false); 
        this.addSupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.addSupplierButton.setForeground(Color.WHITE);
        this.addSupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifySupplierButton = new JButton("Modify Supplier");
        this.modifySupplierButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.modifySupplierButton.setContentAreaFilled(true); 
    	this.modifySupplierButton.setBorderPainted(false); 
    	this.modifySupplierButton.setFocusPainted(false); 
        this.modifySupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifySupplierButton.setForeground(Color.WHITE);
        this.modifySupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.removeSupplierButton = new JButton("Remove Supplier");
        this.removeSupplierButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.removeSupplierButton.setContentAreaFilled(true); 
    	this.removeSupplierButton.setBorderPainted(false); 
    	this.removeSupplierButton.setFocusPainted(false); 
        this.removeSupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.removeSupplierButton.setForeground(Color.WHITE);
        this.removeSupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.addSupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifySupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.removeSupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        ManageSuppliersView.this.supplierService.updateTable((DefaultTableModel) suppliersTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(welcomeMessage, ManageSuppliersView.this.userRepository, ManageSuppliersView.this.userService, ManageSuppliersView.this.productRepository, ManageSuppliersView.this.productService, ManageSuppliersView.this.supplierRepository, ManageSuppliersView.this.supplierService, ManageSuppliersView.this.movementRepository, ManageSuppliersView.this.movementService);
                dashboardView.showWindow();
            }
        });

        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSupplierDialog addSupplierDialog = new AddSupplierDialog(ManageSuppliersView.this.suppliersTable, ManageSuppliersView.this.supplierRepository, ManageSuppliersView.this.supplierService);
                addSupplierDialog.showDialog();
            }
        });

        modifySupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageSuppliersView.this.suppliersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifySupplierDialog modifySupplierDialog = new ModifySupplierDialog(ManageSuppliersView.this.suppliersTable, ManageSuppliersView.this.supplierRepository, ManageSuppliersView.this.supplierService);
                    modifySupplierDialog.showDialog();
                }
            }
        });

        removeSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageSuppliersView.this.suppliersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Supplier produtToRemove = ManageSuppliersView.this.supplierRepository.searchSupplierById(Integer.parseInt(suppliersTableModel.getValueAt(selectedRow, 0).toString()));
                    ManageSuppliersView.this.supplierRepository.removeSupplier(produtToRemove);
                    ManageSuppliersView.this.suppliersTableModel.removeRow(selectedRow);
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

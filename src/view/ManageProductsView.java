package view;

import repository.*;
import service.*;

import model.Product;
import view.manageProductsDialogs.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageProductsView extends JFrame {
    private UserRepository userRepository;
    private UserService userService;
    private ProductRepository productRepository;
    private ProductService productService;
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;
    private MovementRepository movementRepository;
    private MovementService movementService;
    
    private JLabel productsTableTitle;
    private DefaultTableModel productsTableModel;
    private JTable productsTable;
    private JButton backButton;
    private JButton addProductButton;
    private JButton modifyProductButton;
    private JButton removeProductButton;
    private JButton modifyStockButton;
    private JButton viewMovementsButton;

    public ManageProductsView(String welcomeMessage, UserRepository userRepository, UserService userService, ProductRepository productRepository, ProductService productService, SupplierRepository supplierRepository, SupplierService supplierService, MovementRepository movementRepository, MovementService movementService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.productService = productService;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
        this.movementRepository = movementRepository;
        this.movementService = movementService;

        // Window config
        setTitle("Inventory Control App | Manage Products");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.productsTableTitle = new JLabel("Products List");
        this.productsTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.productsTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Id", "Name", "Category", "Quantity", "Unit Price", "Supplier"};
        this.productsTableModel = new DefaultTableModel(tableColumns, 0);
        this.productsTable = new JTable(productsTableModel);

        JTableHeader productsTableHeader = this.productsTable.getTableHeader();
        productsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.productsTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.productsTable));
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
        
        this.addProductButton = new JButton("Add Product");
        this.addProductButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.addProductButton.setContentAreaFilled(true); 
    	this.addProductButton.setBorderPainted(false); 
    	this.addProductButton.setFocusPainted(false); 
        this.addProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.addProductButton.setForeground(Color.WHITE);
        this.addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifyProductButton = new JButton("Modify Product");
        this.modifyProductButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.modifyProductButton.setContentAreaFilled(true); 
    	this.modifyProductButton.setBorderPainted(false); 
    	this.modifyProductButton.setFocusPainted(false); 
        this.modifyProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifyProductButton.setForeground(Color.WHITE);
        this.modifyProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.removeProductButton = new JButton("Remove Product");
        this.removeProductButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.removeProductButton.setContentAreaFilled(true); 
    	this.removeProductButton.setBorderPainted(false); 
    	this.removeProductButton.setFocusPainted(false); 
        this.removeProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.removeProductButton.setForeground(Color.WHITE);
        this.removeProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifyStockButton = new JButton("Modify Stock");
        this.modifyStockButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.modifyStockButton.setContentAreaFilled(true); 
    	this.modifyStockButton.setBorderPainted(false); 
    	this.modifyStockButton.setFocusPainted(false); 
        this.modifyStockButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifyStockButton.setForeground(Color.WHITE);
        this.modifyStockButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewMovementsButton = new JButton("View Movements");
        this.viewMovementsButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.viewMovementsButton.setContentAreaFilled(true); 
    	this.viewMovementsButton.setBorderPainted(false); 
    	this.viewMovementsButton.setFocusPainted(false); 
        this.viewMovementsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.viewMovementsButton.setForeground(Color.WHITE);
        this.viewMovementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.addProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifyProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.removeProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifyStockButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.viewMovementsButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        ManageProductsView.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(welcomeMessage, ManageProductsView.this.userRepository, ManageProductsView.this.userService, ManageProductsView.this.productRepository, ManageProductsView.this.productService, ManageProductsView.this.supplierRepository, ManageProductsView.this.supplierService, ManageProductsView.this.movementRepository, ManageProductsView.this.movementService);
                dashboardView.showWindow();
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductDialog addProductDialog = new AddProductDialog(ManageProductsView.this.productsTable, ManageProductsView.this.productRepository, ManageProductsView.this.productService, ManageProductsView.this.supplierRepository, ManageProductsView.this.supplierService);
                addProductDialog.showDialog();
            }
        });

        modifyProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifyProductDialog modifyProductDialog = new ModifyProductDialog(ManageProductsView.this.productsTable, ManageProductsView.this.productRepository, ManageProductsView.this.productService, ManageProductsView.this.supplierRepository, ManageProductsView.this.supplierService);
                    modifyProductDialog.showDialog();
                }
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Product produtToRemove = ManageProductsView.this.productRepository.searchProductById(Integer.parseInt(productsTableModel.getValueAt(selectedRow, 0).toString()));
                    ManageProductsView.this.productRepository.removeProduct(produtToRemove);
                    ManageProductsView.this.productsTableModel.removeRow(selectedRow);
                }
            }
        });

        modifyStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifyStockDialog modifyStockDialog = new ModifyStockDialog(ManageProductsView.this.productsTable, ManageProductsView.this.productRepository, ManageProductsView.this.productService, ManageProductsView.this.movementRepository, ManageProductsView.this.movementService);
                    modifyStockDialog.showDialog();
                }
            }
        });

        viewMovementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                MovementsView movementsView = new MovementsView(welcomeMessage, userRepository, userService, productRepository, productService, supplierRepository, supplierService, movementRepository, movementService);
                movementsView.showWindow();
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

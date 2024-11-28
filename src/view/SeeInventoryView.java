package view;

import service.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeeInventoryView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    
    private JLabel inventoryTableTitle;
    private DefaultTableModel inventoryTableModel;
    private JTable inventoryTable;
    private JButton backButton;
    private JButton viewMovementsButton;

    public SeeInventoryView(String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;

        // Window config
        setTitle("MasterStock | Inventario");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.inventoryTableTitle = new JLabel("Inventario");
        this.inventoryTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.inventoryTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Producto", "Balance", "Precio unitario", "Precio total"};
        this.inventoryTableModel = new DefaultTableModel(tableColumns, 0);
        this.inventoryTable = new JTable(inventoryTableModel);

        JTableHeader inventoryTableHeader = this.inventoryTable.getTableHeader();
        inventoryTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.inventoryTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        tablePanel.add(new JScrollPane(this.inventoryTable));
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
        
        this.viewMovementsButton = new JButton("Ver movimientos");
        this.viewMovementsButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.viewMovementsButton.setContentAreaFilled(true); 
    	this.viewMovementsButton.setBorderPainted(false); 
    	this.viewMovementsButton.setFocusPainted(false); 
        this.viewMovementsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.viewMovementsButton.setForeground(Color.WHITE);
        this.viewMovementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.viewMovementsButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        SeeInventoryView.this.inventoryService.updateTable((DefaultTableModel) inventoryTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(welcomeMessage, SeeInventoryView.this.userService, SeeInventoryView.this.productService, SeeInventoryView.this.supplierService, SeeInventoryView.this.movementService, SeeInventoryView.this.inventoryService);
                dashboardView.showWindow();
            }
        });

        

        viewMovementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                MovementsView movementsView = new MovementsView(welcomeMessage, SeeInventoryView.this.userService, SeeInventoryView.this.productService, SeeInventoryView.this.supplierService, SeeInventoryView.this.movementService, SeeInventoryView.this.inventoryService);
                movementsView.showWindow();
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

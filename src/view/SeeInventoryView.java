package view;

import service.*;
import model.Inventory;
import model.Product;
import view.seeInventoryDialogs.SetLimitsDialog;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SeeInventoryView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;
    
    private JLabel inventoryTableTitle;
    private DefaultTableModel inventoryTableModel;
    private JTable inventoryTable;
    private JButton backButton;
    private JButton viewMovementsButton;
    private JButton exportAsCSVButton;
    private JButton setLimitsButton;

    public SeeInventoryView(String userAccesLevel, String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;

        // Window config
        setTitle("MasterStock | Inventario");
        setSize(750, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.inventoryTableTitle = new JLabel("Inventario");
        this.inventoryTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.inventoryTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Producto (ID)", "Nombre del producto", "Balance", "Precio promedio", "Costo total"};
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

        this.exportAsCSVButton = new JButton("Exportar como CSV");
        this.exportAsCSVButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.exportAsCSVButton.setContentAreaFilled(true); 
    	this.exportAsCSVButton.setBorderPainted(false); 
    	this.exportAsCSVButton.setFocusPainted(false); 
        this.exportAsCSVButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.exportAsCSVButton.setForeground(Color.WHITE);
        this.exportAsCSVButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.setLimitsButton = new JButton("Establecer límites");
        this.setLimitsButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.setLimitsButton.setContentAreaFilled(true); 
    	this.setLimitsButton.setBorderPainted(false); 
    	this.setLimitsButton.setFocusPainted(false); 
        this.setLimitsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.setLimitsButton.setForeground(Color.WHITE);
        this.setLimitsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.viewMovementsButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.exportAsCSVButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        buttonsPanel.add(this.setLimitsButton);
        
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
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage, SeeInventoryView.this.userService, SeeInventoryView.this.productService, SeeInventoryView.this.supplierService, SeeInventoryView.this.movementService, SeeInventoryView.this.inventoryService, SeeInventoryView.this.notificationService);
                dashboardView.showWindow();
            }
        });

        viewMovementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                MovementsView movementsView = new MovementsView(userAccesLevel, welcomeMessage, SeeInventoryView.this.userService, SeeInventoryView.this.productService, SeeInventoryView.this.supplierService, SeeInventoryView.this.movementService, SeeInventoryView.this.inventoryService, SeeInventoryView.this.notificationService);
                movementsView.showWindow();
            }
        });

        exportAsCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[] tableColumns = {"Producto (ID)", "Nombre del producto", "Balance", "Precio promedio", "Costo total"};
                    int selectedRow = SeeInventoryView.this.inventoryTable.getSelectedRow();

                    if (selectedRow >= 0) {
                        Object[] selectedRowData = new Object[SeeInventoryView.this.inventoryTable.getColumnCount()];

                        for (int i = 0; i < SeeInventoryView.this.inventoryTable.getColumnCount(); i++) {
                            selectedRowData[i] = SeeInventoryView.this.inventoryTable.getValueAt(selectedRow, i);
                        }

                        Object[][] newData = {selectedRowData};
                        JTable singleRowTable = new JTable(new DefaultTableModel(newData, tableColumns));

                        SeeInventoryView.this.inventoryService.exportAsCSV(singleRowTable);
                    } else {
                        SeeInventoryView.this.inventoryService.exportAsCSV(inventoryTable);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setLimitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = SeeInventoryView.this.inventoryTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int inventoryProductId = Integer.parseInt(inventoryTable.getValueAt(inventoryTable.getSelectedRow(), 0).toString());
                    Product inventoryProduct = productService.getProductRepository().searchProductById(inventoryProductId);
                    Inventory inventory = inventoryService.getInventoryRepository().searchInventoryByProduct(inventoryProduct);

                    SetLimitsDialog setLimitsDialog = new SetLimitsDialog(inventory, inventoryService);
                    setLimitsDialog.showDialog();
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

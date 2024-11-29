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

public class MovementsView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;
    private OrderService orderService;
    
    private JLabel movementsTableTitle;
    private DefaultTableModel movementsTableModel;
    private JTable movementsTable;
    private JButton backButton;

    public MovementsView(String userAccesLevel, String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.orderService = orderService;

        // Window config
        setTitle("MasterStock | Movimientos");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.movementsTableTitle = new JLabel("Lista de movimientos");
        this.movementsTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.movementsTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Id", "Fecha", "Tipo", "Cantidad", "Precio unitario", "Descripción", "Producto"};
        this.movementsTableModel = new DefaultTableModel(tableColumns, 0);
        this.movementsTable = new JTable(movementsTableModel);

        JTableHeader movementsTableHeader = this.movementsTable.getTableHeader();
        movementsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        this.backButton = new JButton("Volver");
        this.backButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.backButton.setContentAreaFilled(true); 
    	this.backButton.setBorderPainted(false); 
    	this.backButton.setFocusPainted(false); 
        this.backButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.backButton.setForeground(Color.WHITE);
        this.backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.movementsTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.movementsTable));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        tablePanel.add(backButton);

        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        MovementsView.this.movementService.updateTable((DefaultTableModel) movementsTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                SeeInventoryView seeInventoryView = new SeeInventoryView(userAccesLevel, welcomeMessage, MovementsView.this.userService, MovementsView.this.productService, MovementsView.this.supplierService, MovementsView.this.movementService, MovementsView.this.inventoryService, MovementsView.this.notificationService, MovementsView.this.orderService);
                seeInventoryView.showWindow();
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

package view;

import service.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotificationsView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;
    
    private JLabel notificationsTableTitle;
    private DefaultTableModel notificationsTableModel;
    private JTable notificationsTable;
    private JButton backButton;

    public NotificationsView(String userAccesLevel, String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;

        // Window config
        setTitle("MasterStock | Notificaciones");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.notificationsTableTitle = new JLabel("Notificaciones");
        this.notificationsTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.notificationsTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Fecha", "Descripción"};
        this.notificationsTableModel = new DefaultTableModel(tableColumns, 0);
        this.notificationsTable = new JTable(notificationsTableModel);

        JTableHeader productsTableHeader = this.notificationsTable.getTableHeader();
        productsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        TableColumnModel columnModel = notificationsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(1).setPreferredWidth(800);
        notificationsTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

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
        
        tablePanel.add(this.notificationsTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.notificationsTable));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        tablePanel.add(this.backButton);

        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        NotificationsView.this.notificationService.updateTable((DefaultTableModel) notificationsTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage, NotificationsView.this.userService, NotificationsView.this.productService, NotificationsView.this.supplierService, NotificationsView.this.movementService, NotificationsView.this.inventoryService, NotificationsView.this.notificationService);
                dashboardView.showWindow();
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

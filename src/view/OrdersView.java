package view;

import service.*;
import view.ordersDialogs.MakeOrderDialog;
import view.ordersDialogs.ReceiveOrderDialog;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;
    private OrderService orderService;
    
    private JLabel ordersTableTitle;
    private DefaultTableModel ordersTableModel;
    private JTable ordersTable;
    private JButton backButton;
    private JButton makeOrderButton;
    private JButton receiveOrderButton;

    public OrdersView(String userAccesLevel, String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.orderService = orderService;

        // Window config
        setTitle("MasterStock | Pedidos");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.ordersTableTitle = new JLabel("Pedidos");
        this.ordersTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.ordersTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"ID", "Fecha de realización", "Producto", "Proveedor", "Cantidad", "Estado", "Fecha de recepción"};
        this.ordersTableModel = new DefaultTableModel(tableColumns, 0);
        this.ordersTable = new JTable(ordersTableModel);

        JTableHeader productsTableHeader = this.ordersTable.getTableHeader();
        productsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
    
        tablePanel.add(this.ordersTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.ordersTable));
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

        this.makeOrderButton = new JButton("Realizar pedido");
        this.makeOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.makeOrderButton.setContentAreaFilled(true); 
    	this.makeOrderButton.setBorderPainted(false); 
    	this.makeOrderButton.setFocusPainted(false); 
        this.makeOrderButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.makeOrderButton.setForeground(Color.WHITE);
        this.makeOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.receiveOrderButton = new JButton("Recibir pedido");
        this.receiveOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.receiveOrderButton.setContentAreaFilled(true); 
    	this.receiveOrderButton.setBorderPainted(false); 
    	this.receiveOrderButton.setFocusPainted(false); 
        this.receiveOrderButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.receiveOrderButton.setForeground(Color.WHITE);
        this.receiveOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.makeOrderButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.receiveOrderButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        OrdersView.this.orderService.updateTable((DefaultTableModel) ordersTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage, OrdersView.this.userService, OrdersView.this.productService, OrdersView.this.supplierService, OrdersView.this.movementService, OrdersView.this.inventoryService, OrdersView.this.notificationService, OrdersView.this.orderService);
                dashboardView.showWindow();
            }
        });

        makeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MakeOrderDialog makeOrderDialog = new MakeOrderDialog(OrdersView.this.ordersTable, OrdersView.this.productService, OrdersView.this.orderService, OrdersView.this.inventoryService);
                makeOrderDialog.showDialog();
            }
        });

        receiveOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = OrdersView.this.ordersTable.getSelectedRow();

                if (selectedRow >= 0) {
                    ReceiveOrderDialog receiveOrderDialog = new ReceiveOrderDialog(OrdersView.this.ordersTable, OrdersView.this.orderService, OrdersView.this.movementService, OrdersView.this.inventoryService);
                    receiveOrderDialog.showDialog();
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

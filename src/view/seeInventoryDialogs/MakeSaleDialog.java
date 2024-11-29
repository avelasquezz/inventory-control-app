package view.seeInventoryDialogs;

import service.ProductService;
import service.InventoryService;
import service.MovementService;
import service.NotificationService;
import service.OrderService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Inventory;
import model.Movement;
import model.Product;
import model.Notification;
import model.Order;
import model.Supplier;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MakeSaleDialog extends JDialog {
    private ProductService productService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;
    private OrderService orderService;

    private JLabel quantityTextFieldLabel;
    private JTextField quantityTextField;
    private JLabel unitPriceLabel;
    private JLabel unitPriceValueLabel;
    private JLabel descriptionTextAreaLabel;
    private JTextArea descriptionTextArea;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public MakeSaleDialog(JTable inventoryTable, ProductService productService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService, OrderService orderService) {
        this.productService = productService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.orderService = orderService;

        // Dialog config
        setTitle("Modificar existencias");
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.quantityTextFieldLabel = new JLabel("Cantidad");
        this.quantityTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityTextField = new JTextField();
        this.quantityTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quantityTextField.setMaximumSize(new Dimension(200, 40));
        this.quantityTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.unitPriceLabel = new JLabel("Precio");
        this.unitPriceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceValueLabel = new JLabel(inventoryTable.getValueAt(inventoryTable.getSelectedRow(), 3).toString());
        this.unitPriceValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.unitPriceValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.descriptionTextAreaLabel = new JLabel("Descripción");
        this.descriptionTextAreaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.descriptionTextAreaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.descriptionTextArea = new JTextArea();
        this.descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        this.descriptionTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.descriptionTextArea.setLineWrap(true);
        this.descriptionTextArea.setWrapStyleWord(true);
    
        this.acceptButton = new JButton("Aceptar");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        dialogPanel.add(Box.createVerticalGlue());
        
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.unitPriceLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.unitPriceValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.descriptionTextAreaLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(new JScrollPane(this.descriptionTextArea));
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.acceptButton);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.errorMessageLabel);
        
        dialogPanel.add(Box.createVerticalGlue());

        add(dialogPanel);

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int newMovementId = MakeSaleDialog.this.movementService.generateId();
                    LocalDate newMovementDate = LocalDate.now();
                    String newMovementType = "Venta";
                    int newMovementQuantity = Integer.parseInt(MakeSaleDialog.this.quantityTextField.getText());
                    double newMovementUnitPrice = Double.parseDouble(MakeSaleDialog.this.unitPriceValueLabel.getText());
                    String newMovementDescription = MakeSaleDialog.this.descriptionTextArea.getText();
                    Product newMovementProduct = MakeSaleDialog.this.productService.getProductRepository().searchProductById(Integer.parseInt((String) inventoryTable.getValueAt(inventoryTable.getSelectedRow(), 0)));
    
                    Movement newMovement = new Movement(newMovementId, newMovementDate, newMovementType, newMovementQuantity, newMovementUnitPrice, newMovementDescription, newMovementProduct);
                    MakeSaleDialog.this.movementService.getMovementRepository().addMovement(newMovement);
                    
                    Inventory originalInventory = MakeSaleDialog.this.inventoryService.getInventoryRepository().searchInventoryByProduct(newMovementProduct); 
                    int newInventoryBalance = MakeSaleDialog.this.inventoryService.calculateBalance(movementService, newMovementProduct);
                    
                    if (newInventoryBalance < 0) {
                        MakeSaleDialog.this.movementService.getMovementRepository().removeMovement(newMovement);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Existencias insuficientes");
                    } else {
                        double newInventoryUnitPrice = MakeSaleDialog.this.inventoryService.calculateUnitPrice(movementService, newMovementProduct);
                        double newInventoryTotalPrice = MakeSaleDialog.this.inventoryService.calculateTotalPrice(movementService, newMovementProduct);
                        Inventory newInventory = new Inventory(newMovementProduct, newInventoryBalance, newInventoryUnitPrice, newInventoryTotalPrice, originalInventory.getMinStock(), originalInventory.getMaxStock());
                        inventoryService.getInventoryRepository().updateInventory(newInventory);

                        MakeSaleDialog.this.inventoryService.updateTable((DefaultTableModel) inventoryTable.getModel());

                        if (newInventoryBalance < originalInventory.getMinStock()) {
                            LocalDate newNotificationDate = LocalDate.now();
                            String newNotificationDescription = "El stock de " + newMovementProduct.getName() + " se encuentra por debajo del mínimo establecido (" + originalInventory.getMinStock() + "). El pedido se ha realizado automáticamente teniendo en cuenta el stock máximo (" + originalInventory.getMaxStock() + ").";

                            Notification newNotification = new Notification(newNotificationDate, newNotificationDescription);

                            MakeSaleDialog.this.notificationService.getNotificationRepository().addNotification(newNotification);
                            
                            int newOrderId = MakeSaleDialog.this.orderService.generateId();
                            LocalDate newOrderDate = LocalDate.now();
                            Product newOrderProduct = newMovementProduct;
                            Supplier newOrderSupplier = newMovementProduct.getSupplier();
                            int newOrderQuantity = originalInventory.getMaxStock() - newInventoryBalance;
                            boolean newOrderState = false;
                            LocalDate newOrderReceivedDate = null;
                            
                            Order newOrder = new Order(newOrderId, newOrderDate, newOrderProduct, newOrderSupplier, newOrderQuantity, newOrderState, newOrderReceivedDate);
                            MakeSaleDialog.this.orderService.getOrderRepository().addOrder(newOrder);
                        }
                    }

                    dispose();

                } catch (NumberFormatException ex) {
                    System.out.println(ex);
					MakeSaleDialog.this.errorMessageLabel.setText("Se detectaron datos no válidos.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

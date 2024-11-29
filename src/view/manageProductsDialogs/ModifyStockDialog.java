package view.manageProductsDialogs;

import service.ProductService;
import service.InventoryService;
import service.MovementService;
import service.NotificationService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Inventory;
import model.Movement;
import model.Product;
import model.Notification;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ModifyStockDialog extends JDialog {
    private ProductService productService;
    private MovementService movementService;
    private InventoryService inventoryService;
    private NotificationService notificationService;

    private JLabel quantityTextFieldLabel;
    private JTextField quantityTextField;
    private JLabel unitPriceTextFieldLabel;
    private JTextField unitPriceTextField;
    private JLabel descriptionTextAreaLabel;
    private JTextArea descriptionTextArea;
    private JLabel movementTypeComboBoxLabel;
    private JComboBox<String> movementTypeComboBox;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ModifyStockDialog(JTable productsTable, ProductService productService, MovementService movementService, InventoryService inventoryService, NotificationService notificationService) {
        this.productService = productService;
        this.movementService = movementService;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;

        // Dialog config
        setTitle("Modificar existencias");
        setSize(300, 600);
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

        this.unitPriceTextFieldLabel = new JLabel("Precio unitario");
        this.unitPriceTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceTextField = new JTextField();
        this.unitPriceTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.unitPriceTextField.setMaximumSize(new Dimension(200, 40));
        this.unitPriceTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.movementTypeComboBoxLabel = new JLabel("Tipo de movimiento");
        this.movementTypeComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.movementTypeComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] options = {"Venta", "Compra"};
        this.movementTypeComboBox = new JComboBox<>(options);
        this.movementTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.movementTypeComboBox.setMaximumSize(new Dimension(200, 40));
        
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
        dialogPanel.add(this.unitPriceTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.unitPriceTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.movementTypeComboBoxLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.movementTypeComboBox);
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
                    int newMovementId = ModifyStockDialog.this.movementService.generateId();
                    LocalDate newMovementDate = LocalDate.now();
                    String newMovementType = (String) ModifyStockDialog.this.movementTypeComboBox.getSelectedItem();
                    int newMovementQuantity = Integer.parseInt(ModifyStockDialog.this.quantityTextField.getText());
                    double newMovementUnitPrice = Double.parseDouble(ModifyStockDialog.this.unitPriceTextField.getText());
                    String newMovementDescription = ModifyStockDialog.this.descriptionTextArea.getText();
                    Product newMovementProduct = ModifyStockDialog.this.productService.getProductRepository().searchProductById(Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0)));
    
                    Movement newMovement = new Movement(newMovementId, newMovementDate, newMovementType, newMovementQuantity, newMovementUnitPrice, newMovementDescription, newMovementProduct);
                    
                    ModifyStockDialog.this.productService.getProductRepository().updateProduct(newMovementProduct);
                    ModifyStockDialog.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

                    ModifyStockDialog.this.movementService.getMovementRepository().addMovement(newMovement);
                    
                    Inventory originalInventory = ModifyStockDialog.this.inventoryService.getInventoryRepository().searchInventoryByProduct(newMovementProduct); 
                    int newInventoryBalance = ModifyStockDialog.this.inventoryService.calculateBalance(movementService, newMovementProduct);
                    
                    if (newInventoryBalance < 0) {
                        ModifyStockDialog.this.movementService.getMovementRepository().removeMovement(newMovement);
                        dispose();
                        JOptionPane.showMessageDialog(null, "Existencias insuficientes");
                    } else {
                        double newInventoryUnitPrice = ModifyStockDialog.this.inventoryService.calculateUnitPrice(movementService, newMovementProduct);
                        double newInventoryTotalPrice = ModifyStockDialog.this.inventoryService.calculateTotalPrice(movementService, newMovementProduct);
                        Inventory newInventory = new Inventory(newMovementProduct, newInventoryBalance, newInventoryUnitPrice, newInventoryTotalPrice, originalInventory.getMinStock(), originalInventory.getMaxStock());
                        inventoryService.getInventoryRepository().updateInventory(newInventory);

                        if (newInventoryBalance < originalInventory.getMinStock()) {
                            LocalDate newNotificationDate = LocalDate.now();
                            String newNotificationDescription = "El stock de " + newMovementProduct.getName() + " se encuentra por debajo del mínimo establecido (" + originalInventory.getMinStock() + "). El pedido se ha realizado automáticamente teniendo en cuenta el stock máximo (" + originalInventory.getMaxStock() + ").";

                            Notification newNotification = new Notification(newNotificationDate, newNotificationDescription);

                            ModifyStockDialog.this.notificationService.getNotificationRepository().addNotification(newNotification);
                        }
                    }

                    dispose();

                } catch (NumberFormatException ex) {
					ModifyStockDialog.this.errorMessageLabel.setText("Se detectaron datos no válidos.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

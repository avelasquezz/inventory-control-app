package view.ordersDialogs;

import service.*;
import model.Product;
import model.Supplier;
import model.Order;
import model.Inventory;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class MakeOrderDialog extends JDialog {
    private ProductService productService;
    private OrderService orderService;
    private InventoryService inventoryService;
    
    private JLabel idLabel;
    private JLabel idValueLabel;
    private JLabel productComboBoxLabel;
    private JComboBox<String> productComboBox;
    private JLabel quantityTextFieldLabel;
    private JTextField quantityTextField;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public MakeOrderDialog(JTable ordersTable, ProductService productService, OrderService orderService, InventoryService inventoryService) {
        this.productService = productService;
        this.orderService = orderService;
        this.inventoryService = inventoryService;
        
        // Dialog config
        setTitle("Realizar pedido");
        setSize(380, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.idLabel = new JLabel("Id");
        this.idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idValueLabel = new JLabel(String.valueOf(this.orderService.generateId()));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.productComboBoxLabel = new JLabel("Producto");
        this.productComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.productComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.productComboBox = new JComboBox<>(this.productService.getProductNamesList().toArray(new String[0]));
        this.productComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.productComboBox.setMaximumSize(new Dimension(200, 40));

        this.quantityTextFieldLabel = new JLabel("Cantidad");
        this.quantityTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityTextField = new JTextField();
        this.quantityTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quantityTextField.setMaximumSize(new Dimension(200, 40));
        this.quantityTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

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
        
        dialogPanel.add(this.idLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.idValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add (this.productComboBoxLabel);
        dialogPanel.add (this.productComboBox);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityTextField);
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
                    if (MakeOrderDialog.this.productService.getProductRepository().getProductsList().size() == 0) {
                        MakeOrderDialog.this.errorMessageLabel.setText("No hay productos registrados.");
                        return;
                    } else {
                        int newOrderId = Integer.parseInt(MakeOrderDialog.this.idValueLabel.getText());
                        LocalDate newOrderDate = LocalDate.now();
                        String newOrderProductName = (String) MakeOrderDialog.this.productComboBox.getSelectedItem();
                        Product newOrderProduct = MakeOrderDialog.this.productService.getProductRepository().searchProductByName(newOrderProductName);
                        Supplier newOrdeSupplier = newOrderProduct.getSupplier();
                        int newOrderQuantity = Integer.valueOf(MakeOrderDialog.this.quantityTextField.getText());
                        boolean newOrderState = false;
                        LocalDate newOrderReceivedDate = null;

                        Inventory inventory = MakeOrderDialog.this.inventoryService.getInventoryRepository().searchInventoryByProduct(newOrderProduct);

                        if ((inventory.getBalance() + newOrderQuantity) > inventory.getMaxStock()) {
                            MakeOrderDialog.this.errorMessageLabel.setText("La cantidad del pedido supera el stock máximo.");
                            return;
                        } 

                        Order newOrder = new Order(newOrderId, newOrderDate, newOrderProduct, newOrdeSupplier, newOrderQuantity, newOrderState, newOrderReceivedDate);

                        MakeOrderDialog.this.orderService.getOrderRepository().addOrder(newOrder);
                        MakeOrderDialog.this.orderService.updateTable((DefaultTableModel) ordersTable.getModel());

                        dispose();
                    }
                } catch (NumberFormatException ex) {
					MakeOrderDialog.this.errorMessageLabel.setText("Se detectaron datos no válidos.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
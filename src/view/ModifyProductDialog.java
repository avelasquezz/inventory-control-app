package view;

import repository.ProductRepository;
import repository.SupplierRepository;
import service.ProductService;
import service.SupplierService;
import model.Product;
import model.Supplier;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyProductDialog extends JDialog {
    private ProductRepository productRepository;
    private ProductService productService;
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;

    private JLabel idLabel;
    private JLabel idValueLabel;
    private JLabel nameTextFieldLabel;
    private JTextField nameTextField;
    private JLabel categoryTextFieldLabel;
    private JTextField categoryTextField;
    private JLabel quantityLabel;
    private JLabel quantityValueLabel;
    private JLabel unitPriceTextFieldLabel;
    private JTextField unitPriceTextField;
    private JLabel supplierComboBoxLabel;
    private JComboBox<String> supplierComboBox;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ModifyProductDialog(JTable productsTable, ProductRepository productRepository, ProductService productService, SupplierRepository supplierRepository, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
        
        // Dialog config
        setTitle("Modify Product");
        setSize(300, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.idLabel = new JLabel("Id");
        this.idLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idValueLabel = new JLabel((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextFieldLabel = new JLabel("Name");
        this.nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextField = new JTextField((String) productsTable.getValueAt(productsTable.getSelectedRow(), 1));
        this.nameTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextField.setMaximumSize(new Dimension(200, 40));
        this.nameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.categoryTextFieldLabel = new JLabel("Category");
        this.categoryTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.categoryTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.categoryTextField = new JTextField((String) productsTable.getValueAt(productsTable.getSelectedRow(), 2));
        this.categoryTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.categoryTextField.setMaximumSize(new Dimension(200, 40));
        this.categoryTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.quantityLabel = new JLabel("Quantity");
        this.quantityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityValueLabel = new JLabel((String) productsTable.getValueAt(productsTable.getSelectedRow(), 3));
        this.quantityValueLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityValueLabel.setFont(new Font("Arial", Font.BOLD, 18));
        this.quantityValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceTextFieldLabel = new JLabel("Unit Price");
        this.unitPriceTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceTextField = new JTextField((String) productsTable.getValueAt(productsTable.getSelectedRow(), 4));
        this.unitPriceTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextField.setMaximumSize(new Dimension(200, 40));
        this.unitPriceTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.supplierComboBoxLabel = new JLabel("Supplier");
        this.supplierComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.supplierComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.supplierComboBox = new JComboBox<>(this.supplierService.getSupplierNamesList().toArray(new String[0]));
        this.supplierComboBox.setSelectedItem((String) productsTable.getValueAt(productsTable.getSelectedRow(), 5));
        this.supplierComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        this.supplierComboBox.setMaximumSize(new Dimension(200, 40));
        this.supplierComboBox.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.acceptButton = new JButton("Accept");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        dialogPanel.add(Box.createVerticalGlue());
        
        dialogPanel.add(this.idLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.idValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.nameTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.nameTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.categoryTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.categoryTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.unitPriceTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.unitPriceTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add (this.supplierComboBoxLabel);
        dialogPanel.add (this.supplierComboBox);
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
                    int newProductId = Integer.parseInt(ModifyProductDialog.this.idValueLabel.getText());
                    String newProductName = ModifyProductDialog.this.nameTextField.getText();
                    String newProductCategory = ModifyProductDialog.this.categoryTextField.getText();
                    int newProductQuantity = Integer.parseInt(ModifyProductDialog.this.quantityValueLabel.getText());
                    double newProductUnitPrice = Double.parseDouble(ModifyProductDialog.this.unitPriceTextField.getText());
                    Supplier newProductSupplier = ModifyProductDialog.this.supplierRepository.searchSupplierByName((String) ModifyProductDialog.this.supplierComboBox.getSelectedItem());
                    
                    Product modifiedProduct = new Product(newProductId, newProductName, newProductCategory, newProductQuantity, newProductUnitPrice, newProductSupplier);
            
                    ModifyProductDialog.this.productRepository.updateProduct(modifiedProduct);
                    ModifyProductDialog.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

                    dispose();
                } catch (NumberFormatException ex) {
					ModifyProductDialog.this.errorMessageLabel.setText("Por favor, ingresa valores válidos.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
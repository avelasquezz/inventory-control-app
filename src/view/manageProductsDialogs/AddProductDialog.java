package view.manageProductsDialogs;

import repository.*;
import service.*;
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

public class AddProductDialog extends JDialog {
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
    private JLabel quantityTextFieldLabel;
    private JTextField quantityTextField;
    private JLabel unitPriceTextFieldLabel;
    private JTextField unitPriceTextField;
    private JLabel supplierComboBoxLabel;
    private JComboBox<String> supplierComboBox;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public AddProductDialog(JTable productsTable, ProductRepository productRepository, ProductService productService, SupplierRepository supplierRepository, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.supplierRepository = supplierRepository;
        this.supplierService = supplierService;
        
        // Dialog config
        setTitle("Add Product");
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
        
        this.idValueLabel = new JLabel(String.valueOf(this.productService.generateId()));
        this.idValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.idValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextFieldLabel = new JLabel("Name");
        this.nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextField = new JTextField();
        this.nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.nameTextField.setMaximumSize(new Dimension(200, 40));
        this.nameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.categoryTextFieldLabel = new JLabel("Category");
        this.categoryTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.categoryTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.categoryTextField = new JTextField();
        this.categoryTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.categoryTextField.setMaximumSize(new Dimension(200, 40));
        this.categoryTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.quantityTextFieldLabel = new JLabel("Quantity");
        this.quantityTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityTextField = new JTextField();
        this.quantityTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quantityTextField.setMaximumSize(new Dimension(200, 40));
        this.quantityTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.unitPriceTextFieldLabel = new JLabel("Unit Price");
        this.unitPriceTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceTextField = new JTextField();
        this.unitPriceTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.unitPriceTextField.setMaximumSize(new Dimension(200, 40));
        this.unitPriceTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.supplierComboBoxLabel = new JLabel("Supplier");
        this.supplierComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.supplierComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.supplierComboBox = new JComboBox<>(this.supplierService.getSupplierNamesList().toArray(new String[0]));
        this.supplierComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.supplierComboBox.setMaximumSize(new Dimension(200, 40));

        this.acceptButton = new JButton("Accept");
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
        dialogPanel.add(this.nameTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.nameTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.categoryTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.categoryTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityTextField);
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
                    int newProductId = Integer.parseInt(AddProductDialog.this.idValueLabel.getText());
                    String newProductName = AddProductDialog.this.nameTextField.getText();
                    String newProductCategory = AddProductDialog.this.categoryTextField.getText();
                    int newProductQuantity = Integer.parseInt(AddProductDialog.this.quantityTextField.getText());
                    double newProductUnitPrice = Double.parseDouble(AddProductDialog.this.unitPriceTextField.getText());

                    String supplierName = (String) AddProductDialog.this.supplierComboBox.getSelectedItem();
                    Supplier newProductSupplier = AddProductDialog.this.supplierRepository.searchSupplierByName(supplierName);

                    Product newProduct = new Product(newProductId, newProductName, newProductCategory, newProductQuantity, newProductUnitPrice, newProductSupplier);

                    AddProductDialog.this.productRepository.addProduct(newProduct);
                    AddProductDialog.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

                    dispose();
                } catch (NumberFormatException ex) {
					AddProductDialog.this.errorMessageLabel.setText("Invalid data detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
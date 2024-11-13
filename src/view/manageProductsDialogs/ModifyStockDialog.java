package view.manageProductsDialogs;

import service.ProductService;
import service.MovementService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Movement;
import model.Product;

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

    private JLabel currentQuantityLabel;
    private JLabel currentQuantityValueLabel;
    private JLabel quantityToAddTextFieldLabel;
    private JTextField quantityToAddTextField;
    private JLabel descriptionTextAreaLabel;
    private JTextArea descriptionTextArea;
    private JLabel addStockOptionLabel;
    private JRadioButton addStockOption;
    private JLabel reduceStockOptionLabel;
    private JRadioButton reduceStockOption;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ModifyStockDialog(JTable productsTable, ProductService productService, MovementService movementService) {
        this.productService = productService;
        this.movementService = movementService;

        // Dialog config
        setTitle("Add Stock");
        setSize(300, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.currentQuantityLabel = new JLabel("Current quantity");
        this.currentQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.currentQuantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.currentQuantityValueLabel = new JLabel((String) productsTable.getValueAt(productsTable.getSelectedRow(), 3));
        this.currentQuantityValueLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.currentQuantityValueLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityToAddTextFieldLabel = new JLabel("Movement quantity");
        this.quantityToAddTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.quantityToAddTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.quantityToAddTextField = new JTextField();
        this.quantityToAddTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.quantityToAddTextField.setMaximumSize(new Dimension(200, 40));
        this.quantityToAddTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.descriptionTextAreaLabel = new JLabel("Movement description");
        this.descriptionTextAreaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.descriptionTextAreaLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.descriptionTextArea = new JTextArea();
        this.descriptionTextArea.setFont(new Font("Arial", Font.PLAIN, 16));
        this.descriptionTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.descriptionTextArea.setLineWrap(true);
        this.descriptionTextArea.setWrapStyleWord(true);
        
        // Crete subpanel to organize rabio buttons
        JPanel radioButtonsPanel = new JPanel();
        radioButtonsPanel.setLayout(new BoxLayout(radioButtonsPanel, BoxLayout.X_AXIS));

        this.addStockOptionLabel = new JLabel("Add stock");
        this.addStockOptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.addStockOptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.addStockOption = new JRadioButton();
        
        this.reduceStockOptionLabel = new JLabel("Reduce stock");
        this.reduceStockOptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        this.reduceStockOptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.reduceStockOption = new JRadioButton();

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(addStockOption);
        buttonGroup.add(reduceStockOption);

        radioButtonsPanel.add(Box.createHorizontalGlue());
        
        radioButtonsPanel.add(addStockOption);
        radioButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        radioButtonsPanel.add(addStockOptionLabel);
        radioButtonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        radioButtonsPanel.add(reduceStockOption);
        radioButtonsPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        radioButtonsPanel.add(reduceStockOptionLabel);
        
        radioButtonsPanel.add(Box.createHorizontalGlue());
    
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
        
        dialogPanel.add(this.currentQuantityLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.currentQuantityValueLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.quantityToAddTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.quantityToAddTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.descriptionTextAreaLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(new JScrollPane(this.descriptionTextArea));
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(radioButtonsPanel);
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
                    String newMovementDescription = ModifyStockDialog.this.descriptionTextArea.getText();
                    LocalDate newMovementDate = LocalDate.now();
                    Product newMovementProduct = ModifyStockDialog.this.productService.getProductRepository().searchProductById(Integer.parseInt((String) productsTable.getValueAt(productsTable.getSelectedRow(), 0)));
                    String newMovementType = ModifyStockDialog.this.addStockOption.isSelected() ? "Add" : "Reduce";
                    int oldMovementQuantity = newMovementProduct.getQuantity();
                    int newMovementQuantity;
                    
                    if (ModifyStockDialog.this.addStockOption.isSelected()) {
                        newMovementQuantity = newMovementProduct.getQuantity() + Integer.parseInt(ModifyStockDialog.this.quantityToAddTextField.getText());
                        newMovementProduct.setQuantity(newMovementQuantity);
                    } else if (ModifyStockDialog.this.reduceStockOption.isSelected() & !(newMovementProduct.getQuantity() - Integer.parseInt(ModifyStockDialog.this.quantityToAddTextField.getText()) < 0) & !(newMovementDescription.equals(""))) {
                        newMovementQuantity = newMovementProduct.getQuantity() - Integer.parseInt(ModifyStockDialog.this.quantityToAddTextField.getText());
                        newMovementProduct.setQuantity(newMovementQuantity);
                    } else {
                        throw new NumberFormatException();
                    }
    
                    Movement newMovement = new Movement(newMovementId, newMovementDescription, newMovementDate, newMovementProduct, newMovementType, oldMovementQuantity, newMovementQuantity);
                    ModifyStockDialog.this.productService.getProductRepository().updateProduct(newMovementProduct);

                    ModifyStockDialog.this.productService.updateTable((DefaultTableModel) productsTable.getModel());
                    ModifyStockDialog.this.movementService.getMovementRepository().addMovement(newMovement);

                    dispose();
                } catch (NumberFormatException ex) {
					ModifyStockDialog.this.errorMessageLabel.setText("Invalid data detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}

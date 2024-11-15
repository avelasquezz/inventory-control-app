package view;

import service.*;

import model.Product;
import view.manageProductsDialogs.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageProductsView extends JFrame {
    private UserService userService;
    private ProductService productService;
    private SupplierService supplierService;
    private MovementService movementService;
    
    private JLabel productsTableTitle;
    private JButton searchButton;
    private JTextField searchTextField;
    private JLabel searchErrorMessageLabel;
    private DefaultTableModel productsTableModel;
    private JTable productsTable;
    private JButton backButton;
    private JButton addProductButton;
    private JButton modifyProductButton;
    private JButton removeProductButton;
    private JButton modifyStockButton;
    private JButton viewMovementsButton;

    public ManageProductsView(String welcomeMessage, UserService userService, ProductService productService, SupplierService supplierService, MovementService movementService) {
        this.userService = userService;
        this.productService = productService;
        this.supplierService = supplierService;
        this.movementService = movementService;

        // Window config
        setTitle("MasterStock | Productos");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.productsTableTitle = new JLabel("Lista de productos");
        this.productsTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.productsTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Id", "Nombre", "Categoría", "Proveedor"};
        this.productsTableModel = new DefaultTableModel(tableColumns, 0);
        this.productsTable = new JTable(productsTableModel);

        JTableHeader productsTableHeader = this.productsTable.getTableHeader();
        productsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.productsTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Create subpanel to organize search field
        JPanel searchFieldPanel = new JPanel();
        searchFieldPanel.setLayout(new BoxLayout(searchFieldPanel, BoxLayout.X_AXIS));

        this.searchButton = new JButton("Buscar");
        this.searchButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.searchButton.setContentAreaFilled(true); 
    	this.searchButton.setBorderPainted(false); 
    	this.searchButton.setFocusPainted(false); 
        this.searchButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.searchButton.setForeground(Color.WHITE);
        this.searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.searchTextField = new JTextField();
        this.searchTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.searchTextField.setMaximumSize(new Dimension(300, 40));
        this.searchTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add components to search panel
        searchFieldPanel.add(this.searchButton);
        searchFieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        searchFieldPanel.add(this.searchTextField);

        tablePanel.add(searchFieldPanel);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        this.searchErrorMessageLabel = new JLabel("");
        this.searchErrorMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.searchErrorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.searchErrorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        tablePanel.add(searchErrorMessageLabel);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));
        tablePanel.add(new JScrollPane(this.productsTable));
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
        
        this.addProductButton = new JButton("Agregar");
        this.addProductButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.addProductButton.setContentAreaFilled(true); 
    	this.addProductButton.setBorderPainted(false); 
    	this.addProductButton.setFocusPainted(false); 
        this.addProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.addProductButton.setForeground(Color.WHITE);
        this.addProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifyProductButton = new JButton("Modificar");
        this.modifyProductButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.modifyProductButton.setContentAreaFilled(true); 
    	this.modifyProductButton.setBorderPainted(false); 
    	this.modifyProductButton.setFocusPainted(false); 
        this.modifyProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifyProductButton.setForeground(Color.WHITE);
        this.modifyProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.removeProductButton = new JButton("Remover");
        this.removeProductButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.removeProductButton.setContentAreaFilled(true); 
    	this.removeProductButton.setBorderPainted(false); 
    	this.removeProductButton.setFocusPainted(false); 
        this.removeProductButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.removeProductButton.setForeground(Color.WHITE);
        this.removeProductButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifyStockButton = new JButton("Modificar existencias");
        this.modifyStockButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.modifyStockButton.setContentAreaFilled(true); 
    	this.modifyStockButton.setBorderPainted(false); 
    	this.modifyStockButton.setFocusPainted(false); 
        this.modifyStockButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifyStockButton.setForeground(Color.WHITE);
        this.modifyStockButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.viewMovementsButton = new JButton("Ver movimientos");
        this.viewMovementsButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.viewMovementsButton.setContentAreaFilled(true); 
    	this.viewMovementsButton.setBorderPainted(false); 
    	this.viewMovementsButton.setFocusPainted(false); 
        this.viewMovementsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.viewMovementsButton.setForeground(Color.WHITE);
        this.viewMovementsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.addProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifyProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.removeProductButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifyStockButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.viewMovementsButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        ManageProductsView.this.productService.updateTable((DefaultTableModel) productsTable.getModel());

        // Button actions
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchedName = ManageProductsView.this.searchTextField.getText();

                if (searchedName.equals("")) {
                    ManageProductsView.this.searchErrorMessageLabel.setText("");
                    ManageProductsView.this.productService.updateTable(productsTableModel);
                    return;
                }
                
                List<Product> foundProducts = ManageProductsView.this.productService.getProductRepository().searchProductsByName(searchedName);
                
                if (foundProducts.size() == 0) {
                    ManageProductsView.this.searchErrorMessageLabel.setText("El producto no existe.");
                    ManageProductsView.this.productService.updateTable(productsTableModel);
                } else {
                    ManageProductsView.this.searchErrorMessageLabel.setText("");
                    ManageProductsView.this.productService.updateTable(productsTableModel, foundProducts);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(welcomeMessage, ManageProductsView.this.userService, ManageProductsView.this.productService, ManageProductsView.this.supplierService, ManageProductsView.this.movementService);
                dashboardView.showWindow();
            }
        });

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddProductDialog addProductDialog = new AddProductDialog(productsTable, ManageProductsView.this.productService, ManageProductsView.this.supplierService);
                addProductDialog.showDialog();
            }
        });

        modifyProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifyProductDialog modifyProductDialog = new ModifyProductDialog(ManageProductsView.this.productsTable, ManageProductsView.this.productService, ManageProductsView.this.supplierService);
                    modifyProductDialog.showDialog();
                }
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Product produtToRemove = ManageProductsView.this.productService.getProductRepository().searchProductById(Integer.parseInt(productsTableModel.getValueAt(selectedRow, 0).toString()));
                    ManageProductsView.this.productService.getProductRepository().removeProduct(produtToRemove);
                    ManageProductsView.this.productsTableModel.removeRow(selectedRow);
                }
            }
        });

        modifyStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageProductsView.this.productsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifyStockDialog modifyStockDialog = new ModifyStockDialog(ManageProductsView.this.productsTable, ManageProductsView.this.productService, ManageProductsView.this.movementService);
                    modifyStockDialog.showDialog();
                }
            }
        });

        viewMovementsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                
                MovementsView movementsView = new MovementsView(welcomeMessage, ManageProductsView.this.userService, ManageProductsView.this.productService, ManageProductsView.this.supplierService, ManageProductsView.this.movementService);
                movementsView.showWindow();
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}

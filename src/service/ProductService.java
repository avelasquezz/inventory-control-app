package service;

import repository.ProductRepository;
import model.Product;

import java.util.Random;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductRepository getProductRepository() {
        return this.productRepository;
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Product product : this.productRepository.getProductsList()) {
                findingNewId = findingNewId || product.getId() == newId;
            }
        }

        return newId;
    }

    public void updateTable(DefaultTableModel productsTableModel) {
        productsTableModel.setRowCount(0);
        
        for (Product product : productRepository.getProductsList()) {
            String[] tableRow = {
                String.valueOf(product.getId()), 
                product.getName(), product.getCategory(), 
                String.valueOf(product.getQuantity()), 
                String.valueOf(product.getUnitPrice()), 
                product.getSupplier().getName()
            };
            productsTableModel.addRow(tableRow);
        }

        productsTableModel.fireTableDataChanged();
    }

    public void updateTable(DefaultTableModel productsTableModel, List<Product> productsList) {
        productsTableModel.setRowCount(0);
        
        for (Product product : productsList) {
            String[] tableRow = {
                String.valueOf(product.getId()), 
                product.getName(), product.getCategory(), 
                String.valueOf(product.getQuantity()), 
                String.valueOf(product.getUnitPrice()), 
                product.getSupplier().getName()
            };
            productsTableModel.addRow(tableRow);
        }

        productsTableModel.fireTableDataChanged();
    }
}

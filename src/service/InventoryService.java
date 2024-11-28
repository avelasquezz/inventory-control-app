package service;

import javax.swing.table.DefaultTableModel;

import java.text.DecimalFormat;
import java.util.List;

import model.Movement;
import model.Inventory;
import model.Product;
import repository.InventoryRepository;

public class InventoryService {
    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public InventoryRepository getInventoryRepository() {
        return this.inventoryRepository;
    }

    public void updateTable(DefaultTableModel inventoryTableModel) {
        inventoryTableModel.setRowCount(0);

        DecimalFormat df = new DecimalFormat("#,###");
        
        for (Inventory inventory : inventoryRepository.getInventoryList()) {
            String[] tableRow = {
                inventory.getProduct().getName(), 
                String.valueOf(inventory.getBalance()),
                String.valueOf(df.format(inventory.getUnitPrice())),
                String.valueOf(df.format(inventory.getTotalPrice()))
            };
            inventoryTableModel.addRow(tableRow);
        }

        inventoryTableModel.fireTableDataChanged();
    }

    public int calculateBalance(MovementService movementService, Product product) {
        int balance = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        for (Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Compra") {
                balance = balance + movement.getQuantity(); 
            } else {
                balance = balance - movement.getQuantity();
            }
        }

        return balance;
    }

    public int calculateCumulativePurchases(MovementService movementService, Product product) {
        int cumulativePurchases = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        for (Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Compra") {
                cumulativePurchases = cumulativePurchases + movement.getQuantity(); 
            }
        }

        return cumulativePurchases;
    }

    public double calculateUnitPrice(MovementService movementService, Product product) {
        double unitPrice = 0;
        List<Movement> movementsList = movementService.getMovementRepository().getMovementsList();
        
        int cumulativePurchases = calculateCumulativePurchases(movementService, product);

        for(Movement movement : movementsList) {
            if (movement.getProduct().getId() == product.getId() && movement.getType() == "Compra") {
                unitPrice = unitPrice + ((movement.getQuantity() * movement.getUnitPrice()) / cumulativePurchases);
            }
        }

        return unitPrice;
    }

    public double calculateTotalPrice(MovementService movementService, Product product) {
        int balance = calculateBalance(movementService, product);
        double unitPrice = calculateUnitPrice(movementService, product);

        return balance * unitPrice;
    }
}

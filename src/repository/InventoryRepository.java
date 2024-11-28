package repository;

import java.util.List;
import java.util.ArrayList;

import model.Inventory;
import model.Product;

public class InventoryRepository {
    private List<Inventory> inventoryList = new ArrayList<>();

    public Inventory searchInventoryByProduct(Product product) {
        for (Inventory inventory : inventoryList) {
            if (inventory.getProduct().getId() == product.getId()) {
                return inventory;
            }
        }
        return null;
    }

    public void addInventory(Inventory inventoryToAdd) {
        inventoryList.add(inventoryToAdd);
    }

    public void removeInventory(Inventory InventoryToRemove) {
        inventoryList.remove(InventoryToRemove);
    }

    public void updateInventory(Inventory modifiedInventory) {
        Product modifiedInventoryProduct = modifiedInventory.getProduct();
        Inventory originalInventory = searchInventoryByProduct(modifiedInventoryProduct);
        int originalInventoryIndex = inventoryList.indexOf(originalInventory);

        inventoryList.set(originalInventoryIndex, modifiedInventory);
    }

    public List<Inventory> getInventoryList() {
        return new ArrayList<>(inventoryList);
    }
}

package model;

public class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double unitPrice;
    private Supplier supplier;

    public Product(int id, String name, String category, int quantity, double unitPrice, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.supplier = supplier;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public Supplier getSupplier() {
        return this.supplier;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
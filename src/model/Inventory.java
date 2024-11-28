package model;

public class Inventory {
    private Product product;
    private int balance;
    private double unitPrice;
    private double totalPrice;

    public Inventory(Product product, int balance, double unitPrice, double totalPrice) {
        this.product = product;
        this.balance = balance;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
    }

    // Getter methods
    public Product getProduct() {
        return this.product;
    }

    public int getBalance() {
        return this.balance;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    // Setter methods
    public void setProduct(Product product) {
        this.product = product;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

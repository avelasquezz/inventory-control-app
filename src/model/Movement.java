package model;

import java.time.LocalDate;

public class Movement {
    private int id;
    private String description;
    private LocalDate date;
    private Product product;
    private String type;
    private int quantity;

    public Movement(int id, String description, LocalDate date, Product product, String type, int quantity) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.product = product;
        this.type = type;
        this.quantity = quantity;
    }

    // Getter methods
    public int getId() {
        return this.id;
    }
    
    public String getDescription() {
        return this.description;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public Product getProduct() {
        return this.product;
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    } 

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

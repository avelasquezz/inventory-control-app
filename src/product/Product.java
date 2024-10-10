package product;

public class Product {
    private int id;
    private String category;
    private int quantity;
    private double unitPrice;
    private String expirationDate;

    public Product(int id, String category, int quantity, double unitPrice, String expirationDate) {
        this.id = id;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.expirationDate = expirationDate;
    }

    public Product() {
        this.id = -1;
    }

    public boolean isNullProduct(Product product) {
        return id == -1;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    // Setter methods
    public void setId(int id) {
        this.id = id;
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

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

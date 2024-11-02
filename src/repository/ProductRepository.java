package repository;

import java.util.List;
import java.util.ArrayList;

import model.Product;

public class ProductRepository {
    private List<Product> productsList = new ArrayList<>();

    public Product searchProductById(int id) {
        for (Product product : productsList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product productToAdd) {
        productsList.add(productToAdd);
    }

    public void removeProduct(Product productToRemove) {
        productsList.remove(productToRemove);
    }

    public void updateProduct(Product modifiedProduct) {
        int modifiedProductId = modifiedProduct.getId();
        Product originalProduct = searchProductById(modifiedProductId);
        int originalProductIndex = productsList.indexOf(originalProduct);

        productsList.set(originalProductIndex, modifiedProduct);
    }

    public List<Product> getProductsList() {
        return new ArrayList<>(productsList);
    }
}

package main;

import repository.*;
import service.*;

import view.LoginView;
import model.User;
import model.Product;
import model.Supplier;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);
        SupplierRepository supplierRepository = new SupplierRepository();
        SupplierService supplierService = new SupplierService(supplierRepository);
        MovementRepository movementRepository = new MovementRepository();
        MovementService movementService = new MovementService(movementRepository);
              
        LoginView loginView = new LoginView(userRepository, userService, productRepository, productService, supplierRepository, supplierService, movementRepository, movementService);

        User exampleUser = new User("cc", "111", "Juan Andrés", "Velásquez Jiménez", "222", "juan@example.com", "123", true);
        userRepository.addUser(exampleUser);

        Supplier exampleSupplier = new Supplier(11, "Hooks", "Pereira", "333111");
        supplierRepository.addSupplier(exampleSupplier);

        Product exampleProduct = new Product(1992, "Camiseta negra", "Camisetas", 500, 55000.0, exampleSupplier);
        productRepository.addProduct(exampleProduct);

        loginView.showWindow();
    }
}

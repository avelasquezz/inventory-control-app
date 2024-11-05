package main;

import repository.*;
import service.*;

import view.LoginView;

import model.Supplier;
import model.User;

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

        User exampleUser1 = new User("ID", 1111, "John", "Doe", "12345", "john@example.com", "password123", true);
    	User exampleUser2 = new User("ID", 2222, "Jane", "Smith", "54321", "jane@example.com", "mypassword", true);
    	User exampleUser3 = new User("Passport", 3333, "Alice", "Johnson", "43983", "alice@example.com", "securepass", true);
    	User exampleUser4 = new User("ID", 4444, "Bob", "Brown", "67890", "bob@example.com", "123", true);

        userRepository.addUser(exampleUser1);
        userRepository.addUser(exampleUser2);
        userRepository.addUser(exampleUser3);
        userRepository.addUser(exampleUser4);

        Supplier undefinedSupplier = new Supplier(1111, "Undefined", "Undefined", "Undefined");
        supplierRepository.addSupplier(undefinedSupplier);
            
        loginView.showWindow();
    }
}

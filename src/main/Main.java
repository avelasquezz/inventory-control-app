package main;

import repository.*;
import service.*;

import view.LoginView;

import model.User;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        ProductRepository productRepository = new ProductRepository();
        SupplierRepository supplierRepository = new SupplierRepository();
        MovementRepository movementRepository = new MovementRepository();
        
        UserService userService = new UserService(userRepository);
        ProductService productService = new ProductService(productRepository);
        SupplierService supplierService = new SupplierService(supplierRepository);
        MovementService movementService = new MovementService(movementRepository);
              
        LoginView loginView = new LoginView(userService, productService, supplierService, movementService);

        User exampleUser1 = new User("ID", 1111, "John", "Doe", "12345", "john@example.com", "password123", true);
    	User exampleUser2 = new User("ID", 2222, "Jane", "Smith", "54321", "jane@example.com", "mypassword", true);
    	User exampleUser3 = new User("Passport", 3333, "Alice", "Johnson", "43983", "alice@example.com", "securepass", true);
    	User exampleUser4 = new User("ID", 4444, "Bob", "Brown", "67890", "bob@example.com", "123", true);

        userRepository.addUser(exampleUser1);
        userRepository.addUser(exampleUser2);
        userRepository.addUser(exampleUser3);
        userRepository.addUser(exampleUser4);

        loginView.showWindow();
    }
}

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
        InventoryRepository inventoryRepository = new InventoryRepository();
        NotificationRepository notificationRepository = new NotificationRepository();
        OrderRepository orderRepository = new OrderRepository();
        
        UserService userService = new UserService(userRepository);
        ProductService productService = new ProductService(productRepository);
        SupplierService supplierService = new SupplierService(supplierRepository);
        MovementService movementService = new MovementService(movementRepository);
        InventoryService inventoryService = new InventoryService(inventoryRepository);
        NotificationService notificationService = new NotificationService(notificationRepository);
        OrderService orderService = new OrderService(orderRepository);
              
        LoginView loginView = new LoginView(userService, productService, supplierService, movementService, inventoryService, notificationService, orderService);

        User defaultUser = new User("ID", 1001, "Default", "User", "12345", "user@user.com", "123", true, "Administrador");
        userRepository.addUser(defaultUser);

        loginView.showWindow();
    }
}

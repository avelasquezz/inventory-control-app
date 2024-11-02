package main;

import repository.UserRepository;
import service.UserService;
import view.LoginView;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);      
        LoginView loginView = new LoginView(userService);

        loginView.showLoginWindow();
    }
}

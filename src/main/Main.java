package main;

import repository.UserRepository;
import service.UserService;
import view.LoginView;
import model.User;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(userRepository);      
        LoginView loginView = new LoginView(userService, userRepository);

        User exampleUser = new User("cc", "111", "Juan Andrés", "Velásquez Jiménez", "222", "juan@example.com", "123", true);
        userRepository.addUser(exampleUser);

        loginView.showWindow();
    }
}

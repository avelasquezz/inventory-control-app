package service;

import repository.UserRepository;
import model.User;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public boolean validateUser(String emailAddress, String password) {
        User userToValidate = this.userRepository.searchUserByEmailAddress(emailAddress);
        if (userToValidate == null) {
            return false;
        }
        return userToValidate.getPassword().equals(password);
    }
}
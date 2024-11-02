package repository;

import java.util.List;
import java.util.ArrayList;

import model.User;

public class UserRepository {
    private List<User> usersList = new ArrayList<>();

    public User searchUserByEmailAddress(String emailAddress) {
        for (User user : usersList) {
            if (user.getEmailAddress().equals(emailAddress)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User userToAdd) {
        usersList.add(userToAdd);
    }

    public void removeUser(User userToRemove) {
        usersList.remove(userToRemove);
    }

    public void updateUser(User modifiedUser) {
        String modifiedUserEmailAddress = modifiedUser.getEmailAddress();
        User originalUser = searchUserByEmailAddress(modifiedUserEmailAddress);
        int originalUserIndex = usersList.indexOf(originalUser);

        usersList.set(originalUserIndex, modifiedUser);
    }

    public List<User> getUsersList() {
        return new ArrayList<>(usersList);
    }
}

package user;

import user.User;
import java.util.List;
import java.util.ArrayList;

public class UserRepository {	
	static List<User> usersList = new ArrayList<>();

	public static void createUser(int id, String name, String lastName, String idType, String idNumber, String emailAddress, String phoneNumber, String password, boolean isActive) {
		User user = new User(id, name, lastName, idType, idNumber, emailAddress, phoneNumber, password, isActive);
		usersList.add(user);
	}

	public static User searchUserByEmail(String emailAddress) {
		for (User user: usersList) {
			if (user.getEmailAddress().equals(emailAddress)) {
				return user;
			}
		}
		return new User(); // If it can´t find the searched user, it returns a null user.
	}

	public static void modifyUser(String emailAddress, String attributeToModify, String newAttributeValue) {
		User userToModify = searchUserByEmail(emailAddress);

		if (!userToModify.isNullUser()) {
			switch (attributeToModify) {
				case "id":
					userToModify.setId(Integer.parseInt(newAttributeValue));
					break;

				case "name":
					userToModify.setName(newAttributeValue);
					break;

				case "lastName":
					userToModify.setLastName(newAttributeValue);
					break;

				case "idType":
					userToModify.setIdType(newAttributeValue);
					break;

				case "idNumber":
					userToModify.setIdNumber(newAttributeValue);
					break;

				case "emailAddress":
					userToModify.setEmailAddress(newAttributeValue);
					break;

				case "phoneNumber":
					userToModify.setPhoneNumber(newAttributeValue);
					break;

				case "password":
					userToModify.setPassword(newAttributeValue);
					break;

				case "state":
					userToModify.setState(Boolean.parseBoolean(newAttributeValue));
					break;

				default:
					break;
			}
		}
	}

	public static void activateUser(String emailAddress) {
		modifyUser(emailAddress, "state", "true");
	}

	public static void deactivateUser(String emailAddress) {
		modifyUser(emailAddress, "state", "false");
	}

	public static void showUsers() {
		for (User user : usersList) {
			System.out.println("ID: " + user.getId());
			System.out.println("Name: " + user.getName() + " " + user.getLastName());
			System.out.println("Last Name: " + user.getLastName());
			System.out.println("ID Type: " + user.getIdType());
			System.out.println("ID Number: " + user.getIdNumber());
			System.out.println("Email Address: " + user.getEmailAddress());
			System.out.println("Phone Number: " + user.getPhoneNumber());
			System.out.println("Active: " + (user.getState() ? "Yes" : "No"));
			System.out.println("-------------------------");
		}
	}
}

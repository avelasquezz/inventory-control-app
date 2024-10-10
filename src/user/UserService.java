package user;

public class UserService {
	public static boolean validateUser(String emailAddress, String password) {
		User user = UserRepository.searchUserByEmail(emailAddress);
		if (user.isNullUser()) {
			return false;
		}
		return user.getPassword().equals(password);
	}
}

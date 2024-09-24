package user;

import user.User;
import user.UserRepository;

public class UserService {
	private UserRepository UserRepositoryInstance;

	public UserService() {
		this.UserRepositoryInstance = new UserRepository();
	}

	public static boolean validateUser(String emailAddress, String password) {
		User user = UserRepository.searchUserByEmail(emailAddress);
		if (user.isNullUser()) {
			return false;
		}
		return user.getPassword().equals(password);
	}
}

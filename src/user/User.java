package user;

public class User {
	private int id;
	private String name;
	private String lastName;
	private String idType;
	private String idNumber;
	private String emailAddress;
	private String phoneNumber;
	private String password;
	private boolean isActive;

	// Creating null user using the constructor.
	// Null user id is -1 and the rest of the attributes have default values.
	public User() {
		this.id = -1;
	}

	public User(int id, String name, String lastName, String idType, String idNumber, String emailAddress, String phoneNumber, String password, boolean isActive) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.idType = idType;
		this.idNumber = idNumber;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.isActive = isActive;
	}

	public boolean isNullUser() {
		return id == -1;
	}

	// Getter methods.
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}
	
	public String getIdType() {
		return idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public boolean getState() {
		return isActive;
	}

	// Setter methods.
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void setIdType(String idType) {
		this.idType = idType;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setState(boolean isActive) {
		this.isActive = isActive;
	}
}

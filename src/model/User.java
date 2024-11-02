package model;

public class User {
	private String idType;
	private String idNumber;
	private String name;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String password;
	private boolean isActive;

	public User(String idType, String idNumber, String name, String lastName, String phoneNumber, String
    emailAddress, String password, boolean isActive) {
		this.idType = idType;
		this.idNumber = idNumber;
		this.name = name;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.password = password;
		this.isActive = isActive;
	}

	// Getter methods.
    public String getIdType() {
        return this.idType;
    }

    public String getIdNumber() {
        return this.idNumber;
    }

	public String getName() {
		return this.name;
	}

	public String getLastName() {
		return this.lastName;
	}

    public String getPhoneNumber() {
        return this.phoneNumber;
    }
	
	public String getEmailAddress() {
		return this.emailAddress;
	}

	public String getPassword() {
		return this.password;
	}

	public boolean getState() {
		return this.isActive;
	}

	// Setter methods.
    public void setIdType(String idType) {
        this.idType = idType;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
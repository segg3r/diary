package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "users")
public class User extends Item {

	public static final String EMAIL_COLUMN = "email";
	public static final String FIRST_NAME_COLUMN = "firstName";
	public static final String LAST_NAME_COLUMN = "lastName";
	public static final String PASSWORD_COLUMN = "password";

	@Column(name = EMAIL_COLUMN)
	private String email;
	@Column(name = FIRST_NAME_COLUMN)
	private String firstName;
	@Column(name = LAST_NAME_COLUMN)
	private String lastName;
	@Column(name = PASSWORD_COLUMN)
	private String password;
	@Transient
	private String confirmPassword;

	public User() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

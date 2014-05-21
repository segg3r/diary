package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User extends Item {

	public static final String EMAIL_COLUMN = "email";
	public static final String FIRST_NAME_COLUMN = "firstName";
	public static final String LAST_NAME_COLUMN = "lastName";
	public static final String PASSWORD_COLUMN = "password";

	@Email(message = "Wrong email address")
	@Column(name = EMAIL_COLUMN)
	private String email;
	@NotEmpty(message = "First name should not be empty")
	@Column(name = FIRST_NAME_COLUMN)
	private String firstName;
	@NotEmpty(message = "Last name should not be empty")
	@Column(name = LAST_NAME_COLUMN)
	private String lastName;
	@Size(min = 5, message = "Password should contain at least 5 characters")
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

	public String getFullName() {
		return firstName + " " + lastName;
	}

}

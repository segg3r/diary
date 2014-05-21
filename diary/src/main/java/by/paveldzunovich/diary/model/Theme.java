package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "themes")
public class Theme extends Item {

	private static final String DEFAULT_THEME_NAME = "First theme";

	public static final String USER_COLUMN = "user.id";
	public static final String NAME_COLUMN = "name";
	
	private static final String THEME_MESSAGE = "Theme name is too short";
	
	@ManyToOne
	private User user;
	@Column(name = NAME_COLUMN)
	@NotEmpty(message = THEME_MESSAGE)
	@Size(min = 3, message = THEME_MESSAGE)
	private String name;

	public Theme() {
		super();
	}

	public Theme(User user) {
		super();
		this.user = user;
		this.name = DEFAULT_THEME_NAME;
	}

	public Theme(User user, String name) {
		super();
		this.user = user;
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

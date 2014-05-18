package by.paveldzunovich.diary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notes")
public class Note extends Item {

	public static final String USER_COLUMN = "user.id";
	public static final String NAME_COLUMN = "name";
	public static final String DESCRIPTION_COLUMN = "description";
	public static final String PRIORITY_COLUMN = "priority.id";
	public static final String THEME_COLUMN = "theme.id";
	public static final String PUBLISHED_COLUMN = "published";

	@Column(name = NAME_COLUMN)
	private String name;
	@Column(name = DESCRIPTION_COLUMN)
	private String description;
	@ManyToOne
	private Priority priority;
	@ManyToOne
	private User user;
	@ManyToOne
	private Theme theme;
	@Column(name = PUBLISHED_COLUMN, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date published;

	public Note() {
		super();
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

}

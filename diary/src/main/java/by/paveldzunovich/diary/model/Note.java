package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Note extends Item {

	public static final String USER_COLUMN = "user.id";
	public static final String NAME_COLUMN = "name";
	public static final String DESCRIPTION_COLUMN = "description";
	public static final String PRIORITY_COLUMN = "priority.id";

	@Column(name = NAME_COLUMN)
	private String name;
	@Column(name = DESCRIPTION_COLUMN)
	private String description;
	@ManyToOne
	private Priority priority;
	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Note() {
		super();
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

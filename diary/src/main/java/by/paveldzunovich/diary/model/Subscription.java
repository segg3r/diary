package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription extends Item {

	public static final String USER_COLUMN = "user.id";
	public static final String THEME_COLUMN = "theme.id";
	public static final String UNREAD_COLUMN = "unread";

	@ManyToOne
	private User user;
	@ManyToOne
	private Theme theme;
	@Column(name = UNREAD_COLUMN)
	private int unread;

	public Subscription() {
		super();
	}


	public Subscription(User user, Theme theme) {
		super();
		this.user = user;
		this.theme = theme;
	}

	public int getUnread() {
		return unread;
	}

	public void setUnread(int unread) {
		this.unread = unread;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	public void addUnread() {
		this.unread++;
	}

}

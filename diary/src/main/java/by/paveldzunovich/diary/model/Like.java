package by.paveldzunovich.diary.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "noteLikes")
public class Like extends Item {

	public final static String NOTE_COLUMN = "note.id";
	public final static String USER_COLUMN = "user.id";

	@ManyToOne
	private Note note;
	@ManyToOne
	private User user;

	public Like() {
		super();
	}

	public Like(Note note, User user) {
		super();
		this.note = note;
		this.user = user;
	}

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "priorities")
public class Priority extends Item {

	public static final String NAME_COLUMN = "name";

	@Column(name = NAME_COLUMN)
	private String name;

	public Priority() {
		super();
	}

	public Priority(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

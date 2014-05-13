package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "priorities")
public class Priority extends Item {

	public static final String NAME_COLUMN = "name";
	public static final String COLOR_COLUMN = "color";

	@Column(name = NAME_COLUMN)
	private String name;
	@Column(name = COLOR_COLUMN)
	private String color;

	public Priority() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}

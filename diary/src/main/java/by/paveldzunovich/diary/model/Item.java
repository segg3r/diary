package by.paveldzunovich.diary.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Item implements Comparable<Item> {

	public static final String ID_COLUMN = "id";

	@Id
	@Column(name = ID_COLUMN)
	@GeneratedValue
	private int id;

	public Item() {
		super();
	}

	public Item(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isNew() {
		return id == 0;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Item))
			return false;
		if (!(obj.getClass().equals(getClass())))
			return false;

		Item item = (Item) obj;
		return id == item.id;
	}

	public int compareTo(Item item) {
		return id - item.id;
	}

}

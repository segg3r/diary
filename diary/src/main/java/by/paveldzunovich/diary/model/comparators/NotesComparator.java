package by.paveldzunovich.diary.model.comparators;

import java.util.Comparator;

import by.paveldzunovich.diary.model.Note;

public class NotesComparator implements Comparator<Note> {

	public int compare(Note n1, Note n2) {
		return n1.getPublished().after(n2.getPublished()) ? -1 : 1;
	}

}

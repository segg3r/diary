package by.paveldzunovich.diary.model.comparators;

import java.util.Comparator;

import by.paveldzunovich.diary.model.Priority;

public class PriorityComparator implements Comparator<Priority> {

	private static enum PriorityEnum {
		NORMAL, IMPORTANT;
	}

	public int compare(Priority o1, Priority o2) {
		return ordinal(o1) - ordinal(o2);
	}

	private static int ordinal(Priority priority) {
		return PriorityEnum.valueOf(priority.getName().toUpperCase()).ordinal();
	}

}

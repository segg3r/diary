package by.paveldzunovich.diary.web.controllers.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.services.ifaces.PriorityService;

public class PriorityBinder extends PropertyEditorSupport {

	@Autowired
	private PriorityService priorityService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			int id = Integer.valueOf(text);
			Priority priority = priorityService.getById(id);
			setValue(priority);
		} catch (DaoException e) {
			throw new IllegalArgumentException(e);
		}
	}
}

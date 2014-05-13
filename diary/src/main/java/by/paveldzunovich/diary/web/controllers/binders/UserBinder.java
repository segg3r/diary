package by.paveldzunovich.diary.web.controllers.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.UserService;

public class UserBinder extends PropertyEditorSupport {

	@Autowired
	private UserService userService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			int id = Integer.valueOf(text);
			User user = userService.get(id);
			setValue(user);
		} catch (DaoException e) {
			throw new IllegalArgumentException(e);
		}
	}
}

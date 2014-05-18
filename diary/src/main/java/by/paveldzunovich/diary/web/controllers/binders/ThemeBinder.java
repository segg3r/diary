package by.paveldzunovich.diary.web.controllers.binders;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.services.ifaces.ThemeService;

public class ThemeBinder extends PropertyEditorSupport {

	@Autowired
	private ThemeService themeService;

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			int id = Integer.valueOf(text);
			Theme theme = themeService.getById(id);
			setValue(theme);
		} catch (DaoException e) {
			throw new IllegalArgumentException(e);
		}
	}
}

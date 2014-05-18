package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;

public interface ThemeService {

	void add(Theme theme) throws DaoException;

	List<Theme> getUserThemes(User user) throws DaoException;

	Theme getById(int id) throws DaoException;

	List<Theme> searchThemes(String text) throws DaoException;

}

package by.paveldzunovich.diary.services.impl;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.ThemeService;

public class ThemeServiceImpl implements ThemeService {

	@Autowired
	private ItemDao<Theme> themeDao;

	public void add(Theme theme) throws DaoException {
		themeDao.add(theme);
	}

	public List<Theme> getUserThemes(User user) throws DaoException {
		return themeDao.list(Restrictions.eq(Theme.USER_COLUMN, user.getId()));
	}

	public Theme getById(int id) throws DaoException {
		return themeDao.get(Restrictions.eq(Item.ID_COLUMN, id));
	}

	public List<Theme> searchThemes(String text) throws DaoException {
		return themeDao.list(Restrictions.like(Theme.NAME_COLUMN, text,
				MatchMode.ANYWHERE));
	}

}

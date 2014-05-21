package by.paveldzunovich.diary.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Subscription;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.NoteService;
import by.paveldzunovich.diary.services.ifaces.SubscriptionService;
import by.paveldzunovich.diary.services.ifaces.ThemeService;

public class ThemeServiceImpl implements ThemeService {

	@Autowired
	private ItemDao<Theme> themeDao;
	@Autowired
	private SubscriptionService subscriptionService;
	@Autowired
	private NoteService noteService;


	public void add(Theme theme) throws DaoException {
		themeDao.add(theme);
	}

	public List<Theme> getUserThemes(User user) throws DaoException {
		return themeDao.list(Restrictions.eq(
				Theme.USER_COLUMN, user.getId()));
	}

	public Theme getById(int id) throws DaoException {
		return themeDao.get(Restrictions.eq(Item.ID_COLUMN, id));
	}

	public List<Theme> searchThemes(User user, String text) throws DaoException {
		List<Theme> themes = themeDao.list(Restrictions.like(Theme.NAME_COLUMN,
				text,
				MatchMode.ANYWHERE).ignoreCase());
		List<Theme> userThemes = getUserThemes(user);
		List<Subscription> subscriptions = subscriptionService
				.getUserSubscriptions(user);
		List<Theme> subscriptionThemes = new ArrayList<Theme>();
		for (Subscription subscription : subscriptions) {
			subscriptionThemes.add(subscription.getTheme());
		}

		List<Theme> result = new ArrayList<Theme>();
		for (Theme theme : themes) {
			if (!userThemes.contains(theme)
					&& !subscriptionThemes.contains(theme)) {
				result.add(theme);
			}
		}
		return result;
	}

	public void delete(Theme theme) throws DaoException {
		List<Subscription> subscriptions = subscriptionService
				.getThemeSubscriptions(theme);
		for (Subscription subscription : subscriptions) {
			subscriptionService.deleteSubscription(subscription);
		}

		List<Note> notes = noteService.getThemeNotes(theme);
		for (Note note : notes) {
			noteService.deleteNote(note);
		}

		themeDao.delete(theme);
	}

}

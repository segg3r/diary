package by.paveldzunovich.diary.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Subscription;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.model.comparators.NotesComparator;
import by.paveldzunovich.diary.services.ifaces.NoteService;
import by.paveldzunovich.diary.services.ifaces.SubscriptionService;
import by.paveldzunovich.diary.services.ifaces.ThemeService;

public class NoteServiceImpl implements NoteService {

	@Autowired
	private ItemDao<Note> noteDao;
	@Autowired
	private ThemeService themeService;
	@Autowired
	private NotesComparator notesComparator;
	@Autowired
	private SubscriptionService subscriptionService;

	public void add(Note note) throws DaoException {
		noteDao.add(note);

		List<Subscription> subscriptions = subscriptionService
				.getThemeSubscriptions(note.getTheme());
		for (Subscription subscription : subscriptions) {
			subscription.addUnread();
			subscriptionService.save(subscription);
		}
	}

	public List<Note> getThemeNotes(Theme theme) throws DaoException {
		List<Note> result = noteDao.list(Restrictions.eq(Note.THEME_COLUMN,
				theme.getId()));
		Collections.sort(result, notesComparator);
		return result;
	}

	public List<Note> getNotes(User user) throws DaoException {
		List<Note> result = new ArrayList<Note>();

		List<Theme> userThemes = themeService.getUserThemes(user);
		for (Theme theme : userThemes) {
			result.addAll(getThemeNotes(theme));
		}

		List<Subscription> subscriptions = subscriptionService
				.getUserSubscriptions(user);
		for (Subscription subscription : subscriptions) {
			result.addAll(getThemeNotes(subscription.getTheme()));
		}

		Collections.sort(result, notesComparator);
		return result;
	}

	public void deleteNote(Note note) throws DaoException {
		noteDao.delete(note);
	}

	public Note get(int id) throws DaoException {
		return noteDao.get(Restrictions.eq(Item.ID_COLUMN, id));
	}

}

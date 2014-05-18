package by.paveldzunovich.diary.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.NoteService;

public class NoteServiceImpl implements NoteService {

	@Autowired
	private ItemDao<Note> noteDao;

	public void add(Note note) throws DaoException {
		noteDao.add(note);
	}

	public List<Note> getUserNotes(User user) throws DaoException {
		return noteDao.list(Restrictions.eq(Note.USER_COLUMN, user.getId()));
	}

}

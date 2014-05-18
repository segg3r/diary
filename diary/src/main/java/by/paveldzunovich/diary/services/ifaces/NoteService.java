package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;

public interface NoteService {

	void add(Note note) throws DaoException;

	List<Note> getThemeNotes(Theme theme) throws DaoException;

	List<Note> getNotes(User user) throws DaoException;


}

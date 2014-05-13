package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.User;

public interface NoteService {

	void add(Note note) throws DaoException;

	List<Note> getUserNotes(User user) throws DaoException;

}

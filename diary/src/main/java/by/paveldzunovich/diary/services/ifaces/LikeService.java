package by.paveldzunovich.diary.services.ifaces;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Like;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.User;

public interface LikeService {

	void add(Like like) throws DaoException;

	void delete(Like like) throws DaoException;

	Like get(User user, Note note) throws DaoException;

}

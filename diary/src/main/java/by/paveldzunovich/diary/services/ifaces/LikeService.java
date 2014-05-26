package by.paveldzunovich.diary.services.ifaces;

import java.util.List;
import java.util.Map;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Like;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;

public interface LikeService {

	void add(Like like) throws DaoException;

	void delete(Like like) throws DaoException;

	Like get(User user, Note note) throws DaoException;

	int getThemeLikeNumber(Theme theme) throws DaoException;

	Map<Theme, Integer> getThemesLikes(List<Theme> themes) throws DaoException;

}

package by.paveldzunovich.diary.services.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Like;
import by.paveldzunovich.diary.model.Note;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.LikeService;


public class LikeServiceImpl implements LikeService {

	@Autowired
	private ItemDao<Like> likeDao;

	public void add(Like like) throws DaoException {
		likeDao.add(like);
	}

	public void delete(Like like) throws DaoException {
		likeDao.delete(like);
	}

	public Like get(User user, Note note) throws DaoException {
		return likeDao.get(Restrictions.eq(Like.USER_COLUMN, user.getId()),
				Restrictions.eq(Like.NOTE_COLUMN, note.getId()));
	}

}

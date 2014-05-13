package by.paveldzunovich.diary.services.impl;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.UserService;

public class UserServiceImpl implements UserService {

	private static final String USER_ALREADY_EXISTS = "User already exists.";

	@Autowired
	private ItemDao<User> userDao;

	public void add(User user) throws DaoException {
		User userWithSameEmail = userDao.get(Restrictions.eq(User.EMAIL_COLUMN,
				user.getEmail()));

		if (userWithSameEmail != null) {
			throw new DaoException(USER_ALREADY_EXISTS);
		}
		userDao.add(user);
	}

	public User get(String email, String password) throws DaoException {
		User user = userDao.get(Restrictions.eq(User.EMAIL_COLUMN, email),
				Restrictions.eq(User.PASSWORD_COLUMN, password));
		if (user == null) {
			throw new DaoException("Wrong email/password combination.");
		}
		return user;
	}

	public User get(int id) throws DaoException {
		return userDao.get(Restrictions.eq(Item.ID_COLUMN, id));
	}

}

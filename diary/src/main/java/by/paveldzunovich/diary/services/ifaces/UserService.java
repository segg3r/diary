package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.User;

public interface UserService {

	void add(User user) throws DaoException;

	User get(String email, String password) throws DaoException;

	User get(int id) throws DaoException;

	List<User> search(String text) throws DaoException;

}

package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Subscription;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;

public interface SubscriptionService {

	List<Subscription> getUserSubscriptions(User user) throws DaoException;

	Subscription get(User user, Theme theme) throws DaoException;
	
	void addSubscription(Subscription subscription) throws DaoException;

	void deleteSubscription(Subscription subscription) throws DaoException;

	List<Subscription> getThemeSubscriptions(Theme theme) throws DaoException;

	void save(Subscription subscription) throws DaoException;

	void readTheme(User user, Theme theme) throws DaoException;

}

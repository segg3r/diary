package by.paveldzunovich.diary.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Subscription;
import by.paveldzunovich.diary.model.Theme;
import by.paveldzunovich.diary.model.User;
import by.paveldzunovich.diary.services.ifaces.SubscriptionService;

public class SubscriptionServiceImpl implements SubscriptionService {

	@Autowired
	private ItemDao<Subscription> subscriptionDao;

	public List<Subscription> getUserSubscriptions(User user)
			throws DaoException {
		return subscriptionDao.list(Restrictions.eq(Subscription.USER_COLUMN,
				user.getId()));
	}

	public Subscription get(User user, Theme theme) throws DaoException {
		return subscriptionDao.get(
				Restrictions.eq(Subscription.USER_COLUMN, user.getId()),
				Restrictions.eq(Subscription.THEME_COLUMN, theme.getId()));
	}

	public void addSubscription(Subscription subscription) throws DaoException {
		subscriptionDao.add(subscription);
	}

	public void deleteSubscription(Subscription subscription)
			throws DaoException {
		subscriptionDao.delete(subscription);
	}

	public List<Subscription> getThemeSubscriptions(Theme theme)
			throws DaoException {
		return subscriptionDao.list(Restrictions.eq(Subscription.THEME_COLUMN,
				theme.getId()));
	}

	public void save(Subscription subscription) throws DaoException {
		subscriptionDao.update(subscription);
	}

	public void readTheme(User user, Theme theme) throws DaoException {
		Subscription subscription = get(user, theme);
		if (subscription != null) {
			subscription.setUnread(0);
			save(subscription);
		}
	}

}

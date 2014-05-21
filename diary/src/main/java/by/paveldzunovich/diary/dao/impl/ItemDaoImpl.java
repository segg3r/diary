package by.paveldzunovich.diary.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.hibernate.HibernateSession;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;

public class ItemDaoImpl<T extends Item> implements ItemDao<T> {

	private Class<T> clazz;

	public ItemDaoImpl(Class<T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	public List<T> list(Criterion... criterions) throws DaoException {
		try {
			Session session = HibernateSession.get();

			Criteria criteria = session.createCriteria(clazz);
			for (Criterion criterion : criterions) {
				criteria.add(criterion);
			}

			List<T> list = criteria.list();
			session.close();

			Set<T> uniqueSet = new TreeSet<T>();
			for (T item : list) {
				uniqueSet.add(item);
			}
			List<T> result = new ArrayList<T>();
			result.addAll(uniqueSet);
			return result;
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	public void add(T item) throws DaoException {
		try {
			Session session = HibernateSession.get();

			Transaction transaction = session.beginTransaction();

			session.save(item);
			transaction.commit();

			session.close();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	public void update(T item) throws DaoException {
		try {
			Session session = HibernateSession.get();

			Transaction transaction = session.beginTransaction();

			session.update(item);
			transaction.commit();

			session.close();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	public void delete(T item) throws DaoException {
		try {
			Session session = HibernateSession.get();

			Transaction transaction = session.beginTransaction();

			session.delete(item);
			transaction.commit();

			session.close();
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

	public T get(Criterion... criterions) throws DaoException {
		try {
			return list(criterions).get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		} catch (HibernateException e) {
			throw new DaoException(e);
		}
	}

}

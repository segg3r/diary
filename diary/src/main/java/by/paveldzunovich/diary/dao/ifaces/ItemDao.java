package by.paveldzunovich.diary.dao.ifaces;

import java.util.List;

import org.hibernate.criterion.Criterion;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Item;

public interface ItemDao<T extends Item> {

	List<T> list(Criterion... criterions) throws DaoException;

	T get(Criterion... criterions) throws DaoException;

	void add(T item) throws DaoException;

	void update(T item) throws DaoException;

	void delete(T item) throws DaoException;

}

package by.paveldzunovich.diary.services.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.dao.ifaces.ItemDao;
import by.paveldzunovich.diary.model.Item;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.services.ifaces.PriorityService;

public class PriorityServiceImpl implements PriorityService {

	@Autowired
	private ItemDao<Priority> priorityDao;

	public void initializeList(List<Priority> priorities) throws DaoException {
		List<Priority> existing = priorityDao.list();
		if (existing.isEmpty()) {
			for (Priority priority : priorities) {
				priorityDao.add(priority);
			}
		}
	}

	public Priority getById(int id) throws DaoException {
		return priorityDao.get(Restrictions.eq(Item.ID_COLUMN, id));
	}

	public List<Priority> list() throws DaoException {
		return priorityDao.list();
	}

}

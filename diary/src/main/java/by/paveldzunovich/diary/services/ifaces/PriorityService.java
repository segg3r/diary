package by.paveldzunovich.diary.services.ifaces;

import java.util.List;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Priority;

public interface PriorityService {

	void initializeList(List<Priority> priorities) throws DaoException;

	Priority getById(int id) throws DaoException;

	List<Priority> list() throws DaoException;

}

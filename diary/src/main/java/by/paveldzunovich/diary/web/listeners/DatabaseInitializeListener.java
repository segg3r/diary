package by.paveldzunovich.diary.web.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.services.ifaces.PriorityService;

public class DatabaseInitializeListener implements ServletContextListener {

	Logger log = Logger.getLogger(DatabaseInitializeListener.class);

	private static final List<Priority> PRIORITIES;
	static {
		PRIORITIES = new ArrayList<Priority>();
		PRIORITIES.add(new Priority("important", "red"));
		PRIORITIES.add(new Priority("normal", "black"));
	}

	public void contextDestroyed(ServletContextEvent ev) {

	}

	public void contextInitialized(ServletContextEvent ev) {
		try {
			WebApplicationContext servletContext = WebApplicationContextUtils
				.getWebApplicationContext(ev.getServletContext());

			PriorityService priorityService = (PriorityService) servletContext
				.getBean("priorityService");
			priorityService.initializeList(PRIORITIES);
		} catch (DaoException e) {
			log.error("Error initializing database. ", e);
		}
	}

}

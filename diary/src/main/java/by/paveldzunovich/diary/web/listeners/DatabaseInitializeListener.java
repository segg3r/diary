package by.paveldzunovich.diary.web.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import by.paveldzunovich.diary.dao.exceptions.DaoException;
import by.paveldzunovich.diary.model.Priority;
import by.paveldzunovich.diary.services.ifaces.PriorityService;

public class DatabaseInitializeListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ev) {

	}

	public void contextInitialized(ServletContextEvent ev) {
		WebApplicationContext servletContext = WebApplicationContextUtils
				.getWebApplicationContext(ev.getServletContext());

		PriorityService priorityService = (PriorityService) servletContext
				.getBean("priorityService");

		try {
			List<Priority> list = new ArrayList<Priority>();

			Priority important = new Priority();
			important.setColor("red");
			important.setName("important");

			Priority normal = new Priority();
			normal.setColor("black");
			normal.setName("normal");

			list.add(important);
			list.add(normal);

			priorityService.initializeList(list);
		} catch (DaoException e) {
			System.out.println("Fail.");
		}
	}

}

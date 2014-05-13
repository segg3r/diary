package by.paveldzunovich.diary.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * The Class DB.
 */
public class HibernateSession {

	private static final SessionFactory SESSION_FACTORY;

	static {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.applySettings(configuration.getProperties());

		ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
		SESSION_FACTORY = configuration.buildSessionFactory(serviceRegistry);
	}

	/**
	 * Gets the session.
	 * 
	 * @return the session
	 */
	public static Session get() {
		return SESSION_FACTORY.openSession();
	}

}

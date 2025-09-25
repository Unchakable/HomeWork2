package model;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class SessionFactoryHelper {
    private static final Logger logger = LoggerFactory.getLogger(SessionFactoryHelper.class);
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null){
            logger.info("Initializing Hibernate SessionFactory");
            try {
                Configuration configuration = new Configuration();
                sessionFactory = configuration.configure().buildSessionFactory();
            } catch (Exception e){
                logger.error("Failed to initialize Hibernate SessionFactory", e);
            }
        }
        logger.info("Hibernate SessionFactory initialized successfully");
        return sessionFactory;
    }

    public static void shutdown() {
        logger.info("Shutting down Hibernate SessionFactory");
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
        }
    }

    public static boolean isAvailable() {
        return sessionFactory != null && !sessionFactory.isClosed();
    }
}
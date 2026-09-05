package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
 
 private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);
 private static SessionFactory sessionFactory;
 
 static {
     try {
         // Create the SessionFactory from hibernate.cfg.xml
         Configuration configuration = new Configuration();
         configuration.configure("hibernate.cfg.xml");
         configuration.addAnnotatedClass(entity.Book.class);        // If package is "entity"
         configuration.addAnnotatedClass(entity.Author.class);
         configuration.addAnnotatedClass(entity.Category.class);
         configuration.addAnnotatedClass(entity.Member.class);
         configuration.addAnnotatedClass(entity.BorrowRecord.class);
         ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                 .applySettings(configuration.getProperties())
                 .build();
         
         sessionFactory = configuration.buildSessionFactory(serviceRegistry);
         logger.info("Hibernate SessionFactory created successfully");
         
     } catch (Throwable ex) {
         logger.error("Initial SessionFactory creation failed", ex);
         throw new ExceptionInInitializerError(ex);
     }
 }
 
 public static SessionFactory getSessionFactory() {
     return sessionFactory;
 }
 
 public static void shutdown() {
     if (sessionFactory != null) {
         logger.info("Closing Hibernate SessionFactory");
         sessionFactory.close();
     }
 }
}
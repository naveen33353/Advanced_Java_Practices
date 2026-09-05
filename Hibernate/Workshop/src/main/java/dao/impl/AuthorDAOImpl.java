package dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.AuthorDAO;
import entity.Author;

import java.util.List;

public class AuthorDAOImpl extends GenericDAOImpl<Author, Long> implements AuthorDAO {
 
 private static final Logger logger = LoggerFactory.getLogger(AuthorDAOImpl.class);
 
 @Override
 public List<Author> findByName(String name) {
     Session session = null;
     try {
         session = openSession();
         
         // Using named query defined in Author entity
         Query<Author> query = session.createNamedQuery("Author.findByName", Author.class);
         query.setParameter("name", "%" + name + "%");
         
         List<Author> authors = query.getResultList();
         logger.debug("Found {} authors with name containing: {}", authors.size(), name);
         return authors;
         
     } catch (Exception e) {
         logger.error("Error finding authors by name: {}", name, e);
         throw new RuntimeException("Failed to find authors by name", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public Author findByEmail(String email) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Author a WHERE a.email = :email";
         Query<Author> query = session.createQuery(hql, Author.class);
         query.setParameter("email", email);
         
         Author author = query.uniqueResult();
         logger.debug("Found author by email {}: {}", email, author);
         return author;
         
     } catch (Exception e) {
         logger.error("Error finding author by email: {}", email, e);
         throw new RuntimeException("Failed to find author by email", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Author> findActiveAuthors() {
     Session session = null;
     try {
         session = openSession();
         
         // Using named query
         Query<Author> query = session.createNamedQuery("Author.findActiveAuthors", Author.class);
         
         List<Author> authors = query.getResultList();
         logger.debug("Found {} active authors", authors.size());
         return authors;
         
     } catch (Exception e) {
         logger.error("Error finding active authors", e);
         throw new RuntimeException("Failed to find active authors", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Author> findByNationality(String nationality) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Author a WHERE LOWER(a.nationality) = LOWER(:nationality) AND a.isActive = true";
         Query<Author> query = session.createQuery(hql, Author.class);
         query.setParameter("nationality", nationality);
         
         List<Author> authors = query.getResultList();
         logger.debug("Found {} authors with nationality: {}", authors.size(), nationality);
         return authors;
         
     } catch (Exception e) {
         logger.error("Error finding authors by nationality: {}", nationality, e);
         throw new RuntimeException("Failed to find authors by nationality", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }

 @Override
 public Author findOneByName(String name) {

	 Session session = null;
	 try {
         session = openSession();
         
         // Using named query defined in Author entity
         Query<Author> query = session.createNamedQuery("Author.findByName", Author.class);
         query.setParameter("name", "%" + name + "%");
         
         Author authors = query.getSingleResult();
         logger.debug("Found {} authors with name containing: {}", authors.getFirstName());
         return authors;
         
     } catch (Exception e) {
         logger.error("Error finding authors by name: {}", name, e);
         throw new RuntimeException("Failed to find authors by name", e);
     } finally {
         if (session != null) {
             session.close();
         }
     
     }}
}

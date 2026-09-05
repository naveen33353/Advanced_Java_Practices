package dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.GenericDAO;
import util.HibernateUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {
 
 private static final Logger logger = LoggerFactory.getLogger(GenericDAOImpl.class);
 
 private Class<T> entityClass;
 
 @SuppressWarnings("unchecked")
 public GenericDAOImpl() {
     this.entityClass = (Class<T>) ((ParameterizedType) getClass()
             .getGenericSuperclass()).getActualTypeArguments()[0];
 }
 
 protected Session getCurrentSession() {
     return HibernateUtil.getSessionFactory().getCurrentSession();
 }
 
 protected Session openSession() {
     return HibernateUtil.getSessionFactory().openSession();
 }
 
 @Override
 public T save(T entity) {
     Session session = null;
     Transaction transaction = null;
     try {
         session = openSession();
         transaction = session.beginTransaction();
         
         session.save(entity);
         
         transaction.commit();
         logger.debug("Entity saved successfully: {}", entity);
         return entity;
         
     } catch (Exception e) {
         if (transaction != null) {
             transaction.rollback();
         }
         logger.error("Error saving entity: {}", entity, e);
         throw new RuntimeException("Failed to save entity", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public T findById(ID id) {
     Session session = null;
     try {
         session = openSession();
         T entity = session.get(entityClass, id);
         logger.debug("Entity found by ID {}: {}", id, entity);
         return entity;
         
     } catch (Exception e) {
         logger.error("Error finding entity by ID: {}", id, e);
         throw new RuntimeException("Failed to find entity by ID: " + id, e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<T> findAll() {
     Session session = null;
     try {
         session = openSession();
         
         CriteriaBuilder cb = session.getCriteriaBuilder();
         CriteriaQuery<T> cq = cb.createQuery(entityClass);
         Root<T> root = cq.from(entityClass);
         cq.select(root);
         
         List<T> entities = session.createQuery(cq).getResultList();
         logger.debug("Found {} entities of type {}", entities.size(), entityClass.getSimpleName());
         return entities;
         
     } catch (Exception e) {
         logger.error("Error finding all entities of type: {}", entityClass.getSimpleName(), e);
         throw new RuntimeException("Failed to find all entities", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public void update(T entity) {
     Session session = null;
     Transaction transaction = null;
     try {
         session = openSession();
         transaction = session.beginTransaction();
         
         session.update(entity);
         
         transaction.commit();
         logger.debug("Entity updated successfully: {}", entity);
         
     } catch (Exception e) {
         if (transaction != null) {
             transaction.rollback();
         }
         logger.error("Error updating entity: {}", entity, e);
         throw new RuntimeException("Failed to update entity", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public void delete(T entity) {
     Session session = null;
     Transaction transaction = null;
     try {
         session = openSession();
         transaction = session.beginTransaction();
         
         session.delete(entity);
         
         transaction.commit();
         logger.debug("Entity deleted successfully: {}", entity);
         
     } catch (Exception e) {
         if (transaction != null) {
             transaction.rollback();
         }
         logger.error("Error deleting entity: {}", entity, e);
         throw new RuntimeException("Failed to delete entity", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public void deleteById(ID id) {
     T entity = findById(id);
     if (entity != null) {
         delete(entity);
     } else {
         logger.warn("Entity with ID {} not found for deletion", id);
     }
 }
 
 @Override
 public long count() {
     Session session = null;
     try {
         session = openSession();
         
         CriteriaBuilder cb = session.getCriteriaBuilder();
         CriteriaQuery<Long> cq = cb.createQuery(Long.class);
         Root<T> root = cq.from(entityClass);
         cq.select(cb.count(root));
         
         Long count = session.createQuery(cq).getSingleResult();
         logger.debug("Count of {} entities: {}", entityClass.getSimpleName(), count);
         return count;
         
     } catch (Exception e) {
         logger.error("Error counting entities of type: {}", entityClass.getSimpleName(), e);
         throw new RuntimeException("Failed to count entities", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public boolean existsById(ID id) {
     return findById(id) != null;
 }
}

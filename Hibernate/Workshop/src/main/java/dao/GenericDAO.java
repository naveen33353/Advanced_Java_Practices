package dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable> {
 
 /**
  * Save entity to database
  */
 T save(T entity);
 
 /**
  * Find entity by ID
  */
 T findById(ID id);
 
 /**
  * Find all entities
  */
 List<T> findAll();
 
 /**
  * Update existing entity
  */
 void update(T entity);
 
 /**
  * Delete entity
  */
 void delete(T entity);
 
 /**
  * Delete entity by ID
  */
 void deleteById(ID id);
 
 /**
  * Count total number of entities
  */
 long count();
 
 /**
  * Check if entity exists by ID
  */
 boolean existsById(ID id);
}

package dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CategoryDAO;
import entity.Category;

public class CategoryDAOImpl extends GenericDAOImpl<Category, Long> implements CategoryDAO{

	  private static final Logger logger = LoggerFactory.getLogger(CategoryDAOImpl.class);
	    
	    @Override
	    public Category findByName(String name) {
	        Session session = null;
	        try {
	            session = openSession();
	            
	            String hql = "FROM Category c WHERE LOWER(c.name) = LOWER(:name)";
	            Query<Category> query = session.createQuery(hql, Category.class);
	            query.setParameter("name", name);
	            
	            Category category = query.uniqueResult();
	            logger.debug("Found category by name {}: {}", name, category);
	            return category;
	            
	        } catch (Exception e) {
	            logger.error("Error finding category by name: {}", name, e);
	            throw new RuntimeException("Failed to find category by name", e);
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }
	    }
	    
	    @Override
	    public List<Category> findActiveCategories() {
	        Session session = null;
	        try {
	            session = openSession();
	            
	            String hql = "FROM Category c WHERE c.isActive = true ORDER BY c.name";
	            Query<Category> query = session.createQuery(hql, Category.class);
	            
	            List<Category> categories = query.getResultList();
	            logger.debug("Found {} active categories", categories.size());
	            return categories;
	            
	        } catch (Exception e) {
	            logger.error("Error finding active categories", e);
	            throw new RuntimeException("Failed to find active categories", e);
	        } finally {
	            if (session != null) {
	                session.close();
	            }
	        }
	    }

}

package dao;

import java.util.List;

import entity.Category;

public interface CategoryDAO extends GenericDAO<Category,Long>{

	Category findByName(String categoryName);

	Category save(Category category);

	List<Category> findActiveCategories();

}

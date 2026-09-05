package dao;

import java.util.List;

import entity.Author;

public interface AuthorDAO extends GenericDAO<Author, Long> {
 
 List<Author> findByName(String name);
 
 Author findByEmail(String email);
 
 List<Author> findActiveAuthors();
 
 List<Author> findByNationality(String nationality);
 
 Author findOneByName(String name);
}

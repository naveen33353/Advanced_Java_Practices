package dao;

import java.math.BigDecimal;
import java.util.List;

import entity.Book;
import entity.BookStatus;

public interface BookDAO extends GenericDAO<Book, Long> {
 
 /**
  * Find books by title (case-insensitive, partial match)
  */
 List<Book> findByTitle(String title);
 
 /**
  * Find book by ISBN
  */
 Book findByIsbn(String isbn);
 
 /**
  * Find books by author ID
  */
 List<Book> findByAuthorId(Long authorId);
 
 /**
  * Find books by category ID
  */
 List<Book> findByCategoryId(Long categoryId);
 
 /**
  * Find books by status
  */
 List<Book> findByStatus(BookStatus status);
 
 /**
  * Find books within price range
  */
 List<Book> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
 
 /**
  * Find available books for borrowing
  */
 List<Book> findAvailableBooks();
 
 /**
  * Search books by keyword (searches in title, description)
  */
 List<Book> searchBooks(String keyword);
 
 /**
  * Update book status
  */
 void updateStatus(Long bookId, BookStatus status);
}
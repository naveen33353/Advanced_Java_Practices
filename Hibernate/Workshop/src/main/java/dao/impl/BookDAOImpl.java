package dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BookDAO;
import entity.Book;
import entity.BookStatus;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class BookDAOImpl extends GenericDAOImpl<Book, Long> implements BookDAO {
 
 private static final Logger logger = LoggerFactory.getLogger(BookDAOImpl.class);
 
 @Override
 public List<Book> findByTitle(String title) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Book b WHERE LOWER(b.title) LIKE LOWER(:title)";
         Query<Book> query = session.createQuery(hql, Book.class);
         query.setParameter("title", "%" + title + "%");
         
         List<Book> books = query.getResultList();
         logger.debug("Found {} books with title containing: {}", books.size(), title);
         return books;
         
     } catch (Exception e) {
         logger.error("Error finding books by title: {}", title, e);
         throw new RuntimeException("Failed to find books by title", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public Book findByIsbn(String isbn) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Book b WHERE b.isbn = :isbn";
         Query<Book> query = session.createQuery(hql, Book.class);
         query.setParameter("isbn", isbn);
         
         Book book = query.uniqueResult();
         logger.debug("Found book by ISBN {}: {}", isbn, book);
         return book;
         
     } catch (Exception e) {
         logger.error("Error finding book by ISBN: {}", isbn, e);
         throw new RuntimeException("Failed to find book by ISBN", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Book> findByAuthorId(Long authorId) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Book b WHERE b.author.authorId = :authorId";
         Query<Book> query = session.createQuery(hql, Book.class);
         query.setParameter("authorId", authorId);
         
         List<Book> books = query.getResultList();
         logger.debug("Found {} books by author ID: {}", books.size(), authorId);
         return books;
         
     } catch (Exception e) {
         logger.error("Error finding books by author ID: {}", authorId, e);
         throw new RuntimeException("Failed to find books by author", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Book> findByCategoryId(Long categoryId) {
     Session session = null;
     try {
         session = openSession();
         
         String hql = "FROM Book b WHERE b.category.categoryId = :categoryId";
         Query<Book> query = session.createQuery(hql, Book.class);
         query.setParameter("categoryId", categoryId);
         
         List<Book> books = query.getResultList();
         logger.debug("Found {} books in category ID: {}", books.size(), categoryId);
         return books;
         
     } catch (Exception e) {
         logger.error("Error finding books by category ID: {}", categoryId, e);
         throw new RuntimeException("Failed to find books by category", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Book> findByStatus(BookStatus status) {
     Session session = null;
     try {
         session = openSession();
         
         // Using Criteria API for variety
         CriteriaBuilder cb = session.getCriteriaBuilder();
         CriteriaQuery<Book> cq = cb.createQuery(Book.class);
         Root<Book> root = cq.from(Book.class);
         
         cq.select(root).where(cb.equal(root.get("status"), status));
         
         List<Book> books = session.createQuery(cq).getResultList();
         logger.debug("Found {} books with status: {}", books.size(), status);
         return books;
         
     } catch (Exception e) {
         logger.error("Error finding books by status: {}", status, e);
         throw new RuntimeException("Failed to find books by status", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Book> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
     Session session = null;
     try {
         session = openSession();
         
         CriteriaBuilder cb = session.getCriteriaBuilder();
         CriteriaQuery<Book> cq = cb.createQuery(Book.class);
         Root<Book> root = cq.from(Book.class);
         
         Predicate pricePredicate = cb.between(root.get("price"), minPrice, maxPrice);
         cq.select(root).where(pricePredicate);
         
         List<Book> books = session.createQuery(cq).getResultList();
         logger.debug("Found {} books in price range {} - {}", books.size(), minPrice, maxPrice);
         return books;
         
     } catch (Exception e) {
         logger.error("Error finding books by price range: {} - {}", minPrice, maxPrice, e);
         throw new RuntimeException("Failed to find books by price range", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public List<Book> findAvailableBooks() {
     return findByStatus(BookStatus.AVAILABLE);
 }
 
 @Override
 public List<Book> searchBooks(String keyword) {
     Session session = null;
     try {
         session = openSession();
         
         // ✅ FIXED: Use JOIN FETCH to load author and category eagerly
         String hql = "FROM Book b " +
                     "LEFT JOIN FETCH b.author " +
                     "LEFT JOIN FETCH b.category " +
                     "WHERE LOWER(b.title) LIKE LOWER(:keyword) OR " +
                     "LOWER(b.description) LIKE LOWER(:keyword) OR " +
                     "LOWER(b.isbn) LIKE LOWER(:keyword)";
         
         Query<Book> query = session.createQuery(hql, Book.class);
         query.setParameter("keyword", "%" + keyword + "%");
         
         List<Book> books = query.getResultList();
         logger.debug("Found {} books matching keyword: {}", books.size(), keyword);
         return books;
         
     } catch (Exception e) {
         logger.error("Error searching books with keyword: {}", keyword, e);
         throw new RuntimeException("Failed to search books", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }
 
 @Override
 public void updateStatus(Long bookId, BookStatus status) {
     Session session = null;
     Transaction transaction = null;
     try {
         session = openSession();
         transaction = session.beginTransaction();
         
         String hql = "UPDATE Book b SET b.status = :status WHERE b.bookId = :bookId";
         Query query = session.createQuery(hql);
         query.setParameter("status", status);
         query.setParameter("bookId", bookId);
         
         int updatedEntities = query.executeUpdate();
         transaction.commit();
         
         logger.debug("Updated status for {} book(s) with ID: {} to status: {}", 
                     updatedEntities, bookId, status);
         
     } catch (Exception e) {
         if (transaction != null) {
             transaction.rollback();
         }
         logger.error("Error updating book status for ID: {} to {}", bookId, status, e);
         throw new RuntimeException("Failed to update book status", e);
     } finally {
         if (session != null) {
             session.close();
         }
     }
 }

 
}

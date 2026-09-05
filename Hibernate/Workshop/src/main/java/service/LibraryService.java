package service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.AuthorDAO;
import dao.BookDAO;
import dao.BorrowRecordDAO;
import dao.CategoryDAO;
import dao.MemberDAO;
import dao.impl.AuthorDAOImpl;
import dao.impl.BookDAOImpl;
import dao.impl.BorrowRecordDAOImpl;
import dao.impl.CategoryDAOImpl;
import dao.impl.MemberDAOImpl;
import entity.Author;
import entity.Book;
import entity.BookStatus;
import entity.BorrowRecord;
import entity.BorrowStatus;
import entity.Category;
import entity.Member;
import entity.MembershipType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LibraryService {
 
 private static final Logger logger = LoggerFactory.getLogger(LibraryService.class);
 
 private final BookDAO bookDAO;
 private final AuthorDAO authorDAO;
 private final CategoryDAO categoryDAO;
 private final MemberDAO memberDAO;
 private final BorrowRecordDAO borrowRecordDAO;
 
 public LibraryService() {
     this.bookDAO = new BookDAOImpl();
     this.authorDAO = new AuthorDAOImpl();
     this.categoryDAO = new CategoryDAOImpl();
     this.memberDAO = new MemberDAOImpl();
     this.borrowRecordDAO = new BorrowRecordDAOImpl();
 }
 
 // Book Management
 
 public Book addBook(String title, String isbn, BigDecimal price, Author author, Category category) {
     try {
         Book book = new Book(title, isbn, price);
         book.setAuthor(author);
         book.setCategory(category);
         book.setStatus(BookStatus.AVAILABLE);
         
         Book savedBook = bookDAO.save(book);
         logger.info("Book added successfully: {}", savedBook);
         return savedBook;
         
     } catch (Exception e) {
         logger.error("Error adding book: {}", title, e);
         throw new RuntimeException("Failed to add book", e);
     }
 }
 
 public List<Book> searchBooks(String keyword) {
     return bookDAO.searchBooks(keyword);
 }
 
 public List<Book> getAvailableBooks() {
     return bookDAO.findAvailableBooks();
 }
 
 public List<Book> getBooksByCategory(String categoryName) {
     Category category = categoryDAO.findByName(categoryName);
     if (category != null) {
         return bookDAO.findByCategoryId(category.getCategoryId());
     }
     return List.of(); // Empty list
 }
 
 // Author Management
 
 public Author addAuthor(String firstName, String lastName, String email) {
     try {
         // Check if author already exists
         if (email != null && authorDAO.findByEmail(email) != null) {
             throw new IllegalArgumentException("Author with email already exists: " + email);
         }
         
         Author author = new Author(firstName, lastName, email);
         Author savedAuthor = authorDAO.save(author);
         logger.info("Author added successfully: {}", savedAuthor);
         return savedAuthor;
         
     } catch (Exception e) {
         logger.error("Error adding author: {} {}", firstName, lastName, e);
         throw new RuntimeException("Failed to add author", e);
     }
 }
 
 public List<Author> findAuthorsByName(String name) {
     return authorDAO.findByName(name);
 }
 public Author findAuthorByName(String name) {
     return authorDAO.findOneByName(name);
 }
 
 // Member Management
 
 public Member registerMember(String firstName, String lastName, String email, MembershipType membershipType) {
     try {
         // Check if member already exists
         if (memberDAO.findByEmail(email) != null) {
             throw new IllegalArgumentException("Member with email already exists: " + email);
         }
         
         Member member = new Member(firstName, lastName, email);
         member.setMembershipType(membershipType);
         
         Member savedMember = memberDAO.save(member);
         logger.info("Member registered successfully: {}", savedMember);
         return savedMember;
         
     } catch (Exception e) {
         logger.error("Error registering member: {} {}", firstName, lastName, e);
         throw new RuntimeException("Failed to register member", e);
     }
 }
 
 // Borrowing Management
 
 public BorrowRecord borrowBook(Long bookId, Long memberId) {
     try {
         Book book = bookDAO.findById(bookId);
         Member member = memberDAO.findById(memberId);
         
         if (book == null) {
             throw new IllegalArgumentException("Book not found with ID: " + bookId);
         }
         
         if (member == null) {
             throw new IllegalArgumentException("Member not found with ID: " + memberId);
         }
         
         if (book.getStatus() != BookStatus.AVAILABLE) {
             throw new IllegalStateException("Book is not available for borrowing: " + book.getTitle());
         }
         
         // Check member borrowing limits
         long activeBorrows = borrowRecordDAO.countActiveBorrowsByMember(memberId);
         int maxBorrows = getMaxBorrowsForMembershipType(member.getMembershipType());
         
         if (activeBorrows >= maxBorrows) {
             throw new IllegalStateException("Member has reached borrowing limit");
         }
         
         // Create borrow record
         BorrowRecord borrowRecord = new BorrowRecord(book, member);
         
         // Update book status
         book.setStatus(BookStatus.BORROWED);
         
         // Save both entities
         bookDAO.update(book);
         BorrowRecord savedRecord = borrowRecordDAO.save(borrowRecord);
         
         logger.info("Book borrowed successfully: {} by {}", book.getTitle(), member.getFullName());
         return savedRecord;
         
     } catch (Exception e) {
         logger.error("Error borrowing book ID {} by member ID {}", bookId, memberId, e);
         throw new RuntimeException("Failed to borrow book", e);
     }
 }
 
 public BorrowRecord returnBook(Long borrowRecordId) {
     try {
         BorrowRecord borrowRecord = borrowRecordDAO.findById(borrowRecordId);
         
         if (borrowRecord == null) {
             throw new IllegalArgumentException("Borrow record not found: " + borrowRecordId);
         }
         
         if (borrowRecord.getStatus() != BorrowStatus.ACTIVE) {
             throw new IllegalStateException("Book is not currently borrowed");
         }
         
         // Update borrow record
         borrowRecord.setReturnDate(LocalDate.now());
         borrowRecord.setStatus(BorrowStatus.RETURNED);
         
         // Calculate fine if overdue
         if (borrowRecord.isOverdue()) {
             long overdueDays = borrowRecord.getDaysOverdue();
             BigDecimal fine = BigDecimal.valueOf(overdueDays * 0.50); // $0.50 per day
             borrowRecord.setFineAmount(fine);
         }
         
         // Update book status
         Book book = borrowRecord.getBook();
         book.setStatus(BookStatus.AVAILABLE);
         
         // Save both entities
         borrowRecordDAO.update(borrowRecord);
         bookDAO.update(book);
         
         logger.info("Book returned successfully: {} by {}", 
                    book.getTitle(), borrowRecord.getMember().getFullName());
         return borrowRecord;
         
     } catch (Exception e) {
         logger.error("Error returning book for record ID {}", borrowRecordId, e);
         throw new RuntimeException("Failed to return book", e);
     }
 }
 
 public List<BorrowRecord> getOverdueBooks() {
     return borrowRecordDAO.findOverdueBooks();
 }
 
 public List<BorrowRecord> getMemberBorrowHistory(Long memberId) {
     return borrowRecordDAO.findByMemberId(memberId);
 }
 
 // Category Management
 
 public Category addCategory(String name, String description) {
     try {
         // Check if category already exists
         if (categoryDAO.findByName(name) != null) {
             throw new IllegalArgumentException("Category already exists: " + name);
         }
         
         Category category = new Category(name, description);
         Category savedCategory = categoryDAO.save(category);
         logger.info("Category added successfully: {}", savedCategory);
         return savedCategory;
         
     } catch (Exception e) {
         logger.error("Error adding category: {}", name, e);
         throw new RuntimeException("Failed to add category", e);
     }
 }
 
 // Helper methods
 
 private int getMaxBorrowsForMembershipType(MembershipType membershipType) {
     switch (membershipType) {
         case PREMIUM:
             return 10;
         case FACULTY:
             return 15;
         case STUDENT:
             return 5;
         case SENIOR:
             return 8;
         case REGULAR:
         default:
             return 3;
     }
 }
 
 // Reporting methods
 
 public LibraryReport generateLibraryReport() {
     try {
         long totalBooks = bookDAO.count();
         long availableBooks = bookDAO.findAvailableBooks().size();
         long borrowedBooks = bookDAO.findByStatus(BookStatus.BORROWED).size();
         long totalMembers = memberDAO.count();
         long activeMembers = memberDAO.findActiveMembers().size();
         long overdueBooks = borrowRecordDAO.findOverdueBooks().size();
         
         return new LibraryReport(totalBooks, availableBooks, borrowedBooks, 
                                totalMembers, activeMembers, overdueBooks);
         
     } catch (Exception e) {
         logger.error("Error generating library report", e);
         throw new RuntimeException("Failed to generate report", e);
     }
 }

 public Category findCategoryByName(String categoryName) {
	// TODO Auto-generated method stub
	     Category cat = categoryDAO.findByName(categoryName);
	return cat;
 }
}

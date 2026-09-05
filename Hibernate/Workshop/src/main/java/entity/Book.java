package entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "book_id")
 private Long bookId;
 
 @Column(name = "title", nullable = false, length = 200)
 private String title;
 
 @Column(name = "isbn", unique = true, length = 20)
 private String isbn;
 
 @Column(name = "price", precision = 10, scale = 2)
 private BigDecimal price;
 
 @Column(name = "publication_date")
 private LocalDate publicationDate;
 
 @Column(name = "pages")
 private Integer pages;
 
 @Enumerated(EnumType.STRING)
 @Column(name = "status")
 private BookStatus status = BookStatus.AVAILABLE;
 
 @Lob
 @Column(name = "description")
 private String description;
 
 // Many-to-One relationship with Author
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "author_id", foreignKey = @ForeignKey(name = "fk_book_author"))
 private Author author;
 
 // Many-to-One relationship with Category
 @ManyToOne(fetch = FetchType.LAZY)
 @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "fk_book_category"))
 private Category category;
 
 // One-to-Many relationship with BorrowRecord
 @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private Set<BorrowRecord> borrowRecords = new HashSet<>();
 
 // Constructors
 public Book() {}
 
 public Book(String title, String isbn, BigDecimal price) {
     this.title = title;
     this.isbn = isbn;
     this.price = price;
 }
 
 // Getters and Setters
 public Long getBookId() { return bookId; }
 public void setBookId(Long bookId) { this.bookId = bookId; }
 
 public String getTitle() { return title; }
 public void setTitle(String title) { this.title = title; }
 
 public String getIsbn() { return isbn; }
 public void setIsbn(String isbn) { this.isbn = isbn; }
 
 public BigDecimal getPrice() { return price; }
 public void setPrice(BigDecimal price) { this.price = price; }
 
 public LocalDate getPublicationDate() { return publicationDate; }
 public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }
 
 public Integer getPages() { return pages; }
 public void setPages(Integer pages) { this.pages = pages; }
 
 public BookStatus getStatus() { return status; }
 public void setStatus(BookStatus status) { this.status = status; }
 
 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }
 
 public Author getAuthor() { return author; }
 public void setAuthor(Author author) { this.author = author; }
 
 public Category getCategory() { return category; }
 public void setCategory(Category category) { this.category = category; }
 
 public Set<BorrowRecord> getBorrowRecords() { return borrowRecords; }
 public void setBorrowRecords(Set<BorrowRecord> borrowRecords) { this.borrowRecords = borrowRecords; }
 
 // Helper methods for bidirectional relationships
 public void addBorrowRecord(BorrowRecord borrowRecord) {
     borrowRecords.add(borrowRecord);
     borrowRecord.setBook(this);
 }
 
 public void removeBorrowRecord(BorrowRecord borrowRecord) {
     borrowRecords.remove(borrowRecord);
     borrowRecord.setBook(null);
 }
 
 @Override
 public String toString() {
     return "Book{" +
             "bookId=" + bookId +
             ", title='" + title + '\'' +
             ", isbn='" + isbn + '\'' +
             ", price=" + price +
             ", status=" + status +
             '}';
 }
}
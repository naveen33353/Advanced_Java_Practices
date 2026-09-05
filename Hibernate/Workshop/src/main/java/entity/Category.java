package entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "category_id")
 private Long categoryId;
 
 @Column(name = "name", nullable = false, unique = true, length = 100)
 private String name;
 
 @Column(name = "description", length = 500)
 private String description;
 
 @Column(name = "is_active")
 private Boolean isActive = true;
 
 // One-to-Many relationship with Book
 @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private Set<Book> books = new HashSet<>();
 
 // Constructors
 public Category() {}
 
 public Category(String name) {
     this.name = name;
 }
 
 public Category(String name, String description) {
     this.name = name;
     this.description = description;
 }
 
 // Getters and Setters
 public Long getCategoryId() { return categoryId; }
 public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
 
 public String getName() { return name; }
 public void setName(String name) { this.name = name; }
 
 public String getDescription() { return description; }
 public void setDescription(String description) { this.description = description; }
 
 public Boolean getIsActive() { return isActive; }
 public void setIsActive(Boolean isActive) { this.isActive = isActive; }
 
 public Set<Book> getBooks() { return books; }
 public void setBooks(Set<Book> books) { this.books = books; }
 
 // Helper methods
 public void addBook(Book book) {
     books.add(book);
     book.setCategory(this);
 }
 
 public void removeBook(Book book) {
     books.remove(book);
     book.setCategory(null);
 }
 
 @Override
 public String toString() {
     return "Category{" +
             "categoryId=" + categoryId +
             ", name='" + name + '\'' +
             ", description='" + description + '\'' +
             '}';
 }
}
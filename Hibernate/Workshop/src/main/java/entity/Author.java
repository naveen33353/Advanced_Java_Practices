package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
@NamedQueries({
 @NamedQuery(
     name = "Author.findByName",
     query = "SELECT a FROM Author a WHERE LOWER(a.firstName) LIKE LOWER(:name) OR LOWER(a.lastName) LIKE LOWER(:name)"
 ),
 @NamedQuery(
     name = "Author.findActiveAuthors",
     query = "SELECT a FROM Author a WHERE a.isActive = true ORDER BY a.lastName"
 )
})
public class Author {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "author_id")
 private Long authorId;  
 
 @Column(name = "first_name", nullable = false, length = 100)
 private String firstName;
 
 @Column(name = "last_name", nullable = false, length = 100)
 private String lastName;
 
 @Column(name = "email", unique = true, length = 150)
 private String email;
 
 @Column(name = "birth_date")
 private LocalDate birthDate;
 
 @Column(name = "nationality", length = 100)
 private String nationality;
 
 @Lob
 @Column(name = "biography")
 private String biography;
 
 @Column(name = "is_active")
 private Boolean isActive = true;
 
 // One-to-Many relationship with Book
 @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private Set<Book> books = new HashSet<>();
 
 // Constructors
 public Author() {}
 
 public Author(String firstName, String lastName) {
     this.firstName = firstName;
     this.lastName = lastName;
 }
 
 public Author(String firstName, String lastName, String email) {
     this.firstName = firstName;
     this.lastName = lastName;
     this.email = email;
 }
 
 // Getters and Setters
 public Long getAuthorId() { return authorId; }
 public void setAuthorId(Long authorId) { this.authorId = authorId; }
 
 public String getFirstName() { return firstName; }
 public void setFirstName(String firstName) { this.firstName = firstName; }
 
 public String getLastName() { return lastName; }
 public void setLastName(String lastName) { this.lastName = lastName; }
 
 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }
 
 public LocalDate getBirthDate() { return birthDate; }
 public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
 
 public String getNationality() { return nationality; }
 public void setNationality(String nationality) { this.nationality = nationality; }
 
 public String getBiography() { return biography; }
 public void setBiography(String biography) { this.biography = biography; }
 
 public Boolean getIsActive() { return isActive; }
 public void setIsActive(Boolean isActive) { this.isActive = isActive; }
 
 public Set<Book> getBooks() { return books; }
 public void setBooks(Set<Book> books) { this.books = books; }
 
 // Helper methods
 public void addBook(Book book) {
     books.add(book);
     book.setAuthor(this);
 }
 
 public void removeBook(Book book) {
     books.remove(book);
     book.setAuthor(null);
 }
 
 public String getFullName() {
     return firstName + " " + lastName;
 }
 
 @Override
 public String toString() {
     return "Author{" +
             "authorId=" + authorId +
             ", firstName='" + firstName + '\'' +
             ", lastName='" + lastName + '\'' +
             ", email='" + email + '\'' +
             '}';
 }
}
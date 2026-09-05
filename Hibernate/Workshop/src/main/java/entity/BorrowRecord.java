package entity;

import javax.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "borrow_records")
public class BorrowRecord {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "record_id")
 private Long recordId;
 
 @Column(name = "borrow_date", nullable = false)
 private LocalDate borrowDate;
 
 @Column(name = "due_date", nullable = false)
 private LocalDate dueDate;
 
 @Column(name = "return_date")
 private LocalDate returnDate;
 
 @Column(name = "fine_amount", precision = 10, scale = 2)
 private BigDecimal fineAmount = BigDecimal.ZERO;
 
 @Enumerated(EnumType.STRING)
 @Column(name = "status")
 private BorrowStatus status = BorrowStatus.ACTIVE;
 
 @Column(name = "notes", length = 500)
 private String notes;
 
 // Many-to-One relationship with Book
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "fk_borrow_book"))
 private Book book;
 
 // Many-to-One relationship with Member
 @ManyToOne(fetch = FetchType.EAGER)
 @JoinColumn(name = "member_id", nullable = false, foreignKey = @ForeignKey(name = "fk_borrow_member"))
 private Member member;
 
 // Constructors
 public BorrowRecord() {
     this.borrowDate = LocalDate.now();
     this.dueDate = LocalDate.now().plusWeeks(2); // Default 2 weeks
 }
 
 public BorrowRecord(Book book, Member member) {
     this();
     this.book = book;
     this.member = member;
 }
 
 // Getters and Setters
 public Long getRecordId() { return recordId; }
 public void setRecordId(Long recordId) { this.recordId = recordId; }
 
 public LocalDate getBorrowDate() { return borrowDate; }
 public void setBorrowDate(LocalDate borrowDate) { this.borrowDate = borrowDate; }
 
 public LocalDate getDueDate() { return dueDate; }
 public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
 
 public LocalDate getReturnDate() { return returnDate; }
 public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
 
 public BigDecimal getFineAmount() { return fineAmount; }
 public void setFineAmount(BigDecimal fineAmount) { this.fineAmount = fineAmount; }
 
 public BorrowStatus getStatus() { return status; }
 public void setStatus(BorrowStatus status) { this.status = status; }
 
 public String getNotes() { return notes; }
 public void setNotes(String notes) { this.notes = notes; }
 
 public Book getBook() { return book; }
 public void setBook(Book book) { this.book = book; }
 
 public Member getMember() { return member; }
 public void setMember(Member member) { this.member = member; }
 
 // Helper methods
 public boolean isOverdue() {
     return status == BorrowStatus.ACTIVE && LocalDate.now().isAfter(dueDate);
 }
 
 public long getDaysOverdue() {
     if (!isOverdue()) return 0;
     return java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
 }
 
 @Override
 public String toString() {
     return "BorrowRecord{" +
             "recordId=" + recordId +
             ", borrowDate=" + borrowDate +
             ", dueDate=" + dueDate +
             ", status=" + status +
             '}';
 }
}


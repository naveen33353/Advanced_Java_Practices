package service;

public class LibraryReport {
 
 private long totalBooks;
 private long availableBooks;
 private long borrowedBooks;
 private long totalMembers;
 private long activeMembers;
 private long overdueBooks;
 
 public LibraryReport(long totalBooks, long availableBooks, long borrowedBooks,
                     long totalMembers, long activeMembers, long overdueBooks) {
     this.totalBooks = totalBooks;
     this.availableBooks = availableBooks;
     this.borrowedBooks = borrowedBooks;
     this.totalMembers = totalMembers;
     this.activeMembers = activeMembers;
     this.overdueBooks = overdueBooks;
 }
 
 // Getters
 public long getTotalBooks() { return totalBooks; }
 public long getAvailableBooks() { return availableBooks; }
 public long getBorrowedBooks() { return borrowedBooks; }
 public long getTotalMembers() { return totalMembers; }
 public long getActiveMembers() { return activeMembers; }
 public long getOverdueBooks() { return overdueBooks; }
 
 @Override
 public String toString() {
     return "LibraryReport{" +
             "totalBooks=" + totalBooks +
             ", availableBooks=" + availableBooks +
             ", borrowedBooks=" + borrowedBooks +
             ", totalMembers=" + totalMembers +
             ", activeMembers=" + activeMembers +
             ", overdueBooks=" + overdueBooks +
             '}';
 }
}

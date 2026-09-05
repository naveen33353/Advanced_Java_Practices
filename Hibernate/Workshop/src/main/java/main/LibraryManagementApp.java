package main;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.Address;
import entity.Author;
import entity.Book;
import entity.BookStatus;
import entity.BorrowRecord;
import entity.Category;
import entity.Member;
import entity.MembershipType;
import service.LibraryReport;
import service.LibraryService;
import util.HibernateUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class LibraryManagementApp {
 
 private static final Logger logger = LoggerFactory.getLogger(LibraryManagementApp.class);
 private static final LibraryService libraryService = new LibraryService();
 private static final Scanner scanner = new Scanner(System.in);
 
 public static void main(String[] args) {
     logger.info("Starting Library Management System...");
     
     try {
         // Initialize sample data
//         initializeSampleData();
         
         // Start interactive menu
         runInteractiveMenu();
         
     } catch (Exception e) {
         logger.error("Error in main application", e);
     } finally {
         HibernateUtil.shutdown();
         scanner.close();
         logger.info("Library Management System stopped.");
     }
 }
 
 private static void initializeSampleData() { 
     logger.info("Initializing sample data...");
     
     try {
         // Add Categories
         Category fiction = libraryService.addCategory("Fiction", "Fiction books and novels");
         Category nonFiction = libraryService.addCategory("Non-Fiction", "Educational and reference books");
         Category science = libraryService.addCategory("Science", "Science and technology books");
         Category history = libraryService.addCategory("History", "Historical books and biographies");
         
         // Add Authors
         Author author1 = libraryService.addAuthor("George", "Orwell", "george.orwell@email.com");
         author1.setBirthDate(LocalDate.of(1903, 6, 25));
         author1.setNationality("British");
         
         Author author2 = libraryService.addAuthor("J.K.", "Rowling", "jk.rowling@email.com");
         author2.setBirthDate(LocalDate.of(1965, 7, 31));
         author2.setNationality("British");
         
         Author author3 = libraryService.addAuthor("Stephen", "Hawking", "stephen.hawking@email.com");
         author3.setBirthDate(LocalDate.of(1942, 1, 8));
         author3.setNationality("British");
         
         // Add Books
         Book book1 = libraryService.addBook("1984", "978-0-452-28423-4", 
             new BigDecimal("15.99"), author1, fiction);
         book1.setPublicationDate(LocalDate.of(1949, 6, 8));
         book1.setPages(328);
         book1.setDescription("A dystopian social science fiction novel");
         
         Book book2 = libraryService.addBook("Animal Farm", "978-0-452-28424-1", 
             new BigDecimal("12.99"), author1, fiction);
         book2.setPublicationDate(LocalDate.of(1945, 8, 17));
         book2.setPages(112);
         
         Book book3 = libraryService.addBook("Harry Potter and the Philosopher's Stone", "978-0-7475-3269-9", 
             new BigDecimal("19.99"), author2, fiction);
         book3.setPublicationDate(LocalDate.of(1997, 6, 26));
         book3.setPages(223);
         
         Book book4 = libraryService.addBook("A Brief History of Time", "978-0-553-38016-3", 
             new BigDecimal("18.99"), author3, science);
         book4.setPublicationDate(LocalDate.of(1988, 4, 1));
         book4.setPages(256);
         
         // Add Members
         Member member1 = libraryService.registerMember("John", "Doe", "john.doe@email.com", MembershipType.REGULAR);
         member1.setPhone("555-1234");
         Address address1 = new Address("123 Main St", "Springfield", "IL", "62701", "USA");
         member1.setAddress(address1);
         
         Member member2 = libraryService.registerMember("Jane", "Smith", "jane.smith@email.com", MembershipType.PREMIUM);
         member2.setPhone("555-5678");
         Address address2 = new Address("456 Oak Ave", "Springfield", "IL", "62702", "USA");
         member2.setAddress(address2);
         
         Member member3 = libraryService.registerMember("Bob", "Johnson", "bob.johnson@email.com", MembershipType.STUDENT);
         member3.setPhone("555-9012");
         
         logger.info("Sample data initialized successfully!");
         
     } catch (Exception e) {
         logger.error("Error initializing sample data", e);
     }
 }
 
 private static void runInteractiveMenu() {
     while (true) {
         printMainMenu();
         
         int choice = getIntInput("Enter your choice: ");
         
         switch (choice) {
             case 1:
                 searchBooks();
                 break;
             case 2:
                 displayAvailableBooks();
                 break;
             case 3:
                 borrowBook();
                 break;
             case 4:
                 returnBook();
                 break;
             case 5:
                 displayOverdueBooks();
                 break;
             case 6:
                 registerNewMember();
                 break;
             case 7:
                 addNewBook();
                 break;
             case 8:
                 generateReport();
                 break;
             case 9:
                 logger.info("Thank you for using Library Management System!");
                 return;
             default:
                 System.out.println("Invalid choice. Please try again.");
         }
         
         System.out.println("\nPress Enter to continue...");
         scanner.nextLine();
     }
 }
 
 private static void printMainMenu() {
     System.out.println("\n" + "=".repeat(50));
     System.out.println("         LIBRARY MANAGEMENT SYSTEM");
     System.out.println("=".repeat(50));
     System.out.println("1. Search Books");
     System.out.println("2. Display Available Books");
     System.out.println("3. Borrow Book");
     System.out.println("4. Return Book");
     System.out.println("5. Display Overdue Books");
     System.out.println("6. Register New Member");
     System.out.println("7. Add New Book");
     System.out.println("8. Generate Library Report");
     System.out.println("9. Exit");
     System.out.println("=".repeat(50));
 }
 
 private static void searchBooks() {
     System.out.print("Enter search keyword: ");
     String keyword = scanner.nextLine().trim();
     
     if (keyword.isEmpty()) {
         System.out.println("Please enter a valid keyword.");
         return;
     }
     
     try {
         List<Book> books = libraryService.searchBooks(keyword);
         
         if (books.isEmpty()) {
             System.out.println("No books found matching keyword: " + keyword);
         } else {
             System.out.println("\nSearch Results (" + books.size() + " books found):");
             System.out.println("-".repeat(80));
             for (Book book : books) {
                 displayBookInfo(book);
             }
         }
         
     } catch (Exception e) {
         System.out.println("Error searching books: " + e.getMessage());
         logger.error("Error in searchBooks", e);
     }
 }
 
 private static void displayAvailableBooks() {
     try {
         List<Book> books = libraryService.getAvailableBooks();
         
         if (books.isEmpty()) {
             System.out.println("No books are currently available.");
         } else {
             System.out.println("\nAvailable Books (" + books.size() + " books):");
             System.out.println("-".repeat(80));
             for (Book book : books) {
                 displayBookInfo(book);
             }
         }
         
     } catch (Exception e) {
         System.out.println("Error displaying available books: " + e.getMessage());
         logger.error("Error in displayAvailableBooks", e);
     }
 }
 
 private static void borrowBook() {
     try {
         long bookId = getLongInput("Enter Book ID: ");
         long memberId = getLongInput("Enter Member ID: ");
         
         BorrowRecord borrowRecord = libraryService.borrowBook(bookId, memberId);
         
         System.out.println("Book borrowed successfully!");
         System.out.println("Borrow Record ID: " + borrowRecord.getRecordId());
         System.out.println("Book: " + borrowRecord.getBook().getTitle());
         System.out.println("Member: " + borrowRecord.getMember().getFullName());
         System.out.println("Due Date: " + borrowRecord.getDueDate());
         
     } catch (Exception e) {
         System.out.println("Error borrowing book: " + e.getMessage());
         logger.error("Error in borrowBook", e);
     }
 }
 
 private static void returnBook() {
     try {
         long recordId = getLongInput("Enter Borrow Record ID: ");
         
         BorrowRecord borrowRecord = libraryService.returnBook(recordId);
         
         System.out.println("Book returned successfully!");
         System.out.println("Book: " + borrowRecord.getBook().getTitle());
         System.out.println("Member: " + borrowRecord.getMember().getFullName());
         System.out.println("Return Date: " + borrowRecord.getReturnDate());
         
         if (borrowRecord.getFineAmount().compareTo(BigDecimal.ZERO) > 0) {
             System.out.println("Fine Amount: $" + borrowRecord.getFineAmount());
         }
         
     } catch (Exception e) {
         System.out.println("Error returning book: " + e.getMessage());
         logger.error("Error in returnBook", e);
     }
 }
 
 private static void displayOverdueBooks() {
     try {
         List<BorrowRecord> overdueBooks = libraryService.getOverdueBooks();
         
         if (overdueBooks.isEmpty()) {
             System.out.println("No overdue books found.");
         } else {
             System.out.println("\nOverdue Books (" + overdueBooks.size() + " records):");
             System.out.println("-".repeat(100));
             System.out.printf("%-5s %-30s %-25s %-12s %-10s%n", 
                 "ID", "Book Title", "Member", "Due Date", "Days Late");
             System.out.println("-".repeat(100));
             
             for (BorrowRecord record : overdueBooks) {
                 System.out.printf("%-5d %-30s %-25s %-12s %-10d%n",
                     record.getRecordId(),
                     record.getBook().getTitle(),
                     record.getMember().getFullName(),
                     record.getDueDate(),
                     record.getDaysOverdue()
                 );
             }
         }
         
     } catch (Exception e) {
         System.out.println("Error displaying overdue books: " + e.getMessage());
         logger.error("Error in displayOverdueBooks", e);
     }
 }
 
 private static void registerNewMember() {
     try {
         System.out.print("Enter First Name: ");
         String firstName = scanner.nextLine().trim();
         
         System.out.print("Enter Last Name: ");
         String lastName = scanner.nextLine().trim();
         
         System.out.print("Enter Email: ");
         String email = scanner.nextLine().trim();
         
         System.out.println("Select Membership Type:");
         System.out.println("1. REGULAR  2. PREMIUM  3. STUDENT  4. FACULTY  5. SENIOR");
         int typeChoice = getIntInput("Enter choice: ");
         
      
         MembershipType membershipType;
         switch (typeChoice) {
             case 2: membershipType = MembershipType.PREMIUM; break;
             case 3: membershipType = MembershipType.STUDENT; break;
             case 4: membershipType = MembershipType.FACULTY; break;
             case 5: membershipType = MembershipType.SENIOR; break;
             default: membershipType = MembershipType.REGULAR; break;
         }
         
         Member member = libraryService.registerMember(firstName, lastName, email, membershipType);
         
         System.out.println("Member registered successfully!");
         System.out.println("Member ID: " + member.getMemberId());
         System.out.println("Name: " + member.getFullName());
         System.out.println("Email: " + member.getEmail());
         System.out.println("Membership Type: " + member.getMembershipType());
         
     } catch (Exception e) {
         System.out.println("Error registering member: " + e.getMessage());
         logger.error("Error in registerNewMember", e);
     }
 }
 
 private static void addNewBook() {
     try {
         System.out.print("Enter Book Title: ");
         String title = scanner.nextLine().trim();
         
         System.out.print("Enter ISBN: ");
         String isbn = scanner.nextLine().trim();
         
         BigDecimal price = getBigDecimalInput("Enter Price: $");
         
         System.out.print("Enter Author Name: ");
         String authorName = scanner.nextLine().trim();
         Author author = findAuthor(authorName);
         
         System.out.print("Enter Category Name: ");
         String categoryName = scanner.nextLine().trim();
         Category category = findCategory(categoryName);
      
        

         libraryService.addBook(title, isbn, price, author, category) ;
         System.out.println("\nBook added successfully!");
         System.out.println("Title: " + title);
         System.out.println("Author: " + author.getFullName());
         System.out.println("Category: " + category.getName());

         
     } catch (Exception e) {
         System.out.println("Error adding book: " + e.getMessage());
         logger.error("Error in addNewBook", e);
     }
 }
 
 private static Category findCategory(String categoryName) {
	// TODO Auto-generated method stub
	    Category category = libraryService.findCategoryByName(categoryName);
	return category;
}

 private static Author findAuthor(String authorName) {
	// TODO Auto-generated method stub
	 try {
	        // First, try to find existing author
	        Author existingAuthor = libraryService.findAuthorByName(authorName);
	        return existingAuthor;  
	       
	 } catch (Exception e) {
	        throw new RuntimeException("Error handling author: " + e.getMessage(), e);
	    }
	
}

 private static void generateReport() {
     try {
         LibraryReport report = libraryService.generateLibraryReport();
         
         System.out.println("\n" + "=".repeat(40));
         System.out.println("       LIBRARY REPORT");
         System.out.println("=".repeat(40));
         System.out.println("Total Books: " + report.getTotalBooks());
         System.out.println("Available Books: " + report.getAvailableBooks());
         System.out.println("Borrowed Books: " + report.getBorrowedBooks());
         System.out.println("Total Members: " + report.getTotalMembers());
         System.out.println("Active Members: " + report.getActiveMembers());
         System.out.println("Overdue Books: " + report.getOverdueBooks());
         System.out.println("=".repeat(40));
         
     } catch (Exception e) {
         System.out.println("Error generating report: " + e.getMessage());
         logger.error("Error in generateReport", e);
     }
 }
 
 // Helper methods
 
 private static void displayBookInfo(Book book) {
	    // Check if proxy needs initialization
	    if (book.getAuthor() != null && !Hibernate.isInitialized(book.getAuthor())) {
	        // This will throw LazyInitializationException if no session
	        // Better to handle this in the service layer
	    }
	    
	    System.out.printf("ID: %-3d | %-30s | %-20s | %-12s | $%-6.2f | %s%n",
	        book.getBookId(),
	        truncate(book.getTitle(), 30),
	        book.getAuthor() != null ? truncate(book.getAuthor().getFullName(), 20) : "Unknown",
	        book.getIsbn(),
	        book.getPrice(),
	        book.getStatus()
	    );
	}
 
 private static String truncate(String str, int length) {
     if (str == null) return "";
     return str.length() <= length ? str : str.substring(0, length - 3) + "...";
 }
 
 private static int getIntInput(String prompt) {
     while (true) {
         try {
             System.out.print(prompt);
             String input = scanner.nextLine().trim();
             return Integer.parseInt(input);
         } catch (NumberFormatException e) {
             System.out.println("Please enter a valid number.");
         }
     }
 }
 
 private static long getLongInput(String prompt) {
     while (true) {
         try {
             System.out.print(prompt);
             String input = scanner.nextLine().trim();
             return Long.parseLong(input);
         } catch (NumberFormatException e) {
             System.out.println("Please enter a valid number.");
         }
     }
 }
 
 private static BigDecimal getBigDecimalInput(String prompt) {
     while (true) {
         try {
             System.out.print(prompt);
             String input = scanner.nextLine().trim();
             return new BigDecimal(input);
         } catch (NumberFormatException e) {
             System.out.println("Please enter a valid decimal number.");
         }
     }
 }
}

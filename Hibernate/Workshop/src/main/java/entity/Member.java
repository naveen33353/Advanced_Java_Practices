package entity;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {
 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "member_id")
 private Long memberId;
 
 @Column(name = "first_name", nullable = false, length = 100)
 private String firstName;
 
 @Column(name = "last_name", nullable = false, length = 100)
 private String lastName;
 
 @Column(name = "email", unique = true, length = 150)
 private String email;
 
 @Column(name = "phone", length = 20)
 private String phone;
 
 @Column(name = "membership_date", nullable = false)
 private LocalDate membershipDate;
 
 @Enumerated(EnumType.STRING)
 @Column(name = "membership_type")
 private MembershipType membershipType = MembershipType.REGULAR;
 
 @Column(name = "is_active")
 private Boolean isActive = true;
 
 // Embedded Address
 @Embedded
 private Address address;
 
 // One-to-Many relationship with BorrowRecord
 @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 private Set<BorrowRecord> borrowRecords = new HashSet<>();
 
 // Constructors
 public Member() {
     this.membershipDate = LocalDate.now();
 }
 
 public Member(String firstName, String lastName, String email) {
     this();
     this.firstName = firstName;
     this.lastName = lastName;
     this.email = email;
 }
 
 // Getters and Setters
 public Long getMemberId() { return memberId; }
 public void setMemberId(Long memberId) { this.memberId = memberId; }
 
 public String getFirstName() { return firstName; }
 public void setFirstName(String firstName) { this.firstName = firstName; }
 
 public String getLastName() { return lastName; }
 public void setLastName(String lastName) { this.lastName = lastName; }
 
 public String getEmail() { return email; }
 public void setEmail(String email) { this.email = email; }
 
 public String getPhone() { return phone; }
 public void setPhone(String phone) { this.phone = phone; }
 
 public LocalDate getMembershipDate() { return membershipDate; }
 public void setMembershipDate(LocalDate membershipDate) { this.membershipDate = membershipDate; }
 
 public MembershipType getMembershipType() { return membershipType; }
 public void setMembershipType(MembershipType membershipType) { this.membershipType = membershipType; }
 
 public Boolean getIsActive() { return isActive; }
 public void setIsActive(Boolean isActive) { this.isActive = isActive; }
 
 public Address getAddress() { return address; }
 public void setAddress(Address address) { this.address = address; }
 
 public Set<BorrowRecord> getBorrowRecords() { return borrowRecords; }
 public void setBorrowRecords(Set<BorrowRecord> borrowRecords) { this.borrowRecords = borrowRecords; }
 
 // Helper methods
 public void addBorrowRecord(BorrowRecord borrowRecord) {
     borrowRecords.add(borrowRecord);
     borrowRecord.setMember(this);
 }
 
 public void removeBorrowRecord(BorrowRecord borrowRecord) {
     borrowRecords.remove(borrowRecord);
     borrowRecord.setMember(null);
 }
 
 public String getFullName() {
     return firstName + " " + lastName;
 }
 
 @Override
 public String toString() {
     return "Member{" +
             "memberId=" + memberId +
             ", firstName='" + firstName + '\'' +
             ", lastName='" + lastName + '\'' +
             ", email='" + email + '\'' +
             ", membershipType=" + membershipType +
             '}';
 }
}
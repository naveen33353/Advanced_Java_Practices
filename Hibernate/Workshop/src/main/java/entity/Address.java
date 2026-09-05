package entity;


import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
 
 @Column(name = "street", length = 200)
 private String street;
 
 @Column(name = "city", length = 100)
 private String city;
 
 @Column(name = "state", length = 100)
 private String state;
 
 @Column(name = "postal_code", length = 20)
 private String postalCode;
 
 @Column(name = "country", length = 100)
 private String country;
 
 // Constructors
 public Address() {}
 
 public Address(String street, String city, String state, String postalCode, String country) {
     this.street = street;
     this.city = city;
     this.state = state;
     this.postalCode = postalCode;
     this.country = country;
 }
 
 // Getters and Setters
 public String getStreet() { return street; }
 public void setStreet(String street) { this.street = street; }
 
 public String getCity() { return city; }
 public void setCity(String city) { this.city = city; }
 
 public String getState() { return state; }
 public void setState(String state) { this.state = state; }
 
 public String getPostalCode() { return postalCode; }
 public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
 
 public String getCountry() { return country; }
 public void setCountry(String country) { this.country = country; }
 
 @Override
 public String toString() {
     return street + ", " + city + ", " + state + " " + postalCode + ", " + country;
 }
}
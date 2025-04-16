package Domain.Class;

import java.time.LocalDate;
import Domain.Enummeration.Gender;

public class Student {
    private int id;
    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String country;
    private String houseNumber;
    private String postalCode;

    public Student(int id, String email, String name, LocalDate birthDate, Gender gender, String address, String city, String country, String houseNumber, String postalCode) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public Student(String email, String name, LocalDate birthDate, Gender gender, String address,
            String houseNumber, String postalCode, String city, String country) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHouseNumber() { 
        return houseNumber; 
    }

    public void setHouseNumber(String houseNumber) { 
        this.houseNumber = houseNumber; 
    }
    
    public String getPostalCode() { 
        return postalCode; 
    }

    public void setPostalCode(String postalCode) { 
        this.postalCode = postalCode; 
    }
}

package com.project2.haj.helper.Models;

public class User {

    String fullName;
    String mobile;
    String email;
    String country;
    String password;
    String address;
    String birthDate;
    String emergecyNumber;
    String type;

    public User(String fullName, String mobile, String email, String country, String password, String address, String birthDate, String emergecyNumber, String type) {
        this.fullName = fullName;
        this.mobile = mobile;
        this.email = email;
        this.country = country;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.emergecyNumber = emergecyNumber;
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmergecyNumber() {
        return emergecyNumber;
    }

    public void setEmergecyNumber(String emergecyNumber) {
        this.emergecyNumber = emergecyNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

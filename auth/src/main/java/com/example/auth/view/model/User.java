package com.example.auth.view.model;

import com.hbb20.CountryCodePicker;

public class User {
    private String firstName;
    private String lastName;
    private String Email;
    private String Password;
    private CountryCodePicker countryCodePicker;
    private String phoneNumber;
    private String userId;

    public User(String userId, String firstName, String lastName, String email, String password, String countryCodePicker, String phoneNumber) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        Password = password;
        this.countryCodePicker = getCountryCodePicker();
        this.phoneNumber = phoneNumber;
    }

    public User() {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public CountryCodePicker getCountryCodePicker() {
        return countryCodePicker;
    }

    public void setCountryCodePicker(CountryCodePicker countryCodePicker) {
        this.countryCodePicker = countryCodePicker;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

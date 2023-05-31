package com.cs.wasselha.model;


import android.util.Log;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id; // for the database
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;
    private boolean isVerified;
    private Location location;
    private int review;
    private String documentId; // this is for the firebase database

    public Customer(){
        Faker faker = new Faker();
        List<Customer> customers = new ArrayList<>();
        int i=0;
        while (i<30) {
            int id = faker.number().numberBetween(1,1000000);
            String email = faker.internet().emailAddress();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String phoneNumber = faker.phoneNumber().phoneNumber();
            boolean isVerified = true;
            String password = faker.internet().password();

            Customer customer  = new Customer(id,email,
                    firstName,lastName,phoneNumber,
                    isVerified,password);
            customers.add(customer);

            i++;

        }

        Log.d("customers",customers.toString());
    }

    public Customer(int id,String email, String firstName, String lastName, String phoneNumber,
                    boolean isVerified,String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
        this.password = password;

    }
    public Customer(String email, String firstName, String lastName, String phoneNumber,
                    boolean isVerified, Location location, int review) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
        this.location = location;
        this.review = review;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public Location getLocation() {
        return location;
    }

    public int getReview() {
        return review;
    }

   /* @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }*/

    @Override
    public String toString() {
        return "Customer{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isVerified=" + isVerified +
                ", location=" + location +
                ", review=" + review +
                '}';
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;

    }

    public String getDocumentId() {
        return this.documentId;
    }
}
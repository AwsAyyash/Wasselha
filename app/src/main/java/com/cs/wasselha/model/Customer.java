package com.cs.wasselha.model;


public class Customer {
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private boolean isVerified;
    private Location location;
    private int review;

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

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
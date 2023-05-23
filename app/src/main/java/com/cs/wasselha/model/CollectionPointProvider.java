package com.cs.wasselha.model;

public class CollectionPointProvider {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;
    private String nationalId;
    private int review;

    public CollectionPointProvider(String firstName, String lastName, String email, String password,
                                   String phoneNumber, boolean isVerified, String nationalId, int review) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
        this.nationalId = nationalId;
        this.review = review;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public String getNationalId() {
        return nationalId;
    }

    public int getReview() {
        return review;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ") (" + nationalId + ")";
    }
}

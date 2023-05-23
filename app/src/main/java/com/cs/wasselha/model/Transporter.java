package com.cs.wasselha.model;


import java.io.File;


public class Transporter {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isVerified;

    private String status;
    private String nationalId;
    private File drivingLicense;


    public Transporter() {
    }

    public Transporter(String firstName, String lastName, String email, String phoneNumber,
                       String password, String nationalId, File drivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nationalId = nationalId;
        this.drivingLicense = drivingLicense;
    }

    public Transporter(String firstName, String lastName, String email, String phoneNumber,
                       String password, boolean isVerified, String status, String nationalId, File drivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.isVerified = isVerified;
        this.status = status;
        this.nationalId = nationalId;
        this.drivingLicense = drivingLicense;
    }

    public Transporter(String firstName, String lastName, String email, String phoneNumber,
                       String password, String status, String nationalId, File drivingLicense) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.status = status;
        this.nationalId = nationalId;
        this.drivingLicense = drivingLicense;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public File getDrivingLicense() {
        return drivingLicense;
    }

    public void setDrivingLicense(File drivingLicense) {
        this.drivingLicense = drivingLicense;
    }
}

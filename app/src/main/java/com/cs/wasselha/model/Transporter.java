package com.cs.wasselha.model;


import android.util.Log;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Transporter {

    private int id; // for the database
    private String firstName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean isVerified;

    private String status;
    private String nationalId;
    private File drivingLicense;
    private int review;
    List<Transporter> transporters = new ArrayList<>();

    public Transporter(){
        Faker faker = new Faker();
       // List<Transporter> transporters = new ArrayList<>();
        int i=0;
        while (i<30) {
            int id = faker.number().numberBetween(1,1000000);
            String email = faker.internet().emailAddress();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String phoneNumber = faker.phoneNumber().phoneNumber();
            String password = faker.internet().password();
            String nationalId = faker.idNumber().ssnValid();
            Transporter transporter  = new Transporter(
                    id,firstName,lastName,email,phoneNumber,password,nationalId);
            transporters.add(transporter);

            i++;

        }

        Log.d("transporters",transporters.toString());
    }
    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }



    public Transporter(int id,String firstName, String lastName, String email, String phoneNumber,
                       String password, String nationalId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.nationalId = nationalId;

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

    @Override
    public String toString() {
        return "Transporter{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", isVerified=" + isVerified +
                ", status='" + status + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", drivingLicense=" + drivingLicense +
                ", review=" + review +
                '}';
    }
}

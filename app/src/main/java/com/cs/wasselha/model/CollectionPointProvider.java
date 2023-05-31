package com.cs.wasselha.model;

import android.util.Log;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class CollectionPointProvider {
    private int id; // for the database
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;
    private String nationalId;
    private int review;
    List<CollectionPointProvider> collectionPointProviders = new ArrayList<>();
    public CollectionPointProvider(){
        Faker faker = new Faker();

        int i=0;
        while (i<30) {
            int id = faker.number().numberBetween(1,1000000);
            String email = faker.internet().emailAddress();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String phoneNumber = faker.phoneNumber().phoneNumber();
            boolean isVerified = true;
            String password = faker.internet().password();
            String nationalId = faker.idNumber().ssnValid();
            CollectionPointProvider collectionPointProvider  = new CollectionPointProvider(
                   id, firstName,lastName,email,password,phoneNumber,nationalId,isVerified);
            collectionPointProviders.add(collectionPointProvider);

            i++;

        }

        Log.d("collectionPointProviders",collectionPointProviders.toString());
    }
    public CollectionPointProvider(int id,String firstName, String lastName, String email, String password,
                                   String phoneNumber,  String nationalId ,boolean isVerified) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.isVerified = isVerified;
        this.nationalId = nationalId;
        //this.review = review;
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

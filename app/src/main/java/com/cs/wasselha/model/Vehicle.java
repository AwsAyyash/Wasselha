package com.cs.wasselha.model;

import android.media.Image;
import android.util.Log;

import com.github.javafaker.Faker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Vehicle {

    private int id; // for the database
    private Transporter transporter;
    private Image vehicleImage;
    private String vehicleType;
    private String vehicleNumber;
    private File vehicleLicense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle(){

        Faker faker = new Faker();
        List<Vehicle> vehicles = new ArrayList<>();

        List<String> vehicleTypes = new ArrayList<>();
        vehicleTypes.add("car");
        vehicleTypes.add("van");
        vehicleTypes.add("truck");

        List<Transporter> transportersList = new Transporter().transporters; // todo: i have to get them from the real database

        int i=0;

        while (i<30) {

            int id = faker.number().numberBetween(1,1000000);
            String vehicleTypeCurr = vehicleTypes.get(new Random().nextInt(3));
            Transporter transporterOwner= transportersList.get(new Random().nextInt(transportersList.size()));
            Vehicle vehicle  = new Vehicle(id,transporterOwner,null,vehicleTypeCurr,null);

            vehicles.add(vehicle);

            i++;

        }

        Log.d("vehicles",vehicles.toString());
    }
    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }



    public Vehicle(int id,Transporter transporter, Image vehicleImage, String vehicleType, File vehicleLicense) {
        this.id = id;
        this.transporter = transporter;
        this.vehicleImage = vehicleImage;
        this.vehicleType = vehicleType;
        this.vehicleLicense = vehicleLicense;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public Image getVehicleImage() {
        return vehicleImage;
    }

    public void setVehicleImage(Image vehicleImage) {
        this.vehicleImage = vehicleImage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public File getVehicleLicense() {
        return vehicleLicense;
    }

    public void setVehicleLicense(File vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }
}

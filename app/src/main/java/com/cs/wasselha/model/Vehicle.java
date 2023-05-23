package com.cs.wasselha.model;

import android.media.Image;

import java.io.File;

public class Vehicle {

    private Transporter transporter;
    private Image vehicleImage;
    private String vehicleType;
    private String vehicleNumber;
    private File vehicleLicense;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Vehicle() {
    }

    public Vehicle(Transporter transporter, Image vehicleImage, String vehicleType, File vehicleLicense) {
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

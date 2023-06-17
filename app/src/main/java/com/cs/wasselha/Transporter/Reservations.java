package com.cs.wasselha.Transporter;

public class Reservations {

    String customerName, packageType, sourceCity, destinationCity, time;


    public Reservations(String customerName, String packageType, String sourceCity, String destinationCity, String time)
    {
        this.customerName = customerName;
        this.packageType = packageType;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.time = time;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

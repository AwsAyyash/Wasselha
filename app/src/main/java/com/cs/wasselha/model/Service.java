package com.cs.wasselha.model;

import java.util.Date;

public class Service {

    private Transporter transporter;
    private Location sourcePlace;
    private Location destinationPlace;
    private Date serviceDate;
    private double price;

    public Service() {
    }

    public Service(Transporter transporter, Location sourcePlace, Location destinationPlace, Date serviceDate, double price) {
        this.transporter = transporter;
        this.sourcePlace = sourcePlace;
        this.destinationPlace = destinationPlace;
        this.serviceDate = serviceDate;
        this.price = price;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public Location getSourcePlace() {
        return sourcePlace;
    }

    public void setSourcePlace(Location sourcePlace) {
        this.sourcePlace = sourcePlace;
    }

    public Location getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(Location destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

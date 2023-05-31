package com.cs.wasselha.model;

import android.util.Log;

import com.github.javafaker.Faker;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Service {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Transporter transporter;
    private Location sourcePlace;
    private Location destinationPlace;
    private Date serviceDate;
    private double price;
    List<Service> services = new ArrayList<>();

    public Service(){

        Faker faker = new Faker();



        List<Transporter> transportersList = new Transporter().transporters; // todo: i have to get them from the real database
        List<Location> locationList = new Location().locations;

        int i=0;

        while (i<30) {

            int id = faker.number().numberBetween(1,1000000);

            double price = faker.number().numberBetween(10,500);

            Transporter serviceProvider= transportersList.get(new Random().nextInt(transportersList.size()));
            Location src = locationList.get(new Random().nextInt(locationList.size()));
            Location dest = locationList.get(new Random().nextInt(locationList.size()));
            Service service  = new Service(id,serviceProvider,src,dest,
                    faker.date().between(new Date(),new Date(2023,7,18)),price);


            services.add(service);

            i++;

        }

        Log.d("services",services.toString());
    }

    public Service(int id, Transporter transporter, Location sourcePlace, Location destinationPlace, Date serviceDate, double price) {
        this.id = id;
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

package com.cs.wasselha.model;

import android.util.Log;

import com.cs.wasselha.interfaces.implementation.LocationDA;
import com.cs.wasselha.interfaces.implementation.TransporterDA;
import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Service {


   // @Expose(serialize = false)
    private  transient int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Transporter transporter;
    private Location source_place;
    private Location destination_place;
    private Date service_date;
    private double price;

    public List<Service> getServices() {
        return services;
    }

    //@Expose(serialize = false)
    transient List<Service> services = new ArrayList<>();

    public Service() throws IOException {

        Faker faker = new Faker();



        ArrayList<Transporter> transportersList = new TransporterDA().getTransporters(); // todo: i have to get them from the real database
        List<Location> locationList = new LocationDA().getLocations();

        int i=0;

        while (i<30) {

            //int id = faker.number().numberBetween(1,1000000);

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

    public Service(int id, Transporter transporter, Location source_place, Location destination_place, Date service_date, double price) {
        this.id = id;
        this.transporter = transporter;
        this.source_place = source_place;
        this.destination_place = destination_place;
        this.service_date = service_date;
        this.price = price;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public void setTransporter(Transporter transporter) {
        this.transporter = transporter;
    }

    public Location getSource_place() {
        return source_place;
    }

    public void setSource_place(Location source_place) {
        this.source_place = source_place;
    }

    public Location getDestination_place() {
        return destination_place;
    }

    public void setDestination_place(Location destination_place) {
        this.destination_place = destination_place;
    }

    public Date getService_date() {
        return service_date;
    }

    public void setService_date(Date service_date) {
        this.service_date = service_date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

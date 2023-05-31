package com.cs.wasselha.model;

import android.util.Log;

import com.github.javafaker.Faker;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionPoint {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private CollectionPointProvider collectionPointProvider;
    private String status;
    private LocalTime open_time ; // open_time = LocalTime.of(15,30);
    private LocalTime close_time; // LocalTime lTime = LocalTime.now(); field = lTime.get(ChronoField.AMPM_OF_DAY);

    private Location location;

    public CollectionPoint(){

        Faker faker = new Faker();
        List<CollectionPoint> collectionPoints = new ArrayList<>();

        List<String> statusTypes = new ArrayList<>();
        statusTypes.add("open");
        statusTypes.add("closed");

        // todo: i have to get them from the real database
        List<CollectionPointProvider> collectionPointProviders = new CollectionPointProvider().collectionPointProviders;

        int i=0;

        while (i<30) {

            int id = faker.number().numberBetween(1,1000000);
            String statusTypesCurr = statusTypes.get(new Random().nextInt(3));
            CollectionPointProvider cPPOwner=
                    collectionPointProviders.get(new Random().nextInt(collectionPointProviders.size()));

            CollectionPoint collectionPoint  = new CollectionPoint(id,cPPOwner,statusTypesCurr,LocalTime.of(10,30),
                    LocalTime.of(17,30),null);

            collectionPoints.add(collectionPoint);

            i++;

        }

        Log.d("collectionPoints",collectionPoints.toString());
    }

    public CollectionPoint(int id,CollectionPointProvider collectionPointProvider, String status,
                           LocalTime open_time, LocalTime close_time, Location location) {
        this.id = id;
        this.collectionPointProvider = collectionPointProvider;
        this.status = status;
        this.open_time = open_time;
        this.close_time = close_time;
        this.location = location;
    }

    public CollectionPoint(CollectionPointProvider collectionPointProvider, LocalTime open_time, LocalTime close_time, Location location) {
        this.collectionPointProvider = collectionPointProvider;
        this.open_time = open_time;
        this.close_time = close_time;
        this.location = location;
    }

    public CollectionPointProvider getCollectionPointProvider() {
        return collectionPointProvider;
    }

    public void setCollectionPointProvider(CollectionPointProvider collectionPointProvider) {
        this.collectionPointProvider = collectionPointProvider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalTime getOpen_time() {
        return open_time;
    }

    public void setOpen_time(LocalTime open_time) {
        this.open_time = open_time;
    }

    public LocalTime getClose_time() {
        return close_time;
    }

    public void setClose_time(LocalTime close_time) {
        this.close_time = close_time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

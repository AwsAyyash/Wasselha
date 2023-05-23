package com.cs.wasselha.model;

import java.time.LocalTime;

public class CollectionPoint {

    private CollectionPointProvider collectionPointProvider;
    private String status;
    private LocalTime open_time ; // open_time = LocalTime.of(15,30);
    private LocalTime close_time; // LocalTime lTime = LocalTime.now(); field = lTime.get(ChronoField.AMPM_OF_DAY);

    private Location location;

    public CollectionPoint() {
    }

    public CollectionPoint(CollectionPointProvider collectionPointProvider, String status, LocalTime open_time, LocalTime close_time, Location location) {
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

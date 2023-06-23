package com.cs.wasselha.Models;

import com.cs.wasselha.model.Location;

public class ServicesModel implements Comparable<ServicesModel>{

    String transporterName, time, sourceCity, destinationCity;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    String vehicleType;

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    String imageUrl;

    public int getTransporterId() {
        return transporterId;
    }

    int transporterId;
    Location srcLocation;

    public Location getSrcLocation() {
        return srcLocation;
    }

    public void setSrcLocation(Location srcLocation) {
        this.srcLocation = srcLocation;
    }

    public Location getDestLocation() {
        return destLocation;
    }

    public void setDestLocation(Location destLocation) {
        this.destLocation = destLocation;
    }

    Location destLocation;

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    int review;

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    public ServicesModel(String transporterName, int transporterId, String time, String sourceCity, String destinationCity,
                         String imageUrl, String vehicleType, int review,
                         /*for the sorting*/ Location srcLocation, Location destLocation)
    {
        this.transporterId = transporterId;
        this.transporterName = transporterName;
        this.time = time;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
       this.imageUrl = imageUrl;
       this.vehicleType = vehicleType;
       this.review = review;
       this.srcLocation = srcLocation;
       this.destLocation = destLocation;
    }


    //Getter and Setter
    public String getTransporterName()
    {
        return transporterName;
    }

    public String getTime()
    {
        return time;
    }

    public String getSourceCity()
    {
        return sourceCity;
    }

    public String getDestinationCity()
    {
        return destinationCity;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    @Override
    public int compareTo(ServicesModel o) {
        return review-o.getReview();
    }
}

package com.cs.wasselha.Models;

public class ServicesModel {

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

    public void setTransporterId(int transporterId) {
        this.transporterId = transporterId;
    }

    public ServicesModel(String transporterName, int transporterId, String time, String sourceCity, String destinationCity,
                         String imageUrl,String vehicleType)
    {
        this.transporterId = transporterId;
        this.transporterName = transporterName;
        this.time = time;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
       this.imageUrl = imageUrl;
       this.vehicleType = vehicleType;
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

}

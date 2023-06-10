package com.cs.wasselha.Models;

public class ServicesModel {

    String transporterName, time, sourceCity, destinationCity;
    int image;

    public ServicesModel(String transporterName, String time, String sourceCity, String destinationCity, int image)
    {
        this.transporterName = transporterName;
        this.time = time;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.image = image;
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

    public int getImage()
    {
        return image;
    }

}

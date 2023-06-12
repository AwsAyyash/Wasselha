package com.cs.wasselha.Transporter;

public class Services {

    int image;
    String sourceCity, destinationCity;
    String time;


    public Services(int image, String sourceCity, String destinationCity, String time)
    {
        this.image = image;
        this.sourceCity = sourceCity;
        this.destinationCity = destinationCity;
        this.time = time;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

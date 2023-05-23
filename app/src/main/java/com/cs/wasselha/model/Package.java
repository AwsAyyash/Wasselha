package com.cs.wasselha.model;

public class Package {

    private DeliveryServiceDetails deliveryServiceDetails;
    private String type;
    private double weight;

    public Package() {
    }

    public Package(DeliveryServiceDetails deliveryServiceDetails, String type, double weight) {
        this.deliveryServiceDetails = deliveryServiceDetails;
        this.type = type;
        this.weight = weight;
    }

    public DeliveryServiceDetails getDeliveryServiceDetails() {
        return deliveryServiceDetails;
    }

    public void setDeliveryServiceDetails(DeliveryServiceDetails deliveryServiceDetails) {
        this.deliveryServiceDetails = deliveryServiceDetails;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

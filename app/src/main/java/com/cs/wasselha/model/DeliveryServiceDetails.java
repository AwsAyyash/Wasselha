package com.cs.wasselha.model;

import java.time.LocalDateTime;
import java.util.Date;

public class DeliveryServiceDetails {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Service service;
    private Customer customer;
    private CollectionPoint sourceCollectionPoint;
    private CollectionPoint destinationCollectionPoint;
    private boolean isPaid;
    private LocalDateTime collectionTime;

    private LocalDateTime handoverTime;
   // private String status;

    public DeliveryServiceDetails() {


    }

    public DeliveryServiceDetails(Service service, Customer customer, CollectionPoint sourceCollectionPoint,
                                  CollectionPoint destinationCollectionPoint, boolean isPaid,
                                  LocalDateTime collectionTime, LocalDateTime handoverTime) {
        this.service = service;
        this.customer = customer;
        this.sourceCollectionPoint = sourceCollectionPoint;
        this.destinationCollectionPoint = destinationCollectionPoint;
        this.isPaid = isPaid;
        this.collectionTime = collectionTime;
        this.handoverTime = handoverTime;
        //this.status = status;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public CollectionPoint getSourceCollectionPoint() {
        return sourceCollectionPoint;
    }

    public void setSourceCollectionPoint(CollectionPoint sourceCollectionPoint) {
        this.sourceCollectionPoint = sourceCollectionPoint;
    }

    public CollectionPoint getDestinationCollectionPoint() {
        return destinationCollectionPoint;
    }

    public void setDestinationCollectionPoint(CollectionPoint destinationCollectionPoint) {
        this.destinationCollectionPoint = destinationCollectionPoint;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public LocalDateTime getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(LocalDateTime collectionTime) {
        this.collectionTime = collectionTime;
    }

    public LocalDateTime getHandoverTime() {
        return handoverTime;
    }

    public void setHandoverTime(LocalDateTime handoverTime) {
        this.handoverTime = handoverTime;
    }

   // public String getStatus() {
       // return status;
    //}

   // public void setStatus(String status) {
     //   this.status = status;
   // }
}

package com.cs.wasselha.model;

import java.time.LocalDateTime;

public class DeliveryStatus {

    private DeliveryServiceDetails deliveryServiceDetails;
    private LocalDateTime actionTime;
    private String collectionFrom;
    private String handoverTo;

    public DeliveryStatus() {
    }

    public DeliveryStatus(DeliveryServiceDetails deliveryServiceDetails, LocalDateTime actionTime, String collectionFrom, String handoverTo) {
        this.deliveryServiceDetails = deliveryServiceDetails;
        this.actionTime = actionTime;
        this.collectionFrom = collectionFrom;
        this.handoverTo = handoverTo;
    }

    public DeliveryServiceDetails getDeliveryServiceDetails() {
        return deliveryServiceDetails;
    }

    public void setDeliveryServiceDetails(DeliveryServiceDetails deliveryServiceDetails) {
        this.deliveryServiceDetails = deliveryServiceDetails;
    }

    public LocalDateTime getActionTime() {
        return actionTime;
    }

    public void setActionTime(LocalDateTime actionTime) {
        this.actionTime = actionTime;
    }

    public String getCollectionFrom() {
        return collectionFrom;
    }

    public void setCollectionFrom(String collectionFrom) {
        this.collectionFrom = collectionFrom;
    }

    public String getHandoverTo() {
        return handoverTo;
    }

    public void setHandoverTo(String handoverTo) {
        this.handoverTo = handoverTo;
    }
}

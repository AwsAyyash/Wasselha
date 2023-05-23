package com.cs.wasselha.model;

import java.time.LocalDateTime;

public class Claim {

    private DeliveryServiceDetails deliveryServiceDetails;

    private int writerId;
    private String writerType;
    private String message;
    private LocalDateTime date;

    public Claim() {
    }

    public Claim(DeliveryServiceDetails deliveryServiceDetails, int writerId, String writerType, String message, LocalDateTime date) {
        this.deliveryServiceDetails = deliveryServiceDetails;
        this.writerId = writerId;
        this.writerType = writerType;
        this.message = message;
        this.date = date;
    }

    public DeliveryServiceDetails getDeliveryServiceDetails() {
        return deliveryServiceDetails;
    }

    public void setDeliveryServiceDetails(DeliveryServiceDetails deliveryServiceDetails) {
        this.deliveryServiceDetails = deliveryServiceDetails;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public String getWriterType() {
        return writerType;
    }

    public void setWriterType(String writerType) {
        this.writerType = writerType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

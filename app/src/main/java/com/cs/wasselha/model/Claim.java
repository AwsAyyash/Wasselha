package com.cs.wasselha.model;

import java.time.LocalDateTime;

public class Claim {

    private DeliveryServiceDetails deliveryServiceDetails;

    private int writerId;
    private int writtenToId;
    private String writerType;
    private String writtenToType;
    private String message;

    public int getWrittenToId() {
        return writtenToId;
    }

    public Claim(DeliveryServiceDetails deliveryServiceDetails, int writerId, int writtenToId, String writerType, String writtenToType, String message, LocalDateTime date, int review) {
        this.deliveryServiceDetails = deliveryServiceDetails;
        this.writerId = writerId;
        this.writtenToId = writtenToId;
        this.writerType = writerType;
        this.writtenToType = writtenToType;
        this.message = message;
        this.date = date;
        this.review = review;
    }

    public void setWrittenToId(int writtenToId) {
        this.writtenToId = writtenToId;
    }

    public String getWrittenToType() {
        return writtenToType;
    }

    public void setWrittenToType(String writtenToType) {
        this.writtenToType = writtenToType;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    private LocalDateTime date;
    private int review;

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

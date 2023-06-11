package com.cs.wasselha.Customer;

public class Notifications {

    int image;
    String titleNotification, descriptionNotification, notificationTime;

    public Notifications(int image, String collectionPointName, String descriptionNotification, String notificationTime)
    {
        this.image = image;
        this.titleNotification = collectionPointName;
        this.descriptionNotification = descriptionNotification;
        this.notificationTime = notificationTime;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitleNotification() {
        return titleNotification;
    }

    public void setTitleNotification(String titleNotification) {
        this.titleNotification = titleNotification;
    }

    public String getDescriptionNotification() {
        return descriptionNotification;
    }

    public void setDescriptionNotification(String descriptionNotification) {
        this.descriptionNotification = descriptionNotification;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}

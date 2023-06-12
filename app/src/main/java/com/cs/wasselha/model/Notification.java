package com.cs.wasselha.model;

import java.time.LocalDateTime;

public class Notification {


    private int userId;
    private String userType;
    private String title;

    private String description;
    private LocalDateTime date;

    public Notification() {
    }

    public Notification(int userId, String userType, String title, String description, LocalDateTime date) {
        this.userId = userId;
        this.userType = userType;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}

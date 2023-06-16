package com.cs.wasselha.Claims;


public class Claims {

    String review, message, date;
    int claimImg;

    public Claims(int claimImg,String review, String message, String date)
    {
        this.claimImg = claimImg;
        this.review = review;
        this.message = message;
        this.date = date;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClaimImg() {
        return claimImg;
    }

    public void setClaimImg(int claimImg) {
        this.claimImg = claimImg;
    }
}

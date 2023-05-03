package com.javatpoint.models;

public class DeliveryStatus {
    private String date;
    private String status;

    public DeliveryStatus () {
    
    }

    public DeliveryStatus(String date, String status) {
        this.date = date;
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

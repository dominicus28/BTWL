package com.javatpoint.models;

import java.util.List;

public class Delivery {
    private User sender;
    private User recevier;
    private User courier;
    private String sendingDate;
    private String recevingDate;
    private List<DeliveryStatus> status;

    public Delivery () {
    
    }

    public Delivery(User sender, User recevier, User courier, String sendingDate, String recevingDate,
            List<DeliveryStatus> status) {
        this.sender = sender;
        this.recevier = recevier;
        this.courier = courier;
        this.sendingDate = sendingDate;
        this.recevingDate = recevingDate;
        this.status = status;
    }


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    
    public User getRecevier() {
        return recevier;
    }

    public void setRecevier(User recevier) {
        this.recevier = recevier;
    }

    
    public User getCourier() {
        return courier;
    }

    public void setCourier(User courier) {
        this.courier = courier;
    }

    
    public String getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(String sendingDate) {
        this.sendingDate = sendingDate;
    }

    
    public String getRecevingDate() {
        return recevingDate;
    }

    public void setRecevingDate(String recevingDate) {
        this.recevingDate = recevingDate;
    }

    
    public List<DeliveryStatus> getStatus() {
        return status;
    }

    public void setStatus(List<DeliveryStatus> status) {
        this.status = status;
    }

}

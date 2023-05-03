package com.javatpoint.models;

import java.util.HashMap;

public class Parcel {
    private char size;  // A or B or C
    private String deliverFrom;
    private String deliverTo;
    private float insurance;    // ubezpieczenie
    private HashMap<String, String> status;
    private User sender;
    private User receiver;

    //default constructor  
    public Parcel () {
        
    }

    //constructor using fields
    public Parcel(char size, String deliverFrom, String deliverTo, float insurance, HashMap<String, String> status, User receiver,
            User sender) {
        this.size = size;
        this.deliverFrom = deliverFrom;
        this.deliverTo = deliverTo;
        this.insurance = insurance;
        this.status = status;
        this.receiver = receiver;
        this.sender = sender;
    }

    //getters and setters  

    public char getSize() {
        return size;
    }
    public void setSize(char size) {  
        this.size = size;  
    }
    public String getDeliverFrom() {
        return deliverFrom;
    }
    public void setDeliverFrom(String deliverFrom) {
        this.deliverFrom = deliverFrom;
    }
    public String getDeliverTo() {
        return deliverTo;
    }
    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }
    public float getInsurance() {
        return insurance;
    }
    public void setInsurance(float insurance) {
        this.insurance = insurance;
    }
    public HashMap<String, String> getStatus() {
        return status;
    }
    public void setStatus(HashMap<String, String> status) {
        this.status = status;
    }
        public User getSender() {
        return sender;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
}
package com.javatpoint.models;

public class Parcel {
    private int id;
    private char size;
    private int senderId;
    private int receiverId;
    private int courierId;
    private String deliverFrom;
    private String deliverTo;
    private int status;
    private double price;

    //default constructor  
    public Parcel()  
    {  
        
    }  
    //constructor using fields  
    public Parcel(int id, char size, int senderId, int receiverId, int courierId)   
    {  
        super();  
        this.id = id;  
        this.size = size;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.courierId = courierId;
    }

    //getters and setters  
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }
    public char getSize() {
        return size;
    }
    public void setSize(char size) {  
        this.size = size;  
    }
    public int getSenderId() {
        return senderId;
    }
    public void setSenderId(int senderId) {  
        this.senderId = senderId;  
    }
    public int getRevicerId() {
        return receiverId;
    }
    public void setRevicerId(int receiverId) {  
        this.receiverId = receiverId;  
    }
    public int getCourierId() {
        return courierId;
    }
    public void setCourierId(int courierId) {  
        this.courierId = courierId;  
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
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
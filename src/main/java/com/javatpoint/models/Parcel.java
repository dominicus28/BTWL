package com.javatpoint.models;

import java.util.HashMap;

public class Parcel {
    private char size;  // A or B or C
    private String deliverFrom;
    private String deliverTo;
    private float insurance;    // ubezpieczenie
    private User sender;
    private User receiver;
    private User courier;
    private HashMap<Integer, Telemetry> telemetry;
    private HashMap<Integer, Alarm> alarm;
    private HashMap<Integer, Status> status;

    //default constructor  
    public Parcel () {
        
    }

    //constructor using fields
    public Parcel(char size, String deliverFrom, String deliverTo, float insurance, User sender, User receiver,
            User courier, HashMap<Integer, Telemetry> telemetry, HashMap<Integer, Alarm> alarm,
            HashMap<Integer, Status> status) {
        this.size = size;
        this.deliverFrom = deliverFrom;
        this.deliverTo = deliverTo;
        this.insurance = insurance;
        this.sender = sender;
        this.receiver = receiver;
        this.courier = courier;
        this.telemetry = telemetry;
        this.alarm = alarm;
        this.status = status;
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
    public User getCourier() {
        return courier;
    }
    public void setCourier(User courier) {
        this.courier = courier;
    }

    /* Telemetry */
    public HashMap<Integer, Telemetry> getTelemetry() {
        return telemetry;
    }
    public void setTelemetry(HashMap<Integer, Telemetry> telemetry) {
        this.telemetry = telemetry;
    }

    public void addTelemetry(Telemetry newTelemetry) {
        telemetry.put(telemetry.size() + 1, newTelemetry);
    }

    public void getLastTelemetry() {
        telemetry.get(telemetry.size() - 1);
    }

    /* Alarm */
    public HashMap<Integer, Alarm> getAlarm() {
        return alarm;
    }
    public void setAlarm(HashMap<Integer, Alarm> alarm) {
        this.alarm = alarm;
    }

    public void addAlarm(Alarm newAlarm) {
        alarm.put(alarm.size() + 1, newAlarm);
    }

    public void getLastAlarm() {
        alarm.get(alarm.size() - 1);
    }
    
    /* Status */
    public HashMap<Integer, Status> getStatus() {
        return status;
    }
    public void setStatus(HashMap<Integer, Status> status) {
        this.status = status;
    }

    public void addStatus(Status newStatus) {
        status.put(status.size() + 1, newStatus);
    }

    public void getLastStatus() {
        status.get(status.size() - 1);
    }
}
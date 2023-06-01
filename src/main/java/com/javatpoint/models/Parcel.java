package com.javatpoint.models;
// import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.util.ArrayList;
//import java.time.LocalDateTime;
//import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

// import java.util.HashMap;

@Document
public class Parcel {
    // @Id
    @MongoId
    // @JsonSerialize(using = ToStringSerializer.class)
    // @JsonIgnore
    protected ObjectId id;
    protected char size;  // A or B or C
    // @DocumentReference
    public Place deliverFrom;
    // @DocumentReference
    public Place deliverTo;
    protected float insurance;    // ubezpieczenie
    @DocumentReference
    protected User sender;
    @DocumentReference
    protected User receiver;
    @DocumentReference
    protected User courier;
    @DocumentReference
    protected Box box;

    //default constructor  
    public Parcel () {
        
    }

    //constructor using fields
    public Parcel(char size, Place deliverFrom, Place deliverTo, float insurance, User sender, User receiver,
                User courier, Box box, Telemetry telemetry, Alarm alarm,
                Status status) {
        
        super();
        this.size = size;
        this.deliverFrom = deliverFrom;
        this.deliverTo = deliverTo;
        this.insurance = insurance;
        this.sender = sender;
        this.receiver = receiver;
        this.courier = courier;
        this.box = box;
        // this.telemetry = telemetry;
        // this.alarm = alarm;
        // this.status = status;
        
    }

    //getters and setters  
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }

    public char getSize() {
        return size;
    }
    public void setSize(char size) {  
        this.size = size;  
    }
    public Place getDeliverFrom() {
        return deliverFrom;
    }
    public void setDeliverFrom(Place deliverFrom) {
        this.deliverFrom = deliverFrom;
    }
    public Place getDeliverTo() {
        return deliverTo;
    }
    public void setDeliverTo(Place deliverTo) {
        this.deliverTo = deliverTo;
    }
    public float getInsurance() {
        return insurance;
    }
    public void setInsurance(float insurance) {
        this.insurance = insurance;
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
    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }
    /* Telemetry */
    // public Telemetry getTelemetry() {
    //     return telemetry;
    // }
    // public void setTelemetry(Telemetry telemetry) {
    //     this.telemetry = telemetry;
    // }

    // public void addTelemetry(Telemetry newTelemetry) {
    //     telemetry.put(telemetry.size() + 1, newTelemetry);
    // }

    // public void getLastTelemetry() {
    //     telemetry.get(telemetry.size() - 1);
    // }

    // /* Alarm */
    // public Alarm getAlarm() {
    //     return alarm;
    // }
    // public void setAlarm(Alarm alarm) {
    //     this.alarm = alarm;
    // }

    // public void addAlarm(Alarm newAlarm) {
    //     alarm.put(alarm.size() + 1, newAlarm);
    // }

    // public void getLastAlarm() {
    //     alarm.get(alarm.size() - 1);
    // }
    
    // /* Status */
    // public Status getStatus() {
    //     return status;
    // }
    // public void setStatus(Status status) {
    //     this.status = status;
    // }

    // public void addStatus(Status newStatus) {
    //     status.put(status.size() + 1, newStatus);
    // }

    // public void getLastStatus() {
    //     status.get(status.size() - 1);
    // }
}
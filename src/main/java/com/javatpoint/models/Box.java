package com.javatpoint.models;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
//import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Box {
    /* Unique indentyfier of a box (MAC) */
    @Id
    @JsonIgnore
    private ObjectId id;
    @Indexed(unique = true)
    protected String mac;
    /* current transaction */
    @DocumentReference
    protected ParcelComplete parcelComplete;
    /* logs, all history of a box */
    @DocumentReference
    @JsonIgnore
    protected List <TimestampTelemetry> telemetry;
    @DocumentReference
    @JsonIgnore
    protected List <TimestampAlarm> alarm;
    // @DocumentReference
    // protected List <TimestampStatus> status;
    // @DocumentReference
    // @JsonIgnore
    // protected List <Message> message;
    @JsonIgnore
    public Message default_message = new Message(false, false);
    
    

    public Box() {}

    public Box(String mac) {
        super();
        this.mac = mac;
        parcelComplete = null;
        this.telemetry = new ArrayList<>();
        this.alarm = new ArrayList<>();
        // this.message = new ArrayList<>();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    @JsonIgnore
    public ParcelComplete getParcelComplete() {
        return parcelComplete;
    }

    public void setParcelComplete(ParcelComplete parcelComplete) {
        this.parcelComplete = parcelComplete;
    }
    
    // @JsonIgnore
    public String getMac() {
		return mac;
	}

    public List<TimestampTelemetry> getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(List<TimestampTelemetry> telemetry) {
        this.telemetry = telemetry;
    }

    public List<TimestampAlarm> getAlarm() {
        return alarm;
    }

    public void setAlarm(List<TimestampAlarm> alarm) {
        this.alarm = alarm;
    }

    // public List<Message> getMessage() {
    //     return message;
    // }

    // public void setMessage(List<Message> message) {
    //     this.message = message;
    // }

    
}
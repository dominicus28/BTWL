package com.javatpoint.models;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

//import com.fasterxml.jackson.databind.annotation.JsonSerialize;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Box {
    /* Unique indentyfier of a box (MAC) */
    @Id
    @Indexed(unique = true)
    private String mac;
    /* current transaction */
    @DocumentReference
    protected ParcelComplete parcelComplete;

    /* logs, all history of a box */
    @DocumentReference
    protected List <TimestampTelemetry> telemetry;
    @DocumentReference
    protected List <TimestampAlarm> alarm;
    @DocumentReference
    protected List <TimestampStatus> status;
    @DocumentReference
    protected List <Message> message;
    
    public Box() {}

    public Box(String mac) {
        super();
        this.mac = mac;
    }

    public ParcelComplete getParcelComplete() {
        return parcelComplete;
    }

    public void setParcelComplete(ParcelComplete parcelComplete) {
        this.parcelComplete = parcelComplete;
    }
    
    public String getMac() {
		return mac;
	}

	// public void setMac(String mac) {
	// 	this.mac = mac;
	// }
    public void addMessage(Message newMessage) {
        message.add(newMessage);
    }
}
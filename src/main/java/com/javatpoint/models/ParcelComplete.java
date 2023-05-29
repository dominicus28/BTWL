package com.javatpoint.models;

import java.util.ArrayList;
//import java.time.LocalDateTime;
//import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class ParcelComplete {
    @Id
    // @JsonSerialize(using= ToStringSerializer.class)
    protected ObjectId id;
    @DocumentReference
    protected Parcel parcel;
    @DocumentReference
    protected List <TimestampTelemetry> telemetry;
    @DocumentReference
    protected List <TimestampAlarm> alarm;
    @DocumentReference
    protected List <TimestampStatus> status;

    public ParcelComplete() {}

    // public ParcelComplete(Parcel parcel, List <TimestampTelemetry> telemetry,
    //                     List <TimestampAlarm> alarm, List <TimestampStatus> status) {
    //     super();
    //     this.parcel = parcel;
    //     this.telemetry = telemetry;
    //     this.alarm = alarm;
    //     this.status = status;
    // }

    public ParcelComplete(Parcel parcel) {
        super();
        this.parcel = parcel;
        this.telemetry = new ArrayList<TimestampTelemetry>();
        this.alarm = new ArrayList<TimestampAlarm>();
        this.status = new ArrayList<TimestampStatus>();
    }
    
    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public void addTimestampTelemetry(Telemetry newTimestampTelemetry) {
        telemetry.add(new TimestampTelemetry(newTimestampTelemetry));
    }

    public void addAlarm(Alarm newAlarm) {
        alarm.add(new TimestampAlarm(newAlarm));
    }

    public void addStatus(Status newStatus) {
        status.add(new TimestampStatus(newStatus));
    }
    public TimestampTelemetry getLastTelemetry() {
        return telemetry.get(telemetry.size() - 1);
    }

    public TimestampAlarm getLastAlarm() {
        return alarm.get(alarm.size() - 1);
    }

    public TimestampStatus getLastStatus() {
        return status.get(status.size() - 1);
    }
}

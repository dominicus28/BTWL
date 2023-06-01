package com.javatpoint.models;

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

@Document
// @JsonInclude(JsonInclude.Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ParcelComplete {
    @Id
    // @JsonSerialize(using= ToStringSerializer.class)
    @JsonIgnore
    protected ObjectId id;
    @DocumentReference
    protected Parcel parcel;
    @DocumentReference
    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List <TimestampTelemetry> telemetry;
    @DocumentReference
    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List <TimestampAlarm> alarm;
    @DocumentReference
    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected List <TimestampStatus> status;

    public ParcelComplete() {}

    public ParcelComplete(Parcel parcel) {
        super();
        this.parcel = parcel;
        this.telemetry = new ArrayList<>();
        this.alarm = new ArrayList<>();
        this.status = new ArrayList<>();
    }
    
    @JsonIgnore
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<TimestampTelemetry> getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(List<TimestampTelemetry> telemetry) {
        this.telemetry = telemetry;
    }

    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<TimestampAlarm> getAlarm() {
        return alarm;
    }

    public void setAlarm(List<TimestampAlarm> alarm) {
        this.alarm = alarm;
    }

    // @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<TimestampStatus> getStatus() {
        return status;
    }

    public void setStatus(List<TimestampStatus> status) {
        this.status = status;
    }

    public Message getMessage() {
        return null;
    }

    public Message setMessage(Message message) {
        return null;
    }

    // MOVE TO THE REPOSITORY
    // public void addTelemetry(Telemetry newTimestampTelemetry) {
    //     // if(telemetry == null)
    //     //     this.telemetry = new ArrayList<TimestampTelemetry>();

    //     telemetry.add(new TimestampTelemetry(newTimestampTelemetry));
    // }

    // public void addAlarm(Alarm newAlarm) {
    //     // if(alarm == null)
    //     //     this.alarm = new ArrayList<TimestampAlarm>();

    //     alarm.add(new TimestampAlarm(newAlarm));
    // }

    // public void addStatus(Status newStatus) {
    //     // if(status == null)
    //     //     this.status = new ArrayList<TimestampStatus>();

    //     status.add(new TimestampStatus(newStatus));
    // }
    // public TimestampTelemetry getLastTelemetry() {
    //     return telemetry.get(telemetry.size() - 1);
    // }

    // public TimestampAlarm getLastAlarm() {
    //     return alarm.get(alarm.size() - 1);
    // }

    @JsonIgnore
    public TimestampStatus getLastStatus() {
        if(status != null)
            if(status.size() != 0)
                return status.get(status.size() - 1);

        return null;
    }
}

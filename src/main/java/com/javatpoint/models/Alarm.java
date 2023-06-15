package com.javatpoint.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Alarm {
    @Id
    @JsonIgnore
    private ObjectId id;
    private String alarm;
    private int code;
    // private Telemetry telemetry;

    public Alarm() {
    }

    public Alarm(char code, Telemetry telemetry) {
        this.code = code;
        // this.telemetry = telemetry;
    }
    
        public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    // public Telemetry getTelemetry() {
    //     return telemetry;
    // }
    // public void setTelemetry(Telemetry telemetry) {
    //     this.telemetry = telemetry;
    // }


}
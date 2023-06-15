package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Alarm {
    private String alarm;
    private char code;
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

    public char getCode() {
        return code;
    }
    public void setCode(char code) {
        this.code = code;
    }

    // public Telemetry getTelemetry() {
    //     return telemetry;
    // }
    // public void setTelemetry(Telemetry telemetry) {
    //     this.telemetry = telemetry;
    // }


}
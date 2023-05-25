package com.javatpoint.models;

public class Alarm {
    private char code;
    private Telemetry telemetry;

    public Alarm() {
    }

    public Alarm(char code, Telemetry telemetry) {
        this.code = code;
        this.telemetry = telemetry;
    }
    
    public char getCode() {
        return code;
    }
    public void setCode(char code) {
        this.code = code;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }
    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }


}
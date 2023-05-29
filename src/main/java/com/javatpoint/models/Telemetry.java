package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Telemetry {
    @DocumentReference
    private String mac;
    private float temperature;
    private int batteryStatus;    // %
    private double maxAcceleration;
    private double humidity;
    private int time;
    private double longitude;
    private double latitude;

    public Telemetry() {
        
    }

    public Telemetry(float temperature, int batteryStatus, double maxAcceleration, double humidity, int time,
            double longitude, String mac, double latitude) {
        this.temperature = temperature;
        this.batteryStatus = batteryStatus;
        this.maxAcceleration = maxAcceleration;
        this.humidity = humidity;
        this.time = time;
        this.longitude = longitude;
        this.mac = mac;
        this.latitude = latitude;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public double getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}

package com.javatpoint.models;

public class Telemetry {
    private float temperature;
    private int batteryStatus;    // %
    private double maxAcceleration;
    private double humidity;
    private String time;

    public Telemetry() {
        
    }

    public Telemetry(float temperature, int batteryStatus, double maxAcceleration, double humidity, String time) {
        super();
        this.temperature = temperature;
        this.batteryStatus = batteryStatus;
        this.maxAcceleration = maxAcceleration;
        this.humidity = humidity;
        this.time = time;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}

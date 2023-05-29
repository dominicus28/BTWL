package com.javatpoint.models;

public class ParcelBrief {
    private Parcel parcel;
    private TimestampObject telemetry;
    private TimestampObject alarm;
    private TimestampObject status;

    public ParcelBrief() {}

    public ParcelBrief(Parcel parcel, TimestampObject telemetry, 
                    TimestampObject alarm, TimestampObject status) {
        super();
        this.parcel = parcel;
        this.telemetry = telemetry;
        this.alarm = alarm;
        this.status = status;
    }
    
    public Parcel getParcel() {
        return parcel;
    }

    public void setParcel(Parcel parcel) {
        this.parcel = parcel;
    }

    public TimestampObject getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(TimestampObject telemetry) {
        this.telemetry = telemetry;
    }

    public TimestampObject getAlarm() {
        return alarm;
    }

    public void setAlarm(TimestampObject alarm) {
        this.alarm = alarm;
    }

    public TimestampObject getStatus() {
        return status;
    }

    public void setStatus(TimestampObject status) {
        this.status = status;
    }
}

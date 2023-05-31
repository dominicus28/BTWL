package com.javatpoint.models;

public class ParcelBrief {
    private Parcel parcel;
    private TimestampTelemetry telemetry;
    private TimestampAlarm alarm;
    private TimestampStatus status;

    public ParcelBrief() {}

    public ParcelBrief(Parcel parcel, TimestampTelemetry telemetry, 
                    TimestampAlarm alarm, TimestampStatus status) {
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

    public TimestampTelemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(TimestampTelemetry telemetry) {
        this.telemetry = telemetry;
    }

    public TimestampAlarm getAlarm() {
        return alarm;
    }

    public void setAlarm(TimestampAlarm alarm) {
        this.alarm = alarm;
    }

    public TimestampStatus getStatus() {
        return status;
    }

    public void setStatus(TimestampStatus status) {
        this.status = status;
    }
}

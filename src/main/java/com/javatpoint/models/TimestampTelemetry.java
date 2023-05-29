package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class TimestampTelemetry extends TimestampObject {
    // @DocumentReference
    private Telemetry telemetry;

    public TimestampTelemetry() {}

    public TimestampTelemetry(Telemetry telemetry) {
        super();
        this.telemetry = telemetry;
    }

    public Telemetry getTelemetry() {
        return telemetry;
    }

    public void setTelemetry(Telemetry telemetry) {
        this.telemetry = telemetry;
    }
}

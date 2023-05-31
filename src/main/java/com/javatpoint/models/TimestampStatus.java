package com.javatpoint.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class TimestampStatus extends TimestampObject {
    @DocumentReference
    private Status status;

    public TimestampStatus() {}

    public TimestampStatus(Status status) {
        super();
        this.time = LocalDateTime.now();
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

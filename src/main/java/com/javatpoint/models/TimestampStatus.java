package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class TimestampStatus extends TimestampObject {
    @DocumentReference
    private Status status;

    public TimestampStatus() {}

    public TimestampStatus(Status status) {
        super();
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

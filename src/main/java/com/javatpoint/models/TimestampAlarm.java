package com.javatpoint.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
public class TimestampAlarm extends TimestampObject {
    @DocumentReference
    private Alarm alarm;

    public TimestampAlarm() {}

    public TimestampAlarm(Alarm alarm) {
        super();
        this.time = LocalDateTime.now();
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}

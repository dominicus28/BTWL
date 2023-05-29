package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class TimestampAlarm extends TimestampObject {
    @DocumentReference
    private Alarm alarm;

    public TimestampAlarm() {}

    public TimestampAlarm(Alarm alarm) {
        super();
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
}

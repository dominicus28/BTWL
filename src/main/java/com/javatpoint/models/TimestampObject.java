package com.javatpoint.models;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

// import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class TimestampObject {
    @Id
    private ObjectId id;
    protected LocalDateTime time;
    // @DocumentReference
    // private Object data;

// public TimestampObject() {}

public TimestampObject() {
    super();
    this.time = LocalDateTime.now();
    // this.data = data;
}

public TimestampObject(LocalDateTime time) {
    super();
    this.time = time;
    // this.data = data;
}

public LocalDateTime getTime() {
        return time;
    }
    public void setTime(LocalDateTime time) {
        this.time = LocalDateTime.now();
    }

// public Object getData() {
//         return data;
//     }
//     public void setData(Object data) {
//         this.data = data;
//     }
}

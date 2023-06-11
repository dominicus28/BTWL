package com.javatpoint.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.util.List;

//import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Status {
    @Id
    @JsonIgnore
    private ObjectId id;
    private String status;
    private int code;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Status() {
    }

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
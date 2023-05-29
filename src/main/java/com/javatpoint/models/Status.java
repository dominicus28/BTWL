package com.javatpoint.models;

//import java.util.List;

//import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Status {
    private String status;
    private int code;

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
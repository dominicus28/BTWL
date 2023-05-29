package com.javatpoint.models;

import org.springframework.data.annotation.Id;

public class DeliveryStatus {
    @Id
    private String id;
    private int time;
    // private Status status;
    private int status;

    public DeliveryStatus () {
    
    }

    public DeliveryStatus(int time, int status) {
        this.time = time;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int gettime() {
        return time;
    }

    public void settime(int time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}

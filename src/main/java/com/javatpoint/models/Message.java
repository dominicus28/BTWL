package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Message {
    private boolean protect;
    private boolean open;

    private boolean ack;

    public boolean getAck() {
        return ack;
    }

    public void setAck() {
        this.ack = true;
    }

    // public void setAck(boolean ack) {
    //     this.ack = ack;
    // }

    public Message() {
        protect = false;
        open = false;
    }

    public Message(boolean protect, boolean open) {
        super();
        this.open = open;
        this.protect = protect;
    }

    public boolean getOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean getProtect() {
        return protect;
    }

    public void setProtect(boolean protect) {
        this.protect = protect;
    }
}

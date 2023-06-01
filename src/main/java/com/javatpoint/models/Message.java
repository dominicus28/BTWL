package com.javatpoint.models;

public class Message {
    private boolean protect;
    private boolean open;

    private boolean ack;

    public boolean getAck() {
        return ack;
    }

    public void setAck(boolean ack) {
        this.ack = ack;
    }

    public Message() {}

    public Message(boolean protect, boolean open) {
        this.open = open;
        this.protect = protect;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isProtect() {
        return protect;
    }

    public void setProtect(boolean protect) {
        this.protect = protect;
    }
}

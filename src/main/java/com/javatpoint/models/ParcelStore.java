package com.javatpoint.models;

public class ParcelStore {
    private GeoCoordinates location;
    private DeliveryStatus status;
    private String address;
    private int identifier;

    public ParcelStore () {
    
    }

    // Constructor
    public ParcelStore(GeoCoordinates location, DeliveryStatus status, String address, int identifier) {
        this.location = location;
        this.status = status;
        this.address = address;
        this.identifier = identifier;
    }

    // Getters and Setters
    public GeoCoordinates getLocation() {
        return location;
    }

    public void setLocation(GeoCoordinates location) {
        this.location = location;
    }
    
    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }
}

package com.javatpoint.models;

public class ParcelStore extends Place{
    private GeoCoordinates location;
    private String nr;
    // private int identifier;

    public ParcelStore () {
    
    }

    // Constructor
    public ParcelStore(GeoCoordinates location, String nr, int identifier) {
        this.location = location;

        this.nr = nr;
        // this.identifier = identifier;
    }

    // Getters and Setters
    public GeoCoordinates getLocation() {
        return location;
    }

    public void setLocation(GeoCoordinates location) {
        this.location = location;
    }
    
    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    // public int getIdentifier() {
    //     return identifier;
    // }

    // public void setIdentifier(int identifier) {
    //     this.identifier = identifier;
    // }
}

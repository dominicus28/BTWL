package com.javatpoint.models;

public class Place {
    private Region region;
    private Address address;

    public Place() {}

    public Place(Region region, Address address) {
        super();
        this.region = region;
        this.address = address;
    }

    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

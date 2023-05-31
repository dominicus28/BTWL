package com.javatpoint.models;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

public class Place {
    @DocumentReference
    private Region region;
    private String street;
    private String nrOfHouse;   // and flat f.e. 35/24 or 35A

    public Place() {}

    public Place(Region region, String street, String nrOfHouse) {
        super();
        this.region = region;
        this.street = street;
        this.nrOfHouse = nrOfHouse;
    }

    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getNrOfHouse() {
        return nrOfHouse;
    }
    public void setNrOfHouse(String nrOfHouse) {
        this.nrOfHouse = nrOfHouse;
    }
}

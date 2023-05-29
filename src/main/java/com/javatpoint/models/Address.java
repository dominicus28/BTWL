package com.javatpoint.models;

public class Address {
    String street;
    String nrOfHouse;   // and flat f.e. 35/24 or 35A

    // default constructor
    public Address() {

    }
    // constructor using all fields
    public Address(String street, String nrOfHouse) {
        super();
        this.street = street;
        this.nrOfHouse = nrOfHouse;
    }

    //getters and setters
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

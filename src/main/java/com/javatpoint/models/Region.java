package com.javatpoint.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Region {
    @Id
    @JsonIgnore
    protected ObjectId id;
    public ObjectId getId() {
        return id;
    }
    public void setId(ObjectId id) {
        this.id = id;
    }
    private String country;
    private String province;
    private String city;
    private String postalCode;

    // default constructor
    public Region() {

    }
    // constructor using all fields
    public Region(String country, String province, String city, 
                    String postalCode) {
        super();
        this.country = country;
        this.province = province;
        this.city = city;
        this.postalCode = postalCode;
    }

    //getters and setters
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}

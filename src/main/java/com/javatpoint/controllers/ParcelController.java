package com.javatpoint.controllers;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;  
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.models.Parcel;
import com.javatpoint.services.IParcelService;  

@RestController
public class ParcelController {
    @Autowired
    private IParcelService parcelService;

    //mapping the getParcels() method to /parcels
    @GetMapping(value = "/parcels")
    public List<Parcel> getParcels()
    {
        //finds all the parcels
        List<Parcel> parcels = parcelService.findAll();
        //returns the parcel list
        return parcels;
    }
}

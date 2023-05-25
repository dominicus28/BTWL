package com.javatpoint.controllers;

import java.util.List;  
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.models.Parcel;
// import com.javatpoint.services.IParcelService;  
import com.javatpoint.repositories.ParcelRepository;

@RestController
public class ParcelController {
    private final ParcelRepository parcelRepository;

    public ParcelController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    //mapping the getParcels() method to /parcels
    @GetMapping(value = "user/{id}/parcels")
    public List<Parcel> getParcels(@PathVariable String id)
    {
        //finds all the parcels
        List<Parcel> parcels = parcelRepository.findAll();
        //returns the parcel list
        return parcels;
    }

    @GetMapping(value = "/parcels/{id}")
    public Parcel getParcel(@PathVariable String id)
    {
        //finds all the parcels
        Optional<Parcel> optionalParcel = parcelRepository.findById(id); 
        Parcel parcel = optionalParcel.get();

        return parcel; 
    }

    @GetMapping(value = "/parcels")
    public List<Parcel> getTelemetries()   
    {   
        List<Parcel> telemetries = parcelRepository.findAll();  
       
        return telemetries;
    } 
//    @PostMapping(value = "/parcels")
//    public Parcel postParcel(@RequestBody )
//    {
//        //finds all the parcels
//        List<Parcel> parcels = parcelRepository.findAll();
//        //returns the parcel list
//        return parcels;
//    }

    @PostMapping("/parcels")
    public Parcel createParcel(@RequestBody Parcel newParcel) {
        return parcelRepository.save(newParcel);
    }
}

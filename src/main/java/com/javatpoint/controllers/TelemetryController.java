package com.javatpoint.controllers;

import java.util.List;
import java.util.Optional;
// import java.util.Query;

// import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.javatpoint.models.Telemetry;
// import com.javatpoint.models.Parcel;
import com.javatpoint.repositories.ParcelCompleteRepository;
// import com.javatpoint.repositories.ParcelRepository;
import com.javatpoint.repositories.TelemetryRepository;
//import org.springframework.web.bind.annotation.PutMapping;
  

@RestController  
public class TelemetryController {  
    private final TelemetryRepository telemetryRepository;
    private final ParcelCompleteRepository parcelCompleteRepository;
    private final MongoTemplate mongoTemplate;

    public TelemetryController(TelemetryRepository telemetryRepository, ParcelCompleteRepository parcelCompleteRepository, MongoTemplate mongoTemplate) {
        this.telemetryRepository = telemetryRepository;
        this.parcelCompleteRepository = parcelCompleteRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /* Returns all telemetry data */
    @GetMapping(value = "/telemetries")
    public List<Telemetry> getTelemetries()   
    {   
        List<Telemetry> telemetries = telemetryRepository.findAll();  
       
        return telemetries;
    } 

    /* Get a particular telemetry */
    @GetMapping(value = "/telemetries/{id}")
    public Telemetry getTelemetry(@PathVariable String id)   
    {   
        Optional<Telemetry> optionalTelemetry = telemetryRepository.findById(id); 
        Telemetry telemetry = optionalTelemetry.get();

        return telemetry;  
    } 

    /* Post new telemetry */
    @PostMapping("/telemetries")
    public Telemetry createTelemetry(@RequestBody Telemetry newTelemetry) {
        Telemetry tel = telemetryRepository.save(newTelemetry);
        
        Query query = new Query().addCriteria(Criteria.where("box").is(tel.getMac()));
        Update update = new Update().addToSet("telemetry", newTelemetry);
        return null; //TODO
    }

    // @PostMapping("parcels/{id}/telemetries")
    // public Parcel createTelemetry1(@RequestBody Telemetry newTelemetry, @PathVariable String id) {
    //     Optional<Parcel> optionalParcel = parcelRepository.findById(id); 
    //     Parcel parcel = optionalParcel.get();
    //     parcel.addTelemetry(newTelemetry);

    //     return parcelRepository.insert(parcel);
    // }

    // /* Delete telemetry */
    // @DeleteMapping("/telemetries/{id}")
    // public Telemetry deleteTelemetry(@PathVariable String id) {
    //     Optional<Telemetry> optionalTelemetry = telemetryRepository.findById(id); 
    //     Telemetry telemetry = optionalTelemetry.get();
        
    //     telemetryRepository.deleteById(id);
        
    //     return telemetry;
    // }

    // @PutMapping(value="telemetries/{id}")
    // public Telemetry putTelemetry(@PathVariable String id, @RequestBody Telemetry newerTelemetry) {
    //     Optional<Telemetry> optionalTelemetry = telemetryRepository.findById(id); 
    //     Telemetry telemetry = optionalTelemetry.get();


        
    //     return telemetry;
    // }
}

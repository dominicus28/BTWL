package com.javatpoint.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.models.Telemetry;
import com.javatpoint.repositories.TelemetryRepository;
//import org.springframework.web.bind.annotation.PutMapping;
  

@RestController  
public class TelemetryController {  
    private final TelemetryRepository telemetryRepository;

    public TelemetryController(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
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
    public Telemetry getTelemety(@PathVariable String id)   
    {   
        Optional<Telemetry> optionalTelemetry = telemetryRepository.findById(id); 
        Telemetry telemetry = optionalTelemetry.get();

        return telemetry;  
    } 

    /* Post new telemetry */
    @PostMapping("/telemetries")
    public Telemetry createTelemetry(@RequestBody Telemetry newTelemetry) {
        return telemetryRepository.save(newTelemetry);
    }

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

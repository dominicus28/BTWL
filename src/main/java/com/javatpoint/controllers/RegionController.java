package com.javatpoint.controllers;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.javatpoint.models.ErrorMessage;
import com.javatpoint.models.Region;
import com.javatpoint.repositories.RegionRepository;

@RestController
public class RegionController {
    private final MongoTemplate mongoTemplate;
    private final RegionRepository regionRepository;

    public RegionController(MongoTemplate mongoTemplate,
                            RegionRepository regionRepository) {
        this.mongoTemplate = mongoTemplate;
        this.regionRepository = regionRepository;
    }

    //@GetMapping(value = "/regions/{regionName}/parcels")
    
    // Show all regions
    @GetMapping(value = "/regions")
    public List<Region> getTelemetries()   
    {   
        List<Region> telemetries = regionRepository.findAll();  
       
        return telemetries;
    }

    // Show regions by province
    // @GetMapping(value = "/regions/{provinceName}")
    // public List<Region> getRegionsbyProvince(@PathVariable String provinceName)  // LowerSilesia
    // {
    //     return regionRepository.findRegionByProvince(provinceName);
    // }

    // Show regions by city
    @GetMapping(value = "/regions/cities/{cityName}")
    public List<Region> getRegionsbyCity(@PathVariable String cityName)  // Wroclaw
    {
        return regionRepository.findRegionByCity(cityName);
    }

    // Add region
    @PostMapping("/regions")
    public ResponseEntity createRegion(@RequestBody Region newRegion) {
        if(newRegion.getCity() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("City is null"), null, HttpStatus.BAD_REQUEST);
        if(newRegion.getPostalCode() == null)
            return new ResponseEntity<ErrorMessage>(new ErrorMessage("Postal Code is null"), null, HttpStatus.BAD_REQUEST);

        return  new ResponseEntity<Region>(regionRepository.save(newRegion), null, HttpStatus.OK);
    }
}
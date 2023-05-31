package com.javatpoint.controllers;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

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

}
package com.javatpoint.controllers;

import java.util.List;
import java.util.Optional;
// import java.util.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.query.Query;

import com.javatpoint.models.Box;
import com.javatpoint.repositories.BoxRepository;
//import org.springframework.web.bind.annotation.PutMapping;
  

@RestController  
public class BoxController {
    private final BoxRepository boxRepository;
    private final MongoTemplate mongoTemplate;
    // private final ParcelCompleteRepository parcelCompleteRepository;

    public BoxController(BoxRepository boxRepository, MongoTemplate mongoTemplate) {
        this.boxRepository = boxRepository;
        this.mongoTemplate = mongoTemplate;
    }

    /* Returns all Box data */
    @GetMapping(value = "/boxes")
    public List<Box> getboxes()
    {
        return boxRepository.findAll();
    }

    /* Get a particular Box */
    @GetMapping(value = "/boxes/{id}")
    public Box getBox(@PathVariable String id)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("id").is(id));
        
        return mongoTemplate.findOne(query, Box.class);
    }

    /* Post new Box */
    @PostMapping("/boxes")
    public Box createBox(@RequestBody Box newBox) {
        return boxRepository.save(newBox);
    }

    @GetMapping("/boxes")
    public Box createBox(@RequestBody Box newBox) {
        return boxRepository.save(newBox);
    }
}
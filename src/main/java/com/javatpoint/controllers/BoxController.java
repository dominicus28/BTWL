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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.javatpoint.models.Box;
import com.javatpoint.models.Message;
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
    @GetMapping(value = "/boxes/{mac}")
    public Box getBox(@PathVariable String mac)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("mac").is(mac));
        
        return mongoTemplate.findOne(query, Box.class);
    }

    /* Post new Box */
    @PostMapping("/boxes")
    public Box createBox(@RequestBody Box newBox) {
        return boxRepository.save(newBox);
    }

    /* Get the requested operation */
    @GetMapping("/boxes/{mac}/idle")
    public ResponseEntity idleBox(@PathVariable String mac) {
        Box box = boxRepository.findOne(mac);
        return new ResponseEntity<Message>(box.getParcelComplete().getMessage(), null, HttpStatus.OK);
    }

    /* Put response to the requested operation */
    @PutMapping("/boxes/{mac}/idle")
    public ResponseEntity idleBox(@PathVariable String mac, @RequestBody Message message) {
        Box box = boxRepository.findOne(mac);
        box.getParcelComplete().get
        return boxRepository.save(newBox);
    }

    // @GetMapping("/boxes")
    // public Box createBox(@RequestBody Box newBox) {
    //     return boxRepository.save(newBox);
    // }
}
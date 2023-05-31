package com.javatpoint.controllers;
// import com.javatpoint.models.Status;
import com.javatpoint.repositories.StatusRepository;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.MongoTemplate;

//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Criteria;

@RestController  
public class StatusController {
    private final StatusRepository statusRepository;
    private final MongoTemplate mongoTemplate;
    // private final ParcelCompleteRepository parcelCompleteRepository;

    public StatusController(StatusRepository statusRepository, MongoTemplate mongoTemplate) {
        this.statusRepository = statusRepository;
        this.mongoTemplate = mongoTemplate;
    }


    // /* Post new status - box */
    //     @PostMapping("/boxes/{id}/messages")
    //     public Status statusBox(@RequestBody Status newStatus) {
    //         return statusRepository.save(newStatus);
    //     }

}
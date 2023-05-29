package com.javatpoint.controllers;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;  
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.javatpoint.models.Parcel;
import com.javatpoint.models.ParcelBrief;
import com.javatpoint.models.ParcelComplete;
import com.javatpoint.models.ParcelComplete_a2a;
import com.javatpoint.models.Status;
import com.javatpoint.models.TimestampObject;
import com.javatpoint.models.TimestampStatus;
import com.javatpoint.models.Alarm;
import com.javatpoint.models.User;
import com.javatpoint.models.Box;
import com.javatpoint.models.DeliveryStatus;
import com.javatpoint.repositories.ParcelBriefRepository;
import com.javatpoint.repositories.ParcelCompleteRepository;
// import com.javatpoint.services.IParcelService;  
import com.javatpoint.repositories.ParcelRepository;
import com.javatpoint.repositories.StatusRepository;

@RestController
public class ParcelController {
    private final MongoTemplate mongoTemplate;
    private final ParcelRepository parcelRepository;
    private final ParcelBriefRepository parcelBriefRepository;
    private final ParcelCompleteRepository parcelCompleteRepository;
    private final StatusRepository statusRepository;

    public ParcelController(ParcelRepository parcelRepository, 
                            MongoTemplate mongoTemplate,
                            ParcelBriefRepository parcelBriefRepository,
                            ParcelCompleteRepository parcelCompleteRepository,
                            StatusRepository statusRepository) {
        this.parcelRepository = parcelRepository;
        this.mongoTemplate = mongoTemplate;
        this.parcelBriefRepository = parcelBriefRepository;
        this.parcelCompleteRepository = parcelCompleteRepository;
        this.statusRepository = statusRepository;
    }

    /* Brief data of all sender's parcels */
    @GetMapping(value = "/user/{id}/senders/parcels")
    public List<ParcelBrief> getSendersParcels(@PathVariable String id)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("sender").is(id));
        
        List<Parcel> parcelList = mongoTemplate.find(query, Parcel.class);

        // ParcelBrief[] parcelBriefArr = new ParcelBrief[parcelList.size()];

        List<ParcelBrief> parcelBriefArr = new ArrayList<ParcelBrief>();

        for (Parcel parcel : parcelList) {
            Query parcelQuery = new Query();
            parcelQuery.addCriteria(Criteria.where("parcel").is(parcel.getId()));
            ParcelComplete pC = mongoTemplate.findOne(parcelQuery, ParcelComplete.class);

            parcelBriefArr.add(new ParcelBrief(parcel, pC.getLastTelemetry(),
                            pC.getLastAlarm(), pC.getLastStatus()));
        }

        return parcelBriefArr;
    }

// TODO to co wyżej dla recevier i courier

    /* Expanded data of a parcel */
    @GetMapping(value = "/parcels/{id}")
    public ParcelComplete getParcelComplete(@PathVariable String id)
    {
        Query query = new Query();
        
        query.addCriteria(Criteria.where("parcel").is(id));//parcel.id
        
        return mongoTemplate.findOne(query, ParcelComplete.class);
    }

    @GetMapping(value = "/parcels")
    public List<Parcel> getTelemetries()   
    {   
        List<Parcel> telemetries = parcelRepository.findAll();  
       
        return telemetries;
    } 

    /* Create new parcel of a2a type - sender 
     * Parcel waits for a courier to take the job - code 1
    */
    @PostMapping("/parcels/a2a")
    public Parcel createParcel(@RequestBody Parcel newParcel) {
        newParcel.setCourier(null);
        Parcel rec = parcelRepository.save(newParcel);
        parcelCompleteRepository.save(new ParcelComplete_a2a(rec));

        return rec;
    }

    /* Accept a job - courier 
     * Parcel waits for a courier to arrive - code 3
    */
    @PutMapping("/parcels/a2a/{id}/courier/1/accept")
    public ParcelComplete courierAcceptParcel(@PathVariable String id, @RequestBody User newCourier) {
        /* Find parcel */
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update().set("courier", newCourier.getLogin());
        Parcel rec = mongoTemplate.findAndModify(query, update, Parcel.class);

        /* Get id of the status code 3 */
        Query query0 = new Query();
        query0.addCriteria(Criteria.where("code").is(3));
        Status stat = mongoTemplate.findOne(query0, Status.class);
        
        /* Set new status with a timestamp */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(rec.getId()));
        Update update1 = new Update().addToSet("status", new TimestampStatus(stat));
        ParcelComplete_a2a recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete_a2a.class);

        return recComplete;
    }

    /* Assign a box to the parcel - courier
     * Parcel waits for a courier to arrive - set code 3
     */
    @PutMapping("/parcels/a2a/{id}/courier/3/assign")
    public Parcel courierAssignParcel(@PathVariable String id, @RequestBody Box newBox) {
        /* Assign a box to the parcel */
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update().set("box", newBox.getMac());
        Parcel rec = mongoTemplate.findAndModify(query, update, Parcel.class);

        //TODO check if box even exists
        /* Make sure that all informations have been cleared */
        // Query boxQuery = new Query();
        // boxQuery.addCriteria(Criteria.where("box").is(rec.getBox()));
        // Update boxUpdate = new Update().set("a2a_code3_open_courier_agree", false);
        // boxUpdate.set("a2a_code3_protect_box_open_closed", false);
        // boxUpdate.set("a2a_code3_protect_sender_agree", false);
        // boxUpdate.set("a2a_code5_idle_open_receiver_agree", false);
        // boxUpdate.set("a2a_code5_idle_open_courier_agree", false);
        // boxUpdate.set("a2a_code5_end_box_open_closed", false);
        // mongoTemplate.findAndModify(boxQuery, boxUpdate, Box.class);

        return rec;
    }
    
    /* Open the box that has been assigned to the parcel - courier
     * ?
     */
    @PutMapping("/parcels/a2a/{id}/courier/3/box/open")
    public Box courierOpenParcel(@PathVariable String id) {
        /* Find parcel complete */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(id));
        ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

        /* Get status code and the box */
        TimestampStatus obj = recComplete.getLastStatus();
        Status stat = obj.getStatus();
        Parcel parcel = recComplete.getParcel();
        Box box = parcel.getBox();

        /* Courier tells a box to open itself; only if current code is == 3 */
        if(stat.getCode() == 3) {
            /* Find box and set message to be sent */
            // Query boxQuery = new Query();
            // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
            Update update = new Update().set("a2a_code3_open_courier_agree", true);
            recComplete = mongoTemplate.findAndModify(query1, update, ParcelComplete_a2a.class);
            // return box;
        }

        return box;
    }

    /* Confirm that box has been closed - sender
     * Parcel awaits to be delivered to receiver - set code 5
     * Box open status should be cleaned by now
     */
    @PutMapping("/parcels/a2a/{id}/sender/3/box/protect")
    public ParcelComplete senderCloseParcel(@PathVariable String id) {
        // /* Find parcel */
        // Query query = new Query();
        // query.addCriteria(Criteria.where("id").is(id));
        // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

        /* Get full parcel data */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(id));
        ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

        /* Get status code and the box */
        TimestampStatus obj = recComplete.getLastStatus();
        Status stat = obj.getStatus();
        Parcel parcel = recComplete.getParcel();

        /* Find box */
        // Query boxQuery = new Query();
        // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
        // Box box = mongoTemplate.findOne(boxQuery, Box.class);

        /* Check if operation is allowed; check if code == 3 and lid was opened than closed */
        if(stat.getCode() == 3 && recComplete.a2a_code3_protect_box_opened_closed == true) {
            // /* Get id of the status code 5 */
            // Query query0 = new Query();
            // query0.addCriteria(Criteria.where("code").is(5));
            // Status newStat = mongoTemplate.findOne(query0, Status.class);
            
            // /* Add new status to parcel complete */
            // Update update1 = new Update().addToSet("status", new TimestampStatus(newStat));
            // recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete.class);

            /* Confirm that box has been closed && change state to protected */
            Update update1 = new Update().set("a2a_code3_protect_sender_agree", true); //protect delivery
            recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete_a2a.class);
        }
        
        return recComplete;
    }

    /* Box was delivered to receiver - courier
     * ?
     */
    @PutMapping("/parcels/a2a/{id}/courier/5/box/open")
    public Box courierDeliverParcel(@PathVariable String id) {
        // Query query = new Query();
        // query.addCriteria(Criteria.where("id").is(id));
        // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

        /* Get full parcel data */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(id));
        ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

        /* Find box */
        // Query boxQuery = new Query();
        // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
        // Box box = mongoTemplate.findOne(boxQuery, Box.class);
        TimestampStatus obj = recComplete.getLastStatus();
        Status stat = obj.getStatus();
        Parcel parcel = recComplete.getParcel();
        Box box = parcel.getBox();

        if(stat.getCode() == 5) {
            /* Tell box to open itself */
            // Query boxQuery = new Query();
            // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
            Update update = new Update().set("a2a_code5_idle_open_courier_agree", true);
            recComplete = mongoTemplate.findAndModify(query1, update, ParcelComplete_a2a.class);
        }
        return box;
    }

    /* Open the box after it was delivered to access the goods - receiver
     * 
     */
    @PutMapping("/parcels/a2a/{id}/receiver/5/box/open")
    public Box receiverOpenParcel(@PathVariable String id) {
        // Query query = new Query();
        // query.addCriteria(Criteria.where("id").is(id));
        // Parcel parcel = mongoTemplate.findOne(query, Parcel.class);

        // /* Find box */
        // Query boxQuery = new Query();
        // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
        // Box box = mongoTemplate.findOne(boxQuery, Box.class);
        /* Get full parcel data */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(id));
        ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

        /* Find box */
        TimestampStatus obj = recComplete.getLastStatus();
        Status stat = obj.getStatus();
        Parcel parcel = recComplete.getParcel();
        Box box = parcel.getBox();

        if(stat.getCode() == 5) {
            /* Tell box to open itself */
            // Query boxQuery = new Query();
            // boxQuery.addCriteria(Criteria.where("box").is(parcel.getBox()));
            Update update = new Update().set("a2a_code5_idle_open_receiver_agree", true);
            recComplete = mongoTemplate.findAndModify(query1, update, ParcelComplete_a2a.class);
        }

        return box;
    }

    /* End transaction - courier
     * ?
     */
    // @PutMapping("/parcels/a2a/{id}/courier/end")
    // public Parcel courierEndParcel() {
    //     // newParcel.setCourier(null);
    //     // Parcel rec = parcelRepository.save(newParcel);
    //     // parcelCompleteRepository.save(new ParcelComplete(rec, null, null, null));

    //     return rec;
    // }

    /* End transaction - receiver
     * 
     */
    @PutMapping("/parcels/a2a/{id}/receiver/5/end")
    public ParcelComplete receiverEndParcel(@PathVariable String id) {
        /* Get full parcel data */
        Query query1 = new Query();
        query1.addCriteria(Criteria.where("parcel").is(id));
        ParcelComplete_a2a recComplete = mongoTemplate.findOne(query1, ParcelComplete_a2a.class);

        /* Find box */
        TimestampStatus obj = recComplete.getLastStatus();
        Status stat = obj.getStatus();
        Parcel parcel = recComplete.getParcel();
        Box box = parcel.getBox();

        if(stat.getCode() == 5 && recComplete.a2a_code5_end_box_opened_closed == true) {
            /* Get id of the status code 3 */
            Query query0 = new Query();
            query0.addCriteria(Criteria.where("code").is(5));
            Status newStat = mongoTemplate.findOne(query0, Status.class);
            
            /* Get full parcel data *//* Add new status */
            Update update1 = new Update().addToSet("status", new TimestampStatus(newStat));
            recComplete = mongoTemplate.findAndModify(query1, update1, ParcelComplete_a2a.class);

            /* Confirm that box has been closed */
            // Update updateBox = new Update().set("currentState", 0);      //??? 
            // updateBox.set("lid", 0);
            // box = mongoTemplate.findAndModify(boxQuery, updateBox, Box.class);
        }
        
        return recComplete;
    }
}
